package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatformmobile.ThankYoumPageRepo;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class ThankYoumPage extends ThankYoumPageRepo {
    WebDriver driver;
    public ThankYoumPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isOrderPlaced() {
        if(orderNumber.getText().indexOf("Order #")>=0)
            return true;
        else return false;
    }
}
