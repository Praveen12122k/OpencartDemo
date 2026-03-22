package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    public  WebDriver driver;
    public Logger logger;
    public Properties properties;
    @BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
    @Parameters({"browser","os"})
    public void setup(String browser ,String os) throws IOException {
        //Loading congif.properties File
        //FileReader readprofile=new FileReader("./src//test//resources//config.properties");
        //properties.load(readprofile);
        FileInputStream readprog=new FileInputStream("./src//test//resources//config.properties");
        properties= new Properties();
        properties.load(readprog);

        //Logger
        logger= LogManager.getLogger(this.getClass());

        //Grid Setup
        if (properties.getProperty("environment_env").equalsIgnoreCase("Remote")){
            DesiredCapabilities capabilities= new DesiredCapabilities();
            switch (os.toLowerCase()){
                case "windows": capabilities.setPlatform(Platform.WIN11); break;
                case "mac": capabilities.setPlatform(Platform.MAC); break;
                case "linux": capabilities.setPlatform(Platform.LINUX); break;
                default: System.out.println("No Matching OS"); return;
            }
            switch (browser.toLowerCase()){
                case  "chrome": capabilities.setBrowserName("chrome"); break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
                default: System.out.println("No Matching Browser"); return;
            }
            driver=new RemoteWebDriver(new URL(properties.getProperty("gridurl")),capabilities);
        }

        //Local
        if (properties.getProperty("environment_env").equalsIgnoreCase("local")){
            //Dynamic Browser Based on XML
            switch (browser.toLowerCase()){
                case "chrome":driver=new ChromeDriver(); break;
                case "edge": driver=new EdgeDriver(); break;
                default:
                    System.out.println("No driver is Initiated"); return;
            }
        }

        //driver=new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       // driver.get("http://localhost/opencart/");
        driver.get(properties.getProperty("appurl"));//reading values from properties file
        driver.manage().window().maximize();
    }
    @AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public  void teardown(){
        driver.quit();
    }
    public String randomstring(){
        String generatedtxt= RandomStringUtils.randomAlphabetic(5);
        return generatedtxt;
    }
    public String randomnumber(){
        String generatedtxt= RandomStringUtils.randomNumeric(3);
        return generatedtxt;
    }
    public String randomalphanumber(){
        String generatedtxt= RandomStringUtils.randomAlphanumeric(8);
        return generatedtxt;
    }
    public String captureScreen(String tname){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        File sourcefile=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String tagetfielpath=System.getProperty("user.dir")+"\\screenshots\\"+tname+" "+timeStamp;
        File targetfile= new File(tagetfielpath);
        sourcefile.renameTo(targetfile);
        return tagetfielpath;
    }
}
