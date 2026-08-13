package com.portfolio.automation.pages;

import com.portfolio.automation.config.ConfigManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("button.radius");
    private final By flashMessage = By.id("flash");

    public LoginPage open() {
        String url = ConfigManager.getInstance().getBaseUrl() + "login";
        logger.info("Opening login page: {}", url);
        navigateTo(url);
        return this;
    }

    public LoginPage enterUsername(String username) {
        logger.info("Entering username");
        type(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        logger.info("Entering password");
        type(passwordField, password);
        return this;
    }

    public SecureAreaPage clickLogin() {
        logger.info("Clicking login button");
        click(loginButton);
        return new SecureAreaPage();
    }

    public LoginPage clickLoginExpectingFailure() {
        logger.info("Clicking login button");
        click(loginButton);
        return this;
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isFlashMessageDisplayed() {
        try {
            waitForElementVisible(flashMessage);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFlashError() {
        try {
            return driver.findElement(flashMessage).getAttribute("class").contains("error");
        } catch (Exception e) {
            return false;
        }
    }
}
