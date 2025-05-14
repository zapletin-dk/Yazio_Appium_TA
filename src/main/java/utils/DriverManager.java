package utils;

import configurations.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URI;
import java.time.Duration;

public class DriverManager{
    private static AppiumDriver driver;

    private static final String DEVICE_NAME = ConfigReader.emulatorConfig.deviceName();
    private static final String PLATFORM_NAME = ConfigReader.emulatorConfig.platformName();
    private static final String APP_PACKAGE = ConfigReader.emulatorConfig.appPackage();
    private static final String APP_ACTIVITY = ConfigReader.emulatorConfig.appActivity();
    private static final String URL = ConfigReader.emulatorConfig.remoteURL();
    private static final String AUTOMATION_NAME = ConfigReader.emulatorConfig.automationName();

    private static DriverManager INSTANCE;

    public static DriverManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public AppiumDriver initDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("deviceName", DEVICE_NAME);
        capabilities.setCapability("platformName", PLATFORM_NAME);
        capabilities.setCapability("appPackage", APP_PACKAGE);
        capabilities.setCapability("appActivity", APP_ACTIVITY);
        capabilities.setCapability("automationName", AUTOMATION_NAME);

        driver = new AndroidDriver(new URI(URL).toURL(), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    public void quitDriver(){
        if (driver != null) {
            driver.quit();
        }
    }

    public AppiumDriver getDriver() throws Exception{
        if (driver != null) {
            return driver;
        } else {
            return initDriver();
        }
    }
}