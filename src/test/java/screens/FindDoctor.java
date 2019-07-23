package screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;

import base.ScreenBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FindDoctor extends ScreenBase{
	
	public FindDoctor(AndroidDriver driver){
		super(driver);
	}
	
	public ConnectingDoctor selectDoctor(String doctorName) {
		//scroll screen
//		TouchActions action = new TouchActions(driver);
//		action.scroll(0, 100);
//		action.perform();
		
		//find element within function because many doctors have the same element id
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + doctorName + "\").instance(0))")
		.findElement(MobileBy.id("com.project.WhiteCoat:id/lblSelect")).click();
//		driver.findElementByAndroidUIAutomator("textContains(\"" + doctorName + "\")")
//		.findElement(MobileBy.id("com.project.WhiteCoat:id/lblSelect")).click();
		
		return new ConnectingDoctor(driver);
	}
}
