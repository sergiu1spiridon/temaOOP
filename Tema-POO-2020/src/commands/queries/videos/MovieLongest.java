package commands.queries.videos;

import video.Movie;
import video.Video;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MovieLongest {
    private static MovieLongest instance;

    private MovieLongest() {
    }

    /**
     * Method to get instance of singleton class MovieLongest
     * @return
     */
    public static MovieLongest getInstance() {
        if (instance == null) {
            instance = new MovieLongest();
        }
        return instance;
    }

    /**
     * Returns list of movies sorted by their length
     * @param videosArray // hashtable of videos, not just movies
     * @param ascending
     * @param yearFilter
     * @param genreFilter
     * @return
     */
    public LinkedList<Video> getMovieList(final Hashtable<String, Video> videosArray,
             final int ascending, final List<String> yearFilter, final List<String> genreFilter) {
        LinkedList<Video> newList = new LinkedList<>();

        newList.add(null);

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0);
            // checkk for the year filter
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
            // check for the genre filter
            genreFilter.forEach(genre -> {
                if (genre != null) {
                    if (!video.getGenres().contains(genre)) {
                        ok.set(0);
                    }
                }
            });

            // putting the movie in it's place in the sorted list
            int i = 0;
            while (true) {
                videoFromList.set(newList.get(i));
                if (videoFromList.get() == null) {
                    break;
                }
                if ((videoFromList.get().getDuration() * ascending)
                        < (video.getDuration() * ascending)) {
                    i++;
                } else if (videoFromList.get().getDuration() == video.getDuration()) {
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
            if (video.getDuration() != 0 && ok.intValue() == 1) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
