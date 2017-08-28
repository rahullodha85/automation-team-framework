package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.actions.sectionsmobile.Add_EditBillingOverlay;
import ui.pages.actions.sectionsmobile.Add_EditPaymentOverlay;
import ui.pages.actions.sectionsmobile.Add_EditShippingOverlay;
import ui.pages.actions.sectionsmobile.SaksCart;
import ui.pages.repo.commonplatformmobile.ReviewSubmitmPageRepo;
import ui.support.Config;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class ReviewSubmitmPage extends ReviewSubmitmPageRepo {
    WebDriver driver;
    SaksCart sakscart;
    Add_EditShippingOverlay add_editShippingOverlay;
    Add_EditBillingOverlay add_editBillingOverlay;
    Add_EditPaymentOverlay add_editPaymentOverlay;
    public ReviewSubmitmPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        sakscart = new SaksCart(driver);
        add_editShippingOverlay = new Add_EditShippingOverlay(driver);
        add_editBillingOverlay = new Add_EditBillingOverlay(driver);
        add_editPaymentOverlay = new Add_EditPaymentOverlay(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean haseditPayment() {
        if(editPayment.isDisplayed()){
            return true;
        }else return false;
    }

    public boolean hasPlaceOrder() {
        if(placeOrderBtn.isDisplayed()){
            return true;
        }else return false;
    }

    public String clickPlaceOrder() {
        clickJs(placeOrderBtn);
        waitForAjax("Wait for thankyou page to load");
        return orderNumber.getText().split(" ")[1].substring(1).split("\\n")[0];

    }

    public void emptyCartService(int index) throws Exception {
        sakscart.emptyCartService(index);
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp");
        waitForAjax("loading on page after remove");
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

    public void editAndAddPaymentMethod(Object creditcardtype2, Object creditcardnumber2) {
        clickJs(editPayment);
        add_editPaymentOverlay.addNewPaymentMethod(creditcardtype2.toString(),creditcardnumber2.toString());
    }

    public void updateCartService(Object p0,Object p1) throws Exception {

        //jsCartItem-(\d{4})".*?"0413806435288"
        sakscart.updateCart(p0.toString(),p1.toString());
       /* String regexItemId="Item-(\\d+)\".*?"+p0.toString();
        Object cartItemId= regexSearch.getString(regexItemId,getDriver().getPageSource());
        makeRequest("checkout/checkout.jsp?bmForm=update_cart_item_quantity_service&cartItemId="+cartItemId.toString()+"&itemQuantity="+p1.toString());
        waitForTextOnPage("breadcrumbs");
        makeRequest("checkout/checkout.jsp");
        waitForPageReadyState("waiting after updating page after updating cart");*/

    }

    public void editAndEditExistingShippingAddress(Object address1) {
        clickJs(editShippingAddress);
        Object shippingAddressId=executeJavaScript("return Order.getShippingAddressList()[0].id","finding shipping address id");
        clickJs(editShippingAddressSpecific(shippingAddressId.toString()));
        waitForAjax("wait for overlay to open");
        add_editShippingOverlay.editShippingAddress(address1.toString());
    }

    public void editAndEditExistingBillingAddress(Object address1) {
        clickJs(editBillingAddress);
        Object billingAddressId=executeJavaScript("return Order.getBillingAddressList()[0].id","finding shipping address id");
        clickJs(editShippingAddressSpecific(billingAddressId.toString()));
        add_editBillingOverlay.editBillingAddress(address1.toString());
    }

    public void editAndChooseExistingBillingAddress(int index) {
//
        clickJs(editBillingAddress);
//        waitForAjax("waiti");
        add_editBillingOverlay.chooseExistingBillingAddress(index);


    }

    public void editAndEnterNewShippingAddress(Object s, Object city3, Object state3, Object zipcode3, boolean b) throws InterruptedException {
        clickJs(editShippingAddress);
        clickJs(createNewShippingAddress);
        add_editShippingOverlay.enterNewShippingAddress(s.toString(),city3.toString(),state3.toString(),zipcode3.toString(),b);
    }
}
