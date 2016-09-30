package com.example.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyersTriangleTest {


	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		  System.setProperty("webdriver.chrome.driver", "C:/e46p/chromedriver.exe");
	        driver = new ChromeDriver();
			baseUrl = "file:///C:/Users/O890379/git/WebTestSample/workspace/WebTestSample/WebContent";	//
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();	//本当は必要、ブラウザ表示を残すためにコメント化
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	@Test
	public void Test1() throws Exception {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MysersTriangle.html");
		driver.findElement(By.name("side_a")).clear();
		driver.findElement(By.name("side_a")).sendKeys("3\n");
		driver.findElement(By.name("output_a")).getText();
		assertThat("OK!", is(driver.findElement(By.id("output_a")).getText()));


		// ERROR: Caught exception [ERROR: Unsupported command [getAlert]]

		//wait.until(ExpectedConditions.titleContains("Selenium テスト sample"));

	}

}
