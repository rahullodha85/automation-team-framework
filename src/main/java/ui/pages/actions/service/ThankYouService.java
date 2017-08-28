package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ThankYouPageRepo;
import ui.support.AppStateVariables;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class ThankYouService extends ThankYouPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public ThankYouService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
}
