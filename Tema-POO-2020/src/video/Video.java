package video;

import java.util.ArrayList;

public abstract class Video {
    private String name;
    private int year;
    ArrayList<String> genres;
    private int favored;

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

    @Override
    public String toString() {
        return "Video{" +
                "title='" + name + '\'' +
                ", year_of_debut=" + year +
                ", genres=" + genres +
                '}';
    }
}
