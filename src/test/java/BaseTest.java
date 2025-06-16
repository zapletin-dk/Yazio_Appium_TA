import configurations.DriverFactory;
import configurations.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import steps.StepDefinitions;

import java.io.ByteArrayInputStream;

public class BaseTest {
    public StepDefinitions stepDefinitions;

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
