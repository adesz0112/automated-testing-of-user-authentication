package pages;

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
}
