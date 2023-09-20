package day8_apiChaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//access token for go rest api --> 711ecab7364c6731b16c1cec3a943a5b774f5328b47b80a728d340c29e60a777
//go rest api url --> https://gorest.co.in/
public class CreateUser {

	@Test
	void createTest(ITestContext context) {
		Faker faker = new Faker();
		JSONObject data = new JSONObject();		
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "711ecab7364c6731b16c1cec3a943a5b774f5328b47b80a728d340c29e60a777";
		
		int id=
		given()
			.headers("Authorization","Bearer "+bearerToken)
			.body(data.toString())
			.contentType(ContentType.JSON)
			//.contentType("application/json")
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		context.getSuite().setAttribute("user_id", id);
	}
}
