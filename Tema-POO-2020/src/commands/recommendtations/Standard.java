package commands.recommendtations;

import user.User;
import video.Video;

import java.util.ArrayList;

public final class Standard {
    private static Standard instance;

    private Standard() {
    }

    /**
     * Method to ge instance for singleton class Standard
     * @return
     */
    public static Standard getInstance() {
        if (instance == null) {
            instance = new Standard();
        }
        return instance;
    }

    /**
     * Returns the first video not seen by user from the database
     * @param user
     * @param videosArray
     * @return
     */
    public Video getStandard(final User user, final ArrayList<Video> videosArray) {
        for (Video video:videosArray) {
            if (!user.getViewedVideos().containsKey(video.getName())) {
                return video;
            }
        }
        return null;
    }


}
