package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By login = By.name("login");

    public void login(WebDriver driver, String userLogin, String userPass) {
        driver.get("http://localhost:8080/litecart/admin");
        driver.findElement(username).sendKeys(userLogin);
        driver.findElement(password).sendKeys(userPass);
        driver.findElement(login).click();
    }
}
