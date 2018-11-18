package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class Stepdefs {
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^command new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^incorrect username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void incorrect_username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @Then("^a valid username \"([^\"]*)\" is entered$")
    public void validUserNameIsEntered(String username) {
        enterUsername(username);
    }

    @Then("^invalid username \"([^\"]*)\" is entered$")
    public void invalidUserNameIsEntered(String username) {
        enterUsername(username);
    }

    @Then("^password \"([^\"]*)\" is entered and matching password confirmation are entered$")
    public void passwordAndMatchingPasswordConfirmationAreEntered(String password) throws Throwable {
        enterPassword(password);
        enterPasswordConfirmation(password);
    }

    @Then("^invalid password \"([^\"]*)\" is entered and matching password confirmation are entered$")
    public void invalidPasswordAndMatchingPasswordConfirmationAreEntered(String password) throws Throwable {
        enterPassword(password);
        enterPasswordConfirmation(password);
    }

    @Then("^password \"([^\"]*)\" is entered and invalid password confirmation are entered$")
    public void passwordAndInvalidPasswordConfirmationAreEntered(String password) throws Throwable {
        enterPassword(password);
        enterPasswordConfirmation(password.substring(1));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */

    private void pageHasContent(String content) {
        assertTrue("content not found " + content, driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        enterUsername(username);
        enterPassword(password);
        pressSubmit("login");
    }

    private void pressSubmit(String item) {
        WebElement element = driver.findElement(By.name(item));
        element.submit();
    }

    private void enterPassword(String password) {
        enterField(password, "password");
    }

    private void enterPasswordConfirmation(String password) {
        enterField(password, "passwordConfirmation");
    }

    private void enterUsername(String username) {
        enterField(username, "username");
    }

    private void enterField(String toEnter, String name) {
        WebElement element = driver.findElement(By.name(name));
        element.sendKeys(toEnter);
    }

    @Then("^a new user is created$")
    public void aNewUserIsCreated() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void userIsNotCreatedAndErrorIsReported(String error) throws Throwable {
        pageHasContent(error);
    }

    @And("^submit is clicked$")
    public void submitIsClicked() throws Throwable {
        pressSubmit("signup");
    }
}
