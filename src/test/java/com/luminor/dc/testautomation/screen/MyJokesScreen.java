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

public class MyJokesScreen extends BaseScreenActions {

    public MyJokesScreen() {
        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.of(5, ChronoUnit.SECONDS)), this);
    }

    @AndroidFindBy(id = "ee.paxful.icndb:id/deleteButton")
    private MobileElement deleteButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/addFab")
    private MobileElement addNewJokeButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/editText")
    private MobileElement editNewJokeField;

    @AndroidFindBy(id = "ee.paxful.icndb:id/cancelButton")
    private MobileElement cancelAddNewJokeButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/saveButton")
    private MobileElement saveNewJokeButton;

    @AndroidFindBy(id = "ee.paxful.icndb:id/emptyView")
    private MobileElement myJokesListIsEmptyText;

    @AndroidFindBy(id = "ee.paxful.icndb:id/deleteButton")
    private List<MobileElement> deleteButtonList;


    @Step("My Jokes page is displayed")
    public void myJokesPageIsDisplayed() {
        assertThat(addNewJokeButton.isDisplayed()).as("My Jokes page should be visible").isTrue();
    }

    @Step("On My Jokes page new joke is displayed")
    public void isNewJokeDisplayed() {
        assertThat(deleteButton.isDisplayed()).as("New Joke added to My Jokes page").isTrue();
    }

    @Step("Click delete button")
    public void clickMyJokesDeleteButton() {
        deleteButton.click();
    }

    @Step("On My Jokes page Adding New Joke Button is displayed")
    public void noJokesInMyJokesTabDisplayed() {
        assertThat(myJokesListIsEmptyText.isDisplayed()).as("No Jokes in My Jokes list displayed").isTrue();
    }

    @Step("Click add new joke button")
    public void clickAddNewJokeButton() {
        addNewJokeButton.click();
    }

    @Step("Adding New Joke window is displayed")
    public void addingNewJokeWindowIsDisplayed() {
        assertThat(editNewJokeField.isDisplayed()).as("Add New Joke window is displayed").isTrue();
    }

    @Step("Enter new joke")
    public void enterNewJoke(String newJoke) {
        editNewJokeField.clear();
        editNewJokeField.sendKeys(newJoke);
    }

    @Step("Save New Joke button is displayed")
    public void saveNewJokeIsDisplayed() {
        assertThat(saveNewJokeButton.isDisplayed()).as("Save New Joke button is displayed").isTrue();
    }

    @Step("Click Save New Joke button")
    public void clickSaveNewJoke() {
        saveNewJokeButton.click();
    }

    @Step("Verify delete button is not displayed")
    public void deleteButtonIsNotPresent() {
        assertThat(deleteButtonList.size() < 1).as("Dislike button should not be visible").isTrue();
    }

    @Step("Cancel New Joke button is displayed")
    public void cancelNewJokeIsDisplayed() {
        assertThat(cancelAddNewJokeButton.isDisplayed()).as("Cancel New Joke button is displayed").isTrue();
    }

    @Step("Click Cancel New Joke button")
    public void clickCancelNewJoke() {
        cancelAddNewJokeButton.click();
    }
}
