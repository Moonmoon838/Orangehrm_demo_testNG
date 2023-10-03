import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner extends Setup{

    LoginPage loginPage;

    @Test(priority = 1, description = "Admin try to login with wrong creds")
    public void doLoginWithWrongCreds() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin","WrongPass");
        String textActual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        Assert.assertTrue(textActual.contains("Invalid credentials"));
    }

    @Test (priority = 2, description = "Admin successfully login with valid creds")
    public void doLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
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
