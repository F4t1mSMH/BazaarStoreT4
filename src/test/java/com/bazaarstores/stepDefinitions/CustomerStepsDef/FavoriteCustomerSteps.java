package com.bazaarstores.stepDefinitions.CustomerStepsDef;

import com.bazaarstores.pages.AllPages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FavoriteCustomerSteps {
    AllPages allPages = new AllPages();

//----------------------US07_001---------------------------------------------------------------

    @When("Customer clicks the heart icon on a product")
    public void customer_clicks_the_heart_icon_on_a_product() throws InterruptedException {
        allPages.getCustomerFavoritesPage().clickHeartIconOnFirstProduct();
        System.out.println(" the heart icon is clicked ");
    }
    @Then("The product should be marked as favorite")
    public void the_product_should_be_marked_as_favorite() {
        allPages.getCustomerPage().isProductMarkedAsFavorite();
        System.out.println(" the heart status is active ");
    }
    @And("The heart icon should change to filled")
    public void the_heart_icon_should_change_to_filled() {
        Assert.assertTrue(" Product was not marked as favorite!",
                allPages.getCustomerPage().isProductMarkedAsFavorite());
        System.out.println(" the Product is marked as favorite ");
    }
//----------------------US07_002---------------------------------------------------------------
@And("Customer clicks the heart icon on the same product again")
public void customer_clicks_heart_icon_again() throws InterruptedException {
    allPages.getCustomerFavoritesPage().clickHeartIconOnFirstProduct();
    System.out.println(" the heart icon is clicked again ");
}

    @Then("Error message {string} should appear")
    public void error_message_should_appear(String expectedMessage) {
        Assert.assertTrue("Expected error message not visible!",
                allPages.getCustomerPage().isAlreadyFavoriteMessageDisplayed());
        System.out.println("Error message '" + expectedMessage + "' appeared successfully.");
        allPages.getCustomerPage().ClickCoolToAccpetAlreadyFavoriateMessage();
    }
//----------------------------------------US07_003------------------------------------------------------------
@And("Customer navigates to My Favorites page")
public void customer_navigates_to_my_favorites_page() {
    allPages.getCustomerPage().clickFavoritesLink();
}
    @Then("Customer has at least one product marked as favorite")
    public void customer_has_at_least_one_product_marked_as_favorite() {
        boolean Atleast1Favorites = allPages.getCustomerFavoritesPage().hasFavoriteProducts();
        Assert.assertTrue("Customer has no favorite products!", Atleast1Favorites);
        System.out.println(" Customer has at least one favorite product");
    }

    @And("All favorite products should appear on Favorites page")
    public void all_favorite_products_should_appear_on_favorites_page() {
        Assert.assertTrue("No favorite products displayed!",
                allPages.getCustomerFavoritesPage().hasFavoriteProducts());
        System.out.println(" All favorite products are visible on Favorites page"+ "  " + allPages.getCustomerFavoritesPage().getFavoriteProductNames());


    }
//-----------------------------------------------US07_004---------------------------------------------------------


    @And("Customer clicks the heart icon on a favorite product on My Favorites page")
    public void customer_clicks_the_heart_icon_on_a_favorite_product_on_my_favorites_page() throws InterruptedException {

//        allPages.getCustomerFavoritesPage().clickHeartIconOnFirstProduct();
//        System.out.println(" Clicked heart icon on My Favorites page to remove product");

        allPages.getCustomerFavoritesPage().removeAllFavoriteProducts();


    }

    @Then("The product should be removed from Favorites list")
    public void the_product_should_be_removed_from_favorites_list() {
        boolean stillExists = allPages.getCustomerFavoritesPage().hasFavoriteProducts();
        Assert.assertFalse(" Product was not removed from favorites!", stillExists);

    }
    @And("Empty message should be displayed on the page")
    public void empty_message_should_be_displayed_on_the_page() {
        boolean emptyMsgVisible = allPages.getCustomerFavoritesPage().isEmptyMessageDisplayed();
        Assert.assertTrue(" Empty message not displayed after removing favorite product!", emptyMsgVisible);
        System.out.println("Empty message displayed successfully: 'There's no favorite product'");
    }

    @And("Heart icon status should update on the home page")
    public void heart_icon_status_should_update_on_the_home_page() {
        allPages.getCustomerPage().clickHomeLink();
        allPages.getCustomerPage().verifyHeartIconUnfilled();
        System.out.println("Heart icon status updated correctly (unfilled)");
    }


}

