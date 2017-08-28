package ui.pages.actions.commonplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ui.pages.actions.sections.Add_EditBillingOverlay;
import ui.pages.actions.sections.Add_EditPaymentOverlay;
import ui.pages.actions.sections.Add_EditShippingOverlay;
import ui.pages.actions.sections.SaksCart;
import ui.pages.repo.commonplatform.ReviewSubmitPageRepo;
import ui.support.Config;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ReviewSubmitPage extends ReviewSubmitPageRepo {
    WebDriver driver;
    SaksCart sakscart;
    Add_EditShippingOverlay add_editShippingOverlay;
    Add_EditBillingOverlay add_editBillingOverlay;
    Add_EditPaymentOverlay add_editPaymentOverlay;
    public ReviewSubmitPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        sakscart = new SaksCart(driver);
        add_editShippingOverlay = new Add_EditShippingOverlay(driver);
        add_editBillingOverlay = new Add_EditBillingOverlay(driver);
        add_editPaymentOverlay = new Add_EditPaymentOverlay(driver);
        PageFactory.initElements(driver, this);
    }

    public String clickPlaceOrder() {
        clickJs(placeOrderBtn);
        waitForAjax("Wait for thankyou page to load");
        return orderNumber.getText().split(" ")[1].substring(1).split("\\n")[0];

        
    }

    public boolean hasGiftCardField() {
        return waitUntilElementDisplayed(giftCardField);
    }

    public void updateQuantity(Object product2, String s){

        sakscart.updateQuantity(product2,s);

    }
    public double getSubTotal(Object product){
        return sakscart.getSubTotal(product.toString());
    }

    public boolean hasQuantityUpdated(Object product4,String s){
       return sakscart.hasQuantityUpdated(product4,s);
    }

    public boolean hasItemBeenRemoved(Object product){
        return  sakscart.hasItemBeenRemoved(product);
    }

    public void removeItem(Object product1) {
        sakscart.removeItem(product1.toString());
    }
    public void makeGift(Object product1) {
        sakscart.makeGift(product1.toString());
    }

    public boolean hasGiftApplied(Object upc){
        return sakscart.hasGiftApplied(upc.toString());
    }
    public void editAndEnterNewShippingAddress(Object upc,Object address1,Object city1,Object state1,Object zipcode1){
        clickJs(multipleShipping);
        sakscart.editShippingAddress(upc);
        add_editShippingOverlay.enterNewShippingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString());
    }


    public void editAndEnterNewShippingAddress(Object address1,Object city1,Object state1,Object zipcode1,boolean addToBilling){
        clickJs(editShippingAddress);
        add_editShippingOverlay.enterNewShippingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString(),addToBilling);
    }
    public void editAndEnterNewShippingAddress(Object address1,Object city1,Object state1,Object zipcode1){
        clickJs(editShippingAddress);
        add_editShippingOverlay.enterNewShippingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString());
    }


    public boolean hasShippingAddressUpdated(String bldNo) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(shippingAddressLbl.getText().indexOf(bldNo)>=0){
            return true;
        }
        else return false;
    }

    public void editAndEnterNewBillingAddress(Object address1,Object city1,Object state1,Object zipcode1,Object country1){
        clickJs(editBillingAddress);
        add_editBillingOverlay.enterNewBillingAddress(address1.toString(),city1.toString(),state1.toString(),zipcode1.toString(),country1.toString());
    }


    public boolean hasBillingAddressUpdated(String bldNo) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(billingAddressLbl.getText().indexOf(bldNo)>=0){
            return true;
        }
        else return false;
    }

    public void editAndEditExistingShippingAddress(String address1){
        clickJs(editShippingAddress);
        add_editShippingOverlay.editShippingAddress(address1);

    }
    public void editAndEditExistingBillingAddress( String address1){
        clickJs(editBillingAddress);
        add_editBillingOverlay.editBillingAddress(address1);

    }

    public void changeShippingMethod(String shippingMethod) {
        switch (shippingMethod.toLowerCase()){
            case "rush":clickJs(shippingMethod(1));
            default:
                waitForAjax("Wait For Shipping Method to be applied on review submit");
        }
    }

    public boolean hasShippingMethodChanged(String shippingMethod) {

        if(shippingMethodAddressLbl.getText().toLowerCase().indexOf(shippingMethod)>=0){
                return true;
        }else{
                return false;
        }

    }

    public boolean hasShippingMethodChangedMutipleItems(Object upc,String shippingMethod) {

        return sakscart.hasShippingMethodChangedMultipleItems(upc,shippingMethod);

    }

    public boolean hasDropShipItem(Object product5) {
        return sakscart.hasDropShipIteminCart(product5.toString());
    }

    public boolean hasEGCinCart(Object product6) {
        return sakscart.hasEGCIteminCart(product6.toString());

    }

    public boolean toggleSignature() {
        return false;
    }

    public boolean hasPersonalizedItem(Object product7) {
        return sakscart.hasPersonalizedItem(product7.toString());

    }

    public boolean hasPreOrderItemInCart(Object product8) {
        return sakscart.hasPreOrderItemInCart(product8.toString());

    }

    public void editAndeditCreditCardName(String s) {
        editPayment.click();
        add_editPaymentOverlay.editCreditCardName(s);
    }

    public boolean isPaymentNameEdited(String name) {
        if(creditCardLbl.getText().indexOf(name)>=0)
        return true;
        else return false;
    }

    public void clickShippingBillingTab() {
        clickJs(shipBillTab);
    }

    public void emptyCartService(int index) throws Exception{
        sakscart.emptyCartService(index);
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp");
        waitForAjax("loading on page after remove");

    }

    public void clickReviewSubmitTab() {
        reviewSubmitTab.click();
        waitForAjax("wait for review submit page");
    }

    public void clickShippingMethod(Object product1, String shippingMethod) {
        sakscart.clickShippingMethod(product1,shippingMethod);
    }

    public void removeGift(Object product1) {
        sakscart.removeGift(product1.toString());
    }

    public boolean hasGiftApplicationRemoved(Object product1) {
        return sakscart.hasGiftApplicationRemoved(product1.toString());
    }

    public boolean hasRestrictedShippingMethod(Object product9) {
        return sakscart.hasRestrictionShippingMethod(product9.toString());

    }
    public boolean haseditPayment(){
        if(editPayment.isDisplayed()){
            return true;
        }else return false;
    }

    public void editAndAddPaymentMethod(Object creditcardtype2, Object creditcardnumber2) {
        clickJs(editPayment);
        add_editPaymentOverlay.addNewPaymentMethod(creditcardtype2.toString(),creditcardnumber2.toString());
    }

    public void editAndAddShippingAddressToBillingBook() {
        clickJs(editShippingAddress);
        add_editShippingOverlay.addAddressToBillingBook();
    }

    public void editAndChooseExistingBillingAddress(int index) {
        clickJs(editBillingAddress);
        add_editBillingOverlay.chooseExistingBillingAddress(index);
    }
}
