package day3;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	//Headers
	// The response received from the server consists of zero or more headers along with response status and response body.
	// Each header is key-value pair
	// The header part of the response is used by the server to send extra information which is also referred to as 
	// "Metadata" of the response.
	// the following are the important Header Types from the Response:
			//Content-Type.
			//Server (Which server we are getting this response)
			//Content-Encoding.
	// Header values are always same.

	@Test(priority = 1)
	void testHeaders1() {

		given()
		.when()
			.get("https://www.google.com")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");
	}
	
	@Test(priority = 2)
	void testHeaders2() {
		Response res =
		given()
		.when()
			.get("https://www.google.com");
		
		//Get Single header info
		String contentType = res.header("Content-Type");
		System.out.println("Value of contentType header is: "+contentType);
		
		//Get all headers info
		Headers headers = res.headers();
		for(Header hd: headers) {
			System.out.println(hd.getName()+"               "+hd.getValue());
		}
	}
}
