package com.portfolio.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class SecureAreaPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SecureAreaPage.class);

    private final By pageHeading = By.cssSelector(".example h2");
    private final By flashMessage = By.id("flash");
    private final By logoutButton = By.cssSelector("a.button.secondary");

    public String getHeading() {
        return getText(pageHeading);
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isFlashSuccessDisplayed() {
        try {
            waitForElementVisible(flashMessage);
            return driver.findElement(flashMessage).getAttribute("class").contains("success");
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage clickLogout() {
        logger.info("Clicking logout");
        click(logoutButton);
        return new LoginPage();
    }
}
