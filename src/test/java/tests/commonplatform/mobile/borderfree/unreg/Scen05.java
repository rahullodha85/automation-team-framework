package tests.commonplatform.mobile.borderfree.unreg;

import initializer.BaseTest;
import initializer.Retry;
import org.apache.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.support.Config;

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
    public void placeOrdermUnRegGuestUserCopyShippingToBilling() throws Exception {
        setAuthorInfo("Vevin Moza");

        homePage.changeCountryCurrencyContextChooser(TestData.CURRENCY2,TestData.COUNTRY2);
        AssertFailAndContinue(homemPage.hasCountryChanged(TestData.COUNTRY2),"Country has been changed to "+TestData.COUNTRY2);
        productDetailsPage.addToBag(TestData.PRODUCT1,TestData.SKU1,1);
        AssertFailAndContinue(productDetailsmPage.isItemAdded(TestData.PRODUCT1),"Item has been added");
        driver.get(Config.getApplicationUrl()+"checkout/SaksBag.jsp");
        AssertFailAndContinue(saksBagmPage.hasLandedOnSaksBag(),"Landed on Saks Bag mPage");
        saksBagmPage.clickCheckout().clickCheckoutAsGuest();

        AssertFailAndContinue(shippingBillingmPage.hasFirstNameField(),"Landed on Shipping mPage");
        shippingBillingmPage.addNewShippingAddress(TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3);
        AssertFailAndContinue(paymentmPage.hasCreditCardNameField(),"Landed on Payment mPage");
        paymentmPage.addNewPaymentMethod(TestData.CREDITCARDTYPE1,TestData.CREDITCARDNUMBER1);
        paymentmPage.enterPassword(TestData.PASSWORD1,TestData.PASSWORD1);
        AssertFailAndContinue(reviewSubmitmPage.haseditPayment(),"Landed on Place Order mPage");


        String bldNo=new Random().nextInt(1000000)+"";
        reviewSubmitmPage.editAndEnterNewShippingAddress(bldNo+TestData.ADDRESS3,TestData.CITY3,TestData.STATE3,TestData.ZIPCODE3,true);
        //edit shipping address and add to billing book
        //edit billing address and choose existing bill adderss
        // place order

        AssertFailAndContinue(reviewSubmitmPage.hasShippingAddressUpdated(bldNo),"Shipping address is udpated");
        reviewSubmitmPage.editAndChooseExistingBillingAddress(1);
        AssertFailAndContinue(reviewSubmitmPage.hasBillingAddressUpdated(bldNo),"Billing address is updated");




        String order=reviewSubmitmPage.clickPlaceOrder();
        AssertFailAndContinue(thankYoumPage.isOrderPlaced(),"Landed on Thank You mPage");



    }

}
