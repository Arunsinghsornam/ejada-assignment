package com.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.utils.DriverManager;
import com.utils.ConfigReader;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        String browser = System.getProperty("browser");

        if (browser == null || browser.isEmpty()) {
            browser = ConfigReader.getBrowser();
        }

        DriverManager.initDriver(browser);
    }

    @After
    public void tearDown(Scenario scenario) {
        DriverManager.quitDriver();
    }
}