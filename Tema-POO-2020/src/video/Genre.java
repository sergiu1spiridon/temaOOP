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

    public void addViews(final int viewsNumber) {
        views += viewsNumber;
    }

    public void addVideosContained(final String videoName) {
        videosContained.add(videoName);
    }
}
