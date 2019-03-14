package com.luminor.dc.testautomation.enums;

public enum Page {
  FRONT_PAGE("front_page"),
  LEGAL("legal");

  private String value;

  Page(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
