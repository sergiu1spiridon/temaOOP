package video;

import java.util.*;

public class ShowSeason {
    private int currentSeason;
    private int duration;
    private List<Double> rating;

    public ShowSeason(int currentSeason, int duration, List<Double> rating) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.rating = rating;
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

    public List<Double> getRating() {
        return rating;
    }

    public void setRating(List<Double> rating) {
        this.rating = rating;
    }
}
