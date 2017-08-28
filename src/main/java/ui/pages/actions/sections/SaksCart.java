package ui.pages.actions.sections;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ui.pages.repo.sections.SaksCartRepo;
import ui.support.Config;

import java.util.Random;

/**
 * Created by vevinmoza on 3/23/16.
 */
public class SaksCart extends SaksCartRepo {
    WebDriver driver;
    int quantity;
    public SaksCart(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasItemBeenRemoved(Object product4) {
            if(saksBagText.getText().indexOf(product4.toString())<=0){
                return true;
            }
        else return false;
    }

    public void removeItem(Object product1) {
        waitForAjax("wait to complete loading before removing item");
        waitUntilElementDisplayed(removeItemlink(product1.toString()));
        clickJs(removeItemlink(product1.toString()));
        clickJs(removeConfirm);
        waitForAjax("wait after clicking remove item");

    }
    public void updateQuantity(Object product2, String s){

        clickJs(editQuanityLnk(product2.toString()));
        waitUntilElementDisplayed(qtyOverlayItem);
        qtyOverlayItem.clear();
        qtyOverlayItem.sendKeys(s);
        apply.click();
        waitForAjax("wait after updating quantity on saksbag page");

        quantity=(int)(getSubTotal(product2.toString())/((double)(getUnitPrice(product2.toString()))));
        System.out.println("qty is: "+quantity);
    }


    public boolean hasQuantityUpdated(Object product4,String s) {
        if(quantity==Integer.parseInt(s)){
            return true;
        }
        else return false;
    }

    public void makeGift(String upc){
        clickJs(makeGiftLnk(upc));
        waitForAjax("Waiting for the gift wrapping window to appear");
        giftBoxName.sendKeys("Automation"+ new Random().nextInt(100000));
        giftMessage.sendKeys("Test Message");
        clickJs(applyGifting);
        waitForAjax("Clicked make a gift");
  }
    public String getGiftSummary(String upc){
        return giftSummaryLbl(upc).getText();

    }
    public boolean hasGiftApplied(String upc){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(getGiftSummary(upc).indexOf("Automation")>=0){
            return true;
        }
        else return false;
    }
    public double getSubTotal(Object product){
        return Double.parseDouble(getSubTotalItemLbl(product.toString()).getText().substring(1));
    }

    public double getUnitPrice(Object product){
        return Double.parseDouble(getUnitPriceLbl(product.toString()).getText().substring(1));
    }

    public void editShippingAddress(Object upc){
        clickJs(editShippingAddressLnk(upc.toString()));
    }


    public boolean hasDropShipIteminCart(String s) {
        if(getItem(s).getText().indexOf("This item cannot be returned")>=0){
            return true;
        }else
return false;
    }

    public boolean hasEGCIteminCart(String s) {
        if(getItem(s).getText().indexOf("Standard Gift Card")>=0){
            return true;
        }else
            return false;
    }

    public boolean hasPersonalizedItem(String s) {
        if(getItem(s).getText().indexOf("Personalized Business Card Envelope")>=0)
        {
        return true;
        }
        else{
            return false;
        }
    }

    public boolean hasPreOrderItemInCart(String s) {
        if(getItem(s).getText().indexOf("If you order this item now, you will not be charged until it ships ")>=0)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public void emptyCartService(int index) throws Exception{
        if(getSaksItemsInCart().size()==index)
            return;
                    while(true) {
                        waitForAjax("waiting before remove service");
                        String itemId = getSaksItemsInCart().get(0).getAttribute("id").split("\\-")[1];
                        driver.get(Config.getApplicationUrl() + "checkout/checkout.jsp?bmForm=remove_order_item_service&orderItemId=" + itemId);
                        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp");
                        waitForAjax("wait after removing item: " + itemId);
                        if(getSaksItemsInCart().size()==index){
                            break;
                        }

                    }

    }

    public void selectShippingMethod(Object product1, String shippingMethod) {

        switch (shippingMethod.toLowerCase()){
            case "rush":shippingMethodUpc(product1.toString()).selectByIndex(1);
            default:
                waitForAjax("Wait For Shipping Method to be applied for "+product1.toString());
        }
    }

    public void clickSignatureAtDelievery(Object upc) {
        clickJs(getSignatureDeliveryOption(upc.toString()));
        waitForAjax("waiting for signature delivery to be applied for "+upc.toString());
    }

    public void clickShippingMethod(Object product1, String shippingMethod) {

        switch (shippingMethod.toLowerCase()){
            case "rush":clickJs(shippingMethod(product1.toString(),1));
            default:
                waitForAjax("Wait For Shipping Method to be applied on review submit");
        }
    }

    public boolean hasShippingMethodSelected(String product1,String shippingMethod) {
        if((shippingMethod(product1)).getFirstSelectedOption().getText().toString().toLowerCase().indexOf(shippingMethod)>=0){
            return true;
        }
        return false;
    }

    public void removeGift(String s) {
        clickJs(getRemoveGift(s));
        waitForAjax("removing the gift message from the cart for "+s);

    }

    public boolean hasGiftApplicationRemoved(String s) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(getItem(s).getText().indexOf("Automation")<=0){
            return true;
        }
        else return false;
    }

    public boolean hasRestrictionShippingMethod(String s) {
        if(getItem(s).getText().indexOf("Restricted Shipping Method")>=0)
            return true;
        else return false;
    }

    public boolean hasShippingMethodChangedMultipleItems(Object upc, String shippingMethod) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getItem(upc.toString()).getText().toLowerCase());
            if(getItem(upc.toString()).getText().toLowerCase().indexOf(shippingMethod.toLowerCase())>=0){
                return true;
            }else return false;
    }


}
