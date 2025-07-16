package by.kvitki.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "form-error")
    private WebElement errorMessage;
    @FindBy(name = "login")
    private WebElement emailInput; // Поле email используется в обеих формах

    @FindBy(name = "password")
    private WebElement loginPasswordInput;
    @FindBy(xpath = "//div[contains(@class, 'auth-form__body--login')]//button[@type='submit']")
    private WebElement loginSubmitButton;

    @FindBy(xpath = "//button[text()='Регистрация']")
    private WebElement registrationTab;
    @FindBy(name = "register_password")
    private WebElement registrationPasswordInput;
    @FindBy(name = "register_password_repeat")
    private WebElement registrationPasswordRepeatInput;
    @FindBy(xpath = "//div[contains(@class, 'auth-form__body--register')]//button[@type='submit']")
    private WebElement registrationSubmitButton;


    public AuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * НОВЫЙ МЕТОД
     * Переключает на вкладку регистрации
     */
    public void switchToRegistrationTab() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationTab)).click();
    }

    /**
     * Старый метод для логина
     */
    public void login(String email, String password) {
        emailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginSubmitButton.click();
    }

    /**
     * НОВЫЙ МЕТОД
     * Заполняет и отправляет форму регистрации
     */
    public void register(String email, String password, String passwordRepeat) {
        emailInput.sendKeys(email);
        registrationPasswordInput.sendKeys(password);
        registrationPasswordRepeatInput.sendKeys(passwordRepeat);
        registrationSubmitButton.click();
    }

    /**
     * Старый метод для получения текста ошибки
     */
    public String getErrorMessageText() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}