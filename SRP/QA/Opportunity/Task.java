package Opportunity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import WAS.WAS;

import srpNewInq.InquiryForm;


public class Task {
	
static WebDriver driver;

  @Test
  public void newTask() throws InterruptedException, IOException, URISyntaxException {
	  
	  InquiryForm.Srpinquiryform();	
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriver driver = InquiryForm.getWebDriver();
		
		driver.findElement(By.xpath("//INPUT[@value='New Task']/self::INPUT")).click(); /*Click on New Task*/
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//INPUT[@id='tsk5']/self::INPUT")).sendKeys("Task test");  /*Subject*/
		
		WebElement datepicker = driver.findElement(By.id("tsk4"));
		datepicker.click();
		
		
		WebElement dateEle = driver.findElement(By.xpath("//TD[@onblur='hiOff(this);'][text()='28']/self::TD"));  /*Select date*/
		dateEle.click();
		
		driver.findElement(By.xpath("//SELECT[@id='tsk12']/self::SELECT")).click();						/*Select status*/
		Select status = new Select (driver.findElement(By.xpath("//SELECT[@id='tsk12']/self::SELECT")));
		status.selectByIndex(2);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//SELECT[@id='00N60000001yb8a']/self::SELECT"));							/*Select SRM result*/
		Select srmResult = new Select (driver.findElement(By.xpath("//SELECT[@id='00N60000001yb8a']/self::SELECT")));
		srmResult.selectByIndex(2);
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//INPUT[@value=' Save ']/self::INPUT")).click();		/*Click Save*/
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//A[text()='Task test']/self::A")).click();		/*Open Task*/
		Thread.sleep(1000);
		
		WAS.takeScreenShot("newTask", driver);							/*Task screenshot*/

		System.out.println("Task created succesfully!");
	  	}
	  
	  
//	  public static void takeScreenShot(String methodName) throws IOException {
//		  File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		  FileUtils.copyFile(file,new File("C:\\Users\\abbasi.jaffar\\Desktop\\Selenium_Screenshots\\Image-"+methodName+".png"));
//		
//	  
//  }
}
