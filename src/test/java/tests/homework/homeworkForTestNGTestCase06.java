package tests.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class homeworkForTestNGTestCase06 {

    private WebDriver driver;

    @Test(description = "Verify sign up")

    public void testCase06(){

        driver = BrowserFactory.getDriver("chrome");

        driver.manage().window().maximize();

        driver.get("https://www.tempmailaddress.com/");

        WebElement email = driver.findElement(By.cssSelector("[id='email']"));

        String tempEmail = email.getText();

        System.out.println(tempEmail);

        driver.get("http://practice.cybertekschool.com/");

        driver.findElement(By.linkText("Sign Up For Mailing List")).click();

        driver.findElement(By.cssSelector("[name='full_name']")).sendKeys("Serhat Gezer");

        driver.findElement(By.cssSelector("[name='email']")).sendKeys(tempEmail);

        driver.findElement(By.cssSelector("[type='submit']")).click();

        WebElement signUpMessage = driver.findElement(By.cssSelector("[name='signup_message']"));

        Assert.assertTrue(signUpMessage.isDisplayed(),"sign up message is not displayed");

        driver.navigate().to("https://www.tempmailaddress.com/");



       driver.findElement(By.xpath("//td[contains(text(),'do-not-reply@practice.cybertekschool.com')]")).click();


        WebElement emailSender = driver.findElement(By.id("odesilatel"));

        String ActualSender = emailSender.getText();

        String expectedSender = "do-not-reply@practice.cybertekschool.com";

        Assert.assertEquals(ActualSender,expectedSender,"email is not from no reply cybertek");

        WebElement emailSubject = driver.findElement(By.id("predmet"));

        String actualSubject = emailSubject.getText();

        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertEquals(actualSubject,expectedSubject,"subject is not thanks for ...");

    }

    @AfterMethod

    public void teardown(){

        driver.quit();
    }


}
