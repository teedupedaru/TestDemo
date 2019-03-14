package com.luminor.dc.testautomation.enums;

import java.util.NoSuchElementException;

public enum Runner {
  ANDROID("Android"),
  IOS("iOS"),
  ANDROID_REMOTE("Android_Remote"),
  IOS_REMOTE("iOS_Remote"),
  ANDROID_BROWSERSTACK("Android_BrowserStack"),
  IOS_BROWSERSTACK("iOS_BrowserStack"),
  ANDROID_SAUCELABS("Android_SauceLabs"),
  IOS_SAUCELABS("iOS_SauceLabs"),
  IOS_KOBITON("iOS_Kobiton"),
  ANDROID_KOBITON("Android_Kobiton"),
  ANDROID2("Android farm demo"),
  IOS_FARM_DEMO("Ios farm demo");

  private String value;

  Runner(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Runner getEnum(String value) {
    switch (value) {
      case "Android":
        return ANDROID;
      case "iOS":
        return IOS;
      case "Android_Remote":
        return ANDROID_REMOTE;
      case "iOS_Remote":
        return IOS_REMOTE;
      case "Android_BrowserStack":
        return ANDROID_BROWSERSTACK;
      case "iOS_BrowserStack":
        return IOS_BROWSERSTACK;
      case "Android_SauceLabs":
        return ANDROID_SAUCELABS;
      case "iOS_SauceLabs":
        return IOS_SAUCELABS;
      case "iOS_Kobiton":
        return IOS_KOBITON;
      case "Android_Kobiton":
        return ANDROID_KOBITON;
      case "ANDROID2":
        return ANDROID2;
      case "IOS_FARM_DEMO":
        return IOS_FARM_DEMO;
      default:
        throw new NoSuchElementException("Unknown runner is provided");
    }
  }
}
