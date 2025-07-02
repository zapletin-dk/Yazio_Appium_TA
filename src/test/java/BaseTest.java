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
    private static AppiumServerManager serverManager;
    private static int port;
    public StepDefinitions stepDefinitions;

    @BeforeClass
    public void appiumSetUp() {
        serverManager = new AppiumServerManager();
        port = serverManager.startServer();
    }

    @AfterClass
    public void appiumTearDown() {
        serverManager.stopServer(port);
    }

    @BeforeMethod
    @Parameters({"device", "deviceOS", "osVersion"})
    public void setUp(String device, String deviceOS, String osVersion) {
        DriverFactory.initDriver(device, deviceOS, osVersion, port);
        stepDefinitions = new StepDefinitions();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot();
        }
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void tearDownSuite() {
        serverManager.stopAllServers();
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
