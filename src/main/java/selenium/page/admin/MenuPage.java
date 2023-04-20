package selenium.page.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage {
    private final By listApp = By.xpath("//li[@id = \"app-\"]");
    private final By listDoc = By.xpath(".//li/a");
    private final By header = By.xpath("//h1");
    private final String buttonMenu = "//span[text()=\"%s\"]";

    public void clickListMenu(WebDriver driver) {
        int countMainMenu = driver.findElements(listApp).size();
        for (int i = 0; i < countMainMenu; i++) {
            driver.findElements(listApp).get(i).click();
            int countSubMenu = driver.findElements(listApp).get(i).findElements(listDoc).size();
            if (countSubMenu > 0) {
                for (int j = 0; j < countSubMenu; j++) {
                    driver.findElements(listApp).get(i).findElements(listDoc).get(j).click();
                    driver.findElement(header).isDisplayed();
                }
            } else {
                driver.findElement(header).isDisplayed();
            }
        }
    }

    protected By clickMenuButton(String menuName) {
        return By.xpath(String.format(buttonMenu, menuName));
    }

}
