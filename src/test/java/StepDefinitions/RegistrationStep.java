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

    @Given("I enter name {string} and email {string}")
    public void i_enter_name_and_email(String name, String email) {
        loginPage.enterNameForSignUp(name);
        loginPage.enterEmailForSignUp(email);
        loginPage.clickSignUpButton();
    }
    @Given("I enter password {string} and choose gender {string}")
    public void i_enter_password_and_choose_gender(String password, String gender) {
        registrationPage.enterPassword(password);
        registrationPage.selectTitle(gender);
    }
    @Given("I select date of birth: day {string}, month {string}, year {string}")
    public void i_select_date_of_birth_day_month_year(String day, String month, String year) {

    }
    @Given("I enter firstname {string}, lastname {string}, company {string}")
    public void i_enter_firstname_lastname_company(String firstName, String lastName, String company) {
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterCompany(company);
    }

    @Given("I enter address1 {string}, and address2 {string}")
    public void i_enter_address1_and_address2(String address1, String address2) {
       registrationPage.enterAddress1(address1);
       registrationPage.enterAddress2(address2);
    }

    @Given("the user selects country {string}, and enters state {string}, city {string}")
    public void the_user_selects_country_and_enters_state_city(String country, String state, String city) {
        registrationPage.selectCountry(country);
        registrationPage.enterState(state);
        registrationPage.enterCity(city);
    }
    @Given("the user enters zipcode {string} and phone number {string}")
    public void the_user_enters_zipcode_and_phone_number(String zipcode, String phoneNumber) {
        registrationPage.enterZipcode(zipcode);
        registrationPage.enterMobileNumber(phoneNumber);
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
