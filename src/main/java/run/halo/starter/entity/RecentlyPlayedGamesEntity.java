package run.halo.starter.entity;

import lombok.Data;
import java.util.List;

@Data
public class RecentlyPlayedGamesEntity {
    private ResponseItem response;

    @Data
    public static class ResponseItem {
        private Integer total_count;
        private List<GamesItem> games;
    }

    @Data
    public static class GamesItem {
        private Integer appid;
        private String name;
        private Integer playtime_2weeks;
        private Integer playtime_forever;

        private String img_icon_url;
        private Integer playtime_windows_forever;
        private Integer playtime_mac_forever;
        private Integer playtime_linux_forever;
        private Integer playtime_deck_forever;

    }
}
