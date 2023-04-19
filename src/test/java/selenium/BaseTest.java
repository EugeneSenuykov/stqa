package selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.AdminMenu;
import selenium.page.LoginPage;

import java.time.Duration;

public class BaseTest {
    private WebDriver driver;
    private final LoginPage loginPage = new LoginPage();
    private final AdminMenu adminMenu = new AdminMenu();

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void checkAllAdminSectionTest() {
        loginPage.login(driver, "admin", "admin");
        adminMenu.clickListMenu(driver);
    }

    @AfterEach
    public void stop() {
        driver.close();
    }
}
