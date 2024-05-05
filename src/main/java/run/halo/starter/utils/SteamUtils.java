package run.halo.starter.utils;

public class SteamUtils {
    public static String steamImage(Integer appId,String url) {
        return String.format("http://media.steampowered.com/steamcommunity/public/images/apps/%d/%s.jpg",appId,url);
    }
}
