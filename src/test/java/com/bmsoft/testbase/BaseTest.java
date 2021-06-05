package com.bmsoft.testbase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.util.Properties;

public class BaseTest {

    protected static String baseUrl, browser, email, password;
    protected static long implicitWaitTimeout;
    protected Properties props;

    public BaseTest() {
    }

    @BeforeSuite
    public void InitSuite() {
        try {
            getProperties();
            baseUrl = props.getProperty("baseUrl");
            browser = props.getProperty("defaultBrowser");
            implicitWaitTimeout = Long.parseLong(props.getProperty("implicitWaitTimeout"));
            email = props.getProperty("email");
            password = props.getProperty("password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void FinalizeSuite() {
    }

    private void getProperties() {
        InetAddress host = null;
        props = new Properties();
        try {
            host = InetAddress.getLocalHost();
            /*if(host.getHostName().startsWith("qa")) {
                props.load(new FileInputStream("./src/test/resources/configurations/env-qa.properties"));
            } else{
                props.load(new FileInputStream("./src/test/resources/configurations/env-regression.properties"));
            }*/
            props.load(new FileInputStream("./src/test/resources/configurations/env-qa.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
