package ui.pages.repo.commonplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ReviewSubmitPageRepo extends UiBase {
    @FindBy(css="#jsReviewSubmit")
    public WebElement placeOrderBtn;

    @FindBy(css=".receipt-order-detail-list")
    public WebElement orderNumber;

    @FindBy(css="html")
    public WebElement htmlPage;

    @FindBy(css="#payGCNum")
    public WebElement giftCardField;

    @FindBy(css=".jsShipToMultiple")
    public WebElement multipleShipping;


    @FindBy(css="#jsVal-shippingAddress")
    public WebElement shippingAddressLbl;

    @FindBy(css=".jsChangeShipAddress")
    public WebElement editShippingAddress;



    @FindBy(css=".jsEditCreditCard")
    public WebElement editPayment;

    @FindBy(css="#jsVal-billingAddress")
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

    @FindBy(css="#jsVal-creditCard")
    public WebElement creditCardLbl;

    @FindBy(css="#tab-shipbill")
    public WebElement shipBillTab;

    @FindBy(css=".widebag-item-shipment-summary.top-divided.grid.grid-parent")
    public WebElement shippingMethodAddressLbl;

    //

}
