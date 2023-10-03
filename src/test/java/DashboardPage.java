import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DashboardPage {
    @FindBy(className = "oxd-main-menu-item--name")
    public List<WebElement> menuItems;

    @FindBy(className = "oxd-button")
    List<WebElement> buttons;

    @FindBy(className = "oxd-input")
    List<WebElement> formTextFields;

    @FindBy(className = "oxd-switch-input")
    WebElement btnSwitch;

    public DashboardPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void createUser(String firstName, String lastName, String username, String password){
        menuItems.get(1).click(); //click PIM
        buttons.get(2).click(); //click add button
        formTextFields.get(1).sendKeys(firstName);
        formTextFields.get(3).sendKeys(lastName);
        btnSwitch.click(); //toggle switch
        formTextFields.get(5).sendKeys(username);
        formTextFields.get(6).sendKeys(password);
        formTextFields.get(7).sendKeys(password); //confirm password
        buttons.get(1).click(); //save data
    }
}
