package tests.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class synch_Checkboxes_Locators_Testcases {

    private WebDriver driver;

    @BeforeMethod (description = "Setup")

    public void setUp(){

        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("https://qa1.vytrack.com/user/login");

        driver.findElement(By.name("_username")).sendKeys("storemanager85");

        driver.findElement(By.name("_password")).sendKeys("UserUser123");

        driver.findElement(By.id("_submit")).click();

        driver.navigate().to("https://qa1.vytrack.com/calendar/event");

//
//
//        WebElement mask = driver.findElement(By.cssSelector("[class='loader-mask']"));
//        wait.until(ExpectedConditions.invisibilityOf(mask));




    }


    @Test(description = "Verify that options is displayed")

    public void test01(){

       WebDriverWait wait = new WebDriverWait(driver,20);


        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='btn-group actions-group'] div")));

        WebElement options = driver.findElement(By.cssSelector("[class='btn-group actions-group'] div"));

        Assert.assertTrue(options.isDisplayed());
    }


    @Test(description = "Verify that page number is 1")

    public void test02(){

        WebDriverWait wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='number']")));

        WebElement pageNumber = driver.findElement(By.cssSelector("[type='number']"));

        String expected = pageNumber.getAttribute("value");


        String actualPageNumber ="1";

        Assert.assertEquals(expected,actualPageNumber, "page numebr is not 1");
    }


    @Test (description = "Verify that per page number is 25")


    public void test03(){

        WebDriverWait wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='btn dropdown-toggle ']")));

        WebElement pageNumber = driver.findElement(By.cssSelector("[class='btn dropdown-toggle ']"));

        String actual = pageNumber.getText();

        String expected ="25";

        Assert.assertEquals(actual,expected,"per page nummber is not 25");
    }


    @Test(description = "Verify that number of calendar events (rows in the table) is equals to number of records")

    public void test04(){

    WebDriverWait wait = new WebDriverWait(driver,20);

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dib:nth-of-type(3)")));

    int tableRow = driver.findElements(By.xpath("//table//tbody//tr")).size();

    int records = 20;

    Assert.assertEquals(tableRow,records, "records and tablerow don't match");

    }

    @Test(description = "Verify that all calendar events were selected")

    public void test05(){

        WebDriverWait wait = new WebDriverWait(driver,20);


       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dib:nth-of-type(3)")));

        int unselectedRow = driver.findElements(By.cssSelector("[class='grid-row row-click-action']")).size();

        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));

        wait.until(ExpectedConditions.invisibilityOf(mask));


        driver.findElement(By.cssSelector("[class='btn btn-default btn-small dropdown-toggle'] input")).click();

        int selectedRow = driver.findElements(By.cssSelector("[class='grid-row row-click-action row-selected']")).size();

        Assert.assertEquals(unselectedRow,selectedRow,"was not able to select");

    }

    @Test (description = "")

    public void test06(){

        WebDriverWait wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dib:nth-of-type(3)")));

        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));

        wait.until(ExpectedConditions.invisibilityOf(mask));

        driver.findElement(By.xpath("//table//tr[14]")).click();

        String actualTitle = driver.getTitle();

        String expectedTitle ="All - Calendar Events - Activities";

        Assert.assertEquals(actualTitle,expectedTitle,"message displayed");


    }












    @AfterMethod(description = "teardown")

    public void teardown(){

        driver.quit();
    }




}
