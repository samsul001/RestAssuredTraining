//Day-1
/*
 * RestASssured
 * ------------
 * RestAssured is an API/Library through which we can automate rest APIs
 * 
 * Pre-Requisite
 * -------------
 * 1. Eclipse & Java 9+
 * 2. TestNG
 * 3. Maven
 * 
 * Static imports of Required RestAssured Packages:
 * ------------------------------------------------
 * import static io.restassured.RestAssured.*;
   import static io.restassured.matcher.RestAssuredMatchers.*;
   import static org.hamcrest.Matchers.*;
   
   Required Dependencies for RestAssured Automation
   -------------------------------------------------
   1. rest-assured
   2. json-path
   3. json
   4. gson
   5. TestNG
   6. scribejaa-apis
   7. json-schema validator
   8. xml-schema validator
   
   HTTP Requests
   -------------
   1. GET, 2.POST, 3.PUT, 4.DELETE
   
   Gherkin Language - Keywords
   ---------------------------
   1. Whenever we create RestAssured Test cases which will follow BDD stylr/approact by default.
   2. Gherkin langugae contains number of keywords which will be used in BDD style/approach.
   3. RestAssured support BDD style of writing test cases by default using Gherkin keywords.
   4. Those keyWords or Methods --> given(), when(), then()
   
   given() , when(), then() 
   ------------------------
   - The above three methods are considered individual sections in API automation scripts.
   
   1. given() --> Pre-requisites such as Payload, content-type, path parameter, query parameter, Cookies & authentication, headers etc. will be specified
   2. when() --> Request types such as "GET", "POST", "PUT" & "DELETE" will be specified.
   3. then() --> Validation/Assertion commands will be specifed in then() section.
   
   
 */
package day1;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {
	/*
	 * Get Users
	 * ---------
	 * https://reqres.in/api/users?page=2
	 * 
	 * Post Users
	 * ----------
	 * https://reqres.in/api/users
	 * {
	 * 		"name":"samsul",
	 * 		"job" : "Test Lead"
	 * }
	 * 
	 * Update Users
	 * ------------
	 * https://reqres.in/api/users/2
	 * {
	 * 		"name":"samsul",
	 * 		"job" : "Test Lead"
	 * }
	 * 
	 * Delete Users
	 * ------------
	 * https://reqres.in/api/users/{userid}
	 * 204
	 */
	int id;
	@Test(priority = 1)
	public void listUser() {
		
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	public void createUsr() {
		HashMap<String , String> data = new HashMap<String, String>();
		data.put("name", "Sam");
		data.put("job","Team Lead");
		
		id = given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();
			
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUsr"})
	public void updateUsr() {
		HashMap<String , String> data = new HashMap<String, String>();
		data.put("name", "Sam");
		data.put("job","Team Lead");
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.put("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(200)
			.log().all();	
	}
	
	@Test(priority = 4)
	public void deleteUsr() {
		given()
		.when()
			.delete("https://reqres.in/api/users/"+id)
		.then()
			.statusCode(204)
			.log().all();
	}

}
