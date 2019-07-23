package screens;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ScreenBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomeScreen extends ScreenBase{

	public HomeScreen(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/pushLayout")
	public WebElement notiBell;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblMySelf")
	public WebElement myself;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblChild")
	public WebElement myChild;
	@AndroidFindBy(id="com.project.WhiteCoat:id/tab1Layout")
	public WebElement consult;
	@AndroidFindBy(id="com.project.WhiteCoat:id/tab2Layout")
	public WebElement about;
	@AndroidFindBy(id="com.project.WhiteCoat:id/tab3Layout")
	public WebElement history;
	@AndroidFindBy(id="com.project.WhiteCoat:id/tab4Layout")
	public WebElement profile;
	
	public LetStart tapOnMyself() {
		myself.click();
		return new LetStart(driver);
	}
	
	public LetStart tapOnMyChild() {
		myChild.click();
		return new LetStart(driver);
	}
	
	public HomeScreen tapOnConsult() {
		consult.click();
		return this;
	}
	
	public About tapOnAbout() {
		about.click();
		return new About(driver);
	}
	
	public History tapOnHistory() {
		history.click();
		return new History(driver);
	}
}
