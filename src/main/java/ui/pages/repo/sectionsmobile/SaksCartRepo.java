package ui.pages.repo.sectionsmobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import ui.UiBase;

import java.util.List;

/**
 * Created by vevinmoza on 3/23/16.
 */
public class SaksCartRepo  extends UiBase{

    @FindBy(css=".jsConfirm")
    public WebElement removeConfirm;

    @FindBy(css="#jsWideBag")
    public WebElement saksBagText;

    //action-link
    public WebElement getRemoveGift(String upc){
        return  getItem(upc).findElement(By.className("jsRemoveGifting"));
    }

    public List<WebElement> getSaksItemsInCart(){
        return getDriver().findElements(By.cssSelector("div[class='item jsItemRoot']"));
    }



    @FindBy(css="#giftingMsg1")
    public WebElement giftMessage;



    @FindBy(css="#giftName-new")
    public WebElement giftBoxName;


    public Select shippingMethodUpc(String upc){
            return new Select(getItem(upc).findElement(By.name("selectShipMethod")));
    }

    @FindBy(css="#jsSubmitGifting")
    public WebElement applyGifting;

    @FindBy(css=".js-quantity.quantity")
    public WebElement qtyOverlayItem;

    @FindBy(css=".sfa-button.transactional.medium.js-action-button")
    public WebElement apply;

    public WebElement removeItemlink(String upc){
        return  getItem(upc).findElement(By.className("jsRemoveItem"));
    }

    public WebElement giftSummaryLbl(String upc){
        return  getItem(upc).findElement(By.className("giftbox-summary"));
    }

    public WebElement editShippingAddressLnk(String upc){
        return  getItem(upc).findElement(By.className("jsEditSelectedShipAddress"));
    }


    public WebElement makeGiftLnk(String upc){
       return  getItem(upc).findElement(By.className("jsAddGifting"));
    }

    public WebElement editQuanityLnk(String upc){
        return  getItem(upc).findElement(By.className("jsEditItem"));
    }
    public WebElement getSubTotalItemLbl(String upc){
        return  getItem(upc).findElement(By.className("widebag-item-extended-price"));
    }
    public WebElement getUnitPriceLbl(String upc){
        return  getItem(upc).findElement(By.className("widebag-item-unit-price"));
    }

    public WebElement getSignatureDeliveryOption(String upc){
        return  getItem(upc).findElement(By.className("jsSignatureRequired"));
    }

    public WebElement getItem(String upc){
        return getDriver().findElement(By.cssSelector("div[data-cartproductcode='"+upc +"']"));
    }

    public WebElement shippingMethod(String upc ,int index){
        return getItem(upc).findElement(By.className("jsAddressLevelShipping")).findElements(By.tagName("label")).get(index);
    }

    public Select shippingMethod(String upc){
        return new Select(getItem(upc).findElement(By.className("jsSelectShipMethod")));
    }


}
