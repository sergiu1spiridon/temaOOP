package commands.UserCommand;

import user.User;
import video.RatedVideos;
import video.Show;
import video.ShowSeason;
import video.Video;

public final class RatingCommand {
    private static RatingCommand instance;

    /**
     * Method to get instance of RatingCommand class
     * @return
     */
    public static RatingCommand getInstance() {
        if (instance == null) {
            instance = new RatingCommand();
        }
        return instance;
    }

    /**
     * Adds a rating to a movie viewed by user
     * @param user
     * @param video
     * @param grade
     * @return
     */
    public int addRating(final User user, final Video video, final double grade) {
        RatedVideos videoToRate = new RatedVideos(video.getName(), 0);
        if (!user.getViewedVideos().containsKey(video.getName())) {
            return 0; // video was not seen by user
        }

        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate)) {
                return 1; // video is already rated by user
            }
        }
        int numOfRatings = video.getNumberOfRatings();
        video.setRating((video.getRating() * numOfRatings + grade) / (numOfRatings + 1));
        video.setNumberOfRatings(numOfRatings + 1);
        user.getUserRatings().add(videoToRate);
        user.setNumberOfRatings(user.getNumberOfRatings() + 1);
        return 2; // video rated with success
    }

    /**
     * Adds rating to a season of a show viewed by user
     * @param user
     * @param video
     * @param grade
     * @param seasonNumber
     * @return
     */
    public int addRating(final User user, final Video video, final double grade,
                         final int seasonNumber) {
        // videoToRate is the season that needs to be rated
        RatedVideos videoToRate = new RatedVideos(video.getName(), seasonNumber);
        if (!user.getViewedVideos().containsKey(video.getName())) {
            return 0; // show is not seen
        }
        for (RatedVideos i:user.getUserRatings()) {
            if (i.equals(videoToRate)) {
                return 1; // season is already rated by user
            }
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
        return 2; // rated with success
    }
}
