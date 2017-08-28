package ui.pages.repo.commonplatformmobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class PaymentmPageRepo extends UiBase {
    @FindBy(css="#payCC")
    public WebElement paymentType;

    @FindBy(css="#payCCNum")
    public WebElement paymentCardNumber;

    @FindBy(css="#payCCName")
    public WebElement paymentName;

    @FindBy(css="#ccMonth")
    public WebElement monthExpire;

    @FindBy(css="#ccYear")
    public WebElement yearExpire;

    @FindBy(css="#payCCV")
    public WebElement paymentCvv;

    @FindBy(css="#jsCompletePayment")
    public WebElement continueReviewSubmit;

    @FindBy(css="#jsAccountEnter")
    public WebElement continueReviewSubmitAccount;


    @FindBy(css="#tab-payment")
    public WebElement paymentTab;


    @FindBy(css="#password")
    public WebElement password;

    @FindBy(css="#password2")
    public WebElement re_enterpassword;
}
