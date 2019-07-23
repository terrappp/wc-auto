package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TimeSlotRemind extends ScreenBase{
	
	public TimeSlotRemind(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblCancel")
	public WebElement back;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblOK")
	public WebElement ok;
	
	public TimeSlot clickOnBack() {
		back.click();
		return new TimeSlot(driver);
	}
	
	public PaymentScreen clickOnOK() {
		ok.click();
		return new PaymentScreen(driver);
	}
}
