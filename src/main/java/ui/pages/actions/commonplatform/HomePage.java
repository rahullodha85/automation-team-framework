package ui.pages.actions.commonplatform;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.pages.repo.commonplatform.HomePageRepo;
import ui.support.Config;

/**
 * Created by vevinmoza on 3/18/16.
 */
public class HomePage extends HomePageRepo {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        setDriver(driver);
        PageFactory.initElements(driver, this);
    }


    public void searchFor(Object item) throws Exception{
        searchClickBtn.click();
        searchBox.clear();
        searchBox.sendKeys(item.toString()+ Keys.ENTER);
    }


    public boolean hasCountryChanged(Object country1) {
        if(countryChosen().getAttribute("src").contains(country1.toString())){
            return true;
        }else
            return false;
    }

    public void openAccount(String createdEmail, Object password1) throws Exception{
        openSignInSection();
        username.sendKeys(createdEmail);
        password.sendKeys(password1.toString());
        signIn.submit();
        signIn.click();
        waitForAjax("Wait For your account page to load");
    }


    public void openSignInSection() throws Exception{
        driver.get(Config.getApplicationUrl()+"Entry.jsp");
        waitForAjax("Wait for home page to load before fresh login");
        mouseHover(signInLocation);
        welcomeSigninLink.click();
        waitForAjax("Wait for sign in page to load");
    }
    public void orderSearch(Object orderNumber,Object zipCode1){
        orderNumBox.sendKeys(orderNumber.toString());
        zipCode.sendKeys(zipCode1.toString());
        orderStatusBtn().click();
        waitForAjax("Waiting for order details to load");

    }

    public void changeCountryCurrencyContextChooser(Object currency1, Object country1) throws Exception {
        driver.get(Config.getApplicationUrl()+"main/context_chooser.jsp?bmForm=context_chooser&bmFormID=le_UhaK&bmUID=le_UhaL&bmIsForm=true&bmPrevTemplate=%2Fmain%2Fcontext_chooser.jsp&bmSingle=" +
                "INTERNATIONAL%3C%3Ecurrency&INTERNATIONAL%3C%3Ecurrency="+currency1.toString()+"&bmDynamic=INTERNATIONAL%3C%3Ecurrency%3AAUD%3ABSD%3ABHD%3APAB%3ABBD%3ABZD%3ABOB%3ABAM%3AGBP%3ABGN%3" +
                "AXOF%3AXAF%3ACAD%3AKYD%3ACLP%3ACOP%3ANIO%3ACRC%3AHRK%3ACZK%3ADKK%3ADOP%3AEGP%3AEUR%3APYG%3AHKD%3AHUF%3AINR%3AIDR%3AILS%3AJMD%3AJPY%3AJOD%3AKRW%3AKWD%3ALBP%3AHNL%3ASZL%3AMYR%3AMXN%" +
                "3AMAD%3ANAD%3ANPR%3AANG%3ARON%3ANZD%3ANOK%3AOMR%3APKR%3APEN%3APHP%3APLN%3AQAR%3AGTQ%3AKHR%3AMVR%3ASAR%3ASGD%3AZAR%3ALKR%3ASEK%3ACHF%3ATWD%3ABDT%3AKZT%3ATHB%3ATRY%3AUSD%3AUAH%3AAED" +
                "%3AUYU%3ACNY%3A&bmSingle=USA&USA=US+Dollar+%28USD%29&bmSingle=CANADA&CANADA=Canadian+Dollar+%28CAD%29&bmSingle=INTERNATIONAL%3C%3Ecountry&INTERNATIONAL%3C%3E" +
                "country="+country1.toString()+"&bmDynamic=INTERNATIONAL%3C%3Ecountry%3AAF%3AAL%3ADZ%3AAD%3AAO%3AAI%3AAG%3AAM%3AAW%3AAU%3AAT%3AAZ%3ABS%3ABH%3ABD%3ABB%3ABY%3ABE%3ABZ%3ABJ%3ABM%3ABT%" +
                "3ABO%3ABQ%3ABA%3ABW%3ABR%3ABN%3ABG%3ABF%3ABI%3AKH%3ACM%3ACA%3ACV%3AKY%3ACF%3ATD%3ACL%3ACN%3ACX%3ACC%3ACO%3AKM%3ACD%3ACG%3ACK%3ACR%3AHR%3ACW%3ACY%3ACZ%3ACI%3ADK%3ADJ%3ADM%3ADO%3AEC" +
                "%3AEG%3ASV%3AGQ%3AER%3AEE%3AET%3AFK%3AFO%3AFJ%3AFI%3AFR%3AGF%3APF%3AGA%3AGM%3AGE%3ADE%3AGH%3AGI%3AGR%3AGL%3AGD%3AGP%3AGT%3AGG%3AGN%3AGW%3AGY%3AHT%3AVA%3AHN%3AHK%3AHU%3AIS%3AIN%3" +
                "AID%3AIQ%3AIE%3AIM%3AIL%3AIT%3AJM%3AJP%3AJE%3AJO%3AKZ%3AKE%3AKI%3AKR%3AXK%3AKW%3AKG%3ALA%3ALV%3ALB%3ALS%3ALR%3ALY%3ALI%3ALT%3ALU%3AMO%3AMK%3AMG%3AMW%3AMY%3AMV%3AML%3AMT%3AMH%3AMQ" +
                "%3AMR%3AMU%3AYT%3AMX%3AFM%3AMD%3AMC%3AMN%3AME%3AMS%3AMA%3AMZ%3AMM%3ANA%3ANR%3ANP%3ANL%3ANC%3ANZ%3ANI%3ANE%3ANG%3ANU%3ANO%3AOM%3APK%3APW%3APA%3APG%3APY%3APE%3APH%3APL%3APT%3AQA%3" +
                "ARO%3ARU%3ARW%3ARE%3ABL%3AKN%3ALC%3APM%3AVC%3AWS%3ASM%3AST%3ASA%3ASN%3ARS%3ASC%3ASL%3ASG%3ASX%3ASK%3ASI%3ASB%3ASO%3AZA%3AES%3ALK%3ASR%3ASZ%3ASE%3ACH%3ATW%3ATJ%3ATZ%3ATH%3ATL%3ATG" +
                "%3ATO%3ATT%3ATN%3ATR%3ATC%3ATV%3AUS%3AUG%3AUA%3AAE%3AGB%3AUY%3AUZ%3AVU%3AVN%3AVG%3AWF%3AYE%3AZM%3AZW%3A&bmSubmit=update&update=Save+%26+Continue+Shopping&bmSubmit=proceed&" +
                "bmSingle=USA&USA=US+Dollar+%28USD%29");
        System.out.println("");
    }

    public void closePopup() throws Exception{
        if(waitUntilElementDisplayed(emailPopupFrame, 5)){
            driver.switchTo().frame(emailPopupFrame);
            frameCloseBtn.click();
            driver.switchTo().defaultContent();
        }
    }

    public void clickYourAccountsLink(){
        mouseHover(welcomeSigninLink);
        yourAccountLink.click();
    }
}