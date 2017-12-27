package com.capgemini.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	public static void screenshot(WebDriver driver, String screenshotName  ) {
		
	     try {
			 TakesScreenshot ts = (TakesScreenshot)driver;
			 File src = ts.getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(src,new File( "./Screenshots/"+screenshotName+".png"));
		} catch (Exception e) {
			
			System.out.println("unable to take screenshot"+ e.getMessage());
		} 
	}

}
