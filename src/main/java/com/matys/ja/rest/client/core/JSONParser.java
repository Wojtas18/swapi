package com.matys.ja.rest.client.core;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public String getNameFromJSON(JSONObject jsonObject) {
		String name = "name";
		String toReturn = "";
		try {
			toReturn = jsonObject.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}

}
