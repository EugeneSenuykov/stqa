package selenium.page.customer;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class MainPage {
    private final By sticker = By.cssSelector("div.sticker");
    private final By logout = By.xpath("//h3[text()=\"Account\"]/..//a[text()=\"Logout\"]");
    private final By emailLogin = By.xpath("//input[@name=\"email\"]");
    private final By passLogin = By.xpath("//input[@name=\"password\"]");
    private final By buttonLogin = By.xpath("//button[@name=\"login\"]");

    protected final By productsList = By.cssSelector("li.product");
    protected final By productsCampaigns = By.xpath("//h3[text()=\"Campaigns\"]//following-sibling::div//li");
    protected final By productName = By.cssSelector("div.name");
    protected final By mainPrice = By.cssSelector("div.price-wrapper");
    protected final By regularPrice = By.cssSelector("s.regular-price");
    protected final By campaignPrice = By.cssSelector("strong.campaign-price");
    protected final By createUser = By.xpath("//form[@name=\"login_form\"]//tr[last()]//a");
    protected final By basketCount = By.xpath("//span[@class=\"quantity\"]");
    protected final By checkout = By.xpath("//a[text()=\"Checkout »\"]");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkSticker() {
        List<WebElement> products = driver.findElements(productsList);
        products.forEach(e -> Assertions.assertEquals(1, e.findElements(sticker).size()));
    }

    public void logOut() {
        driver.findElement(logout).click();
    }

    public void userLogIn(Map<String, String> credential) {
        driver.findElement(emailLogin).sendKeys(credential.get("login"));
        driver.findElement(passLogin).sendKeys(credential.get("pass"));
        driver.findElement(buttonLogin).click();
    }
}
