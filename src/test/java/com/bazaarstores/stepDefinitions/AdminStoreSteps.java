package com.bazaarstores.stepDefinitions;

import com.bazaarstores.pages.*;
import com.bazaarstores.utilities.ConfigReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class AdminStoreSteps {

    @When("Navigate to Stores page.")
    public void navigate_to_stores_page() {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickStores();
    }






    @When("Observe the list of stores displayed.")
    public void observe_the_list_of_stores_displayed() {

    }






    @Then("All existing stores are displayed correctly with Name, Description, Location, and Admins.")
    public void all_existing_stores_are_displayed_correctly_with_name_description_location_and_admins() {
        StoresPage storesPage = new StoresPage();

        for (WebElement row : storesPage.getAllStoreRows()) {
            String name = storesPage.getStoreCellText(row, 1);
            String description = storesPage.getStoreCellText(row, 2);
            String location = storesPage.getStoreCellText(row, 3);
            String admin = row.findElement(By.xpath("(./td[normalize-space()!=''])[last()]")).getText().trim();

            Assert.assertFalse( "Store name is missing", name.isEmpty());
            Assert.assertFalse("Description is missing for " + name, description.isEmpty());
            Assert.assertFalse("Admin name is missing for " + name, admin.isEmpty());

            System.out.printf("%s | %s | %s %n", name, description, location, admin);
        }

    }








    @When("Navigate to Add Store page")
    public void navigate_to_add_store_page() {
        StoresPage storesPage = new StoresPage();
        storesPage.clickAddstore();

    }







    @When("Leave one or more required fields empty:")
    public void leave_one_or_more_required_fields_empty(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("Name");
        String description = data.get("Description");
        String location = data.get("Location");
        String admin = data.get("Admin");

        AddStorePage addStorePage = new AddStorePage();
        addStorePage
                .enterName(name)
                .enterDescription(description)
                .enterLocation(location)
                .enterAdmins(admin);


    }






    @When("Click Save")
    public void click_save() {
        AddStorePage addStorePage = new AddStorePage();
        addStorePage.clickSubmit();


    }








    @Then("Error message is shown for missing required fields; store is not added")
    public void error_message_is_shown_for_missing_required_fields_store_is_not_added() {
        AddStorePage addStorePage = new AddStorePage();
        String actualMessage = addStorePage.getErrorMessage();

        System.out.println("Error message displayed: " + actualMessage);


        Assert.assertTrue(
                "Expected 'required' message, but got: " + actualMessage,
                actualMessage.contains("required")
                );

    }







    @When("Enter valid Name, Description, Location, and Admins :")
    public void enter_valid_name_description_location_and_admins(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("Name");
        String description = data.get("Description");
        String location = data.get("Location");
        String admin = data.get("Admin");


        AddStorePage addStorePage = new AddStorePage();


        addStorePage
                .enterName(name)
                .enterDescription(description)
                .enterLocation(location)
                .enterAdmins(admin)
        ;
    }








    @Then("The store is successfully added to the list, and a confirmation message is displayed.")
    public void the_store_is_successfully_added_to_the_list_and_a_confirmation_message_is_displayed() {
        StoresPage storesPage = new StoresPage();
        storesPage.getSuccessMessage();

        boolean found = storesPage.isStoreDisplayedInList("Extra", "Electronics store", "Store Manager");
        Assert.assertTrue("Store not displayed in the list after creation!", found);
    }





    @When("Click Edit for an existing store.")
    public void click_edit_for_an_existing_store() {
        StoresPage storesPage = new StoresPage();
        storesPage.clickEditButtonForStore(ConfigReader.getStoreName());
    }

    @When("Click Edit for an updated store.")
    public void click_edit_for_an_updated_store() {
        StoresPage storesPage = new StoresPage();
        storesPage.clickEditButtonForStore(ConfigReader.getStoreNewName());
    }







    @When("Enter valid Name, Location :")
    public void enter_valid_name_location() {
        EditStorePage editStorePage = new EditStorePage();

        editStorePage.enterName(ConfigReader.getStoreNewName()).enterDescription(ConfigReader.getStoreDes());
    }









    @Then("The store is successfully update to the list, and a confirmation message is displayed.")
    public void the_store_is_successfully_update_to_the_list_and_a_confirmation_message_is_displayed() {

        StoresPage storesPage = new StoresPage();
        storesPage.getupdateMessage();
    }











    @When("Try Leave one or more required fields empty:")
    public void try_leave_one_or_more_required_fields_empty(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> dataSets = dataTable.asMaps(String.class, String.class);

        EditStorePage editStorePage = new EditStorePage();
        AddStorePage addStorePage = new AddStorePage();

        for (Map<String, String> data : dataSets) {
            String name = data.get("Name");
            String description = data.get("Description");
            String location = data.get("Location");
            String admin = data.get("Admin");

            System.out.println("Trying with: " + name + ", " + description + ", " + location + ", " + admin);

            addStorePage
                    .enterName(name)
                    .enterDescription(description)
                    .enterLocation(location)
                    .enterAdmins(admin)
                    .clickSubmit()
            ;
            try {
                String msg = addStorePage.getErrorMessage();
                System.err.println("Error message: " + msg);
                Assert.assertTrue("Expected 'required' message but got: " + msg,
                        msg.toLowerCase().contains("required"));
            } catch (Exception e) {
                System.err.println("No error message found for dataset: " + data);
            }
        }
    }






    @When("Check the updated store.")
    public void check_the_updated_store() {
        StoresPage storesPage = new StoresPage();
        List<WebElement> storeRows = storesPage.getAllStoreRows();
        String expectedName = ConfigReader.getStoreNewName();
        String expectedDescription = ConfigReader.getStoreDes();

        boolean storeFound = false;
        for (WebElement row : storeRows) {
            String name = storesPage.getStoreCellText(row, 1).trim();
            String description = storesPage.getStoreCellText(row, 2).trim();

            if (name.equalsIgnoreCase(expectedName)) {
                System.out.printf("Found updated store: %s | %s %n",
                        name, description);

                Assert.assertEquals("Description not updated correctly",
                        expectedDescription, description);

                storeFound = true;
                break;
            }
        }

        Assert.assertTrue("Updated store not found in the list: " + expectedName, storeFound);
    }

    @When("Click Delete on a store.")
    public void click_delete_on_a_store()  {
        StoresPage storesPage = new StoresPage();
        storesPage.clickDeleteButtonForStore(ConfigReader.getStoreNewName());

    }
    @Then("Confirmation dialog appears asking for confirmation.")
    public void confirmation_dialog_appears_asking_for_confirmation() {
        StoresPage storesPage = new StoresPage();
        storesPage
                .DeleteMessageConform();

    }


    @Then("Click Yes, delete it on a store.")
    public void click_yes_delete_it_on_a_store()  {
        StoresPage storesPage = new StoresPage();

        storesPage.ConformDelete();

    }
    @Then("Confirm deletion, and store is removed from UI  list; success message shown.")
    public void confirm_deletion_and_store_is_removed_from_ui_list_success_message_shown()  {

        StoresPage storesPage = new StoresPage();
        storesPage.getDeleteMessage().isStorePresent(ConfigReader.getStoreNewName());
        System.out.println("Checking if store '" + ConfigReader.getStoreNewName() + "' still exists...");
        Assert.assertFalse(
                "Store '" + ConfigReader.getStoreNewName() + "' still exists in the list!",
               storesPage.isStorePresent(ConfigReader.getStoreNewName())
        );


    }



    @Then("Click Cancel in confirmation dialog.")
    public void click_cancel_in_confirmation_dialog() {
        StoresPage storesPage = new StoresPage();
        storesPage.CancelDelete();
    }
    @Then("Confirm store remains in the list.")
    public void confirm_store_remains_in_the_list() {
        StoresPage storesPage = new StoresPage();
        storesPage.isStorePresent(ConfigReader.getStoreNewName());
        System.out.println("Checking if store '" + ConfigReader.getStoreNewName() + "' still exists...");
        Assert.assertTrue(
                "Store '" + ConfigReader.getStoreNewName() + "' not exists in the list!",
                storesPage.isStorePresent(ConfigReader.getStoreNewName())
        );
        System.out.println("Store '" + ConfigReader.getStoreNewName() + "' still exists in the list!");


    }







}




