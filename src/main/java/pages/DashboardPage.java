package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(css = "a[href='/logout']")
    private WebElement logoutLink;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        clickOn(logoutLink);
    }
}
