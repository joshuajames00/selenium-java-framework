package com.portfolio.automation.stepdefinitions;

import com.portfolio.automation.pages.CheckboxesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckboxSteps {

    @Given("the user navigates to the checkboxes page")
    public void theUserNavigatesToTheCheckboxesPage() {
        new CheckboxesPage().open();
    }

    @When("the user checks checkbox {int}")
    public void theUserChecksCheckbox(int index) {
        CheckboxesPage page = new CheckboxesPage();
        if (!page.isChecked(index)) {
            page.clickCheckbox(index);
        }
    }

    @When("the user unchecks checkbox {int}")
    public void theUserUnchecksCheckbox(int index) {
        CheckboxesPage page = new CheckboxesPage();
        if (page.isChecked(index)) {
            page.clickCheckbox(index);
        }
    }

    @Then("checkbox {int} should be checked")
    public void checkboxShouldBeChecked(int index) {
        Assert.assertTrue(new CheckboxesPage().isChecked(index),
            "Checkbox " + index + " should be checked");
    }

    @Then("checkbox {int} should not be checked")
    public void checkboxShouldNotBeChecked(int index) {
        Assert.assertFalse(new CheckboxesPage().isChecked(index),
            "Checkbox " + index + " should not be checked");
    }
}
