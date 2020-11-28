package video;

import java.util.ArrayList;

public class Genre {
    private String genreName;

    private int views;

    private ArrayList<String> videosContained;

    public Genre(String genreName) {
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

    public void addViews(int viewsNumber) {
        views += viewsNumber;
    }

    public void addVideosContained (String videoName) {
        videosContained.add(videoName);
    }
}
