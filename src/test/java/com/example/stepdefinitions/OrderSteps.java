package com.example.stepdefinitions;

import com.example.pages.CartPage;
import com.example.pages.CheckoutPage;
import com.example.pages.InventoryPage;
import com.example.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class OrderSteps {
    InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());
    CartPage cartPage = new CartPage(DriverFactory.getDriver());
    CheckoutPage checkoutPage = new CheckoutPage(DriverFactory.getDriver());

    @When("I add {int} products to the cart")
    public void i_add_products_to_cart(int count) {
        inventoryPage.addProductsToCart(count);
    }

    @When("I go to the cart")
    public void i_go_to_cart() {
        inventoryPage.goToCart();
    }

    @Then("I should see {int} items in the cart")
    public void i_should_see_items_in_cart(int count) {
        Assertions.assertThat(cartPage.getCartItemCount()).isEqualTo(count);
    }

    @When("I click checkout")
    public void i_click_checkout() {
        cartPage.clickCheckout();
    }

    @When("I enter personal details {string} {string} {string}")
    public void i_enter_details(String fName, String lName, String zip) {
        checkoutPage.enterDetails(fName, lName, zip);
    }

    @When("I click continue")
    public void i_click_continue() {
        checkoutPage.clickContinue();
    }
    
    @Then("I should see the checkout summary")
    public void i_see_checkout_summary() {
        Assertions.assertThat(checkoutPage.isSummaryDisplayed()).isTrue();
    }

    @When("I click finish")
    public void i_click_finish() {
        checkoutPage.clickFinish();
    }

    @Then("I should see the order confirmation {string}")
    public void i_should_see_confirmation(String msg) {
        Assertions.assertThat(checkoutPage.getCompleteMessage()).isEqualTo(msg);
    }
    
    @Then("The URL should contain {string}")
    public void url_should_contain(String partialUrl) {
        Assertions.assertThat(DriverFactory.getDriver().getCurrentUrl()).contains(partialUrl);
    }
}