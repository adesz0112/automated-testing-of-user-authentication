package StepDefinitions;

import Utils.CsvReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.RegistrationPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationStep {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private User user;

    public RegistrationStep() {
        this.driver = Hook.getDriver();
        this.loginPage = new LoginPage(driver);
        this.registrationPage = new RegistrationPage(driver);
    }

    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        driver.get("https://automationexercise.com/login");
        loginPage.acceptConsent();

    }
    @Given("I register a valid user")
    public void i_register_a_valid_user() {
        List<User> users = CsvReader.readUsersFromCsv("src/test/resources/testdata/test_users.csv");
        user = users.get(0);

        loginPage.userNameAndEmail(user);
        loginPage.clickSignUpButton();
        registrationPage.fillPersonalInfo(user);
        registrationPage.fillAddressInfo(user);
    }

    @When("I click the Sign Up button")
    public void i_click_the_sign_up_button() {
        registrationPage.clickOnCreateAccount();
    }
    @Then("I should be registered to the page")
    public void i_should_be_registered_to_the_page() {
        assertTrue(registrationPage.isAccountCreated());
    }
}
