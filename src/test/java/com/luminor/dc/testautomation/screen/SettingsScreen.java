package com.luminor.dc.testautomation.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SettingsScreen extends BaseScreenActions {

    public SettingsScreen() {
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.of(5, ChronoUnit.SECONDS)), this);
    }

    @AndroidFindBy(id = "ee.paxful.icndb:id/characterFirstName")
    private MobileElement firstNameField;

    @AndroidFindBy(id = "ee.paxful.icndb:id/characterLastName")
    private MobileElement lasttNameField;

    @AndroidFindBy(id = "ee.paxful.icndb:id/shakeBehaviourSwitch")
    private MobileElement shakeBehaviourSwitch;


    @Step("Settings page is displayed")
    public void settingsPageIsDisplayed() {
        assertThat(firstNameField.isDisplayed()).as("Settings page should be visible").isTrue();
        assertThat(lasttNameField.isDisplayed()).as("Settings page should be visible").isTrue();
        assertThat(shakeBehaviourSwitch.isDisplayed()).as("Settings page should be visible").isTrue();
    }

    @Step("Enter character first name")
    public void enterCharacterFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    @Step("Enter character last name")
    public void enterCharacterLastName(String lastName) {
        lasttNameField.clear();
        lasttNameField.sendKeys(lastName);
    }
}
