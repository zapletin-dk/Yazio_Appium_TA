package utils;

import configurations.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;

public class DriverFactory {
    public static AppiumDriver createDriver(String os) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String appiumUrl = getAppiumUrl();

        if (os.toLowerCase().contains("android")) {
            capabilities.setCapability("deviceName", ConfigReader.ANDROID_CONFIG.deviceName());
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("appPackage", "com.yazio.android");
            capabilities.setCapability("appActivity", "com.yazio.android/yazio.feature.MainActivity");
            capabilities.setCapability("automationName", "UiAutomator2");
            try {
                return new AndroidDriver(new URI(appiumUrl).toURL(), capabilities);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create Android driver", e);
            }
        } else if (os.toLowerCase().contains("ios")) {
            XCUITestOptions iosOptions = new XCUITestOptions()
                    .setDeviceName(ConfigReader.IOS_CONFIG.deviceName())
                    .setPlatformName("ios")
                    .setAutomationName("XCUITest")
                    .setBundleId("com.yazio.android");
            try {
                return new IOSDriver(new URI(appiumUrl).toURL(), iosOptions);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create iOS driver", e);
            }
        }
        throw new IllegalArgumentException("Unsupported platform: " + os);
    }

    private enum RunMode {
        LOCAL("local");

        private final String mode;
        private final String defaultUrl;

        RunMode(String mode) {
            this(mode, null);
        }

        RunMode(String mode, String defaultUrl) {
            this.mode = mode;
            this.defaultUrl = defaultUrl;
        }

        static RunMode fromString(String mode) {
            for (RunMode runMode : values()) {
                if (runMode.mode.equalsIgnoreCase(mode)) {
                    return runMode;
                }
            }
            throw new IllegalArgumentException("Unknown run mode: " + mode);
        }
    }

    private static String getAppiumUrl() {
        RunMode runMode = RunMode.fromString(System.getProperty("runMode", "local"));
        return switch (runMode) {
            case LOCAL -> ConfigReader.ENVIRONMENT_CONFIG.appiumURL();
            default -> throw new IllegalArgumentException("Unsupported run mode: " + runMode);
        };
    }
}
