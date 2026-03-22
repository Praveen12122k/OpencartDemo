package pageObjects;

import basepage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
    //Constructor
   public MyAccountPage(WebDriver driver){
        super(driver);
    }

    //Locator
    @FindBy(xpath = "//h1[normalize-space()='My Account']") WebElement h2_heading;
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']") WebElement link_logout;

    //Action Method
    public boolean ismyaccountpagedisplayed(){
        try {
            return h2_heading.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public void logout(){
        Actions act= new Actions(driver);
        act.moveToElement(link_logout).click().build().perform();
      //  link_logout.click();
    }
}
