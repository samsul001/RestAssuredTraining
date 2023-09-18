package day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class XMLSchemaValidation {

	//If API is related to SOAP related technologies, then it will return xml response
	//Now a days, many APIs are using JSON response format only not an XML
	//POSTMAN does not support XML schema validation, It supports only JSON schema validation
	//XML schema can validated by using program 
	//XML Response is in (.xml) format where as XML Schema is in (.xsd) format
	//Method "matchesXsdInClasspath" from the class "RestAssuredMatchers" class will be used to validate xml schema of an APIs xml response.
	
	//https://www.liquid-technologies.com/online-xml-to-xsd-converter (xml response to xml schema)
	
	@Test
	void xmlSchemaValidation() {
		
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("traveller.xsd"));
		
	}
}
