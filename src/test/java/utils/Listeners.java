package utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.TestBase;

public class Listeners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {

		System.out.println("Passed testcase - "+result.getName());
		
	}
	
	public static String screenshotpath;
	
	public void onTestFailure(ITestResult result) {
		//Making HTML code run in the report
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		//** Screenshot failed testcase
	    //Using GregorianCalendar to fetch the time details
        Calendar cal = new GregorianCalendar();
        //Month value is always 1 less than actual. For February, MONTH would return 1 
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int sec = cal.get(Calendar.SECOND);
        int min = cal.get(Calendar.MINUTE);
        int date = cal.get(Calendar.DATE);
        int day = cal.get(Calendar.HOUR_OF_DAY);

        //Take screen shot and store it in volatile memory with reference name scrFile
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("Got the screenshot!");
        try {
            //Create unique file name and store in under screenshot folder of root directory
            screenshotpath = System.getProperty("user.dir")+"/src/test/resources/screenshots/" + result.getName() + "_" + year + "_" + date+ "_" + (month + 1) + "_" + day + "_" + min + "_" + sec + ".jpeg";
            System.out.println("Added empty file!");
            //Copy the file to destination
            FileHandler.copy(scrFile, new File(screenshotpath));
            System.out.println("Added image file!");
            //showing in the report
    		Reporter.log("<a href=" + screenshotpath + " target=\"_blank\">Screenshot link</a>");
    		Reporter.log("<br>");
    		Reporter.log("<a href=" + screenshotpath + " target=\"_blank\"><img height=200 width=200 src=" + screenshotpath + "></a>");
    		System.out.println("Capturing screenshot for the failed testcase - "+ result.getName());
    		
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println("Capturing failed!");
            e.printStackTrace();

        }
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
