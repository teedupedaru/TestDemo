package com.luminor.dc.testautomation;

import com.luminor.dc.testautomation.helpers.Appium;
import com.luminor.dc.testautomation.helpers.AppiumDriverFactory;
import com.luminor.dc.testautomation.helpers.SecondCallHelpers;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.net.MalformedURLException;

public class BaseDriver extends Base {

    protected static AppiumController appium;
    protected static AppiumDriver driver;
    protected static SecondCallHelpers secondCall;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        appium = new AppiumController(configuration);
        appium.start();
        driver = AppiumDriverFactory.getInstance();
        secondCall = new SecondCallHelpers();
    }

    @AfterAll
    public static void tearDown() {
        Appium.reset();
        appium.stop();
    }
}
