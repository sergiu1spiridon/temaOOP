package video;

import java.util.HashMap;
import java.util.Hashtable;

public class ViewedVideos {
    private static ViewedVideos instance = null;
    private final Hashtable<String, Integer> hash;

    private ViewedVideos() {
        hash = new Hashtable<String, Integer>(0);
    }

    public static ViewedVideos getInstance() {
        if(instance == null) {
            instance = new ViewedVideos();
        }
        return instance;
    }

    public void addVideo(String vid, int numberOfViews) {
        Integer a = instance.hash.get(vid);
        if(a == null) {
            instance.hash.put(vid, numberOfViews);
        }
        else {
            a+= numberOfViews;
            instance.hash.put(vid, a);
        }
    }

    public Integer getVideo(String vid) {
        Integer a = instance.hash.get(vid);

        return a;
    }
}
