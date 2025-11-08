package com.bazaarstores.pages.MangerPages;

import com.bazaarstores.pages.DashboardPage;
import org.openqa.selenium.By;

public class ProductsPage extends DashboardPage {
    private By addProductButton = By.cssSelector("a[href*='product/create']");
    private By editButton = By.xpath("//*[@id=\"table-bordered\"]/div/div/div/div/table/tbody/tr[6]/td[6]/button[1]");
    private By deleteButton = By.xpath("//*[@id=\"table-bordered\"]/div/div/div/div/table/tbody/tr[6]/td[6]/button[2]");

    public void clickEditButton(){
        return;
    }
    public void clickDeleteButton(){
        return;
    }

}
