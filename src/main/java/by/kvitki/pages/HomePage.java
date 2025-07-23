package by.kvitki.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String HOME_PAGE_URL = "https://www.kvitki.by/";

    @FindBy(css = "a.the-header__user-link")
    private WebElement profileIcon;

    @FindBy(xpath = "//button[normalize-space()='Разрешить все cookie-файлы и принять Политику обработки персональных данных']")
    private WebElement acceptCookiesButton;

    @FindBy(xpath = "//a[text()='Контакты']")
    private WebElement contactsLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public HomePage openPage() {
        driver.get(HOME_PAGE_URL);
        return this;
    }

    public HomePage acceptCookies() {
        try {
            wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", acceptCookiesButton);
        } catch (Exception e) {
            // Игнорируем ошибку, если баннера нет
        }
        return this;
    }

    public void goToContactsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(contactsLink)).click();
    }

    public AuthPage clickProfileIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
        return new AuthPage(driver);
    }
}