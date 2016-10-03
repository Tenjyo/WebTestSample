package com.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChromedriverSample2 {

	static ResourceBundle bundle = null;

	@Before
	public void setUp() throws Exception {

		try {
			bundle = ResourceBundle.getBundle("webdriver");
			System.setProperty("webdriver.chrome.driver", bundle.getString("chromedriverpath"));
		} catch (MissingResourceException e) {
			e.printStackTrace();
		}

		//String val = bundle.getString("chromedriverpath");
		//System.out.println(val);
		//String val = "C:/e46p/chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", bundle.getString("chromedriverpath"));

		//System.setProperty("webdriver.chrome.driver", "C:/e46p/chromedriver.exe");
		//System.out.println(System.getProperty("webdriver.chrome.driver"));
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void doAGoogleSearch () {

		WebDriver driver =new ChromeDriver();
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get("http://www.google.co.jp");

		String currentUrl = driver.getCurrentUrl();

		assertThat(currentUrl, startsWith("https://"));

		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("cheese\n");

		WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
				By.partialLinkText("Wikipedia")));
		link.click();

		wait.until(ExpectedConditions.titleContains("Cheese"));

		// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the  screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File("C:/temp/selenium.png"));
		}catch (IOException e)  {
			System.out.println(e.getMessage());
		}

	}



}
