package configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/main/resources/configs/ios.properties",
})
public interface IosDeviceConfig extends Config {
    @Key("deviceName")
    String deviceName();
}