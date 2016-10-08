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
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
//sahagin Seleniumテストのドキュメンテーション
//import org.sahagin.runlib.external.PageDoc;
//import org.sahagin.runlib.external.TestDoc;
//import org.sahagin.runlib.external.adapter.webdriver.WebDriverAdapter;


//@PageDoc("マイヤーズの三角形/ページ")
public class MyersTriangleTest {

	WebDriver driver;
	static private String baseUrl;
    static ResourceBundle bundle = null;

	Wait<WebDriver> wait;

    private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	    try {
	        bundle = ResourceBundle.getBundle("webdriver");
			System.setProperty("webdriver.chrome.driver", bundle.getString("chromedriverpath"));
			System.setProperty("webdriver.ie.driver",bundle.getString("iedriverpath"));
			//System.setProperty( InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,bundle.getString("iedriverpath"));

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

		if("IE".equals(bundle.getString("driverselect"))){
			driver = new InternetExplorerDriver();
		} else
		if("Chrome".equals(bundle.getString("driverselect"))){
			driver = new ChromeDriver();
		} else {
			driver = new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

//		WebDriverAdapter.setAdapter(driver);	//for sahagin

		Wait<WebDriver> wait = new WebDriverWait(driver, 10);
	}


	@After
	public void tearDown() throws Exception {
		driver.quit();	//本当は必要、ブラウザ表示を残すためにコメント化
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


//	@TestDoc("スモークテスト：「{name}」をセットする")
	@Test
	public void smokeTest1() throws Exception {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MyersTriangle.html");

		String currentUrl = driver.getCurrentUrl();

//		assertThat(currentUrl, startsWith("http://"));


		wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

		driver.findElement(By.name("side_a")).clear();
		driver.findElement(By.name("side_a")).sendKeys("3\n");
		driver.findElement(By.id("output_a")).getText();
		assertThat( driver.findElement(By.id("output_a")).getText(), is("OK!"));

		//wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

	}


	@Test
	public void inital_Display1() {

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);

		driver.get(baseUrl + "/MyersTriangle.html");
		wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

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

		wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

//
		System.out.println("font color =" + driver.findElement(By.id("output_a")).getCssValue("font-color"));
		System.out.println("font color =" + driver.findElement(By.id("output_a")).getCssValue("color"));
		System.out.println("font color =" + driver.findElement(By.id("output_a")).getAttribute("color"));
		System.out.println("font color =" + driver.findElement(By.id("output_a")).getAttribute("font-color"));
		System.out.println("text =" + driver.findElement(By.id("output_a")).getText());
		System.out.println("font color =" + driver.findElement(By.xpath("//h4")).getCssValue("font-color"));
		System.out.println("font color =" + driver.findElement(By.xpath("//h4")).getCssValue("color"));
		System.out.println("value =" + driver.findElement(By.xpath("//h4")).getAttribute("value"));
		System.out.println("text =" + driver.findElement(By.xpath("//h4")).getText());
//

	}


	@Test
	public void Normal1() throws Exception {

		Wait<WebDriver> wait = new WebDriverWait(driver, 20);

		driver.get(baseUrl + "/MyersTriangle.html");
		wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

		//初期表示のチェック
		//※ページオブジェクトパターンで、チェック用のメソッド呼び出し（未実装）


		driver.findElement(By.name("side_a")).clear();

		driver.findElement(By.name("side_a")).sendKeys("33\n");
		assertThat(driver.findElement(By.name("side_a")).getAttribute("value"), is("33"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("長さ０です。"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("チェック中！"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));

		driver.findElement(By.name("side_b")).clear();

		driver.findElement(By.name("side_b")).sendKeys("33\n");
		assertThat(driver.findElement(By.name("side_b")).getAttribute("value"), is("33"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("長さ０です。"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));

		driver.findElement(By.name("side_c")).clear();

		driver.findElement(By.name("side_c")).sendKeys("33\n");
		assertThat(driver.findElement(By.name("side_c")).getAttribute("value"), is("33"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("正三角形です"));

	}

	@Test
	public void Normal2() throws Exception {

		driver.get(baseUrl + "/MyersTriangle.html");
		wait.until(ExpectedConditions.titleContains("マイヤーズの三角形"));

		//初期表示のチェック
		//※ページオブジェクトパターンで、チェック用のメソッド呼び出し（未実装）

		driver.findElement(By.name("side_b")).clear();

		driver.findElement(By.name("side_b")).sendKeys("33\n");
		assertThat(driver.findElement(By.name("side_b")).getAttribute("value"), is("33"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("長さ０です。"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("長さ０です。"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));


		driver.findElement(By.name("side_a")).clear();

		driver.findElement(By.name("side_a")).sendKeys("666\n");
		assertThat(driver.findElement(By.name("side_a")).getAttribute("value"), is("666"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("チェック中！"));
		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("未確定です。"));

		driver.findElement(By.name("side_c")).clear();

		driver.findElement(By.name("side_c")).sendKeys("33\n");
		assertThat(driver.findElement(By.name("side_c")).getAttribute("value"), is("33"));
		assertThat(driver.findElement(By.id("output_a")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_b")).getText(), is("OK!"));
		assertThat(driver.findElement(By.id("output_c")).getText(), is("OK!"));

		assertThat(driver.findElement(By.id("field_Decision")).getText(),is("二等辺三角形です"));

	}


	@Test
	public void Check_Conditions1() throws Exception {

		Wait<WebDriver> wait = new WebDriverWait(driver, 20);

		driver.get(baseUrl + "/MyersTriangle.html");

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
