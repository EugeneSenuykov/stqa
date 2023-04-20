package selenium.page.admin;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesPage extends MenuPage {
    private final By rowsCountries = By.cssSelector("tr.row");
    private final By linkCountries = By.cssSelector("a:not([title])");
    private final By countZones = By.xpath("./td[last()-1]");
    private final By valuesZones = By.xpath("//input[contains(@name, \"name\") and @type=\"hidden\"]");

    public void checkAlphabetOrder(WebDriver driver) {
        driver.findElement(clickMenuButton("Countries")).click();
        List<String> countries = new ArrayList<>();
        driver.findElements(rowsCountries).forEach(e -> countries.add(e.findElement(linkCountries).getText()));

        isSortAlphabet(countries, "стран");
    }

    public void checkGeoZone(WebDriver driver) {
        List<String> linksZone = new ArrayList<>();

        driver.findElement(clickMenuButton("Countries")).click();

        driver.findElements(rowsCountries).forEach(e -> {
            if (Integer.parseInt(e.findElement(countZones).getText()) > 0) {
                linksZone.add(e.findElement(linkCountries).getAttribute("href"));
            }
        });

        for (String s : linksZone) {
            driver.get(s);
            List<String> nameZones = new ArrayList<>();
            driver.findElements(valuesZones).forEach(e -> nameZones.add(e.getAttribute("value")));

            isSortAlphabet(nameZones, s);
        }
    }

    private void isSortAlphabet(List<String> list, String listName) {
        List<String> zonesSort = new ArrayList<>(list);
        Collections.sort(zonesSort);

        Assertions.assertEquals(list, zonesSort,
                String.format("Список %s отсортирован не в алфавитном порядке", listName));
    }
}
