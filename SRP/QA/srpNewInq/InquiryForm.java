package srpNewInq;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import SRP.EmailReport;

public class InquiryForm {
	 public static String email_inq;
		public static WebDriver driver;
		static String URL;
		static String fn;
		static String ln;
  @Test
  public static void Srpinquiryform () throws InterruptedException, IOException, URISyntaxException {
			//Read properties file
	  		Properties prop = new Properties();
	  		InputStream input = new FileInputStream("./Drivers/config.properties");
	  		prop.load(input);
	  		
	  		//Read SF environment
	  		System.out.println("Enter the environment: ");
	  		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	  		String env = reader.readLine();
	  		
			//Read SF URL, username, password
	  		String loginURL="";
	  		String uName="";
	  		String pswd="";
	  		
	  		if(env.equalsIgnoreCase("Scrabble")) {
	  			loginURL = prop.getProperty("Scrabble");
	  			uName = prop.getProperty("Scrabble_username");
	  			pswd = prop.getProperty("Scrabble_password");
	  		} else if(env.equalsIgnoreCase("Production")) {
	  			loginURL = prop.getProperty("Production");
	  			uName = prop.getProperty("Production_username");
	  			pswd = prop.getProperty("Production_password");
	  		}
	  		
	  
//			System.out.println("Start time is:"+ System.currentTimeMillis());
			fn = "Qa_"+RandomStringUtils.randomNumeric(4);
		    ln = "Test"+RandomStringUtils.randomNumeric(3);
		    String email = "abbasi.wileytest@gmail.com";
		    String mobile = RandomStringUtils.randomNumeric(10);	    
		    String HomePhone = RandomStringUtils.randomNumeric(10);
		    String zip = "60173";
		    email_inq = email;
		    
		    URI domain = new URI("http://example.com");
		    java.net.URL u = domain.toURL();
		    
		    System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("loginURL-->"+loginURL);
			driver.get(loginURL);
			driver.findElement(By.id("username")).sendKeys(uName);
			driver.findElement(By.id("password")).sendKeys(pswd);
			driver.findElement(By.name("Login")).submit();
			Thread.sleep(3001);
		    
			driver.findElement(By.id("01r60000000UNpS_Tab")).click();
			Thread.sleep(1000);
			
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id35:j_id36:opp__retrainowner")).click();
			Thread.sleep(500);
			
		    driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id40:opp__firstname")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id40:opp__firstname")).sendKeys(fn);	
			
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id42:opp__lastname")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id42:opp__lastname")).sendKeys(ln);	
			Thread.sleep(1000);
			
			/*To change the partner change the Account id here */
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id44:campusPicklist")).click();
	        Select partner= new Select (driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id44:campusPicklist")));
	        partner.selectByIndex(4);
	        Thread.sleep(500);
			
			/*Program should match with the above account id selected */
//			Select sc2 =new Select(driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id48:programPicklist")));
//			sc2.selectByValue("a1C18000000V7TnEAK");	
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id48:programPicklist")).click();
	        Select selectprog= new Select (driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id48:programPicklist")));
	        selectprog.selectByIndex(4);
	        Thread.sleep(3000);
	        
			/*Specializatoin should match with the above account id selected */
//			Select sc4=new Select(driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id52:specPicklist")));
//			sc4.selectByValue("MBA-INT MKTG");	
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id52:specPicklist")).click();
	        Select specialization= new Select (driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id52:specPicklist")));
	        specialization.selectByIndex(1);
	        Thread.sleep(3000);
	        
			/*Term should match with the above program selected */
//			Select sc3 =new Select(driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id55:timeframe")));
//			sc3.selectByValue("0-3 months");	
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id55:timeframe")).click();
	        Select term= new Select (driver.findElement(By.id("j_id0:theentireform:j_id27:j_id39:j_id55:timeframe")));
	        term.selectByIndex(1);
	        Thread.sleep(500);
	        
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id62:emailAdd")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id62:emailAdd")).sendKeys(email);	
			
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id64:Home_Phone")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id64:Home_Phone")).sendKeys(mobile);	
			
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id66:Mobile_Phone")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id61:j_id66:Mobile_Phone")).sendKeys(mobile);	
			
			/*Country should be one of the 6 combinations */
			Select sc41 =new Select(driver.findElement(By.id("j_id0:theentireform:j_id27:j_id70:j_id77:Country")));
			sc41.selectByValue("USA");	
			
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id70:j_id81:Zip")).click();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id70:j_id81:Zip")).sendKeys(zip);	
			
			Select sc5 =new Select(driver.findElement(By.id("j_id0:theentireform:j_id27:j_id83:j_id86:campaignList")));
			sc5.selectByValue("ACAWinter");	
			Thread.sleep(1500);
			//driver.findElement(By.name("j_id0:theentireform:j_id27:j_id28:subBtn")).submit();
			driver.findElement(By.id("j_id0:theentireform:j_id27:j_id28:subBtn")).click();
			Thread.sleep(30000);
			
			URL = driver.getCurrentUrl();
			//return URL;
			
			System.out.println("Inquiry form Created");
			
//			EmailReport.main(null);

	}
  
  public static String getOppURL() {
		return URL;
	}
	public static WebDriver getWebDriver() {
		return driver;
	}
	public static String getfirstname() {
		return fn;
	}
	public static String getlastname() {
		return ln;	
	}
	
}
