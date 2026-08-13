package com.portfolio.automation.stepdefinitions;

import com.portfolio.automation.pages.SecureAreaPage;
import io.cucumber.java.en.When;

public class SecureAreaSteps {

    @When("the user logs out")
    public void theUserLogsOut() {
        new SecureAreaPage().clickLogout();
    }
}
