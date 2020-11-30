package commands.UserCommand;

import user.User;
import video.Video;
import video.ViewedVideos;

public final class ViewCommand {
    private static ViewCommand instance;

    /**
     * Method to get instance of singleton class
     * @return
     */
    public static ViewCommand getInstance() {
        if (instance == null) {
            instance = new ViewCommand();
        }
        return instance;

    }

    /**
     * Adds video to viewed videos for user
     * @param user
     * @param video
     */
    public void addView(final User user, final Video video) {
        Integer viewCount = 0;
        if (user.getViewedVideos().get(video.getName()) != null) {
            viewCount = user.getViewedVideos().get(video.getName());
        }

        user.getViewedVideos().put(video.getName(), viewCount + 1);

        ViewedVideos.getInstance().addVideo(video.getName(), 1);
    }
}
