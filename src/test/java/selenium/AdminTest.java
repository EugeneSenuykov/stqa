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
    public void addNewProduct() {
        loginPage = new LoginPage(driver);
        addNewProductPage = new AddNewProductPage(driver);

        loginPage.login("admin", "admin");
        addNewProductPage.addProduct();
    }
}
