package video;

import java.util.ArrayList;

public class Show extends Video{

    private ArrayList<Season> seasons;
    private int numberOfSeasons;

    public Show(String name, int year, ArrayList<String> genres, ArrayList<Season> seasons, int numberOfSeasons) {
        super(name, year, genres);
        this.seasons = seasons;
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }
}
