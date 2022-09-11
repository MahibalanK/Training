package Accounts;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Accounts_Tests_Chrome {

    WebDriver driver;
    WebDriverWait wait ;
    PageActions objAccountPage;

    @BeforeTest
    public void launch() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://author-nedbankstage.adobecqms.net/content/nedbank-stage/za/en/dev-test/global-search.html?wcmmode=disabled");
        driver.findElement(By.xpath("//div[@class=' coral3-Accordion-label']")).click();
        driver.findElement(By.xpath("//input[@placeholder='User name']")).sendKeys("harleen");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Welcome@Nedbankdev12");
        driver.findElement(By.xpath("//button[@id='submit-button']")).click();
        driver.navigate().refresh();
        driver.findElement(By.xpath("//button[contains(text(),'Accept')]")).click();
        wait = new WebDriverWait(driver,30);

        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='serach-icon-div']/button")).click();
        driver.findElement(By.xpath("(//input[contains(@data-placeholder,'search')])[1]")).sendKeys("Deposit");


       /* Instant start = Instant.now();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='nbd-qs-page-results'])[1]//p[1]")));
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
*/
        long start = System.currentTimeMillis();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='nbd-qs-page-results'])[1]//p[1]")));
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        long seconds = (elapsedTime / 1000) % 60;

        System.out.println("Time taken to display result "+seconds);
        String result = driver.findElement(By.xpath("(//div[@class='nbd-qs-page-results'])[1]//p[1]")).getText();
        Assert.assertTrue("Results string not mached ",result.contains("Deposit"));
        driver.quit();

    }


    @Test(invocationCount = 2,singleThreaded = false)
    public void setup() throws InterruptedException {

        System.out.println("Test done");

    }



}
