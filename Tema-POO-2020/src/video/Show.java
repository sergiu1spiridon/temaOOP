package video;

import java.util.ArrayList;

public class Show extends Video{

    private ArrayList<ShowSeason> showSeasons;
    private int numberOfSeasons;

    private int duration;

    public Show(String name, int year, ArrayList<String> genres, ArrayList<ShowSeason> showSeasons, int numberOfSeasons) {
        super(name, year, genres);
        this.showSeasons = showSeasons;
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<ShowSeason> getSeasons() {
        return showSeasons;
    }

    public void setSeasons(ArrayList<ShowSeason> showSeasons) {
        this.showSeasons = showSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void calculateDuration() {
        int newDuration = this.getSeasons().stream().mapToInt(ShowSeason::getDuration).sum();
        super.setDuration(newDuration);
    }
}
