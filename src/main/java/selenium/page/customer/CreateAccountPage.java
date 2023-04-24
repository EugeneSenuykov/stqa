package selenium.page.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CreateAccountPage extends MainPage {
    private final By firstName = By.xpath("//input[@name=\"firstname\"]");
    private final By lastName = By.xpath("//input[@name=\"lastname\"]");
    private final By address1 = By.xpath("//input[@name=\"address1\"]");
    private final By city = By.xpath("//input[@name=\"city\"]");
    private final By postcode = By.xpath("//input[@name=\"postcode\"]");
    private final By email = By.xpath("//input[@name=\"email\"]");
    private final By phone = By.xpath("//input[@name=\"phone\"]");
    private final By password = By.xpath("//input[@name=\"password\"]");
    private final By confirmedPassword = By.xpath("//input[@name=\"confirmed_password\"]");
    private final By createAccount = By.xpath("//button[@name=\"create_account\"]");
    private final By selectCountry = By.xpath("//span[@class=\"selection\"]/..");
    private final By zoneCode = By.xpath("//select[@name=\"zone_code\"]/..");

    private final String optionCountry = "//li[@class=\"select2-results__option\" and text()= \"%s\"]";
    private final String optionZone = "//select[@name=\"zone_code\"]/option[text()=\"%s\"]";


    private final WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public Map<String, String> createNewAccount() {
        Random random = new Random();
        Map<String, String> credential = new HashMap<>();
        String login = String.format("test%s@mail.com", random.nextInt(100000));
        String pass = "test0";
        credential.put("login", login);
        credential.put("pass", pass);

        driver.findElement(createUser).click();
        driver.findElement(firstName).sendKeys("Ivan");
        driver.findElement(lastName).sendKeys("Ivanov");
        driver.findElement(address1).sendKeys("Test address");
        driver.findElement(postcode).sendKeys("12345");
        driver.findElement(city).sendKeys("Oklahoma");

        driver.findElement(selectCountry).click();
        driver.findElement(selectOptionCountry("United States")).click();
        driver.findElement(zoneCode).click();
        driver.findElement(selectOptionZoneCode("Colorado")).click();

        driver.findElement(email).sendKeys(login);
        driver.findElement(phone).sendKeys("123456789");
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmedPassword).sendKeys("test0");

        driver.findElement(createAccount).click();
        return credential;
    }

    private By selectOptionCountry(String country) {
        return By.xpath(String.format(optionCountry, country));
    }
    private By selectOptionZoneCode(String zone) {
        return By.xpath(String.format(optionZone, zone));
    }
}
