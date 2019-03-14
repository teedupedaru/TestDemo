package com.luminor.dc.testautomation.helpers;

public class SecondCallHelpers {

  private String launchApp = "";
  private String resetApp = "";

  public SecondCallHelpers() {}

  public void launchAppOnSecondCall() {
    String callerClassName = new Exception().getStackTrace()[1].getClassName();
    if (launchApp.equals(callerClassName)) {
      Appium.getDriver().launchApp();
    }
    launchApp = callerClassName;
  }

  public void resetAppOnSecondCall() {
    String callerClassName = new Exception().getStackTrace()[1].getClassName();
    if (resetApp.equals(callerClassName)) {
      Appium.reset();
    }
    resetApp = callerClassName;
  }
}
