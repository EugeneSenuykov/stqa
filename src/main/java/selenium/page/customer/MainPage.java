package selenium.page.customer;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.page.BasePage;

import java.util.List;
import java.util.Map;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[@class=\"quantity\"]")
    public WebElement basketCount;

    @FindBy(xpath = "//a[text()=\"Checkout »\"]")
    public WebElement checkout;

    @FindBy(css = "li.product")
    public List<WebElement> productsList;

    private final By sticker = By.cssSelector("div.sticker");
    private final By logout = By.xpath("//h3[text()=\"Account\"]/..//a[text()=\"Logout\"]");
    private final By emailLogin = By.xpath("//input[@name=\"email\"]");
    private final By passLogin = By.xpath("//input[@name=\"password\"]");
    private final By buttonLogin = By.xpath("//button[@name=\"login\"]");

    protected final By productsCampaigns = By.xpath("//h3[text()=\"Campaigns\"]//following-sibling::div//li");
    protected final By productName = By.cssSelector("div.name");
    protected final By mainPrice = By.cssSelector("div.price-wrapper");
    protected final By regularPrice = By.cssSelector("s.regular-price");
    protected final By campaignPrice = By.cssSelector("strong.campaign-price");
    protected final By createUser = By.xpath("//form[@name=\"login_form\"]//tr[last()]//a");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void checkSticker() {
        List<WebElement> products = productsList;
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
