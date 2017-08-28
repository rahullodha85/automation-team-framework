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
public class Scen37 extends BaseTest {
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

    public void placeOrderUnregShipToMultipleAddresses() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT2+"&itemQuantity=1&sku_id="+TestData.SKU2+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT2),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.clickCheckout().clickCheckoutAsGuest();
        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS1,TestData.CITY1,TestData.STATE1,TestData.ZIPCODE1);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        String bldNo=new Random().nextInt(100000)+"";
        reviewSubmitPage.editAndEnterNewShippingAddress(TestData.PRODUCT1,bldNo+TestData.ADDRESS1,TestData.CITY1,TestData.STATE1,TestData.ZIPCODE1);
        AssertFailAndContinue(shippingBillingPage.hasMultiShippingAddressUpdate(bldNo),"Landed on Place Order Page");
        shippingBillingPage.clickMultiShippingContinueCheckout();
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");

    }
}
/*






		rsp.ClickPlaceOrder();
		conf.CreateAccount();
		conf.GoToYourAccount();
		ValidateYourAcc.ShippingAddressContains(Storage.get("shippingAddress"));
		ValidateYourAcc.BillingAddressContains(Storage.get("shippingAddress"));
		ValidateYourAcc.CreditCardContains(Storage.get("ccNumber"));
 */