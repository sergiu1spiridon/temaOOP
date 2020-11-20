package video;

import java.util.ArrayList;

public abstract class Video {
    private String name;
    private int year;
    ArrayList<String> genres;
    private int favored;
    private double rating;
    private int numberOfRatings;
    private int duration;

    public Video(String name, int year, ArrayList<String> genres) {
        this.name = name;
        this.year = year;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getFavored() {
        return favored;
    }

    public void setFavored(int favored) {
        this.favored = favored;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return name;
    }
}
