package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class GeoZonesPage extends MenuPage {
    private final By valuesZones = By.xpath("//input[contains(@name, \"name\") and @type=\"hidden\"]/..");

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
}