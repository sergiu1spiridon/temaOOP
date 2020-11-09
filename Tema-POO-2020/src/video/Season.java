package video;

public class Season {
    private int currentSeason;
    private int duration;
    private int rating;

    public Season(int currentSeason, int duration, int rating) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
