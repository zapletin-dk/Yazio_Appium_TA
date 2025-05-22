package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
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

    public LoginPage allowNotifications() {
        allowNotificationsButton.click();
        return this;
    }

    public LoginPage declineNotifications() {
        denyNotificationsButton.click();
        return this;
    }

    public LoginPage clickAlreadyHaveAccount() {
        alreadyHaveAccountButton.click();
        return this;
    }

    public LoginPage clickContinueWithGoogleAccount() {
        continueWithGoogleButton.click();
        return this;
    }

    public LoginPage clickContinueWithEmail() {
        continueWithEmailButton.click();
        return this;
    }

    public LoginPage fillEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage clickLoginButton() {
        loginButton.click();
        return new MainPage();
    }
}
