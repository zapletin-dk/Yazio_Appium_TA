import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.StepDefinitions;
import utils.DriverManager;

public class BaseTest {
    protected AndroidDriver driver;
    DriverManager driverManager = DriverManager.getInstance();
    StepDefinitions stepDefinitions;

    @BeforeMethod
    public void setUp() throws Exception {
        driver = (AndroidDriver) driverManager.getDriver();
        stepDefinitions = new StepDefinitions(driver);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
    }
}
