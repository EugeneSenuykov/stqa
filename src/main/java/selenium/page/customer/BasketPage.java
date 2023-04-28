package selenium.page.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage extends MainPage {
    private final WebDriver driver;
    private final By shortcuts = By.xpath("//ul[@class=\"shortcuts\"]//a");
    private final By buttonRemove = By.xpath("//button[@name=\"remove_cart_item\"]");

    public BasketPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void deleteAllProduct() {
        driver.findElement(checkout).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        while (true) {
            try {
                WebElement element = driver.findElement(shortcuts);
                driver.findElement(buttonRemove).click();
                wait.until(ExpectedConditions.stalenessOf(element));
            } catch (NoSuchElementException e) {
                driver.findElement(buttonRemove).click();
                break;
            }
        }
    }
}
