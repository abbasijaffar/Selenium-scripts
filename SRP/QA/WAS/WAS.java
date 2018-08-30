package WAS;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import SRP.EmailReport;
import SRP.Gmail;
import srpNewInq.InquiryForm;

public class WAS {
	
	static WebDriver driver;
	String OppTitle;
	String Status;
	Boolean result;
	List<WebElement> event;
	String ApptTime;
	String StudentTime;
	Select dropDown;
	List<WebElement> l;
	List<WebElement> timeslot;
	int RandomTZ;
	int RandomSlot;
	int dropDownSize;
	String Subject;
	String GTitle;
	String msg;
	String WASTitle;
	
	
	
	
	
  @Test(priority = 0)
  public void WASOpp() throws InterruptedException, IOException, URISyntaxException {
	  
	  //Test case-1 START
	    InquiryForm.Srpinquiryform();	
		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver = InquiryForm.getWebDriver();
		String oppURLReturned = InquiryForm.getOppURL();
		System.out.println("Opp URL-->"+oppURLReturned);
		Thread.sleep(5001);
		
		OppTitle = driver.getTitle();
		Thread.sleep(4000);
  }
  
  @Test(priority = 1)
  public void newAppointment() throws InterruptedException {
		//Test case-1 START
		//Click on Offer appointment
		driver.findElement(By.name("deltaksrp__offer_appointment")).click();
		Thread.sleep(3001);
				
		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td/following-sibling::td[1]")).getText();
		System.out.println("Status "+Status);
		result = Status.equals("New");
		if(result==true) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}  
  
		Gmail.main(null);
		
      Thread.sleep(10000);
      
      System.out.println("Title-->"+driver.getTitle());
      List<WebElement> inbox = driver.findElements(By.xpath("//*[@class='bog']"));
		System.out.println(inbox.size());
		Subject = InquiryForm.getfirstname()+" "+InquiryForm.getlastname()+": Your Appointment Request Has Been Received";
		System.out.println(Subject);

		Thread.sleep(5000);
		
		driver.navigate().refresh();
		List<WebElement> inbox2 = driver.findElements(By.xpath("//span[@class='bog']"));
      System.out.println(inbox2.size());
      System.out.println("Current page-->"+driver.getTitle());
      for(int a=0; a<inbox2.size(); a++) {
      	msg = inbox2.get(a).getText();
          if(msg.matches("(?i:.*"+Subject+".*)")) {
               inbox2.get(a).click();
               System.out.println("Found the email");
               break; 
          }
      }
		
		Thread.sleep(20000);
		
		
		GTitle = driver.getTitle();
		
  }	

  @Test(priority = 2)
  public void emailOpened() throws InterruptedException {
		//Click on Request Appointment
		driver.findElement(By.xpath("//A[contains(text(),'Request  Appointment')]")).click();
      Thread.sleep(20000);
      WASTitle = "";
      for(String winHandle : driver.getWindowHandles()){
      	driver.switchTo().window(winHandle);
      	WASTitle = driver.getTitle();
      	}
      
  
  System.out.println("OppTitle-->"+OppTitle);
  //Switch to Salesforce page
      for(String winHandle : driver.getWindowHandles()){
      	driver.switchTo().window(winHandle);
      	if(driver.getTitle().equals(OppTitle)){
      	break;
      	}
      	}

      //Refresh Salesforce page
      driver.navigate().refresh();
      Thread.sleep(10000);
      
      //Page down
      Actions action = new Actions(driver);
      action.sendKeys(Keys.PAGE_DOWN).perform();
      Thread.sleep(2000);
      
		//Salesforce status changes to Email Opened		
		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td[2]")).getText();
		System.out.println("Status "+Status);
		result = Status.equals("Email Opened");
		if(result==true) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}
		System.out.println("WASTitle"+ WASTitle);
		
		//Switch to WAS window opened
		for(String winHandle : driver.getWindowHandles()){
      	driver.switchTo().window(winHandle);
      	if(driver.getTitle().equals(WASTitle)){
      	break;
      	}
      	}
  }	
  
  @Test(priority = 3)
	 public void scheduled() throws InterruptedException {  
      //Read dropdown list values and select random timezone
      dropDown = new Select(driver.findElement(By.xpath("//SELECT[@id='j_id0:webschedform:timezonelist']/self::SELECT")));
      l = dropDown.getOptions();
      dropDownSize = l.size();
      RandomTZ = (int)(Math.random() * dropDownSize);
      if(RandomTZ==0) {
      	RandomTZ=RandomTZ+1;
      }
      dropDown.selectByIndex(RandomTZ);
      Thread.sleep(6000);

      //Choose random time slot of any day displayed
      timeslot = driver.findElements(By.xpath("//li[@class='datebutton']"));
      RandomSlot = (int)(Math.random() * timeslot.size());
      if(RandomSlot!=0) {
    	  RandomSlot=RandomSlot-1;
      }
      timeslot.get(RandomSlot).click();
      Thread.sleep(9000);
      
      // After selecting timeslot click on 'Confirm Appointment' button at the bottom of page.
    driver.findElement(By.xpath("//A[@href='#'][contains(text(),'CONFIRM APPOINTMENT ')]/self::A")).click();
    
  //Switch to Salesforce window opened
  		for(String winHandle : driver.getWindowHandles()){
          	driver.switchTo().window(winHandle);
          	if(driver.getTitle().equals(OppTitle)){
          	break;
          	}
          	}
  		
  	//Refresh Salesforce page
      driver.navigate().refresh();
      Thread.sleep(10000);		
    

      
  //Salesforce status changes to Scheduled		
  		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td/following-sibling::td[1]")).getText();
  		System.out.println("Status "+Status);
  		result = Status.equals("Scheduled");
  		if(result==true) {
  			System.out.println("Pass");
  		} else {
  			System.out.println("Fail");
  		}
  		
  	//Salesforce Current Web Scheduled Appointment field updated time
  		ApptTime = driver.findElement(By.xpath("//DIV[@id='00N60000002aLWN_ileinner']/self::DIV")).getText();
  		System.out.println("Current Web Scheduled Appointmwnt ="+ApptTime);

  		
//  		//Salesforce Student Chosen Unmodified Time
  		StudentTime = driver.findElement(By.xpath("//td/span[contains(text(),'Student Chosen Unmodified Time')]/../../td[2]")).getText();
  		System.out.println("Student Chosen Unmodified Time ="+StudentTime);
  }
  
  @Test(priority = 4)
  		public void eventCreated() {
  		event = driver.findElements(By.xpath("//*[text()='Web Scheduled Appointment']"));
  		if(event.size()>0) {
  	    System.out.println("Event created successfully!");
  		}else {
  		System.out.println("Event is not created");
  		}    		
     
  		
          	//Switch to WAS window opened
      		for(String winHandle : driver.getWindowHandles()){
              	driver.switchTo().window(winHandle);
              	if(driver.getTitle().equals(WASTitle)){
              	break;
              	}
              	}
  		
  		}
  		
  @Test(priority = 5)
  	  public void canceled() throws InterruptedException {			
  		 
    // Click on 'Revise Appointment'
    driver.findElement(By.xpath("//A[@id='j_id0:webschedform:cancellink']/self::A")).click();
    
    Thread.sleep(20000);
    
  //Switch to Opps window opened
  		for(String winHandle : driver.getWindowHandles()){
          	driver.switchTo().window(winHandle);
          	if(driver.getTitle().equals(OppTitle)){
          	break;
          	}
          	}
  
  //Refresh Salesforce page
  driver.navigate().refresh();
  Thread.sleep(10000);
  
  
           
  //Salesforce status changes to Canceled		
  		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td/following-sibling::td[1]")).getText();
  		System.out.println("Status "+Status);
  		result = Status.contains("Canceled");
  		if(result==true) {
  			System.out.println("Pass");	
  			
  		} else {
  			System.out.println("Fail");
  		}
  	       

  	//Salesforce Current Web Scheduled Appointment field updated time
  	ApptTime = driver.findElement(By.xpath("//DIV[@id='00N60000002aLWN_ileinner']/self::DIV")).getText();
  	System.out.println("Current Web Scheduled Appointmwnt ="+ApptTime);
  			
 			
//		//Salesforce Student Chosen Unmodified Time
		StudentTime = driver.findElement(By.xpath("//td/span[contains(text(),'Student Chosen Unmodified Time')]/../../td[2]")).getText();
		System.out.println("Student Chosen Unmodified Time ="+StudentTime);
  	}
  	
  @Test(priority = 6)
  	public void eventDeleted() throws InterruptedException {
		
  	//Verify event is deleted
		event = driver.findElements(By.xpath("//*[text()='Web Scheduled Appointment']"));
		if(event.size()>0) {
	    System.out.println("Event did not get deleted");
		}else {
		System.out.println("Event is deleted");
		} 
  	
  	Thread.sleep(10000);
  		
		//Switch to WAS window opened
				for(String winHandle : driver.getWindowHandles()){
		        	driver.switchTo().window(winHandle);
		        	if(driver.getTitle().equals(WASTitle)){
		        	break;
		        	}
		        	}	
				
  	}
  	
  @Test(priority = 7)
  	public void rescheduled() throws InterruptedException {
    //WAS ReSchedule New appointment
    driver.findElement(By.xpath("//A[@href='#'][text()='Schedule New Appointment']/self::A")).click();
    
  //Read dropdown list values and select random timezone
    dropDown = new Select(driver.findElement(By.xpath("//SELECT[@id='j_id0:webschedform:timezonelist']/self::SELECT")));
    l = dropDown.getOptions();
    dropDownSize = l.size();
    RandomTZ = (int)(Math.random() * dropDownSize);
    if(RandomTZ==0) {
    	RandomTZ=RandomTZ+1;
    }
    dropDown.selectByIndex(RandomTZ);
    Thread.sleep(6000);

    //Choose random time slot of any day displayed
    //COMMENTED
    timeslot = driver.findElements(By.xpath("//li[@class='datebutton']"));
  
    RandomSlot = (int)(Math.random() * timeslot.size());
    if(RandomSlot!=0) {
  	  RandomSlot=RandomSlot-1;
    }
//    System.out.println(RandomSlot);
    timeslot.get(RandomSlot).click();
    Thread.sleep(9000);
   
    
    // After selecting timeslot click on 'Confirm Appointment' button at the bottom of page.
  driver.findElement(By.xpath("//A[@href='#'][contains(text(),'CONFIRM APPOINTMENT ')]/self::A")).click();
  
  Thread.sleep(20000);
  
//Switch to Opp window opened
		for(String winHandle : driver.getWindowHandles()){
        	driver.switchTo().window(winHandle);
        	if(driver.getTitle().equals(OppTitle)){
        	break;
        	}
        	}
 
	//Refresh Salesforce page
  driver.navigate().refresh();
  Thread.sleep(10000);
  

       
  //Salesforce status changes to Rescheduled	
  		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td/following-sibling::td[1]")).getText();
  		System.out.println("Status "+Status);
  		result = Status.equals("Rescheduled");
  		if(result==true) {
  			System.out.println("Pass");
  		} else {
  			System.out.println("Fail");
  		}
  		
  //Salesforce Current Web Scheduled Appointment field updated time
  ApptTime = driver.findElement(By.xpath("//DIV[@id='00N60000002aLWN_ileinner']/self::DIV")).getText();
  System.out.println("Current Web Scheduled Appointmwnt ="+ApptTime);
      			
      			
//	//Salesforce Student Chosen Unmodified Time
	StudentTime = driver.findElement(By.xpath("//td/span[contains(text(),'Student Chosen Unmodified Time')]/../../td[2]")).getText();
	System.out.println("Student Chosen Unmodified Time ="+StudentTime);
  	}
  	
  @Test(priority = 8)
  	public void rescheduledAppointment() throws InterruptedException {
  event = driver.findElements(By.xpath("//*[text()='Web Scheduled Appointment']"));
	if(event.size()>0) {
  System.out.println("Event created successfully!");
	}else {
	System.out.println("Event is not created");
	} 
  
  Thread.sleep(10000);
 
  	
  	
  	
	//Switch to Opp window opened
			for(String winHandle : driver.getWindowHandles()){
	        	driver.switchTo().window(winHandle);
	        	if(driver.getTitle().equals(OppTitle)){
	        	break;
	        	}
	        	}
  
	//Refresh Salesforce page
	driver.navigate().refresh();
	Thread.sleep(10000);
  	}
  	
  @Test(priority = 9)
  	public void offerAppointment() throws InterruptedException {

  //Click on Offer appointment button
		driver.findElement(By.name("deltaksrp__offer_appointment")).click();
		Thread.sleep(3001);
		
		Status = driver.findElement(By.xpath("//tr[td='WebScheduler Status']/td/following-sibling::td[1]")).getText();
		System.out.println("Status "+Status);
		result = Status.equals("New");
		if(result==true) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}  
		
	//Salesforce Current Web Scheduled Appointment field updated time
	    ApptTime = driver.findElement(By.xpath("//DIV[@id='00N60000002aLWN_ileinner']/self::DIV")).getText();
	    System.out.println("Current Web Scheduled Appointmwnt ="+ApptTime);
	        			
	        			
//		//Salesforce Student Chosen Unmodified Time
		StudentTime = driver.findElement(By.xpath("//td/span[contains(text(),'Student Chosen Unmodified Time')]/../../td[2]")).getText();
		System.out.println("Student Chosen Unmodified Time ="+StudentTime);
  	}
  	
  @Test(priority = 10)
  	public void rescheduledEvent() throws InterruptedException {
	    event = driver.findElements(By.xpath("//*[text()='Web Scheduled Appointment']"));
		if(event.size()>0) {
	    System.out.println("Event created successfully!");
		}else {
		System.out.println("Event is not created");
		} 
	    
	    Thread.sleep(10000);	        	      		

  	}
  
  @Test(priority = 11)
  	public void schldEmail() throws InterruptedException, IOException {
  //Verifying emails in Gmail
  
  //Switch to Gmail
  for(String winHandle : driver.getWindowHandles()){
  	driver.switchTo().window(winHandle);
  	if(driver.getTitle().equals(GTitle)){
  	break;
  	}
  	}
  //Click back to go to inbox
  driver.navigate().back();
  driver.findElement(By.xpath("//*[@class='G-Ni J-J5-Ji'][3]")).click();
  
  //Gtitle = inbox title
  GTitle = driver.getTitle();
  System.out.println("Gmail Title = "+GTitle);
  Thread.sleep(15000);
  
	List<WebElement> inbox3 = driver.findElements(By.xpath("//*[@class='bog']"));
	Subject = "Scheduled Email Confirmation for "+InquiryForm.getfirstname()+" "+InquiryForm.getlastname();
	System.out.println(Subject);
	
	for(int a=0; a<inbox3.size(); a++) {   //searching for scheduled email
  	msg = inbox3.get(a).getText();
      if(msg.contains(Subject)) {
           inbox3.get(a).click();
           System.out.println("Found the scheduled email");
           break; 
      }
  }
	
	takeScreenShot("schldEmail",driver);
	
  	}
  

  	
  @Test(priority = 12)
  	public void cncldEmail() throws InterruptedException, IOException {
	driver.navigate().back();
	Thread.sleep(5000);
	List<WebElement> inbox4 = driver.findElements(By.xpath("//*[@class='bog']"));
	Subject = InquiryForm.getfirstname()+" "+InquiryForm.getlastname()+" : "+"Your appointment has been canceled";
	System.out.println(Subject);
	
	for(int a=0; a<inbox4.size(); a++) {
  	msg = inbox4.get(a).getText();
      if(msg.matches("(?i:.*"+Subject+".*)")) {
           inbox4.get(a).click();
           System.out.println("Found the canceled email");
           break; 
      }
  }
	takeScreenShot("cncldEmail",driver);
  
  
  driver.navigate().back();
  Thread.sleep(3000);
  
  	}
  	
  @Test(priority = 13)
  	public void reschldEmail() throws IOException { 
	List<WebElement >inbox5 = driver.findElements(By.xpath("//*[@class='bog']"));
	Subject = "Rescheduled Email Confirmation for "+InquiryForm.getfirstname()+" "+InquiryForm.getlastname();
	System.out.println(Subject);	
	
	for(int a=0; a<inbox5.size(); a++) {
  	msg = inbox5.get(a).getText();
      if(msg.matches("(?i:.*"+Subject+".*)")) {
           inbox5.get(a).click();
           System.out.println("Found the rescheduled email");
           break; 
      }
  }
	
	takeScreenShot("reschldEmail",driver);
	  driver.navigate().back();
  	}
  	
  @Test(priority = 14)
  	public void apptAlreadySchldEmail() throws IOException {
	List<WebElement> inbox6 = driver.findElements(By.xpath("//*[@class='bog']"));
	Subject = InquiryForm.getfirstname()+" "+InquiryForm.getlastname()+":Your Appointment's Scheduled";
	System.out.println(Subject);
	
	for(int a=0; a<inbox6.size(); a++) {
  	msg = inbox6.get(a).getText();
      if(msg.matches("(?i:.*"+Subject+".*)")) {
           inbox6.get(a).click();
           System.out.println("Found the email");
           break; 
      }
  }
	
	takeScreenShot("apptAlreadySchldEmail",driver);

	System.out.println("WAS Completed succesfully!");
  	}
  
  
  public static void takeScreenShot(String methodName, WebDriver d) throws IOException {
	  File file = ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(file,new File("C:\\Users\\abbasi.jaffar\\Desktop\\Selenium_Screenshots\\Image-"+methodName+".png"));
  }
  
  @Test(priority = 15)
  public void report() {
  EmailReport.main(null);
  }
  }
