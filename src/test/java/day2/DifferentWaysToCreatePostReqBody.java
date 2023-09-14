//Create PayLoad using HashMap - If we have small set of data, nothing wrong to use HashMap.
//Best approach to create payLoad is to create Pojo class
package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
public class DifferentWaysToCreatePostReqBody {

	//1) Create Payload using HashMap
	//@Test(priority = 1)
	public void testPostUsingHashMap() {
		HashMap data =new HashMap();
		
		data.put("name", "John");
		data.put("location", "UK");
		data.put("phone", "8765578");
		
		String[] courseArr = {"Java", "ReactJS"};
		data.put("Courses", courseArr);
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.post("http://localhost:3000/students");
//		.then()
//			.statusCode(201)
//			.body("name", equalTo("Scott"))
//			.body("location", equalTo("France"))
//			.body("phone", equalTo("12345678"))
//			.body("Courses[0]",equalTo("Python"))
//			.body("Courses[1]",equalTo("Kotlin"))
//			.header("Content-Type", equalTo("application/json; charset=utf-8"))
//			.log().all();
	}
	
	//@Test(priority = 2)
	public void testDelete() {
		given()
		.when()
			.delete("http://localhost:3000/students/5")
		.then()
			.statusCode(200);
	}
	
	//2) Create Payload using org.json package
	//@Test(priority = 1)
	public void testPostUsingJSONObj() {
		JSONObject data =new JSONObject();
		
		data.put("name", "Faleela");
		data.put("location", "India");
		data.put("phone", "987065411");
		
		String[] courseArr = {"C++", "VueJS"};
		data.put("Courses", courseArr);
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
//			.body("name", equalTo("John"))
//			.body("location", equalTo("France"))
//			.body("phone", equalTo("12345678"))
//			.body("Courses[0]",equalTo("Python"))
//			.body("Courses[1]",equalTo("Kotlin"))
//			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.log().all();
	}
	
	//3) Using POJO Class(Plain Old Java Object Class)
	//@Test
	public void testPostUsingPojoClass() {
		Pojo_PostRequestBody data = new Pojo_PostRequestBody();
		
		data.setName("Safa");
		data.setLocation("Saudi Arabia");
		data.setPhone("3489888");
		String co[] = {"Farming","Agri"};
		data.setCourses(co);
		
		given()
			.contentType(ContentType.JSON)
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Safa"))
			.body("location", equalTo("Saudi Arabia"))
			.body("phone", equalTo("3489888"))
			.body("course[0]",equalTo("Farming"))
			.body("course[1]",equalTo("Agri"))
//			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.log().all();
	}
	
	//4) Using external json file
	@Test
	public void testPostUsingExternalJSONFile() throws FileNotFoundException {
		
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);		
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType(ContentType.JSON)
			.body(data.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name", equalTo("Alam"))
			.body("location", equalTo("Spain"))
			.body("phone", equalTo("90877633"))
			.body("courses[0]",equalTo("MachineLearning"))
			.body("courses[1]",equalTo("RPA"))
//			.header("Content-Type", equalTo("application/json; charset=utf-8"))
			.log().all();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
