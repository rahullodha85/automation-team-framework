package ui.pages.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.IndexPageRepo;

/**
 * Created by fdkzb on 1/21/2015.
 */
public class IndexPageActions extends IndexPageRepo {

    WebDriver driver;

    public IndexPageActions(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    public void searchTerm(String keyword){
            sendText(searchBox,keyword+Keys.ENTER);
    }

    public String getTitleByRow(int row)
    {
        return getText(bookRow(row).get(1));
    }

    public String getAuthorByRow(int row)
    {
        return getText(bookRow(row).get(0));
    }
    public String getManufacturerByRow(int row)
    {
        return getText(bookRow(row).get(2));
    }
    public String getProductGroupByRow(int row)
    {
        return getText(bookRow(row).get(3));
    }

    public String selectedTitle()
    {
      //  return getText(selectedBookDetails).split("\n")[0].split(": ")[1];
        return null;
    }
    public String selectedAuthor()
    {
      //  return getText(selectedBookDetails).split("\n")[1].split(": ")[1];
        return null;
    }
    public String selectedManufacturer()
    {
       // return getText(selectedBookDetails).split("\n")[2].split(": ")[1];
        return null;
    }
    public String selectedProductGroup()
    {
       // return getText(selectedBookDetails).split("\n")[3].split(": ")[1];
        return null;
    }

    public void clickOnRow(int i)
    {
           boolean disp =  waitUntilElementDisplayed(bookRow(i).get(1));
        if(!disp) scrollDown();
        click(bookRow(i).get(1));
    }

    public void clickOnSelectedTitle()
    {
     /*   waitUntilElementDisplayed(selectedTitle);
        click(selectedTitle);*/
    }

    public boolean unCheckAuthor()
    {

       /* waitUntilElementDisplayed(authorTitle);
        mouseHover(authorTitle);
        waitUntilElementDisplayed(authorDropDown);
        click(authorDropDown);
        waitUntilElementDisplayed(columns);
        mouseHover(columns);
        if(!authorCheckBox.isSelected()){

            clickJs(authorCheckBox);
        }

        waitUntilElementDisplayed(authorTitle,10);
        return isDisplayed(authorTitle);*/
        return false;
    }

}
