package com.portfolio.automation.stepdefinitions;

import com.portfolio.automation.pages.LoginPage;
import com.portfolio.automation.pages.SecureAreaPage;
import com.portfolio.automation.utils.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    @Given("the user navigates to the login page")
    public void theUserNavigatesToTheLoginPage() {
        new LoginPage().open();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        LoginPage page = new LoginPage();
        page.enterUsername(username);
        page.enterPassword(password);
        page.clickLoginExpectingFailure();
    }

    @Given("the user logs in with valid credentials")
    public void theUserLogsInWithValidCredentials() {
        LoginPage page = new LoginPage();
        page.enterUsername(TestData.VALID_USERNAME);
        page.enterPassword(TestData.VALID_PASSWORD);
        page.clickLoginExpectingFailure();
    }

    @Then("the secure area should be displayed")
    public void theSecureAreaShouldBeDisplayed() {
        String heading = new SecureAreaPage().getHeading();
        Assert.assertTrue(heading.contains("Secure Area"),
            "Expected 'Secure Area' heading but found: " + heading);
    }

    @And("a login success message should be shown")
    public void aLoginSuccessMessageShouldBeShown() {
        Assert.assertTrue(new SecureAreaPage().isFlashSuccessDisplayed(),
            "Expected a login success flash message on the secure area page");
    }

    @Then("an error message should indicate the credentials are invalid")
    public void anErrorMessageShouldIndicateCredentialsAreInvalid() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isFlashMessageDisplayed(),
            "Flash error message should be visible");
        Assert.assertTrue(loginPage.getFlashMessage().contains("invalid"),
            "Expected 'invalid' in error message");
    }

    @Then("the user should be on the login page")
    public void theUserShouldBeOnTheLoginPage() {
        Assert.assertTrue(new LoginPage().getCurrentUrl().contains("login"),
            "Expected URL to contain 'login' after logout");
    }
}
