package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ReviewSubmitPageRepo;
import ui.support.AppStateVariables;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class ReviewSubmitService extends ReviewSubmitPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public ReviewSubmitService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void placeOrder() throws Exception {
       makeRequest("checkout/checkout.jsp?bmForm=international_submit_order_service&&");
        waitForTextOnPage("SROrderTrackingInfo");
      /*  makeRequest("checkout/checkout.jsp?bmForm=submit_order_service&&");
        waitForTextOnPage("SROrderTrackingInfo");*/

        makeRequest("checkout/checkout.jsp");

        waitForPageReadyState("waiting for place order page");
        waitForPageReadyState("waiting for place order page");
    }
}
