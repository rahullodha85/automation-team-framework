package ui.pages.actions.commonplatformmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import ui.pages.repo.commonplatformmobile.PaymentmPageRepo;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class PaymentmPage extends PaymentmPageRepo {
    WebDriver driver;
    public PaymentmPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasCreditCardNameField() {
        if(paymentName.isDisplayed()){
            return true;
        }else return false;
    }

    public void addNewPaymentMethod(Object creditcardtype1, Object creditcardnumber1) {
        paymentType.sendKeys(creditcardtype1.toString());
        paymentCardNumber.sendKeys(creditcardnumber1.toString());
        paymentName.sendKeys("Hello World");

        new Select(monthExpire).selectByIndex(12);
        yearExpire.sendKeys("2027");
        paymentCvv.sendKeys("121");
        continueReviewSubmit.click();
        waitForAjax("Wait for loading of review submit page for unreg user");


    }

    public void enterPassword(Object password1, Object password2) {

        password.clear();
        password.sendKeys(password1.toString());
        re_enterpassword.clear();
        re_enterpassword.sendKeys(password2.toString());
        clickJs(continueReviewSubmitAccount);
        waitForAjax("Wait for loading of review submit page for unreg user");
    }

    public void clickContinueCheckout() {
        if(paymentCvv.isDisplayed()){
        paymentCvv.sendKeys("121");}
        clickJs(continueReviewSubmit);
        waitForAjax("waiting for review submit page to load");
    }

    public boolean hasCvvField() {
        return false;
    }

    public boolean hasPasswordField() {
        if(password.isDisplayed()){
            return true;
        }else return false;
    }
}
