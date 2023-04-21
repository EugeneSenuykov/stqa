package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By login = By.name("login");

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userLogin, String userPass) {
        driver.get("http://localhost:8080/litecart/admin");
        driver.findElement(username).sendKeys(userLogin);
        driver.findElement(password).sendKeys(userPass);
        driver.findElement(login).click();
    }
}
