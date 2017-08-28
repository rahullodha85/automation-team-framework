package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.SystemClock;
import ui.pages.actions.sections.SaksCart;
import ui.pages.repo.commonplatform.SaksBagPageRepo;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class SaksBagPage extends SaksBagPageRepo {
    WebDriver driver;
    SaksCart sakscart;

    public SaksBagPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        sakscart = new SaksCart(driver);
        PageFactory.initElements(driver, this);
    }

    public void applyPromo(Object promo) {
        promoBox.sendKeys(promo.toString());
        clickJs(promoApplyBtn);
    }

    public SaksBagPage clickCheckout() {
        Object loginRequired=executeJavaScript("return Order.isLoginRequired()");
       // Object internationalorder=executeJavaScript("return services.isInternational()");
        //if(executeJavaScript("return Order.isLoginRequired()"))
       if(loginRequired.toString().equalsIgnoreCase("true"))
       {clickJs(checkoutbagBtn);}
        else{clickJs(enterCheckoutbagBtn);}
        return new SaksBagPage(driver);
    }

    public void loginWith(Object login, Object password) throws InterruptedException {
      login(login.toString(),password.toString());
        Thread.sleep(3000);
        boolean pageStateSaksBag=saksBody.getText().toLowerCase().contains("previous sessions");
        System.out.println(saksBody);
        if(pageStateSaksBag) {

            clickJs(checkoutbag_reBtn);
        }
        waitForAjax("waiting after clicking checkout on saks bag");
        if(saksBody.getText().indexOf("There is no default shipping/billing address.")>=0){
            return;
        }

      // System.out.println("******************");
        //waitUntilElementDisplayed(,5);

        //change this condition for payment page, something spefic asking willis,ajay
        //how to reinitilize the html page after click
        if(saksBody.getText().toLowerCase().indexOf("security code")>=0 && saksBody.getText().toLowerCase().indexOf("continue checkout")>=0 ) {
            waitUntilElementDisplayed(paymentCvv, 4);
            paymentCvv.sendKeys("121");
        }
        System.out.println("test payment page: "+getpageSource().toLowerCase().contains("continue checkout"));
        if(getpageSource().toLowerCase().contains("continue checkout")){
            continueCheckoutPayment.click();
        }
        waitForAjax("waiting after clicking checkout on payment page");

    }

    public void loginWithInvalidPassword(Object login, Object password)  {
        login(login.toString(),password.toString());

    }

    public boolean hasPromoCodeField() {
        return waitUntilElementDisplayed(promoBox);

    }
    private void login(String login,String password){
        loginBox.sendKeys(login.toString());
        passwordBox.sendKeys(password.toString());
        clickJs(signInCheckout);

        // waitUntilAnimationIsDone(".loading-overlay");
        waitForAjax("waiting after clicking checkout on login window");
    }

    public boolean hasPromoRemoveLink() {
    return waitUntilElementDisplayed(promoRemoveLnk);

    }

    public boolean hasItemBeenRemoved(Object product){
        return  sakscart.hasItemBeenRemoved(product);
    }

    public void removeItem(Object product1) {
            sakscart.removeItem(product1.toString());
    }

    public void makeGift(Object product1) {
        sakscart.makeGift(product1.toString());
        waitForAjax("Wait after making it a gift for page to load");
    }

    public boolean hasGiftApplied(Object upc){
        return sakscart.hasGiftApplied(upc.toString());
    }


    public boolean hasDropShipItem(Object product5) {
        return sakscart.hasDropShipIteminCart(product5.toString());
    }

    public void forgotPassword(Object login1) {
        getforgotPasswordlink().click();
        waitForAjax("wait for forgot password page");
        emailForgotPassword.sendKeys(login1.toString());
        getContinueButtonforgotPassword().click();


    }

    public boolean hasPasswordReset(Object login1) {
        waitForAjax("wait for password reset");
        if(getDriver().getPageSource().indexOf("An e-mail has been sent to you at "+login1.toString())>=0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasInvalidPassword() {
        waitForAjax("wait for invalid password message");
        if(getDriver().getPageSource().indexOf("The e-mail address and/or password entered do not match")>=0){
            return true;
        }
        else {
            return false;
        }
    }

    public void clickCheckoutAsGuest() {
        checkoutasguest.click();
        waitForAjax("wait for shipping page to load");
    }

    public void selectShippingMethod(Object zipcode1, String shippingMethod) {
        zipCode.sendKeys(zipcode1.toString());
        waitForAjax("Waiting for fedex request to complete");
        shippingMethodDrpDwn.sendKeys(shippingMethod);
        waitForAjax("Waiting for complete the choice of shipping method");
    }
}




