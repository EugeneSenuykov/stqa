package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.admin.CountriesPage;
import selenium.page.admin.MenuPage;
import selenium.page.LoginPage;
import selenium.page.customer.MainPage;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MenuPage menuPage;
    private MainPage mainPage;
    private CountriesPage countriesPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void checkAllAdminSectionTest() {
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);

        loginPage.login("admin", "admin");
        menuPage.clickListMenu();
    }

    @Test
    public void checkStickers() {
        mainPage = new MainPage(driver);

        driver.get("http://localhost:8080/litecart");
        mainPage.checkSticker();
    }

    @Test
    public void checkCountries() {
        loginPage = new LoginPage(driver);
        countriesPage = new CountriesPage(driver);

        loginPage.login("admin", "admin");
        countriesPage.checkAlphabetOrder();
        countriesPage.checkGeoZone();
    }

    @AfterEach
    public void stop() {
        driver.close();
    }
}
