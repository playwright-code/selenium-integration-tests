package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By zipInput = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By finishBtn = By.id("finish");
    private By summaryContainer = By.className("checkout_summary_container");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) { super(driver); }

    public void enterDetails(String fName, String lName, String zip) {
        sendKeys(firstNameInput, fName);
        sendKeys(lastNameInput, lName);
        sendKeys(zipInput, zip);
    }
    
    public void clickContinue() { click(continueBtn); }
    public boolean isSummaryDisplayed() { return isDisplayed(summaryContainer); }
    public void clickFinish() { click(finishBtn); }
    public String getCompleteMessage() { return getText(completeHeader); }
}