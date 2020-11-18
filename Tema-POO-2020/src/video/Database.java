package video;

import actor.Actor;
import commands.UserCommand.FavoriteCommand;
import commands.UserCommand.RatingCommand;
import commands.UserCommand.ViewCommand;
import commands.queries.actors.Average;
import commands.queries.actors.Awards;
import entertainment.Season;
import fileio.*;
import user.User;

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
    public void initFields() {
        this.setVideosArray(input);
        this.setUsersArray(input);
        this.setActorsArray(input);
        this.getCommands(input);

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

    private void getCommands(Input input) {
        String videoName, userName;
        for(ActionInputData action:input.getCommands()) {
            if (action.getActionType().equals("command")) {

                String actionType = action.getType();

                if (actionType != null) {
                    switch (actionType) {
                        case "favorite" -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            FavoriteCommand.getInstance().addFavorite(usersArray.get(userName),
                                    videosArray.get(videoName));
                            //System.out.println("In favorite");
                        }
                        case "view" -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            ViewCommand.getInstance().addView(usersArray.get(userName),
                                    videosArray.get(videoName));
                            //System.out.println("In view");
                        }
                        default -> {
                            videoName = action.getTitle();
                            userName = action.getUsername();
                            if (action.getSeasonNumber() == 0) {
                                RatingCommand.getInstance().addRating(usersArray.get(userName),
                                        videosArray.get(videoName), action.getGrade());
                            } else {
                                RatingCommand.getInstance().addRating(usersArray.get(userName),
                                        videosArray.get(videoName), action.getGrade(), action.getSeasonNumber());
                                //System.out.println(action.getSeasonNumber());
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
                                //System.out.println(actorLinkedList.size());
                                actorLinkedList.forEach(actor -> System.out.println(actor.getName() + " " + actor.getRating()));
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
                        }
                    }
                }
            }
        }
    }
}
