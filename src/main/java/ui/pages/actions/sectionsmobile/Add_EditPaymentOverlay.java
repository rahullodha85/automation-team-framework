package ui.pages.actions.sectionsmobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.sectionsmobile.Add_EditPaymentRepo;
import ui.utils.RandomNumberGenerator;

/**
 * Created by vevinmoza on 3/25/16.
 */
public class Add_EditPaymentOverlay extends Add_EditPaymentRepo {

    WebDriver driver;

    public Add_EditPaymentOverlay(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public void editCreditCardName(String s) {
        paymentUserName.clear();
        paymentUserName.sendKeys(s);
        //bug click is not needed
        paymentApply.click();
        paymentCvv.sendKeys("121");

        paymentApply.click();
        waitForAjax("Applying Credit Card Name");

    }

    public void addNewPaymentMethod(String creditcardtype1, String creditcardnumber1) {
        clickJs(enterNewCreditCardLnk());
        clickJs(addCreditCard);
        paymentType.sendKeys(creditcardtype1.toString());
        paymentCardNumber.sendKeys(creditcardnumber1.toString());
        paymentName.sendKeys("Hello World"+ RandomNumberGenerator.generateNumber());
        monthExpire.sendKeys("12");
        yearExpire.sendKeys("2027");
        paymentCvv.sendKeys("121");
        paymentApply.click();
        waitForAjax("Wait for loading of review submit page for unreg user");
    }
}
