package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class GeoZonesPage extends MenuPage {
    private final By valuesZones = By.xpath("//input[contains(@name, \"name\") and @type=\"hidden\"]/..");
    private final By listNames = By.xpath("//tr[@class=\"row\"]//a[not( contains(@title, \"Edit\"))]");
    private final By optionName = By.xpath("//select[contains(@name, \"zone_code\")]/option[@selected]");

    private final WebDriver driver;

    public GeoZonesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkGeoZone(List<String> linksZone) {
        for (String s : linksZone) {
            driver.get(s);
            List<String> nameZones = new ArrayList<>();
            driver.findElements(valuesZones).forEach(e -> nameZones.add(e.getText()));
            isSortAlphabet(nameZones, s);
        }
    }

    public void isAlphabetGeoZones() {
        List<String> links = new ArrayList<>();
        driver.findElement(findMenuButton("Geo Zones")).click();
        driver.findElements(listNames).forEach(e -> links.add(e.getAttribute("href")));

        links.forEach(e -> {
            List<String> nameZones = new ArrayList<>();
            driver.get(e);
            driver.findElements(optionName).forEach(ex -> nameZones.add(ex.getText()));
            isSortAlphabet(nameZones, e);
        });
    }
}