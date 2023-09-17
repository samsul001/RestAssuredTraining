package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
//https://reqres.in/api/users?page=2
public class parsingJSONResponseData {

	//@Test
	void testJsonResponse1() {
		//Approach 1
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("book[3].title", equalTo("TamilPoems"));
	}
	
	//@Test
	void testJsonResponse2() {
		//Approach 2. 
		// jsonPath().get() => this methods returns a particular field from a Json response in Object data type.
		Response res =
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
			
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
			Assert.assertEquals(res.jsonPath().get("book[3].title").toString(), "TamilPoems");
	}
	
	//@Test
	void testJsonResponseBodyData() {
		Response res=
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		JSONObject jo = new JSONObject(res.asString());
		boolean status = false;
		for(int i=0; i<jo.getJSONArray("book").length(); i++) {
			String titleObj = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			if(titleObj.equals("Oru kal oru kannadi")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
	
	@Test
	void totalSumOfAllBooks() {
		Response res=
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		double totalPrice =0;
		JSONObject jo = new JSONObject(res.asString());
		
		for(int i=0; i<jo.getJSONArray("book").length();i++) {
			String price = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
			totalPrice = totalPrice+Double.parseDouble(price);
		}
		Assert.assertEquals(totalPrice, 7292.3);
	}
	
	
	
	
	
	
	
	
}
