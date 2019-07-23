package base;

import java.io.IOException;

import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import screens.HomeScreen;
import screens.LetStart;
import utils.AppiumServer;
import utils.CommonUtils;
import utils.ExcelReader;

public class TestBase {
	

	public static AndroidDriver driver;
//	public HomeScreen homeScreen;
//	public LetStart webview;
	public static ExcelReader excel = new ExcelReader("./TestData/WC_TestData.xlsx");
	
	@BeforeSuite
	public void setUp() throws IOException, InterruptedException{
		
		if(driver==null){
	
		AppiumServer.stop();
		AppiumServer.start();
		CommonUtils.loadConfigProp("whitecoat.properties");
		CommonUtils.setCapabilities();
		driver = CommonUtils.getDriver();
		}
		
	}
	
	@AfterSuite
	public void tearDown() throws IOException{
		
		driver.quit();
		AppiumServer.stop();
		
	}

}
