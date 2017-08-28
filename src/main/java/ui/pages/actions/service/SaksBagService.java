package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.SaksBagPageRepo;
import ui.support.AppStateVariables;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class SaksBagService extends SaksBagPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public SaksBagService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToSaksBag() throws Exception {

        makeRequest("checkout/SaksBag.jsp?PRODUCT%3C%3Eprd_id="+appStateVariables.getProductId()+"&FOLDER%3C%3Efolder_id="+appStateVariables.getFolderId()+"&bmUID="+appStateVariables.getBmUID());


        //initialize saks bag
        makeRequest("checkout/SaksBag.jsp?bmForm=initialize_saks_bag_service");
        waitForTextOnPage("breadcrumbs");
        //refresh saks bag page
        makeRequest("checkout/SaksBag.jsp");
        waitForPageReadyState("waiting for saks bag page to load and refresh");


    }

    public boolean hasLandedOnSaksBag() {
        waitForAjax("landing on saks bag");
        if(getpageSource().indexOf("Enter One Promo Code At A Time")>=0){
           System.out.println("true");
            return true;
        }{
            System.out.println("false");
        return false;}
    }

    public void doGuestCheckout() throws Exception {
        // guest checkout request
        makeRequest("checkout/checkout.jsp?bmForm=international_login_as_guest_user&LOGIN<>userid=");
        waitForTextOnPage("breadcrumbs");
        // shipping page display
        makeRequest("checkout/checkout.jsp");
        waitForPageReadyState("wait for page to load during guest checkout");
        // continue to checkout
        makeRequest("checkout/checkout.jsp?bmForm=international_continue_to_checkout_service");
        waitForTextOnPage("SROrderTrackingInfo");
        //get titles for names
        makeRequest("checkout/checkout.jsp?bmForm=get_option_list_service&listName=titles");
        waitForTextOnPage("Select a Title");
        // refresh shipping page
        makeRequest("checkout/checkout.jsp");
        waitForPageReadyState("waiting for guest shipping page to load");


    }

    public void applyPromo(Object promo1) throws Exception {
        makeRequest("checkout/checkout.jsp?bmForm=applyPromoSaksBag&promoCode="+promo1.toString());
        waitForTextOnPage("breadcrumbs");
        makeRequest("checkout/SaksBag.jsp");
    }

    public boolean hasPromoRemoveLink() {
        return waitUntilElementDisplayed(promoRemoveLnk);

    }

    public void loginWith(Object login1, Object password1) throws Exception {
        makeRequest("checkout/checkout.jsp?bmForm=login_registered_user_service&LOGIN<>userid="+login1.toString()+"&LOGIN<>password="+password1.toString());
        waitForTextOnPage("breadcrumbs");
        makeRequest("checkout/checkout.jsp");
        waitForPageReadyState("waiting after login");
        makeRequest("checkout/checkout.jsp?bmForm=continue_to_checkout_service");
        waitForTextOnPage("SROrderTrackingInfo");
        makeRequest("checkout/checkout.jsp");
        waitForPageReadyState("waiting after login second time");

    }
}
