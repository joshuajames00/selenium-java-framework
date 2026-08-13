package com.portfolio.automation.pages;

import com.portfolio.automation.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(DropdownPage.class);

    private final By dropdownLocator = By.id("dropdown");

    public DropdownPage open() {
        String url = ConfigManager.getInstance().getBaseUrl() + "dropdown";
        logger.info("Opening dropdown page: {}", url);
        navigateTo(url);
        waitForElementVisible(dropdownLocator);
        return this;
    }

    public DropdownPage selectOption(String optionText) {
        logger.info("Selecting option: {}", optionText);
        WebElement element = waitForElementVisible(dropdownLocator);
        new Select(element).selectByVisibleText(optionText);
        return this;
    }

    public String getSelectedOption() {
        WebElement element = driver.findElement(dropdownLocator);
        return new Select(element).getFirstSelectedOption().getText();
    }
}
