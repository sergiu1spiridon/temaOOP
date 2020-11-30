package video;

import java.util.ArrayList;

public final class Show extends Video {

    private ArrayList<ShowSeason> showSeasons;
    private int numberOfSeasons;

    public Show(final String name, final int year, final ArrayList<String> genres,
                final ArrayList<ShowSeason> showSeasons, final int numberOfSeasons) {
        super(name, year, genres);
        this.showSeasons = showSeasons;
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<ShowSeason> getSeasons() {
        return showSeasons;
    }

    public void setSeasons(final ArrayList<ShowSeason> showSeasons) {
        this.showSeasons = showSeasons;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(final int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    /**
     * Adds the duration of all the seasons to get the duration of the show
     */
    public void calculateDuration() {
        int newDuration = this.getSeasons().stream().mapToInt(ShowSeason::getDuration).sum();
        super.setDuration(newDuration);
    }
}
