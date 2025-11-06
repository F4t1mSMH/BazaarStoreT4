package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FavoriteCustomerSteps {
    AllPages allPages = new AllPages();
    @When("Customer clicks the heart icon on a product")
    public void customer_clicks_the_heart_icon_on_a_product() throws InterruptedException {
        allPages.getCustomerPage().clickHeartIconOnFirstProduct();
        System.out.println(" the heart icon is clicked ");
    }
    @Then("The product should be marked as favorite")
    public void the_product_should_be_marked_as_favorite() {
        allPages.getCustomerPage().isProductMarkedAsFavorite();
        System.out.println(" the heart status is active ");
    }
    @Then("The heart icon should change to filled")
    public void the_heart_icon_should_change_to_filled() {
        Assert.assertTrue(" Product was not marked as favorite!",
                allPages.getCustomerPage().isProductMarkedAsFavorite());
        System.out.println(" the Product is marked as favorite ");
    }
//----------------------US07_002---------------------------------------------------------------
@When("Customer clicks the heart icon on the same product again")
public void customer_clicks_heart_icon_again() throws InterruptedException {
    allPages.getCustomerPage().clickHeartIconOnFirstProduct();
}

    @Then("Error message {string} should appear")
    public void error_message_should_appear(String expectedMessage) {
        Assert.assertTrue("Expected error message not visible!",
                allPages.getCustomerPage().isAlreadyFavoriteMessageDisplayed());
        System.out.println("Error message '" + expectedMessage + "' appeared successfully.");
        allPages.getCustomerPage().ClickCoolToAccpetAlreadyFavoriateMessage();
    }

}
