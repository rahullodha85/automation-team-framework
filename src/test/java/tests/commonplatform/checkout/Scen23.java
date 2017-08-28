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
public class Scen23 extends BaseTest {


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
    public void placeOrderWith2ItemsAndNewShippingBillingAddress() throws Exception {
        setAuthorInfo("Vevin Moza");

        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=3&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT2+"&itemQuantity=3&sku_id="+TestData.SKU2+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT2),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.clickCheckout().loginWith(TestData.LOGIN1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitPage.hasGiftCardField(),"Landed on Place Order Page");
       // reviewSubmitPage.emptyCartService();
        reviewSubmitPage.clickShippingBillingTab();
        String bldNo=new Random().nextInt(1000000)+"";

        shippingBillingPage.editAndEnterNewShippingAddress( bldNo+"Vesey St","New York","New York","10281");
        shippingBillingPage.editAndEnterNewBillingAddress( bldNo+"Vesey St","New York","New York","10281","United States");
        reviewSubmitPage.clickShippingBillingTab();
        AssertFailAndContinue(shippingBillingPage.hasShippingAddressUpdated(bldNo),"Shipping address is Added");
        AssertFailAndContinue(shippingBillingPage.hasBillingAddressUpdated(bldNo),"Shipping address is Added");
        reviewSubmitPage.clickReviewSubmitTab();
        reviewSubmitPage.emptyCartService(0);

       // AssertFailAndContinue(shippingBillingPage.hasBillingAddressUpdated(bldNo),"Shipping address is Added");


    }
}
/*
String item1 = itemData.get(ItemType.REGULAR);

		SearchFunction.SearchFor(item1);
		pdp.AddToBag(1);
		pdp.EnterBag();
		bag.ClickCheckout().LoginWith(dataObject.getEmailID(), dataObject.getLoginPassword());
		rsp.ClickShippingAndBillingTab();
		shp.ClickEditShippingAddress();
		shp.EditShippingAddress("", "", dataObject.getAddress1(), "", dataObject.getCity(), "", dataObject.getState(), dataObject.getZipCode(), "", "", "", "");
		shp.ClickEditBillingAddress();
		shp.AddBillingAddress(dataObject.getAddress1(), dataObject.getState(), dataObject.getCity(), dataObject.getZipCode(),true);
		pap.ClickCheckout();

		pap.ClickCheckout();
		ValidateRs.ShippingAddressContains(dataObject.getAddress1());
		ValidateRs.BillingAddressContains(dataObject.getAddress1());
		rsp.ClickPlaceOrder();

		ValidateConfirmation.BillingAddressContains(dataObject.getAddress1());
		ValidateConfirmation.ShippingAddressContains(dataObject.getAddress1());
 */