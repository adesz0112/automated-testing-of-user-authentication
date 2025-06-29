package StepDefinitions;

import Utils.CsvReader;
import io.cucumber.java.en.Given;
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

    public LoginStep() {
        this.driver = Hook.getDriver();
        this.loginPage = new LoginPage(driver);
        this.registrationPage = new RegistrationPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    @When("I go to the login page")
    public void i_go_to_the_login_page() {
        driver.get("https://automationexercise.com/login");
    }
    @When("I log in with the same credentials")
    public void i_log_in_with_the_same_credentials() {
        User user = Hook.registeredUsers.get(Hook.registeredUsers.size() - 1);
        loginPage.enterEmailForLogin(user.getEmail());
        loginPage.enterPasswordForLogin(user.getPassword());
        loginPage.clickLoginButton();
    }
    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        assertTrue(loginPage.isUserLoggedIn());
    }
}
