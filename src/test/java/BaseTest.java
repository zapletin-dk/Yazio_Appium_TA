import configurations.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import steps.StepDefinitions;
import utils.DriverFactory;

public class BaseTest {
    public StepDefinitions stepDefinitions;

    @BeforeMethod
    @Parameters({"device", "deviceOS"})
    public void setUp(String device, String deviceOS) {
        DriverFactory.initDriver(device, deviceOS);
        stepDefinitions = new StepDefinitions();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
