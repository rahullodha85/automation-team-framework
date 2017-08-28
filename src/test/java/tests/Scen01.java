package tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import initializer.BaseTest;
import ui.pages.actions.ProductArrayActions;
import ui.support.Config;
import ui.support.EnvironmentConfig;

import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.useRelaxedHTTPSValidation;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;


import com.jayway.restassured.authentication.FormAuthConfig;
/**
 * Created by txbak on 1/16/2015.
 */
public class Scen01 extends BaseTest {

    WebDriver driver;

    private static final Logger logger = Logger.getLogger(Scen01.class);


    @BeforeClass
    public void setUp() throws Exception {
        initializeDriver();
        driver = getDriver();
        driver.get(EnvironmentConfig.getApplicationUrl());
        setUpPageInitializer(driver);
    }

    @BeforeMethod()
    public void beforeMethod() throws Exception
    {

            driver.manage().deleteAllCookies();
            driver.get(EnvironmentConfig.getApplicationUrl());
            logger.info("Url launched:" + EnvironmentConfig.getApplicationUrl());
   }


    @Test
    public void sortbybrand() throws Exception {
        setAuthorInfo("Vevin Moza");
        indexPageActions.searchTerm("Shoes");
        AssertFailAndContinue(productArrayActions.searchedTerm("Shoes"),"Search for keyword in Search Box");
        productArrayActions.clickOnSortBy(ProductArrayActions.Sort.BRAND);

        AssertFailAndContinue(productArrayActions.isProductArrayRefreshed(),"Product array should be refreshed");

    }

    @Test
    public void Test2() throws Exception {
        setAuthorInfo("Vevin Moza");
        baseURI="http://qa.saksdirect.com";
        when().
                get("/checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+TestData.PRODUCT1+"&itemQuantity=3&sku_id="+TestData.SKU1+"&type=REQUEST_ADD_TO_BAG").
                then().
                assertThat().statusCode(200).
                log().all();

    }


    @Test
    public void Test3() throws Exception {
        setAuthorInfo("Vevin Moza");
        indexPageActions.searchTerm("Shoes");
        AssertFailAndContinue(productArrayActions.searchedTerm("Shoes"),"Search for keyword in Search Box");
        /*setAuthorInfo("Ajay");
        refreshCurrentPage();
        AssertFailAndContinue(!indexPageActions.unCheckAuthor(),"Verify Author title not displayed after unchecking");

*/
    }
}



