package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class AddNewCountryPage extends CountriesPage {
    private final By blankList = By.xpath("//form[@enctype=\"multipart/form-data\"]//a[@target=\"_blank\"]");

    private final WebDriver driver;

    public AddNewCountryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkLinks() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.findElement(findMenuButton("Countries")).click();
        driver.findElement(addNewCountryButton).click();

        int count = driver.findElements(blankList).size();
        String mainWindow = driver.getWindowHandle();
        Set<String> handles;

        for (int i = 0; i < count; i++) {
            driver.findElements(blankList).get(i).click();
            handles = driver.getWindowHandles();
            handles.remove(mainWindow);

            String[] array = handles.toArray(new String[0]);
            driver.switchTo().window(array[0]);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
}
