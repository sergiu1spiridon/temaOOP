package commands.queries.videos;

import video.Movie;
import video.Video;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class MovieFavorite {
    private static MovieFavorite instance;

    private MovieFavorite() {
    }

    /**
     * Method to get instance of singleton MovieFavorite class
     * @return
     */
    public static MovieFavorite getInstance() {
        if (instance == null) {
            instance = new MovieFavorite();
        }
        return instance;
    }

    /**
     * Returns list of movies sorted by the number of times they
     * appear in the lists of favorite movies
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

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0);
            // check if the year of the movie appears in the list
            // of years the user has given in the query
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
            // check for the movie to have one of the needed genres
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
            // ok is 1 if the movie meets the year and genre requirements
            if (video.getFavored() != 0 && ok.intValue() == 1) {
                newList.add(i, video);
            }

        });

        newList.remove(null);

        return newList;
    }
}
