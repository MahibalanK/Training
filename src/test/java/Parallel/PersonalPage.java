package Parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PersonalPage
{
    WebDriver driver;
    PropertiesConfiguration objPersonalPage;
    BrowserConfiguration bc;

    @Parameters("browser")
    @BeforeTest
    public void setup(String browser)
    {
        bc=new BrowserConfiguration(driver);
        if(browser.equalsIgnoreCase("Edge"))
        {
            bc.getEdge();

        }
        else if (browser.equalsIgnoreCase("Chrome"))
        {

            bc.getChrome();

        }
        else
        {
            System.out.println("Invalid Browser");
        }
    }
    @Test
    public void fixedLoanLnk() throws InterruptedException
    {
        System.out.println("Test is called");
        objPersonalPage = new PropertiesConfiguration(driver);  //Object for Properties file is created
        objPersonalPage.clickAcceptButton();
        objPersonalPage.mouseHoverPersonal();
        objPersonalPage.clickfixedLoanLnk();
        objPersonalPage.clickAcceptButton();
        objPersonalPage.scrollDown();
        objPersonalPage.loanAmount();
        objPersonalPage.monthAmount();
        objPersonalPage.totalAmount();
        objPersonalPage.interestRate();
        objPersonalPage.scrollBar();
        objPersonalPage.clickRadioButton();
        objPersonalPage.clickExploreRate();
        objPersonalPage.clickSeeLoanDetails();
        objPersonalPage.clickCloseLoanDetails();

    }
    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
        System.out.println("Browser closed sucessfully");
    }

}