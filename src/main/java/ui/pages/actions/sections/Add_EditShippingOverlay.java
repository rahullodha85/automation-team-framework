package ui.pages.actions.sections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.UiBase;
import ui.pages.repo.sections.Add_EditShippingOverlayRepo;

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
    public void enterNewShippingAddress(String address1,String city1,String state1,String zipcode1){
        addPartialShippingAddress(address1,city1,state1,zipcode1);
        applyShippingAddress();

    }

    private void applyShippingAddress() {
        applyAddress.click();
        waitForAjax("making call to qas add edit shipping");
        if(getpageSource().indexOf("We have checked your address against")>=0){
            clickJs(useAddressAsEntered);
        }
        waitForAjax("clicked on entered new shipping address on review submit");
    }

    public void enterNewShippingAddress(String address1,String city1,String state1,String zipcode1,boolean addToBilling){
        addPartialShippingAddress(address1,city1,state1,zipcode1);
        if(addToBilling)
        addToBillingBook.click();

        applyShippingAddress();
    }

    private void addPartialShippingAddress(String address1,String city1,String state1,String zipcode1) {
        clickJs(selectExistingAddressRadio());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        enterNewShippingAddressRadio().click();

        titleDropDwn.sendKeys("Mr.");
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
    }


    public void editShippingAddress(String address1){
        waitForAjax("clicked on edit shipping");
        address.clear();
        address.sendKeys(address1);

        applyShippingAddress();
    }


    public void chooseExistingShippingAddress(int index) {
        clickJs(selectExistingAddressRadio());
        waitForAjax("wait for window edit to open");
        shippingExistingDropDwn().selectByIndex(index);
        applyShippingAddress();
    }

    public void addAddressToBillingBook() {
        addToBillingBook.click();
        applyShippingAddress();
    }
}
