package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatformmobile.ProductDetailsmPageRepo;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class ProductDetailsmPage extends ProductDetailsmPageRepo {
    WebDriver driver;
    public ProductDetailsmPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    public boolean isItemAdded(Object product1) throws InterruptedException {
        waitForPageReadyState("waiting for pdp page to load");
        Thread.sleep(2000);
        if(getpageSource().indexOf(product1.toString())>=0){
            return true;
        }else
            return false;
    }
}
