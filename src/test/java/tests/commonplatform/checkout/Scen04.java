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
public class Scen04 extends BaseTest {


    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen04.class);


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
    public void placeOrderWith4ItemsRemove2ItemsAndUpdate2Items() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.searchFor(TestData.PRODUCT1);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        homePage.searchFor(TestData.PRODUCT2);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        homePage.searchFor(TestData.PRODUCT3);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        homePage.searchFor(TestData.PRODUCT4);
        AssertFailAndContinue(productDetailsPage.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailsPage.addToBag();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");



        productDetailsPage.enterBag();
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage. removeItem(TestData.PRODUCT4);
        AssertFailAndContinue(saksBagPage.hasItemBeenRemoved(TestData.PRODUCT4),"Item has been removed");

        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);

        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        reviewSubmitPage.removeItem(TestData.PRODUCT1);
        AssertFailAndContinue(reviewSubmitPage.hasItemBeenRemoved(TestData.PRODUCT1),"Item has been removed");


        reviewSubmitPage.updateQuantity(TestData.PRODUCT2,"4");
        AssertFailAndContinue(reviewSubmitPage.hasQuantityUpdated(TestData.PRODUCT2,"4"),"Item Quantity is Updated");


        reviewSubmitPage.updateQuantity(TestData.PRODUCT3,"4");
        AssertFailAndContinue(reviewSubmitPage.hasQuantityUpdated(TestData.PRODUCT3,"4"),"Item Quantity is Updated");

        //item quanity updated validation
        //  reviewSubmitPage.clickPlaceOrder();
        // AssertFailAndContinue(thankYouPage.hasPrintReceipt(),"Order has been successfully placed");
        //assert thankYouPage.hasQuantityItem(TestData.PRODUCT2,"4");

        //assert thankYouPage.hasQuantityItem(TestData.PRODUCT2,"4"); item quantity updated
    }



}
