package com.luminor.dc.testautomation.singletons;

import com.luminor.dc.testautomation.enums.Users;

public final class TestData {

  private static TestData instance;
  private Users user;

  private TestData() {}

  public static TestData getInstance() {
    if (instance == null) {
      instance = new TestData();
    }
    return instance;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }
}
