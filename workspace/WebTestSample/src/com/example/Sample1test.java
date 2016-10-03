package com.example;

import static org.junit.Assert.*;

import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
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


public class Sample1test {

	private WebDriver driver;
	static private String baseUrl;
    static ResourceBundle bundle = null;

	private StringBuffer verificationErrors = new StringBuffer();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	    try {
	        bundle = ResourceBundle.getBundle("webdriver");
			System.setProperty("webdriver.chrome.driver", bundle.getString("chromedriverpath"));

			baseUrl = "file:///C:/e46p/Selenium";	//

	    } catch (MissingResourceException e) {
	        e.printStackTrace();
	    }
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}




    @Before
    public void setUp() {
	//	System.setProperty("webdriver.chrome.driver", "C:/e46p/chromedriver.exe");
    //    //System.setProperty("webdriver.chrome.driver", chromeDriverPath());
    	driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


	@Test
	public void testSampleTest1() throws Exception {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/sample1.html");
		driver.findElement(By.id("COPYTEXTFROM")).clear();
		driver.findElement(By.id("COPYTEXTFROM")).sendKeys("abc");
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
		driver.findElement(By.xpath("//input[@value='ポップアップできます']")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [getAlert]]

		//wait.until(ExpectedConditions.titleContains("Selenium テスト sample"));

	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();	//本当は必要、ブラウザ表示を残すためにコメント化
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
