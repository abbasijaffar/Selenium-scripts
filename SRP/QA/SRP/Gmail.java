package SRP;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import srpNewInq.InquiryForm;

public class Gmail {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		  System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		  WebDriver driver;
			if(InquiryForm.getWebDriver() != null) {	
				driver = InquiryForm.getWebDriver();
//			}else if(m_BDT_OLA.getWebDriver()) {
				
			}else {
				driver = new ChromeDriver();
			}
			
			
			driver.manage().window().maximize();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin');");
		Thread.sleep(3000);
		for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
		}
		
		driver.findElement(By.name("identifier")).sendKeys("abbasi.wileytest@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.name("password")).sendKeys("Test1234!");
		driver.findElement(By.id("passwordNext")).click();
		Thread.sleep(10000);


	}
}
