package com.bazaarstores.pages;

import com.bazaarstores.pages.CustomerPages.CustomerFavoritesPage;
import com.bazaarstores.pages.CustomerPages.CustomerPage;

public class AllPages {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private DashboardPage dashboardPage;
    private CustomerPage customerPage;
private CustomerFavoritesPage customerFavoritesPage;

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public RegistrationPage getRegistrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage();
        }
        return registrationPage;
    }

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null) {
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }
    public CustomerPage getCustomerPage() {
        if (customerPage == null) {
            customerPage = new CustomerPage();
        }
        return customerPage;
    }
    public CustomerFavoritesPage getCustomerFavoritesPage() {
        if (customerFavoritesPage == null) {
            customerFavoritesPage = new CustomerFavoritesPage();
        }
        return customerFavoritesPage;
    }
}