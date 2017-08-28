package tests.commonplatform.checkout;

import initializer.BaseTest;
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
public class Scen14 extends BaseTest {


    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        logger.info("Url launched:" + Config.getApplicationUrl());

    }

    @Test
    public void placeOrderWith1DropShipItem() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.searchFor(TestData.PRODUCT5);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");


        productDetailsPage.enterBag();
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        AssertFailAndContinue(saksBagPage.hasDropShipItem(TestData.PRODUCT5),"Item in cart is a drop ship item");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);

        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        AssertFailAndContinue(reviewSubmitPage.hasDropShipItem(TestData.PRODUCT5),"Item in cart is a drop ship item");
             //reviewSubmitPage.

    }
}
/*
/*
		rsp.ClickPlaceOrder();
		ValidateConfirmation.isThankYouPage();*/
