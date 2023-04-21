package selenium.page.customer;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends MainPage {
    private final By productTitle = By.cssSelector("h1.title");
    private final By priceWrapper = By.cssSelector("div.information div.price-wrapper");

    private final WebDriver driver;


    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void checkProduct() {
        WebElement product = driver.findElements(productsCampaigns).get(0);

        String mainProductName = productName.findElement(product).getText();
        String mainRegularPrice = getTextElement(product, mainPrice, regularPrice);
        String mainCampaignPrice = getTextElement(product, mainPrice, campaignPrice);

        String mainColorRegularPrice = product.findElement(mainPrice).findElement(regularPrice).getCssValue("color");
        String mainTextDecorateRegularPrice = product.findElement(mainPrice).findElement(regularPrice).getCssValue("text-decoration");

        String mainColorCampaignPrice = product.findElement(mainPrice).findElement(campaignPrice).getCssValue("color");
        String mainTextCampaignPrice = product.findElement(mainPrice).findElement(campaignPrice).getCssValue("font-weight");
        String mainFontSizeRegularPrice = product.findElement(mainPrice).findElement(regularPrice).getCssValue("font-size");
        String mainFontSizeCampaignPrice = product.findElement(mainPrice).findElement(campaignPrice).getCssValue("font-size");

        product.click();

        String productName = driver.findElement(productTitle).getText();
        String subRegularPrice = getTextDriver(driver, priceWrapper, regularPrice);
        String subCampaignPrice = getTextDriver(driver, priceWrapper, campaignPrice);

        String subColorRegularPrice = driver.findElement(priceWrapper).findElement(regularPrice).getCssValue("color");
        String subTextDecorateRegularPrice = driver.findElement(priceWrapper).findElement(regularPrice).getCssValue("text-decoration");

        String subColorCampaignPrice = driver.findElement(priceWrapper).findElement(campaignPrice).getCssValue("color");
        String subTextCampaignPrice = driver.findElement(priceWrapper).findElement(campaignPrice).getCssValue("font-weight");
        String subFontSizeRegularPrice = driver.findElement(priceWrapper).findElement(regularPrice).getCssValue("font-size");
        String subFontSizeCampaignPrice = driver.findElement(priceWrapper).findElement(campaignPrice).getCssValue("font-size");


        Assertions.assertEquals(mainProductName, productName, "Product name does not match");
        Assertions.assertEquals(mainRegularPrice, subRegularPrice, "Regular price does not match");
        Assertions.assertEquals(mainCampaignPrice, subCampaignPrice, "Campaign price does not match");

        Assertions.assertEquals("gray", getColor(mainColorRegularPrice), "Color regular price is not gray for main page");
        Assertions.assertEquals("gray", getColor(subColorRegularPrice), "Color regular price is not gray for product page");

        Assertions.assertTrue(mainTextDecorateRegularPrice.contains("line-through"), "Regular price is not crossed out for main page");
        Assertions.assertTrue(subTextDecorateRegularPrice.contains("line-through"), "Regular price is not crossed out for product page");

        Assertions.assertEquals("red", getColor(mainColorCampaignPrice), "Color campaign price is not red for main page");
        Assertions.assertEquals("red", getColor(subColorCampaignPrice), "Color campaign price is not red for product page");

        Assertions.assertTrue(isBold(mainTextCampaignPrice), "Text campaign price is not bold for main page");
        Assertions.assertTrue(isBold(subTextCampaignPrice), "Text campaign price is not bold for product page");

        Assertions.assertTrue(compareFontSize(mainFontSizeRegularPrice, mainFontSizeCampaignPrice),
                "Campaign price less than regular price for main page");
        Assertions.assertTrue(compareFontSize(subFontSizeRegularPrice, subFontSizeCampaignPrice),
                "Campaign price less than regular price for product page");
    }


    private String getTextElement(WebElement element, By wrapper, By price) {
        if (element.findElement(wrapper).findElement(price).isDisplayed()) {
            return element.findElement(wrapper).findElement(price).getText();
        } else {
            return null;
        }
    }

    private String getTextDriver(WebDriver webDriver, By wrapper, By price) {
        if (webDriver.findElement(wrapper).findElement(price).isDisplayed()) {
            return webDriver.findElement(wrapper).findElement(price).getText();
        } else {
            return null;
        }
    }

    private String getColor(String rgbaColor) {
        rgbaColor = rgbaColor.substring(5, rgbaColor.length() - 1);
        List<String> colorArr = List.of(rgbaColor.split(", "));
        List<Integer> numbers = new ArrayList<>();
        colorArr.forEach(e -> numbers.add(Integer.parseInt(e)));

        if (numbers.size() >= 3 && numbers.get(0) == numbers.get(1) && numbers.get(1) == numbers.get(2)) {
            return "gray";
        }

        if (numbers.size() >= 2 && numbers.get(1) == 0 && numbers.get(2) == 0) {
            return "red";
        }
        return "";
    }

    private Boolean isBold(String text) {
        return text.equals("700") || text.equals("bold");
    }

    private Boolean compareFontSize(String regular, String campaign) {
        double regularPrice = Double.parseDouble(regular.substring(0, regular.length() - 2));
        double campaignPrice = Double.parseDouble(campaign.substring(0, campaign.length() - 2));

        return campaignPrice > regularPrice;
    }
}
