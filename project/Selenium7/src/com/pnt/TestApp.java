package com.pnt;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.thoughtworks.selenium.Selenium;

public class TestApp {
	
  public WebDriver driver = null;	
  public Selenium selenium = null;
  @BeforeMethod
  public void beforeMethod() {
	  
	  driver = new FirefoxDriver();
	  String url = "http://live.huffingtonpost.com";
	  selenium = new WebDriverBackedSelenium(driver, url );
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.navigate().to(url);
	  driver.manage().window().maximize();
	 
	   
	  
  }
  
  
  
  @Test
  public void f() {
	 
	  System.out.println(selenium.getTitle());
	  driver.findElement(By.cssSelector("#nav-us>span")).click();
	  selenium.refresh();
	  driver.findElement(By.cssSelector("#nav-world>span")).click();
	  selenium.refresh();
	 
	  
	 // selenium.mouseOver("xpath=//*[@id='nav-politics']/span");
	  mouseOver("html/body/div[2]/div[2]/div[2]/div/div[1]/div"); 
	  
	  
  }
  
  public void mouseOver(String locators){
	  
	  try{
		  
	  WebElement element = driver.findElement(By.xpath(locators));
	  Actions build = new Actions(driver);
	  Actions hover = build.moveToElement(element);
	  hover.perform();
	  
	  }catch(Exception e){
		  
	  WebElement element = driver.findElement(By.xpath(locators));
	  Locatable hover = (Locatable)element;
	  Mouse mouse = ((HasInputDevices)driver).getMouse();
	  mouse.mouseMove(hover.getCoordinates());  
		  
	  }
  }
  
  @AfterMethod
  public void afterMethod() {
	  //driver.quit();
  }

}
