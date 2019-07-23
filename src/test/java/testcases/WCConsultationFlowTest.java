package testcases;

import java.io.IOException;
import java.util.Hashtable;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import screens.ConnectedDoctor;
import screens.ConnectingDoctor;
import screens.DrugAllergy;
import screens.FindDoctor;
import screens.HomeScreen;
import screens.LetBegin;
import screens.LetStart;
import screens.LogIn;
import screens.Medication;
import screens.Symptoms;
import screens.WifiConnection;
import utils.CommonUtils;
import utils.TestUtils;

public class WCConsultationFlowTest extends TestBase{
	@Test(dataProvider = "getData", priority = 1)
	public void doConsult(Hashtable<String, String> data){
		
		// **login
		LogIn login = new LogIn(driver);
		HomeScreen homeScreen = (HomeScreen) login.doLogin(data.get("email"), data.get("password"), data.get("expected1"), data.get("expected2"));
	
		// **home screen
		LetStart letStart = homeScreen.tapOnMyself();
		
		// **let's start screen
		LetBegin letBegin = (LetBegin) letStart.tapOnLetStart(data.get("isChildConsult")=="Yes");
		
		// **let's begin screen
		//Symptoms symptoms = letBegin.skip();
		letBegin.experiencingSymtomps(data.get("experiencingSymtomps"));
		Symptoms symptoms = letBegin.next();
		
		// **symptoms screen
		Medication medication = symptoms.skip();
		
		// **medication screeen
		DrugAllergy drugAllergy = medication.skip();
		//medication.inputMedicationTaken(data.get("medications"));
		//DrugAllergy drugAllergy = medication.next();
		
		// **drug allergy screen
		drugAllergy.checkG6PD();
		
		ConnectingDoctor connectingDoctor;
		if (data.get("isSeeDoctor").equalsIgnoreCase("No")) {
			System.out.println("CHOOSE A SPECIFIC DOCTOR");
			//wifi and find a doctor screens
			WifiConnection wifi = drugAllergy.tapOnChooseDoctor();
			FindDoctor findDoctor = (FindDoctor) wifi.tapOnOK(false);
			connectingDoctor = findDoctor.selectDoctor(data.get("doctorName"));
			
		} else {
			System.out.println("CHOOSE A RANDOM DOCTOR");
			//wifi and connecting to doctor screens
			WifiConnection wifi = drugAllergy.tapOnSeeDoctor();
			connectingDoctor = (ConnectingDoctor) wifi.tapOnOK(true);
		}
		
		//connecting to doctor screen
		ConnectedDoctor connectedDoctor = connectingDoctor.waitToTapOnConnect();
	}
	
	@DataProvider
	public static Object[][] getData() {
		//read data by sheet name
		return TestUtils.getData("Consultation Flow");
	}
	
}
