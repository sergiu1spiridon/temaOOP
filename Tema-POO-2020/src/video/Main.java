package video;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<>(0);
        a.add("Action");
        a.add("Fantasy");

        Video vid = new Movie("The Purge", 2014, a, 267, 5);

        System.out.println(vid);
    }
}
