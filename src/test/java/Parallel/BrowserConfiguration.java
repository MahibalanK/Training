package Parallel;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class BrowserConfiguration
{
    WebDriver driver;
    public BrowserConfiguration(WebDriver driver)
    {
        this.driver=driver;
    }
    public void getEdge()
    {

        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Window is maximised");

        driver.get("https://stg-banking.nedbank.co.za/home.html");
        System.out.println("Site Launched Successfully");

    }
    public void getChrome()
    {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Window is maximised");

        driver.get("https://stg-banking.nedbank.co.za/home.html");
        System.out.println("Site Launched Successfully");

    }
}
