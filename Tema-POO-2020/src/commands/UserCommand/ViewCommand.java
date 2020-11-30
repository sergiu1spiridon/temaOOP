package commands.UserCommand;

import user.User;
import video.Video;
import video.ViewedVideos;

public final class ViewCommand {
    private static ViewCommand instance;

    public static ViewCommand getInstance() {
        if (instance == null) {
            instance = new ViewCommand();
        }
        return instance;

    }

    public void addView(final User user, final Video video) {
        Integer viewCount = 0;
        if (user.getViewedVideos().get(video.getName()) != null) {
            viewCount = user.getViewedVideos().get(video.getName());
        }

        user.getViewedVideos().put(video.getName(), viewCount + 1);

        ViewedVideos.getInstance().addVideo(video.getName(), 1);
    }
}
