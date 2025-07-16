package by.kvitki.tests;

import by.kvitki.pages.HomePage;
import by.kvitki.pages.AuthPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testInvalidLoginShowsError() {
        String expectedErrorMessage = "Неверный логин или пароль";
        HomePage homePage = new HomePage(driver);

        homePage.openPage()
        .acceptCookies();

        homePage.goToContactsPage();

        AuthPage loginPage = homePage.clickProfileIcon();

        loginPage.login("invalid@test.com", "invalidpassword");

        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Текст ошибки не соответствует ожидаемому!");
    }

    @Test
    public void testRegistrationWithMismatchedPasswords() {
        String expectedErrorMessage = "Пароли не совпадают";

        HomePage homePage = new HomePage(driver);
        AuthPage authPage; // Или AuthPage authPage;

        homePage.openPage()
        .acceptCookies();

        homePage.goToContactsPage();

        authPage = homePage.clickProfileIcon();

        authPage.switchToRegistrationTab(); // Этот метод мы ранее добавили в AuthPage/LoginPage

        authPage.register("new.test.user@google.com", "good_password123", "different_password456");

        String actualErrorMessage = authPage.getErrorMessageText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Сообщение об ошибке для несовпадающих паролей неверное!");
    }
}