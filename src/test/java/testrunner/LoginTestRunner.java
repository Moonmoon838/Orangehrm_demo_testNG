package testrunner;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;

public class LoginTestRunner extends Setup {

    LoginPage loginPage;

    @Test(priority = 1, description = "Admin try to login with wrong creds", enabled = false)
    public void doLoginWithWrongCreds() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","WrongPass");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        Assert.assertTrue(textActual.contains("Invalid credentials"));
    }

    @Test (priority = 2, description = "Admin successfully login with valid creds")
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONArray empArray = Utils.readJSONList("./src/test/resources/employees.json");
        JSONObject empObj = (JSONObject) empArray.get(0);
        if((System.getProperty("username")!=null) && (System.getProperty("password")!=null)){
            loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        }
        else {
            loginPage.doLogin(empObj.get("username").toString(), empObj.get("password").toString());
        }

        //loginPage.doLogin("Admin", "admin123");
        Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed());
    }

    @Test (priority = 3, description = "Admin successfully logs out")
    public void doLogout(){
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        String textActual = driver.findElement(By.className("orangehrm-login-title")).getText();
        String textExcepted ="Login";
        Assert.assertEquals(textActual,textExcepted);

    }
}
