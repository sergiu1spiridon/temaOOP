package commands.queries.videos;

import video.Show;
import video.Video;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ShowFavorite {
    private static ShowFavorite instance;

    private ShowFavorite() {
    }

    /**
     * Method to get instance of singleton ShowFavorite class
     * @return
     */
    public static ShowFavorite getInstance() {
        if (instance == null) {
            instance = new ShowFavorite();
        }
        return instance;
    }

    /**
     * Returns list of shows sorted by the number of times they
     * appear in the lists of favorite videos
     * @param videosArray // array of videos, not just shows
     * @param ascending
     * @param yearFilter
     * @param genreFilter
     * @return
     */
    public LinkedList<Video> getShowList(final Hashtable<String, Video> videosArray,
           final int ascending, final List<String> yearFilter, final List<String> genreFilter) {
        LinkedList<Video> newList = new LinkedList<>();

        newList.add(null);

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0);
            // check for the year of the show t be one of the
            // ones in the query
            if (yearFilter != null) {
                ok.set(0);
                yearFilter.forEach(year -> {
                    if (year != null) {
                        if (year.equals(Integer.toString(video.getYear()))) {
                            ok.set(1);
                        }
                    } else {
                        ok.set(1);
                    }
                });
            }

            // check for the video to be a show.
            if (!(video instanceof Show)) {
                ok.set(0);
            }
            // check for the genres in filter
            genreFilter.forEach(genre -> {
                if (genre != null) {
                    if (!video.getGenres().contains(genre)) {
                        ok.set(0);
                    }
                }
            });

            // sorting
            int i = 0;
            while (true) {
                videoFromList.set(newList.get(i));
                if (videoFromList.get() == null) {
                    break;
                }
                if ((videoFromList.get().getFavored() * ascending)
                        < (video.getFavored() * ascending)) {
                    i++;
                } else if (videoFromList.get().getFavored() == video.getFavored()) {
                    if (ascending == 1) {
                        if (videoFromList.get().getName().compareTo(video.getName()) < 0) {
                            i++;
                        } else {
                            break;
                        }
                    } else {
                        if (videoFromList.get().getName().compareTo(video.getName()) > 0) {
                            i++;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
            if (video.getFavored() != 0 && ok.intValue() == 1) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
