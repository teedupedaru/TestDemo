package com.luminor.dc.testautomation.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class MainMenuScreen extends BaseScreenActions {

    public MainMenuScreen() {
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.of(5, ChronoUnit.SECONDS)), this);
    }

    @AndroidFindBy(id = "ee.paxful.icndb:id/menu_chuck_jokes")
    private MobileElement chuckJokesButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/menu_my_jokes")
    private MobileElement myJokesButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/menu_settings")
    private MobileElement settingButton;


    @Step("Main menu is displayed")
    public void mainMenuIsDisplayed() {
        assertThat(chuckJokesButton.isDisplayed()).as("Chuck Jokes should be visible").isTrue();
        assertThat(myJokesButton.isDisplayed()).as("My Jokes should be visible").isTrue();
        assertThat(settingButton.isDisplayed()).as("Settings should be visible").isTrue();
    }

    @Step("Click Chuck Jokes Menu Button")
    public void clickChuckJokesMenuButton() {
        chuckJokesButton.click();
    }

    @Step("Click My Jokes Menu Button")
    public void clickMyJokesMenuButton() {
        myJokesButton.click();
    }

    @Step("Click Setting Menu Button")
    public void clickSettingsMenuButton() {
        settingButton.click();
    }
}
