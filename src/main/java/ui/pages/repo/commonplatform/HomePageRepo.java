package ui.pages.repo.commonplatform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class HomePageRepo extends UiBase {

    @FindAll({
        @FindBy(css="#toolbar > li.header-utils__list-item.header-utils-search > div.search-flyout > div > div > form > section > input.header-utils-search__input"),
        @FindBy(name="SearchString")
    })
    public WebElement searchBox;

    @FindAll({
        @FindBy(css="#site-search > form > div.search-box > input.ui-autocomplete-input"),
        @FindBy(css="#toolbar > li.header-utils__list-item.header-utils-search > div > div")
    })
    public WebElement searchClickBtn;

    public WebElement  countryChosen(){
       return getDriver().findElement(By.cssSelector("li[class='menu-item shipping']")).findElement(By.tagName("img"));
    }

    @FindBy(name="LOGIN<>userid")
    public WebElement username;

    @FindBy(name="LOGIN<>password")
    public WebElement password;

    @FindBy(name="signIn")
    public WebElement signIn;

    @FindBy(css = "iframe[src*='email_popup']")
    public WebElement emailPopupFrame;

    @FindBy(id = "close-button")
    public WebElement frameCloseBtn;

    @FindBy(css = "#toolbar > div > ul > li:nth-child(1) > div > ul.first-of-type > li.first-child > a")
    public WebElement yourAccountLink;

    @FindBy(name="ORDER<>orderNum")
    public WebElement orderNumBox;

    @FindBy(name="ORDER<>zip")
    public WebElement zipCode;

    @FindBy(css=".account")
    public WebElement signInLocation;

    @FindAll ({
        @FindBy(className = "account"),
        @FindBy(css= "#toolbar > li.header-utils__list-item.header-utils-account.loggedout-user-utils > a")
    })
    public WebElement welcomeSigninLink;

    public WebElement getbmFormIDFromApp(){
        WebElement formId=getDriver().findElement(By.cssSelector("input[name='bmFormID']"));

        return formId;
    }

    public WebElement getbmUIDFromApp(){
        WebElement uId=getDriver().findElement(By.cssSelector("input[name='bmUID']"));

        return uId;

    }

    public WebElement getbmSIDFromApp(){
        WebElement sid=getDriver().findElement(By.cssSelector("input[name='sid']"));

        return sid;
    }

    public WebElement orderStatusBtn(){
        return getDriver().findElement(By.cssSelector("input[name='viewOrderStatus']"));
    }



    //
    //
}
