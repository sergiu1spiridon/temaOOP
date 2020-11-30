package video;

import java.util.Objects;

public final class RatedVideos {
    private String title;
    private int seasonNumber;

    public RatedVideos(final String title, final int seasonNumber) {
        this.title = title;
        this.seasonNumber = seasonNumber;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RatedVideos)) {
            return false;
        }
        RatedVideos that = (RatedVideos) o;
        return seasonNumber == that.seasonNumber
                && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, seasonNumber);
    }
}
