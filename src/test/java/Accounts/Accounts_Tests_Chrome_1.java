package Accounts;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Accounts_Tests_Chrome_1 {

    WebDriver driver;
    WebDriverWait wait ;
    PageActions objAccountPage;

    @Test
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(chromeOptions);

        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://personal.nedbank.co.za/home.html");


        driver.findElement(By.xpath("//button[contains(text(),'Accept')]")).click();
        wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='nbd-qs-page-results'])[1]//p[1]")));

        driver.findElement(By.xpath("//div[@class='serach-icon-div']/button")).click();

        driver.findElement(By.xpath("(//input[contains(@data-placeholder,'search')])[1]")).sendKeys("Deposit");

        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        long seconds = (elapsedTime / 1000) % 60;

        System.out.println("Time taken to display result "+seconds);
        String result = driver.findElement(By.xpath("(//div[@class='nbd-qs-page-results'])[1]//p[1]")).getText();
        Assert.assertTrue("Results string not mached ",result.contains("Deposit"));

        driver.quit();

    }



}
