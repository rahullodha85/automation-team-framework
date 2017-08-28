package ui.pages.repo.commonplatformmobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.UiBase;

/**
 * Created by vevinmoza on 4/4/16.
 */
public class ThankYoumPageRepo extends UiBase {
    @FindBy(css="#confirmation-head > h2")
    public WebElement orderNumber;

    @FindBy(css="#checkout-wrap > div > div.grid.grid-parent > div > ul.action-group > li:nth-child(3) > a")
    public WebElement printReceipt;
}
