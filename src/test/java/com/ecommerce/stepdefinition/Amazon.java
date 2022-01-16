package com.ecommerce.stepdefinition;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {
	static WebDriver d;
	static long start;
	
     @BeforeClass
     public static void launch() {
    	 
    	 System.out.println("Browser launch");
    	 
    	WebDriverManager.chromedriver().setup();
    	d = new ChromeDriver();
    	d.get("https://www.amazon.com/");
    	d.manage().window().maximize();
    	d.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    	 
     }
     
     @AfterClass
     public static void close() {
    	d.quit();
     }
     
     @BeforeMethod
     public void startTime() {
    	 long start = System.currentTimeMillis();
     }
     
     @AfterMethod
     public void endTime() {
    	long end = System.currentTimeMillis();
    	long t = end - start; 
    	System.out.println("Time taken :" +t);
     }
     
     @Test
     public void method1() {
    
        	 System.out.println("search mobile");
        	WebElement search = d.findElement(By.id("twotabsearchtextbox"));
        	search.sendKeys("mobile",Keys.ENTER);
     }
}
