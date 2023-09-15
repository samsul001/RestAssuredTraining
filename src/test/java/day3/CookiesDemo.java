package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {
	
	//Cookies & Headers
	// Sometimes Cookies & Headers will be received as part of the response
	// Cookies values are always not same but Cookie name will be sames
	
	@Test(priority = 1)
	void testCookies1() {

		given()
		.when()
			.get("https://www.google.com")
		.then()
			.cookie("AEC","Ad49MVHEcxCKUhf1Jm8EBP4BY1O3mQcPMJkJ2FXOLuQ-uFCist2PX2I9")
			.log().all();
	}
	
	@Test(priority = 2)
	void testCookies2() {
		Response res =
		given()
		.when()
			.get("https://www.google.com");
		
		String cookie_aec = res.getCookie("AEC");
		System.out.println(cookie_aec);
		
		Map<String, String>cookies = res.getCookies();
		Set<String> set = cookies.keySet();
		for(String cookie: set) {
			System.out.println(cookie+"  "+res.getCookie(cookie));
		}
	}

}
