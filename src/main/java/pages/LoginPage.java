package pages;

import model.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement nameInput;
    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailInput;
    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signUpButton;
    @FindBy(xpath = "//p[contains(@class, 'fc-button-label') and text()='Beleegyezés']")
    private WebElement consentButton;
    @FindBy(xpath = "//p[contains(text(),'Email Address already exist')]")
    private WebElement emailAlreadyExistsMessage;
    @FindBy(css = "input[data-qa='login-email']")
    private WebElement loginEmailInput;
    @FindBy(css = "input[data-qa='login-password']")
    private WebElement loginPasswordInput;
    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;
    @FindBy(xpath = "//a[contains(text(), 'Logged in as')]")
    private WebElement loggedInAsText;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterNameForSignUp(String name) {
        typeInto(nameInput, name);
    }

    public void enterEmailForSignUp(String email) {
        typeInto(emailInput, email);
    }

    public void clickSignUpButton() {
        clickOn(signUpButton);
    }

    public void acceptConsent() {
        try {
            WebElement consentBtn = waitUntilVisible(consentButton);
            if (consentBtn.isDisplayed() && consentBtn.isEnabled()) {
                clickOn(consentBtn);
            }
        } catch (TimeoutException e) {
            System.out.println("Consent button not visible, continuing test.");
        }
    }

    public void userNameAndEmail(User user) {
        enterNameForSignUp(user.getName());
        enterEmailForSignUp(user.getEmail());
    }

    public boolean isEmailAlreadyExistsMessageVisible() {
        try {
            waitUntilVisible(emailAlreadyExistsMessage);
            return emailAlreadyExistsMessage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void enterEmailForLogin(String email) {
        typeInto(loginEmailInput, email);
    }

    public void enterPasswordForLogin(String password) {
        typeInto(loginPasswordInput, password);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }

    public boolean isUserLoggedIn() {
        try {
            waitUntilVisible(loggedInAsText);
            return loggedInAsText.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
