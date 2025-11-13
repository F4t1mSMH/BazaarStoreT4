package com.bazaarstores.pages;

import com.bazaarstores.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoresPage extends BasePage {
//    private final By ListOfStores = By.xpath("//table[@class='table table-bordered mb-0']/tbody");
//    private final By storeName = By.xpath("//table[@class='table table-bordered mb-0']//tr[td[contains(normalize-space(), '%s')]]");

    private final By storeRows = By.xpath("//table[@class='table table-bordered mb-0']/tbody/tr");
    private final By addStoreButton = By.linkText("ADD STORE");
    private final By SuccessMessage =  By.cssSelector("div.toast-message");
    private final By AllList =  By.xpath("//table");
    private final By EidtButton = By.xpath(".//td[last()]//a[i[contains(@class,'bi-pencil-square')]]");
    private final By DeleteButton = By.xpath(".//button[contains(@onclick,'confirmDelete')]");
    private final By DeleteMessage = By.xpath("//div[@class='swal2-html-container']");
    private final By YesDelete = By.xpath("//button[contains(@class,'swal2-confirm')]");
    private final By CancelDeleteButton = By.xpath("//button[contains(@class,'swal2-cancel')]");
    private final By SuccessDeleteMessage = By.xpath("//div[contains(@class,'toast-message') and contains(.,'deleted successfully')]");
//    private final By rowOflist =  By.xpath("//table/tbody/tr");

    public List<WebElement> getAllStoreRows() {
        return Driver.getDriver().findElements(storeRows);
    }

    public String getStoreCellText(WebElement row, int columnIndex) {
        return row.findElement(By.xpath("./td[" + columnIndex + "]")).getText();
    }

    public AddStorePage clickAddstore() {
        click(addStoreButton);
        return new AddStorePage();
    }

    public StoresPage getSuccessMessage() {
        waitForElementToBeVisible(SuccessMessage);
      String  getSuccess =  getText(SuccessMessage);
        System.out.println("getSuccess = " + getSuccess);
        Assert.assertTrue(getSuccess.contains("Store created successfully!"));

        return this;
    }

    public StoresPage getupdateMessage() {
        waitForElementToBeVisible(SuccessMessage);
        String  getSuccess =  getText(SuccessMessage).trim();
        System.out.println("getupdate = " + getSuccess);
        Assert.assertTrue(getSuccess.contains("Store updated successfully!"));

        return this;
    }

    public boolean  isStoreDisplayedInList(String name, String description, String admin) {

        waitForElementToBeVisible(AllList);



        List<WebElement> rows = getAllStoreRows();

        for (WebElement row : rows) {
            String rowText = row.getText().trim();
            if (rowText.contains(name) &&
                    rowText.contains(description) &&
                    rowText.contains(admin)) {
                System.out.println("Store found in list: " + name);
                return true;
            }
        }
        System.out.println("Store not found in list: " + name);

        return false;
    }






    public EditStorePage clickEditButtonForStore(String storeName) {
        waitForElementToBeVisible(AllList);

        List<WebElement> rows = getAllStoreRows();

        for (WebElement row : rows) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));
            String currentName = nameCell.getText().trim();
            System.out.println("Stores found in list: " + currentName);

            if (currentName.equalsIgnoreCase(storeName.trim())) {
                WebElement editButton = row.findElement(EidtButton);
                new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(editButton));
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", editButton);
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", editButton);
                System.out.println("✅ Clicked Edit for store: " + storeName);
                return new EditStorePage();
            }
        }

        Assert.fail("Store not found in list: " + storeName);
        return null;
    }


    public StoresPage clickDeleteButtonForStore(String storeName) {
        waitForElementToBeVisible(AllList);

        for (WebElement row : getAllStoreRows()) {
            WebElement nameCell = row.findElement(By.xpath("./td[1]"));
            String currentName = nameCell.getText().trim();


            if (currentName.equalsIgnoreCase(storeName.trim())) {
                WebElement deleteButton = row.findElement(DeleteButton);
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", deleteButton);
                ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", deleteButton);
                System.out.println("Clicked Delete for store: " + storeName);
                return this;
            }
        }

            Assert.fail("Store not found in list: " + storeName);

        return this;}


    public StoresPage DeleteMessageConform() {
        isDisplayed(DeleteMessage);
        System.out.println("DeleteMessage = " + getText(DeleteMessage));
        return new StoresPage();
    }
    public StoresPage ConformDelete()  {

        click(YesDelete);
        return new StoresPage();
    }
    public StoresPage CancelDelete()  {

        click(CancelDeleteButton);
        return new StoresPage();
    }


    public StoresPage getDeleteMessage() {
        waitForElementToBeVisible(SuccessDeleteMessage);
        String  getDelete =  getText(SuccessDeleteMessage).trim();
        System.err.println("getDelete = " + getDelete);
        Assert.assertTrue(getDelete.contains("Store deleted successfully!"));

        return this;
    }


    public boolean isStorePresent(String storeName) {
        waitForElementToBeVisible(AllList);

        for (WebElement row : getAllStoreRows()) {
            String currentName = row.findElement(By.xpath("./td[1]")).getText().trim();

            if (currentName.equalsIgnoreCase(storeName.trim())) {
                return true;
            }
        }
        return false;
    }






}
