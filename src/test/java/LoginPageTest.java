import entity.UserFactory;
import io.qameta.allure.*;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

@Feature("Login Page functionality")
public class LoginPageTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login flow")
    @Owner("Danill Zapletin")
    @Description("Test to verify that the user can sign in with an existing account")
    public void signInAsExistingUserTest() {
        stepDefinitions.loginAs(UserFactory.getExistingUser());
        Assert.assertTrue(stepDefinitions.isWelcomeBackTextVisibleAfterLogIn(), "Welcome back text isn't visible after login");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login flow")
    @Owner("Danill Zapletin")
    @Description("Test to verify that the user can sign in with an existing account")
    public void signInAsExistingUserFailedTest() {
        stepDefinitions.loginAs(UserFactory.getExistingUser());
        Assert.assertFalse(stepDefinitions.isWelcomeBackTextVisibleAfterLogIn(), "Welcome back text isn't visible after login");
    }
}
