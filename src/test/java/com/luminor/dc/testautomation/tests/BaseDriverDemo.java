package com.luminor.dc.testautomation.tests;

import com.luminor.dc.testautomation.AppiumController;
import com.luminor.dc.testautomation.helpers.Appium;
import com.luminor.dc.testautomation.helpers.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseDriverDemo extends BaseDemo {

  protected static AppiumController appium;
  protected static AppiumDriver driver;


  @BeforeAll
  public static void setUp() throws MalformedURLException {
    appium = new AppiumController(configuration);
    appium.start();
    driver = AppiumDriverFactory.getInstance();
  }

  @AfterAll
  public static void tearDown() {
    appium.stop();
  }
}
