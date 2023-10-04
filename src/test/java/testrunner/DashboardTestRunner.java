package testrunner;

import com.github.javafaker.Faker;
import config.EmployeeModel;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class DashboardTestRunner extends Setup {

    @BeforeTest
    public void login(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }

    @Test (priority = 1)
    public void createUser() throws IOException, ParseException, InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String password = faker.internet().password();

        EmployeeModel model = new EmployeeModel();
        model.setFirstname(firstName);
        model.setLastname(lastName);
        model.setUsername(username);
        model.setPassword(password);

        dashboardPage.createUser(model);

        Thread.sleep(7000);
        String textTitleExcepted = driver.findElement(By.xpath("//*[contains(text(),\"Personal Details\")]")).getText();
        if(textTitleExcepted.contains("Personal Details")){
            Utils.saveEmployeeInfo(model);
        }
    }

}
