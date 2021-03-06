package ui.pages.actions.sectionsmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.sectionsmobile.Add_EditBillingOverlayRepo;

/**
 * Created by vevinmoza on 3/24/16.
 */
public class Add_EditBillingOverlay  extends Add_EditBillingOverlayRepo{

    WebDriver driver;

    public Add_EditBillingOverlay(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public void enterNewBillingAddress(String address1,String city1,String state1,String zipcode1,String country1){

        enterNewShippingAddressRadio().click();
        waitForAjax("waiting for edit billing address overlay on mobile on shipping page to load");

        firstName.sendKeys("Hello");
        lastName.sendKeys("World");
        country.sendKeys(country1);
        waitForAjax("wait ajax after fill country");
        address.sendKeys(address1);
        waitForAjax("w1");
        city.sendKeys(city1);
        if(isInternational()){
            state_int.clear();
            waitForAjax("w1");
            state_int.sendKeys(state1);
        }else{
            state.clear();
        state.sendKeys(state1);}
        waitForAjax("w2");
        zipcode.sendKeys(zipcode1);
        contactnumber.sendKeys("213-999-9088");
        applyAddress.click();
        waitForAjax("making call to qas billing");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked entered billing address new review submit page");
    }
    public void editBillingAddress(String address1){
        waitForAjax("clicked on edit billing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitUntilElementDisplayed(address);
        address.clear();
        address.sendKeys(address1);
        address.sendKeys("United States");
        applyAddress.click();
        waitForAjax("making call to qas edit billing");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked after editing the billing address");
    }


    public void chooseExistingBillingAddress(int index) {
        clickJs(getAddressSpecific(index));
        clickJs(getAddressSpecific(index-1));
        clickJs(getAddressSpecific(index));
        //waitForAjax("selecting billing address radio ");
       // clickJs(applyBillingAddress);
        clickJs(applyBillingAddress);
      //  applyBillingAddress.click();
    }
}
