package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

   //Constructor
   public HomePage(WebDriver driver){
        super(driver);
    }

   //Locators
   @FindBy(xpath = "//span[normalize-space()='My Account']") WebElement link_MyAccount;
   @FindBy(xpath = "//a[normalize-space()='Register']") WebElement link_Register;
   @FindBy(xpath = "//a[normalize-space()='Login']") WebElement link_login;

   //Action Methods
   public void clickMyAccount(){
       link_MyAccount.click();
   }
   public void clickRegister(){
       link_Register.click();
   }
   public void clicklogin(){
       link_login.click();
   }
}
