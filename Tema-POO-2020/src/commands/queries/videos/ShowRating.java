package commands.queries.videos;

import video.Show;
import video.Video;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ShowRating {
    private static ShowRating instance;

    private ShowRating() {
    }

    /**
     * Method to get instance of singleton class ShowRating
     * @return
     */
    public static ShowRating getInstance() {
        if (instance == null) {
            instance = new ShowRating();
        }
        return instance;
    }

    /**
     * Returns list of shows sorted by their rating
     * @param videosArray
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
            ok.set(1);
            // check for year to be in year filter list
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

            if (!(video instanceof Show)) {
                ok.set(0);
            }
            // check for one of the genres in the filter to be in the show's genre list
            genreFilter.forEach(genre -> {
                if (!video.getGenres().contains(genre)) {
                    ok.set(0);
                }
            });

            int i = 0;
            while (true) {
                videoFromList.set(newList.get(i));
                if (videoFromList.get() == null) {
                    break;
                }
                if ((videoFromList.get().getRating() * ascending)
                        < (videoFromList.get().getRating() * ascending)) {
                    i++;
                } else if (videoFromList.get().getRating() == video.getRating()) {
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
            if (video.getRating() != 0 && ok.intValue() == 1) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
