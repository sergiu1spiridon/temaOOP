package commands.queries.videos;

import video.Movie;
import video.Show;
import video.Video;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ShowLongest {
    private static ShowLongest instance;

    private ShowLongest() {
    }

    public static ShowLongest getInstance() {
        if (instance == null) {
            instance = new ShowLongest();
        }
        return instance;
    }

    public LinkedList<Video> getShowList(Hashtable<String, Video> videosArray, int ascending, List<String> yearFilter, List<String> genreFilter) {
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

            if (!(video instanceof Show)) {
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
                if ((videoFromList.get().getDuration() * ascending) < (video.getDuration() * ascending)) {
                    i++;
                }
                else if (videoFromList.get().getDuration() == video.getDuration()) {
                    if (ascending == 1) {
                        if (videoFromList.get().getName().compareTo(video.getName()) < 0) {
                            i++;
                        }
                        else
                            break;
                    }
                    else {
                        if (videoFromList.get().getName().compareTo(video.getName()) > 0) {
                            i++;
                        }
                        else
                            break;
                    }
                }
                else {
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
