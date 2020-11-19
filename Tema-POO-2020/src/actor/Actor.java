package actor;

import actor.ActorsAwards;
import video.Video;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class Actor {

    private String name;

    private String careerDescription;

    private ArrayList<String> filmography;

    private Map<ActorsAwards, Integer> awards;

    private int numberOfAwards;

    private double rating;

    public Actor(String name, String careerDescription, ArrayList<String> filmography, Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.rating = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        //Random random = new Random();

        //this.rating = random.nextDouble();
        this.rating = rating;
    }

    public void calculateRating(Hashtable<String, Video> videosArray) {
        double countDiffZeroRatings = 0;
        double newRating = 0;

        for (String vidName:this.filmography) {
            Video video = videosArray.get(vidName);
            if (video == null)
                continue;
            double videoRating = video.getRating();
            if (videoRating != 0) {
                newRating += videoRating;
                countDiffZeroRatings++;
            }
        }
        if (countDiffZeroRatings != 0)
            newRating = newRating / countDiffZeroRatings;
        this.setRating(newRating);
    }

    public int getNumberOfAwards() {
        return numberOfAwards;
    }

    public void setNumberOfAwards(int numberOfAwards) {
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
