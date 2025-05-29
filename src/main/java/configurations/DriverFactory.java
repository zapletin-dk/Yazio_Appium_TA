package configurations;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {
    private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public static void initDriver(String device, String deviceOS, String osVersion) {
        AppiumDriver driver = DriverFactory.createDriver(device, deviceOS, osVersion);
        DriverManager.setDriverThreadLocal(driver);
    }

    private static AppiumDriver createDriver(String device, String deviceOS, String osVersion) {
        AppiumDriver driver;

        try {
            URL appiumURL = new URI(getAppiumUrl()).toURL();

            return switch (OsType.fromString(deviceOS)) {
                case ANDROID -> {
                    driver = new AndroidDriver(appiumURL, configAndroidDriver(device, osVersion));
                    driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
                    yield driver;
                }
                case IOS -> {
                    driver = new IOSDriver(appiumURL, configIosDriver(device, osVersion));
                    driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
                    yield driver;
                }
            };
        } catch (Exception e) {
            throw new RuntimeException("Failed to create driver for " + deviceOS, e);
        }
    }

    private static UiAutomator2Options configAndroidDriver(String device, String osVersion) {
        return new UiAutomator2Options()
                .setDeviceName(device)
                .setPlatformName("android")
                .setPlatformVersion(osVersion)
                .setAppPackage("com.yazio.android")
                .setAppActivity("com.yazio.android/yazio.feature.MainActivity")
                .setAutomationName("UiAutomator2");
    }

    private static XCUITestOptions configIosDriver(String device, String osVersion) {
        return new XCUITestOptions()
                .setDeviceName(device)
                .setPlatformName("ios")
                .setPlatformVersion(osVersion)
                .setAutomationName("XCUITest")
                .setBundleId("com.yazio.android");
    }

    private static String getAppiumUrl() {
        RunMode runMode = RunMode.fromString(System.getProperty("runMode", "local"));
        return switch (runMode) {
            case LOCAL -> "http://127.0.0.1:4723/";
            default -> throw new IllegalArgumentException("Unsupported run mode: " + runMode);
        };
    }

    private enum OsType {
        ANDROID("android"),
        IOS("ios");

        private final String name;

        OsType(String name) {
            this.name = name;
        }

        static OsType fromString(String name) {
            for (OsType osType : values()) {
                if (osType.name.equalsIgnoreCase(name)) {
                    return osType;
                }
            }
            throw new IllegalArgumentException("Unknown os name: " + name);
        }
    }

    private enum RunMode {
        LOCAL("local");

        private final String mode;

        RunMode(String mode) {
            this.mode = mode;
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
