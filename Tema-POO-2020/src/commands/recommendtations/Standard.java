package commands.recommendtations;

import user.User;
import video.Video;

import java.util.ArrayList;

public final class Standard {
    private static Standard instance;

    private Standard() {
    }

    public static Standard getInstance() {
        if (instance == null) {
            instance = new Standard();
        }
        return instance;
    }

    public Video getStandard(final User user, final ArrayList<Video> videosArray) {
        for (Video video:videosArray) {
            if (!user.getViewedVideos().containsKey(video.getName())) {
                return video;
            }
        }
        return null;
    }


}
