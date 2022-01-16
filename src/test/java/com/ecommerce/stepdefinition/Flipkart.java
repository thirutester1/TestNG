package com.ecommerce.stepdefinition;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	static WebDriver d;
	static long start;
	static String name;
   @BeforeClass
	   public static void launch(){
        WebDriverManager.chromedriver().setup();
        d = new ChromeDriver();
        d.manage().window().maximize();
        d.get("https://www.flipkart.com/");
;        d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            
	   }
   @AfterClass
       public static void close() {
	   d.quit();
   }
   @BeforeMethod
   public void start() {
	   long start = System.currentTimeMillis();
   }
   @AfterMethod
   public void end() {
	   long end = System.currentTimeMillis();
   	long t = end - start; 
   	System.out.println("Time taken :" +t);
   }
   @Test(priority=1)
public void login() {
	   System.out.println("login test");
   	try {
   		WebElement button = d.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
   		 button.isDisplayed();
   	     button.click();
   	}catch (Exception e) {
   		System.out.println("Popup not displayed");
   	}
   }
   @Test(priority=2)
public void search() {
  	 System.out.println("search mobile");
  	WebElement search = d.findElement(By.xpath("//input[@type='text']"));
  	search.sendKeys("realme",Keys.ENTER);
  	
   }
   @Test(priority=3)
public void name() { 
  	WebElement mobileName = d.findElement(By.xpath("//div[contains(text(),'realme Narzo 50A (Oxygen Blue, 64 GB)')]"));
	name = mobileName.getText();
	System.out.println(name);
  	mobileName.click();

   }   
   @Test(priority=4)
public void window() {
  	System.out.println("Window handling");
  	String par = d.getWindowHandle();
  	Set<String> child = d.getWindowHandles();
		for(String x : child) {
		if(!par.equals(x)) {
				System.out.println("tab switched");
				d.switchTo().window(x);
			}
		}
  }
		@Test(priority=5)
		public void validation() {
		WebElement nam = d.findElement(By.xpath("//span[@class='B_NuCI']"));
		String name2 = nam.getText();
			System.out.println(name2);
			SoftAssert s = new SoftAssert();
			s.assertEquals(name, name2);
			System.out.println("passed");
		
   }
 
		@Test(priority=6, invocationCount = 2)
		public void screenshot() throws IOException{
			SimpleDateFormat sec = new SimpleDateFormat("ss");
			String date = sec.format(new Date(0));
			TakesScreenshot ts = (TakesScreenshot)d;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File des = new File(".//target//Image"+date+".jpg");
			FileUtils.copyFile(src, des);
		}
	   }

