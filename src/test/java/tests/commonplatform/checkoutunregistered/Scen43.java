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
public class Scen43 extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen35.class);


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
    public void placeOrderUnregUsePersonalizedItemAndDropShipItem() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.searchFor(TestData.PRODUCT7);
        AssertFailAndContinue(productDetailsPage.personalizedPageLoaded(),"Search for keyword in Search Box");
        productDetailsPage.addToBagPersonalized();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT5+"&itemQuantity=1&sku_id="+TestData.SKU5+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT5),"Item has been added");

        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage.clickCheckout().clickCheckoutAsGuest();
        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS1,TestData.CITY1,TestData.STATE1,TestData.ZIPCODE1);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);

        AssertFailAndContinue(reviewSubmitPage.hasDropShipItem(TestData.PRODUCT5),"Has drop ship item in the cart");
        AssertFailAndContinue(reviewSubmitPage.hasPersonalizedItem(TestData.PRODUCT7),"Has personalized item in th cart");


    }
}
/*


		rsp.ClickPlaceOrder();
		conf.EnterPassword("test123", "test13");
		ValidateConfirmation.hasPasswordDoesnotMatchError();
		conf.EnterPassword("test", "test");
		ValidateConfirmation.hasInvalidPasswordError();
		conf.EnterPassword("test123", "test123");
		conf.GoToYourAccount();
		ValidateYourAcc.ShippingAddressContains(Storage.get("shippingAddress"));
		ValidateYourAcc.BillingAddressContains(Storage.get("shippingAddress"));
		ValidateYourAcc.CreditCardContains(Storage.get("ccNumber"));
 */