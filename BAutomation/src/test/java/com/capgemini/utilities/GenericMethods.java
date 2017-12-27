package com.capgemini.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GenericMethods {

	public void click(WebDriver driver, String propertyValue, String property)
	{
		if(property == "id")
		driver.findElement(By.id(propertyValue)).click();
		else if(property == "name")
		driver.findElement(By.name(propertyValue)).click();
		else
		driver.findElement(By.xpath(propertyValue)).click();	
	}
	
	public void sendKeys(WebDriver driver, String propertyValue, String property, String data)
	{
		if(property == "id")
		driver.findElement(By.id(propertyValue)).sendKeys(data);
		else if(property == "name")
		driver.findElement(By.name(propertyValue)).sendKeys(data);
		else
		driver.findElement(By.xpath(propertyValue)).sendKeys(data);
	}
	
	
}
