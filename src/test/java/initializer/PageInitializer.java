package initializer;

import org.openqa.selenium.WebDriver;

import ui.pages.actions.IndexPageActions;
import ui.pages.actions.ProductArrayActions;
import ui.pages.actions.commonplatform.*;
import ui.pages.actions.commonplatformmobile.*;
import ui.pages.actions.service.*;
import ui.support.Config;

public class PageInitializer extends BrowserInitializer {
    public IndexPageActions indexPageActions;
    public ProductArrayActions productArrayActions;


    public HomePage homePage;
    public PaymentPage paymentPage;
    public ProductDetailsPage productDetailsPage;
    public ReviewSubmitPage reviewSubmitPage;
    public SaksBagPage saksBagPage;
    public ShippingBillingPage shippingBillingPage;
    public ThankYouPage thankYouPage;
    public YourAccountPage yourAccountPage;

    public HomemPage homemPage;
    public PaymentmPage paymentmPage;
    public ProductDetailsmPage productDetailsmPage;
    public ReviewSubmitmPage reviewSubmitmPage;
    public SaksBagmPage saksBagmPage;
    public ShippingBillingmPage shippingBillingmPage;
    public ThankYoumPage thankYoumPage;
    public YourAccountmPage yourAccountmPage;


    public HomeService homeService;
    public PaymentService paymentService;
    public ProductDetailService productDetailService;
    public ReviewSubmitService reviewSubmitService;
    public SaksBagService saksBagService;
    public ShippingBillingService shippingBillingService;
    public ThankYouService thankYouService;
    public YourAccountService yourAccountService;

    public void initializePages(WebDriver driver){
        indexPageActions =    new IndexPageActions(driver);
        productArrayActions = new ProductArrayActions(driver);
        homePage = new HomePage(driver);
        paymentPage=new PaymentPage(driver);
        productDetailsPage=new ProductDetailsPage(driver);
        reviewSubmitPage= new ReviewSubmitPage(driver);
        saksBagPage= new SaksBagPage(driver);
        shippingBillingPage= new ShippingBillingPage(driver);
        thankYouPage= new ThankYouPage(driver);
        yourAccountPage= new YourAccountPage(driver);

        homemPage = new HomemPage(driver);
        paymentmPage=new PaymentmPage(driver);
        productDetailsmPage=new ProductDetailsmPage(driver);
        reviewSubmitmPage= new ReviewSubmitmPage(driver);
        saksBagmPage= new SaksBagmPage(driver);
        shippingBillingmPage= new ShippingBillingmPage(driver);
        thankYoumPage= new ThankYoumPage(driver);
        yourAccountmPage= new YourAccountmPage(driver);

        homeService = new HomeService(driver,appStateVariables);
        paymentService=new PaymentService(driver,appStateVariables);
        productDetailService=new ProductDetailService(driver,appStateVariables);
        reviewSubmitService= new ReviewSubmitService(driver,appStateVariables);
        saksBagService= new SaksBagService(driver,appStateVariables);
        shippingBillingService= new ShippingBillingService(driver,appStateVariables);
        thankYouService= new ThankYouService(driver,appStateVariables);
        yourAccountService= new YourAccountService(driver,appStateVariables);

    }
}
