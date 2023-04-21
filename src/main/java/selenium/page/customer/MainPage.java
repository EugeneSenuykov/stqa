package selenium.page.customer;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private final By productsList = By.cssSelector("li.product");
    private final By sticker = By.cssSelector("div.sticker");
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkSticker() {
        List<WebElement> products = driver.findElements(productsList);
        products.forEach(e -> Assertions.assertEquals(1, e.findElements(sticker).size()));
    }
}
