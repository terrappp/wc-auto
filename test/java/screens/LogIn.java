package screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LogIn extends ScreenBase{
	
	public LogIn(AndroidDriver driver){
		super(driver);
	}
	
	@AndroidFindBy(id="com.project.WhiteCoat:id/txtEmail")
	public WebElement emailInput;
	@AndroidFindBy(id="com.project.WhiteCoat:id/txtPassword")
	public WebElement passwordInput;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblForgetPassword")
	public WebElement forgetPassword;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblLogIn")
	public WebElement logIn;
	@AndroidFindBy(id="com.project.WhiteCoat:id/lblSignUp")
	public WebElement signUp;
	
	public ScreenBase doLogin(String email, String password, String expected1, String expected2) {
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		
		//collapse the keyboard
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		logIn.click();
		
		try {
			//in case of login successfully
			System.out.println("LOGIN SUCCESSFULLY!!!");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			
			//check for Myself and MyChild buttons
			WebElement myself = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.project.WhiteCoat:id/lblMySelf")));
			Assert.assertEquals(expected1, myself.getText());
			
			WebElement mychild = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.project.WhiteCoat:id/lblChild")));
			Assert.assertEquals(expected2, mychild.getText());

			return new HomeScreen(driver);
		} catch (Throwable t) {
			System.out.println("LOGIN FAILED!!!");
			//in case of login failed
			
			//check for warning message from email
			Assert.assertEquals(expected1, emailInput.getText());
			
			//check for warning message from password
			Assert.assertEquals(expected2, passwordInput.getText());

			return this;
		}
	}
	
	public ForgotPassword clickOnForgotPassword() {
		forgetPassword.click();
		return new ForgotPassword(driver);
	}
	
	public SignUp clickOnSignUp() {
		signUp.click();
		return new SignUp(driver);
	}
}
