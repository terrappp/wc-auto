package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WifiConnection extends ScreenBase{
	
	public WifiConnection(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblOK")
	public WebElement ok;
	
	public ScreenBase tapOnOK(boolean isSeeDoctor) {
		ok.click();
		if (isSeeDoctor) {
			return new ConnectingDoctor(driver);
		} else {
			return new FindDoctor(driver);
		}
	}
}
