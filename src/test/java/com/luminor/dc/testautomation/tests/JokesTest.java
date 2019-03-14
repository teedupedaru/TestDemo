package com.luminor.dc.testautomation.tests;

import com.luminor.dc.testautomation.BaseDriver;
import com.luminor.dc.testautomation.screen.ChuckJokesScreen;
import com.luminor.dc.testautomation.screen.MainMenuScreen;
import com.luminor.dc.testautomation.screen.MyJokesScreen;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Jokes Tests")
@DisplayName("Jokes Tests")
class JokesTest extends BaseDriver {

    @BeforeEach
    public void beforeTest() {
        secondCall.resetAppOnSecondCall();
    }

    @Test
    @DisplayName("Liking Jokes Test")
    public void likingJokesTest() {
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.clickJokeLikeButton();
        chuckJokesScreen.dislikeButtonIsDisplayed();
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.mainMenuIsDisplayed();
        mainMenuScreen.clickMyJokesMenuButton();
        MyJokesScreen myJokesScreen = new MyJokesScreen();
        myJokesScreen.myJokesPageIsDisplayed();
        myJokesScreen.isNewJokeDisplayed();
        myJokesScreen.clickMyJokesDeleteButton();
        myJokesScreen.noJokesInMyJokesTabDisplayed();
        mainMenuScreen.mainMenuIsDisplayed();
        mainMenuScreen.clickChuckJokesMenuButton();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.dislikeButtonIsNotPresent();
    }

    @Test
    @DisplayName("Disliking Jokes Test")
    public void dislikingJokesTest() {
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        chuckJokesScreen.clickJokeLikeButton();
        chuckJokesScreen.dislikeButtonIsDisplayed();
        chuckJokesScreen.clickJokeDislikeButton();
        chuckJokesScreen.dislikeButtonIsNotPresent();
    }

    @Test
    @DisplayName("Adding new joke to My Jokes Screen")
    public void addingNewJokeTest() {
        String newJoke = "Jason Statham is a joke in Chuck Norris's eyes.";
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.clickMyJokesMenuButton();
        MyJokesScreen myJokesScreen = new MyJokesScreen();
        myJokesScreen.myJokesPageIsDisplayed();
        myJokesScreen.clickAddNewJokeButton();
        myJokesScreen.addingNewJokeWindowIsDisplayed();
        myJokesScreen.enterNewJoke(newJoke);
        myJokesScreen.saveNewJokeIsDisplayed();
        myJokesScreen.clickSaveNewJoke();
        myJokesScreen.isNewJokeDisplayed();
        myJokesScreen.clickMyJokesDeleteButton();
        myJokesScreen.deleteButtonIsNotPresent();
        mainMenuScreen.clickChuckJokesMenuButton();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
    }

    @Test
    @DisplayName("Canceling Adding new joke to My Jokes Screen")
    public void cancelingAddingNewJokeTest() {
        String newJoke = "Jason Statham is a joke in Chuck Norris's eyes.";
        ChuckJokesScreen chuckJokesScreen = new ChuckJokesScreen();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
        MainMenuScreen mainMenuScreen = new MainMenuScreen();
        mainMenuScreen.clickMyJokesMenuButton();
        MyJokesScreen myJokesScreen = new MyJokesScreen();
        myJokesScreen.myJokesPageIsDisplayed();
        myJokesScreen.clickAddNewJokeButton();
        myJokesScreen.addingNewJokeWindowIsDisplayed();
        myJokesScreen.enterNewJoke(newJoke);
        myJokesScreen.cancelNewJokeIsDisplayed();
        myJokesScreen.clickCancelNewJoke();
        myJokesScreen.deleteButtonIsNotPresent();
        mainMenuScreen.clickChuckJokesMenuButton();
        chuckJokesScreen.chuckJokesPageIsDisplayed();
    }
}
