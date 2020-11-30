package commands.UserCommand;

import user.User;
import video.Video;

import java.util.ArrayList;
import java.util.Map;

public final class FavoriteCommand {

    private static FavoriteCommand instance;

    private FavoriteCommand() {

    }

    /**
     * Method to get instance of FavoriteCommand
     * @return
     */
    public static FavoriteCommand getInstance() {
        if (instance == null) {
            instance = new FavoriteCommand();
        }
        return instance;
    }

    /**
     * Adds the video to the user's list of favorites
     * @param user
     * @param video
     * @return
     */
    public int addFavorite(final User user, final Video video) {
        if (user == null || video == null) {
            return 0;
        }
        Map<String, Integer> viewedVids = user.getViewedVideos();

        // user must have seen the video and not have added it already to favorites
        if (viewedVids.containsKey(video.getName())) {
            ArrayList<String> favedVids = user.getFavouriteVideos();
            if (!favedVids.contains(video.getName())) {
                favedVids.add(video.getName());
                video.setFavored(video.getFavored() + 1);
                user.setFavouriteVideos(favedVids);
                return 2; // added with success
            }
            return 1; // video was in favorite list
        }
        return 0; // video not seen
    }
}
