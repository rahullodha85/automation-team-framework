package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatformmobile.HomemPageRepo;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class HomemPage extends HomemPageRepo {
    WebDriver driver;
    public HomemPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasCountryChanged(Object country1) throws InterruptedException {
        waitForAjax("ajax request for context change");
        waitForPageReadyState("Waiting for Context Chooser to complete change request");
        Object country= executeJavaScript("return getCookie('E4X_COUNTRY')");
        if(country.toString().equals(country1.toString())){
        return true;
        }
        else return false;
    }

    public void searchFor(Object product3) {

    }
}
