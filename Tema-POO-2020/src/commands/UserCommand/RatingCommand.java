package commands.UserCommand;

import user.User;
import video.RatedVideos;
import video.Show;
import video.ShowSeason;
import video.Video;

public class RatingCommand {
    private static RatingCommand instance;

    public static RatingCommand getInstance() {
        if (instance == null) {
            instance = new RatingCommand();
        }
        return instance;
    }

    public void addRating(User user, Video video, double grade) {
        RatedVideos videoToRate = new RatedVideos(video.getName(), 0);
        if (!user.getViewedVideos().containsKey(video.getName()))
            return;

        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate))
                return;
        }
        int numOfRatings = video.getNumberOfRatings();
        video.setRating((video.getRating() * numOfRatings + grade) / (numOfRatings + 1));
        video.setNumberOfRatings(numOfRatings + 1);
        user.getUserRatings().add(videoToRate);
    }

    public void addRating(User user, Video video, double grade, int seasonNumber) {
        RatedVideos videoToRate = new RatedVideos(video.getName(), seasonNumber);
        if (!user.getViewedVideos().containsKey(video.getName()))
        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate))
                return;
        }
        Show myShow = (Show) video;
        ShowSeason mySeason = myShow.getSeasons().get(seasonNumber - 1);
        int numOfRatings = mySeason.getNumberOfRatings();
        mySeason.setRating((mySeason.getRating() * numOfRatings + grade) / (numOfRatings + 1));
        mySeason.setNumberOfRatings(numOfRatings + 1);
        user.getUserRatings().add(videoToRate);
        double newShowRating = 0;
        for (ShowSeason i:myShow.getSeasons()) {
            newShowRating += i.getRating();
        }
        newShowRating /= myShow.getNumberOfSeasons();
        mySeason.setRating(newShowRating);
    }
}
