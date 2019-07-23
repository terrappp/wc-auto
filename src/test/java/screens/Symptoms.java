package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Symptoms extends ScreenBase{
	
	public Symptoms(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/closeLayout")
	public WebElement close;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblSkip")
	public WebElement skip;
	@AndroidFindBy(id="com.project.WhiteCoat:id/btnAddPhoto")
	public WebElement addPhoto;
	
	//addPhoto triggers phone-based applications, so that automation code is not able to handle adding photo.
	public Cancel doCancel() {
		close.click();
		return new Cancel(driver);
	}
	
	public Medication skip() {
		skip.click();
		return new Medication(driver);
	}
	
	public AddPhoto tapToAddPhoto() {
		addPhoto.click();
		return new AddPhoto(driver);
	}
}
