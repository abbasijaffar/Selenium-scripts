package SRP;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public  class GetChromeDriver 
{
	 public static WebDriver getDriver() 
	{
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
		return new ChromeDriver();

	}
}
