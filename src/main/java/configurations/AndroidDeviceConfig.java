package configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/main/resources/configs/android.properties",
})
public interface AndroidDeviceConfig extends Config {
    @Key("deviceName")
    String deviceName();
}