package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PaymentComplete extends ScreenBase{
	
	public PaymentComplete(AndroidDriver driver){
		super(driver);
	}
	
//	@AndroidFindBy(id="com.project.WhiteCoat:id/lblDone")
//	public WebElement done;
	
	public HomeScreen clickOnDone() {
		//scroll down to see Done button
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Done\").instance(0))");
		driver.findElement(By.id("com.project.WhiteCoat:id/lblDone")).click();
		
		return new HomeScreen(driver);
	}
}
