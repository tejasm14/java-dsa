/*
package java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import com.google.gson.JsonObject;

public class HttpCall {
	
	private static final HttpClient httpClient = HttpClient.newBuilder()
			.version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10))
			.build();
	
	
	public static void main(String[] args) {
		
		String apiUrl = "https://reqres.in/api/users?page=1";

		HttpRequest httpRequest = HttpRequest
				.newBuilder()
				.uri(URI.create(apiUrl))
				.GET()
				//.setHeader(apiUrl, apiUrl) we can set header here too
				.build();
		
		try {
			HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			System.out.println("Post call");
			HttpCall.postCall();
		
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void postCall() throws InterruptedException, IOException {
		
		String apiUrl = "https://reqres.in/api/users";
		
		JsonObject test = new JsonObject();
		test.addProperty("name", "Ryan");
		test.addProperty("job", "test");
		
		HttpRequest httpRequest = HttpRequest
				.newBuilder()
				.uri(URI.create(apiUrl))
				.header("Content-Type", "application/json")
				.POST(BodyPublishers.ofString(test.toString()))
				.build();
		
		HttpResponse<?> response = httpClient.send(httpRequest, BodyHandlers.discarding());
		System.out.println(response);
		
	
	}
	

}
*/
