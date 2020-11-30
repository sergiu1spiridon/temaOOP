package commands.recommendtations;

import user.User;
import video.Video;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class Search {
    public Search() {

    }

    /**
     * Returns list of videos that have genreFilter in their genres, sorted by rating
     * @param videosArray
     * @param genreFilter
     * @param user
     * @return
     */
    public LinkedList<Video> getVideoList(final Hashtable<String, Video> videosArray,
                     final String genreFilter, final User user) {
        LinkedList<Video> newList = new LinkedList<>();

        newList.add(null);

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(1);
            // check for genreFiler to be in video's genres
            if (!video.getGenres().contains(genreFilter)) {
                ok.set(0);
            }

            int i = 0;
            while (true) {
                videoFromList.set(newList.get(i));
                if (videoFromList.get() == null) {
                    break;
                }
                if ((videoFromList.get().getRating()) < (video.getRating())) {
                    i++;
                } else if (videoFromList.get().getRating() == video.getRating()) {
                        if (videoFromList.get().getName().compareTo(video.getName()) < 0) {
                            i++;
                        } else {
                            break;
                        }
                } else {
                    break;
                }
            }
            if (ok.intValue() == 1 && (!user.getViewedVideos().containsKey(videoName))) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
