package steps;

import io.appium.java_client.AppiumDriver;
import pages.LoginPage;
import pages.MainPage;

public class StepDefinitions {
    private AppiumDriver driver;
    LoginPage loginPage;
    MainPage mainPage;

    public StepDefinitions(AppiumDriver driver){
        this.driver = driver;
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    public void loginAsExistingUser(){
        loginPage.allowNotifications()
                .clickAlreadyHaveAccount()
                .clickContinueWithEmail()
                .fillEmailField("yazio_test@yopmail.com")
                .fillPasswordField("qwerty123")
                .clickLoginButton();
    }

    public boolean welcomeTextOccursOnMainPage(){
        return mainPage.welcomeToYazioTextIsShown();
    }
}
