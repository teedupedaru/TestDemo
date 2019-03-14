package com.luminor.dc.testautomation.enums;

public enum Users {
  USER_LT_1("000002", "reach", "lt", "en"),
  USER_LT_2("autotest", "autotest159", "lt", "en"),
  USER_EE_1("111111", "young", "ee", "en"),
  USER_LV_1("000001", "young", "lv", "en"),
  USER_LV_2("000003", "client", "lv", "en"),
  USER_LV_3("222222", "young", "lv", "en");

  private final Object[] values;

  Users(Object... vals) {
    this.values = vals;
  }

  public String getUsername() {
    return (String) values[0];
  }

  public String getPassword() {
    return (String) values[1];
  }

  public String getCountry() {
    return (String) values[2];
  }

  public String getLanguage() {
    return (String) values[3];
  }
}
