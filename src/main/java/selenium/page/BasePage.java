package selenium.page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String url) {
        driver.get(url);
    }

    protected void isSortAlphabet(List<String> list, String listName) {
        List<String> zonesSort = new ArrayList<>(list);
        Collections.sort(zonesSort);

        Assertions.assertEquals(list, zonesSort,
                String.format("Список %s отсортирован не в алфавитном порядке", listName));
    }
}