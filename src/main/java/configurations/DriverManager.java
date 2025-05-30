package configurations;

import io.appium.java_client.AppiumDriver;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    public static AppiumDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            throw new IllegalStateException("Driver not initialized. Please call initializeDriver first.");
        }
        return driverThreadLocal.get();
    }

    public static void setDriverThreadLocal(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
        }
        driverThreadLocal.remove();
    }
}