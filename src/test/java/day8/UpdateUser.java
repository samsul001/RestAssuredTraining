package day8;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class UpdateUser {
	
	@Test
	void updateUser(ITestContext context) {
		Faker faker = new Faker();		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		int id = (Integer)context.getSuite().getAttribute("user_id");
		String bearerToken = "711ecab7364c6731b16c1cec3a943a5b774f5328b47b80a728d340c29e60a777";
		
		given()
			.body(data.toString())
			.contentType(ContentType.JSON)
			.headers("Authorization","Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200);
	}

}
