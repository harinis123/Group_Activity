package com.selinium;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Activity2 {

	WebDriver driver;
	String username = "tamil909";
	String password = "Tamil@123";
	String verifyuser = "Welcome tamil909";
	String name = "Ashok";
	String country = "India";
	String city = "Chennai";
	String card = "4567231064569090";
	String month = "10";
	String year = "22";

	@BeforeTest
	@Parameters("browser")
	public void launch(String browser) throws Exception {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\harinis\\eclipse-workspace-selenium\\seleniumProject\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\harinis\\eclipse-workspace-selenium\\seleniumProject\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else {

			throw new Exception("Browser is not correct");
		}
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		driver.manage().window().maximize();
		driver.get("https://www.demoblaze.com/index.html#");
	}

	@AfterTest
	public void logoutandclosebrowser() throws InterruptedException {
		Thread.sleep(2000);
		WebElement logout = driver.findElement(By.id("logout2"));
		logout.click();
		Thread.sleep(3000);
		driver.quit();
	}

	@BeforeTest()

	public void signup() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement signuplink = driver.findElement(By.id("signin2"));
		signuplink.click();

		WebElement susername = driver.findElement(By.id("sign-username"));
		susername.sendKeys(username);

		WebElement spassword = driver.findElement(By.id("sign-password"));
		spassword.sendKeys(password);

		WebElement signupbtn = driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]"));
		signupbtn.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());

		Alert signupalert = driver.switchTo().alert();
		String signupmsg = signupalert.getText();
		System.out.println(signupmsg);
		signupalert.accept();
		Thread.sleep(2000);

		try {
			WebElement signupclosebtn = driver
					.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[1]"));
			signupclosebtn.click();
			System.out.println("Already Signup");

		} catch (Exception e) {
			System.out.println("Successfully Signup");
		}
	}

	@BeforeTest(dependsOnMethods = "signup")
	public void login() throws InterruptedException {

		Thread.sleep(2000);
		WebElement loginlink = driver.findElement(By.linkText("Log in"));
		loginlink.click();
		Thread.sleep(2000);
		WebElement lusername = driver.findElement(By.id("loginusername"));
		lusername.sendKeys(username);

		WebElement lpassword = driver.findElement(By.id("loginpassword"));
		lpassword.sendKeys(password);

		WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]"));
		loginbtn.click();

	}

	/*
	 * @AfterTest() public void logout() throws InterruptedException {
	 * Thread.sleep(3000);
	 * 
	 * Thread.sleep(3000); }
	 */

	@BeforeClass
	public void verifyusername() throws InterruptedException {
		Thread.sleep(5000);
		WebElement who = driver.findElement(By.id("nameofuser"));
		String husername = who.getText();
		System.out.println(husername);
		Assert.assertEquals(husername, verifyuser);
	}

	@BeforeMethod
	public void home() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a")).click();
		Thread.sleep(2000);
	}

	@Test(priority = 1)
	public void selectlaptop() throws InterruptedException {

		WebElement laptop = driver.findElement(By.linkText("Laptops"));
		laptop.click();
		Thread.sleep(2000);

		WebElement product1 = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[3]/div/div/h4/a"));
		String productname1 = product1.getText();
		System.out.println(productname1);
		product1.click();

	}

	@Test(priority = 2)
	public void selectphone() throws InterruptedException {
		WebElement phone = driver.findElement(By.linkText("Phones"));
		phone.click();
		Thread.sleep(2000);

		WebElement product2 = driver.findElement(By.linkText("Sony xperia z5"));
		String productname2 = product2.getText();
		System.out.println(productname2);
		product2.click();
		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void selectmonitor() throws InterruptedException {
		WebElement monitor = driver.findElement(By.linkText("Monitors"));
		monitor.click();
		Thread.sleep(2000);

		WebElement product3 = driver.findElement(By.linkText("Apple monitor 24"));
		String productname3 = product3.getText();
		System.out.println(productname3);
		product3.click();
		Thread.sleep(2000);

	}

	@AfterMethod
	public void addandcheckcart() throws InterruptedException {
		Thread.sleep(2000);
		WebElement addtocart = driver.findElement(By.linkText("Add to cart"));
		addtocart.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());

		Alert addtocartalert = driver.switchTo().alert();
		addtocartalert.accept();

		WebElement cart = driver.findElement(By.linkText("Cart"));
		cart.click();
		Thread.sleep(2000);

	}

	@AfterClass
	public void placeonorder() throws InterruptedException, IOException {
		Thread.sleep(3000);

		WebElement ordering = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button"));
		ordering.click();

		WebElement ordername = driver.findElement(By.id("name"));
		ordername.sendKeys(name);

		WebElement ordercountry = driver.findElement(By.id("country"));
		ordercountry.sendKeys(country);

		WebElement ordercity = driver.findElement(By.id("city"));
		ordercity.sendKeys(city);

		WebElement ordercard = driver.findElement(By.id("card"));
		ordercard.sendKeys(card);

		WebElement ordercardmonth = driver.findElement(By.id("month"));
		ordercardmonth.sendKeys(month);

		WebElement ordercardyear = driver.findElement(By.id("year"));
		ordercardyear.sendKeys(year);

		WebElement purchase = driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]"));
		purchase.click();

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("C:\\Users\\harinis\\Pictures\\Screenshots\\Order5.png");
		FileHandler.copy(sourceFile, destinationFile);

		WebElement okbtn = driver.findElement(By
				.cssSelector("body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button"));
		okbtn.click();

	}

}
