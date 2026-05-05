package com.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features/api.feature",
        glue = {"com.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/api/cucumber.html",
                "json:target/cucumber-reports/api/cucumber.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        		tags = "@api",
        monochrome = true
)
public class ApiRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
