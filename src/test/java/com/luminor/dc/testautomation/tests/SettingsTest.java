package com.luminor.dc.testautomation.tests;

import com.luminor.dc.testautomation.BaseDriver;
import com.luminor.dc.testautomation.screen.ChuckJokesScreen;
import com.luminor.dc.testautomation.screen.MainMenuScreen;
import com.luminor.dc.testautomation.screen.SettingsScreen;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Setting Tests")
@DisplayName("Settings Tests")
class SettingsTest extends BaseDriver {

    @BeforeEach
    public void beforeTest() {
        secondCall.resetAppOnSecondCall();
    }

    @Test
    @DisplayName("Change character first name in settings menu")
    public void changeCharacterFirstNameInSettingMenu() {
        String firstName = "Jason";
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.mainMenuIsDisplayed();
        mainMenuScreen.clickSettingsMenuButton();
        SettingsScreen settingsScreen = new SettingsScreen();
        settingsScreen.settingsPageIsDisplayed();
        settingsScreen.enterCharacterFirstName(firstName);
        settingsScreen.hideKeyboard();
        mainMenuScreen.clickChuckJokesMenuButton();
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.checkFirstNameChange(firstName);
    }

    @Test
    @DisplayName("Change character last name in settings menu")
    public void changeCharacterLastNameInSettingMenu() {
        String lastName = "Statham";
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.mainMenuIsDisplayed();
        mainMenuScreen.clickSettingsMenuButton();
        SettingsScreen settingsScreen = new SettingsScreen();
        settingsScreen.settingsPageIsDisplayed();
        settingsScreen.enterCharacterLastName(lastName);
        settingsScreen.hideKeyboard();
        mainMenuScreen.clickChuckJokesMenuButton();
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.checkLastNameChange(lastName);
    }

    @Test
    @DisplayName("Change character first and last name in settings menu")
    public void changeCharacterFirstAndLastNameInSettingMenu() {
        String firstName = "Jason";
        String lastName = "Statham";
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.mainMenuIsDisplayed();
        mainMenuScreen.clickSettingsMenuButton();
        SettingsScreen settingsScreen = new SettingsScreen();
        settingsScreen.settingsPageIsDisplayed();
        settingsScreen.enterCharacterFirstName(firstName);
        settingsScreen.enterCharacterLastName(lastName);
        settingsScreen.hideKeyboard();
        mainMenuScreen.clickChuckJokesMenuButton();
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.checkFirstNameChange(firstName);
        chuckJokesScreen.checkLastNameChange(lastName);
    }
}
