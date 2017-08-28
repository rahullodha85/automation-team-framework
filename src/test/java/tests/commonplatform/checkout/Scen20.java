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
public class Scen20 extends BaseTest {


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
    public void placeOrderWithPersonalizedAndDropShipItem() throws Exception {
        setAuthorInfo("Vevin Moza");
        homePage.searchFor(TestData.PRODUCT7);
        AssertFailAndContinue(productDetailsPage.personalizedPageLoaded(),"Search for keyword in Search Box");
        productDetailsPage.addToBagPersonalized();
        AssertFailAndContinue(productDetailsPage.hasOverlayAppeared(),"Item added to bag");
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT5+"&itemQuantity=1&sku_id="+TestData.SKU5+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT5),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
        AssertFailAndContinue(reviewSubmitPage.hasDropShipItem(TestData.PRODUCT5),"Landed on Place Order Page");
        AssertFailAndContinue(reviewSubmitPage.hasPersonalizedItem(TestData.PRODUCT7),"Landed on Place Order Page");

    }
}




	/*String item1 = itemData.get(ItemType.DROPSHIP);
		String item2 = itemData.get(ItemType.PERSONALIZED);

		SearchFunction.SearchFor(item1);
		pdp.AddToBag(1);
		SearchFunction.SearchFor(item2);
		pdp.PersonalizeAddToBag(1,"Personalized text");
		pdp.EnterBag();
		bag.ClickCheckout().LoginWith(dataObject.getEmailID(), dataObject.getLoginPassword());
//		ValidateRs.isDropship(dataObject.getSkuListInfo().get(0));
//		ValidateRs.isPersonalized(dataObject.getSkuListInfo().get(1), "Personalized text"); dynamic data check
		rsp.ClickPlaceOrder();*/