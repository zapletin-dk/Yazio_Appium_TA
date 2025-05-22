package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    @iOSXCUITFindBy(accessibility = "Home")
    @AndroidFindBy(id = "com.yazio.android:id/bottomNavAboutMe")
    private WebElement profileButton;

    @iOSXCUITFindBy(accessibility = "Settings")
    @AndroidFindBy(xpath = "//android.view.View[@resource-id='welcome_back_screen.text.welcome_back_text']")
    private WebElement welcomeBackToYazioText;

    public MainPage() {
        super();
    }

    public boolean isWelcomeBackTextVisible() {
        return welcomeBackToYazioText.isDisplayed();
    }
}
