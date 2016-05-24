package com.matys.ja.rest.client.core;

import java.net.URI;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SwapiClient {

	Client client;

	public SwapiClient() {
		client = Client.create();
	}

	public String getStringResponse(URI uri) {
		if(uri == null) {
			throw new NullPointerException("Uri is: " + uri);
		}
		WebResource webResource = client.resource(uri);
		ClientResponse clientResponse = sendRequestAndGetClientResponse(webResource);
		checkStatus(clientResponse);
		return clientResponse.getEntity(String.class);
	}

	void checkStatus(ClientResponse clientResponse) {
		if(clientResponse.getStatus() != 200) {
			throw new RuntimeException("Wrong status code: " + clientResponse.getStatus());
		}
	}

	private ClientResponse sendRequestAndGetClientResponse(WebResource webResource) {
		return webResource.accept("application/json").header("USER-AGENT", "Mozilla/4.78")
				.get(ClientResponse.class);
	}
	
}
