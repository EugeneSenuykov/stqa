package selenium;

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

    public Application() {
        driver = new ChromeDriver();
        basePage = new BasketPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public Integer checkBasket() {
        basePage.open("http://localhost:8080/litecart");
        openProductPage();
        return(deleteAllProduct());
    }

    private void openProductPage() {
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

    private Integer deleteAllProduct() {
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
        return Integer.parseInt(basketPage.basketCount.getText());
    }

    public void quit() {
        driver.quit();
    }

}
