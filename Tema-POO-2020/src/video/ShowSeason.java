package video;

import java.util.List;

public final class ShowSeason {
    private int currentSeason;
    private int duration;
    private double rating;
    private int numberOfRatings;

    public ShowSeason(final int currentSeason, final int duration, final List<Double> rating) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.rating = 0;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(final int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }
}
