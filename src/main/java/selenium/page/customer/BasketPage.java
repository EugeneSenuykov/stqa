package selenium.page.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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

    public void deleteAllProduct() throws InterruptedException {
        driver.findElement(checkout).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        clickShortcuts(shortcuts, buttonRemove);
        while (true) {
            try {
                wait.until(ExpectedConditions.stalenessOf(driver.findElement(shortcuts)));
                clickShortcuts(shortcuts, buttonRemove);
            } catch (NoSuchElementException e) {
                driver.findElement(buttonRemove).click();
                break;
            }
        }

        Thread.sleep(5000);
    }

    public void clickShortcuts(By shortcuts, By buttonRemove) {
        if (driver.findElements(shortcuts).size() > 0) {
            driver.findElements(shortcuts).get(0).click();
            driver.findElement(buttonRemove).click();
        } else {
            driver.findElement(buttonRemove).click();
        }
    }
}
