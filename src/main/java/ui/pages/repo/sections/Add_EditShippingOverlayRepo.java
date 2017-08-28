package ui.pages.repo.sections;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/23/16.
 */
public class Add_EditShippingOverlayRepo extends UiBase {


    public WebElement selectExistingAddressRadio(){
        return getDriver().findElement(By.cssSelector("label[for='jsChooseExistingShippingAddress']"));
    }


    public WebElement enterNewShippingAddressRadio(){
        return getDriver().findElement(By.cssSelector("label[for=jsNewShippingAddress]"));
    }

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

    @FindBy(css="#provinceRegion")
    public WebElement state_int;

    @FindBy(css="#shipZip")
    public WebElement zipcode;

    @FindBy(css="#shipPhone")
    public WebElement contactnumber;

    @FindBy(css="#makeDefault-wrap > label > span")
    public WebElement makeDefaultShipping;

    @FindBy(css="#makeBilling-wrap > label > span")
    public WebElement addToBillingBook;

    @FindBy(css="#jsUpdateShippingAddress")
    public WebElement applyAddress;

    @FindBy(css=".checkout-cancel")
    public WebElement cancelAddress;



    public Select shippingExistingDropDwn(){
        return new Select(getDriver().findElement(By.id("jsExistingShippingDropDown")));
    }

    @FindBy(css="#jsUseEntered")
    public WebElement useAddressAsEntered;



}
