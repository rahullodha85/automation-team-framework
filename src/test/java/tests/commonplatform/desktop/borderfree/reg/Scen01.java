package tests.commonplatform.desktop.borderfree.reg;

import initializer.BaseTest;
import initializer.Retry;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pages.actions.ProductArrayActions;
import ui.support.Config;
import ui.support.EnvironmentConfig;

import java.util.Random;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class Scen01 extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        // driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        driver.manage().window().setPosition(new Point(900,0));
        logger.info("Url launched:" + Config.getApplicationUrl());

    }

    @Test
    public void placeOrderRegGuestUserCreateAccountAfterInternationalShippingOrder() throws Exception {
        setAuthorInfo ("Vevin Moza");

        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY2,TestData.COUNTRY2);
        AssertFailAndContinue(homePage.hasCountryChanged(TestData.COUNTRY2),"Country has been changed to "+TestData.COUNTRY2);
        productDetailsPage.addToBag(TestData.PRODUCT1,TestData.SKU1,1);

        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage.clickCheckout().clickCheckoutAsGuest();

        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        String createdEmail=shippingBillingPage.addNewShippingAddress(TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");
        paymentPage.enterPassword(TestData.PASSWORD1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");
        String order=reviewSubmitPage.clickPlaceOrder();

        AssertFailAndContinue(thankYouPage.isOrderPlaced(),"Landed on Thank You Page");

        homePage.openSignInSection();
        AssertFailAndContinue(yourAccountPage.hasSignInSection(),"Landed on Sign in on Your Account Page");
        homePage.orderSearch(order,TestData.ZIPCODE3);
        AssertFailAndContinue(yourAccountPage.hasOrderDetails(),"Opened order details ");
        homePage.openAccount(createdEmail,TestData.PASSWORD1);
        AssertFailAndContinue(yourAccountPage.hasLoggedIn(),"Landed on Logged in Your Account Page");
        yourAccountPage.openShippingAddressBook();
        AssertFailAndContinue(yourAccountPage.hasDefaultShippingAddress(TestData.ADDRESS3),"Shipping Address Present and Default");
        yourAccountPage.openBillingAddressBook();
        AssertFailAndContinue(yourAccountPage.hasDefaultBillingAddress(TestData.ADDRESS3),"Billing Address Present and Default");
        yourAccountPage.openPaymentMethod();
        AssertFailAndContinue(yourAccountPage.hasPaymentMethod(TestData.CREDITCARDTYPE1),"Payment Method Present and Default");
        yourAccountPage.openOrderHistory();
        AssertFailAndContinue(yourAccountPage.hasOrderHistoryPresent(order),"Order History is Present");


    }

}
