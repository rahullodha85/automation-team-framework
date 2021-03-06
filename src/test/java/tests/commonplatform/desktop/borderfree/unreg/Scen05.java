package tests.commonplatform.desktop.borderfree.unreg;

import initializer.BaseTest;
import initializer.Retry;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
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
public class Scen05 extends BaseTest {
    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen05.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        // driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().deleteAllCookies();
        driver.manage().window().setPosition(new Point(900,0));
        logger.info("Url launched:" + Config.getApplicationUrl());

    }

    @Test
    public void placeOrderUnRegGuestUserCopyShippingToBilling() throws Exception {
        setAuthorInfo("Vevin Moza");
        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY2,TestData.COUNTRY2);

        AssertFailAndContinue(homePage.hasCountryChanged(TestData.COUNTRY2),"Country has been changed to "+TestData.COUNTRY2);
        productDetailsPage.addToBag(TestData.PRODUCT1,TestData.SKU1,1);
        AssertFailAndContinue(productDetailsPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagPage.hasPromoCodeField(),"Landed on Saks Bag Page");
        saksBagPage.clickCheckout().clickCheckoutAsGuest();

        AssertFailAndContinue(shippingBillingPage.hasFirstNameField(),"Landed on Shipping Page");
        shippingBillingPage.addNewShippingAddress(TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3);
        AssertFailAndContinue(paymentPage.hasCreditCardNameField(),"Landed on Payment Page");
        paymentPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        AssertFailAndContinue(reviewSubmitPage.haseditPayment(),"Landed on Place Order Page");


        String bldNo=new Random().nextInt(1000000)+"";
        reviewSubmitPage.editAndEnterNewShippingAddress(bldNo+TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3,true);
        //edit shipping address and add to billing book
        //edit billing address and choose existing bill adderss
        // place order

        AssertFailAndContinue(reviewSubmitPage.hasShippingAddressUpdated(bldNo),"Shipping address is udpates");
        reviewSubmitPage.editAndChooseExistingBillingAddress(2);
        AssertFailAndContinue(reviewSubmitPage.hasBillingAddressUpdated(bldNo),"Billing address is edited");
        String order=reviewSubmitPage.clickPlaceOrder();
        AssertFailAndContinue(thankYouPage.isOrderPlaced(),"Landed on Thank You Page");



    }

}
