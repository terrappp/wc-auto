package testcases;

import java.io.IOException;
import java.util.Hashtable;

import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import screens.HomeScreen;
import screens.LetStart;
import utils.CommonUtils;
import utils.TestUtils;

public class SelendroidHomeScreenValidationTest extends TestBase{

	
	
	
	@Test(dataProvider = "getData", priority = 1)
	public void validateHomeScreenTest(Hashtable<String, String> data){
		
		homeScreen = new HomeScreen(driver);
		homeScreen.typeData(data.get("inputText"));
		homeScreen.validateTextView();
		homeScreen.startWebView();
	
		
	}
	
	@DataProvider
	public static Object[][] getData() {		
		return TestUtils.getData("HelloWorld");
	}
	
}
