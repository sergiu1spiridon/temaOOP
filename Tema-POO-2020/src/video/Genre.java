package video;

import java.util.ArrayList;

public final class Genre {
    private String genreName;

    private int views;

    private ArrayList<String> videosContained;

    public Genre(final String genreName) {
        this.genreName = genreName;
        this.views = 0;
        videosContained = new ArrayList<>();
    }

    public String getGenreName() {
        return genreName;
    }

    public int getViews() {
        return views;
    }

    public ArrayList<String> getVideosContained() {
        return videosContained;
    }

    /**
     * Adds views number to the genre instanc
     * @param viewsNumber
     */
    public void addViews(final int viewsNumber) {
        views += viewsNumber;
    }

    /**
     * Adds a video to the list of videos that have the genre
     * @param videoName
     */
    public void addVideosContained(final String videoName) {
        videosContained.add(videoName);
    }
}
