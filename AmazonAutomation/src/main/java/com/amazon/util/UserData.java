package com.amazon.util;

public class UserData {

	private static String browser;
	private static String prodUrl;
	private static String stageUrl;
	private static String enviroment;
	private static String gridURL;

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		UserData.browser = browser;
	}

	public String getProdUrl() {
		return prodUrl;
	}

	public void setProdUrl(String prodUrl) {
		UserData.prodUrl = prodUrl;
	}

	public String getStageUrl() {
		return stageUrl;
	}

	public void setStageUrl(String stageUrl) {
		UserData.stageUrl = stageUrl;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(String enviroment) {
		UserData.enviroment = enviroment;
	}

	public String getGridURL() {
		return gridURL;
	}

	public void setGridURL(String gridURL) {
		UserData.gridURL = gridURL;
	}
	
}
