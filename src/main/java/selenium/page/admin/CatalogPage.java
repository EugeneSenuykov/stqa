package selenium.page.admin;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CatalogPage extends MenuPage {
    protected final By addNewProduct = By.xpath("//a[text()=\" Add New Product\"]");
    private final By products = By.xpath("//td/a[contains(text(), 'Duck')]");

    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void findLogs() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<String> listProducts = new ArrayList<>();
        driver.findElements(products).forEach(e -> listProducts.add(e.getAttribute("href")));

        listProducts.forEach(e -> {
            driver.get(e);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
            driver.manage().logs().get("client").getAll().forEach(
                    ex -> Assertions.assertTrue(ex.getMessage().isEmpty())
            );
        });
    }

}
