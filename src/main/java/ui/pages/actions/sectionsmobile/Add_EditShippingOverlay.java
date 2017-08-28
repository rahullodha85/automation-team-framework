package ui.pages.actions.sectionsmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.sectionsmobile.Add_EditShippingOverlayRepo;

/**
 * Created by vevinmoza on 3/23/16.
 */
public class Add_EditShippingOverlay extends Add_EditShippingOverlayRepo {
    WebDriver driver;

    public Add_EditShippingOverlay(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public void enterNewShippingAddress(String address1,String city1,String state1,String zipcode1) throws InterruptedException {

        enterNewShippingAddressRadio().click();

        waitForAjax("waiting for edit shipping address overlay on mobile on shipping page to load");
        Thread.sleep(1000);
        firstName.clear();
        firstName.sendKeys("Hello");
        lastName.clear();
        lastName.sendKeys("World");
        address.clear();
        address.sendKeys(address1);
        city.clear();
        city.sendKeys(city1);
       if(isInternational()){
           state_int.clear();
           state_int.sendKeys(state1);
       }else{
           state.clear();
        state.sendKeys(state1);}
        zipcode.clear();
        zipcode.sendKeys(zipcode1);
        contactnumber.clear();
        contactnumber.sendKeys("213-999-9088");
        applyAddress.click();
        waitForAjax("making call to qas add edit shipping");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked on entered new shipping address on review submit");
    }
    public void enterNewShippingAddress(String address1,String city1,String state1,String zipcode1,boolean addToBilling) throws InterruptedException {

        //enterNewShippingAddressRadio().click();
       // waitForAjax("waiting for edit shipping address overlay on mobile on shipping page to load");
        Thread.sleep(1000);
        firstName.clear();
        firstName.sendKeys("Hello");
        lastName.clear();
        lastName.sendKeys("World");
        address.clear();
        address.sendKeys(address1);
        city.clear();
        city.sendKeys(city1);
        if(isInternational()){
            state_int.clear();
            state_int.sendKeys(state1);
        }else{
            state.clear();
            state.sendKeys(state1);}
        zipcode.clear();
        zipcode.sendKeys(zipcode1);
        contactnumber.clear();
        contactnumber.sendKeys("213-999-9088");
        if(addToBilling)
            addToBillingBook.click();
        applyAddress.click();
        waitForAjax("making call to qas add edit shipping");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked on entered new shipping address on review submit");
    }

    public void editShippingAddress(String address1){
        waitForAjax("clicked on edit shipping");
        waitUntilElementDisplayed(address);
        address.clear();
        address.sendKeys(address1);
        applyAddress.click();
        waitForAjax("making call to qas edit shipping");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("saving edit shipping address on review submit");
    }


    public void chooseExistingShippingAddress(int index) {
        clickJs(selectExistingAddressRadio());
        clickJs(applyAddress);
        waitForAjax("wait for window edit to open");


        waitForAjax("making call to qas add edit shipping");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked on entered new shipping address on review submit");
    }
}
