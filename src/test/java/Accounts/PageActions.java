package Accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class PageActions {
    WebDriver driver;
    Actions builder;
    Action mouseOver;
    JavascriptExecutor executor;
    AccountPage page;

    public PageActions(WebDriver driver){
        this.driver = driver;
         page = new AccountPage(driver);
    }

    /**
     * get all data from the table
     */
    public void getTableData(String strTableXpath){
       //"(//table)[1]"+"//tr"=>(//table)[1]//tr
        String tableRowsXpath, rowData;
        tableRowsXpath = strTableXpath+"//tr" ;
        //store all table rows data
        List<String> allRowsData;
        allRowsData = new ArrayList<String>();

        //get row size
        int totalRows = driver.findElements(By.xpath(tableRowsXpath)).size();
        if(totalRows > 0){
            for (int initialRow =1; initialRow<totalRows ; initialRow++){
                rowData = driver.findElement(By.xpath(tableRowsXpath + "["+initialRow+"]")).getText();
                allRowsData.add(rowData);
            }
        }
        System.out.println("Table data are "+allRowsData.toString());
    }


    public void mouseHoverPersonal() throws InterruptedException
    {
        if (page.personalLnk.isDisplayed())
        {
            builder = new Actions(driver);
            mouseOver = builder.moveToElement(page.personalLnk).build();
            mouseOver.perform();
            Thread.sleep(3000);
            System.out.println("MouseOver on Personal Loan tab");
        }

    }

    /**
     method to click Accept button
     **/
    public void clickAcceptButton() throws InterruptedException
    {
        //Assert.assertEquals(page.acceptBtn.isDisplayed(),true);
        page.acceptBtn.click();
        Thread.sleep(3000);
        System.out.println("Accept button is clicked");
    }

    /**
     method to click Fixed Loan Link
     **/
    public void clickfixedLoanLnk() throws InterruptedException
    {
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",page.fixedLoanLnk);
        Thread.sleep(3000);
        System.out.println("Fixed Loan link is clicked successfully");
    }
    /**
     method for scrolling down to specific block
     **/
    public void scrollDown() throws InterruptedException
    {
        executor.executeScript("arguments[0].scrollIntoView(true)",page.footerText);
        page.footerText.getLocation();
        Thread.sleep(3000);
        System.out.println("Scroll down to Calculator Block Successfully");
    }

    /**
     method to verify Loan Amount
     **/
    public void loanAmount() throws InterruptedException
    {
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].style.backgroundColor = 'Orange'",page.loanAmntValue);
        System.out.println("Text box colored is changed to Orange");
        Thread.sleep(3000);

        //String actualTitle=page.loanAmnt.getText();
        String value=page.loanAmntR.getText();
        System.out.println(value);
        String actualTitle = page.loanAmntValue.getAttribute("value");
        System.out.println(actualTitle);
        String expectTitle="2000";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected title is matched");

    }

    /**
     method to verify Monthly Amount
     **/
    public void monthAmount() throws InterruptedException
    {
        String actualTitle=page.monthlyAmnt.getText();
        System.out.println(actualTitle +" Monthly Amount");
        String expectTitle="R197.30";
        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Monthly Amount is matched");

    }

    /**
     method to verify Total Amount
     **/
    public void totalAmount() throws InterruptedException
    {
        String actualTitle=page.totalAmnt.getText();
        System.out.println(actualTitle +" Total Amount");
        String expectTitle="R4,735.20";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Total Amount is matched");

    }

    /**
     method to verify Interest Rate
     **/
    public void interestRate() throws InterruptedException
    {
        String actualTitle=page.interestRate.getText();
        System.out.println(actualTitle + " Interest Rate");
        String expectTitle="18%";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Interest Rate is matched");

    }

    /**
     method to click Radio button
     **/
    public void clickRadioButton() throws InterruptedException
    {
        String actualTitle=page.radioBtn.getText();
        System.out.println(actualTitle + " of Radio Button");
        String expectTitle="I have my own insurance";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Text is matched");

        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",page.radioBtn);
        Thread.sleep(6000);
        System.out.println("I have my own insurance button is clicked");
    }

    /**
     method to click Explore Rate
     **/
    public void clickExploreRate() throws InterruptedException
    {
        String actualTitle=page.exploreRate.getText();
        System.out.println(actualTitle);
        String expectTitle="Explore rate";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Explore Rate Text is matched");

        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",page.exploreRate);
        Thread.sleep(3000);
        System.out.println("Explore rate is clicked and expanded successfully");
    }

    /**
     method to click See Loan Details
     **/
    public void clickSeeLoanDetails() throws InterruptedException
    {
        String actualTitle=page.seeLoanDetails.getText();
        System.out.println(actualTitle);
        String expectTitle="See loan details";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected See loan details Text is matched");

        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",page.seeLoanDetails);
        Thread.sleep(3000);
        System.out.println("See Loan details is clicked and expanded successfully");
    }

    /**
     method to click Close Loan Details
     **/
    public void clickCloseLoanDetails() throws InterruptedException
    {
        String actualTitle=page.closeLoanDetails.getText();
        System.out.println(actualTitle);
        String expectTitle="Close loan details";

        Assert.assertEquals(actualTitle,expectTitle);
        System.out.println("Actual and Expected Close loan details Text is matched");

        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();",page.closeLoanDetails);
        Thread.sleep(3000);
        System.out.println("Close Loan details is clicked and closed successfully");
    }

    public void scrollBar() throws InterruptedException {

        builder.moveToElement(page.scrollBar).moveByOffset(20,0).click().perform();
        System.out.println("Scroll Bar is moved to three years");
    }

}
