package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoggingDemo {
	
	//https://reqres.in/api/users?page=2
	
	//@Test(priority =1)
	public void testLogs1() {
		//To print only body/response
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
		.log().body();
	}
	
	//@Test(priority =2)
	public void testLogs2() {
		//To print only cookies
		given()
		.when()
			.get("https://www.google.com")
		.then()
		.log().cookies();
	}
	
	@Test(priority=3)
	public void testLogs3() {
		//To print only cookies
		given()
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
		.log().headers();
	}
	
	

}
