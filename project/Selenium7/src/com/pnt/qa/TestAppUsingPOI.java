package com.pnt.qa;

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

public class TestAppUsingPOI {
  
	public WebDriver driver = null;
	int numOfRows, numOfColumns;
	String [][] dataHolder;
	String url, us,world,politics,tech,justice;
	
	
  @BeforeMethod
  public void beforeMethod() throws IOException {
	  String fileName = "/Users/mrahman53/Documents/workspace/Selenium7/file.xls";
	  fileReader(fileName);
	  url = dataHolder[2][1];
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.navigate().to(url);
	  driver.manage().window().maximize();
	  
  }
  
  
  
  
  
  @Test
  public void f() {
	  us = dataHolder[3][1];
	  clickCss(us);
	  sleep(2);
	  world = dataHolder[4][1];
	  clickCss(world);
	  sleep(2);
	  politics = dataHolder[5][1];
	  clickCss(politics);
	  sleep(2);
	  justice = dataHolder[6][1]; 
	  clickCss(justice);
	  sleep(2);
	  tech = dataHolder[7][1];
	  clickCss(tech);
	  sleep(2);
	  
  }
  
  
 
  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
  
  public void fileReader(String file) throws IOException{
	  
	  //Core java to find the file location and prepared to read the file
	  // and FileInputStream prepared for POI libraries(HSSFWorkbook....Cell)
	  File dataFile = new File(file);
	  FileInputStream fis = new FileInputStream(dataFile);
	  //POI's job to read the Excel file
	  HSSFWorkbook wb = new HSSFWorkbook(fis);
	  HSSFSheet sheet = wb.getSheetAt(0);
	  //determine number of rows and columns
	  numOfRows = sheet.getLastRowNum();
	  numOfColumns = sheet.getRow(0).getLastCellNum();
	  dataHolder = new String [numOfRows][numOfColumns];
	  //iterate through all the rows
	  for(int i=0; i<numOfRows; i++){
		  
		HSSFRow row = sheet.getRow(i);
		//iterate through all the cell
		 for(int j=0; j<numOfColumns; j++){
			 
	       HSSFCell cell = row.getCell(j);
	       //Extracting data and store to an array
	       String data = convertCellToString(cell);
	       dataHolder [i][j] = data;
	  
		 }
	  }

  }
  //Helper method to extract data
  public String convertCellToString(HSSFCell cell){
	  Object data = null;
	  int dataType = cell.getCellType();
	  
	  switch(dataType){
	  
	  case HSSFCell.CELL_TYPE_NUMERIC:
	      data = cell.getNumericCellValue();
	      break;
	  
	  case HSSFCell.CELL_TYPE_STRING:
		  data = cell.getStringCellValue();
		  break;
		  
	  case HSSFCell.CELL_TYPE_BOOLEAN:
		  data = cell.getBooleanCellValue();
		  break;	 
		  
      default:
    	     System.out.println("This format is not supported..change your format and come back..if you are crazy one");
	  
	  }
	  
	  return data.toString();
  }
  
  public void sleep(int time){
	  //1000 ms =  1 sec.
	  try {
          Thread.sleep(time * 1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
  
  //click css
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

}
