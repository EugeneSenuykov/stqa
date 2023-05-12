package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LoggingPreferences;
import selenium.page.admin.*;
import selenium.page.customer.BasketPage;
import selenium.page.customer.CreateAccountPage;
import selenium.page.customer.MainPage;
import selenium.page.customer.ProductPage;

import java.util.logging.Level;

public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected MenuPage menuPage;
    protected MainPage mainPage;
    protected CountriesPage countriesPage;
    protected GeoZonesPage geoZonesPage;
    protected ProductPage productPage;
    protected CreateAccountPage createAccountPage;
    protected AddNewProductPage addNewProductPage;
    protected BasketPage basketPage;
    protected AddNewCountryPage addNewCountryPage;
    protected CatalogPage catalogPage;

    @BeforeEach
    public void setUp() {
        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable("browser", Level.WARNING);
        ChromeOptions options = new ChromeOptions();
        options.setCapability("goog:loggingPrefs", prefs);
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void stop() {
        driver.close();
        driver.quit();
    }
}
