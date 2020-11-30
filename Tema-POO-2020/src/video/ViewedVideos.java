package video;

import java.util.Hashtable;

public final class ViewedVideos {
    private static ViewedVideos instance = null;
    private final Hashtable<String, Integer> hash;

    private ViewedVideos() {
        hash = new Hashtable<String, Integer>(0);
    }

    /**
     * Method to get insance of singleton class
     * @return
     */
    public static ViewedVideos getInstance() {
        if (instance == null) {
            instance = new ViewedVideos();
        }
        return instance;
    }

    /**
     * Add views to a video, and if that video is not
     * in the list adding it to the list of videos
     * @param vid
     * @param numberOfViews
     */
    public void addVideo(final String vid, final int numberOfViews) {
        Integer a = instance.hash.get(vid);
        if (a == null) {
            instance.hash.put(vid, numberOfViews);
        } else {
            a += numberOfViews;
            instance.hash.put(vid, a);
        }
    }

    /**
     * Resetting the hashtable with videos and their total number of views
     * @param vid
     * @param numberOfViews
     */
    public void setVideo(final String vid, final int numberOfViews) {
        instance.hash.put(vid, numberOfViews);
    }

    /**
     * Method to get the number of views of a video
     * @param vid
     * @return
     */
    public Integer getVideo(final String vid) {
        Integer a = instance.hash.get(vid);

        return a;
    }
}
