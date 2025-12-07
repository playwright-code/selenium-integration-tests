package com.example.stepdefinitions;

import com.example.utils.ConfigReader;
import com.example.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    @Before
    public void setup() {
        ConfigReader.loadConfig();
        DriverFactory.initDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        // FIXED: Only take screenshot if driver is not null
        if (scenario.isFailed() && DriverFactory.getDriver() != null) {
            final byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        }
        DriverFactory.quitDriver();
    }
}