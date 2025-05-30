import configurations.DriverFactory;
import configurations.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import steps.StepDefinitions;

public class BaseTest {
    public StepDefinitions stepDefinitions;

    @BeforeMethod
    @Parameters({"device", "deviceOS", "osVersion"})
    public void setUp(String device, String deviceOS, String osVersion) {
        DriverFactory.initDriver(device, deviceOS, osVersion);
        stepDefinitions = new StepDefinitions();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
