package ui.pages.repo.commonplatform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ShippingBillingPageRepo extends UiBase {

    @FindBy(css=".jsEditShipAddress")
    public WebElement editShipAddress;


    @FindBy(css=".jsEditBillAddress")
    public WebElement editBillingAddress;

    @FindBy(css="#jsVal-shippingAddress")
    public WebElement shippingAddressLbl;

    @FindBy(css="#jsVal-billingAddress")
    public WebElement billingAddressLbl;


    @FindBy(css="#provinceRegion")
    public WebElement state_int;


    @FindBy(css="#shipTitle")
    public WebElement titleDropDwn;

    @FindBy(css="#shipFirst")
    public WebElement firstName;

    @FindBy(css="#shipLast")
    public WebElement lastName;

    @FindBy(css="#shipAddress1")
    public WebElement address;

    @FindBy(css="#shipCity")
    public WebElement city;

    @FindBy(css="#shipState")
    public WebElement state;

    @FindBy(css="#shipZip")
    public WebElement zipcode;

    @FindBy(css="#shipEmail")
    public WebElement email;

    @FindBy(css="#shipPhone")
    public WebElement contactnumber;

    @FindBy(css="#jsCompleteMultiShip")
    public WebElement multiShippingContinue;

    @FindBy(css="#tab-shipbill")
    public WebElement shippingBillingTab;



    @FindBy(css=".sfa-button.large.transactional")
    public WebElement continuecheckoutshippage;

    @FindBy(css="#jsUseEntered")
    public WebElement useAddressAsEntered;


}
