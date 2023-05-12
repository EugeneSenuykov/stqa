package selenium.page.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage extends MainPage {
    @FindBy(xpath = "//button[@name=\"remove_cart_item\"]")
    public WebElement buttonRemove;

    @FindBy(xpath = "//span[@class=\"quantity\"]")
    public WebElement basketCount;

    public By shortcuts = By.xpath("//ul[@class=\"shortcuts\"]//a");

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
