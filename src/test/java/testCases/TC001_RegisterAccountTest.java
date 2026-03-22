package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterAccountPage;
import testBase.BaseTest;

public class TC001_RegisterAccountTest extends BaseTest {

    @Test(groups ={"Regression","Master"})
    public void verify_account_registration(){
        logger.info("verify_account_registration Test Started");
        try{
        HomePage Home= new HomePage(driver);
        Home.clickMyAccount();
        Home.clickRegister();

        RegisterAccountPage Regpage= new RegisterAccountPage(driver);
        Regpage.enterFirstname(randomstring().toUpperCase());
        Regpage.enterLastname(randomstring().toUpperCase());
        Regpage.enterEmail(randomalphanumber()+"@gmail.com");

        Regpage.enterPassword(randomalphanumber());

        Regpage.selectIagree();
        logger.info("User Entered All Details and Clicked on I Agree ");
        Regpage.clickContinue();

        String confirmationmsg=Regpage.getConfirmationMsg();
        if (confirmationmsg.equals("Your Account Has Been Created!"))
        {
            Assert.assertTrue(true);
        }else {
            logger.error("Test Failed");
            logger.debug("Debug logs");//This will not be executed when root is on INFO
            Assert.assertTrue(false);
        }
        //Assert.assertEquals(confirmationmsg,"Your Account Has Been Created!");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        logger.info("verify_account_registration Test Completed");
    }

}
