package Opportunity;

import java.io.File;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import WAS.WAS;
import srpNewInq.InquiryForm;

public class Event {
	
	static WebDriver driver;
  @Test
  public void newEvent() throws InterruptedException, IOException, URISyntaxException {
	  
	  InquiryForm.Srpinquiryform();	
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = InquiryForm.getWebDriver();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//INPUT[@value='New Event']/self::INPUT")).click();       /*Click on New Task*/
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//INPUT[@id='evt5']/self::INPUT")).sendKeys("Test Event");           /*Subject*/
		
		driver.findElement(By.xpath("//SELECT[@id='evt10']/self::SELECT"));
		Select type = new Select (driver.findElement(By.xpath("//SELECT[@id='evt10']/self::SELECT")));		/*select type*/
		type.selectByIndex(3);
			
		driver.findElement(By.xpath("//SELECT[@id='00N60000001yb8a']/self::SELECT"));
		Select SRMresult = new Select (driver.findElement(By.xpath("//SELECT[@id='00N60000001yb8a']/self::SELECT")));		/*Choose SRM result*/
		SRMresult.selectByIndex(1);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//INPUT[@value=' Save ']/self::INPUT")).click();			/*Click on save8*/
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//A[text()='Test Event']/self::A")).click();		/*Click the newly created event*/
		Thread.sleep(3000);
		
		
		
		WAS.takeScreenShot("newEvent", driver);							/*screenshot of new event*/

		
		System.out.println("Task created succesfully!");
	  	} 
  
	  
//	  
//	  public static void takeScreenShot(String methodName) throws IOException {
//		  File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		  FileUtils.copyFile(file,new File("C:\\Users\\abbasi.jaffar\\Desktop\\Selenium_Screenshots\\Image-"+methodName+".png"));
//	
//		
//  }
}
