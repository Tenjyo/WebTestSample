package com.example;

import static org.junit.Assert.*;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

    private String chromeDriverPath() {
        String path;
        if (SystemUtils.IS_OS_MAC || SystemUtils.IS_OS_MAC_OSX) {
            path = "chromedriver/mac/chromedriver"; // Mac環境の場合
        } else {
            path = "chromedriver/win/chromedriver.exe"; // Windows環境の場合
        }
        File file = new File(path);
        return file.getAbsolutePath();
    }


    @Before
    public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:/e46p/chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", chromeDriverPath());
        driver = new ChromeDriver();
		baseUrl = "file:///C:/e46p/Selenium";	//
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
