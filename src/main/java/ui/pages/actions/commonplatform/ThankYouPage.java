package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ThankYouPageRepo;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ThankYouPage extends ThankYouPageRepo {
    WebDriver driver;

    public ThankYouPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasPrintReceipt() {
        return waitUntilElementDisplayed(printReceipt);
    }

    public boolean isOrderPlaced() {
        if(orderNumber.getText().indexOf("Order #")>=0)
        return true;
        else return false;
    }
}
