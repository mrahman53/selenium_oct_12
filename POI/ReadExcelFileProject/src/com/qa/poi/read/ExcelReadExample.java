package com.qa.poi.read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReadExample {
	
	 @SuppressWarnings("unchecked")
	 public static void main(String[] args) throws Exception { 
		 // 
		 // An excel file name. You can create a file name with a full 
		 // path information.  
		 //
		 String filename = "C:\\DevTools\\EclipsWorkspace\\ReadExcelFileProject\\data.xlsx";
		 // 
		 // Create an ArrayList to store the data read from excel sheet.
		 //   
		 List sheetData = new ArrayList();   
		 FileInputStream fis = null;
		 try {
			 // Create a FileInputStream that will be use to read the   
			 // excel file.  
			 //   
			 fis = new FileInputStream(filename);
			 //  
			 // Create an excel workbook from the file system.  
			 //           
			  XSSFWorkbook wb = new XSSFWorkbook(fis);
			 //       
			 // Get the first sheet on the workbook.      
			 //      
			 XSSFSheet sheet = wb.getSheetAt(0);
			 //
			 // When we have a sheet object in hand we can iterator on
			 // each sheet's rows and on each row's cells. We store the  
			 // data read on an ArrayList so that we can printed the        
			 // content of the excel to the console.          
			 //
			 Iterator rows = sheet.rowIterator();  
			 while (rows.hasNext()) {    
				 XSSFRow row = (XSSFRow) rows.next();
				 Iterator cells = row.cellIterator();   
				 List data = new ArrayList();      
				 while (cells.hasNext()) {    
					 XSSFCell cell = (XSSFCell) cells.next();
					 data.add(cell); 
					 }   
				 sheetData.add(data); 
				 }
		 } catch (IOException e)
		 {      
			 e.printStackTrace(); 
			 } finally {     
				 if (fis != null) {  
					 fis.close();   
					 }      
				 }     
			 showExcelData(sheetData); 
			 }
	 		private static void showExcelData(List sheetData) {  
	 			//      
	 			// Iterates the data and print it out to the console.
	 			//      
	 			for (int i = 0; i < sheetData.size(); i++) { 
	 				List list = (List) sheetData.get(i); 
	 				for (int j = 0; j < list.size(); j++) {
	 					XSSFCell cell = (XSSFCell) list.get(j); 
	 					switch(cell.getCellType()){
	 					case Cell.CELL_TYPE_STRING:
	 					
	 					System.out.print(       
	 							cell.getRichStringCellValue().getString()); 
	 					break;
	 				
	 					case Cell.CELL_TYPE_NUMERIC:
	 						System.out.print(cell.getNumericCellValue());
	 						break;
	 					}
	 					if (j < list.size() - 1) {   
	 						System.out.print(",  ");   
	 						}    
	 					}   
	 				System.out.println(" "); 
	 				}  
	 			}
	 		}
