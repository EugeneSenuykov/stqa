package selenium;

import org.junit.jupiter.api.Test;
import selenium.page.customer.BasketPage;
import selenium.page.customer.CreateAccountPage;
import selenium.page.customer.MainPage;
import selenium.page.customer.ProductPage;

import java.util.Map;

public class CustomerTest extends BaseTest {
    @Test
    public void checkStickersTest() {
        mainPage = new MainPage(driver);

        driver.get("http://localhost:8080/litecart");
        mainPage.checkSticker();
    }

    @Test
    public void checkProductsTest() {
        productPage = new ProductPage(driver);

        driver.get("http://localhost:8080/litecart");
        productPage.checkProduct();
    }

    @Test
    public void userRegistrationTest() {
        createAccountPage = new CreateAccountPage(driver);
        mainPage = new MainPage(driver);
        Map<String, String> credential;

        driver.get("http://localhost:8080/litecart");
        credential = createAccountPage.createNewAccount();
        mainPage.logOut();
        mainPage.userLogIn(credential);
        mainPage.logOut();
    }

    @Test
    public void checkBasketTest() throws InterruptedException {
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);

        driver.get("http://localhost:8080/litecart");
        productPage.openProductPage();
        basketPage.deleteAllProduct();
    }

}
