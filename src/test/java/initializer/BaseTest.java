package initializer;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import ui.support.Config;
import ui.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BaseTest extends PageInitializer {
   static XmlParser dataForTests;
    public BaseTest() {
        try {
             dataForTests= new XmlParser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final Logger logger = Logger.getLogger(BaseTest.class);

    public void setUpPageInitializer(WebDriver driver) {
        initializePages(driver);
    }

    public void refreshCurrentPage()
    {
        getDriver().navigate().refresh();
    }

    public static  String dataRetrieve(String tagName,int index)
    {
            try{
            return dataForTests.getTagName(tagName,index).trim();}
            catch (Exception e){
                e.printStackTrace();
            }
        return null;
    }

    public static  String dataRetrieve(String tagName,int index,String attribute)
    {
        try{
            return dataForTests.getAttributeForTag(tagName,index,attribute).trim();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String getRandomItem()  {

        try {
            String passervice = RestClient.getRequest("http://hd1qdms01lx.digital.hbc.com:9550/v1/product-array-service/array/search?query=shoes");


            List<String> products = RegexSearch.getMultipleString("\"product_code\" : \"(\\d+)", passervice, 1);
            //System.out.println(products);
            Collections.shuffle(Arrays.asList(products));
            for (int i = 0; i < products.size(); i++) {
                String pdpervice = RestClient.getRequest("http://hd1qdms01lx.digital.hbc.com:9460/v2/product-detail-service/pdp/product/" + products.get(i));

                pdpervice = pdpervice.replaceAll("\\s+", "");
                List<String> skuids = RegexSearch.getMultipleString("sku_id\":\"(\\d+)\",\"price\":\\{.*?\\},\"status_alias\":\"(\\w+)", pdpervice, 1);
                List<String> status = RegexSearch.getMultipleString("sku_id\":\"(\\d+)\",\"price\":\\{.*?\\},\"status_alias\":\"(\\w+)", pdpervice, 2);

                //System.out.println(skuids);
               // System.out.println(status);
                for (int j = 0; j < skuids.size(); j++) {
                    if (status.get(j).contains("available")) {
                        return  products.get(i)+" "+skuids.get(j);
                    }
                }


            }

        }
        catch ( IOException e){
            e.printStackTrace();

        }
        return "0" + " " + "0";
    }
    public void waitForAjax()
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int iterationsIndex=0;
        while (iterationsIndex<=120) // Handle timeout somewhere
        {
            Object ajaxIsComplete = js.executeScript("return window.jQuery != undefined && !!jQuery && jQuery.active == 0");
            if ((boolean)ajaxIsComplete)
            {
                break;
            }
            System.out.println("Waiting..");
            staticWait(500);
            iterationsIndex++;
        }
    }

    public void staticWait(int milliSecs)
    {
        try {
            Thread.sleep(milliSecs);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   public enum TestData {
       PRODUCT1(dataRetrieve("ProductSaks", 0)), SKU1(dataRetrieve("ProductSaks", 0, "skuid")),
       LOGIN1(dataRetrieve("Login", 0)), PASSWORD1(dataRetrieve("Password", 0)),
       PROMO1(dataRetrieve("Promo", 0)),
       PRODUCT2(dataRetrieve("ProductSaks", 1)), SKU2(dataRetrieve("ProductSaks", 1, "skuid")), PRODUCT3(dataRetrieve("ProductSaks", 2)), SKU3(dataRetrieve("ProductSaks", 2, "skuid")),
       PRODUCT4(dataRetrieve("ProductSaks", 3)), SKU4(dataRetrieve("ProductSaks", 3, "skuid")), PRODUCT5(dataRetrieve("ProductSaks", 4)), SKU5(dataRetrieve("ProductSaks", 4, "skuid")),
       PRODUCT6(dataRetrieve("ProductSaks", 5)),
       SKU6(dataRetrieve("ProductSaks", 5, "skuid")), PRODUCT7(dataRetrieve("ProductSaks", 6)), PRODUCT8(dataRetrieve("ProductSaks", 7)), SKU8(dataRetrieve("ProductSaks", 7, "skuid")),
       LOGIN2(dataRetrieve("Login", 1)), PASSWORD2(dataRetrieve("Password", 1)), ADDRESS1(dataRetrieve("Address", 0)), STATE1(dataRetrieve("State", 0)), CITY1(dataRetrieve("State", 0)), ZIPCODE1(dataRetrieve("ZipCode", 0)),
       ADDRESS3(dataRetrieve("Address", 2)), STATE3(dataRetrieve("State", 2)), CITY3(dataRetrieve("City", 2)), ZIPCODE3(dataRetrieve("ZipCode", 2)),
       ADDCOUNTRY3(dataRetrieve("AddressCountry", 2)),
       ADDRESS4(dataRetrieve("Address", 3)), STATE4(dataRetrieve("State", 3)), CITY4(dataRetrieve("City", 3)), ZIPCODE4(dataRetrieve("ZipCode", 3)),
       ADDCOUNTRY4(dataRetrieve("AddressCountry", 3)),
       CREDITCARDTYPE1(dataRetrieve("CardType", 0)), CREDITCARDNUMBER1(dataRetrieve("CreditCardNumber", 0)), PRODUCT9(dataRetrieve("ProductSaks", 8)), SKU9(dataRetrieve("ProductSaks", 8, "skuid")), COUNTRY1(dataRetrieve("Country", 0)), CURRENCY1(dataRetrieve("Currency", 0)), COUNTRY2(dataRetrieve("Country", 1)), CURRENCY2(dataRetrieve("Currency", 1)), ADDRESS2(dataRetrieve("Address", 1)), STATE2(dataRetrieve("State", 1)), CITY2(dataRetrieve("City", 1)), ZIPCODE2(dataRetrieve("ZipCode", 1)), ADDRESSCOUNTRYCODE2(dataRetrieve("AddressCountryCode", 1)),
       LOGIN3(dataRetrieve("Login", 2)), PASSWORD3(dataRetrieve("Password", 2)), ADDRESSCOUNTRYCODE1(dataRetrieve("AddressCountryCode", 0)),
       CREDITCARDTYPE2(dataRetrieve("CardType", 1)), CREDITCARDNUMBER2(dataRetrieve("CreditCardNumber", 1)), COUNTRY3(dataRetrieve("Country", 2)), CURRENCY3(dataRetrieve("Currency", 2)),
       PRODUCTRANDOM1(getRandomItem().split(" ")[0]), SKURANDOM1(getRandomItem().split(" ")[1]), LOGIN5(dataRetrieve("Login", 4)), PASSWORD5(dataRetrieve("Password", 4)),
       PRODUCTOFF1(dataRetrieve("ProductOff5th", 0)), SKUOFF1(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF2(dataRetrieve("ProductOff5th", 0)), SKUOFF2(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF3(dataRetrieve("ProductOff5th", 0)), SKUOFF3(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF4(dataRetrieve("ProductOff5th", 0)), SKUOFF4(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF5(dataRetrieve("ProductOff5th", 0)), SKUOFF5(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF6(dataRetrieve("ProductOff5th", 0)), SKUOFF6(dataRetrieve("ProductOff5th", 0, "skuid")),
       PRODUCTOFF7(dataRetrieve("ProductOff5th", 0)), SKUOFF7(dataRetrieve("ProductOff5th", 0, "skuid"));
       private final String text;

       private TestData(final String text) {
           this.text = text;
       }

       @Override
       public String toString() {
           return text;
       }
   }







}