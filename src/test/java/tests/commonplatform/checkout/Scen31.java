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
public class Scen31 extends BaseTest {


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
    public void placeOrderWithPreOrderAndRegularItemsAndMakeThemGift() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=3&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT5+"&itemQuantity=3&sku_id="+TestData.SKU5+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT5),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.makeGift(TestData.PRODUCT1);
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT1),"Gift has been applied");
        saksBagPage.makeGift(TestData.PRODUCT5);
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT5),"Gift has been applied");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        //reviewSubmitPage.emptyCartService();
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT1),"Gift has been applied");
        AssertFailAndContinue(saksBagPage.hasGiftApplied(TestData.PRODUCT5),"Gift has been applied");
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        reviewSubmitPage.emptyCartService(0);


    }



}



/*
	String item1 = itemData.get(ItemType.REGULAR);
		String item2 = itemData.get(ItemType.REGULAR);

		SearchFunction.SearchFor(item1);
		pdp.AddToBag(1);
		SearchFunction.SearchFor(item2);
		pdp.AddToBag(1);
		pdp.EnterBag();

		bag.ClickCheckout().LoginWith(dataObject.getEmailID(), dataObject.getLoginPassword());

		rsp.MakeGift(item1).With("Automation", "test123");
		rsp.ClickPlaceOrder();
		ValidateConfirmation.isGiftWrapApplied(item2,"");
 */