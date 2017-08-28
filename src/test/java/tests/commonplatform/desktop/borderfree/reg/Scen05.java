package tests.commonplatform.desktop.borderfree.reg;

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
public class Scen05 extends BaseTest {
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
    public void placeOrderRegDefaultShippingBillingShippingDifferentCountry() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY1,TestData.COUNTRY1);

        AssertFailAndContinue(homePage.hasCountryChanged(TestData.COUNTRY1),"Country has been changed to "+TestData.COUNTRY1);
        productDetailsPage.addToBag(TestData.PRODUCT1,TestData.SKU1,1);
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage.clickCheckout().loginWith(TestData.LOGIN5,TestData.PASSWORD5);

        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS2,TestData.CITY2,TestData.STATE2,TestData.ZIPCODE2);
        AssertFailAndContinue(paymentPage.hasCvvField(),"Landed on Payment Page");
        paymentPage.clickContinueCheckout();
        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");

        reviewSubmitPage.clickPlaceOrder();
        AssertFailAndContinue(thankYouPage.isOrderPlaced(),"Landed on Thank You Page");
        yourAccountPage.changeDefaultShippingAddress();
        yourAccountPage.changeDefaultBillingAddress();

    }
}
