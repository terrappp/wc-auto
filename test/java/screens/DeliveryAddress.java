package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DeliveryAddress extends ScreenBase{
	
	public DeliveryAddress(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblNext")
	public WebElement next;
	
	public TimeSlot clickOnNext() {
		next.click();
		return new TimeSlot(driver);
	}
}
