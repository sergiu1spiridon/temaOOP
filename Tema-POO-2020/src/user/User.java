package user;

import video.Video;

import java.util.HashMap;

public abstract class User {
    private boolean standard;
    private boolean premium;
    private HashMap<String, Video> favouriteVideos;
    private HashMap<String, Video> viewedVideos;

    public User(boolean standard, boolean premium, HashMap<String, Video> favouriteVideos, HashMap<String, Video> viewedVideos) {
        this.standard = standard;
        this.premium = premium;
        this.favouriteVideos = favouriteVideos;
        this.viewedVideos = viewedVideos;
    }

    public boolean isStandard() {
        return standard;
    }

    public void setStandard(boolean standard) {
        this.standard = standard;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public HashMap<String, Video> getFavouriteVideos() {
        return favouriteVideos;
    }

    public void setFavouriteVideos(HashMap<String, Video> favouriteVideos) {
        this.favouriteVideos = favouriteVideos;
    }

    public HashMap<String, Video> getViewedVideos() {
        return viewedVideos;
    }

    public void setViewedVideos(HashMap<String, Video> viewedVideos) {
        this.viewedVideos = viewedVideos;
    }
}
