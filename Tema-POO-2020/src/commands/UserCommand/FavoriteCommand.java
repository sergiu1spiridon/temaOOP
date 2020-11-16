package commands.UserCommand;

import user.User;
import video.Video;
import video.ViewedVideos;

import java.util.ArrayList;
import java.util.Map;

public class FavoriteCommand {

    private static FavoriteCommand instance;

    private FavoriteCommand() {

    }

    public static FavoriteCommand getInstance() {
        if(instance == null) {
            instance = new FavoriteCommand();
        }
        return instance;
    }

    public static void addFavorite(User user, Video video) {
        if (user == null || video == null)
            return;
        Map<String, Integer> viewedVids = user.getViewedVideos();

        if (viewedVids.containsKey(video.getName())) {
            ArrayList<String> favedVids = user.getFavouriteVideos();
            if (favedVids.contains(video.getName())){
                favedVids.add(video.getName());
                video.setFavored(video.getFavored() + 1);
                user.setFavouriteVideos(favedVids);
            }
        }
    }
}
