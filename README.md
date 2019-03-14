# Test Automation for Mobile Apps

## What are the preconditions?
  
  * Java 8 (Environment Variables/Paths added to system)
  * Appium is installed via NPM
  * Android Studio (for Windows => Phone connection)  
  * Android device (developer mode activated)
  * IntelliJ IDEA (or similar IDE)
 
## Setting up for testing Paxful ICNDB application on Android device:

  * Navigate to src\main\resources\config\config.yaml to edit Android device details on which you want to test on, at the moment set up     with Android 7.0.
  * As the .apk file is added to the project there is no need to install it to the phone, framework will handle it.
  * It is written out below what commands you need to run the full suite (all tests are marked with @test annotations)
  * It is possible to run all test separately, then just right click on a test and choose Run. 
    
## Paxful ICNDB application test coverage overview

  * As the ICNDB application did not have to many functionalities I automated most of them - total 7 tests.
  * JokesTests (src\test\java\com\luminor\dc\testautomation\tests\JokesTest.java) covers Chuck Jokes and My Jokes screens and               SettingsTests (src\test\java\com\luminor\dc\testautomation\tests\SettingsTest.java) covers Setting screen.

## How the project structure looks like?

```
    /
    |-- gradle
    |   |-- wrapper
    |-- apps
    |-- build
    |   |-- allure-results
    |-- src
    |   |-- main
    |       |-- java
    |           |-- api
    |               |-- methods
    |               |-- model
    |           |-- enums
    |           |-- helper
    |               |-- configuration
    |           |-- json
    |           |-- Environment.java
    |       |-- resources
    |           |-- config
    |   |-- test
    |       |-- java
    |           |-- helpers
    |           |-- screen
    |           |-- tests
    |           |-- AppiumController.java
    |           |-- Base.java
    |           |-- BaseDriver.java
    |       |-- resources
    |           |-- allure.properties
    |-- build.gradle
    |-- docker-compose.yml
    |-- README.md
    |-- settings.gradle
```

**gradle/wrapper/** - Gradle wrapper\
**apps/** - Mobile apps for tests\
**build/allure-results/** - Allure logs\
**src/main/java/\*/api/methods/** - Raw API methods (call and return without transformations)\
**src/main/java/\*/api/model/** - API request and response models\
**src/main/java/\*/api/\*Api.java** - public API methods with prepared returns to use in tests\
**src/main/java/\*/enums/** - enums which are used in project\
**src/main/java/\*/helper/** - helpers that not used in tests directly (configuration, file read and etc.)\
**src/main/java/\*/helper/configuration/** - configuration objects used in config.yaml\
**src/main/java/\*/Environment.java** - factory for support services and APIs\
**src/main/resources/config** - configuration files\
**src/main/resources/translation_\*.properties** - legacy translations (should be removed on later stage)\
**src/main/resources/translation_\*.txt** - API to get translations should be used in future\
**src/test/java/\*/helpers/** - helpers to work with Appium\
**src/test/java/\*/screen/** - PageObjects for app (contains elements and steps)\
**src/test/java/\*/tests/** - Test implementation\
**src/test/java/\*/AppiumController.java** - Appium driver provider by configuration\
**src/test/java/\*/Base.java** - Base properties used in every test\
**src/test/java/\*/BaseDriver.java** - TestClass set-up and teardown\
**src/test/resources/allure.properties** - Allure reports configuration file\
**docker-compose.yml** - Docker configuration for containers used in test run\

## API/DB Helper structure

   **api** - package for api helpers\
   **db** - package for db helpers\
   sub-packages:\
   **methods** - Raw CRUD method that does only one action without input/output transformations\
   **model** - request/response models used in helper\
   **classes in root folder** - API/DB methods that exposed to client and used in tests\

## Test creation

### Screens

Screens contains elements with locators with them and steps (actual actions). All actions with elements should be done
with steps.

**Name** - Screen name should describe actual page functionality (eg. LoginScreen, MainScreen)\
**Constructor** - should be placed right after class declaration\
**Elements** - After constructor screen elements with locators block. All elements should be private.\
**Steps** - Actual actions for the elements. Should be described with @Step("Text that is displayed in Allure reports") 
annotation

### Tests

Tests contains test cases for specific functionality.


## Run build

### Run clean build to make sure that everything is okay

Just to make sure that everything is in order run gradle clean build task

**On Mac OS X**
```sh
./gradlew build
```

**On Windows**
```sh
gradlew build
```

### Run tests

**On Mac OS X**
```sh
./gradlew clean test
```

**On Windows**
```sh
gradlew clean test
```

### Generate Allure reports

**On Mac OS X**
```sh
./gradlew allureReport
```

**On Windows**
```sh
gradlew allureReport
```

If you want to serve report to review results you can run serve command

**On Mac OS X**
```sh
./gradlew allureServe
```

**On Windows**
```sh
gradlew allureServe
```



