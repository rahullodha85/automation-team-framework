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
        driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        logger.info("Url launched:" + Config.getApplicationUrl());

    }



    @Test
    public void placeOrderWith2ItemsAndMakeThemGift() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.searchFor(TestData.PRODUCT1);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        homePage.searchFor(TestData.PRODUCT2);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag("2");
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        productDetailsPage.enterBag();
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.makeGift(TestData.PRODUCT1);
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT1),"Gift has been applied");
        saksBagPage.makeGift(TestData.PRODUCT2);
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT2),"Gift has been applied");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT1),"Gift has been applied");
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT2),"Gift has been applied");
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");


       }



}


