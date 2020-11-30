package commands.recommendtations;

import user.User;
import video.Video;

import java.util.ArrayList;

public final class BestUnseen {
    private static BestUnseen instance;

    private BestUnseen() {
    }

    public static BestUnseen getInstance() {
        if (instance == null) {
            instance = new BestUnseen();
        }
        return instance;
    }

    public Video getBestUnseen(final User user, final ArrayList<Video> videoArrayList) {
        Video videoForReturn = null;

        for (Video video:videoArrayList) {
            if (!user.getViewedVideos().containsKey(video.getName())) {
                if (videoForReturn == null || (video.getRating() > videoForReturn.getRating())) {
                    videoForReturn = video;
                }
            }
        }
        return videoForReturn;
    }
}
