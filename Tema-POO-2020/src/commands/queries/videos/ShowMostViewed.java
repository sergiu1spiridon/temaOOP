package commands.queries.videos;

import video.Show;
import video.Video;
import video.ViewedVideos;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ShowMostViewed {
    private static ShowMostViewed instance;

    private ShowMostViewed() {
    }

    /**
     * Method to get instance of class ShowMostViewed
     * @return
     */
    public static ShowMostViewed getInstance() {
        if (instance == null) {
            instance = new ShowMostViewed();
        }
        return instance;
    }

    /**
     * Returns list of shows sorted by their number of views
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

        ViewedVideos allViewedHash = ViewedVideos.getInstance();

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0);

            // check for the show year to be one of the years in filter
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

            // video has to be a show
            if (!(video instanceof Show)) {
                ok.set(0);
            }
            // check for one of the genres in filters to be
            // in the list of genres of the show
            genreFilter.forEach(genre -> {
                if (genre != null) {
                    if (!video.getGenres().contains(genre)) {
                        ok.set(0);
                    }
                }
            });

            int i = 0;
            while (true) {
                videoFromList.set(newList.get(i));
                if (videoFromList.get() == null) {
                    break;
                }
                if ((allViewedHash.getVideo(videoFromList.get().getName()) * ascending)
                        < (allViewedHash.getVideo(video.getName()) * ascending)) {
                    i++;
                } else if ((allViewedHash.getVideo(videoFromList.get().getName()) * ascending)
                        == (allViewedHash.getVideo(video.getName()) * ascending)) {
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
            if (allViewedHash.getVideo(video.getName()) != 0 && ok.intValue() == 1) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
