package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage extends BasePage {
    private By addToCartButtons = By.xpath("//button[contains(text(), 'Add to cart')]");
    private By cartLink = By.className("shopping_cart_link");
    private By inventoryList = By.className("inventory_list");
    private By titleSpan = By.className("title");
    private By twitterLink = By.className("social_twitter");
    private By facebookLink = By.className("social_facebook");
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) { super(driver); }

    public void addProductsToCart(int count) {
        List<WebElement> items = driver.findElements(addToCartButtons);
        for (int i = 0; i < count && i < items.size(); i++) {
            items.get(i).click();
        }
    }
    
    public void goToCart() { click(cartLink); }
    public boolean isInventoryDisplayed() { return isDisplayed(inventoryList); }
    public String getPageTitle() { return getText(titleSpan); }
    public boolean isTwitterDisplayed() { return isDisplayed(twitterLink); }
    public boolean isFacebookDisplayed() { return isDisplayed(facebookLink); }
    public String getCartBadgeCount() {
        if(driver.findElements(cartBadge).isEmpty()) return "0";
        return getText(cartBadge);
    }
}