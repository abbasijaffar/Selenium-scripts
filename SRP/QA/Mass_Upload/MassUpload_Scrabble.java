package Mass_Upload;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.AWTException;
import java.awt.Robot;

//import jxl.read.biff.BiffException;

//import jxl.read.biff.BiffException;

public class MassUpload_Scrabble {


		public static void main(String[] args) throws InterruptedException, IOException
		
	{
		
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");	
			WebDriver driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.get("https://cs23.salesforce.com");	    
			driver.findElement(By.id("username")).sendKeys("automation@scrabble.com");
			driver.findElement(By.id("password")).sendKeys("Auto1234");
			driver.findElement(By.name("Login")).submit();
			
			System.out.println("automation@scrabble.com login Completed");
			Thread.sleep(9000);
			
			WebElement element = driver.findElement(By.id("phSearchInput"));
			element.sendKeys("Aaron Yurkiewicz");
			element.submit();
			Thread.sleep(2000);			  
			
			driver.findElement(By.linkText("Aaron Yurkiewicz")).click();			 
			Thread.sleep(2000);
								  
								  driver.findElement(By.id("chatterTab")).click();
								  Thread.sleep(2000);
						
								  driver.findElement(By.id("moderatorMutton")).click();
								  Thread.sleep(2000);
								  driver.findElement(By.id("USER_DETAIL")).click();
								  
								  
								  System.out.println("***Aaron login Completed");
								  Thread.sleep(2000);
						     
		
		driver.findElement(By.name("login")).click(); 
		Thread.sleep(2000);
		
		driver.findElement(By.id("01r60000000UJl4_Tab")).click();
		Thread.sleep(2000);
	
		driver.findElement(By.name("go")).click(); 
		Thread.sleep(2000);
		
		driver.findElement(By.id("00B60000005kiXl_listButtons")).click(); 
		Thread.sleep(2000);
	
		driver.findElement(By.id("ep")).click();
		Thread.sleep(2000);
		
		/* Giving Mass Upload Name */
		
		driver.findElement(By.id("Name")).sendKeys("Mass_Upload_Automation_05");
		Thread.sleep(2000);

		driver.findElement(By.name("save")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.name("attachFile")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("file")).click();
		Thread.sleep(1000);
	 	
		Runtime.getRuntime().exec("osascript "+"/Users/ramesh.raja/Documents/00_Work/Selenium/AppleScript/MassUpload1.scpt");
	 	
	 	Thread.sleep(40000);
	 	System.out.println("Attachment success");  
	    Thread.sleep(10000);
	    
	    driver.findElement(By.id("Attach")).click();
		System.out.println("Attach");         
		Thread.sleep(10000);	
		
		driver.findElement(By.name("cancel")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.name("srp_upload_attachment")).click();
		Thread.sleep(4000);
		
		 Alert alert = driver.switchTo().alert();
		 alert.accept();
		 Thread.sleep(4000);
		driver.navigate().refresh();
		 Thread.sleep(4000);
		 driver.close();
	
	}
}
