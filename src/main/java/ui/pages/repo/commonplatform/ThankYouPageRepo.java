package ui.pages.repo.commonplatform;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class ThankYouPageRepo extends UiBase {
    //

    @FindBy(css=".receipt-order-detail-list")
    public WebElement orderNumber;

    @FindBy(css="#checkout-wrap > div > div.grid.grid-parent > div > ul.action-group > li:nth-child(3) > a")
    public WebElement printReceipt;
}
