import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.UserFactory;

public class LoginPageTest extends BaseTest {

    @Test
    @Description("Test to verify that the user can sign in with an existing account")
    public void signInAsExistingUserTest() {
        stepDefinitions.loginAsExistingUser(UserFactory.existingUser);
        Assert.assertTrue(stepDefinitions.isWelcomeBackTextVisibleAfterLogIn(), "Welcome back text is visible after login");
    }
}
