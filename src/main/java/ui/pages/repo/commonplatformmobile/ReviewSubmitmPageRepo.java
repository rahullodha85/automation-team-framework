package ui.pages.repo.commonplatformmobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class ReviewSubmitmPageRepo extends UiBase {
    @FindBy(css="#jsReviewSubmit")
    public WebElement placeOrderBtn;

    @FindBy(css="#confirmation-head > h2")
    public WebElement orderNumber;

    @FindBy(css="html")
    public WebElement htmlPage;

    @FindBy(css="#payGCNum")
    public WebElement giftCardField;

    @FindBy(css=".jsShipToMultiple")
    public WebElement multipleShipping;


    @FindBy(css="#module-shipping-address")
    public WebElement shippingAddressLbl;

    @FindBy(css=".jsChangeShipAddress")
    public WebElement editShippingAddress;



    @FindBy(css=".jsEditCreditCard")
    public WebElement editPayment;

    @FindBy(css="#jsVal-singleBillingAddress")
    public WebElement billingAddressLbl;

    @FindBy(css=".jsEditBillAddress")
    public WebElement editBillingAddress;

    @FindBy(css="#tab-review")
    public WebElement reviewSubmitTab;


    @FindBy(css=".jsAddressLevelShipping")
    public WebElement shippingMethodAddressLevel;

    public WebElement shippingMethod(int index){
        return shippingMethodAddressLevel.findElements(By.tagName("label")).get(index);
    }

    public WebElement editShippingAddressSpecific(String id){
        return getDriver().findElement(By.cssSelector("#option-"+id+"-wrap > label > div > div"));
    }

    @FindBy(css="#address-book-shipping-create > span.title")
    public WebElement createNewShippingAddress;
    @FindBy(css="#jsVal-creditCard")
    public WebElement creditCardLbl;

    @FindBy(css="#tab-shipbill")
    public WebElement shipBillTab;


    @FindBy(css=".widebag-item-shipment-summary.top-divided.grid.grid-parent")
    public WebElement shippingMethodAddressLbl;
}
