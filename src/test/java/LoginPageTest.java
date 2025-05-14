import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void signInAsExistingUserTest(){
        stepDefinitions.loginAsExistingUser();
        Assert.assertTrue(stepDefinitions.welcomeTextOccursOnMainPage());
    }
}
