package video;

import java.util.ArrayList;

public abstract class Video {
    private final String name;
    private final int year;
    private ArrayList<String> genres;
    private int favored;
    private double rating;
    private int numberOfRatings;
    private int duration;

    public Video(final String name, final int year, final ArrayList<String> genres) {
        this.name = name;
        this.year = year;
        this.genres = genres;
    }

    /**
     * Get the title of the video
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get the year when the video came out
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     * Get the video's list of genres
     * @return
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Set the array of genres the video is a part of
     * @param genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Get the number of times the video has been favored
     * @return
     */
    public int getFavored() {
        return favored;
    }

    /**
     * Setter for the number of times the video has been favored
     * @param favored
     */
    public void setFavored(final int favored) {
        this.favored = favored;
    }

    /**
     * Getter for rating field
     * @return
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for rating field
     * @param rating
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Get the number of times the video has been rated
     * @return
     */
    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    /**
     * Setter for numberOfRatings
     * @param numberOfRatings
     */
    public void setNumberOfRatings(final int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    /**
     * Method to get duration of a video
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Method to set the duration of a video
     * @param duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name;
    }
}
