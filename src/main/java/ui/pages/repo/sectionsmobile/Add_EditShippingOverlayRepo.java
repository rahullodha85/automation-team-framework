package ui.pages.repo.sectionsmobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.UiBase;

import java.util.List;

/**
 * Created by vevinmoza on 3/23/16.
 */
public class Add_EditShippingOverlayRepo extends UiBase {


    public WebElement selectExistingAddressRadio(){
        List<WebElement> addresses=getDriver().findElements(By.cssSelector("div[class='option address-option']"));
        for(int i=0;i<addresses.size();i++){
            System.out.println(addresses.get(i).getText());
         if(addresses.get(i).getAttribute("outerHTML").indexOf("checked")>=0){
             continue;
         }
            else {
             return addresses.get(i).findElement(By.tagName("label"));}
        }
        return null;
    }


    public WebElement enterNewShippingAddressRadio(){
        return getDriver().findElement(By.id("address-book-shipping-create"));
    }

    @FindBy(css="#shipTitle")
    public WebElement applySelectedAddress;

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

    @FindBy(css="#makeBilling-wrap > label")
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
