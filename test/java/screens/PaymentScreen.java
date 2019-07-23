package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class PaymentScreen extends ScreenBase{
	
	public PaymentScreen(AndroidDriver driver){
		super(driver);
	}
	
	public PaymentScreen applyPromoCode(String promoCode) throws InterruptedException {
		//scroll down to see Apply button
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Apply\").instance(0))");
		driver.findElement(By.id("com.project.WhiteCoat:id/txtCorporateIdentifier")).sendKeys(promoCode);
		//collapse the keyboard
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(400); //wait for BACK completely
		driver.findElement(By.id("com.project.WhiteCoat:id/btnApply")).click();
		
		return this;
	}
	
	public PaymentComplete clickOnConfirm() {
		//scroll down to see Confirm button --(instance(1)--
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Confirm\").instance(1))");
		driver.findElement(By.id("com.project.WhiteCoat:id/lblPayNow")).click();
		
		return new PaymentComplete(driver);
	}
}
