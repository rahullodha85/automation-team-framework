package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.PaymentPageRepo;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class PaymentPage extends PaymentPageRepo {
    WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void addNewPaymentMethod(Object creditcardtype1, Object creditcardnumber1) {
        paymentType.sendKeys(creditcardtype1.toString());
        paymentCardNumber.sendKeys(creditcardnumber1.toString());
        paymentName.sendKeys("Hello World");
        monthExpire.sendKeys("12");
        yearExpire.sendKeys("2027");
        paymentCvv.sendKeys("121");
        continueReviewSubmit.click();
        waitForAjax("Wait for loading of review submit page for unreg user");

    }

    public boolean hasCreditCardNameField() {
        if(paymentName.isDisplayed()){
            return true;
        }else return false;
    }

    public boolean hasCvvField() {
        if(paymentCvv.isDisplayed()){
            return true;
        }else return false;
    }

    public void enterPassword(Object password1, Object password2) {
            clickJs(paymentTab);
            password.clear();
            password.sendKeys(password1.toString());
            re_enterpassword.clear();
            re_enterpassword.sendKeys(password2.toString());
            clickJs(continueReviewSubmit);
        waitForAjax("Wait for loading of review submit page for unreg user");
    }

    public boolean hasPasswordMismatchError() {
        if(  getDriver().getPageSource().indexOf("Password does not match.")>=0){
        return true;
        }
        else return false;
    }

    public boolean hasInvalidPassword() {
        if(getDriver().getPageSource().indexOf("Password must be at least six characters and include one number and one letter.")>=0){
            return true;
        }
        else return false;
    }

    public void clickContinueCheckout() {

        if(saksBody.getText().toLowerCase().indexOf("security code")>=0 && saksBody.getText().toLowerCase().indexOf("continue checkout")>=0 ) {
            waitUntilElementDisplayed(paymentCvv, 4);
            paymentCvv.sendKeys("121");
        }

        clickJs(continueReviewSubmit);
        waitForAjax("waiting for review submit page to load");
    }

    public boolean hasPasswordField() {
        if(password.isDisplayed()){
            return true;
        }else return false;
    }
}
