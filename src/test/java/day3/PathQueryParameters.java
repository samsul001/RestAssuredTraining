package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PathQueryParameters {
	
	//path parameter and Query Parameter
	// While Query Parameters describe how to look , Patha Parameters show where to look.
	// path parameters are part of endpoind of URI where as 
	// Query parameter is key-value pairs that will be used to apply filter for attributes present in the 
	
	//https://reqres.in/api/users?page=2&id=5
	// https://reqres.in --> this is called domain , this will first reach to server
	// /api/users --> Path Parameters
	// page=2&id=5 --> Query Parameters
	
	@Test
	public void testQueryPathParam() {
		
		given()
			.pathParam("myPath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
			.get("https://reqres.in/api/{myPath}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
