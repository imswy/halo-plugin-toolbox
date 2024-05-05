package run.halo.starter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ReactiveSettingFetcher;
import run.halo.starter.config.SteamSettingConfig;
import run.halo.starter.entity.RecentlyPlayedGamesEntity;
import run.halo.starter.utils.SteamUtils;
import java.util.List;

@Service
@AllArgsConstructor
public class SteamService {
    private final ReactiveSettingFetcher settingFetcher;

    private static final WebClient webClient = WebClient.builder().baseUrl("http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/").build();

    public Mono<RecentlyPlayedGamesEntity> recentlyPlayedGames() {

        return settingFetcher.get(SteamSettingConfig.GROUP).flatMap(setting -> {
            String key = setting.get(SteamSettingConfig.TOKEN).asText();
            String id = setting.get(SteamSettingConfig.STEAM_ID).asText();

            String url = String.format("?key=%s&steamid=%s",key,id);
            return webClient.get().uri(url).retrieve().bodyToMono(RecentlyPlayedGamesEntity.class).flatMap(
                entity -> {
                    List<RecentlyPlayedGamesEntity.GamesItem> updatedGames  =  entity.getResponse().getGames().stream().peek(
                        item ->{
                            //获取对应游戏的缩略图
                            item.setImg_icon_url(SteamUtils.steamImage(item.getAppid(),item.getImg_icon_url()));
                        }).toList();
                    entity.getResponse().setGames(updatedGames);
                    return Mono.just(entity);
                }
            );
        });
    }
}
