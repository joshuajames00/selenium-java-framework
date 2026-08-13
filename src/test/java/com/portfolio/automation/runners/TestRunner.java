package com.portfolio.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "classpath:features",
    glue = {
        "com.portfolio.automation.stepdefinitions",
        "com.portfolio.automation.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "json:target/cucumber-reports/cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
    },
    tags = "not @wip",
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}