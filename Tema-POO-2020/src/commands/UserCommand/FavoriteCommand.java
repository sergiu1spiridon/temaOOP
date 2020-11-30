package commands.UserCommand;

import user.User;
import video.Video;

import java.util.ArrayList;
import java.util.Map;

public final class FavoriteCommand {

    private static FavoriteCommand instance;

    private FavoriteCommand() {

    }

    public static FavoriteCommand getInstance() {
        if (instance == null) {
            instance = new FavoriteCommand();
        }
        return instance;
    }

    public int addFavorite(final User user, final Video video) {
        if (user == null || video == null) {
            return 0;
        }
        Map<String, Integer> viewedVids = user.getViewedVideos();

        if (viewedVids.containsKey(video.getName())) {
            ArrayList<String> favedVids = user.getFavouriteVideos();
            if (!favedVids.contains(video.getName())) {
                favedVids.add(video.getName());
                video.setFavored(video.getFavored() + 1);
                user.setFavouriteVideos(favedVids);
                return 2;
            }
            return 1;
        }
        return 0;
    }
}
