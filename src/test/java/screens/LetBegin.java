package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LetBegin extends ScreenBase{
	
	public LetBegin(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/closeLayout")
	public WebElement close;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblSkip")
	public WebElement skip;
	
	public LetBegin experiencingSymtomps(String symptoms) {
		String[] symptomList = symptoms.split(", ");
		for (String stm: symptomList) {
			driver.findElementByAndroidUIAutomator("textContains(\"" + stm + "\")").click();
		}
		return this;
	}
	
	public LetBegin requireServices(String services) {
		String[] serviceList = services.split(",");
		for (String srv: serviceList) {
			driver.findElementByAndroidUIAutomator("text(" + srv + ")").click();
		}
		return this;
	}
	
	public Cancel doCancel() {
		close.click();
		return new Cancel(driver);
	}
	
	public Symptoms skip() {
		skip.click();
		return new Symptoms(driver);
	}
	
	public Symptoms next() {
		driver.findElement(MobileBy.id("com.project.WhiteCoat:id/lblNext")).click();
		return new Symptoms(driver);
	}
}
