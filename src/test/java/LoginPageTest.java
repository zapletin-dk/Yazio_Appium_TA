import entity.UserFactory;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    @Description("Test to verify that the user can sign in with an existing account")
    public void signInAsExistingUserTest() {
        stepDefinitions.loginAs(UserFactory.getExistingUser());
        Assert.assertTrue(stepDefinitions.isWelcomeBackTextVisibleAfterLogIn(), "Welcome back text is visible after login");
    }
}
