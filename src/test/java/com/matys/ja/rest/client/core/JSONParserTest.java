package com.matys.ja.rest.client.core;

import static org.assertj.core.api.Assertions.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class JSONParserTest {

	@Test
	public void testGetNameFromJSON() throws JSONException {
		//given
		JSONObject jsonObject = new JSONObject(); 
		jsonObject.put("name", "firstName lastName");
		JSONParser  jsonParser = new JSONParser();
		String name = "firstName lastName";
		//when
		String result = jsonParser.getNameFromJSON(jsonObject);
		//then
		assertThat(result).isEqualTo(name);
	}
	
	
}
