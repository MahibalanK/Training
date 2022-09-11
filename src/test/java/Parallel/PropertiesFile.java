package Parallel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class PropertiesFile<list>
{
    WebDriver driver;
    Actions builder;
    Action mouseOver;
    JavascriptExecutor executor;

    @FindBy(xpath = "//a[@data-val='Personal loans']")
    WebElement personalLnk;

    @FindBy(xpath = "//a[@data-parent='Personal loans']")
    WebElement fixedLoanLnk;

    @FindBy(xpath="//button[contains(text(),'Accept')]")
    WebElement acceptBtn;

    @FindBy(xpath="//span[@class='nbd-currency-input']")
    WebElement loanAmntR;

    @FindBy(xpath="//input[@id='loanamount']")
    WebElement loanAmntValue;

    @FindBy(xpath="//li[@data-pagequicklinkname='Repayments calculator']")
    WebElement footerText;

    @FindBy(xpath="//p[contains(text(),'R197.30')]")
    WebElement monthlyAmnt;

    @FindBy(xpath="//p[contains(text(),'R4,735.20')]")
    WebElement totalAmnt;

    @FindBy(xpath="//div[contains(text(),'18%')]")
    WebElement interestRate;

    @FindBy(xpath = "(//div[@class='slidecontainer']//input)")
    WebElement scrollBar;

    @FindBy(xpath = "//label[@for='personal-insurance-no']")
    WebElement radioBtn;

    @FindBy(xpath = "//button[@data-target='#interest-rate']")
    WebElement exploreRate;

    @FindBy(xpath = "//a[@data-toggle='modal']")
    WebElement seeLoanDetails;

    @FindBy(xpath = "//a[@data-dismiss='modal']")
    WebElement closeLoanDetails;

    /**
     Constructor to initialize the page elements
     **/

    public PropertiesFile(WebDriver driver)
    {

        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
}
