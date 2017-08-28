package tests.commonplatform.loginstickiness;

import initializer.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import tests.commonplatform.desktop.borderfree.unreg.Scen02;
import ui.support.Config;

import java.util.concurrent.TimeUnit;

/**
 * Created by williskong on 11/3/16.
 */
public class StickyWorld extends BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(Scen02.class);

    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        driver.get(Config.getApplicationUrl());
        setUpPageInitializer(driver);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().setPosition(new Point(900,0));
        logger.info("Url launched:" + Config.getApplicationUrl());
    }
    @BeforeMethod
    public void before() throws Exception{
        waitForAjax();
        driver.manage().deleteAllCookies();
        setAuthorInfo("Willis Kong");
        homePage.closePopup();
    }

    @Test
    public void withDomesticUserUsingWelcomeLink() throws Exception {
        homePage.welcomeSigninLink.click();
        homePage.closePopup();
        yourAccountPage.login("domestic_user@automation.com", "test1234");
        skipToLoginOverlay();
        AssertFail(driver.findElements(By.id("jsLoginWrap")).size()==0,
                "Login overlay is not displaying when domestic user is logged in.");
    }

    @Test
    public void withDomesticUserUsingYourAccountsLink() throws Exception {
        homePage.clickYourAccountsLink();
        homePage.closePopup();
        yourAccountPage.login("domestic_user@automation.com", "test1234");
        skipToLoginOverlay();
        AssertFail(driver.findElements(By.id("jsLoginWrap")).size()==0,
                "Login overlay is not displaying when domestic user is logged in.");
    }

    @Test
    public void withoutUser() throws Exception {
        skipToLoginOverlay();
        AssertFail(driver.findElements(By.id("jsLoginWrap")).size() > 0,
                "Login overlay is displaying when no user is logged in.");
    }

    public void skipToLoginOverlay() throws Exception{
        homePage.searchFor("0400087306531");
        productDetailsPage.addToBag();
        productDetailsPage.enterBag();
        saksBagPage.enterCheckoutbagBtn.click();
    }
}
