package ui.pages.actions.service;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.PaymentPageRepo;
import ui.support.AppStateVariables;

/**
 * Created by vevinmoza on 3/30/16.
 */
public class PaymentService extends PaymentPageRepo {
    WebDriver driver;
    AppStateVariables appStateVariables;
    public PaymentService(WebDriver driver, AppStateVariables appStateVariables) {
        this.driver = driver;
        this.appStateVariables=appStateVariables;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public void addPaymentMethod(Object creditcardtype1, Object creditcardnumber1) throws Exception {
        /*makeRequest("checkout/checkout.jsp?bmForm=international_submit_payment_service" +
                "&CREDIT_CARD%3C%3EcardBrand_cd=MasterCard" +
                "&CREDIT_CARD%3C%3EcardNum=5444009999222205" +
                "&CREDIT_CARD%3C%3EcardholderName=Amit+Jain" +
                "&CREDIT_CARD%3C%3EcardMonth_cd=12" +
                "&CREDIT_CARD%3C%3EcardYear_cd=2028" +
                "&card_cvNumber=121" +
                "&promoCode=" +
                "&USER_ACCOUNT%3C%3Epassword=" +
                "&USER_ACCOUNT%3C%3EconfirmPassword=" +
                "&USER_ACCOUNT%3C%3EATR_passwordHint=");*/

        makeRequest("checkout/checkout.jsp?bmForm=submit_payment_service" +
                "&CREDIT_CARD%3C%3EcardBrand_cd=MC" +
                "&CREDIT_CARD%3C%3EcardNum=5444009999222205" +
                "&CREDIT_CARD%3C%3EcardholderName=Master+Card" +
                "&CREDIT_CARD%3C%3EcardMonth_cd=12" +
                "&CREDIT_CARD%3C%3EcardYear_cd=2028" +
                "&card_cvNumber=121" +
                "&ACCOUNT%3C%3EaccountNumber=" +
                "&ACCOUNT%3C%3EnotificationEmail=" +
                "&promoCode=" +
                "&USER_ACCOUNT%3C%3Epassword=" +
                "&USER_ACCOUNT%3C%3EconfirmPassword=" +
                "&USER_ACCOUNT%3C%3EATR_passwordHint="
                );
        //       waitForTextOnPage("SROrderTrackingInfo");
        makeRequest("checkout/checkout.jsp?#review");
    }

    public void continueCheckout() throws Exception {
        Object creditCard=executeJavaScript("return Order.getCreditCard()");

        String cardYear=regexSearch.getString("cardYear=(\\d{4})",creditCard.toString());
        String id=regexSearch.getString("id=(\\d+)",creditCard.toString());
        String cardHolderName=regexSearch.getString("cardName=([a-zA-Z .'-]+\\d+)",creditCard.toString());
        String cardMonth=regexSearch.getString("cardName=([a-zA-Z .'-]+\\d+)",creditCard.toString());
        //String cardMonth=regexSearch.getString("cardName=([a-zA-Z .'-]+\\d+)",creditCard.toString());
        System.out.println(cardYear);

        /*makeRequest("checkout/checkout.jsp?bmForm=submit_payment_service&" +
                "CREDIT_CARD%3C%3Eccd_id="+jObjectCreditCard.toString() +
                "&card_cvNumber=121" +
                "&CREDIT_CARD%3C%3EcardholderName=Hello+World98857" +
                "&CREDIT_CARD%3C%3EcardMonth_cd=2" +
                "&CREDIT_CARD%3C%3EcardYear_cd=2018" +
                "&CREDIT_CARD%3C%3EindDefaultCredit=false" +
                "&ACCOUNT%3C%3EaccountNumber=" +
                "&ACCOUNT%3C%3EnotificationEmail=" +
                "&" +
                "promoCode=");*/
    }
}
