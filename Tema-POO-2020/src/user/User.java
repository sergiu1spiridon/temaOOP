package user;

import video.RatedVideos;

import java.util.ArrayList;
import java.util.Map;

public final class User {
    private String userName;
    private String submisionType;
    private ArrayList<String> favouriteVideos;
    private Map<String, Integer> viewedVideos;
    private ArrayList<RatedVideos> userRatings;
    private int numberOfRatings;

    public User(final String userName, final String submisionType,
                final ArrayList<String> favouriteVideos, final Map<String, Integer> viewedVideos) {
        this.userName = userName;
        this.submisionType = submisionType;
        this.favouriteVideos = favouriteVideos;
        this.viewedVideos = viewedVideos;
        this.userRatings = new ArrayList<>(0);
        this.numberOfRatings = 0;
    }

    public String getSubmisionType() {
        return submisionType;
    }

    public void setSubmisionType(final String submisionType) {
        this.submisionType = submisionType;
    }

    public ArrayList<String> getFavouriteVideos() {
        return favouriteVideos;
    }

    public void setFavouriteVideos(final ArrayList<String> favouriteVideos) {
        this.favouriteVideos = favouriteVideos;
    }

    public Map<String, Integer> getViewedVideos() {
        return viewedVideos;
    }

    public void setViewedVideos(final Map<String, Integer> viewedVideos) {
        this.viewedVideos = viewedVideos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public ArrayList<RatedVideos> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(final ArrayList<RatedVideos> userRatings) {
        this.userRatings = userRatings;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    @Override
    public String toString() {
        return userName;
    }
}
