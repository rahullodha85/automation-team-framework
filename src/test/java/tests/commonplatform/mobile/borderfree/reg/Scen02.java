package tests.commonplatform.mobile.borderfree.reg;

import initializer.BaseTest;
import initializer.Retry;
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
public class Scen02 extends BaseTest {
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
    public void placeOrdermRegNewShippingBillingAddress() throws Exception {
        setAuthorInfo("Vevin Moza");
        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY2,TestData.COUNTRY2);
        AssertFailAndContinue(homemPage.hasCountryChanged(TestData.COUNTRY2),"Country has been changed to "+TestData.COUNTRY2);
        driver.get(Config.getApplicationUrl()+"checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=1&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG");
        AssertFailAndContinue(productDetailsmPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagmPage.hasLandedOnSaksBag(),"Landed on Saks Bag Page");
        saksBagmPage.clickCheckout().loginWith(TestData.LOGIN3,TestData.PASSWORD3);
        AssertFailAndContinue(reviewSubmitmPage.hasPlaceOrder(),"Landed on Place Order Page");
        reviewSubmitmPage.emptyCartService(1);
        String bldNo=new Random().nextInt(1000000)+"";
        shippingBillingmPage.editAndEnterNewShippingAddress(bldNo+TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3);
       AssertFailAndContinue(shippingBillingmPage.hasShippingAddressUpdated(bldNo),"Shipping address is Added");
        shippingBillingmPage.editAndEnterNewBillingAddress(bldNo+TestData.ADDRESS4,TestData.CITY4,TestData.STATE4,TestData.ZIPCODE4,TestData.ADDCOUNTRY4);
        AssertFailAndContinue(shippingBillingmPage.hasBillingAddressUpdated(bldNo),"Billing address is Added");
        shippingBillingmPage.clickContinueCheckout();
        paymentmPage.clickContinueCheckout();

        reviewSubmitmPage.clickPlaceOrder();
        AssertFailAndContinue(thankYoumPage.isOrderPlaced(),"Landed on Thank You Page");


    }
}
