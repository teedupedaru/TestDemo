package com.luminor.dc.testautomation.screen;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.assertj.core.api.Assertions.assertThat;

import com.luminor.dc.testautomation.Base;
import com.luminor.dc.testautomation.helpers.Appium;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;

public class BaseScreenActions extends Base {

  protected AppiumDriver driver;
  protected String os;

  public BaseScreenActions() {
    this.driver = Appium.getDriver();
    this.os = driver.getCapabilities().getCapability(MobileCapabilityType.PLATFORM_NAME).toString();
  }

  @Step("Hide keyboard")
  public void hideKeyboard() {
    try {
      if (Appium.isAndroid()) {
        driver.hideKeyboard();
      } else if (Appium.isIOS()) {
        List<By> locators = new ArrayList<>();
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        locators.add(By.xpath("//XCUIElementTypeButton[@name='DONE']"));
        locators.add(By.xpath("//XCUIElementTypeToolbar[@name='Toolbar']//XCUIElementTypeButton"));
        for (By locator : locators) {
          if (driver.findElements(locator).size() > 0) {
            driver.findElement(locator).click();
            return;
          }
        }
      }
    } catch (WebDriverException e) {
      System.out.println("Keyboard not displayed");
    } finally {
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
  }

  @Step("Keyboard is displayed")
  public void isKeyboardShown() {
    wait_for_keyboard();
    keyboardDisplayAssert();
  }

  protected void swipeFromElementToLeft(MobileElement element, double distanceMod, int duration) {
    Point elementCenter = element.getCenter();
    new TouchAction(driver)
        .press(point(elementCenter.x, elementCenter.y))
        .waitAction(waitOptions(ofSeconds(duration)))
        .moveTo(point((int) Math.round(elementCenter.x * distanceMod), elementCenter.y))
        .release()
        .perform();
  }

  protected void swipeFromElementToRight(MobileElement element, double distanceMod, int duration) {
    Point elementCenter = element.getCenter();
    new TouchAction(driver)
        .press(point(elementCenter.x, elementCenter.y))
        .waitAction(waitOptions(ofSeconds(duration)))
        .moveTo(point((int) Math.round(elementCenter.x * (1 + distanceMod)), elementCenter.y))
        .release()
        .perform();
  }

  protected void swipeFromLeftToRight(MobileElement element, int duration) {
    Dimension dimension = driver.manage().window().getSize();
    Point elementCenter = element.getCenter();
    new TouchAction(driver)
        .press(point(1, elementCenter.y))
        .waitAction(waitOptions(ofSeconds(duration)))
        .moveTo(point(dimension.width - 1, elementCenter.y))
        .release()
        .perform();
  }

  protected void swipeFromRightToLeft(MobileElement element, int duration) {
    Dimension dimension = driver.manage().window().getSize();
    Point elementCenter = element.getCenter();
    new TouchAction(driver)
        .press(point(dimension.width - 1, elementCenter.y))
        .waitAction(waitOptions(ofMillis(duration)))
        .moveTo(point(1, elementCenter.y))
        .release()
        .perform();
  }

  protected void swipeVertical(
      double startPercentage, double finalPercentage, double anchorPercentage, int duration) {
    Dimension size = driver.manage().window().getSize();
    int anchor = (int) (size.width * anchorPercentage);
    int startPoint = (int) (size.height * startPercentage);
    int endPoint = (int) (size.height * finalPercentage);
    new TouchAction(driver)
        .press(point(anchor, startPoint))
        .waitAction(waitOptions(ofSeconds(duration)))
        .moveTo(point(anchor, endPoint))
        .release()
        .perform();
  }

  protected void scrollToElementByAccessibilityId(String id) {
    int numScrolls = 1;
    int maxScrolls = 10;

    try {
      driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
      if (os.equals("Android")) {
        int max_height = driver.manage().window().getSize().height;
        boolean isFoundTheElement = driver.findElementsByAccessibilityId(id).size() > 0;
        if (isFoundTheElement) {
          double dem =
              (double) driver.findElement(MobileBy.AccessibilityId(id)).getLocation().y
                  / max_height
                  * 100;
          if (dem > 80.00) {
            swipeVertical(0.8, 0.3, 0.5, 1);
          }
        }
        while (!isFoundTheElement && numScrolls < maxScrolls) {
          swipeVertical(0.8, 0.2, 0.5, 1);
          isFoundTheElement = driver.findElementsByAccessibilityId(id).size() > 0;
          numScrolls++;
        }
      } else {
        MobileElement element = (MobileElement) driver.findElement(MobileBy.AccessibilityId(id));
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "true");
        driver.executeScript("mobile:scroll", scrollObject);
      }
    } finally {
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
  }

  // Inner methods

  private void wait_for_keyboard() {
    int millis = 300;
    int count = 10;
    int currentCount = 0;
    while (true) {
      try {
        keyboardDisplayAssert();
        return;
      } catch (WebDriverException | AssertionError e) {
        if (currentCount < count) {
          //Sleep.sleepInMiliseconds(millis);
          currentCount++;
        } else {
          throw e;
        }
      }
    }
  }

  private void keyboardDisplayAssert() {
    switch (os) {
      case "Android":
        assertThat(((AndroidDriver) driver).isKeyboardShown())
            .as("Keyboard should be displayed")
            .isTrue();
        break;
      case "iOS":
        assertThat(((IOSDriver) driver).isKeyboardShown())
            .as("Keyboard should be displayed")
            .isTrue();
        break;
      default:
        throw new NotFoundException("Unknown OS provided: " + os);
    }
  }
}
