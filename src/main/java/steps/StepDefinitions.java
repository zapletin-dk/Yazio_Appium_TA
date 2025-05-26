package steps;

import pages.LoginPage;
import pages.MainPage;
import entity.User;

public class StepDefinitions {
    private final LoginPage loginPage;
    private final MainPage mainPage;

    public StepDefinitions() {
        loginPage = new LoginPage();
        mainPage = new MainPage();
    }

    public void loginAs(User user) {
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
