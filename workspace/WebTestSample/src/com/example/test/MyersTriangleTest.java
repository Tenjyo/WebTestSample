package com.example.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.MissingResourceException;
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

public class MyersTriangleTest {

	private WebDriver driver;
	static private String baseUrl;
    static ResourceBundle bundle = null;

    private StringBuffer verificationErrors = new StringBuffer();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	    try {
	        bundle = ResourceBundle.getBundle("webdriver");

			System.setProperty("webdriver.chrome.driver", bundle.getString("chromedriverpath"));

	        baseUrl = bundle.getString("baseUrl");


	    } catch (MissingResourceException e) {
	        e.printStackTrace();
	    }
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//String value = bundle.getString("chromedriverpath");
		//System.setProperty("webdriver.chrome.driver", "C:/e46p/chromedriver.exe");

		driver = new ChromeDriver();

//		baseUrl = "file:///C:/Users/O890379/git/WebTestSample/workspace/WebTestSample/WebContent";	//
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();	//本当は必要、ブラウザ表示を残すためにコメント化
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	@Test
	public void TestSample1() throws Exception {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MysersTriangle.html");
		driver.findElement(By.name("side_a")).clear();
		driver.findElement(By.name("side_a")).sendKeys("3\n");
		driver.findElement(By.id("output_a")).getText();
		assertThat( driver.findElement(By.id("output_a")).getText(), is("OK!"));

		// ERROR: Caught exception [ERROR: Unsupported command [getAlert]]

		//wait.until(ExpectedConditions.titleContains("Selenium テスト sample"));

	}


	@Test
	public void inital_page1() {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MysersTriangle.html");

		//初期表示のチェック
		assertThat(driver.findElement(By.id("field_Decision")).getText(),  is("？"));
		assertThat(driver.findElement(By.name("side_a")).getAttribute("value"), is("0"));
		assertThat(driver.findElement(By.name("side_b")).getAttribute("value"), is("0"));
		assertThat(driver.findElement(By.name("side_c")).getAttribute("value"), is("0"));
		//assertThat(driver.findElement(By.name("side_a")).getText(), is("0"));
		//assertThat(driver.findElement(By.name("side_b")).getText(), is("0"));
		//assertThat(driver.findElement(By.name("side_b")).getText(), is("0"));

		assertThat(driver.findElement(By.id("output_a")).getText(), is(""));
		assertThat(driver.findElement(By.id("output_b")).getText(), is(""));
		assertThat(driver.findElement(By.id("output_b")).getText(), is(""));
	}


	@Test
	public void Test2() throws Exception {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MysersTriangle.html");

		//初期表示のチェック
		assertThat("？", is(driver.findElement(By.id("field_Decision")).getText()));

		driver.findElement(By.name("side_b")).clear();
		driver.findElement(By.name("side_b")).sendKeys("3\n");
		driver.findElement(By.id("output_b")).getText();

		assertThat(driver.findElement(By.id("output_a")).getText(), is("長さ０です"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("チェック中！"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("チェック中！"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));

		assertThat(driver.findElement(By.name("side_b")).getAttribute("value"), is("3"));


		driver.findElement(By.name("side_a")).clear();
		driver.findElement(By.name("side_a")).sendKeys("3\n");
		driver.findElement(By.id("output_a")).getText();
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("長さ０です。"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));

		assertThat(driver.findElement(By.name("side_a")).getAttribute("value"), is("3"));
		assertThat(driver.findElement(By.name("side_b")).getAttribute("value"), is("3"));


		driver.findElement(By.name("side_c")).clear();
		driver.findElement(By.name("side_c")).sendKeys("3\n");
		driver.findElement(By.id("output_c")).getText();
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("正三角形です"));

	}

}
