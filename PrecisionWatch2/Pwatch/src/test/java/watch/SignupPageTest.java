package watch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SignupPageTest {
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	static ExtentReports extent;

	@BeforeClass
	public static ExtentReports startTest() {

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Automation results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Kumar");
		return extent;
	}

	@BeforeTest
	public void start() throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();

		options.addExtensions(new File(".//Metamask//Extension1.crx"));

		driver = new ChromeDriver(options);

		driver.manage().deleteAllCookies();

		driver.get("https://main.d1wxtput80cmif.amplifyapp.com");

		System.out.println("Page title of new tab: " + driver.getTitle());

		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(newTb.get(0));

		driver.navigate().refresh();

		driver.navigate().refresh();

		WebDriverWait wait = new WebDriverWait(driver, 60);

		driver.switchTo().window(newTb.get(1));

		Dimension d = new Dimension(1800, 1080);

		driver.manage().window().setSize(d);
		
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// creates file at System.getProperty("user.dir")

		FileUtils.copyFile(srcFile, new File(".//Screenshots//helo1.png"));
	}

	@Test(priority = 1)
	public void EnterName() throws IOException {
		extent.createTest("Test case 1 = Enter your name");
		WebElement Name=driver.findElement(By.xpath("//*[text()='Login']"));
		Name.click();
		System.out.println(" Login button clicked succesfully");
	}

//	@Test(priority = 2)
//	public void EnterAddress() throws InterruptedException 
//	{
//		extent.createTest("Test Case 2 = Enter your address");     
//		WebElement Addr=driver.findElement(By.xpath("//*[@placeholder='Your Address']"));
//		Addr.sendKeys("NRT,AP");
//		System.out.println("Address entered successfully");
//	}
//
//	@Test(priority = 3)
//	public void EnterMobile() throws InterruptedException 
//	{
//		extent.createTest("Test Case 3 = Enter your mobile number");     
//		WebElement mbl=driver.findElement(By.id("mobile"));
//		mbl.sendKeys("1234567890");
//		System.out.println("Mobile number entered successfully");
//	}
//
//	@Test(priority = 4)
//	public void EnterEmail() throws IOException 
//	{
//		extent.createTest("Test case 4 = Enter your Email id");
//		WebElement Email=driver.findElement(By.id("email"));
//		Email.sendKeys("kumar@gmail.com");
//	}
//
//	@Test(priority = 5)
//	public void EnterWalletAddress() throws IOException 
//	{
//		extent.createTest("Test case 5 = Enter your Wallet Address");
//		WebElement wallet=driver.findElement(By.id("walletAddress"));
//		wallet.sendKeys("0xA1b7B3C428b76BAb680D73F1bF7b4AB1Bcf1d06d");
//	}
//
//	@Test(priority = 6)
//	public void EnterPassword() throws IOException 
//	{
//		extent.createTest("Test case 6 = Enter your password");
//		WebElement pswd=driver.findElement(By.id("password"));
//		pswd.sendKeys("Rohit@123");
//	}
//
//	@Test(priority = 7)
//	public void ConfirmPassword() throws IOException 
//	{
//		extent.createTest("Test case 7 = Confirm your password");
//		WebElement Cpswd=driver.findElement(By.id("c_password"));
//		Cpswd.sendKeys("Rohit@123");
//	}
	/*
	 * @Test(priority = 8) public void CreateAccount() throws IOException {
	 * extent.createTest("Test case 8 = Click on CREATE ACCOUNT button"); WebElement
	 * submit=driver.findElement(By.xpath(
	 * "//*[@id=\"root\"]/div/div/div[2]/div/div[2]/form/button")); submit.click();
	 * }
	 * 
	 * @AfterTest public void close() { driver.quit(); }
	 */
	@AfterClass
	public static void endTest() {

		extent.flush();
	}

}
