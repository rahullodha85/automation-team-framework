package ui.pages.actions.service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.ShippingBillingPageRepo;
import ui.support.AppStateVariables;
import ui.utils.RandomNumberGenerator;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class ShippingBillingService extends ShippingBillingPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public ShippingBillingService(WebDriver driver,AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void addShippingBillingAddress(Object country,Object CountryCode,Object address,Object city,Object state,Object postal) throws Exception {
        //validate shipping address

        makeRequest("checkout/checkout.jsp?bmForm=validate_international_ship_address_service&SHIP_TO_ADDRESS<>indGift=3" +
                "&SHIP_TO_ADDRESS<>firstName=Amit" +
                "&SHIP_TO_ADDRESS<>middleName=" +
                "&SHIP_TO_ADDRESS<>lastName=Raj" +
                "&SHIP_TO_ADDRESS<>address3=Bank%20of%20America" +
                "&undefined=Canada" +
                "&SHIP_TO_ADDRESS<>country_cd="+CountryCode.toString() +
                "&SHIP_TO_ADDRESS<>address1="+address.toString() +
                "&SHIP_TO_ADDRESS<>address2=" +
                "&SHIP_TO_ADDRESS<>city="+city.toString() +
                "&SHIP_TO_ADDRESS<>state_cd="+state.toString() +
                "&SHIP_TO_ADDRESS<>postal="+postal.toString() +
                "&setAsBillAddress=true" +
                "&international-ship-method=EXPRESS_DDP|PREPAID" +
                "&LOGIN<>userid=amit.jain%40gmail.com" +
                "&SHIP_TO_ADDRESS<>phone=9866789921" +
                "&count=1" +
                "&SHIP_TO_ADDRESS<>uad_id=");
        waitForTextOnPage("Verified");
        makeRequest("checkout/checkout.jsp?bmForm=international_add_address_and_continue_service" +
                "&SHIP_TO_ADDRESS%3C%3EindGift=3" +
                "&SHIP_TO_ADDRESS%3C%3EfirstName=Amit" +
                "&SHIP_TO_ADDRESS%3C%3EmiddleName=" +
                "&SHIP_TO_ADDRESS%3C%3ElastName=Raj" +
                "&SHIP_TO_ADDRESS%3C%3Eaddress3=Bank+of+America" +
                "&undefined=Canada" +
                "&SHIP_TO_ADDRESS%3C%3Ecountry_cd="+CountryCode.toString() +
                "&SHIP_TO_ADDRESS%3C%3Eaddress1="+address.toString() +
                "&SHIP_TO_ADDRESS%3C%3Eaddress2=" +
                "&SHIP_TO_ADDRESS%3C%3Ecity="+city.toString() +
                "&SHIP_TO_ADDRESS%3C%3Estate_cd="+state.toString() +
                "&SHIP_TO_ADDRESS%3C%3Epostal="+postal.toString() +
                "&setAsBillAddress=true" +
                "&international-ship-method=EXPRESS_DDP%7CPREPAID" +
                "&LOGIN%3C%3Euserid=amit.jain%40gmail.com" +
                "&SHIP_TO_ADDRESS%3C%3Ephone=9866789921" +
                "&count=1" +
                "&SHIP_TO_ADDRESS%3C%3Euad_id=" +
                "&BILL_TO_ADDRESS%3C%3Estate_cd="+state.toString());
        waitForTextOnPage("SROrderTrackingInfo");
        //get credit card types
        makeRequest("checkout/checkout.jsp?bmForm=get_option_list_service&listName=creditCardTypes");
        waitForTextOnPage("MasterCard");
        makeRequest("checkout/checkout.jsp#payment");
        waitForPageReadyState("Waiting for payment page to load");



          }
}
