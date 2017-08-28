package ui.pages.repo.commonplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/29/16.
 */
public class YourAccountPageRepo extends UiBase {

    @FindBy(css="#saksBody")
    public WebElement yourAccountPage;

    @FindAll({
        @FindBy(name="LOGIN<>userid"), //saks
        @FindBy(name="username")}) //off5th
    public WebElement username;

    @FindAll({
        @FindBy(name="LOGIN<>password"), //saks
        @FindBy(name="password")}) //off5th
    public WebElement password;

    @FindAll({
        @FindBy(name="signIn"), //saks
        @FindBy(css="#account-content-area > div > div.account__body-section > div.sign-in > div > div.sign-in__account > section > form > div.sign-into-account__button > button > span > span")}) //off5th
    public WebElement signIn;

    public WebElement shippingAddressBook(){
            return getDriver().findElement(By.partialLinkText("SHIPPING ADDRESS BOOK"));
    };

    public WebElement orderHistoryBook(){
        return getDriver().findElement(By.partialLinkText("ORDER HISTORY"));
    };

    public WebElement billingAddressBook(){
        return getDriver().findElement(By.partialLinkText("BILLING ADDRESS BOOK"));
    };
    public WebElement paymentAddressBook(){
        return getDriver().findElement(By.partialLinkText("PAYMENT METHOD"));
    };
    public WebElement cancel(){
        return getDriver().findElement(By.partialLinkText("Cancel"));
    };
    public WebElement orderList(){
        return getDriver().findElement(By.cssSelector("form[name='viewSelectedOrder']"));
    };

}
