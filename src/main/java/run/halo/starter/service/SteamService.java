package run.halo.starter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ReactiveSettingFetcher;
import run.halo.starter.config.SteamSettingConfig;
import run.halo.starter.entity.RecentlyPlayedGamesEntity;

@Service
@AllArgsConstructor
public class SteamService {
    private final ReactiveSettingFetcher settingFetcher;
    public Mono<RecentlyPlayedGamesEntity> recentlyPlayedGames() {

        return settingFetcher.get(SteamSettingConfig.GROUP).flatMap(setting -> {
            String key = setting.get("token").asText();
            String id = setting.get("steamId").asText();
            String url = String.format("?key=%s&steamid=%s",key,id);
            WebClient webClient = WebClient.builder().baseUrl("http://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/").build();
            return webClient.get().uri(url).retrieve().bodyToMono(RecentlyPlayedGamesEntity.class);
        });
    }
}
