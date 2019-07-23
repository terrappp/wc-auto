package screens;

import org.openqa.selenium.WebElement;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DrugAllergy extends ScreenBase{
	
	public DrugAllergy(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/G6PDOptionLayout")
	public WebElement g6pd;
	@AndroidFindBy(id="com.project.WhiteCoat:id/closeLayout")
	public WebElement close;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblFindDoctor")
	public WebElement seeDoctor;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblSelectDoctor")
	public WebElement chooseDoctor;
	
	public Cancel doCancel() {
		close.click();
		return new Cancel(driver);
	}
	
	public DrugAllergy checkG6PD() {
		g6pd.click();
		return this;
	}
	
	public WifiConnection tapOnSeeDoctor() {
		seeDoctor.click();
		return new WifiConnection(driver);
	}
	
	public WifiConnection tapOnChooseDoctor() {
		chooseDoctor.click();
		return new WifiConnection(driver);
	}
}
