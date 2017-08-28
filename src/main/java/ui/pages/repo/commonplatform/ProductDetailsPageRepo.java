package ui.pages.repo.commonplatform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ProductDetailsPageRepo extends UiBase {

/*
    @FindBy(css="#plainAdd > input.reskin-addtobag-button.sfa-button.large.transactional")
    public WebElement addToBag;
*/

    //

    @FindAll({
        @FindBy(css="#pdp-content-area > div > div > div > div > article > section.product__summary > section > div > div:nth-child(4) > button"),
        @FindBy(css="#pdp-content-area > div > div > div > div > article > section.product__summary > section.product__sku-selection > div > div:nth-child(3) > button")
    })
    public WebElement addToBag;

    @FindAll({
        @FindBy(css="#saksbagoverlay_insert > div.sub-header.clearfix > a.sfa-button.small.transactional"),
        @FindBy(css = "#saksbagoverlay_insert > div.sub-header.clearfix > a.saksBagViewMyBag")
    })
    public WebElement viewMyBag;

    @FindBy(css="#SaksBagOverlay")
    public WebElement saksBagOverlay;

    @FindBy(css="html")
    public WebElement htmlPage;

    @FindBy(css="#MainProductqtyToBuy0")
    public WebElement qtyBox;

    @FindBy(name="persText-raw")
    public WebElement textBoxForMessagePersonalized;

    @FindBy(css=".product__personalize-button.is-hidden-on-mobile")
    public WebElement personalAddToBag;

    @FindBy(css="#pers_checkout")
    public WebElement personalAddToBagFinal;


}
