package com.amazon.util;

import java.util.Properties;

public class Variables {

	public static Properties Config;
	public static Properties OR;
	public static String server = "smtp.gmail.com";
	public static String from = "frankkristopoulos11@gmail.com";
	public static String password = "w@rmashinroXX123";
	public static String[] to = { "jagrutsolanki1@gmail.com" };
	public static String subject = "Amazon Automation Test Report";
	public static String messageBody = "Testcase Execution Failed";
	public static String attachmentPath = System.getProperty("user.dir") + "//Reports.zip";
	public static String attachmentName = "reports.zip";
	public static String lastName, email, phone, description,account;
}
