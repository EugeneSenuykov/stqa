package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.page.admin.CountriesPage;
import selenium.page.admin.GeoZonesPage;
import selenium.page.admin.MenuPage;
import selenium.page.admin.LoginPage;
import selenium.page.customer.MainPage;
import selenium.page.customer.ProductPage;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MenuPage menuPage;
    private MainPage mainPage;
    private CountriesPage countriesPage;
    private GeoZonesPage geoZonesPage;
    private ProductPage productPage;

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
    public void checkStickersTest() {
        mainPage = new MainPage(driver);

        driver.get("http://localhost:8080/litecart");
        mainPage.checkSticker();
    }

    @Test
    public void checkCountriesTest() {
        loginPage = new LoginPage(driver);
        countriesPage = new CountriesPage(driver);
        geoZonesPage = new GeoZonesPage(driver);

        loginPage.login("admin", "admin");
        countriesPage.checkAlphabetOrder();
        geoZonesPage.checkGeoZone(countriesPage.findGeoZone());
    }

    @Test
    public void checkPageEditGeoZoneTest() {
        loginPage = new LoginPage(driver);
        geoZonesPage = new GeoZonesPage(driver);

        loginPage.login("admin", "admin");
        geoZonesPage.isAlphabetGeoZones();
    }

    @Test
    public void checkProductsTest() {
        productPage = new ProductPage(driver);

        driver.get("http://localhost:8080/litecart");
        productPage.checkProduct();
    }

    @AfterEach
    public void stop() {
        driver.close();
    }
}
