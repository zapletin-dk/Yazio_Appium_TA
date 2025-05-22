package steps;

import pages.LoginPage;
import pages.MainPage;
import utils.User;

public class StepDefinitions {
    private LoginPage loginPage;
    private MainPage mainPage;

    public StepDefinitions() {
        loginPage = new LoginPage();
        mainPage = new MainPage();
    }

    public void loginAsExistingUser(User user) {
        loginPage.allowNotifications()
                .clickAlreadyHaveAccount()
                .clickContinueWithEmail()
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
    }

    public boolean isWelcomeBackTextVisibleAfterLogIn() {
        return mainPage.isWelcomeBackTextVisible();
    }
}
