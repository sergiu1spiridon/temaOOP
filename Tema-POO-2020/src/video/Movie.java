package video;

import java.util.ArrayList;

public class Movie extends Video{

    private int duration;

    public Movie(String name, int year, ArrayList<String> genres, int duration) {
        super(name, year, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
