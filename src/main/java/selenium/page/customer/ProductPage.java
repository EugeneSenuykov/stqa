package selenium.page.customer;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends MainPage {
    @FindBy(css = "h1.title")
    public WebElement productTitle;

    @FindBy(css = "div.information div.price-wrapper")
    public WebElement priceWrapper;

    @FindBy(xpath = "//button[@name='add_cart_product']")
    public WebElement buttonAddProduct;

    @FindBy(xpath = "//a[text()=\"Home\"]")
    public WebElement buttonHome;

    @FindBy(xpath = "//td[@class=\"options\"]")
    public List<WebElement> options;

    @FindBy(xpath = "//select[@name=\"options[Size]\"]")
    public WebElement selectOptions;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

        String productName = productTitle.getText();
        String subRegularPrice = getTextDriver(priceWrapper, regularPrice);
        String subCampaignPrice = getTextDriver(priceWrapper, campaignPrice);

        String subColorRegularPrice = priceWrapper.findElement(regularPrice).getCssValue("color");
        String subTextDecorateRegularPrice = priceWrapper.findElement(regularPrice).getCssValue("text-decoration");

        String subColorCampaignPrice = priceWrapper.findElement(campaignPrice).getCssValue("color");
        String subTextCampaignPrice = priceWrapper.findElement(campaignPrice).getCssValue("font-weight");
        String subFontSizeRegularPrice = priceWrapper.findElement(regularPrice).getCssValue("font-size");
        String subFontSizeCampaignPrice = priceWrapper.findElement(campaignPrice).getCssValue("font-size");


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

    private String getTextDriver(WebElement wrapper, By price) {
        if (wrapper.findElement(price).isDisplayed()) {
            return wrapper.findElement(price).getText();
        } else {
            return null;
        }
    }

    private String getColor(String color) {
        if (color.contains("rgba")) {
            color = color.substring(5, color.length() - 1);
        } else if (color.contains("rgb")) {
            color = color.substring(4, color.length() - 1);
        } else {
            throw new IllegalArgumentException("Неверный формат цвета");
        }

        List<String> colorArr = List.of(color.split(", "));
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
        return switch (text) {
            case "700", "800", "900", "bold", "bolder" -> true;
            default -> false;
        };
    }

    private Boolean compareFontSize(String regular, String campaign) {
        double regularPrice = Double.parseDouble(regular.substring(0, regular.length() - 2));
        double campaignPrice = Double.parseDouble(campaign.substring(0, campaign.length() - 2));

        return campaignPrice > regularPrice;
    }
}
