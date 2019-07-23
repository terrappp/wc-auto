package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DrugPrescribed extends ScreenBase{
	
	public DrugPrescribed(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblNext")
	public WebElement next;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblNoMedicine")
	public WebElement dontNeed;
	
	public DeliveryAddress clickOnNext() {
		next.click();
		return new DeliveryAddress(driver);
	}
	
	public NoMedRemind clickOnDontNeed() {
		dontNeed.click();
		return new NoMedRemind(driver);
	}
}
