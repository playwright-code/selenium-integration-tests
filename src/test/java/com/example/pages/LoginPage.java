package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginBtn = By.id("login-button");
    private By errorMsg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) { super(driver); }

    public void enterUsername(String username) { sendKeys(usernameInput, username); }
    public void enterPassword(String password) { sendKeys(passwordInput, password); }
    public void clickLogin() { click(loginBtn); }
    public String getErrorMessage() { return getText(errorMsg); }
    public boolean isErrorDisplayed() { return isDisplayed(errorMsg); }
}