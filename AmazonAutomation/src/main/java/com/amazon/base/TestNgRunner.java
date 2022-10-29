package com.amazon.base;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class TestNgRunner {

	public static void main(String[] args) {
		
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(System.getProperty("user.dir") + "\\testng.xml");
		testng.setTestSuites(suites);
		testng.run();
	}

}
