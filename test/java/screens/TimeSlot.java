package screens;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TimeSlot extends ScreenBase{
	
	public TimeSlot(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblReviewOrder")
	public WebElement reviewOrder;
	
	public TimeSlotRemind clickOnReviewOrder() {
		reviewOrder.click();
		return new TimeSlotRemind(driver);
	}
	
	public TimeSlot selectATimeSlot(String timeslot) {
		
		WebElement element = driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + timeslot + "\").instance(0))");
		List<WebElement> timeslots = driver.findElements(By.id("com.project.WhiteCoat:id/lblTimeSlot"));
		List<String> slotStrings = new ArrayList<String>();
		//can not use names.indexOf(element) because it always returns -1 ?! - don't know why
		//System.out.println(names.size() + "INDEX" + names.indexOf(element));
		
		for (int i=0; i<timeslots.size(); i++) {
			slotStrings.add(timeslots.get(i).getText());
		}
		List<WebElement> selections = driver.findElements(By.id("com.project.WhiteCoat:id/borderLayout"));
		selections.get(slotStrings.indexOf(element.getText())).click();
		
		return this;
	}
}
