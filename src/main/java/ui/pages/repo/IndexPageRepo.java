package ui.pages.repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

import java.util.List;


public class IndexPageRepo extends UiBase {

    @FindBy(id="SimpleSearchForm_SearchTerm")
    public WebElement searchBox;




    public List<WebElement> bookRow(int i)
    {
        String css = "#gridview-1015-record-ext-record-" + (i+1) + ">td";
       return getDriver().findElements(By.cssSelector(css));
    }

}
