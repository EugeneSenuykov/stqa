package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class CountriesPage extends MenuPage {
    private final By rowsCountries = By.cssSelector("tr.row");
    private final By linkCountries = By.cssSelector("a:not([title])");
    private final By countZones = By.xpath("./td[last()-1]");
    protected final By addNewCountryButton = By.xpath("//a[@class=\"button\"]");

    private final WebDriver driver;

    public CountriesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkAlphabetOrder() {
        driver.findElement(findMenuButton("Countries")).click();
        List<String> countries = new ArrayList<>();
        driver.findElements(rowsCountries).forEach(e -> countries.add(e.findElement(linkCountries).getText()));

        isSortAlphabet(countries, "стран");
    }

    public List<String> findGeoZone() {
        List<String> linksZone = new ArrayList<>();

        driver.findElement(findMenuButton("Countries")).click();

        driver.findElements(rowsCountries).forEach(e -> {
            if (Integer.parseInt(e.findElement(countZones).getText()) > 0) {
                linksZone.add(e.findElement(linkCountries).getAttribute("href"));
            }
        });

        return linksZone;
    }
}
