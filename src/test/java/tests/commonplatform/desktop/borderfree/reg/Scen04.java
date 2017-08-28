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
public class Scen04 extends BaseTest {
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
    public void placeOrderRegEditPaymentMethod() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY2,TestData.COUNTRY2);

        AssertFailAndContinue(homePage.hasCountryChanged(TestData.COUNTRY2),"Country has been changed to "+TestData.COUNTRY2);
        productDetailsPage.addToBag(TestData.PRODUCT1,TestData.SKU1,1);
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage.clickCheckout().loginWith(TestData.LOGIN3,TestData.PASSWORD3);

        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");
        reviewSubmitmPage.updateCartService(TestData.PRODUCT1,1);
        reviewSubmitPage.emptyCartService(1);



        reviewSubmitPage.editAndAddPaymentMethod(TestData.CREDITCARDTYPE2,TestData.CREDITCARDNUMBER2);

        reviewSubmitPage.clickPlaceOrder();
        AssertFailAndContinue(thankYouPage.isOrderPlaced(),"Landed on Thank You Page");


    }
}
