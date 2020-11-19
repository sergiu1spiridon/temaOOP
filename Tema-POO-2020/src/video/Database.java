package video;

import actor.Actor;
import commands.UserCommand.FavoriteCommand;
import commands.UserCommand.RatingCommand;
import commands.UserCommand.ViewCommand;
import commands.queries.actors.Average;
import commands.queries.actors.Awards;
import commands.queries.actors.FilterDescription;
import entertainment.Season;
import fileio.*;
import org.json.simple.JSONArray;
import user.User;
import fileio.Writer;

import java.io.IOException;
import java.util.*;

public class Database {
    Input input;

    Hashtable<String, User> usersArray;
    Hashtable<String, Video> videosArray;
    ArrayList<Actor> actorsArray;
    //ArrayList<Command> commandArray

    public Database(Input input) {
        this.input = input;
    }

    public void initFields(JSONArray arrayResult, Writer fileWriter) throws IOException {
        this.setVideosArray(input);
        this.setUsersArray(input);
        this.setActorsArray(input);
        this.getCommands(input, arrayResult, fileWriter);

    }
    private void setUsersArray(Input input) {
        usersArray = new Hashtable<>(0);

        for (UserInputData inputUser:input.getUsers()) {
            User myUser = new User(inputUser.getUsername(), inputUser.getSubscriptionType(),
                    inputUser.getFavoriteMovies(), inputUser.getHistory());

            //System.out.println(myUser);
            ViewedVideos inst = ViewedVideos.getInstance();

            Map<String, Integer> viewedByUser = myUser.getViewedVideos();

            viewedByUser.forEach((String k,Integer v) -> inst.addVideo(k, v));


            usersArray.put(inputUser.getUsername(), myUser);
        }
    }

    private void setVideosArray(Input input) {
        videosArray = new Hashtable<>(0);

        for (MovieInputData inputMovie:input.getMovies()
             ) {
            Movie myMovie = new Movie(inputMovie.getTitle(), inputMovie.getYear(), inputMovie.getGenres(),
                    inputMovie.getDuration());
            videosArray.put(inputMovie.getTitle(), myMovie);
        }

        for (SerialInputData inputShow:input.getSerials()
        ) {
            ArrayList<ShowSeason> showSeasons = new ArrayList<>(0);
            int currentseason = 0;

            for (Season inputShowSeason :inputShow.getSeasons()) {
                currentseason++;
                ShowSeason mySeason = new ShowSeason(currentseason, inputShowSeason.getDuration(), inputShowSeason.getRatings());

                showSeasons.add(mySeason);
            }

            Show myShow = new Show(inputShow.getTitle(), inputShow.getYear(), inputShow.getGenres(),
                    showSeasons, inputShow.getNumberSeason());

            videosArray.put(inputShow.getTitle(), myShow);
        }
    }

    private void setActorsArray(Input input) {
        actorsArray = new ArrayList<>(0);

        for (ActorInputData inputActor: input.getActors()) {
            Actor myActor = new Actor(inputActor.getName(), inputActor.getCareerDescription(),
                    inputActor.getFilmography(), inputActor.getAwards());

            myActor.calculateNumberOfAwards();

            actorsArray.add(myActor);
        }
    }

    private void getCommands(Input input, JSONArray arrayResult, Writer fileWriter) throws IOException {
        String videoName, userName;
        for(ActionInputData action:input.getCommands()) {
            if (action.getActionType().equals("command")) {

                String actionType = action.getType();

                if (actionType != null) {
                    switch (actionType) {
                        case "favorite" -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            int success = FavoriteCommand.getInstance().addFavorite(usersArray.get(userName),
                                    videosArray.get(videoName));
                            if (success == 2) {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "success -> " + videoName + " was added as favourite"));
                            }
                            else if (success == 1) {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "error -> " + videoName + " is already in favourite list"));
                            }
                            else {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "error -> " + videoName + " is not seen"));
                            }
                        }
                        case "view" -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            ViewCommand.getInstance().addView(usersArray.get(userName),
                                    videosArray.get(videoName));
                            arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                    "success -> " + videoName + " was viewed with total views of "
                                            + usersArray.get(userName).getViewedVideos().get(videoName)));
                            //System.out.println("In view");
                        }
                        default -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            int success;
                            if (action.getSeasonNumber() == 0) {
                                success = RatingCommand.getInstance().addRating(usersArray.get(userName),
                                        videosArray.get(videoName), action.getGrade());
                            } else {
                                success = RatingCommand.getInstance().addRating(usersArray.get(userName),
                                        videosArray.get(videoName), action.getGrade(), action.getSeasonNumber());
                            }
                            if (success == 0) {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "error -> " + videoName + " is not seen"));
                            }
                            else if (success == 1) {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "error -> " + videoName + " has been already rated"));
                            }
                            else {
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?",
                                        "success -> " + videoName + " was rated with " + action.getGrade() + " by " +
                                        userName));
                            }
                        }
                    }
                }
            }
            if (action.getActionType().equals("query")) {
                String objectType = action.getObjectType();

                if(objectType!=null && objectType.equals("actors"))
                {
                    String actionType = action.getCriteria();
                    if (actionType != null) {
                        switch (actionType) {
                            case "average" -> {
                                int ascending = 1;
                                if (action.getSortType().equals("desc"))
                                    ascending = -1;
                                actorsArray.forEach((actor -> actor.calculateRating(videosArray)));
                                Average actorsAverage = Average.getInstance();

                                LinkedList<Actor> actorLinkedList = actorsAverage.getRatingList(actorsArray, ascending);
                                while (actorLinkedList.size() > action.getNumber()) {
                                    actorLinkedList.remove(action.getNumber());
                                }
                                arrayResult.add(fileWriter.writeFile(action.getActionId(), "?", "Query result: " +
                                        actorLinkedList));
                            }

                            case "awards" -> {
                                List<String> awardToSearch = action.getFilters().get(3);
                                int ascending = 1;

                                if (action.getSortType().equals("desc"))
                                    ascending = -1;

                                Awards actorsAwards = Awards.getInstance();
                                System.out.println();
                                System.out.println(awardToSearch);

                                LinkedList<Actor> actorLinkedList = actorsAwards.getAwardsList(actorsArray, awardToSearch, ascending);

                                actorLinkedList.forEach(actor -> System.out.println(actor.getName() + " " + actor.getNumberOfAwards() +
                                        " " + actor.getAwards()));
                            }

                            case "filter_description" -> {
                                List<String> wordsToSearch = action.getFilters().get(2);
                                int ascending = 1;

                                if (action.getSortType().equals("desc"))
                                    ascending = -1;

                                FilterDescription filter = FilterDescription.getInstance();

                                LinkedList<Actor> filteredActors = filter.getFilteredActors(actorsArray, wordsToSearch, ascending);

                                filteredActors.forEach(actor -> System.out.println(actor.getName()));
                            }
                        }
                    }
                }
            }
        }
    }
}
