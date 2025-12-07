package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    private By cartItems = By.className("cart_item");
    private By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) { super(driver); }

    public int getCartItemCount() { 
        return driver.findElements(cartItems).size(); 
    }
    public void clickCheckout() { click(checkoutBtn); }
}