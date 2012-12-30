package com.df;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class TestDDF {
  
	public WebDriver driver = null;
	int numOfRows,numOfCols;
	String [][] dataList;
	String us,world,tech,subTech;
	
  @BeforeMethod
  public void beforeMethod() throws IOException {
	  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  String baseUrl = "http://www.cnn.com";
	  driver.navigate().to(baseUrl);
	  driver.manage().window().maximize();
	  String fileName = "/Users/mrahman53/Documents/workspace/Selenium7/file.xls";
      excelReader(fileName);
  
  }
  
  //Read excel file
  public void excelReader(String file) throws IOException{
	  
	  File myFile = new File(file);
	  FileInputStream myStream = new FileInputStream(myFile);
	  HSSFWorkbook myWB = new HSSFWorkbook(myStream);
	  HSSFSheet sheet = myWB.getSheetAt(0);
	  numOfRows = sheet.getLastRowNum();
	  numOfCols = sheet.getRow(0).getLastCellNum();
	  dataList = new String[numOfRows][numOfCols];
	  
	  for(int i=0; i<numOfRows; i++){
		  
	       HSSFRow row = sheet.getRow(i);
		   
		  for(int j=0; j<numOfCols; j++){
			  
		     HSSFCell cell = row.getCell(j);
		     String data = convertCellToString(cell);
		     dataList [i][j]= data;
		     //System.out.println(dataList [i][j]);
	    }
	  
	  }
  }
  
  public String convertCellToString(HSSFCell cell){
	 int type = cell.getCellType();
	 
	 Object data = null;
	 
	 switch(type){
	 
	 case HSSFCell.CELL_TYPE_NUMERIC:
		 data = cell.getNumericCellValue();
		 break;

	 case HSSFCell.CELL_TYPE_BOOLEAN:
		 data = cell.getBooleanCellValue();
		 break;
		 
	 case HSSFCell.CELL_TYPE_STRING:
		 data = cell.getStringCellValue();
		 break;
	 
	 case HSSFCell.CELL_TYPE_FORMULA:
         System.out.println(cell.getCellFormula());
         break;	 
		 
    default :
    	    System.out.println("This is not supported");
	 
	 }
	  
	  return data.toString();
	  
  }

  @Test
  public void f() throws InterruptedException {
	  
	 us = dataList[2][1];
	 clickCss(us);
	 world = dataList[3][1];
	 clickCss(world);
	 tech = dataList[4][1];
	 clickCss(tech);
	 sleep(2);
	 subTech = dataList[5][1];
	 driver.findElement(By.xpath(subTech)).click();
	 
 
  }
  public void clickCss(String css){
	  try{
	  driver.findElement(By.cssSelector(css)).click();
	  }catch (StaleElementReferenceException ex){
		  driver.navigate().refresh();
		  sleep(2);
		  driver.findElement(By.cssSelector(css)).click();
	  }catch (Exception ex){
		  driver.navigate().refresh();
		  sleep(3);
		  driver.findElement(By.cssSelector(css)).click();
	  }
  }
  
  public void sleep(int time){
	  //1000 ms =  1 sec.
	  try {
          Thread.sleep(time * 1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
  
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
	  
  }

}
