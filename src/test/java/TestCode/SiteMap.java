package TestCode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SiteMap extends UrlCheck{
    public static WebDriver driver;
    public static void main(String args[]) throws IOException, InterruptedException {

       //String Url = "https://stg-business.nedbank.co.za/sitemap.xml";
       // String Url = "https://personal.nedbank.co.za/sitemap.xml";
        String Url = "https://stg-business.nedbank.co.za/sitemap.xml";
        //String Url = "https://business.nedbank.co.za/sitemap.xml";
        //String Url = "https://private.nedbank.co.za/sitemap.xml";


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to(Url);
        Thread.sleep(8000);
        String actualUrl ;
        List<String> Urls = new LinkedList<>();
        List<WebElement> UrlsElement = driver.findElements(By.cssSelector("div>span"));
        for (WebElement Ele:UrlsElement) {
            actualUrl = Ele.getText();
            if(actualUrl.startsWith("https")) {
                Urls.add(Ele.getText());
            }
        }

        System.out.println("SOURCE....."+Urls.size());
        driver.close();
        driver.quit();

        for (int i = 0; i < Urls.size(); i++) {
                writeExcelWithStatus(Urls.get(i),i,0);
        }

    }
}