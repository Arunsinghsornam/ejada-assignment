package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * FirefoxRunner - Executes all Cucumber tests on Firefox browser.
 * Configured via testng.xml for parallel execution.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/firefox/cucumber.html",
                "json:target/cucumber-reports/firefox/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "not @wip",
        monochrome = true
)
public class FirefoxRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        System.setProperty("browser", "firefox");
        return super.scenarios();
    }
}
