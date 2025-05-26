package configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/main/resources/configs/environment.properties",
})
public interface EnvironmentConfig extends Config {
    @Key("appiumURL")
    String appiumURL();
}

