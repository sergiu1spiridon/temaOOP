package commands.recommendtations;

import user.User;
import video.Video;

import java.util.ArrayList;
import java.util.LinkedList;

public final class Favorite {
    public Favorite() {
    }

    public Video getFavorite(final User user, final ArrayList<Video> videosArray) {
        LinkedList<Video> sortedVideos = new LinkedList<>();
        int i = 0;
        Video videoFromList;

        for (Video video:videosArray) {
            i = 0;
            while (i < sortedVideos.size()) {
                videoFromList = sortedVideos.get(i);
                if (videoFromList.getFavored() >= video.getFavored()) {
                    i++;
                } else {
                    break;
                }
            }
            sortedVideos.add(i, video);
        }
        for (Video video:sortedVideos) {
            if (!user.getViewedVideos().containsKey(video.getName())) {
                return video;
            }
        }
        return null;
    }
}
