package testcases;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.TestBase;
import screens.LetStart;
import utils.CommonUtils;

public class WebViewScreenValidationTest extends TestBase {
	
	

	
	
	@Test(priority = 2)
	public void validateWebViewScreen(){
		
		LetStart webview = new LetStart(driver);
		webview.validateWebViewText();
		
	}

}
