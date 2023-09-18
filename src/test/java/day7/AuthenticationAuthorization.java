package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

//Authentication - It checks whether credentials are valid or not,this is first level of security.
//Authorization - It checks whether authenticated users have permission/access previlages or not. 
// Only authienticated/valid users can have authorization, this is second level of security.

//Following arthe types of Authentication that can be validated by RestAssured:
// 1.Basic, 2.Digest, 3.preemptive, 4.Bearer Token, 5.oauth 1.0, 6.oauth 2.0, 7.API Key

//If an application is more sensitive, oauth 2.0 authentication will be implemeneted.
//In the API requirement document, Dev would have mentioned, user needs valid cred/API Key/Bearer token
//Depends on API design/requirement, QA perform different kinds of authentication testing 
//All kinds of authetication should be specified in given section.

//basic, digest & preemptice(combination of basic & digest) authentication validation steps are same for user point of view, 
//only internal alogorithms will be changed.

//Bearer Toke, We have to create tokens that needs to be passed in headers along with the request

//ouath2.0 is more secures than oauth1.0, only difference between the both authentication is reduced parameters for oauth2.0
//oauth1.0 authentication requires paramters "consumerKey", "consumerSecret", "accessToken", "tokenSecrate"
//oauth2.0 authentication requires parameter "accessToken" only, now a days this modern applications are using this authentication only
//To generate oauth2 tokens,the following steps needs to be followed:
// we need to get client_id from the identify api after passing cred.
// by which we can get to know "clientSecrate" and "clientCode" in the response of second level api security api.
// if we pass "clientid", "clientSecrate" & "clientCode", we can get access token
// "clientId", "clientSecret" ,"clientCode" needs to be mentioned in query params
// oauth2.0 access token will be generated in different ways for different applications

//APIKey Authentication -> API Keys needs to be generated and then generated api key should be sent as key value pairs 
// which needs to be added in query params

public class AuthenticationAuthorization {
	
	//@Test(priority = 1)
	void testBasicAuth() {
		
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//@Test(priority = 2)
	void testDigestAuth() {
		
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//@Test(priority = 3)
	void testPreemptiveAuth() {
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated",equalTo(true))
			.log().all();
	}
	
	//@Test(priority = 4)
	void testBearerTokenAuth() {
		String bearerToken = "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";
		given()
			.headers("Authorization","Bearer "+bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200);
	}
	
	//@Test(prioirty=5
	void testOAUTH1Authentication() {
		given()
			.auth().oauth("consumerKey", "consumerSecrate", "accessToken", "tokenSecrate")
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 6)
	void testOAuth2Authentication() {
		given()
			.auth().oauth2("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")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200);
	}
	
	@Test(priority =7)
	void testAPIKeyAuth() {
		/*Method1
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&cnt=1")
		.then()
			.statusCode(200);
		*
		*/
		
		//Method 2
		given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.pathParam("myPath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "1")
		.when()
			.get("https://api.openweathermap.org/{myPath}")
		.then()
			.statusCode(200);
	}
}
