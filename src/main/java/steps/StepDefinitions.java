package steps;

import entity.User;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import pages.LoginPage;
import pages.MainPage;

public class StepDefinitions {
    private final LoginPage loginPage;
    private final MainPage mainPage;

    public StepDefinitions() {
        loginPage = new LoginPage();
        mainPage = new MainPage();
    }

    @Step("Login as {user}")
    public void loginAs(User user) {
        loginPage.allowNotifications()
                .clickAlreadyHaveAccount()
                .clickContinueWithEmail()
                .fillEmailField(user.getEmail())
                .fillPasswordField(user.getPassword())
                .clickLoginButton();
    }

    @Step("Verify that welcome back message occurs after log in")
    @Attachment(value = "Welcome back text", type = "text/plain")
    public boolean isWelcomeBackTextVisibleAfterLogIn() {
        return mainPage.isWelcomeBackTextVisible();
    }
}
