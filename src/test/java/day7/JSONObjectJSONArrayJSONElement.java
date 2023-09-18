package day7;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONArray;

//JSON Object
// It always start with curly brace "{" and ends with "}". Element present inside the curly braces are JSON Objects.

//JSON Array
// It always start with square brace "[" and ends with "]". Element present inside the square braces are JSON Array.

//JSON Element
// It can be JSON Object or JSON Array

//http://localhost:3000/students --> provide response which is starting from array

public class JSONObjectJSONArrayJSONElement {
	
	@Test
	void testParseJSONArray() {
		Response res =
		given()
		.when()
			.get("http://localhost:3000/students");
		
		JSONArray jrr = new JSONArray(res.asString());

		for(int i=0; i<jrr.length();i++) {
			System.out.println(jrr.getJSONObject(i).get("location"));
		}
	}

}
