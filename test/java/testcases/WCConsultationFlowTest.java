package testcases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ScreenBase;
import base.TestBase;
import screens.ConnectedDoctor;
import screens.ConnectingDoctor;
import screens.DeliveryAddress;
import screens.DrugAllergy;
import screens.DrugPrescribed;
import screens.FindDoctor;
import screens.HomeScreen;
import screens.LetBegin;
import screens.LetStart;
import screens.LogIn;
import screens.Medication;
import screens.NoMedRemind;
import screens.PaymentComplete;
import screens.PaymentScreen;
import screens.Symptoms;
import screens.TimeSlot;
import screens.TimeSlotRemind;
import screens.WaivedConsultationFee;
import screens.WifiConnection;
import utils.TestUtils;

public class WCConsultationFlowTest extends TestBase{
	
	@DataProvider
	public static Object[][] getData() {
		//read data by sheet name
		return TestUtils.getData("Consultation Flow");
	}
	
	public PaymentScreen buyMed(DrugPrescribed selectDrug, String isBuyMed, String timeslot) {
		PaymentScreen payment;
		if (isBuyMed.equalsIgnoreCase("Yes")) { //buy med
			DeliveryAddress deliveryAddress = selectDrug.clickOnNext();
			TimeSlot timeslotScreen = deliveryAddress.clickOnNext();
			timeslotScreen.selectATimeSlot(timeslot);
			TimeSlotRemind timeslotRemind = timeslotScreen.clickOnReviewOrder();
			payment = timeslotRemind.clickOnOK();
		} else { //dont buy med
			NoMedRemind noMedRemind = selectDrug.clickOnDontNeed();
			payment = noMedRemind.clickOnOK();
		}
		
		return payment;
	}
	
	public void doPayment(PaymentScreen payment, String promoCode) throws InterruptedException {
		//payment
		if (!promoCode.equalsIgnoreCase("")) {
			payment.applyPromoCode(promoCode);
		}
		
		//confirm and done
		PaymentComplete paymentComplete = payment.clickOnConfirm();
		paymentComplete.clickOnDone();
		
		return;
	}
	
	@Test(dataProvider = "getData", priority = 1)
	public void doConsult(Hashtable<String, String> data) throws InterruptedException{
		
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
		
		//wait till call ended
		ScreenBase returned = connectedDoctor.waitTillCallEnded();
		try {
			//waived consultation fee screen
			WaivedConsultationFee waived = (WaivedConsultationFee) returned;
			System.out.println("WAIVED");
			
			ScreenBase afterClickOk = waived.tapOnOK();
			try { //select medication
				DrugPrescribed selectDrug = (DrugPrescribed) afterClickOk;
				PaymentScreen payment = buyMed(selectDrug, data.get("isBuyMed"), data.get("timeslot"));
				doPayment(payment, data.get("promoCode"));
//				PaymentScreen payment;
//				if (data.get("isBuyMed").equalsIgnoreCase("Yes")) { //buy med
//					DeliveryAddress deliveryAddress = selectDrug.clickOnNext();
//					TimeSlot timeslot = deliveryAddress.clickOnNext();
//					timeslot.selectATimeSlot(data.get("timeslot"));
//					TimeSlotRemind timeslotRemind = timeslot.clickOnReviewOrder();
//					payment = timeslotRemind.clickOnOK();
//				} else { //dont buy med
//					NoMedRemind noMedRemind = selectDrug.clickOnDontNeed();
//					payment = noMedRemind.clickOnOK();
//				}
//				
//				//payment
//				if (!data.get("promoCode").equalsIgnoreCase("")) {
//					payment.applyPromoCode(data.get("promoCode"));
//				}
//				
//				//confirm and done
//				PaymentComplete paymentComplete = payment.clickOnConfirm();
//				paymentComplete.clickOnDone();

			} catch(Throwable t) { //jump to payment screen
				PaymentScreen payment = (PaymentScreen) afterClickOk;
				//payment
				doPayment(payment, data.get("promoCode"));
//				if (!data.get("promoCode").equalsIgnoreCase("")) {
//					payment.applyPromoCode(data.get("promoCode"));
//				}
//				
//				//confirm and done
//				PaymentComplete paymentComplete = payment.clickOnConfirm();
//				paymentComplete.clickOnDone();
			}
			
		} catch(Throwable t1) {
			try {
				DrugPrescribed selectDrug = (DrugPrescribed) returned;
				System.out.println("PRESCRIBED");
				PaymentScreen payment = buyMed(selectDrug, data.get("isBuyMed"), data.get("timeslot"));
				doPayment(payment, data.get("promoCode"));
				
			} catch(Throwable t2) {
				PaymentScreen payment = (PaymentScreen) returned;
				System.out.println("PAYMENT");
				doPayment(payment, data.get("promoCode"));
			}
		}

	}	
}
