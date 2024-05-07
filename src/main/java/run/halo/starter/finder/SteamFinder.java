package run.halo.starter.finder;

import reactor.core.publisher.Mono;
import run.halo.starter.entity.RecentlyPlayedGamesEntity;

public interface SteamFinder {
    Mono<RecentlyPlayedGamesEntity> get();
}
