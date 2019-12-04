package tests.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

import java.util.List;

public class VyTrackCalendarEvents {

    private WebDriver driver;

    private WebDriverWait wait;

    private WebElement mask;


    @BeforeMethod(description = "Setup")

    public void setUp() {

        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("https://qa1.vytrack.com/user/login");

        driver.findElement(By.name("_username")).sendKeys("storemanager85");

        driver.findElement(By.name("_password")).sendKeys("UserUser123");

        driver.findElement(By.id("_submit")).click();

        driver.navigate().to("https://qa1.vytrack.com/calendar/event");

        wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dib:nth-of-type(3)")));

        mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));

        wait.until(ExpectedConditions.invisibilityOf(mask));



    }

    @Test(description = "Verify that “view”, “edit” and “delete” options are available")

    public void test01(){

        WebDriverWait wait = new WebDriverWait(driver,20);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".dib:nth-of-type(3)")));

        WebElement mask = driver.findElement(By.cssSelector("[class='loader-mask']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));

        Actions action = new Actions(driver);

        WebElement threeDots = driver.findElement(By.xpath("//tbody//tr[14]//td[9]"));
        action.moveToElement(threeDots).perform();

        WebElement option =driver.findElement(By.cssSelector("a[href='/calendar/event/update/184']"));

        Assert.assertTrue(option.isDisplayed());

        }

        @Test(description = "Verify that “Title” column still displayed")

        public void test02(){



            driver.findElement(By.cssSelector("[class='fa-cog hide-text']")).click();


            String [] unSelect ={"column-c104", "column-c105", "column-c106", "column-c107", "column-c108", "column-c109"};


            for(String each: unSelect){

                driver.findElement(By.id(each)).click();

            }

            WebElement title = driver.findElement(By.cssSelector("[class='grid-header-cell__label']"));

            Assert.assertTrue(title.isDisplayed());

        }

        @Test(description = "Verify that “Save And Close”, “Save And New” and “Save” options are available")

        public void test03(){

        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();

        mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));

        wait.until(ExpectedConditions.invisibilityOf(mask));

        driver.findElement(By.cssSelector("[class='btn-success btn dropdown-toggle']")).click();

        WebElement option;

        for(int i=0; i<=3;i++){

            option = driver.findElement(By.xpath("//li//button[contains(text(),'Save')]"));

            Assert.assertTrue(option.isDisplayed(),"Option" +i+"is not Display");

        }

        }

        @Test (description = "Verify that “All Calendar Events” page subtitle is displayed")


        public void test04(){

            driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();

            mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));

            wait.until(ExpectedConditions.invisibilityOf(mask));

            driver.findElement(By.cssSelector("[title='Cancel']")).click();

            wait.until(ExpectedConditions.invisibilityOf(mask));

            WebElement subTitle = driver.findElement(By.cssSelector("[class='oro-subtitle']"));

            Assert.assertTrue(subTitle.isDisplayed());

        }













        @AfterMethod(description = "Clean up")

    public void teardown(){

        driver.quit();
        }



}