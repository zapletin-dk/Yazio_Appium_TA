package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    @iOSXCUITFindBy(accessibility = "Allow")
    private WebElement allowNotificationsButton;

    @iOSXCUITFindBy(accessibility = "Deny")
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    private WebElement denyNotificationsButton;

    @iOSXCUITFindBy(accessibility = "Skip")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='welcome_screen.button.get_started_button']")
    private WebElement getStartedButton;

    @iOSXCUITFindBy(accessibility = "Already have an account?")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='welcome_screen.button.already_have_account_button']")
    private WebElement alreadyHaveAccountButton;

    @iOSXCUITFindBy(accessibility = "Continue with Google")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='login_screen.button.google_login']")
    private WebElement continueWithGoogleButton;

    @iOSXCUITFindBy(accessibility = "Continue with Email")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='login_screen.button.email_login']")
    private WebElement continueWithEmailButton;

    @iOSXCUITFindBy(accessibility = "Email")
    @AndroidFindBy(id = "com.yazio.android:id/mailEdit")
    private WebElement emailField;

    @iOSXCUITFindBy(accessibility = "Password")
    @AndroidFindBy(id = "com.yazio.android:id/passEdit")
    private WebElement passwordField;

    @iOSXCUITFindBy(accessibility = "Login")
    @AndroidFindBy(id = "com.yazio.android:id/loginButton")
    private WebElement loginButton;

    public LoginPage() {
        super();
    }

    @Step("Click allow notifications")
    public LoginPage allowNotifications() {
        allowNotificationsButton.click();
        return this;
    }

    public LoginPage declineNotifications() {
        denyNotificationsButton.click();
        return this;
    }

    @Step("Click already have an account button")
    public LoginPage clickAlreadyHaveAccount() {
        alreadyHaveAccountButton.click();
        return this;
    }

    public LoginPage clickContinueWithGoogleAccount() {
        continueWithGoogleButton.click();
        return this;
    }

    @Step("Click continue with email button")
    public LoginPage clickContinueWithEmail() {
        continueWithEmailButton.click();
        return this;
    }

    @Step("Fill email field with {email}")
    public LoginPage fillEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("Fill password field with {password}")
    public LoginPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Click login button")
    public MainPage clickLoginButton() {
        loginButton.click();
        return new MainPage();
    }
}
