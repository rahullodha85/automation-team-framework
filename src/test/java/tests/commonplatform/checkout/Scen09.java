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
public class Scen09 extends BaseTest {


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
    public void placeOrderWith2ItemsAndAddBillingAddress() throws Exception {
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

        // need gift cards
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);

        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        String bldNo=new Random().nextInt(1000000)+"";
        reviewSubmitPage.editAndEnterNewBillingAddress( bldNo+"Vesey St","New York","New York","10281","United States");

        AssertFailAndContinue(reviewSubmitPage.hasBillingAddressUpdated(bldNo),"Billing address is Added");
        //reviewSubmitPage.

    }
}
/*

		rsp.ClickPlaceOrder();
		yap.Login(dataObject.getEmailID(), dataObject.getLoginPassword());
		yap.ClickBillingAddressBook();
		yap.removeFromBillingAddressBook(dataObject.getAddress1()+Common.getUniqueNumber());
 */

