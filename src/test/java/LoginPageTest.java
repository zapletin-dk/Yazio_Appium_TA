import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

public class LoginPageTest extends BaseTest{


    @Test
    public void start() {}
    @Test
    public void signInAsExistingUserTest(){
        stepDefinitions.loginAsExistingUser();
        Assert.assertTrue(stepDefinitions.welcomeTextOccursOnMainPage());
    }
}
