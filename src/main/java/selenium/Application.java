package selenium;

import io.cucumber.java.After;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.page.BasePage;
import selenium.page.customer.BasketPage;
import selenium.page.customer.ProductPage;

import java.time.Duration;

public class Application {
    private WebDriver driver;
    private BasePage basePage;
    private ProductPage productPage;
    private BasketPage basketPage;
    private int countBasket;

    public Application() {
        driver = new ChromeDriver();
        basePage = new BasketPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    @Дано("пользователь открывает страницу магазина")
    public void openSite() {
        driver.get("http://localhost:8080/litecart");
    }

    @Когда("добавляем товары в корзину")
    public void openProductPage() {
        int count = Integer.parseInt(productPage.basketCount.getText());
        if (count == 0) {
            for (int i = 0; i < 3; i++) {
                String basketText = productPage.basketCount.getText();

                productPage.productsList.get(0).click();

                if (productPage.options.size() > 0) {
                    new Select(productPage.selectOptions).selectByIndex(1);
                }

                productPage.buttonAddProduct.click();
                isVisible(productPage.basketCount, basketText);
                productPage.buttonHome.click();
            }
        } else {
            Assertions.fail("Корзина не пуста");
        }
    }

    @И("удаляем товар из корзины")
    public void deleteAllProduct() {
        basketPage.checkout.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while (true) {
            try {
                WebElement element = driver.findElement(basketPage.shortcuts);
                basketPage.buttonRemove.click();
                wait.until(ExpectedConditions.stalenessOf(element));
            } catch (NoSuchElementException e) {
                basketPage.buttonRemove.click();
                break;
            }
        }
        basePage.open("http://localhost:8080/litecart");
        countBasket = Integer.parseInt(basketPage.basketCount.getText());
    }

    @Тогда("^количество элементов в корзине равно (\\d+)$")
    public void checkBasket(int count) {
        Assertions.assertEquals(count, countBasket);
    }

    @After
    public void quit() {
        System.out.println("test1");
        driver.quit();
    }

    private Boolean isVisible(WebElement element, String text) {
        for (int i = 0; i < 10; i++) {
            if (element.getText().equals(text)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                return true;
            }
        }
        return false;
    }

}
