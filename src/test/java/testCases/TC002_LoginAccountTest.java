package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginAccountPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;

public class TC002_LoginAccountTest extends BaseTest {

    @Test(groups ={"Sanity","Master"})
    public void verify_account_login(){
        logger.info("User is on Home Page");
        try {
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            hp.clicklogin();
            logger.info("User clicked on Login From Dropdown and Navigated to LoginAccount Page");

            LoginAccountPage lp = new LoginAccountPage(driver);
            lp.enteremail(properties.getProperty("email"));
            lp.enterpassword(properties.getProperty("password"));
            lp.clicklogin();
            logger.info("User Successfully Logged in ");

            MyAccountPage mp = new MyAccountPage(driver);
            Assert.assertTrue(mp.ismyaccountpagedisplayed(), "Login Falied");
            mp.logout();
            logger.info("User logged Out Successfully");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
        logger.info("verify_account_login Successfully Executed");

    }
}
