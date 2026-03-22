package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegisterAccountPage extends BasePage {
    //Constructor
    public RegisterAccountPage(WebDriver driver){
        super(driver);
    }
    //Locators
    @FindBy(id = "input-firstname") WebElement txtFirstname;
    @FindBy(id = "input-lastname") WebElement txtLastname;
    @FindBy(id = "input-email") WebElement txtEmail;
    @FindBy(id = "input-password") WebElement txtPassword;
    @FindBy(name = "agree") WebElement chkIagree;
    @FindBy(xpath = "//button[normalize-space()='Continue']") WebElement btnContinue;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;

    //Action Method
    public void enterFirstname(String fname){
        txtFirstname.sendKeys(fname);
    }
    public void enterLastname(String lname){
        txtLastname.sendKeys(lname);
    }
    public void enterEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void enterPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void selectIagree(){
       // chkIagree.submit();
       //chkIagree.click();
        Actions act = new Actions(driver);
        act.moveToElement(chkIagree).click().build().perform();
    }
    public void clickContinue(){
        btnContinue.click();
    }
    public String getConfirmationMsg(){
        try {
            return msgConfirmation.getText();
        }catch (Exception e){
            return(e.getMessage());
        }

    }
}
