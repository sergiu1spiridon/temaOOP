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

    public static MovieFavorite getInstance() {
        if (instance == null) {
            instance = new MovieFavorite();
        }
        return instance;
    }

    public LinkedList<Video> getMovieList(final Hashtable<String, Video> videosArray,
            final int ascending, final List<String> yearFilter, final List<String> genreFilter) {
        LinkedList<Video> newList = new LinkedList<>();

        newList.add(null);

        AtomicReference<Video> videoFromList = new AtomicReference<>();

        AtomicInteger ok = new AtomicInteger(1);

        videosArray.forEach((videoName, video) -> {
            ok.set(0);

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

            if (!(video instanceof Movie)) {
                ok.set(0);
            }
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
