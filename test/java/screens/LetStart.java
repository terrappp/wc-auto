package screens;



import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import base.ScreenBase;

public class LetStart extends ScreenBase {

	public LetStart(AndroidDriver driver) {
		super(driver);
	}
	
//	@AndroidFindBys({
//		@AndroidBy(id="io.selendroid.testapp:id/tableHeader"),
//		@AndroidBy(className="android.widget.TextView")	
//	})
//	public WebElement webviewText;
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblOK")
	public WebElement letStart;
	@AndroidFindBy(id="com.project.WhiteCoat:id/view1")
	public WebElement tapToCancel;
	
	public ScreenBase tapOnLetStart(boolean child) {
		letStart.click();
		if (child) {
			return new SelectChild(driver);
		} else {
			return new LetBegin(driver);
		}
	}
	
	public HomeScreen doCancel() {
		tapToCancel.click();
		return new HomeScreen(driver);
	}
}
