package day6;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class JSONSchemaValidation {
	
	//In Response, value of the data will be vlidated
	//In Schema, type of the data will be validation
	//Method "matchesJsonSchemaInClasspath()" from the class JSONSchemaValidator will be used to validate JSON Schema of an API response
	//The name of the schema file should be specified as an argument for method "matchesJsonSchemaInClasspath()"
	
	// To create JSON schema, We need to copy & paste response in the below website.
	// https://jsonformatter.org/json-to-jsonschema (json response--> jsonschema coverter)
	// Json schema having different draft version, each draft version provides different structure of schema
	// json response and json schema are in json format.
	
	@Test
	void jsonSchemaValidation() {
		given()
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

		
	}

}
