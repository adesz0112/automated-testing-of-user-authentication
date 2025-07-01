package StepDefinitions;

import Utils.CsvReader;
import Utils.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStep {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private User user;
    private final String url = "https://automationexercise.com/login";
    private final String csv = "src/test/resources/testdata/test_users.csv";

    public LoginStep() {
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        this.registrationPage = new RegistrationPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @When("I go to the login page")
    public void i_go_to_the_login_page() {
        driver.get(url);
    }
    @When("I log in with the same credentials")
    public void i_log_in_with_the_same_credentials() {
        List<User> users = CsvReader.readUsersFromCsv(csv);
        int index = Hook.getUserIndex()-1;
        user = users.get(index);
        loginPage.enterEmailForLogin(user.getEmail());
        loginPage.enterPasswordForLogin(user.getPassword());
        loginPage.clickLoginButton();
    }

    @When("I try to log in with the wrong password")
    public void i_try_to_log_in_with_the_wrong_password() {
        List<User> users = CsvReader.readUsersFromCsv(csv);
        int index = Hook.getUserIndex()-1;
        user = users.get(index);
        loginPage.enterEmailForLogin(user.getEmail());
        loginPage.enterPasswordForLogin("wrongPassword");
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        assertTrue(dashboardPage.isUserLoggedIn());
    }

    @Then("I should get an error message about the wrong password")
    public void i_should_get_an_error_message_about_the_wrong_password() {
        assertTrue(loginPage.isLoginErrorVisible());
    }
}
