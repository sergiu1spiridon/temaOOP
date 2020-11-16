package video;

import java.util.*;

public class ShowSeason {
    private int currentSeason;
    private int duration;
    private double rating;
    private int numberOfRatings;

    public ShowSeason(int currentSeason, int duration, List<Double> rating) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.rating = 0;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
