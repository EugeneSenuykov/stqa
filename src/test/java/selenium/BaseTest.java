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
    private final LoginPage loginPage = new LoginPage();
    private final MenuPage menuPage = new MenuPage();
    private final MainPage mainPage = new MainPage();
    private final CountriesPage countriesPage = new CountriesPage();

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void checkAllAdminSectionTest() {
        loginPage.login(driver, "admin", "admin");
        menuPage.clickListMenu(driver);
    }

    @Test
    public void checkStickers() {
        driver.get("http://localhost:8080/litecart");
        mainPage.checkSticker(driver);
    }

    @Test
    public void checkCountries() {
        loginPage.login(driver, "admin", "admin");
        countriesPage.checkAlphabetOrder(driver);
        countriesPage.checkGeoZone(driver);
    }

    @AfterEach
    public void stop() {
        driver.close();
    }
}
