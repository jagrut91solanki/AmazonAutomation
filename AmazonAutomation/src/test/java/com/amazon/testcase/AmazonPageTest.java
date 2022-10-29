package com.amazon.testcase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.amazon.base.TestBase;
import com.amazon.util.CommonUtils;

import listeners.Retry;

public class AmazonPageTest extends TestBase {
	public AmazonPageTest() {

		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {

		initialization();
		CommonUtils.enviroment(uData.getEnviroment());
	}

	@Test(priority = 1, retryAnalyzer = Retry.class, enabled = true)
	public void AmazonTest() throws Exception {

		amazonn.clickhamburgerMenu();
		amazonn.clickTvAppAndElectronicsLink();
		amazonn.clickTelevisionSection();
		amazonn.filterByBrand();
		amazonn.sortByButtonSelector();
		amazonn.sortByHighToLowSelector();
		amazonn.secondHighestPricedItemSelector();
		amazonn.switchTheWindow();
		amazonn.checkAboutThisItemSection();
		amazonn.assertAboutthisItemText();
		amazonn.logAboutItemText();
	}

	@AfterMethod
	public void tearDown() throws Exception {

		driver.quit();
		driver = null;

	}

}
