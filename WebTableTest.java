package com.testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class WebTableTest {
    private WebDriver driver;
    private String baseUrl = "http://www.way2automation.com/angularjsprotractor/webtables/";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAddUser() {
        // Step A: Navigate to the URL
        driver.get(baseUrl);

        // Step B: Validate that you are on the User List Table
        String pageTitle = driver.getTitle();
        Assert.assertTrue(pageTitle.contains("Protractor practice website - WebTables"), "Not on the User List Table page");

        // Step C: Click Add User
        driver.findElement(By.cssSelector("button[ng-click='pop()']")).click();

        // Step D: Add users with the details below.
        // Generate unique username
        String uniqueUsername = "User" + UUID.randomUUID().toString().substring(0, 8);

        // Enter user details
        driver.findElement(By.name("FirstName")).sendKeys("FName1");
        driver.findElement(By.name("LastName")).sendKeys("LName1");
        driver.findElement(By.name("UserName")).sendKeys(uniqueUsername);
        driver.findElement(By.name("Password")).sendKeys("Pass1");
		driver.findElement(By.name("Customer")).sendKeys("Company AAA");
        driver.findElement(By.name("Role")).sendKeys("Admin");
        driver.findElement(By.name("Email")).sendKeys("admin@mail.com");
        driver.findElement(By.name("Cell")).sendKeys("082555");

        // Click Save
        driver.findElement(By.cssSelector("button[ng-click='save(user)']")).click();
		
		// Second user details
		driver.findElement(By.name("FirstName")).sendKeys("FName2");
        driver.findElement(By.name("LastName")).sendKeys("LName3");
        driver.findElement(By.name("UserName")).sendKeys(uniqueUsername);
        driver.findElement(By.name("Password")).sendKeys("Pass2");
		driver.findElement(By.name("Customer")).sendKeys("Company BBB");
        driver.findElement(By.name("Role")).sendKeys("Customer");
        driver.findElement(By.name("Email")).sendKeys("customer@mail.com");
        driver.findElement(By.name("Cell")).sendKeys("083444");
		
		// Click Save
		driver.findElement(By.cssSelector("button[ng-click='save(user)']")).click();

        // Step F: Ensure that your users are added to the list
        List<WebElement> users = driver.findElements(By.xpath("//table[@table-title='Smart Table example']//tbody//tr"));
        boolean userAdded = false;

        for (WebElement user : users) {
            WebElement usernameField = user.findElement(By.xpath(".//td[3]"));
            if (usernameField.getText().equals(uniqueUsername)) {
                userAdded = true;
                break;
            }
        }

        Assert.assertTrue(userAdded, "User not added successfully: " + uniqueUsername);
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
