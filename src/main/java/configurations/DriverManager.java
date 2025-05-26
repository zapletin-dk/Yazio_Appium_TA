package configurations;

import io.appium.java_client.AppiumDriver;
import utils.DriverFactory;

import java.time.Duration;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Duration IMPLICIT_WAIT_TIMEOUT = Duration.ofSeconds(20);

    public static AppiumDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(initDriver("ANDROID"));
        }
        return driverThreadLocal.get();
    }

    private static AppiumDriver initDriver(String deviceOS) {
        AppiumDriver driver = DriverFactory.createDriver(deviceOS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIMEOUT);
        return driver;
    }

    public static void quitDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.remove();
    }
}