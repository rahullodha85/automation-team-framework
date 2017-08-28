package ui;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import ui.support.Config;
import ui.utils.RegexSearch;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class UiBase {

    private static final String SELENIUM_MAXTIMEOUT = "selenium.element.maxtimeout";
    private static final String SELENIUM_MINTIMEOUT = "selenium.element.mintimeout";
    private static final String SELENIUM_IMPLICITWAIT = "selenium.element.implicitwait";
    private static final String PAGELOAD_MAXTIMEOUT = "selenium.pageload.timeout";
    private static final String THREAD_MAXTIMEOUT = "thread.maxtimeout";
    private static final String CONFIG_FILE = "Config.properties";
    private boolean checkoutType;
    private static String browserType;
    private Actions actions;
    private JavascriptExecutor javascriptExecutor;


    public  WebDriver getDriver() {
        return driver;
    }
    public RegexSearch regexSearch= new RegexSearch();

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }



    private WebDriver driver;


    private static final Logger logger = Logger.getLogger(UiBase.class);


    private static PropertiesConfiguration getPropertiesConfiguration() {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(loadAndGetResourceLocation(CONFIG_FILE));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return config;
    }

    public boolean isInternational(){
        return Boolean.parseBoolean(executeJavaScript("return services.isInternational()").toString());
    }


    private static String loadAndGetResourceLocation(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(fileName).toString();
    }

    public static int getMinTimeout() {
        PropertiesConfiguration config = getPropertiesConfiguration();
        return getTimeOutForProperty(config, SELENIUM_MINTIMEOUT);
    }


    public static int getMaxTimeout() {
        PropertiesConfiguration config = getPropertiesConfiguration();
        return getTimeOutForProperty(config, SELENIUM_MAXTIMEOUT);
    }

    public static int getDefaultImplicitTimeout() {
        PropertiesConfiguration config = getPropertiesConfiguration();

        return getTimeOutForProperty(config, SELENIUM_IMPLICITWAIT);
    }

    public static int getMaxPageLoadTimeout() {
        PropertiesConfiguration config = getPropertiesConfiguration();

        return getTimeOutForProperty(config, PAGELOAD_MAXTIMEOUT);
    }


    private static int getTimeOutForProperty(PropertiesConfiguration config, String config_property_name) {
        String timeOut = config.getString(config_property_name);
        return Integer.parseInt(timeOut);
    }


   public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

    public static String getCssLocator(WebElement element) {
        String val = element.toString();
        return val.substring(val.lastIndexOf("selector:") + 10, val.length() - 1).trim();
    }
    public static String getXpathLocator(WebElement element) {
        String val = element.toString();
        return val.substring(val.lastIndexOf("xpath:") + 7, val.length() - 1).trim();
    }

    public void click(WebElement element)
    {
        element.click();
    }

    public void clickJs(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
    }



    public int numberOfElement(List<WebElement> elements)
    {
        int size =elements.size();
        return size;
    }


    public void sendText(WebElement element,String text)
    {
        try {
             element.sendKeys(text);
        }catch (Throwable t){
            t.printStackTrace();

        }

    }

    public String getText(WebElement element)
    {
        try {
            return element.getText();
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }

    }

    public Boolean waitUntilElementDisplayed(WebElement element)
    {
        try {
            (new WebDriverWait(driver, getMaxTimeout()))
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public Boolean waitUntilElementDisplayed(WebElement element, int timeInSeconds)
    {
        try {

            (new WebDriverWait(driver, timeInSeconds))

                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    public Boolean waitUntilElementDisplayed(String cssSelector)
    {
        try {

            (new WebDriverWait(driver, getMaxTimeout()))

                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }


    public Boolean waitUntilElementDisplayed(List<WebElement> elements, int timeInSeconds)
    {
        boolean retVal = false;
        try {
            (new WebDriverWait(driver, timeInSeconds))

                    .until(ExpectedConditions.visibilityOfAllElements(elements));
            retVal = true;
        }
        catch (Exception e){
            retVal = false;
        }
        return retVal;

    }

    public Boolean waitUntilElementDisplayed(List<WebElement> elements, String matchText, int timeInSeconds)
    {
        boolean retVal = false;
        for (WebElement element : elements) {
            (new WebDriverWait(driver, timeInSeconds))
                    .until(ExpectedConditions.visibilityOf(element));
            if (getText(element).equalsIgnoreCase(matchText)) {
                retVal = true;
                break;
            }

        }
        return retVal;
    }



    public boolean waitForElement(List<WebElement> elements, String matchText, int timeOut){
        boolean retVal = false;
        for (WebElement element : elements) {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(timeOut, TimeUnit.SECONDS)
                    .pollingEvery(2, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            wait.until(ExpectedConditions.visibilityOf(element));
            if (getText(element).equalsIgnoreCase(matchText)) {
                retVal = true;
                break;
            }

        }
        return retVal;
    }


    public void staticWait()
    {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void staticWait(int milliSecs)
    {
        try {
            Thread.sleep(milliSecs);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mouseHover(WebElement element) {

        staticWait();
        actions = new Actions(getDriver());
        actions.moveToElement(element).perform();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        staticWait();


    }






    public Object executeJavaScript(String script){
        javascriptExecutor=  (JavascriptExecutor) getDriver();
        while(true) {
            if (javascriptExecutor.executeScript(script) == null) {
                System.out.println("waiting for javascript variable probable initialization");
            }
            else {
                break;
            }
        }
        return javascriptExecutor.executeScript(script);
    }


    public Object executeJavaScript(String script,String location){
        javascriptExecutor=  (JavascriptExecutor) getDriver();
        while(true) {
            if (javascriptExecutor.executeScript(script) == null) {
                System.out.println("waiting for javascript variable probable initialization: "+location);
            }
            else {
                break;
            }
        }
        return javascriptExecutor.executeScript(script);
    }

    public void executeJavaScript(String script,WebElement element){
        javascriptExecutor=  (JavascriptExecutor) getDriver();
         javascriptExecutor.executeScript(script,element);
    }

    public void switchToFrame(WebElement element)
    {
        waitUntilElementDisplayed(element);
        getDriver().switchTo().frame(element);
    }

    public void switchToFrame(int frameId)
    {
        getDriver().switchTo().frame(frameId);
    }
    public void switchToFrame(String frameName)
    {
        getDriver().switchTo().frame(frameName);
    }

    public void switchToDefaultFrame()
    {
        getDriver().switchTo().defaultContent();
    }
    public Alert switchToAlart()
    {
        return getDriver().switchTo().alert();
    }
    public void switchToWindows(String win){
        getDriver().switchTo().window(win);
    }

    public void switchToNewWindow(){
        for(String winHandle : getWindowHandles()){
            getDriver().switchTo().window(winHandle);
        }
    }
    public Set <String> getWindowHandles(){
        return getDriver().getWindowHandles();
    }
    public String getWindowHandle(){
        return getDriver().getWindowHandle();
    }

    public Boolean waitForPageTitle(String title){
        try{
            (new WebDriverWait(driver, getMaxTimeout()))
                    .until(ExpectedConditions.titleIs(title));
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public Boolean waitForPageTitle(String title, int timeInSeconds){
        try{
            (new WebDriverWait(driver, timeInSeconds))
                    .until(ExpectedConditions.titleIs(title));
            return true;
        }
        catch(Exception e){
            return false;
        }

    }

    public boolean waitForTextToAppear(WebElement element,String textToAppear, int secs) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, secs);
            wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean waitForTextToAppearEqual(final WebElement element, final String textToAppear, int secs) {
        boolean result= false;
        try {
            result = new WebDriverWait(driver, secs).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    String text = element.getText();
                    return text.equalsIgnoreCase(textToAppear);
                }
            });
        } catch (Exception e) {
            return false;
        }
        return result;
    }


    public boolean waitForTextToAppear(WebElement element,String textToAppear) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, getMaxTimeout());
            wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public String getRandomNumber(int count){
        return RandomStringUtils.randomNumeric(count);
    }

    public String getRandomString(int count){
        return RandomStringUtils.randomAlphabetic(count);
    }

    public String getCurrentDate(String dateString){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateString);
        return dateFormat.format(cal.getTime());
    }
    public String getTitle()
    {
        String title = driver.getTitle();
        return title;
    }

    public String getFrameName(){
        return getDriver().findElement(By.tagName("iframe")).getAttribute("name");
    }

    public Boolean isDisplayed(WebElement element){
        try {
            boolean b = element.isDisplayed();
            String text = element.getText();


            return b;
        }
        catch(Throwable t)
        {
            return false;
        }

    }

    public Boolean isDisplayed(List<WebElement> element){
        boolean b =true;
        try {
            for(int i=0;i<element.size();i++)
            {
                b = b && element.get(i).isDisplayed();
            }

            return b;
        }
        catch(Throwable t)
        {
            return false;
        }

    }


    public boolean isEnabled(WebElement element){
        try {
            return element.isEnabled();
        }catch (Throwable t){
            return false;
        }
    }
    public boolean isSelected(WebElement element){
        try {
            return element.isSelected();
        }catch (Throwable t){
            return false;
        }
    }
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
        staticWait();
        refreshPage();
        System.out.println("deleteAllCookies");
        staticWait();
    }
    public String getAttributeValue(WebElement element, String attributeName){
        return element.getAttribute(attributeName);
    }

    public boolean isAttributePresent(WebElement element, String attributeName) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attributeName);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;

    }
    public void makeRequest(String request) throws Exception {
        System.out.println(Config.getApplicationUrl()+request);
        driver.get(Config.getApplicationUrl()+request);
    }
    public void navigateBack(){
        driver.navigate().back();
    }

    public boolean navigateAndVerify(WebElement currentPageElement,WebElement nextPageElement){

        waitUntilElementDisplayed(currentPageElement);
        click(currentPageElement);
        boolean ele = waitUntilElementDisplayed(nextPageElement);
        System.out.println("Navigating to " + driver.getTitle() + " Page ->" + ele);
        //logger.info("Navigating to "+driver.getTitle()+" Page ->"+ele);
        navigateBack();
        staticWait();

        return ele;
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }


    public void waitForFrameToLoad(String frameName){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
    }

    public boolean isAlertPresent(){
        try
        {
            getDriver().switchTo().alert();
            return true;
        }   // try
        catch (NoAlertPresentException Ex)
        {
            return false;
        }
    }

    public void acceptAlert(){
        driver.switchTo().alert().accept();

    }

    public void waitAndAcceptAlert(int waitTimeInSeconds)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
            Alert alert =wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            logger.info("Alert Displayed: " + alert.getText());
            alert.accept();
            staticWait(3000);
            logger.info("Alert Accepted");

        } catch (Exception e) {
            logger.info("Alert Not Displayed.");
        }
    }

    public String getCssValue(WebElement element, String cssValueName)
    {
        return element.getCssValue(cssValueName);
    }

    public long scrollPosition(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        return  (Long) executor.executeScript("return window.scrollY;");
    }


    public void scrollDown(){
        ((JavascriptExecutor) driver).executeScript("scroll(0,250);");
    }



    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    public Date convertStringToDate(String dateString, String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(dateString);
            logger.info("String converted to date object "+date);
            String val = formatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public int getRandomElementNumberFrom2(List<WebElement> allElement){
        int num = numberOfElement(allElement);
        Random r = new Random();
        return r.nextInt(num-2)+2;
    }

    public void closeCurrentWindow(){
       getDriver().close();
    }

    public String getpageSource(){

        return driver.getPageSource();
    }
    public double textToPrice(String text){
        String priceText = text.toString();
        if(priceText.contains("$")){
            priceText = priceText.replace("$","");
        }
        if(priceText.contains(",")){
            priceText = priceText.replace(",","");
        }
        if(priceText.contains("-")){
            priceText = priceText.replace("-","");
        }if(priceText.contains(" ")){
            priceText = priceText.replace(" ","");
        }
        double price = new Double(priceText);
        return price;
    }
    public void waitForAjax()
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int iterationsIndex=0;
        while (iterationsIndex<=120) // Handle timeout somewhere
        {
            Object ajaxIsComplete = js.executeScript("return window.jQuery != undefined && !!jQuery && jQuery.active == 0");
            if ((boolean)ajaxIsComplete)
            {
                break;
            }
            System.out.println("Waiting..");
            staticWait(500);
            iterationsIndex++;
        }
    }
    public void waitForAjax(String location)
    {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        int iterationsIndex=0;
        while (iterationsIndex<=90) // Handle timeout somewhere
        {
            Object ajaxIsComplete = js.executeScript("return window.jQuery != undefined && !!jQuery && jQuery.active == 0");
            if ((boolean)ajaxIsComplete)
            {
                break;
            }
            System.out.println(location);
            staticWait(500);
            iterationsIndex++;
        }

    }

   /* public static boolean waitForJQueryProcessing(WebDriver driver,
                                                  int timeOutInSeconds) {
        boolean jQcondition = false;
        try {
            new WebDriverWait(driver, timeOutInSeconds) {
            }.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driverObject) {
                    return (Boolean) ((JavascriptExecutor) driverObject)
                            .executeScript("return jQuery.active == 0");
                }
            });
            jQcondition = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return window.jQuery != undefined && jQuery.active === 0");
            return jQcondition;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jQcondition;
    }*/
    public void waitUntilAnimationIsDone(final String cssLocator)
    {
        WebDriverWait wdw = new WebDriverWait(driver, 20);
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                System.out.println("animation test");
                String temp = ((JavascriptExecutor)driver)
                        .executeScript("return jQuery('"+cssLocator+"').is(':animated')").toString();
                return  temp.equalsIgnoreCase("false");
            }
        };

        try{
            wdw.until(expectation);
        }catch(TimeoutException e){
            throw new AssertionError("Element animation is not finished in time. Css locator: " + cssLocator);
        }
    }

    public void waitForTextOnPage(String text){
        int i=0;
        while(i<10){

            if(driver.getPageSource().contains(text)){
                break;
            }
            else{
                System.out.println("waiting for json text : "+text);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }
    public void waitUntilElementInvisible(String element){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(element)));
    }
    public void waitForPageReadyState(String s) throws InterruptedException {
        while(true){
            Object pageState=executeJavaScript(("return document.readyState"));
            if(pageState.toString().equalsIgnoreCase("complete")){
                break;
            }
            else{
                Thread.sleep(500);
                System.out.println(s);
            }
        }
    }

}
