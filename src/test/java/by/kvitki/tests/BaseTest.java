package by.kvitki.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        // Если тест упал (FAILURE), делаем скриншот
        if (ITestResult.FAILURE == result.getStatus()) {
            // Создаем папку screenshots, если ее нет
            File screenshotsDir = new File("./screenshots/");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Сохраняем скриншот с именем упавшего теста
            FileUtils.copyFile(screenshot, new File("./screenshots/" + result.getName() + ".png"));
            System.out.println("Скриншот для упавшего теста сохранен: " + result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}