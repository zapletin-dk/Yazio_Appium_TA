package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private WebElement allowNotificationsButton;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    private WebElement denyNotificationsButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='welcome_screen.button.get_started_button']")
    private WebElement getStartedButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='welcome_screen.button.already_have_account_button']")
    private WebElement alreadyHaveAccountButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='login_screen.button.google_login']")
    private WebElement continueWithGoogleButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='login_screen.button.email_login']")
    private WebElement continueWithEmailButton;

    @AndroidFindBy(id = "com.yazio.android:id/mailEdit")
    private WebElement emailField;

    @AndroidFindBy(id = "com.yazio.android:id/passEdit")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.yazio.android:id/loginButton")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage allowNotifications(){
        allowNotificationsButton.click();
        return this;
    }

    public LoginPage declineNotifications(){
        denyNotificationsButton.click();
        return this;
    }

    public LoginPage clickAlreadyHaveAccount(){
        alreadyHaveAccountButton.click();
        return this;
    }

    public LoginPage clickContinueWithGoogleAccount(){
        continueWithGoogleButton.click();
        return this;
    }

    public LoginPage clickContinueWithEmail(){
        continueWithEmailButton.click();
        return this;
    }


    public LoginPage fillEmailField(String email){
       // emailField.click();
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage fillPasswordField(String password){
       // passwordField.click();
        passwordField.sendKeys(password);
        return this;
    }

    public MainPage clickLoginButton(){
        loginButton.click();
        return new MainPage(driver);
    }
}
