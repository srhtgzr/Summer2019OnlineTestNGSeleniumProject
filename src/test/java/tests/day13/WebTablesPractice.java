package tests.day13;

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

import java.util.List;

public class WebTablesPractice {

    private WebDriver driver;

    private WebDriverWait wait;

    @BeforeMethod

    public void setUp(){

        driver= BrowserFactory.getDriver("chrome");


        driver.get("http://practice.cybertekschool.com/tables");

        wait = new WebDriverWait(driver, 15);

        //I rcommend to use this wait, for any element, not only web table
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table1")));


    }


    @Test(description = "Print table1 data")

    public void test1(){

        //<table> stands for web table in HTML
        //table1 is id of first table
        //once we find this table as web element, we can print all text from there
        //If you are getting NoSuchElementException


        WebElement table = driver.findElement(By.id("table1"));

        System.out.println(table.getText());

    }

    @Test (description = "Verify that number of columns is equals to 6 in first table")

    public void tet02(){

        //  used find elements since we are looking for more than one element
        //used size method to get the element counts
        int ActualColumnNumber = driver.findElements(By.xpath("//table[@id='table1']//th")).size();

        int expectedColumnNumber =  6;

        Assert.assertEquals(ActualColumnNumber,expectedColumnNumber);


    }


    @Test(description = "Verify that number of rows equals to 5")

    public void test03(){

        int expectedRowCount = 5;


        int actualRowCount = driver.findElements(By.xpath("//table[@id='table1']//tr")).size();

        Assert.assertEquals(expectedRowCount,actualRowCount);


    }

    @Test(description = "Print all values from the 2nd row(excluding table header)")

    public void test04(){
        List<WebElement> row = driver.findElements(By.xpath("//table[@id='table1']//tbody//tr[2]//td"));

        for(WebElement cell: row){

            System.out.println(cell.getText());
        }

    }

    @Test(description = "Print all values from the n-th row (excluding table header)")
    public void test5() {
        //if index = 1, then it's a first row
        //if index = 2, then it's a second row
        //if we don't specify td index, it will take all td elements
        //in css we use space " ", in xpath // to get to any child
        //or in css we use ">", in xpath /, to get to direct child
        //css selector alternative: table[id='table1'] tbody tr:nth-of-type(2) td
        //if index will exceed table size, you will not get any errors, list will be just empty
        //findElements() doesn't give NoSuchElementException, in any case.
        int index = 1;
        List<WebElement> row = driver.findElements(By.xpath("//table[@id='table1']//tbody//tr[" + index + "]//td"));
        for (WebElement cell : row) {
            System.out.println(cell.getText());
        }
    }

    @Test(description = "Verify that email in the third row is equals to jdo@hotmail.com")

        public void test06(){

        int row = 3; // represents row number

        int column = 3; // reprresen column number

        WebElement cell = driver.findElement(By.xpath("//table[@id='table1']//tbody//tr["+row+"]//td["+column+"]"));
        String expectedEmail = "jdoe@hotmail.com";
        String actualEmail = cell.getText();
        Assert.assertEquals(actualEmail, expectedEmail);

        }

        @Test( description = "Get all values from email column and verify that every value contains @")

        public void test07(){

        List<WebElement> emails = driver.findElements(By.xpath("//table[@id='table1']//tbody//tr//th[3]"));

        for(WebElement email: emails){

           Assert.assertTrue(email.getText().contains("@"));
        }

        }

        @Test (description = "Verify that after clicking on last name, values will be sorted in alphabetic order")

        public void test08(){


        driver.findElement(By.xpath("//table[@id='table1']//thead//tr//th[1]")).click();

            List<WebElement> names =  driver.findElements(By.xpath("//table[@id='table1']//tbody//td[1]"));

            for(int index=0; index<names.size()-1; index++){

                String lastName = names.get(index).getText();

                String followingLastName = names.get(index+1).getText();

                Assert.assertTrue(lastName.compareTo(followingLastName) < 0);

            }
        }

    @AfterMethod

    public void teardown(){

        driver.quit();
    }


}
