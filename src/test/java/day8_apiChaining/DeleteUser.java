package day8_apiChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DeleteUser {
	
	@Test
	void deleteUser(ITestContext context) {
		int id = (Integer)context.getSuite().getAttribute("user_id");
		String bearerToken = "711ecab7364c6731b16c1cec3a943a5b774f5328b47b80a728d340c29e60a777";
		
		given()
		.headers("Authorization","Bearer "+bearerToken)
		.pathParam("id", id)
	.when()
		.delete("https://gorest.co.in/public/v2/users/{id}")
	.then()
		.statusCode(204);
	}

}
