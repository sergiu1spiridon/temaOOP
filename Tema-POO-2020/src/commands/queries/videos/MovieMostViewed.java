package commands.queries.videos;

import video.Movie;
import video.Video;
import video.ViewedVideos;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MovieMostViewed {
    private static MovieMostViewed instance;

    private MovieMostViewed() {
    }

    /**
     * Method to get instance of class MovieMostViewed
     * @return
     */
    public static MovieMostViewed getInstance() {
        if (instance == null) {
            instance = new MovieMostViewed();
        }
        return instance;
    }

    /**
     * Rteurns list of movies sorted by their number of views
     * @param videosArray // array of videos, not just movies
     * @param ascending
     * @param yearFilter
     * @param genreFilter
     * @return
     */
    public LinkedList<Video> getMovieList(final Hashtable<String, Video> videosArray,
           final int ascending, final List<String> yearFilter, final List<String> genreFilter) {
        LinkedList<Video> newList = new LinkedList<>();

        newList.add(null);

        ViewedVideos allViewedHash = ViewedVideos.getInstance();

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0); // will be 1 if year filter is met

            // check for year filter
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
            // check for the video to be a movie
            if (!(video instanceof Movie)) {
                ok.set(0);
            }
            // check for genre filer. ok will be 0 if it's not met
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
