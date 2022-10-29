package com.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.amazon.base.TestBase;
import com.amazon.util.Variables;
import com.relevantcodes.extentreports.LogStatus;

public class AmazonPage extends TestBase {

	// By Locators
	By HAMBERBURMENU_SELECTOR = By.xpath(Variables.OR.getProperty("hamburgerMenu"));
	By TVPPLINK_SELECTOR = By.xpath(Variables.OR.getProperty("tvAppliElecLink"));
	By TV_SELECTOR = By.xpath(Variables.OR.getProperty("televisionSelector"));
	By BRANDCHECKBOX_SELECTOR = By.xpath(Variables.OR.getProperty("brandcheckboxSelect"));
	By SORTBY_BUTTONSELECTOR = By.xpath(Variables.OR.getProperty("sortByButton"));
	By SORTBYHIGHTOLOW_SELECTOR = By.xpath(Variables.OR.getProperty("sortbyHtoL"));
	By SECONDHIGHEST_PRICE = By.xpath(Variables.OR.getProperty("secondHighPriceSelector"));
	By ABOUT_ITEM = By.xpath(Variables.OR.getProperty("aboutItemSelect"));
	By ABOUTITEMCONTENT_SELECTOR = By.xpath(Variables.OR.getProperty("aboutItemContent"));

	// Page Actions
	public AmazonPage() {

		PageFactory.initElements(driver, this);
	}

	public void clickhamburgerMenu() {

		try {
			commonUtil.waitForElementToBeVisible(HAMBERBURMENU_SELECTOR);
			commonUtil.click(HAMBERBURMENU_SELECTOR);
			test.log(LogStatus.INFO, "Hamburger menu clicked");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Hamburger menu Click Failed");
			throw new AssertionError("Hamburger menu Click Failed", e);
		}
	}

	public void clickTvAppAndElectronicsLink() {

		try {
			commonUtil.scrollTillElement(TVPPLINK_SELECTOR);
			commonUtil.waitForElementToBeVisible(TVPPLINK_SELECTOR);
			commonUtil.click(TVPPLINK_SELECTOR);
			test.log(LogStatus.INFO, "TV, Appliances and Electronics Menu clicked");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "TV, Appliances and Electronics Menu click Fail");
			throw new AssertionError("TV, Appliances and Electronics Menu click Fail", e);
		}
	}

	public void clickTelevisionSection() {

		try {
			commonUtil.waitForElementToBeVisible(TV_SELECTOR);
			commonUtil.click(TV_SELECTOR);
			test.log(LogStatus.INFO, "Televisions under Tv, Audio & Cameras sub section clicked");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Televisions under Tv, Audio & Cameras sub section click Fail");
			throw new AssertionError("Televisions under Tv, Audio & Cameras sub section click Fail", e);
		}
	}

	public void filterByBrand() {

		try {
			commonUtil.scrollTillElement(BRANDCHECKBOX_SELECTOR);
			commonUtil.waitForElementToBeVisible(BRANDCHECKBOX_SELECTOR);
			commonUtil.click(BRANDCHECKBOX_SELECTOR);
			test.log(LogStatus.INFO, "Scroll down and filter the results by Brand ‘Samsung");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Scroll down and filter the results by Brand ‘Samsung Failed");
			throw new AssertionError("Scroll down and filter the results by Brand ‘Samsung Failed", e);
		}
	}

	public void sortByButtonSelector() {

		try {
			Thread.sleep(3000);
			commonUtil.waitForElementToBeVisible(SORTBY_BUTTONSELECTOR);
			commonUtil.click(SORTBY_BUTTONSELECTOR);
			test.log(LogStatus.INFO, "Click to Open Dropdown Pass");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Click to Open Dropdown Fail");
			throw new AssertionError("Click to Open Dropdown Fail", e);
		}
	}

	public void sortByHighToLowSelector() {

		try {
			commonUtil.waitForElementToBeVisible(SORTBYHIGHTOLOW_SELECTOR);
			commonUtil.click(SORTBYHIGHTOLOW_SELECTOR);
			test.log(LogStatus.INFO, "Sort the Samsung results with price High to Low Pass");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Sort the Samsung results with price High to Low Fail");
			throw new AssertionError("Sort the Samsung results with price High to Low Fail", e);
		}
	}

	public void secondHighestPricedItemSelector() {

		try {
			commonUtil.waitForElementToBeVisible(SECONDHIGHEST_PRICE);
			commonUtil.click(SECONDHIGHEST_PRICE);
			test.log(LogStatus.INFO, "Second highest priced item clicked");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Second highest priced item click Fail");
			throw new AssertionError("Second highest priced item click Fail", e);
		}
	}

	public void switchTheWindow() {

		try {
			commonUtil.SwitchToAnotherWindow(driver, 2);
			test.log(LogStatus.INFO, "Switch to the SecondWindow Pass");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Switch to the SecondWindow Fail");
			throw new AssertionError("Switch to the SecondWindow Fail", e);
		}
	}

	public void checkAboutThisItemSection() {

		try {
			commonUtil.scrollTillElement(ABOUT_ITEM);
			test.log(LogStatus.INFO, "“About this item” section is present Pass");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "“About this item” section is not present Fail");
			throw new AssertionError("“About this item” section is not present Fail", e);
		}
	}

	public void assertAboutthisItemText() {
		String ActualText;
		String ExpectedText;
		try {
			commonUtil.waitForElementToBeVisible(ABOUT_ITEM);
			ActualText = commonUtil.getallText(ABOUT_ITEM);
			ExpectedText = "About this item";
			commonUtil.verifyEquals(ExpectedText, ActualText);
			test.log(LogStatus.INFO, "Assert that “About this item” Text Matched with Expected Text Pass");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Assert that “About this item” Text Matched with Expected Text Fail");
			throw new AssertionError("Assert that “About this item” Text Matched with Expected Text Fail", e);
		}
	}

	public void logAboutItemText() {
		String AboutItemText;
		try {
			commonUtil.waitForElementToBeVisible(ABOUTITEMCONTENT_SELECTOR);
			AboutItemText = commonUtil.getallText(ABOUTITEMCONTENT_SELECTOR);
			System.out.println("AboutItemText" + AboutItemText);
			test.log(LogStatus.INFO, AboutItemText);
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Log for About this scetion Fail");
			throw new AssertionError("Log for About this scetion Fail", e);
		}
	}

}
