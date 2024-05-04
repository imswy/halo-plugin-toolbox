package run.halo.starter.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import run.halo.app.plugin.ApiVersion;
import run.halo.starter.entity.RecentlyPlayedGamesEntity;
import run.halo.starter.service.SteamService;

@ApiVersion("v1alpha1")
@RequestMapping("/steam")
@RestController
@AllArgsConstructor
@Slf4j
public class SteamController {
    private final SteamService steamService;
    @GetMapping("/RecentlyPlayedGames")
    public Mono<RecentlyPlayedGamesEntity> GetRecentlyPlayedGames() {
        return steamService.recentlyPlayedGames();
    }
}
