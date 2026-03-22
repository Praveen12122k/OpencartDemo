package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAccountPage extends BasePage {

    //Constructor
     public LoginAccountPage(WebDriver driver){
         super(driver);
     }

     //Locator
    @FindBy(xpath = "//input[@id='input-email']") WebElement input_email;
    @FindBy(xpath = "//input[@id='input-password']") WebElement input_password;
    @FindBy(xpath = "//button[normalize-space()='Login']") WebElement btn_login;

    //Action Method
    public  void enteremail(String email){
        input_email.sendKeys(email);
    }
    public  void enterpassword(String password){
        input_password.sendKeys(password);
    }
    public  void clicklogin(){
        btn_login.click();
    }

}
