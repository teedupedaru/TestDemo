package com.luminor.dc.testautomation.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Appium {

  public static AppiumDriver getDriver() {
    return AppiumDriverFactory.getInstance();
  }

  public static boolean isAndroid() {
    return getDriver()
        .getCapabilities()
        .getCapability(MobileCapabilityType.PLATFORM_NAME)
        .toString()
        .equals("Android");
  }

  public static boolean isIOS() {
    return getDriver()
        .getCapabilities()
        .getCapability(MobileCapabilityType.PLATFORM_NAME)
        .toString()
        .equals("iOS");
  }

  /** Reset application */
  public static void reset() {
    if (isIOS()) {
      getDriver().removeApp("lt.ba.luminor.app");
      getDriver().installApp(getAppPath());
      getDriver().launchApp();
    } else {
      getDriver().resetApp();
    }
  }

  public static String getAppPath() {
    return getDriver().getCapabilities().getCapability(MobileCapabilityType.APP).toString();
  }
}
