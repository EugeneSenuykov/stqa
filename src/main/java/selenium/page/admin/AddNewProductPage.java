package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Random;

public class AddNewProductPage extends CatalogPage {
    private final By statusRadio = By.xpath("//label[text()=\" Enabled\"]/input");
    private final By nameEn = By.xpath("//input[@name=\"name[en]\"]");
    private final By code = By.xpath("//input[@name=\"code\"]");
    private final By categories = By.xpath("//input[@name=\"categories[]\"]");
    private final By defaultCategories = By.xpath("//select[@name=\"default_category_id\"]");
    private final By gender = By.xpath("//input[@name=\"product_groups[]\"]");
    private final By quantity = By.xpath("//input[@name=\"quantity\"]");
    private final By quantityUnit = By.xpath("//select[@name=\"quantity_unit_id\"]");
    private final By deliveryStatus = By.xpath("//select[@name=\"delivery_status_id\"]");
    private final By soldOutStatus = By.xpath("//select[@name=\"sold_out_status_id\"]");
    private final By dateFrom = By.xpath("//input[@name=\"date_valid_from\"]");
    private final By dateTo = By.xpath("//input[@name=\"date_valid_to\"]");
    private final By newImages = By.xpath("//input[@name=\"new_images[]\"]");

    private final By informationButton = By.xpath("//a[text()=\"Information\"]");
    private final By pricesButton = By.xpath("//a[text()=\"Prices\"]");
    private final By saveButton = By.xpath("//button[@name=\"save\"]");

    private final By manufacturerId = By.xpath("//select[@name=\"manufacturer_id\"]");
    private final By keywords = By.xpath("//input[@name=\"keywords\"]");
    private final By shortDescription = By.xpath("//input[@name=\"short_description[en]\"]");
    private final By longDescription = By.xpath("//div[@class=\"trumbowyg-editor\"]");
    private final By headTitle = By.xpath("//input[@name=\"head_title[en]\"]");
    private final By metaDescription = By.xpath("//input[@name=\"meta_description[en]\"]");

    private final By purchasePrice = By.xpath("//input[@name=\"purchase_price\"]");
    private final By purchasePriceCurrencyCode = By.xpath("//select[@name=\"purchase_price_currency_code\"]");

    private final String findProduct = "//a[text()=\"%s\"]";
    private final String pathToFile = "src/main/resources/product.png";
    private final WebDriver driver;

    public AddNewProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void addProduct() {
        Random random = new Random();
        String productName = String.format("product %s", random.nextInt(100000));
        String productCode = String.valueOf(random.nextInt(99999));

        driver.findElement(findMenuButton("Catalog")).click();
        driver.findElement(addNewProduct).click();

        if (!driver.findElement(statusRadio).isSelected())
            driver.findElement(statusRadio).click();

        driver.findElement(nameEn).sendKeys(productName);
        driver.findElement(code).sendKeys(productCode);

        driver.findElements(categories).forEach(e -> {
            if (!e.isSelected())
                e.click();
        });
        new Select(driver.findElement(defaultCategories)).selectByIndex(1);

        driver.findElements(gender).get(0).click();
        driver.findElements(gender).get(1).click();
        driver.findElement(quantity).clear();
        driver.findElement(quantity).sendKeys("100");

        String quantityUnitID = new Select(driver.findElement(quantityUnit)).getFirstSelectedOption().getText();
        if (!quantityUnitID.equals("pcs")) {
            new Select(driver.findElement(quantityUnit)).selectByValue("1");
        }

        String soldOutStatusId = new Select(driver.findElement(soldOutStatus)).getFirstSelectedOption().getText();
        if (!soldOutStatusId.equals("Temporary sold out")) {
            new Select(driver.findElement(soldOutStatus)).selectByValue("2");
        }

        setDatepicker(driver, dateFrom, "11/01/2023");
        setDatepicker(driver, dateTo, "11/12/2023");
        driver.findElement(newImages).sendKeys(getAbsolutePath(pathToFile));


        driver.findElement(informationButton).click();
        new Select(driver.findElement(manufacturerId)).selectByValue("1");
        driver.findElement(keywords).sendKeys("test keywords");
        driver.findElement(shortDescription).sendKeys("test short description");

        driver.findElement(longDescription).sendKeys("test long description");
        driver.findElement(headTitle).sendKeys("test head title");
        driver.findElement(metaDescription).sendKeys("test meta description");


        driver.findElement(pricesButton).click();
        driver.findElement(purchasePrice).clear();
        driver.findElement(purchasePrice).sendKeys("125.00");
        new Select(driver.findElement(purchasePriceCurrencyCode)).selectByValue("USD");

        driver.findElement(saveButton).click();
        driver.findElement(By.xpath(String.format(findProduct, productName))).isDisplayed();

    }

    public void setDatepicker(WebDriver driver, By xpath, String date) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(xpath));
        driver.findElement(xpath).sendKeys(date);
        driver.findElement(By.cssSelector("body")).click();
    }

    public static String getAbsolutePath(String relativePath) {
        Path path = Paths.get(relativePath);
        Path absolutePath = path.toAbsolutePath();
        return absolutePath.toString();
    }
}
