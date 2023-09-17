package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class parsingXMLResponseData {

	//http://restapi.adequateshop.com/api/Traveler?page=1	
	//http://restapi.adequateshop.com/swagger/docs/v1 
	
	//Swagger document
	//http://restapi.adequateshop.com/swagger/ui/index
	
	@Test(priority=1)
	void testXMLResponse1() {
		//Approach -1
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1")
		.then()
			.statusCode(200)
			.body("TravelerinformationResponse.page", equalTo("1"))
			.body("TravelerinformationResponse.totalrecord", equalTo("5930"))
			.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"));
	}
	
	@Test(priority=2)
	void testXMLResponse2() {
		//Approach - 2
		Response res =
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		String pageNo = res.xmlPath().get("TravelerinformationResponse.page").toString();
		String totalRecord = res.xmlPath().get("TravelerinformationResponse.totalrecord").toString();
		String travelerName = res.xmlPath().getString("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
		
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(pageNo, "1");
		Assert.assertEquals(totalRecord, "5930");
		Assert.assertEquals(travelerName, "Developer");
		
	}
	
	@Test(priority = 3)
	void testXMLResponse3() {
		Response res=
		given()
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
		XmlPath xmlObj = new XmlPath(res.asString());
		
		List<String> travellers = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");
		List<String> travellerNames = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		
		Assert.assertEquals(travellers.size(), 10);
		boolean status = false;
		for(String travellerName: travellerNames) {
			if(travellerName.equals("1234")) {
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	}
}
