package com.portfolio.automation.utils;

import com.portfolio.automation.constants.AppConstants;
import com.portfolio.automation.driver.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtil() {}

    public static void captureAndAttach(String scenarioName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            logger.warn("Cannot capture screenshot: WebDriver is null");
            return;
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot - " + scenarioName,
                "image/png", new ByteArrayInputStream(screenshot), "png");
            saveLocally(scenarioName, screenshot);
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for scenario '{}': {}", scenarioName, e.getMessage());
        }
    }

    private static void saveLocally(String scenarioName, byte[] screenshot) throws IOException {
        Path screenshotDir = Paths.get(AppConstants.SCREENSHOTS_DIR);
        Files.createDirectories(screenshotDir);

        String safeName = scenarioName.replaceAll("[^a-zA-Z0-9_-]", "_");
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        Path filePath = screenshotDir.resolve(safeName + "_" + timestamp + ".png");

        Files.write(filePath, screenshot);
        logger.info("Screenshot saved: {}", filePath.toAbsolutePath());
    }
}