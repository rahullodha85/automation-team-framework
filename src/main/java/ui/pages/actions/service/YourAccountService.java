package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.YourAccountPageRepo;
import ui.support.AppStateVariables;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class YourAccountService extends YourAccountPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public YourAccountService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }
}
