package screens;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;

public class FindDoctor extends ScreenBase{
	
	public FindDoctor(AndroidDriver driver){
		super(driver);
	}
	
	public ConnectingDoctor selectDoctor(String doctorName) {
		//scroll screen
//		TouchActions action = new TouchActions(driver);
//		action.scroll(0, 100);
//		action.perform();
		
//		driver.findElementByAndroidUIAutomator("textContains(\"" + doctorName + "\")")
//		.findElement(MobileBy.id("com.project.WhiteCoat:id/lblSelect")).click();
		
		//find element within function because many doctors have the same element id
		WebElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + doctorName + "\").instance(0))");
		List<WebElement> names = driver.findElements(By.id("com.project.WhiteCoat:id/lblName"));
		List<String> nameStrings = new ArrayList<String>();
		//can not use names.indexOf(element) because it always returns -1 ?! - don't know why
		//System.out.println(names.size() + "INDEX" + names.indexOf(element));
		
		for (int i=0; i<names.size(); i++) {
			nameStrings.add(names.get(i).getText());
		}
		List<WebElement> selections = driver.findElements(By.id("com.project.WhiteCoat:id/lblSelect"));
		selections.get(nameStrings.indexOf(element.getText())).click();
		
		return new ConnectingDoctor(driver);
	}
}
