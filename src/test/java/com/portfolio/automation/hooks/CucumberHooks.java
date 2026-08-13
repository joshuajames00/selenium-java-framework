package com.portfolio.automation.hooks;

import com.portfolio.automation.driver.DriverFactory;
import com.portfolio.automation.utils.ScreenshotUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CucumberHooks {

    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("=== Starting scenario: {} ===", scenario.getName());
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.warn("=== Scenario FAILED: {} ===", scenario.getName());
            ScreenshotUtil.captureAndAttach(scenario.getName());
        } else {
            logger.info("=== Scenario PASSED: {} ===", scenario.getName());
        }
        DriverFactory.quitDriver();
    }
}