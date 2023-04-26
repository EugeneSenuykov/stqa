package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends MenuPage {
    protected By addNewProduct = By.xpath("//a[text()=\" Add New Product\"]");

    private final WebDriver driver;

    public CatalogPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
