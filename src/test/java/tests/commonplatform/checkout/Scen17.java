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
public class Scen17 extends BaseTest {


    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        //driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        //logger.info("Url launched:" + Config.getApplicationUrl());

    }



    @Test
    public void placeOrderWithSignatureAtDelievery() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        //to be completed not working right now
        reviewSubmitPage.toggleSignature();
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        AssertFailAndContinue(reviewSubmitPage.hasEGCinCart(TestData.PRODUCT6),"EGC is in cart");
        AssertFailAndContinue(reviewSubmitPage.hasDropShipItem(TestData.PRODUCT5),"Drop ship item is in cart");
        //reviewSubmitPage.

    }
}


/*	String item1 = itemData.get(ItemType.DROPSHIP);

		SearchFunction.SearchFor(item1);
		pdp.AddToBag(1);
		SearchFunction.SearchFor(dataObject.getSkuListInfo().get(1));
		pdp.AddToBag(1);
		pdp.EnterBag();
		bag.ClickCheckout().LoginWith(dataObject.getEmailID(), dataObject.getLoginPassword());
		rsp.ToggleSignature(item1);
		ValidateRs.hasSignatureOnDelivery(item1);
		rsp.ClickPlaceOrder();
		ValidateConfirmation.isThankYouPage();*/



