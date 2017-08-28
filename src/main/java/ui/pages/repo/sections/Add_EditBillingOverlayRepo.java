package ui.pages.repo.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/24/16.
 */
public class Add_EditBillingOverlayRepo extends UiBase{



    public WebElement selectExistingAddressRadio(){
        return getDriver().findElement(By.cssSelector("label[for='jsSelectExistingBillingAddress']"));
    }


    public WebElement enterNewShippingAddressRadio(){
        return getDriver().findElement(By.cssSelector("label[for=jsNewBillingAddress]"));
    }

    @FindBy(css="#jsBillingTitleDropDown")
    public WebElement titleDropDwn;

    @FindBy(name="BILL_TO_ADDRESS<>firstName")
    public WebElement firstName;

    @FindBy(name="BILL_TO_ADDRESS<>lastName")
    public WebElement lastName;

    @FindBy(name="BILL_TO_ADDRESS<>address1")
    public WebElement address;

    @FindBy(name="BILL_TO_ADDRESS<>city")
    public WebElement city;

    @FindBy(css="#jsBillingStateDropDown")
    public WebElement state;

    @FindBy(css="#billZip")
    public WebElement zipcode;

    @FindBy(name="BILL_TO_ADDRESS<>phone")
    public WebElement contactnumber;

    @FindBy(css="#save_billing_address_service > div.field-group.grid.grid-parent > div:nth-child(14) > label > span")
    public WebElement makeDefaultShipping;

    @FindBy(css="#save_billing_address_service > div.field-group.grid.grid-parent > div:nth-child(15) > label > span")
    public WebElement addToBillingBook;

    @FindBy(css="#jsUpdateBillingAddress")
    public WebElement applyAddress;

    @FindBy(css="#provinceRegion")
    public WebElement state_int;

    @FindBy(css=".checkout-cancel")
    public WebElement cancelAddress;

    @FindBy(css="#jsBillingCountryDropDown")
    public WebElement country;



    public Select billingExistingDropDwn(){
        return new Select(getDriver().findElement(By.id("jsExistingBillingDropDown")));
    }
    @FindBy(css="#jsUseEntered")
    public WebElement useAddressAsEntered;
}
