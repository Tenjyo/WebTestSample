package com.example;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JavaScript電卓 {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://change-this-to-the-site-you-are-testing/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testJavaScript() throws Exception {
		driver.get(baseUrl + "file:///C:/Selenium/JavaScript%E9%9B%BB%E5%8D%93.html");
		driver.findElement(By.id("COPYTEXTFROM")).clear();
		driver.findElement(By.id("COPYTEXTFROM")).sendKeys("aabbcc");
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
		driver.findElement(By.id("COPYTEXTFROM")).clear();
		driver.findElement(By.id("COPYTEXTFROM")).sendKeys("eeffdd");
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
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
