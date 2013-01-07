package com.qa.rf;

import java.io.*;

	public class ReadTextFileExample {
		public static void main(String[] args) {
			File file = new File("test.txt");
			StringBuilder contents = new StringBuilder();
			BufferedReader reader = null;
			
			 try {
				 reader = new BufferedReader(new FileReader(file)); 
				 String text = null; 
				 // repeat until all lines is read
				 while ((text = reader.readLine()) != null) {
					 
					 contents.append(text).append(System.getProperty("line.separator"));
					 
				 }        
				 } catch (IOException e) { 
					 e.printStackTrace(); 
					 } finally { 
						 try { 
							 if (reader != null) {
								 reader.close();
								 } 
							 } catch (IOException e) { 
								 e.printStackTrace();   
								 } 
							 } 
					// show file contents here 
					 System.out.println(contents.toString());
						 }
					 }
				 
	
	
	
	
	
