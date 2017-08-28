package ui.pages.actions.commonplatform;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.PaymentPageRepo;
import ui.pages.repo.commonplatform.YourAccountPageRepo;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class YourAccountPage extends YourAccountPageRepo {
    WebDriver driver;

    public YourAccountPage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
    public void openShippingAddressBook() throws InterruptedException {
        shippingAddressBook().click();
        waitForPageReadyState("wait for shipping address book to open");
        waitForAjax("Wait For Shipping Address Book to open");
    }
    public void openOrderHistory(){
        orderHistoryBook().click();

        waitForAjax("Wait For Order History Book to open");
    }
    public void openBillingAddressBook() throws InterruptedException {
        billingAddressBook().click();
        waitForPageReadyState("wait for shipping address book to open");
        waitForAjax("Wait For Billing Address Book to open");
    }
    public void openPaymentMethod() throws InterruptedException {
         paymentAddressBook().click();
        waitForPageReadyState("wait for page to load payment method");
        waitForAjax("Wait For Payment Book to open");
    }
    public void login(String user, String pw){
        username.sendKeys(user);
        password.sendKeys(pw);
        signIn.submit();
        waitForAjax();
        if(signIn.isDisplayed())
            signIn.click();
    }
    public boolean hasDefaultShippingAddress(Object address){
        boolean state=getDriver().getPageSource().indexOf(address.toString())>=0 && getDriver().getPageSource().indexOf("Set as Default Address")<=0;
        cancel().click();
        if(state){
        return true;
        }
        else return false;
    }


    public boolean hasDefaultBillingAddress(Object address) throws InterruptedException {
        waitForPageReadyState("wait for page ready state");
        waitForAjax("wait for page billing account");
        boolean state=getDriver().getPageSource().indexOf(address.toString())>=0 && getDriver().getPageSource().indexOf("Set as Default Address")<=0;
        cancel().click();
        if(state){
            return true;
        }
        else return false;
    }


    public boolean hasPaymentMethod(Object payment) throws InterruptedException {
        waitForPageReadyState("wait for page ready state");
        waitForAjax("wait for page payment method");
        Thread.sleep(1000);

        boolean state=getDriver().getPageSource().toLowerCase().indexOf(payment.toString().toLowerCase())>=0 && getDriver().getPageSource().toLowerCase().indexOf("Set as Default Payment Type".toLowerCase())<=0;
        cancel().click();
        if(state){
            return true;
        }
        else return false;
    }


    public boolean hasLoggedIn() throws InterruptedException {
        Thread.sleep(3000);
        if(yourAccountPage.getText().indexOf("ACCOUNT INFORMATION FOR")>=0)
        return true;
        else return false;
    }

    public boolean hasOrderHistoryPresent(Object order) {
        if(orderList().getText().indexOf(order.toString())>=0){
            return true;
        }
        return false;
    }

    public boolean hasSignInSection() throws InterruptedException {
        Thread.sleep(3000);
        if(getDriver().getPageSource().indexOf("You have previously created an account at")>=0){
            return true;
        }
        return false;
    }

    public boolean hasOrderDetails() throws InterruptedException {
        Thread.sleep(3000);
        if(getDriver().getPageSource().indexOf("left to cancel this order")>=0){
            return true;
        }
        return false;
    }

    public void changeDefaultShippingAddress() throws Exception {
        makeRequest("account/mng_shipAddress.jsp?bmForm=mngSetDefaultShip&defShipId=56297194365390012");
        waitForPageReadyState("Waiting for page to complete loading for shipping account page");

    }

    public void changeDefaultBillingAddress() throws Exception {
        makeRequest("account/mng_billAddress.jsp?bmForm=mngSetDefaultBill&defBillId=56297194365390117");
        waitForPageReadyState("Waiting for page to complete loading for billing account page");
    }
}
