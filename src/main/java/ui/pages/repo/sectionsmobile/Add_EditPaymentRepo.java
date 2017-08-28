package ui.pages.repo.sectionsmobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/25/16.
 */
public class Add_EditPaymentRepo extends UiBase {

    public WebElement enterNewCreditCardLnk(){
        return getDriver().findElement(By.cssSelector("label[for='newCreditCard']"));
    }

    @FindBy(css="#credit-card-layer-add")
    public WebElement addCreditCard;



    @FindBy(css="#payCCName")
    public WebElement paymentUserName;

    @FindBy(css="#jsSaveCreditCard")
    public WebElement paymentApply;

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
}
