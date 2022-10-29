package com.amazon.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadJson {

	public void getJsonData() throws Exception {

		FileInputStream inputStream = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java" + "/objectRepository/Config.json");
		JSONParser parser = new JSONParser();
		JSONObject jsonobj = (JSONObject) parser.parse(new InputStreamReader(inputStream));
		UserData uData = new UserData();
		uData.setBrowser((String) jsonobj.get("browser"));
		uData.setEnviroment((String) jsonobj.get("enviroment"));
		uData.setStageUrl((String) jsonobj.get("STAGE"));
		uData.setProdUrl((String) jsonobj.get("PROD"));
		
	}
}
