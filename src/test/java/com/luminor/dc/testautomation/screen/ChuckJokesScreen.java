package com.luminor.dc.testautomation.screen;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChuckJokesScreen extends BaseScreenActions {

    public ChuckJokesScreen() {
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.of(5, ChronoUnit.SECONDS)), this);
    }

    @AndroidFindBy(id = "ee.paxful.icndb:id/shareButton")
    private MobileElement shareButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/text")
    private MobileElement chuckNorrisJokesList;

    @AndroidFindBy(id = "ee.paxful.icndb:id/likeButton")
    private MobileElement likeButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/dislikeButton")
    private MobileElement dislikeButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/dislikeButton")
    private List<MobileElement> dislikeButtonList;


    @Step("Chuck Jokes page is displayed")
    public void chuckJokesPageIsDisplayed() {
        assertThat(shareButton.isDisplayed()).as("Chuck Jokes page should be visible").isTrue();
    }

    @Step("Verify first name change")
    public void checkFirstNameChange(String firstName) {
        assertThat(chuckNorrisJokesList.getText())
                .contains(firstName);
    }

    @Step("Verify first last change")
    public void checkLastNameChange(String lastName) {
        assertThat(chuckNorrisJokesList.getText())
                .contains(lastName);
    }

    @Step("Click like button")
    public void clickJokeLikeButton() {
        likeButton.click();
    }

    @Step("Verify dislike button is displayed")
    public void dislikeButtonIsDisplayed() {
        assertThat(dislikeButton.isDisplayed()).as("Dislike button should be visible").isTrue();
    }

    @Step("Click dislike button")
    public void clickJokeDislikeButton() {
        dislikeButton.click();
    }

    @Step("Verify dislike button is not displayed")
    public void dislikeButtonIsNotPresent() {
        assertThat(dislikeButtonList.size() < 1).as("Dislike button should not be visible").isTrue();
    }
}
