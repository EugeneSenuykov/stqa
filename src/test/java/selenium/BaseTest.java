package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import selenium.page.admin.*;
import selenium.page.customer.BasketPage;
import selenium.page.customer.CreateAccountPage;
import selenium.page.customer.MainPage;
import selenium.page.customer.ProductPage;

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

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void stop() {
        driver.close();
        driver.quit();
    }
}
