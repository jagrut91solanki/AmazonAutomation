package com.amazon.util;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {
			System.out.println("Path of user DIR" + System.getProperty("user.dir"));
			extent = new ExtentReports(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\AmazonTest\\ExecutionReport.html",
					true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(
					new File(System.getProperty("user.dir") + "\\src\\main\\java\\extentconfig\\ReportsConfig.xml"));
		}
		return extent;
	}
}
