package ui.support;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

import java.net.URISyntaxException;

import static com.thoughtworks.selenium.SeleneseTestBase.fail;


public class EnvironmentConfig {
    public static String configFile;
    public static final String APP_URL = "AppUrl";
    public static final String ENV_PATH = "Environment/";
    private static XMLConfiguration config;

    public static String getApplicationUrl() {
        getConfig();
        return config.getString(ENV_PATH + APP_URL);
    }





    private static void getConfig() {

        configFile = getEnvironmentProfile();
        if (configFile != null) {
            configFile = configFile + ".xml";

            try {
                config = new XMLConfiguration(loadAndGetResourceLocation(configFile));
            } catch (ConfigurationException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            config.setExpressionEngine(new XPathExpressionEngine());
        } else
            fail("As a test, I didnt get any environment config. Please pass a environment config to me");
    }



    public static String loadAndGetResourceLocation(String fileName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        return classLoader.getResource(fileName).toString();
    }

    public static String getEnvironmentProfile() {
        try {
            return Config.getEnvironmentProfile();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}