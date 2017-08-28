package ui.pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.ProductArrayRepo;

/**
 * Created by fdkzb on 1/21/2015.
 */
public class ProductArrayActions extends ProductArrayRepo {

    WebDriver driver;



    public ProductArrayActions(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    public boolean searchedTerm(String keyword){
        waitUntilElementDisplayed(searchedItem);
        return getText(searchedItem).indexOf(keyword)>=0;
    }
    public void clickOnSortBy(Sort sortby){
        click(Sort.BRAND.getSortby());
    }
    public boolean isProductArrayRefreshed(){
        return getText(productArrayRefreshMessage).toLowerCase().indexOf("refreshed")>=0;
    }

}
