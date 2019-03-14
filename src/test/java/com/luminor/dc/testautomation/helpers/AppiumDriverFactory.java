package com.luminor.dc.testautomation.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumDriverFactory {
    private static AppiumDriver DRIVER;

    public static AppiumDriver getInstance() {
        if (DRIVER == null) {
            throw new RuntimeException("Driver was not initialised.");
        }
        return DRIVER;
    }

    public static void close() {
        if (DRIVER != null) {
            DRIVER.quit();
            DRIVER = null;
        }
    }

    public static AppiumDriver createIOSDriver(URL url, DesiredCapabilities capabilities) {
        DRIVER = new IOSDriver(url, capabilities);
        return DRIVER;
    }

    public static AppiumDriver createAndroidDriver(URL url, DesiredCapabilities capabilities) {
        DRIVER = new AndroidDriver(url, capabilities);
        return DRIVER;
    }
}
