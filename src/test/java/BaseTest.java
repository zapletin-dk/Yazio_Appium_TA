import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.StepDefinitions;
import utils.DriverManager;

public class BaseTest {
    public StepDefinitions stepDefinitions;

    @BeforeMethod
    public void setUp() {
        stepDefinitions = new StepDefinitions();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
