package TestCode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Ignore;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionDemo {

    WebDriver driver ;
    Actions builder;
    Action mouseOver;
    JavascriptExecutor executor;
    WebDriverWait explicitWait;
    Robot robotobj ;

    /*
    *** do chrome driver setup ***
    */
    @BeforeClass
    public void setup(){
        //driver setup done here
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://stg-banking.nedbank.co.za/see-all-accounts/pay-as-you-use/rates-and-fees.html");
    }

    /*
     *** navigate to how to guides page ***
     */
    @Ignore
    @Test(enabled = false)
    public void goToHowToGuide() throws InterruptedException {

        WebElement accountsLnk = driver.findElement(By
                                .xpath("//a[@data-val='Accounts']"));


        builder = new Actions(driver);
        builder.moveToElement(accountsLnk).perform();
        //mouseOver.perform();

        //action instance for mouse over
       /* builder.doubleClick(accountsLnk).perform();

        builder.contextClick(accountsLnk).perform();
*/  WebElement guideLnk = driver.findElement(By.xpath("(//a[text()='How-to guides'])[5]"));
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",guideLnk);



        //js click here

    }

    /*
     *** change bg color,do key board actions ,scroll ***
     */
    @Ignore
    @Test(enabled = false)
    public void hightlightSearchBox() throws InterruptedException, AWTException {

        //click accept button
        explicitWait = new WebDriverWait(driver,50);
        explicitWait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//input[@placeholder='Search guides']")));
        WebElement acceptBtn =driver.findElement(By.
                xpath("//button[contains(text(),'Accept')]"));
        acceptBtn.click();

        //changing 'color' of search text box here
        WebElement guideSearchTxt = driver.findElement(By.
                xpath("//input[@placeholder='Search guides']"));
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].style.backgroundColor = 'pink'"
                ,guideSearchTxt);

        //keyboard action performed here using selenium inbuilt
        guideSearchTxt.sendKeys(Keys.SHIFT,"bank");

        //explicit wait for visibility
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//span[text()='rename your ']")));
        WebElement resultsLnk =driver.findElement(By.
                xpath("//span[text()='rename your ']"));
        Thread.sleep(3000);

        //performing keyboard actions
        guideSearchTxt.sendKeys(Keys.ARROW_DOWN);
        guideSearchTxt.sendKeys(Keys.ENTER);

        //scroll to element using js
        WebElement footerText = driver.findElement(By
                .xpath("//p[starts-with(text(),'Nedbank')]"));
        executor.executeScript("arguments[0].scrollIntoView(true)"
                ,footerText);

        footerText.getLocation();
        //do mouse move and right click using robot class
        Point elementLocation = footerText.getLocation();
        int x,y;
        x = elementLocation.getX();
        y = elementLocation.getY();
        System.out.println(elementLocation.getX()+" "+elementLocation.getY());

        robotobj = new Robot();
        robotobj.mouseMove(x, y-800);
        robotobj.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robotobj.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        Thread.sleep(2000);
        robotobj.keyPress(KeyEvent.VK_DOWN);
        robotobj.keyPress(KeyEvent.VK_ENTER);

        /*robotobj.keyPress(KeyEvent.VK_DOWN);
        robotobj.keyPress(KeyEvent.VK_DOWN);*/


    }

    @Ignore
    @Test(enabled = false)
    public void clickSlidder() throws InterruptedException {
        explicitWait = new WebDriverWait(driver,50);
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//output)[1]")));

        WebElement acceptBtn =driver.findElement(By.
                xpath("//button[contains(text(),'Accept')]"));
        acceptBtn.click();


        //scroll to element using js
        WebElement sliderText = driver.findElement(By
                .xpath("(//div[contains(text(),'preferred repayment term')])[1]"));

        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true)"
                ,sliderText);

        WebElement slidder = driver.findElement(By
                .xpath("(//div[@class='slidecontainer']//input)[1]"));


        builder = new Actions(driver);
        builder.moveToElement(slidder).moveByOffset(20,0).click().perform();

        WebElement loanAmount = driver.findElement(By
                .xpath("//input[@id='loanamount']"));
        String exAmt = loanAmount.getAttribute("value");
        System.out.println("loan amount "+exAmt);
        String amt = (String) executor.executeScript("arguments[0].value"
                ,loanAmount);
        System.out.println(amt);


    }

    @Ignore
    @Test
    public void getTableHeaderValues(){

        explicitWait = new WebDriverWait(driver,50);
        explicitWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("(//table)[1]//child::tr[1]/th")));

       List<WebElement> tableHeaderValues = driver.findElements(By.
                xpath("(//table)[1]//child::tr[1]/th/label"));

        int size = tableHeaderValues.size();
        System.out.println("Size of the headers "+size);

       for( int initialsize =0;initialsize<size; initialsize++ ) {
           System.out.println(tableHeaderValues.get(initialsize).getText());
       }

    }

}
