package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AddPhoto extends ScreenBase{
	
	public AddPhoto(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblAlbum")
	public WebElement selectPhoto;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblPhoto")
	public WebElement takePhoto;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblCancel")
	public WebElement cancel;
	
	public Symptoms doCancel() {
		cancel.click();
		return new Symptoms(driver);
	}
}
