package com.capgemini.scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.capgemini.utilities.ReadExcelConfig1;
import com.capgemini.utilities.WriteExcelConfig1;

public class BigFixNew {

	public static void main(String[] args) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
		ReadExcelConfig1 excel = new ReadExcelConfig1("C:\\Automation QA\\Workspace\\BAutomation\\data\\BigFix\\BigFix_data.xlsx");
		WriteExcelConfig1 write = new WriteExcelConfig1("C:\\Automation QA\\Workspace\\BAutomation\\data\\BigFix\\BigFix_data.xlsx");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://g1nwgepba001.gpn.globalpay.com:8443/");
		String userName = excel.getCellData("Login_Crendentials","UserName",2);
		driver.findElement(By.id("username")).sendKeys(userName);
		String password = excel.getCellData("Login_Crendentials","Password",2);
		driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000);
		//Click on Login Button
	    driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/form/div/div/button")).click();
	    driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
	    //Waiting for element to be loaded
	    WebDriverWait wait = new WebDriverWait(driver,50);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/bf-header/div/nav/div[2]/ul/li[1]/a")));
	    //Click on Devices Tab
	    driver.findElement(By.xpath("/html/body/bf-header/div/nav/div[2]/ul/li[1]/a")).click();
	    //Reading server name from excel
	    for(int i=1; i<=excel.rowCount("Servers"); i++)
	    {
	    String server = excel.getCellData("Servers","Server Name",i+1);
	    Thread.sleep(5000);
	    //Enter the server name into the search field
	    driver.findElement(By.xpath("//input[@class='form-control bf-search-box ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty']")).sendKeys(server);
	    Thread.sleep(8000);
	    //Clicking on settings icon
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-wrench']")));
	    driver.findElement(By.xpath("//i[@class='fa fa-wrench']")).click();
	    Thread.sleep(4000);
	    //Screenshot.screenshot(driver, "server"+i);
	    //Enter deployment name
	    String deploymentName = excel.getCellData("Servers","Deployment_Name",2);
	    driver.findElement(By.xpath("(//input[@placeholder='Search'])[4]")).sendKeys(deploymentName);
	    Thread.sleep(5000);
	    //Getting Deployments Count
	    String count = driver.findElement(By.xpath("(//span[@class='ng-binding ng-scope'])[4]")).getText();
	    //System.out.println(count);
	    Thread.sleep(5000);
	    if(!count.equalsIgnoreCase("0 Deployments"))
	    {
	    System.out.println("Deployment is present");
	    //Getting status
	    String status = driver.findElement(By.xpath("//span[@status='deployment.status']")).getText();
	    //printing status in console
	    System.out.println(server + "  status is  " + status);
	   // WriteExcelConfig write = new WriteExcelConfig("C:\\Automation QA\\workspace\\AVT\\data\\BigFix\\BigFix_data.xlsx");
	    write.setCellData("Servers","Status", i+1, status);
	    }
	    else
	    {
	    	System.out.println("Deployment is not present");
	    	write.setCellData("Servers","Status", 2,"Not Available");
	    	
	    }
	    //Click on devices Tab
	    driver.findElement(By.xpath("/html/body/bf-header/div/nav/div[2]/ul/li[1]/a")).click();
	    }
	    driver.quit();
		}
			

	}


