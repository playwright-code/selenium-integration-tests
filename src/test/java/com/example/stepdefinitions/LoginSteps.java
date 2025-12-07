package com.example.stepdefinitions;

import com.example.pages.InventoryPage;
import com.example.pages.LoginPage;
import com.example.utils.ConfigReader;
import com.example.utils.CsvUtils;
import com.example.utils.DriverFactory;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import java.util.Map;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    InventoryPage inventoryPage = new InventoryPage(DriverFactory.getDriver());

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        DriverFactory.getDriver().get(ConfigReader.getProperty("url"));
    }

    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        loginPage.enterUsername(ConfigReader.getProperty("username"));
        loginPage.enterPassword(ConfigReader.getProperty("password"));
        loginPage.clickLogin();
    }
    
    @When("I login using CSV credentials for role {string}")
    public void i_login_from_csv(String role) {
        Map<String, String> creds = CsvUtils.getCredentialsByRole(role);
        loginPage.enterUsername(creds.get("username"));
        loginPage.enterPassword(creds.get("password"));
        loginPage.clickLogin();
    }

    @Then("I should be logged in or see an error based on {string}")
    public void verify_login_status(String role) {
        if (role.equals("locked")) {
             Assertions.assertThat(loginPage.isErrorDisplayed()).as("Error message should be displayed for locked user").isTrue();
             Assertions.assertThat(loginPage.getErrorMessage()).contains("locked out");
        } else {
             Assertions.assertThat(inventoryPage.isInventoryDisplayed()).as("Inventory should be displayed for valid user").isTrue();
        }
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected() {
        Assertions.assertThat(inventoryPage.isInventoryDisplayed()).isTrue();
    }
    
    @Then("I should see the product list")
    public void i_should_see_product_list() {
        Assertions.assertThat(inventoryPage.isInventoryDisplayed()).isTrue();
    }
    
    @Then("The page title should be {string}")
    public void page_title_should_be(String expectedTitle) {
        Assertions.assertThat(inventoryPage.getPageTitle()).isEqualTo(expectedTitle);
    }
    
    @Then("I should see the {string} social link")
    public void verify_social_link(String network) {
        if(network.equalsIgnoreCase("Twitter")) 
            Assertions.assertThat(inventoryPage.isTwitterDisplayed()).isTrue();
        if(network.equalsIgnoreCase("Facebook")) 
            Assertions.assertThat(inventoryPage.isFacebookDisplayed()).isTrue();
    }
    
    @Then("The cart badge should be empty")
    public void cart_badge_empty() {
        Assertions.assertThat(inventoryPage.getCartBadgeCount()).isEqualTo("0");
    }
    
    @Then("The cart badge should display {string}")
    public void cart_badge_display(String count) {
         Assertions.assertThat(inventoryPage.getCartBadgeCount()).isEqualTo(count);
    }
}