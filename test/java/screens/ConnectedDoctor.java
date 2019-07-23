package screens;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ConnectedDoctor extends ScreenBase{
	
	public ConnectedDoctor(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/IbtnMedicine")
	public WebElement prescription;
	@AndroidFindBy(id="com.project.WhiteCoat:id/IbtnCamera")
	public WebElement switchCamera;
	@AndroidFindBy(id="com.project.WhiteCoat:id/IbtnMute")
	public WebElement mute;
	
	public boolean stillExist(String locator) {
		try {
			  System.out.println("CHECKING!");
	          return driver.findElementById(locator).isDisplayed();
	        } catch (NoSuchElementException e) {
	          // Returns true because the element is not present in DOM. The
	          // try block checks if the element is present but is invisible.
			  System.out.println("NOSUCHELEMENT!");
	          return false;
	        } catch (StaleElementReferenceException e) {
	          // Returns true because stale element reference implies that element
	          // is no longer visible.
	          System.out.println("STALE!");
	          return false;
	        }
	}
	
	public ScreenBase waitTillCallEnded() throws InterruptedException {
		//reset implicitWait to make find "Mute" less after ended call
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
//		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
//				.withTimeout(Duration.ofHours(3))
//				.pollingEvery(Duration.ofSeconds(5))
//				.ignoring(NoSuchElementException.class);
//		wait1.until(ExpectedConditions.invisibilityOfElementLocated(By.id("com.project.WhiteCoat:id/IbtnMute")));
		
		String locator = "com.project.WhiteCoat:id/IbtnMute";
		while (stillExist(locator)) {
			System.out.println("LOOP!");
			Thread.sleep(3000);
		}
		System.out.println("STOP LOOPPING!");
		
		//reset implicitWait to make find elements take less time
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		try {
			driver.findElement(By.id("com.project.WhiteCoat:id/lblOK"));
			
			//reset implicitWait to 10s
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			return new WaivedConsultationFee(driver);
		} catch(NoSuchElementException e1) {
			try {
				driver.findElement(By.id("com.project.WhiteCoat:id/lblNext"));
				
				//reset implicitWait to 10s
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				return new DrugPrescribed(driver);
			} catch(NoSuchElementException e2) {
				//reset implicitWait to 10s
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				return new PaymentScreen(driver);
			}
		}
		
	}
}
