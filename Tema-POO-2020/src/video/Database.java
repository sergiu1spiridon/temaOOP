package video;

import actor.Actor;
import commands.UserCommand.FavoriteCommand;
import entertainment.Season;
import fileio.*;
import user.User;

import java.util.ArrayList;
import java.util.Hashtable;

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
        this.setUsersArray(input);
        this.setVideosArray(input);
        this.setUsersArray(input);
        this.getCommands(input);

    }
    private void setUsersArray(Input input) {
        usersArray = new Hashtable<>(0);

        for (UserInputData inputUser:input.getUsers()) {
            User myUser = new User(inputUser.getUsername(), inputUser.getSubscriptionType(),
                    inputUser.getFavoriteMovies(), inputUser.getHistory());

            System.out.println(myUser);
            ViewedVideos inst = ViewedVideos.getInstance();

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
            ArrayList<ShowSeason> showSeasons = new ArrayList<ShowSeason>(0);
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
        actorsArray = new ArrayList<Actor>(0);

        for (ActorInputData inputActor: input.getActors()) {
            Actor myActor = new Actor(inputActor.getName(), inputActor.getCareerDescription(),
                    inputActor.getFilmography(), inputActor.getAwards());

            actorsArray.add(myActor);
        }
    }

    private void getCommands(Input input) {
        for(ActionInputData action:input.getCommands()) {
            if (action.getActionType() == "command" && action.getType() == "favorite") {
                String videoName = action.getTitle();
                String userName = action.getUsername();
                FavoriteCommand.addFavorite(usersArray.get(userName), videosArray.get(videoName));
            }
        }
    }
}