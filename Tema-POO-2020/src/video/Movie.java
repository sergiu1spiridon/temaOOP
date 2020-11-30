package video;

import java.util.ArrayList;

public final class Movie extends Video {

    private int duration;

    public Movie(final String name, final int year, final ArrayList<String> genres,
                 final int duration) {
        super(name, year, genres);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }
}
