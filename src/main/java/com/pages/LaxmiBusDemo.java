package com.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LaxmiBusDemo {
	public static void main(String[] args) throws Exception {

		/*System is a java class that belongs to the java.lang package, 
   setProperty take two parameters(key, value) 
    if the key or value is null then throw nullPointerExeception,
    if the key is empty then throw illegalArgumentExecption.Value means path of the browser */

		System.setProperty("webdriver.chrome.driver","D://chromedriver_win32/chromedriver.exe");

		//WebDriverManager no need to path of the browser it is automated browser and driver management.	
		//WebDriverManager.chromedriver().setup();

		/*Using WebDriver interface instead of creating an instance of a specific browser class
		(ChromeDriver,FirefoxDriver,SafariDriver,IEDriver etc.) 
		It is used to perform cross browser testing */

		WebDriver driver = new ChromeDriver();

		//Open Url 
		driver.get(" https://www.laxmiholidays.com/");

		//Maximize the window
		driver.manage().window().maximize();

		//Define the expected title
		String expectedTitle = "Laxmi Holidays";

		//Get the actual title of the webpage
		String actualTitle = driver.getTitle();

		//Compare the actual and expected titles
		if(actualTitle.equals(expectedTitle)) 
		{
			System.out.println("Test passed actual title matches expected title");
		}
		else
		{
			System.out.println("Test failed actual title: "+actualTitle+ "does not match expected title: "+expectedTitle);
		}

		//Enter source station and locate
		WebElement sourceStationBox = driver.findElement(By.xpath("//div[text()='Origin']"));
		sourceStationBox.click();

		// Create robot class object,It is used for keyboard interaction, Robot class it is part of java 
		Robot robot = new Robot();

		//Enter Source station value
		String text = "Mana";
		for (char c : text.toCharArray()) {
			int keys = KeyEvent.getExtendedKeyCodeForChar(c);
			robot.keyPress(keys);
			robot.keyRelease(keys);
		}

		//This line simulates pressing the Enter key
		robot.keyPress(KeyEvent.VK_ENTER);

		//This line simulates releasing the Enter key
		robot.keyRelease(KeyEvent.VK_ENTER);

		//Enter target station value
		String text1 = "Delhi";
		for (char c : text1.toCharArray()) {
			int keys = KeyEvent.getExtendedKeyCodeForChar(c);
			robot.keyPress(keys);
			robot.keyRelease(keys);  
		}
		//This statement pauses the execution of fixed timing
		Thread.sleep(2000);	
		robot.keyPress(KeyEvent.VK_ENTER);

		//Date select for travling. Providing specific date, month  
		String month = "Mar";
		String date = "22";

		while(true)
		{
			//Create the object of wait(Emplicity wait) it is dynamic wait.
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Mar']")));

			String months= driver.findElement(By.xpath("//a[text()='Mar']")).getText();

			String arr[] = months.split(" ");
			String mnt   =  arr[0];
			if(mnt.equalsIgnoreCase(month))
				break;
			else
				driver.findElement(By.xpath("//a[@title='Next month (PageDown)']")).click();
		}

		//Date select by using for each loop.
		List<WebElement> alldate = driver.findElements(By.xpath("//table[@class='ant-calendar-table']//td"));
		for(WebElement ele :alldate) {
			String dt = ele.getText();
			if(dt.equalsIgnoreCase(date)) {
				ele.click();
				break;
			}
		}

		//Click on search button
		driver.findElement(By.id("gt-search")).click();

		//List of all buses will be displayed

		WebDriverWait wait = new WebDriverWait(driver, 20);
		for (int i = 1; i <= 13; i++) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@type='button'])[" + i + "]")));
			driver.findElement(By.xpath("(//button[@type='button'])[" + i + "]")).click();

			Thread.sleep(2000);
			//Check available seat list on webpage 
			WebElement seat = driver.findElement(By.xpath("(//div[@class='ant-col-3 text-center'])[" + (i+1) + "]"));
			String seatsAvailableText = seat.getText();

			// Extract only the numeric part of the string
			String numericPart = seatsAvailableText.replaceAll("\\D+", "");

			// Parse the numeric part as an integer
			int seatsAvailableOnWebpage = Integer.parseInt(numericPart);

			//Seat available on seat layout
			List<WebElement> seatAvailable = driver.findElements(By.xpath("//div[@class='available_seat' or @class='ladies_qta_seat available_seat' or @class='gents_qta_seat available_seat']"));
			System.out.println("Total seat available:=>" + seatAvailable.size());
			int totalSeatAvailableOnSeatLayout = seatAvailable.size();

			//comparison of seats Available in the list is matching with the seats available on the seat layout 
			if (seatsAvailableOnWebpage == totalSeatAvailableOnSeatLayout) {
				System.out.println("Available seats on the web page:=> "+seatsAvailableOnWebpage+ " = " +totalSeatAvailableOnSeatLayout+" <=Available seats on the seat layout: " + i + "th bus");
			} else {
				System.out.println("Available seats on the web page:=> "+seatsAvailableOnWebpage+ " != " +totalSeatAvailableOnSeatLayout+" <= Do not match the seat layout for " + i + "th bus.");
			}
			// Check if seat availability is zero
			if (seatsAvailableOnWebpage == 0) {
				// Click on the service-close-btn button if available
				try {
					WebElement closeButton = driver.findElement(By.xpath("//a[@class='service-close-btn']"));
					closeButton.click();
				} catch (NoSuchElementException e) {
					System.out.println("Service close button not found for the " + i + "th bus.");
				}
			} else {
				// this button work if seat available then Close bus view
				try {
					WebElement closeButton = driver.findElement(By.xpath("//a[@title='close']"));
					closeButton.click();
				} catch (NoSuchElementException e) {
					System.out.println("Close button not found for the " + i + "th bus.");
				}
			}
		}
		//close the current window
		driver.close();
	}		
}

