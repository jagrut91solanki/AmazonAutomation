package com.amazon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.amazon.pages.AmazonPage;
import com.amazon.util.CommonUtils;
import com.amazon.util.ExtentManager;
import com.amazon.util.ReadJson;
import com.amazon.util.UserData;
import com.amazon.util.Variables;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis, fis1;
	public static FileInputStream jsonFile;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static CommonUtils commonUtil = new CommonUtils();
	public static UserData uData = new UserData();
	public static AmazonPage amazonn = new AmazonPage();

	public TestBase() {

		Variables.OR = new Properties();

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java" + "/objectRepository/OR.properties");
			log.info("Properties file found");
		} catch (FileNotFoundException e) {
			log.info("Properties file not found");
			e.printStackTrace();
		}

		try {
			Variables.OR.load(fis);
			log.info("Properties file loaded !!!");
		} catch (IOException e) {
			log.info("Properties file not loaded");
			e.printStackTrace();
		}

		ReadJson jsonread = new ReadJson();

		try {
			jsonread.getJsonData();
			log.info("JSON file loaded !!!");
		} catch (Exception e) {
			//log.error("JSON file not loaded");
			e.printStackTrace();
		}
	}

	public static void initialization() throws MalformedURLException {

		if (driver == null) {
			UserData uData = new UserData();
			String browserName = uData.getBrowser();

			if (browserName.equals("chrome")) {
				Map<String, Object> prefs = new HashMap<String, Object>();
				WebDriverManager.chromedriver().setup();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--incognito");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("use-fake-ui-for-media-stream");
				driver = new ChromeDriver(options);
				log.info("Chrome Browser launched");

			}
			 else if (browserName.equals("firefox")) {

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("permissions.default.microphone", 1);
				prefs.put("permissions.default.camera", 1);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addPreference("dom.webnotifications.enabled", false);
				options.addPreference("permissions.default.microphone", 1);
				options.addPreference("permissions.default.camera", 1);
				driver = new FirefoxDriver(options);

			}

			else if (browserName.equals("edge")) {

				WebDriverManager.edgedriver().setup();
				EdgeOptions options = new EdgeOptions();
				options.setCapability("dom.webnotifications.enabled", 1);
				options.setCapability("permissions.default.microphone", 1);
				options.setCapability("permissions.default.camera", 1);
				driver = new EdgeDriver(options);
			}

			else if (browserName.equals("grid"))
			{	    
				String hubURL = System.getProperty("hubUrl", "http://localhost:4444/wd/hub");
				Map<String, Object> prefs = new HashMap<String, Object>();
				WebDriverManager.chromedriver().setup();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setPageLoadStrategy(PageLoadStrategy.NONE);
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--incognito");
				options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
				options.setExperimentalOption("useAutomationExtension", false);
				options.addArguments("use-fake-ui-for-media-stream");
				driver = new RemoteWebDriver(new URL(hubURL), options);
				log.info("Chrome Browser launched");
			}

			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(commonUtil.PAGE_LOAD_TIMEOUT));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(commonUtil.IMPLICIT_WAIT));
		}
	}
}
