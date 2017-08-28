package tests.commonplatform.checkoutunregistered;

import initializer.BaseTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.support.Config;

import java.util.Random;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class Scen40 extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen35.class);


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
    public void placeOrderUnregShippingMethodChooseOnBagApplyGiftCardPassWordMismatch() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
           driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.selectShippingMethod(TestData.ZIPCODE1,"Rush");
        saksBagPage.clickCheckout().clickCheckoutAsGuest();
        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS1,TestData.CITY1,TestData.STATE1,TestData.ZIPCODE1);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        paymentPage.enterPassword(TestData.PASSWORD1,TestData.PASSWORD2);
        AssertFailAndContinue(paymentPage.hasPasswordMismatchError(),"Check Password mismatching");
        paymentPage.enterPassword(TestData.PASSWORD1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");

    }
}
/*
		rsp.ApplyGiftCard("6022990000115991", "5854");
		ValidateRs.hasGiftCardAppliedText();
		/*Waseem: Not placing the Order as the Gift cards are getting used up every transaction.
		Instead validating Gift card is applied. Spoke with Operations(Venky) team on 11/09

//		rsp.ClickPlaceOrder();
//		ValidateConfirmation.isGiftCardApplied();
//		yap.CancelOrder(ValidateConfirmation.getOrderNumber(),dataObject.getZipCode());
 */