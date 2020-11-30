package actor;

import video.Video;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public final class Actor {

    private final String name;

    private final String careerDescription;

    private final ArrayList<String> filmography;

    private final Map<ActorsAwards, Integer> awards;

    private int numberOfAwards;

    private double rating;

    public Actor(final String name, final String careerDescription, final ArrayList<String>
            filmography, final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(final double rating) {
        //Random random = new Random();

        //this.rating = random.nextDouble();
        this.rating = rating;
    }

    public void calculateRating(final Hashtable<String, Video> videosArray) {
        double countDiffZeroRatings = 0;
        double newRating = 0;

        for (String vidName:this.filmography) {
            Video video = videosArray.get(vidName);
            if (video == null) {
                continue;
            }
            double videoRating = video.getRating();
            if (videoRating != 0) {
                newRating += videoRating;
                countDiffZeroRatings++;
            }
        }
        if (countDiffZeroRatings != 0) {
            newRating = newRating / countDiffZeroRatings;
        }
        this.setRating(newRating);
    }

    public int getNumberOfAwards() {
        return numberOfAwards;
    }

    public void setNumberOfAwards(final int numberOfAwards) {
        this.numberOfAwards = numberOfAwards;
    }

    public void calculateNumberOfAwards() {
        int awardNumber = 0;
        for (int i:awards.values()) {
            awardNumber += i;
        }
        this.setNumberOfAwards(awardNumber);
    }

    @Override
    public String toString() {
        return name;
    }
}
