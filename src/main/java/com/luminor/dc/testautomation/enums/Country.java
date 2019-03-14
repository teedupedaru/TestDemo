package com.luminor.dc.testautomation.enums;

import java.util.NoSuchElementException;

public enum Country {
  LT,
  LV,
  EE;

  public static Country getCountry(String locale) {
    switch (locale.toLowerCase()) {
      case "en":
      case "lt":
        return Country.LT;
      case "lv":
        return Country.LV;
      case "ee":
        return Country.EE;
      default:
        throw new NoSuchElementException("locale not defined in country enum: " + locale);
    }
  }
}
