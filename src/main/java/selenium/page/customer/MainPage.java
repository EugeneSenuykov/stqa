package selenium.page.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage {
    private final By productsList = By.cssSelector("li[class^='product']");
    private final By sticker = By.cssSelector("div[class^='sticker']");

    public void checkSticker(WebDriver driver) {
        List<WebElement> products = driver.findElements(productsList);
        products.forEach(e -> e.findElement(sticker).isDisplayed());
    }
}
