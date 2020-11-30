package video;

import java.util.ArrayList;

public abstract class Video {
    private String name;
    private int year;
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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getFavored() {
        return favored;
    }

    public void setFavored(final int favored) {
        this.favored = favored;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name;
    }
}
