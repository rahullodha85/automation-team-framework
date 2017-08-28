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
public class Scen01Service extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        driver.get(Config.getApplicationUrl()+"Entry.jsp");
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        logger.info("Url launched:" + Config.getApplicationUrl());

    }

    @Test
    public void placeOrderHappyPathWithPromoService() throws Exception {
        setAuthorInfo("Vevin Moza");

        homeService.searchFor(TestData.PRODUCT1);
        AssertFailAndContinue(productDetailService.hasAddToBagButton(),"Search for keyword in Search Box");
        productDetailService.addToBag(TestData.PRODUCT3,TestData.SKU3,3);

        AssertFailAndContinue(productDetailService.isItemInBag(TestData.PRODUCT1),"Item has been added to Bag");
        saksBagService.goToSaksBag();

        AssertFailAndContinue(saksBagService.hasLandedOnSaksBag(),"Landed on Saks Bag Page");



        saksBagService.applyPromo(TestData.PROMO1);
        AssertFailAndContinue(saksBagService.hasPromoRemoveLink(),"Promo Code Applied");
        saksBagService.loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        paymentService.continueCheckout();
        // reviewSubmitPage.clickPlaceOrder();
        //AssertFailAndContinue(thankYouPage.hasPrintReceipt(),"Order has been successfully placed");

    }
}

/*
       //test till here
        AssertFailAndContinue(productDetailService.isItemInBag(TestData.PRODUCT1),"Item has been added to Bag");
        saksBagService.goToSaksBag();
        AssertFailAndContinue(saksBagService.hasLandedOnSaksBag(),"Landed on Saks Bag Page");

        saksBagService.doGuestCheckout();
        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingService.addShippingBillingAddress(TestData.COUNTRY2,TestData.ADDRESSCOUNTRYCODE2,TestData.ADDRESS2,TestData.CITY2,TestData.STATE2,TestData.ZIPCODE2);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentService.addPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);


        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");

        reviewSubmitService.placeOrder();
 */