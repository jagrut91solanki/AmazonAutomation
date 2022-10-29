package com.amazon.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.amazon.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CommonUtils extends TestBase {

	public long PAGE_LOAD_TIMEOUT = 50;
	public long IMPLICIT_WAIT = 50;
	private static int timeout = 30;
	public static WebElement data;
	public String screenshotPath;
	public String screenshotName;
	public static WebDriverWait wait;
	public String text;
	public static HashMap<Integer, List<String>> Global_totalWindow = new HashMap<Integer, List<String>>();
	public static int Global_countForWindow = 0;

	public void captureScreenshot() throws IOException {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
			FileUtils.copyFile(scrFile, new File(
					System.getProperty("user.dir") + "\\target\\surefire-reports\\AmazonTest\\" + screenshotName));
		} catch (Exception e) {
			System.err.println("Screenshot capture Failed");
		}
	}

	public void switchTab(String tabNumber) {

		try {
			Thread.sleep(800);
			ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
			int tab = Integer.parseInt(tabNumber);
			driver.switchTo().window(tabs2.get(tab));
			test.log(LogStatus.INFO, "Switch tab to :: " + tab);
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Can not Switch Tab :: " + e.getMessage());
			throw new AssertionError("Can not Switch Tab", e);
		}
	}

	public static void enviroment(String env) {

		try {
			System.out.println("Enters into Enviroment : " + env);
			UserData uData = new UserData();
			System.out.println("Enviroment is " + env);
			switch (env) {
			case "STAGE":
				driver.get(uData.getStageUrl());
				log.info("Navigated to " + uData.getStageUrl());
				break;
			case "PROD":
				driver.get(uData.getProdUrl());
				waitForPageLoad();
				log.info("Navigated to " + uData.getProdUrl());
				break;
			default:
				driver.get(uData.getProdUrl());
				log.info("Navigated to " + uData.getProdUrl());
				break;
			}
		} catch (Exception e) {
			System.out.println("FAILURE: URL did not load: " + env);
			test.log(LogStatus.ERROR, "URL did not load because " + e.getMessage());
			throw new AssertionError("URL did not load  ", e);
		}
	}

	public void click(By xpath) {

		try {
			driver.findElement(xpath).click();
			System.out.println(xpath + "Button clicked");
			test.log(LogStatus.INFO, xpath + " clicked");
			Thread.sleep(800);
		} catch (Exception e) {
			System.err.println("Cannot Click " + e.getMessage());
			test.log(LogStatus.ERROR, "Unable to click on :: " + xpath + " Button");
			throw new AssertionError("Unable to click on ::  " + xpath + " Button", e);
		}
	}

	public static void zip(String filepath) {
		try {
			File inFolder = new File(filepath);
			File outFolder = new File("Reports.zip");
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
			BufferedInputStream in = null;
			byte[] data = new byte[1000];
			String files[] = inFolder.list();
			for (int i = 0; i < files.length; i++) {
				in = new BufferedInputStream(new FileInputStream(inFolder.getPath() + "/" + files[i]), 1000);
				out.putNextEntry(new ZipEntry(files[i]));
				int count;
				while ((count = in.read(data, 0, 1000)) != -1) {
					out.write(data, 0, count);
				}
				out.closeEntry();
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void delete(final File folder) throws FileNotFoundException {
		System.out.println("Enteres into Delete Function");
		try {
			if (folder.isDirectory()) { // Check if folder file is a real folder
				File[] list = folder.listFiles(); // Storing all file name within array
				System.out.println("list of Files" + list.length);
				System.out.println("list of Files not null");// Checking list value is null or not to check folder
																// containts atlest one file
				for (int i = 0; i < list.length; i++) {
					File tmpF = list[i];
					System.out.println("list[i]" + list[i]);
					if (tmpF.getName().endsWith(".jpg")) {
						if (tmpF.isDirectory()) { // if folder found within folder remove that folder using recursive
													// method
							delete(tmpF);
						}
						tmpF.delete();
					} // else delete file
				}
				if (!folder.delete()) { // if not able to delete folder print message
					System.out.println("can't delete folder : " + folder);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

	}

	public String getallText(By xpath) {

		String text;

		try {
			System.out.println("Enters into verify Text");
			text = driver.findElement(xpath).getText();
			System.out.println("all Description text " + text);

		} catch (Exception e) {

			System.err.println("Data " + e.getMessage());
			test.log(LogStatus.ERROR, "Text is not verified and displayed :: " + data);
			throw new AssertionError(data + " is not displayed");
		}
		return text;
	}

	public void scrollTillElement(By xpath) {
		try {
			System.out.println("eneter into scroll down");
			WebElement element = driver.findElement(xpath);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			Thread.sleep(500);
			test.log(LogStatus.INFO, "Scrolled and element found");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Scrolled and element not found");
			System.out.println("Fail " + e.getMessage());
			throw new AssertionError("Scrolling not working");
		}
	}

	public void verifyEquals(String expected, String actual) throws IOException {
		try {
			Thread.sleep(800);
			Assert.assertEquals(expected, actual);
			test.log(LogStatus.INFO,
					"Verification successfull Actual Page Title is ::  " + actual + " and Expected is :: " + expected);
		} catch (Throwable t) {
			test.log(LogStatus.ERROR, " Verification failed with exception :: " + t.getMessage());
			System.out.println("Fail " + t.getMessage());
			throw new AssertionError("Verification failed");
		}
	}

	public void waitForElementToBeVisible(By selector) {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.presenceOfElementLocated(selector));
			test.log(LogStatus.INFO, "Element is Visible :: " + selector);

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Element is not Visible :: " + selector);
			throw new NoSuchElementException(String.format("Element was not visible:: ", selector));
		}
	}

	public static void makeDirectory() {

		System.out.println("Enter into Make diretory");
		String directories = System.getProperty("user.dir") + "\\target\\surefire-reports\\AmazonTest";
		File file = new File(directories);
		boolean result = file.mkdirs();
		System.out.println("Status = " + result);
	}

	public void verifyCheckboxvalue(String element) {
		WebElement webElement = driver.findElement(By.xpath(Variables.OR.getProperty(element)));
		if (webElement.isSelected()) {
			System.out.println(element + " Checkbox is Selected TRUE");
			test.log(LogStatus.INFO, "Checkbox is Selected TRUE " + element);
		} else {
			System.out.println(element + " Checkbox is Selected FALSE");
			test.log(LogStatus.ERROR, "Checkbox is Selected FALSE " + element);
			throw new AssertionError("Checkbox is Selected FALSE " + element);
		}
	}

	public static void waitForPageLoad() {

		System.out.println("Enters into Page load");
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public void SwitchToAnotherWindow(WebDriver driver, int window_number) {
		System.out.println("switch to window");
		List<String> windowlist = null;

		Set<String> windows = driver.getWindowHandles();
		System.out.println("widnows total  " + windows.size());
		windowlist = new ArrayList<String>(windows);

		String currentWindow = driver.getWindowHandle();

		if (!currentWindow.equalsIgnoreCase(windowlist.get(window_number - 1))) {
			driver.switchTo().window(windowlist.get(window_number - 1));
		}

	}

}
