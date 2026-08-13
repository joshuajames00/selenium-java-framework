package com.portfolio.automation.stepdefinitions;

import com.portfolio.automation.pages.DropdownPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DropdownSteps {

    @Given("the user navigates to the dropdown page")
    public void theUserNavigatesToTheDropdownPage() {
        new DropdownPage().open();
    }

    @When("the user selects {string} from the dropdown")
    public void theUserSelectsFromTheDropdown(String option) {
        new DropdownPage().selectOption(option);
    }

    @Then("{string} should be selected in the dropdown")
    public void shouldBeSelectedInTheDropdown(String expectedOption) {
        String actual = new DropdownPage().getSelectedOption();
        Assert.assertEquals(actual, expectedOption,
            "Dropdown selection mismatch");
    }
}
