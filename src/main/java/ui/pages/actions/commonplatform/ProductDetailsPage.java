package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ProductDetailsPageRepo;
import ui.support.Config;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ProductDetailsPage extends ProductDetailsPageRepo {
    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    //
    public void addToBag(Object product,Object sku,Object quanity) throws Exception {

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+product.toString()+"&itemQuantity="+quanity.toString()+"&sku_id="+sku.toString()+"&type=REQUEST_ADD_TO_BAG");
        waitForTextOnPage("addToBagResults");

    }

    public void addToBag() {
        waitForAjax("waiting for page to load the pdp page");
        waitUntilElementDisplayed(addToBag);
        clickJs(addToBag);
   }
    public void addToBag(String qty) {
        waitForAjax("waiting for page to load the pdp page");

        waitUntilElementDisplayed(addToBag);
        qtyBox.clear();
        qtyBox.sendKeys(qty);

        clickJs(addToBag);


        //addToBag.click();
    }

    public void enterBag() {
        executeJavaScript("arguments[0].setAttribute('style', 'top: 99px; right: 440.438px; z-index: 9998; display: block;')",saksBagOverlay);
        waitUntilElementDisplayed(viewMyBag);
        viewMyBag.click();

    }


    public boolean hasOverlayAppeared() {

       return waitUntilElementDisplayed(viewMyBag);


    }

    public boolean hasAddToBagButton() {
        waitForAjax("wait before checking add to bag button is present");
      return waitUntilElementDisplayed(addToBag);
    }
    public boolean personalizedPageLoaded() {
        clickJs(qtyBox);
            if(getpageSource().indexOf("Personalize this item")>=0){
                 return true;
            }
        else{
                return false;
            }

    }

    public boolean isItemAdded(Object product) throws InterruptedException {

        waitForTextOnPage("addToBagResults");

        System.out.println(getpageSource());
        if(getpageSource().indexOf(product.toString())>=0){
            return true;
        }else
            return false;
    }

    public void addToBagPersonalized() {

        clickJs(personalAddToBag);
        waitForAjax("wait for personal bag page to load");
        textBoxForMessagePersonalized.sendKeys("PTM");
        clickJs(personalAddToBagFinal);
        waitForAjax("added personalized item to bag");
    }
}
