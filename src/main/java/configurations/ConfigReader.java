package configurations;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    public static final AndroidDeviceConfig ANDROID_CONFIG = ConfigFactory.create(AndroidDeviceConfig.class, System.getProperties());
    public static final IosDeviceConfig IOS_CONFIG = ConfigFactory.create(IosDeviceConfig.class, System.getProperties());
    public static final EnvironmentConfig ENVIRONMENT_CONFIG = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());
}