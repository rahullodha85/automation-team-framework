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
public class Scen27 extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


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

    public void placeOrderAssociateDiscount() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");

        saksBagPage.clickCheckout().loginWith(TestData.LOGIN2,TestData.PASSWORD2);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");

    }



}


/*
		ValidateRs.isAssociateDiscountApplied(false);
		Storage.save("shippingCharge", rsp.GetShippingCharge());
		rsp.ClickPlaceOrder();
		ValidateConfirmation.isShippingChargeApplied();

 */