package com.luminor.dc.testautomation;

import com.luminor.dc.testautomation.enums.Runner;
import com.luminor.dc.testautomation.helper.configuration.TestConfiguration;
import com.luminor.dc.testautomation.helpers.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumController {

    private TestConfiguration configuration;
    private Runner runner;
    private static AppiumDriverLocalService service;
    private DesiredCapabilities overrideCapabilities;
    private File app;

    /**
     * Create Appium controller instance with provided configuration in configuration file
     *
     * @param configuration configuration file params instance
     */
    public AppiumController(TestConfiguration configuration) {
        this.configuration = configuration;
        this.runner = configuration.currentRunner;
    }

    /**
     * Create Appium controller instance with provided configuration in configuration file and
     * override test runner
     *
     * @param configuration configuration file params instance
     * @param runner        test runner
     */
    public AppiumController(TestConfiguration configuration, Runner runner) {
        this.configuration = configuration;
        this.runner = runner;
    }

    /**
     * Create Appium controller instance with provided configuration in configuration file with
     * override or added information from additional desired capabilities instance
     *
     * @param configuration        configuration file params instance
     * @param overrideCapabilities capabilities to override or add
     */
    public AppiumController(
            TestConfiguration configuration, DesiredCapabilities overrideCapabilities) {
        this.configuration = configuration;
        this.runner = configuration.currentRunner;
        this.overrideCapabilities = overrideCapabilities;
    }

    /**
     * Create Appium controller instance with provided configuration in configuration file with
     * override or added information from additional desired capabilities instance and override test
     * runner
     *
     * @param configuration        configuration file params instance
     * @param runner               test runner
     * @param overrideCapabilities capabilities to override or add
     */
    public AppiumController(
            TestConfiguration configuration, Runner runner, DesiredCapabilities overrideCapabilities) {
        this.configuration = configuration;
        this.runner = runner;
        this.overrideCapabilities = overrideCapabilities;
    }

    /**
     * Start Appium driver
     *
     * @throws MalformedURLException error handling
     */
    public void start() throws MalformedURLException {
        File classpathRoot;
        File appDir;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (runner) {
            case IOS:
                service = AppiumDriverLocalService.buildDefaultService();
                service.start();
                classpathRoot = new File(System.getProperty("user.dir"));
                appDir = new File(classpathRoot, configuration.local.path_to_app);
                app = new File(appDir, configuration.local.appIOSName);
                capabilities.setCapability(
                        MobileCapabilityType.PLATFORM_NAME, configuration.deviceiOS.platform_name);
                capabilities.setCapability(
                        MobileCapabilityType.DEVICE_NAME, configuration.deviceiOS.device_name);
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                capabilities.setCapability(
                        MobileCapabilityType.PLATFORM_VERSION, configuration.deviceiOS.platform_version);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability("simpleIsVisibleCheck", true);
                capabilities.merge(overrideCapabilities);
                AppiumDriverFactory.createIOSDriver(service.getUrl(), capabilities);
                break;
            case ANDROID:
                service = AppiumDriverLocalService.buildDefaultService();
                service.start();
                classpathRoot = new File(System.getProperty("user.dir"));
                appDir = new File(classpathRoot, configuration.local.path_to_app);
                app = new File(appDir, configuration.local.appAndroidName);
                capabilities.setCapability(
                        MobileCapabilityType.DEVICE_NAME, configuration.deviceAndroid.device_name);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2"); => For Android 8.0+
                capabilities.merge(overrideCapabilities);
                AppiumDriverFactory.createAndroidDriver(
                        new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
                break;

            default:
                throw new NoSuchElementException("Unknown runner provided");
        }
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * iOS install app
     */
    public void iOSInstall() {
        if (getDriver()
                .getCapabilities()
                .getCapability(MobileCapabilityType.PLATFORM_NAME)
                .toString()
                .equals("iOS")) {
            getDriver().installApp(app.getAbsolutePath());
            getDriver().launchApp();
        }
    }

    /**
     * Stop Appium driver
     */
    public void stop() {
        AppiumDriverFactory.close();
        if (service != null) {
            service.stop();
            service = null;
        }
    }

    private AppiumDriver getDriver() {
        return AppiumDriverFactory.getInstance();
    }
}
