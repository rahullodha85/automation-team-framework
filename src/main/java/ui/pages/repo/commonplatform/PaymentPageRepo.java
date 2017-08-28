package ui.pages.repo.commonplatform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class PaymentPageRepo extends UiBase {

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

    @FindBy(css="#tab-payment")
    public WebElement paymentTab;


    @FindBy(css="#password")
    public WebElement password;

    @FindBy(css="#password2")
    public WebElement re_enterpassword;

    @FindBy(css="#saksBody")
    public WebElement saksBody;

}
