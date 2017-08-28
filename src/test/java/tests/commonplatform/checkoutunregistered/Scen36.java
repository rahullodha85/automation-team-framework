package tests.commonplatform.checkoutunregistered;

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
public class Scen36 extends BaseTest {
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

    public void placeOrderUnregHappyPathWithPromoAndGiftCard() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=3&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.applyPromo(TestData.PROMO1);
        AssertFailAndContinue(saksBagPage.hasPromoRemoveLink(),"Promo Code Applied");
        saksBagPage.clickCheckout().clickCheckoutAsGuest();
        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS1,TestData.CITY1,TestData.STATE1,TestData.ZIPCODE1);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");
        String order=reviewSubmitPage.clickPlaceOrder();
        AssertFailAndContinue(thankYouPage.isOrderPlaced(),"Landed on Thank You Page");

    }
}
/*

		rsp.ApplyGiftCard("6022990000115991", "5854");

		/*Waseem: Not placing the Order as the Gift cards are getting used up every transaction.
		Instead validating Gift card is applied. Spoke with Operations(Venky) team on 11/09
ValidateRs.hasGiftCardAppliedText();
//		rsp.ClickPlaceOrder();
//		yap.CancelOrder(ValidateConfirmation.getOrderNumber(), dataObject.getZipCode());
 */