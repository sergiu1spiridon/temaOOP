package commands.UserCommand;

import user.User;
import video.*;

public class ViewCommand {
    private static ViewCommand instance;

    public static ViewCommand getInstance() {
        if (instance == null) {
            instance = new ViewCommand();
        }
        return instance;

    }

    public void addView(User user, Video video) {
        Integer viewCount = 0;
        if (user.getViewedVideos().get(video.getName()) != null) {
            viewCount = user.getViewedVideos().get(video.getName());
        }

        user.getViewedVideos().put(video.getName(), viewCount + 1);

        ViewedVideos.getInstance().addVideo(video.getName(), 1);
    }
}
