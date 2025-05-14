package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {
    public MainPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.yazio.android:id/bottomNavAboutMe")
    private WebElement profileButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome to YAZIO']")
    private WebElement welcomeToYazioText;

    public boolean welcomeToYazioTextIsShown(){
        return welcomeToYazioText.isDisplayed();
    }
}
