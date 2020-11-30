package commands.recommendtations;

import user.User;
import video.Genre;
import video.Video;
import video.ViewedVideos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;

public final class Popular {
    private LinkedList<Genre> sortedGenres;

    public Popular() {
        sortedGenres = new LinkedList<>();
    }

    /**
     * Create a list of genres sorted by the sum of views the videos
     * with each genre has
     * @param databaseVideos
     */
    public void createGenreList(final ArrayList<Video> databaseVideos) {
        ViewedVideos viewedVideos = ViewedVideos.getInstance();
        AtomicReference<Genre> genreToAddInList = new AtomicReference<>();
        databaseVideos.forEach((video) -> {
            int i = 0;
            /*
             if the video has a new genre object is created
             if the video has a genre that is in list that genre is popped
             from the list and the number of its views is recalculated and then
             the genre is added in it's new place in the sorted list
            */
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
                    } else {
                        break;
                    }
                }
                sortedGenres.add(i, genreToAddInList.get());
            }
        });
    }

    /**
     * Returns the first video unseen from the most popular genre
     * @param user
     * @return
     */
    public String getPopular(final User user) {
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
