package com.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class DriverFactory {

    // ThreadLocal is required for running tests in parallel without issues
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    /**
     * Initializes the driver based on system property "browser".
     * Checks for Azure DevOps environment (TF_BUILD) to enable Headless mode.
     */
    public static WebDriver initDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        // TF_BUILD is a standard environment variable in Azure Pipelines
        boolean isCI = System.getenv("TF_BUILD") != null;

        WebDriver driver = null;

        if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (isCI) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }
            driver = new FirefoxDriver(options);
        } 
        else if (browser.equals("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
            }
            driver = new EdgeDriver(options);
        } 
        else {
            // Default to Chrome
            ChromeOptions options = new ChromeOptions();
            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage"); // Crucial for Linux/Docker
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--remote-allow-origins=*");
            }
            driver = new ChromeDriver(options);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Save the driver to ThreadLocal so getDriver() can find it
        tlDriver.set(driver);
        return getDriver();
    }

    /**
     * Returns the WebDriver instance for the current thread.
     * Required by Hooks.java and LoginSteps.java
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * Quits the driver and removes it from the current thread.
     * Required by Hooks.java
     */
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}