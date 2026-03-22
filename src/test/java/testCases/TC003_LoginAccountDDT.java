package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginAccountPage;
import pageObjects.MyAccountPage;
import testBase.BaseTest;
import utilities.DataProviders;

public class TC003_LoginAccountDDT extends BaseTest {

    @Test(dataProvider ="LoginData",dataProviderClass=DataProviders.class,groups = "DataDriven")
    public void LoginaccountDDT(String email,String password,String expresult){
        try{
        logger.info("LoginaccountDDT Test Execution Started");
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        hp.clicklogin();
        logger.info("User clicked on Login From Dropdown and Navigated to LoginAccount Page");

        LoginAccountPage lp = new LoginAccountPage(driver);
        lp.enteremail(email);
        lp.enterpassword(password);
        lp.clicklogin();

        MyAccountPage mp = new MyAccountPage(driver);
        boolean targetele=mp.ismyaccountpagedisplayed();

        /*
         * Data is valid--Successfull Login--Test Passed--Logout
         * Data is Valid--UnSuccessfull Login--Test failed
         * */

        if (expresult.equalsIgnoreCase("Valid")){
            if (targetele==true){
                mp.logout();
                Assert.assertTrue(true);
            }else {
                Assert.assertTrue(false);
            }
        }
        /*
        * Data is InValid--Successfull Login--Test failed
        * Data is InValid--UnSuccessfull Login--Test Passed
        *  */
        if (expresult.equalsIgnoreCase("Invalid")){
            if (targetele==false){
                Assert.assertTrue(true);
            }else {
                mp.logout();
                Assert.assertTrue(false);
            }
        }}catch (Exception e){
           Assert.fail();
        }
        logger.info("LoginaccountDDT Test Execution Completed");

    }
}
