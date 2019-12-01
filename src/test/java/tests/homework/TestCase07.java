package tests.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

public class TestCase07 {

    private WebDriver driver;

    @BeforeMethod(description = "Set up testing page")

    public void setup(){

        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("http://practice.cybertekschool.com/");

    }

    @Test (description = "File upload")

    public void testCase07(){

        driver.findElement(By.linkText("File Upload")).click();

        driver.findElement(By.id("file-upload")).sendKeys("/Users/srhtgzr/Desktop/sublime");

        driver.findElement(By.id("file-submit")).click();

       WebElement title = driver.findElement(By.cssSelector("[class='example']>h3"));

        WebDriverWait wait = new WebDriverWait(driver,10);


        wait.until(ExpectedConditions.visibilityOf(title));

        String actualTitle = title.getText();

        String expectedTitle = "File Uploaded!";

        Assert.assertEquals(actualTitle,expectedTitle,"subject line is not file uploaded!");

        WebElement fileName = driver.findElement(By.id("uploaded-files"));

        System.out.println(fileName.getText());

        Assert.assertTrue(fileName.isDisplayed());

    }

    @Test(description = "Autocomplete")

    public void testcase08(){

        driver.findElement(By.linkText("Autocomplete")).click();

        driver.findElement(By.id("myCountry")).sendKeys("United States of America");

        String actualText = driver.findElement(By.cssSelector("[id='myCountryautocomplete-list']>div>strong")).getText();

        String expectedText = "United States of America";

        Assert.assertEquals(actualText,expectedText,"text is not United States of America");
    }

    @Test(description = "Status code verification")

    public void testCase09(){

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.cssSelector("[href='status_codes/200']")).click();

        WebElement statusCode = driver.findElement(By.cssSelector("[class='example']>p"));

        Assert.assertTrue(statusCode.isDisplayed());

    }


    @Test(description = "Status code verification 2")

    public void testCase10(){

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.cssSelector("[href='status_codes/301']")).click();

        WebElement statusCode = driver.findElement(By.cssSelector("[class='example']>p"));

        System.out.println(statusCode.getText());

        Assert.assertTrue(statusCode.isDisplayed());
    }

    @Test(description = "Status code verification 3")

    public void testCase11(){

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.cssSelector("[href='status_codes/404']")).click();

        WebElement statusCode = driver.findElement(By.cssSelector("[class='example']>p"));

        System.out.println(statusCode.getText());

        Assert.assertTrue(statusCode.isDisplayed());
    }

    @Test(description = "Status code verification 4")

    public void testCase12(){

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.cssSelector("[href='status_codes/500']")).click();

        WebElement statusCode = driver.findElement(By.cssSelector("[class='example']>p"));

        System.out.println(statusCode.getText());

        Assert.assertTrue(statusCode.isDisplayed());
    }














    @AfterMethod(description = "Teardown")

    public void teardown(){

        driver.quit();
    }



}
