package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SelectChild extends ScreenBase{
	
	public SelectChild(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblNext")
	public WebElement next;
	@AndroidFindBy(id="com.project.WhiteCoat:id/closeLayout")
	public WebElement close;
	
	public SelectChild selectChild(String childName) {
		driver.findElementByAndroidUIAutomator("text(" + childName + ")").click();
		return this;
	}
	
	public LetBegin clickOnNext() {
		next.click();
		return new LetBegin(driver);
	}
	
	public Cancel doCancel() {
		close.click();
		return new Cancel(driver);
	}
}
