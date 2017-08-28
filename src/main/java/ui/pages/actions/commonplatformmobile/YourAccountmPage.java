package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatformmobile.YourAccountmPageRepo;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class YourAccountmPage extends YourAccountmPageRepo {
    WebDriver driver;
    public YourAccountmPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasSignInSection() {
        return false;
    }

    public boolean hasOrderDetails() {
        return false;
    }
}
