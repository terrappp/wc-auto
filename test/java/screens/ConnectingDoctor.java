package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ConnectingDoctor extends ScreenBase{
	
	public ConnectingDoctor(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblCancelConsult")
	public WebElement cancelConsult;
	
	public AreYouSure doCancel() {
		cancelConsult.click();
		return new AreYouSure(driver);
	}
	
	public ConnectedDoctor waitToTapOnConnect() {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		WebElement connect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.project.WhiteCoat:id/shimmerFrameLayout")));
		connect.click();
		return new ConnectedDoctor(driver);
	}
}
