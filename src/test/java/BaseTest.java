import configurations.AppiumServerManager;
import configurations.DriverFactory;
import configurations.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.*;
import steps.StepDefinitions;

import java.io.ByteArrayInputStream;

public class BaseTest {
    public StepDefinitions stepDefinitions;

    private static AppiumServerManager serverManager = new AppiumServerManager();

    @BeforeSuite
    @Parameters({"deviceOS"})
    public void setUp(String deviceOS) {
        serverManager.startServer(deviceOS);
    }

    @AfterSuite
    public void tearDown() {
        serverManager.stopServer();
    }

    @BeforeMethod
    @Parameters({"device", "deviceOS", "osVersion"})
    public void setUp(String device, String deviceOS, String osVersion) {
        DriverFactory.initDriver(device, deviceOS, osVersion);
        stepDefinitions = new StepDefinitions();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot();
        }
        DriverManager.quitDriver();
    }

    @Step("Make screenshot of failed part")
    protected void takeScreenshot() {
        try {
            byte[] screenshot = DriverManager.getDriver().getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot on failure", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            System.err.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
