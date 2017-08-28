package ui.pages.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

import java.util.List;


public class ProductArrayRepo extends UiBase {


    @FindBy(css = "#right_con > h1")
    public  WebElement searchedItem;

    @FindBy(css = "#ErrorMessageText")
    public  WebElement productArrayRefreshMessage;

    @FindBy(css = "#WC_CatalogSearchResultDisplay_link_39")
    public static WebElement sortBrandlink;


    public enum Sort {

        BRAND(sortBrandlink);
        private WebElement sortby;

        public WebElement getSortby() {
            return this.sortby;
        }

        private Sort(WebElement sortby) {
            this.sortby = sortby;
        }

        ;


    }


}
