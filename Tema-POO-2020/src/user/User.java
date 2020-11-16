package user;

import video.Video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    String userName;
    String submisionType;
    private ArrayList<String> favouriteVideos;
    private Map<String, Integer> viewedVideos;

    public User(String userName, String submisionType, ArrayList<String> favouriteVideos, Map<String, Integer> viewedVideos) {
        this.userName = userName;
        this.submisionType = submisionType;
        this.favouriteVideos = favouriteVideos;
        this.viewedVideos = viewedVideos;
    }

    public String getSubmisionType() {
        return submisionType;
    }

    public void setSubmisionType(String submisionType) {
        this.submisionType = submisionType;
    }

    public ArrayList<String> getFavouriteVideos() {
        return favouriteVideos;
    }

    public void setFavouriteVideos(ArrayList<String> favouriteVideos) {
        this.favouriteVideos = favouriteVideos;
    }

    public Map<String, Integer> getViewedVideos() {
        return viewedVideos;
    }

    public void setViewedVideos(Map<String, Integer> viewedVideos) {
        this.viewedVideos = viewedVideos;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", submisionType='" + submisionType + '\'' +
                ", favouriteVideos=" + favouriteVideos +
                ", viewedVideos=" + viewedVideos +
                '}';
    }
}
