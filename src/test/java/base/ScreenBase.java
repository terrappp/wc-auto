package base;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ScreenBase {
	
	
	public AndroidDriver driver;
	
	public ScreenBase(AndroidDriver driver){
		
		this.driver = driver;
		loadElements();
	}


	public void loadElements(){
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
