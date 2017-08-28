package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.actions.sections.Add_EditBillingOverlay;
import ui.pages.actions.sections.Add_EditPaymentOverlay;
import ui.pages.actions.sections.Add_EditShippingOverlay;
import ui.pages.actions.sections.SaksCart;
import ui.pages.repo.commonplatform.ShippingBillingPageRepo;
import ui.support.Config;

import java.util.Random;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ShippingBillingPage extends ShippingBillingPageRepo {
    WebDriver driver;
    SaksCart sakscart;
    Add_EditShippingOverlay add_editShippingOverlay;
    Add_EditBillingOverlay add_editBillingOverlay;

    public ShippingBillingPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        sakscart = new SaksCart(driver);
        add_editShippingOverlay = new Add_EditShippingOverlay(driver);
        add_editBillingOverlay = new Add_EditBillingOverlay(driver);

        PageFactory.initElements(driver, this);
    }

    public void editAndEnterNewShippingAddress(Object address1, Object city1, Object state1, Object zipcode1) throws Exception {

        if(isInternational()){
            driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp#shipBill");
        }
        clickJs(editShipAddress);
        add_editShippingOverlay.enterNewShippingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString());

    }
    public void editAndEnterNewBillingAddress(Object address1,Object city1,Object state1,Object zipcode1,Object country1){
        clickJs(editBillingAddress);
        add_editBillingOverlay.enterNewBillingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString(),country1.toString());
    }

    public boolean hasShippingAddressUpdated(String bldNo) {
        if(shippingAddressLbl.getText().indexOf(bldNo)>=0){
            return true;
        }else return false;
    }

    public boolean hasBillingAddressUpdated(String bldNo) {
        if(billingAddressLbl.getText().indexOf(bldNo)>=0){
            return true;
        }else return false;
    }
    public boolean hasFirstNameField(){
        if(firstName.isDisplayed())
            return true;
        else return false;
    }
    public String addNewShippingAddress(Object address1,Object city1,Object state1,Object zipcode1){
            Object country=executeJavaScript("return getCookie('E4X_COUNTRY')");
            String email1="testuser"+new Random().nextInt(1000000)+"@gmail.com";
            titleDropDwn.sendKeys("Mr.");
            firstName.sendKeys("Hello");
            lastName.sendKeys("World");
            address.sendKeys(address1.toString());
            city.sendKeys(city1.toString());
        if(isInternational()&& !country.toString().equalsIgnoreCase("CA")){
            //state_int.clear();
            waitForAjax("add shipping address new unreg");
            state_int.sendKeys(state1.toString());
        }
        else if((isInternational()&& country.toString().equalsIgnoreCase("CA"))){
            waitForAjax("add shipping address new unreg");
            state.sendKeys(state1.toString());
        }
        else{
           // state.clear();
            state.sendKeys(state1.toString());}


            zipcode.sendKeys(zipcode1.toString());
            email.sendKeys(email1);
            contactnumber.sendKeys("2129990877");
            continuecheckoutshippage.click();
            waitForAjax("wait for loading of payment page");
            if(getDriver().getPageSource().indexOf("We have checked your address")>=0){
                clickJs(useAddressAsEntered);
                waitForAjax("wait for qas service to confirm validation before going to payment");
            }else {

            }
        return email1;


    }
    public void clickMultiShippingContinueCheckout(){
        clickJs(multiShippingContinue);
    }
    public boolean hasMultiShippingAddressUpdate(String bldNo) {
        if(getDriver().getPageSource().indexOf(bldNo)>=0){
                return true;
        }return false;
    }

    public void selectShippingMethod(Object product1, String shippingMethod) {
        sakscart.selectShippingMethod(product1,shippingMethod);
    }

    public void clickSignatureAtDelievery(Object product1) {
        sakscart.clickSignatureAtDelievery(product1.toString());
    }

    public boolean hasShippingMethodSelected(Object product1,String shippingMethod) {
        return sakscart.hasShippingMethodSelected(product1.toString(),shippingMethod);
    }

    public void clickContinueCheckout() {
        continuecheckoutshippage.click();
        waitForAjax("Waiting for payment page to load");
    }

    public void editAndChooseExistingShippingAddress(int index) {
        if(isInternational()){
            shippingBillingTab.click();
        }
        clickJs(editShipAddress);
        add_editShippingOverlay.chooseExistingShippingAddress(index);

    }

    public void editAndChooseExistingBillingAddress(int index) {
        if(isInternational()){
            clickJs(shippingBillingTab);
        }
        clickJs(editBillingAddress);
     add_editBillingOverlay.chooseExistingBillingAddress(index);

    }
}
