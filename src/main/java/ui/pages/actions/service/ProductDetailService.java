package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ProductDetailsPageRepo;
import ui.support.AppStateVariables;

import java.util.concurrent.TimeUnit;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class ProductDetailService extends ProductDetailsPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public ProductDetailService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        setDriver(driver);
        this.appStateVariables=appStateVariables;
        PageFactory.initElements(driver, this);
    }

    public void addToBag(Object product,Object sku,Object quantity) throws Exception {
        String prdId=regexSearch.findString(getCurrentUrl(),"prd_id=","&FOLDER");
        String folderId= regexSearch.findString(getCurrentUrl(),"folder_id=","&prp8=");
        appStateVariables.setFolderId(folderId);
        appStateVariables.setProductId(prdId);
        //add to bag click on pdp page
        makeRequest("checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+product.toString()+"&itemQuantity="+quantity.toString()+"&sku_id="+sku.toString()+"&type=REQUEST_ADD_TO_BAG");
        if(driver.getPageSource().indexOf("session_expired")>=0){
            makeRequest("checkout/checkout.jsp?bmForm=add_saks_suggests_item_service_product_array&productCode="+product.toString()+"&itemQuantity="+quantity.toString()+"&sku_id="+sku.toString()+"&type=REQUEST_ADD_TO_BAG");

        }

        waitForTextOnPage("addToBagResults");
        //refreshing the pdp page
        makeRequest("main/ProductDetail.jsp");

        waitForPageReadyState("wait for pdp refresh after adding items in cart");
        //waitForAjax("wait between product and insert ajax");
        //displays the saksbag overlay
        //uncomment later
       /* makeRequest("include/saksbaginsert.jsp?PRODUCT%3C%3Eprd_id="+prdId+"&FOLDER%3C%3Efolder_id="+folderId+"&bmUID="+appStateVariables.getBmUID()+"&num_of_items_added="+quantity.toString());
        waitForPageReadyState("Waiting for Saks Bag Overlay");*/
    }

    public boolean hasAddToBagButton() throws InterruptedException {

        waitForTextOnPage("reskin-addtobag-button sfa-button large transactional");

        if(getpageSource().indexOf("ADD TO BAG")>=0){
            return true;
        }
            return false;

    }


    public boolean isItemInBag(Object product1) {
        if(getpageSource().indexOf(product1.toString())>=0){
            return true;
        }
        return false;
    }
}
