package pages;

import model.User;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(css = "input[data-qa='signup-name']")
    private WebElement nameInput;
    @FindBy(css = "input[data-qa='signup-email']")
    private WebElement emailInput;
    @FindBy(css = "button[data-qa='signup-button']")
    private WebElement signUpButton;
    @FindBy(xpath = "//p[contains(@class, 'fc-button-label') and text()='Beleegyezés']")
    private WebElement consentButton;

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
}
