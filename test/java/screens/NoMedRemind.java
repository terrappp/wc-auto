package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class NoMedRemind extends ScreenBase{
	
	public NoMedRemind(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblOK")
	public WebElement ok;
	
	public PaymentScreen clickOnOK() {
		ok.click();
		return new PaymentScreen(driver);
	}
}
