package configurations;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    public static final AndroidConfig ANDROID_CONFIG = ConfigFactory.create(AndroidConfig.class, System.getProperties());
    public static final IosConfig IOS_CONFIG = ConfigFactory.create(IosConfig.class, System.getProperties());
    public static final EnvironmentConfig ENVIRONMENT_CONFIG = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());
}