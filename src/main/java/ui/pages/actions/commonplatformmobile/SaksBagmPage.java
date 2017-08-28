package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.actions.commonplatform.SaksBagPage;
import ui.pages.repo.commonplatformmobile.SaksBagmPageRepo;
import ui.support.Config;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class SaksBagmPage extends SaksBagmPageRepo {
    WebDriver driver;
    public SaksBagmPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasPromoCodeField() {
        return false;
    }

    public boolean hasLandedOnSaksBag() {
        waitForAjax("wait for page to load saksbag");
        if(getpageSource().indexOf("You will be able to apply promotional codes and gift cards to your order after you proceed to Checkout.")>=0){
            return true;}
            else return false;
        }

    public SaksBagmPage clickCheckout() {

        Object loginRequired=executeJavaScript("return Order.isUserLoggedIn()");
        // Object internationalorder=executeJavaScript("return services.isInternational()");
        //if(executeJavaScript("return Order.isLoginRequired()"))
        if(loginRequired.toString().equalsIgnoreCase("true"))
        {clickJs(enterCheckoutbagBtn);}
        else{
            clickJs(checkoutbagBtn);}
        return new SaksBagmPage(driver);

    }
    public void clickCheckoutAsGuest() {
        checkoutasguest.click();

    }

    public void loginWith(Object login3, Object password3) throws Exception {
        login(login3.toString(),password3.toString());
        Thread.sleep(2000);
        boolean pageStateSaksBag=htmlPage.getText().toLowerCase().contains("previous sessions");
        System.out.println(pageStateSaksBag);
        if(pageStateSaksBag) {

            clickJs(checkoutbag_reBtn);
        }
        waitForAjax("waiting after clicking checkout on saks bag");
        Thread.sleep(4000);
        if(saksBody.getText().indexOf("There is no default shipping/billing address.")>=0){
            return;
        }


        waitForAjax("waiting in saksbag before checking for payment page");
        Thread.sleep(4000);
        System.out.println("Security Code Present: "+getpageSource().indexOf("Security Code"));
        if(saksBody.getText().indexOf("Security Code")>=0 ) {
           // waitUntilElementDisplayed(paymentCvv, 4);
            paymentCvv.sendKeys("121");
        }
        System.out.println("test payment page: "+getpageSource().toLowerCase().contains("next step"));
        if(getpageSource().toLowerCase().contains("next step")){
            continueCheckoutPayment.click();
        }
        makeRequest("checkout/checkout.jsp#processPayment");
        waitForAjax("waiting after clicking checkout on payment page");
    }
    private void login(String login,String password){
        loginBox.sendKeys(login.toString());
        passwordBox.sendKeys(password.toString());
        clickJs(signInCheckout);

        // waitUntilAnimationIsDone(".loading-overlay");
        waitForAjax("waiting after clicking checkout on login window");
    }
}

