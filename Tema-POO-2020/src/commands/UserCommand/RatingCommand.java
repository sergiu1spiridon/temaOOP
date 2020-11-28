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

    public int addRating(User user, Video video, double grade) {
        RatedVideos videoToRate = new RatedVideos(video.getName(), 0);
        if (!user.getViewedVideos().containsKey(video.getName()))
            return 0;

        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate))
                return 1;
        }
        int numOfRatings = video.getNumberOfRatings();
        video.setRating((video.getRating() * numOfRatings + grade) / (numOfRatings + 1));
        video.setNumberOfRatings(numOfRatings + 1);
        user.getUserRatings().add(videoToRate);
        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
        return 2;
    }

    public int addRating(User user, Video video, double grade, int seasonNumber) {
        RatedVideos videoToRate = new RatedVideos(video.getName(), seasonNumber);
        if (!user.getViewedVideos().containsKey(video.getName())) {
            return 0;
        }
        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate))
                return 1;
        }
        Show myShow = (Show) video;
        ShowSeason mySeason = myShow.getSeasons().get(seasonNumber - 1);
        int numOfRatings = mySeason.getNumberOfRatings();
        mySeason.setRating((mySeason.getRating() * numOfRatings + grade) / (numOfRatings + 1));
        mySeason.setNumberOfRatings(numOfRatings + 1);
        user.getUserRatings().add(videoToRate);
        double newShowRating = 0;
        for (ShowSeason i:myShow.getSeasons()) {
            newShowRating = newShowRating + i.getRating();
        }
        newShowRating = newShowRating / myShow.getNumberOfSeasons();
        myShow.setNumberOfRatings(myShow.getNumberOfRatings() + 1);
        myShow.setRating(newShowRating);
        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
        return 2;
    }
}
