package day8_apiChaining;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetUser {
	
	@Test
	void getUser(ITestContext context) {
		int id = (Integer) context.getSuite().getAttribute("user_id");
		String bearerToken = "711ecab7364c6731b16c1cec3a943a5b774f5328b47b80a728d340c29e60a777";
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200);
	}
}
