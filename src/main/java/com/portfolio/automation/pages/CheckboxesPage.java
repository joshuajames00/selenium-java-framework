package com.portfolio.automation.pages;

import com.portfolio.automation.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(CheckboxesPage.class);

    private final By checkboxForm = By.id("checkboxes");
    private final By checkboxInputs = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage open() {
        String url = ConfigManager.getInstance().getBaseUrl() + "checkboxes";
        logger.info("Opening checkboxes page: {}", url);
        navigateTo(url);
        waitForElementVisible(checkboxForm);
        return this;
    }

    public boolean isChecked(int oneBasedIndex) {
        List<WebElement> checkboxes = driver.findElements(checkboxInputs);
        return checkboxes.get(oneBasedIndex - 1).isSelected();
    }

    public CheckboxesPage clickCheckbox(int oneBasedIndex) {
        logger.info("Clicking checkbox {}", oneBasedIndex);
        List<WebElement> checkboxes = driver.findElements(checkboxInputs);
        checkboxes.get(oneBasedIndex - 1).click();
        return this;
    }
}
