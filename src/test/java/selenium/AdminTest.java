package selenium;

import org.junit.jupiter.api.Test;
import selenium.page.admin.*;

public class AdminTest extends BaseTest {

    @Test
    public void checkAllAdminSectionTest() {
        loginPage = new LoginPage(driver);
        menuPage = new MenuPage(driver);

        loginPage.login("admin", "admin");
        menuPage.clickListMenu();
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
    public void addNewProductTest() {
        loginPage = new LoginPage(driver);
        addNewProductPage = new AddNewProductPage(driver);

        loginPage.login("admin", "admin");
        addNewProductPage.addProduct();
    }

    @Test
    public void checkLinksTest() {
        loginPage = new LoginPage(driver);
        addNewCountryPage = new AddNewCountryPage(driver);

        loginPage.login("admin", "admin");
        addNewCountryPage.checkLinks();
    }

    @Test
    public void checkLogs() {
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);

        loginPage.login("admin", "admin");
        driver.get("http://localhost:8080/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        catalogPage.findLogs();
    }
}
