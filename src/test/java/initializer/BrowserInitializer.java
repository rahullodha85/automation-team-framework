package initializer;


import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.reports.utils.Utils;
import atu.testng.selenium.reports.CaptureScreen;
import junit.framework.Assert;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import ui.UiBase;
import ui.support.AppStateVariables;
import ui.support.Config;
import ui.utils.RegexSearch;
import ui.utils.browser.Browser;

import ui.utils.browser.ChromeDriverDownloader;



import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;




@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
        MethodListener.class })
public class BrowserInitializer
{

    {

        String propertiesFile = Config.getReportPropertiesFile();
        System.setProperty("atu.reporter.config", propertiesFile);

    }


    public WebDriver driver;
    private static final String BROWSER_SIZE = "browserSize";
    private static final String PLATFORM = "platform";
    private static final String BROWSER_NAME = "browserName";
    private static final String BROWSER_PARAMETERS = "browserParameters";
    private static final String BROWSER_VERSION = "version";

    private static final Logger logger = Logger.getLogger(BrowserInitializer.class);
    private HashMap<String, Object> browserCapabilities = new HashMap<String, Object>();
    private Browser browser;

    public UiBase base= new UiBase();
    public AppStateVariables appStateVariables= new AppStateVariables();

    List<Thread> threadCollection = new ArrayList<Thread>();
    File chromedriver;


    public BrowserInitializer(){

        ATUReports.indexPageDescription = "HBC Automation";

    }

    public void initializeDriver() throws Exception {
        try {
            setBrowserCapabilities();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        browser = setDesiredCapabilities(capabilities);
        driver = getWebDriverWithCapabilities(capabilities);
        //setWindowSize(driver);
        setTimeOuts(driver);
        base.setDriver(driver);
        setDriver(driver);

        shutdownHook(driver);
    }

    public WebDriver getWebDriverWithCapabilities(Capabilities capabilities)   {

        try {
            if(Config.getExecutionType().equalsIgnoreCase("grid")){
                RemoteWebDriver driver = new RemoteWebDriver(new URL(Config.getGridUrl()), capabilities);
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            }
            else if(Config.getExecutionType().equalsIgnoreCase("local")){
                if(Config.getBrowser().contains("firefox"))
                {
                    return new FirefoxDriver();
                }
                else if(Config.getBrowser().contains("chrome"))
                {
                   System.setProperty("webdriver.chrome.driver",chromedriver.getAbsolutePath());
                   return new ChromeDriver(capabilities);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }



    private void setBrowserCapabilities() throws Exception {

        browserCapabilities.put(PLATFORM, Config.getOsPlatform());
        browserCapabilities.put(BROWSER_NAME, Config.getBrowser());
        browserCapabilities.put(BROWSER_PARAMETERS, Config.getBrowserParameters());
        browserCapabilities.put(BROWSER_VERSION, Config.getBrowserVersion());

    }

    private Browser setDesiredCapabilities(DesiredCapabilities capabilities) throws Exception {
        browser = Browser.getBrowser(browserCapabilities.get(BROWSER_NAME).toString());


            capabilities.setBrowserName(browser.name().toLowerCase());


        setBrowserParameters(capabilities);
        if (browser.name() == "CHROME") {
            setChromeCapabilities(capabilities);
            base.setBrowserType("CHROME");
        }
        if (browser.name() == "FIREFOX") {
            base.setBrowserType("FIREFOX");
        }

        return browser;
    }

    private void setChromeCapabilities(DesiredCapabilities capabilities) throws Exception {
        capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
        if(!Config.getExecutionType().equalsIgnoreCase("grid")) {
            chromedriver = new ChromeDriverDownloader().downloadAndExtract();
            capabilities.setCapability(CHROME_DRIVER_EXE_PROPERTY, chromedriver.getAbsolutePath());
        }

    }



    private void setBrowserParameters(DesiredCapabilities capabilities) {
        if (browserCapabilities.get(BROWSER_PARAMETERS).toString().length() > 0) {
            String[] parameters = browserCapabilities.get(BROWSER_PARAMETERS).toString().split(";");
            setBrowserParameters(capabilities, parameters);
        }
    }

    private void setBrowserParameters(DesiredCapabilities capabilities, String[] parameters) {
        for (String parameter : parameters) {
            if (!parameter.isEmpty()) {
                String[] key_value = parameter.split(":");
                capabilities.setCapability(key_value[0], key_value[1]);
            }
        }
    }



    public void setDriver(WebDriver driver)
    {
        this.driver=driver;
    }

    public WebDriver getDriver() {

        return this.driver;
    }
    private void setTimeOuts(WebDriver driver) {

        driver.manage().timeouts().implicitlyWait(UiBase.getDefaultImplicitTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(UiBase.getMaxPageLoadTimeout(), TimeUnit.SECONDS);
    }

    private void setWindowSize(WebDriver driver) {
        driver.manage().window().maximize();
    }

    private void shutdownHook(WebDriver driver) {
        final WebDriver finalDriver = driver;


        final Thread thread = new Thread() {
            public void run() {
                try {
                        finalDriver.quit();

                    System.out.println("All Tests Completed");
                } catch (Exception e) {
                    final Thread currentThread = Thread.currentThread();
                    logger.info("Thread Name is" + currentThread.getName() + "and " + currentThread.isAlive());
                    try {

                            finalDriver.quit();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        };
        threadCollection.add(thread);
        Runtime.getRuntime().addShutdownHook(thread);
    }



    public void setAuthorInfo(String authorInfo)
    {
        ATUReports.setAuthorInfo(authorInfo, Utils.getCurrentTime(), "1.0");
    }

    public void setRequirementCoverage(String requirementCoverage)
    {
        ATUReports.setTestCaseReqCoverage(requirementCoverage);
    }

    public void AddInfoStep(String infoDescription)
    {
        ATUReports.add(infoDescription, LogAs.INFO, new CaptureScreen(
                CaptureScreen.ScreenshotOf.BROWSER_PAGE));
    }

    public void AssertFailAndContinue(boolean result,String stepDescription) throws Exception
    {
        try {
            ATUReports.setWebDriver(driver);
            stepDescription=stepDescription.replaceAll(":","").replaceAll("\\*","").replaceAll("\"","").replaceAll("<","")
                    .replaceAll(">","").replaceAll("\\?","").replaceAll("\\/","").replaceAll("\\\\","").replaceAll("\\|","");
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
            String methodName = e.getMethodName();
            if (!result) {
                ATUReports.add(stepDescription, LogAs.FAILED, new CaptureScreen(
                        CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                takeSnapShot(driver, System.getProperty("user.dir") + "\\" + "target\\screenshots\\" + methodName + "\\" + stepDescription + ".png");
            } else {
                ATUReports.add(stepDescription, LogAs.PASSED, new CaptureScreen(
                        CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                takeSnapShot(driver,System.getProperty("user.dir")+"\\"+"target\\screenshots\\"+methodName+"\\"+stepDescription+".png");
            }
        }
        catch(Exception e)
        {

        }
    }

    public void AssertFail(boolean result,String stepDescription)
    {
        try {
            ATUReports.setWebDriver(driver);
            stepDescription=stepDescription.replaceAll(":","").replaceAll("\\*","").replaceAll("\"","").replaceAll("<","")
                    .replaceAll(">","").replaceAll("\\?","").replaceAll("\\/","").replaceAll("\\\\","").replaceAll("\\|","");
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
            String methodName = e.getMethodName();
            if (!result) {
                ATUReports.add(stepDescription, LogAs.FAILED, new CaptureScreen(
                        CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                takeSnapShot(driver, System.getProperty("user.dir") + "\\" + "target\\screenshots\\" + methodName + "\\" + stepDescription + ".png");
                Assert.fail(stepDescription);
            } else {
                ATUReports.add(stepDescription, LogAs.PASSED, new CaptureScreen(
                        CaptureScreen.ScreenshotOf.BROWSER_PAGE));
                takeSnapShot(driver,System.getProperty("user.dir")+"\\"+"target\\screenshots\\"+methodName+"\\"+stepDescription+".png");
            }
        }
        catch (Exception e)
        {
            Assert.fail(stepDescription);
        }
    }

    public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        String screenShotFilePath=fileWithPath;

        //  screenShotFilePath.replaceAll(":","").replaceAll("")

        File DestFile=new File(fileWithPath);

        //Copy file at destination

        try {
            org.apache.commons.io.FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.getMessage();
        }


    }

}
