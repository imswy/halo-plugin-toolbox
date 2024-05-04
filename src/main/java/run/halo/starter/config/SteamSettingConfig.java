package run.halo.starter.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class SteamSettingConfig {

    public static final String CONFIG_MAP_NAME = "plugin-steam-config";
    public static final String GROUP = "steam";

    private String token;
    private String steamId;
}
