package commands.recommendtations;

import user.User;
import video.Genre;
import video.Video;
import video.ViewedVideos;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public class Popular {
    LinkedList<Genre> sortedGenres;

    public Popular() {
        sortedGenres = new LinkedList<>();
    }

    public void createGenreList(ArrayList<Video> databaseVideos) {
        ViewedVideos viewedVideos = ViewedVideos.getInstance();
        AtomicReference<Genre> genreToAddInList = new AtomicReference<>();
        databaseVideos.forEach((video) -> {
            int i = 0;
            for (String genreName:video.getGenres()) {
                for (i = 0; i < sortedGenres.size(); i++) {
                    if (sortedGenres.get(i).getGenreName().equals(genreName)) {
                        genreToAddInList.set(sortedGenres.get(i));
                        sortedGenres.remove(i);
                        i--;
                        break;
                    }
                }
                if (i == sortedGenres.size()) {
                    genreToAddInList.set(new Genre(genreName));
                }

                genreToAddInList.get().addViews(viewedVideos.getVideo(video.getName()));
                genreToAddInList.get().addVideosContained(video.getName());
                i = 0;
                Genre genreFromList;
                while (i < sortedGenres.size()) {
                    genreFromList = sortedGenres.get(i);

                    if (genreFromList.getViews() > genreToAddInList.get().getViews()) {
                        i++;
                    } else
                        break;
                }
                sortedGenres.add(i, genreToAddInList.get());
            }
        });
    }

    public String getPopular(User user) {
        for (Genre genre:sortedGenres) {
            for (String videoName:genre.getVideosContained()) {
                if (!user.getViewedVideos().containsKey(videoName)) {
                    return videoName;
                }
            }
        }
        return null;
    }
}
