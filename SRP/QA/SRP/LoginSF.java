package SRP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginSF {

	public void salesForceLogin(String url) throws InterruptedException 
	{

		WebDriver driver1 = SRP.GetChromeDriver.getDriver();
		driver1.get(url);
		driver1.manage().window().fullscreen();
		driver1.findElement(By.id("username")).sendKeys("automation@scrabble.com");
		driver1.findElement(By.id("password")).sendKeys("Auto1234");
		driver1.findElement(By.name("Login")).submit();
		Thread.sleep(3001);
		
		
		
//		
//		driver.findElement(By.name("str")).click();
//		driver.findElement(By.name("str")).sendKeys(PRD_W2L.email_temp);
//
//		Thread.sleep(10001);
//
//		driver.findElement(By.id("phSearchButton")).click();
//		Thread.sleep(5001);
//
//		driver.close();
//WebElement msg=driver.findElement(By.xpath(".//*[@id='PRD_W2L.email']"));
//String text=msg.getText();
//if (msg.isEnabled() && text.contains(PRD_W2L.email_temp))
//{ 
//    System.out.println("Your Lead created with email id ");
//}else{
//    System.out.println("Check the Webto Lead Flow");
//}
				
		

	}
}