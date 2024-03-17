package com.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareSeat {

	public static void main(String[] args) throws AWTException {
		
		System.setProperty("webdriver.chrome.driver","D://chromedriver_win32/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.laxmiholidays.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement location = driver.findElement(By.xpath("//div[text()='Origin']"));
		location.click();
		Robot robot = new Robot();
		String text = "Manali";
		for (char c : text.toCharArray()) {
		    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
		    robot.keyPress(keyCode);
		    robot.keyRelease(keyCode);
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		String text1 = "Delhi";
		for (char c : text1.toCharArray()) {
		    int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
		    robot.keyPress(keyCode);
		    robot.keyRelease(keyCode);  
		}
		
		//automatic current date(today date) select by using robot class
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
         driver.findElement(By.id("gt-search")).click();
		WebDriverWait wait = new WebDriverWait(driver,20);
		
		//1st bus view
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[1]")));
	    driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
	    
	    
	    WebElement seat = driver.findElement(By.xpath("(//div[@class='ant-col-3 text-center'])[2]"));
	    String seatsAvailableText = seat.getText();
	    
	    // Extract only the numeric part of the string
        String numericPart = seatsAvailableText.replaceAll("\\D+", "");
        
        // Parse the numeric part as an integer
        int seatsAvailableOnWebpage = Integer.parseInt(numericPart);
	  
	    //Seat availab;e on seat layout
	    List <WebElement> seatAvailable = driver.findElements(By.xpath("//div[@class=\"available_seat\" or @class=\"gents_qta_seat available_seat\"]"));
	    System.out.println("Total seat available:=>"+seatAvailable.size());
	   int totalSeatAvailableOnSeatLayout = seatAvailable.size();
	    
       //comparison of seats Available in the list is matching with the seats available on the seat layout 
	   if(seatsAvailableOnWebpage == totalSeatAvailableOnSeatLayout) {
		   System.out.println("Available seats on the web page match the seat layout!");
       } else {
           System.out.println("Available seats on the web page do not match the seat layout.");
       }
	   
	   //close one bus view 
	   driver.findElement(By.xpath("//a[@title='close']")).click(); 
	  
	   //2nd bus view
	     
	   WebDriverWait wait2= new WebDriverWait(driver,20);
	   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
	    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	    
	    
	    WebElement seat2 = driver.findElement(By.xpath("(//div[@class='ant-col-3 text-center'])[3]"));
	    String seatsAvailableText2 = seat2.getText();
	    
	    // Extract only the numeric part of the string
       String numericPart2 = seatsAvailableText2.replaceAll("\\D+", "");
       
       // Parse the numeric part as an integer
       int seatsAvailableOnWebpage2 = Integer.parseInt(numericPart2);
	  
	    //Seat available on seat layout
	    List <WebElement> seatAvailable2 = driver.findElements(By.xpath("//div[@class=\"available_seat\" or @class=\"gents_qta_seat available_seat\"]"));
	    System.out.println("Total seat available:=>"+seatAvailable2.size());
	   int totalSeatAvailableOnSeatLayout2 = seatAvailable2.size();
	    
       //comparison of seats Available in the list is matching with the seats available on the seat layout 
	   if(seatsAvailableOnWebpage2 == totalSeatAvailableOnSeatLayout2) {
		   System.out.println("Available seats on the web page match the seat layout! for 2nd bus");
      } else {
          System.out.println("Available seats on the web page do not match the seat layout for 2nd bus.");
      }
	   
	   //close second bus view 
	   driver.findElement(By.xpath("//a[@title='close']")).click(); 
	   
	   //3rd bus view
	   
	   WebDriverWait wait3= new WebDriverWait(driver,20);
	   wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[3]")));
	    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
	    
	    
	    WebElement seat3 = driver.findElement(By.xpath("(//div[@class='ant-col-3 text-center'])[4]"));
	    String seatsAvailableText3 = seat3.getText();
	    
	    // Extract only the numeric part of the string
       String numericPart3 = seatsAvailableText3.replaceAll("\\D+", "");
       
       // Parse the numeric part as an integer
       int seatsAvailableOnWebpage3 = Integer.parseInt(numericPart3);
	  
	    //Seat available on seat layout
	    List <WebElement> seatAvailable3 = driver.findElements(By.xpath("//div[@class='available_seat' or @class='gents_qta_seat available_seat']"));
	    System.out.println("Total seat available:=>"+seatAvailable3.size());
	   int totalSeatAvailableOnSeatLayout3 = seatAvailable3.size();
	   
       //comparison of seats Available in the list is matching with the seats available on the seat layout    
	   if(seatsAvailableOnWebpage3 == totalSeatAvailableOnSeatLayout3) {
		   System.out.println("Available seats on the web page match the seat layout! for 3nd bus");
      } else {
          System.out.println("Available seats on the web page do not match the seat layout for 3nd bus.");
      }
	   
	   //close second bus view 
	   driver.findElement(By.xpath("//a[@title='close']")).click(); 
	   
	   
	   
	   //for next bus
	   WebDriverWait wait7= new WebDriverWait(driver,20);
	   wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[7]")));
	    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
	    
	    
	    WebElement seat7 = driver.findElement(By.xpath("(//div[@class='ant-col-3 text-center'])[8]"));
	    String seatsAvailableText7 = seat7.getText();
	    
	    // Extract only the numeric part of the string
       String numericPart7 = seatsAvailableText7.replaceAll("\\D+", "");
       
       // Parse the numeric part as an integer
       int seatsAvailableOnWebpage7 = Integer.parseInt(numericPart7);
	  
	    //Seat available on seat layout
	    List <WebElement> seatAvailable7 = driver.findElements(By.xpath("//div[@class='available_seat' or @class='gents_qta_seat available_seat']"));
	    System.out.println("Total seat available:=>"+seatAvailable7.size());
	   int totalSeatAvailableOnSeatLayout7 = seatAvailable7.size();
	  
       //comparison of seats Available in the list is matching with the seats available on the seat layout 
	   if(seatsAvailableOnWebpage7 == totalSeatAvailableOnSeatLayout7) {
		   System.out.println("Available seats on the web page match the seat layout! for 7th bus");
      } else {
          System.out.println("Available seats on the web page do not match the seat layout for 7th bus.");
      }
	   
	   //close second bus view 
	   driver.findElement(By.xpath("//a[@title='close']")).click(); 
       
	}
 
}
