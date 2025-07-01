package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutLink;
    @FindBy(xpath = "//a[contains(text(), 'Logged in as')]")
    private WebElement loggedInAsText;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        clickOn(logoutLink);
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
