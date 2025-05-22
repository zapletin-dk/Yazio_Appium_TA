package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

abstract class BasePage {
    public BasePage() {
        try {
            AppiumDriver driver = DriverManager.getDriver();
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing page elements: " + e.getMessage());
        }
    }
}
