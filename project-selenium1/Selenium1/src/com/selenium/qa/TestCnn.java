package com.selenium.qa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class TestCnn {

	public Selenium browser = null;
	
	@Before   //set up the test
	public void setUp() throws Exception {
		
		browser = new DefaultSelenium("localhost", 4444, "*safari", "http://www.cars.com");
		browser.start();
		browser.windowMaximize();
		
		
	}
	
	@Test   //all the test steps
	public void test() {
		browser.open("/");
		
		
	}


	@After   //clean up test
	public void tearDown() throws Exception {
		browser.stop();
		//browser.close();
		
	}

	
}
