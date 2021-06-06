package com.bmsoft.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SetupDriver {

    private static final String DRIVER_PROP_FILE = "src/test/resources/configurations/basic-config.properties";
    private static Properties props;

    public static WebDriver getDriver(WebDriver driver, String browser, String baseUrl) throws IOException,
            FileNotFoundException, Exception {

        props = new Properties();
        props.load(new FileInputStream(DRIVER_PROP_FILE));

        if (isWindows()) {
            if (browser.equalsIgnoreCase("firefox")) {
                System.setProperty("webdriver.gecko.driver", props.getProperty(Constants.FIREFOX_DRIVER_WIN));
                driver = new FirefoxDriver();
            }
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", props.getProperty(Constants.CHROME_DRIVER_WIN));
                driver = new ChromeDriver();
            }
            if (browser.equalsIgnoreCase("msedge")) {
                System.setProperty("webdriver.edge.driver", props.getProperty(Constants.EDGE_DRIVER_WIN));
                driver = new EdgeDriver();
            }

        } else if (isMac()) {
            if (browser.equalsIgnoreCase("firefox")) {
                //FirefoxOptions options = new FirefoxOptions();
                //options.setCapability(CapabilityType.BROWSER_VERSION, 48);
                System.setProperty("webdriver.gecko.driver", props.getProperty(Constants.FIREFOX_DRIVER_MAC));
                driver = new FirefoxDriver();

            }
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", props.getProperty(Constants.CHROME_DRIVER_MAC));
                driver = new ChromeDriver();
            }

        }else if(isUnix()){
            if (browser.equalsIgnoreCase("firefox")) {
                //FirefoxOptions options = new FirefoxOptions();
                //options.setCapability(CapabilityType.BROWSER_VERSION, 48);
                System.setProperty("webdriver.gecko.driver", props.getProperty(Constants.FIREFOX_DRIVER_UNIX));
                driver = new FirefoxDriver();

            }
            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", props.getProperty(Constants.CHROME_DRIVER_UNIX));
                driver = new ChromeDriver();
            }
        }

        driver.manage().window().maximize();
        return driver;
    }

    private static boolean isWindows() {
        String os = System.getProperty("os.name");
        return os.startsWith("Windows");
    }

    private static boolean isMac() {
        String os = System.getProperty("os.name");
        return os.startsWith("Mac");
    }

    private static boolean isUnix() {
        String os = System.getProperty("os.name");
        return os.contains("nix") || os.contains("nux") || os.contains("aix");
    }
}
