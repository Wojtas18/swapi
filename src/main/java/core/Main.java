package core;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Main {
	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://swapi.co/api/people/?format=json&page=1");
			ClientResponse response = webResource.accept("application/json").header("USER-AGENT", "Mozilla/4.78")
					.get(ClientResponse.class);
			String output = response.getEntity(String.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			// System.out.println("Output from Server .... \n");
			// System.out.println(output);

			JSONObject obj = new JSONObject(output);

			JSONArray array = obj.getJSONArray("results");

			JSONObject obje = new JSONObject(array.get(0).toString());

			// System.out.println(obje);

			System.out.println(obje.getString("name"));
			JSONArray firstArray = obje.getJSONArray("vehicles");
			for (int i = 0; i < firstArray.length(); i++) {
				webResource = client.resource(firstArray.getString(i));
				response = webResource.accept("application/json").header("USER-AGENT", "Mozilla/4.78")
						.get(ClientResponse.class);
				String output2 = response.getEntity(String.class);
				JSONObject obj2 = new JSONObject(output2);
				System.out.println("Pojazd " + i + ": " + obj2.get("name"));
			}
			// System.out.println(obje.getString("vehicles"));

			JSONArray species = obje.getJSONArray("species");
			for (int i = 0; i < species.length(); i++) {
				webResource = client.resource(species.getString(i));
				response = webResource.accept("application/json").header("USER-AGENT", "Mozilla/4.78")
						.get(ClientResponse.class);
				String output2 = response.getEntity(String.class);
				JSONObject obj2 = new JSONObject(output2);
				System.out.println("Rasa: " + obj2.get("name"));
			}
			// System.out.println(obje.getString("species"));

			webResource = client.resource(obje.getString("homeworld"));
			response = webResource.accept("application/json").header("USER-AGENT", "Mozilla/4.78")
					.get(ClientResponse.class);
			String output2 = response.getEntity(String.class);
			JSONObject obj2 = new JSONObject(output2);
			System.out.println("Planeta: " + obj2.get("name"));

			// System.out.println(obje.getString("homeworld"));
			// System.out.println(obj.getString("results"));
			client.destroy();
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}