package pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import configurations.DriverManager;

abstract class BasePage {
    public BasePage() {
        try {
            PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
        } catch (Exception e) {
            throw new RuntimeException("Error initializing page elements: " + e.getMessage());
        }
    }
}
