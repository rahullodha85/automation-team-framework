package ui.pages.repo.commonplatformmobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class SaksBagmPageRepo extends UiBase {

    @FindBy(css="#promoCodeEntry")
    public WebElement promoBox;

    @FindBy(css="#promoApply")
    public WebElement promoApplyBtn;


    @FindBy(css="#jsSubmitGifting")
    public WebElement submitGift;

    @FindBy(css="#jsStartCheckout")
    public WebElement checkoutbagBtn;



    @FindBy(css="#jsEnterQuickCheckout")
    public WebElement enterCheckoutbagBtn;

    @FindBy(css="#jsEnterQuickCheckout")
    public WebElement checkoutbag_reBtn;


    @FindBy(css="#loginEmail")
    public WebElement loginBox;

    @FindBy(css="#loginPass")
    public WebElement passwordBox;

    @FindBy(css="#container")
    public WebElement saksBody;


    @FindBy(css=".action-link.jsRemovePromo")
    public WebElement promoRemoveLnk;



    @FindBy(css="#jsCompletePayment")
    public WebElement continueCheckoutPayment;


    @FindBy(css="#paySelectCC")
    public WebElement paymentDrpDwn;



    @FindBy(css="#submit_payment_service")
    public WebElement paymentBoxLbl;



    @FindBy(css="#payCCV")
    public WebElement paymentCvv;

    @FindBy(css="#jsQuickCheckout")
    public WebElement signInCheckout;

    @FindBy(css="#jsBagZipCode")
    public WebElement zipCode;

    @FindBy(css="#jsSelectShippingMethod")
    public WebElement shippingMethodDrpDwn;

    @FindBy(css="#email")
    public WebElement emailForgotPassword;

    @FindBy(tagName="html")
    public WebElement htmlPage;
    //

    @FindBy(css="#jsCheckoutEnhancementsGuestCheckout")
    public WebElement checkoutasguest;

    public WebElement removeItemlink(String upc){
        return  getItem(upc).findElement(By.className("jsRemoveItem"));
    }

    public WebElement getItem(String upc){
        return getDriver().findElement(By.cssSelector("div[data-cartproductcode='"+upc +"']"));
    }

    public WebElement getforgotPasswordlink(){
        return getDriver().findElement(By.cssSelector("a[href='/account/forgotpassword.jsp']"));
    }

    public WebElement getContinueButtonforgotPassword(){
        return getDriver().findElement(By.name("email_reset_password")).findElement(By.tagName("button"));
    }
}
