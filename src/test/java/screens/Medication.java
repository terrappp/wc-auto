package screens;

import java.util.List;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Medication extends ScreenBase{
	
	public Medication(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/closeLayout")
	public WebElement close;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblSkip")
	public WebElement skip;
	@AndroidFindBy(id="com.project.WhiteCoat:id/medicineAutoCompeteTextView")
	public WebElement medication;
	@AndroidFindBy(id="com.project.WhiteCoat:id/takeMedicineTimeAutoComplete")
	public WebElement howlong;
	@AndroidFindBy(id="com.project.WhiteCoat:id/txtDosage")
	public WebElement dosage;
	
	public Cancel doCancel() {
		close.click();
		return new Cancel(driver);
	}
	
	public DrugAllergy skip() {
		skip.click();
		return new DrugAllergy(driver);
	}
	
	public DrugAllergy next() {
		driver.findElement(MobileBy.id("com.project.WhiteCoat:id/lblNext")).click();
		return new DrugAllergy(driver);
	}
	
	public Medication inputMedicationTaken(String medications) {
		if (medications != "") {
			//patient can input many medication taken, separate by ";"
			String[] medicationList = medications.split("; ");
			
			int index = 0;
			for (String med: medicationList) {
				//within a medication taken, details are separated by "," fully
				String[] detailList = med.split(", ");
				
				//medication: fill and select from suggestions
				List<WebElement> meds = driver.findElements(MobileBy.id("com.project.WhiteCoat:id/medicineAutoCompeteTextView"));
				meds.get(index).sendKeys(detailList[0]);
//				driver.findElementByAndroidUIAutomator("textContains(\"" + detailList[0] + "\")").click();
//				driver.pressKey(new KeyEvent(AndroidKey.PAGE_UP));
//				driver.pressKey(new KeyEvent(AndroidKey.ENTER));
				
				//how long: select from suggestions
				List<WebElement> howlongs = driver.findElements(MobileBy.id("com.project.WhiteCoat:id/takeMedicineTimeAutoComplete"));
				howlongs.get(index).click();
				for (int i=0; i < Integer.parseInt(detailList[1]); i++ ) {
					driver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
				}
				driver.pressKey(new KeyEvent(AndroidKey.ENTER));
				
				//dosage: input dosage
				List<WebElement> dosages = driver.findElements(MobileBy.id("com.project.WhiteCoat:id/txtDosage"));
				dosages.get(index).sendKeys(detailList[2]);
				
				index++;
				//add more record if not end of list yet
				if (index <= medicationList.length) {
					driver.findElement(MobileBy.id("com.project.WhiteCoat:id/addMedicineLayout")).click();
				}
			}	
		}
		return this;
	}
}
