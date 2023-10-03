package testrunner;

import config.Setup;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class DashboardTestRunner extends Setup {

    @BeforeTest
    public void login(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test (priority = 1)
    public void createUser(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.createUser("Alice", "Bob", "alice10", "P@ssword1234");
    }

}
