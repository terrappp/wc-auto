package screens;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WaivedConsultationFee extends ScreenBase{
	
	public WaivedConsultationFee(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblOK")
	public WebElement ok;
	
	public ScreenBase tapOnOK() {
		ok.click();
		//reset implicitWait to make find elements take less time
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try {
			driver.findElement(By.id("com.project.WhiteCoat:id/lblNext"));
			//reset implicitWait to 10s
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return new DrugPrescribed(driver);
		} catch(NoSuchElementException e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return new PaymentScreen(driver);
		}
	}
}
