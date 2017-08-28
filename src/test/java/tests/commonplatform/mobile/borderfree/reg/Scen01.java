package tests.commonplatform.mobile.borderfree.reg;

import initializer.BaseTest;
import initializer.Retry;
import org.apache.log4j.Logger;
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
        logger.info("Url launched:" + Config.getApplicationUrl());

    }

    @Test

    public void placeOrdermRegGuestUserCreateAccountAfterInternationalShippingOrder() throws Exception {
        setAuthorInfo("Vevin Moza");
        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY1,TestData.COUNTRY1);

        AssertFailAndContinue(homemPage.hasCountryChanged(TestData.COUNTRY1),"Country has been changed to "+TestData.COUNTRY1);
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsmPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagmPage.hasLandedOnSaksBag(),"Landed on Saks Bag Page");
        saksBagmPage.clickCheckout().clickCheckoutAsGuest();
        AssertFailAndContinue(shippingBillingmPage.hasFirstNameField(),"Landed on Shipping Page");
        String createdEmail=shippingBillingmPage.addNewShippingAddress(TestData.ADDRESS2,TestData.CITY2,TestData.STATE2,TestData.ZIPCODE2);
        AssertFailAndContinue(paymentmPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentmPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        paymentmPage.enterPassword(TestData.PASSWORD1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitmPage.haseditPayment(),"Landed on Place Order Page");
        String order=reviewSubmitmPage.clickPlaceOrder();
        AssertFailAndContinue(thankYoumPage.isOrderPlaced(),"Landed on Thank You Page");
     /*   homePage.openSignInSection();
        AssertFailAndContinue(yourAccountmPage.hasSignInSection(),"Landed on Sign in on Your Account Page");
        homePage.orderSearch(order,TestData.ZIPCODE2);
        AssertFailAndContinue(yourAccountmPage.hasOrderDetails(),"Opened order details ");
        homePage.openAccount(createdEmail,TestData.PASSWORD1);
*/      /*  AssertFailAndContinue(yourAccountmPage.hasLoggedIn(),"Landed on Logged in Your Account Page");
        yourAccountPage.openShippingAddressBook();
        AssertFailAndContinue(yourAccountmPage.hasDefaultShippingAddress(TestData.ADDRESS2),"Shipping Address Present and Default");
        yourAccountPage.openBillingAddressBook();
        AssertFailAndContinue(yourAccountmPage.hasDefaultBillingAddress(TestData.ADDRESS2),"Billing Address Present and Default");
        yourAccountPage.openPaymentMethod();
        AssertFailAndContinue(yourAccountmPage.hasPaymentMethod(TestData.CREDITCARDTYPE1),"Payment Method Present and Default");
        yourAccountPage.openOrderHistory();
        AssertFailAndContinue(yourAccountmPage.hasOrderHistoryPresent(order),"Order History is Present");*/


    }

}
