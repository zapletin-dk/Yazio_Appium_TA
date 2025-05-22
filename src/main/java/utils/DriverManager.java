package utils;

import configurations.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public static AppiumDriver getDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver == null) {
            driver = initDriver();
            driverThreadLocal.set(driver);
        }
        return driver;
    }

    private static AppiumDriver initDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String appiumUrl = getAppiumUrl();

        DriverFactory driverFactory = createDriverFactory();
        AppiumDriver driver = driverFactory.createDriver(appiumUrl, capabilities);

        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
        return driver;
    }

    private static DriverFactory createDriverFactory() {
        String platformName = ConfigReader.ENVIRONMENT_CONFIG.os().toLowerCase();
        if (platformName.contains("android")) {
            return new AndroidDriverFactory();
        } else if (platformName.contains("ios")) {
            return new IOSDriverFactory();
        }
        throw new IllegalArgumentException("Unsupported platform: " + platformName);
    }

    private static String getAppiumUrl() {
        RunMode runMode = RunMode.fromString(System.getProperty("runMode", "local"));
        return switch (runMode) {
            case LOCAL -> ConfigReader.ENVIRONMENT_CONFIG.appiumURL();
            case REMOTE -> System.getProperty("remoteAppiumUrl", runMode.defaultUrl);
            case CI -> System.getProperty("ciAppiumUrl", runMode.defaultUrl);
            default -> throw new IllegalArgumentException("Unsupported run mode: " + runMode);
        };
    }

    public static void quitDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driverThreadLocal.remove();
            }
        }
    }

    private enum RunMode {
        LOCAL("local"),
        REMOTE("remote", "http://remote-appium-server:4723"),
        CI("ci", "http://ci-appium-server:4723");

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

    private interface DriverFactory {
        AppiumDriver createDriver(String appiumUrl, DesiredCapabilities capabilities);
    }

    private static class AndroidDriverFactory implements DriverFactory {
        @Override
        public AppiumDriver createDriver(String appiumUrl, DesiredCapabilities capabilities) {
            capabilities.setCapability("deviceName", ConfigReader.ANDROID_CONFIG.deviceName());
            capabilities.setCapability("platformName", ConfigReader.ANDROID_CONFIG.platformName());
            capabilities.setCapability("appPackage", ConfigReader.ANDROID_CONFIG.appPackage());
            capabilities.setCapability("appActivity", ConfigReader.ANDROID_CONFIG.appActivity());
            capabilities.setCapability("automationName", "UiAutomator2");
            try {
                return new AndroidDriver(new URI(appiumUrl).toURL(), capabilities);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create Android driver", e);
            }
        }
    }

    private static class IOSDriverFactory implements DriverFactory {
        @Override
        public AppiumDriver createDriver(String appiumUrl, DesiredCapabilities capabilities) {
            XCUITestOptions iosOptions = new XCUITestOptions()
                    .setDeviceName(ConfigReader.IOS_CONFIG.deviceName())
                    .setPlatformName(ConfigReader.IOS_CONFIG.platformName())
                    .setBundleId(ConfigReader.IOS_CONFIG.appPackage());
            try {
                return new IOSDriver(new URI(appiumUrl).toURL(), iosOptions);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create iOS driver", e);
            }
        }
    }
}