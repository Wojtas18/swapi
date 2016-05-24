package com.matys.ja.rest.client.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import com.sun.jersey.api.client.ClientResponse;

public class SwapiClientTest {

	
	@Test
	public void testGetResponseFromServer() throws URISyntaxException {
		//given
		SwapiClient client = new SwapiClient();
		URI someUri = new URI("http://swapi.co/api/?format=json");
		String expectedOutput = "{\"people\":\"http://swapi.co/api/people/\",\"planets\":\"http://swapi.co/api/planets/\"," +  
			    "\"films\":\"http://swapi.co/api/films/\"," + 
			    "\"species\":\"http://swapi.co/api/species/\"," + 
			    "\"vehicles\":\"http://swapi.co/api/vehicles/\"," + 
			    "\"starships\":\"http://swapi.co/api/starships/\"}";
		//when
		String clientResponse = client.getStringResponse(someUri);
		//then
		assertThat(expectedOutput).isEqualTo(clientResponse);
	}
	
	@Test
	public void testGetStatusCodeDifferentThen200() {
		//given
		ClientResponse mockClientResponse = mock(ClientResponse.class);
		SwapiClient swapiClient = new SwapiClient();
		when(mockClientResponse.getStatus()).thenReturn(404);
		//when
		Throwable thrown = catchThrowable(() -> { 
			swapiClient.checkStatus(mockClientResponse); 
			});
		//then
		assertThat(thrown).isInstanceOf(RuntimeException.class);
	}
	
	@Test
	public void testUriIsNull() {
		//given
		SwapiClient swapiClient = new SwapiClient();
		URI uri = null;
		//when
		Throwable thrown = catchThrowable(() -> { 
			swapiClient.getStringResponse(uri);
			});
		//then
		assertThat(thrown).isInstanceOf(NullPointerException.class);
	}
	
}
