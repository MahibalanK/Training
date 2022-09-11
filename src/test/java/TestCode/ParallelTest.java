package TestCode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class ParallelTest {
    public WebDriver driver;

    @Ignore
    @Test
    public void EdgeTest() {
        //Initializing the edge driver (Gecko)
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://stg-banking.nedbank.co.za/home.html");


    }

    @Ignore
    @Test
    public void ChromeTest()
    {
        //Initialize the chrome driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://stg-banking.nedbank.co.za/home.html");
    }
}