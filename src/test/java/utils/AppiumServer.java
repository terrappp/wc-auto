package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AppiumServer {
	
	//Start Appium programmatically
	public static AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File("/home/linuxbrew/.linuxbrew/bin/node"))
			.withIPAddress("127.0.0.1")
			.withAppiumJS(new File("/home/linuxbrew/.linuxbrew/lib/node_modules/appium/build/lib/main.js"))
			.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
			.withLogFile(new File(System.getProperty("user.dir")+"/src/test/resources/logs/Appium.log")));

	private static Process process;
	
	//Starting the Appium Server
	
	public static void start() throws IOException {
		
		service.start();
		System.out.println("Appium server started!");
	}
	
	//Stopping the Appium Server
	public static void stop() throws IOException {

		service.stop();
		System.out.println("Appium server stopped");
	}


}
