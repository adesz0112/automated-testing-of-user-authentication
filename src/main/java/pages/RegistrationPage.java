package pages;

import model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
    @FindBy(id = "id_gender1")
    private WebElement mrRadioButton;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "days")
    private WebElement dayDropdown;
    @FindBy(id = "months")
    private WebElement monthDropDown;
    @FindBy(id = "years")
    private WebElement yearDropDown;
    @FindBy(id = "first_name")
    private WebElement firstNameInput;
    @FindBy(id = "last_name")
    private WebElement lastNameInput;
    @FindBy(id = "company")
    private WebElement companyInput;
    @FindBy(id = "address1")
    private WebElement address1Input;
    @FindBy(id = "address2")
    private WebElement address2Input;
    @FindBy(id = "country")
    private WebElement countryDropDown;
    @FindBy(id = "state")
    private WebElement stateInput;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(id = "zipcode")
    private WebElement zipcodeInput;
    @FindBy(id = "mobile_number")
    private WebElement mobileNumberInput;
    @FindBy(css = "button[data-qa='create-account']")
    private WebElement createAccountButton;
    @FindBy(xpath = "//b[text()='Account Created!']")
    private WebElement accountCreatedMessage;


    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void selectTitle(String title) {
        String genderId = title.equalsIgnoreCase("male") ? "id_gender1" : "id_gender2";
        WebElement genderRadio = driver.findElement(By.id(genderId));
        clickOn(genderRadio);
    }

    public void enterPassword(String password) {
        typeInto(passwordInput, password);
    }

    public void selectDayByValue(String value) {
        Select select = new Select(dayDropdown);
        select.selectByValue(value);
    }

    public void selectMonthByValue(String value) {
        Select select = new Select(monthDropDown);
        select.selectByValue(value);
    }

    public void selectYearByValue(String value) {
        Select select = new Select(yearDropDown);
        select.selectByValue(value);
    }

    public void enterFirstName(String name) {
        typeInto(firstNameInput, name);
    }

    public void enterLastName(String name) {
        typeInto(lastNameInput, name);
    }

    public void enterCompany(String company) {
        typeInto(companyInput, company);
    }

    public void enterAddress1(String address1) {
        typeInto(address1Input, address1);
    }

    public void enterAddress2(String address2) {
        typeInto(address2Input, address2);
    }

    public void selectCountry(String country) {
        Select select = new Select(countryDropDown);
        select.selectByValue(country);
    }

    public void enterState(String state) {
        typeInto(stateInput, state);
    }

    public void enterCity(String city) {
        typeInto(cityInput, city);
    }

    public void enterZipcode(String zipcode) {
        typeInto(zipcodeInput, zipcode);
    }

    public void enterMobileNumber(String mobileNumber) {
        typeInto(mobileNumberInput, mobileNumber);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickOnCreateAccount() {
        scrollToElement(createAccountButton);
        clickOn(createAccountButton);
    }

    public boolean isAccountCreated() {
        try {
            return accountCreatedMessage.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void fillPersonalInfo(User user) {
        selectTitle(user.getTitle());
        enterPassword(user.getPassword());
        selectDayByValue(user.getDay());
        selectMonthByValue(user.getMonth());
        selectYearByValue(user.getYear());
    }

    public void fillAddressInfo(User user) {
        enterFirstName(user.getFirstName());
        enterLastName(user.getLastName());
        enterCompany(user.getCompany());
        enterAddress1(user.getAddress1());
        enterAddress2(user.getAddress2());
        selectCountry(user.getCountry());
        enterState(user.getState());
        enterCity(user.getCity());
        enterZipcode(user.getZipcode());
        enterMobileNumber(user.getMobileNumber());
    }

}
