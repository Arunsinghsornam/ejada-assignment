package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/chrome/cucumber.html",
                "json:target/cucumber-reports/chrome/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        tags = "not @wip",
        monochrome = true
)
public class ChromeRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
	    return super.scenarios();
	}
}
