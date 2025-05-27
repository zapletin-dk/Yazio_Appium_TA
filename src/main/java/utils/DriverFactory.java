package utils;

import configurations.DriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.time.Duration;

public class DriverFactory {
    private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public static void initDriver(String device, String deviceOS) {
        AppiumDriver driver = DriverFactory.createDriver(device, deviceOS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
        DriverManager.setDriverThreadLocal(driver);
    }

    private static AppiumDriver createDriver(String device, String deviceOS) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String appiumUrl = getAppiumUrl();

        if (deviceOS.toLowerCase().contains("android")) {
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformName", "android");
            capabilities.setCapability("appPackage", "com.yazio.android");
            capabilities.setCapability("appActivity", "com.yazio.android/yazio.feature.MainActivity");
            capabilities.setCapability("automationName", "UiAutomator2");
            try {
                return new AndroidDriver(new URI(appiumUrl).toURL(), capabilities);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create Android driver", e);
            }
        } else if (deviceOS.toLowerCase().contains("ios")) {
            XCUITestOptions iosOptions = new XCUITestOptions()
                    .setDeviceName(device)
                    .setPlatformName("ios")
                    .setAutomationName("XCUITest")
                    .setBundleId("com.yazio.android");
            try {
                return new IOSDriver(new URI(appiumUrl).toURL(), iosOptions);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create iOS driver", e);
            }
        }
        throw new IllegalArgumentException("Unsupported platform: " + deviceOS);
    }

    private static String getAppiumUrl() {
        RunMode runMode = RunMode.fromString(System.getProperty("runMode", "local"));
        return switch (runMode) {
            case LOCAL -> "http://127.0.0.1:4723/";
            default -> throw new IllegalArgumentException("Unsupported run mode: " + runMode);
        };
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
}
