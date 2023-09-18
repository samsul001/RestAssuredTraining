package day5;

import java.io.File;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class FileUploadDownload {
	
	//To test single fileUpload, Multiple file upload apis, we use "file" or "files" as  key parameter in body section of Postman for respecitve file upload types
	//User should manually attach the single file/multiple fiels for "file/files" key paramters in the Body --> form-data section.
	
	//Step by Step procedure to setup fileupload APIs
	// Check for the folder "spring-boot-file-upload-download-rest-api-master" in download section & navigate to it
	// run command java -jar file-demo-0.0.1-SNAPSHOT.jar , hence Springboot sample fileupload webapp will be opened in local host.
	// fileupload api: http://localhost:8080/uploadFile
	// Using multipart() method & content type, send uploadFile api request.
	// "POST" request will be sent along with file to happen fileupload
	
	// Interview
	// If you want to upload file, How do you specificy file data ?
	// along with multipart() method with contentType as "multipart/formdata" , I can specify single file upload.
	
	@Test(priority=1)
	void testSingleFileUpload() {
		File myFile = new File("C:\\Users\\samsu\\Documents\\Software_Test_Architect\\Backend_testing\\REST_Assured\\Test_Data\\Test1.txt");
		
		given()
			//.contentType(ContentType.MULTIPART)
			.contentType("multipart/form-data")
			.multiPart("file",myFile)
		.when()
			.post("http://localhost:8080/uploadFile")
		.then()
			.statusCode(200)
			.body("fileName", equalTo("Test1.txt"))
			.log().all();
		
	}
	
	//@Test(priority=2)
	void testMultipleFileUpload1() {
		//approach 1
		File myFile1 = new File("C:\\Users\\samsu\\Documents\\Software_Test_Architect\\Backend_testing\\REST_Assured\\Test_Data\\Test1.txt");
		File myFile2 = new File("C:\\Users\\samsu\\Documents\\Software_Test_Architect\\Backend_testing\\REST_Assured\\Test_Data\\Test2.txt");
		given()
			//.contentType(ContentType.MULTIPART)
			.contentType("multipart/form-data")
			.multiPart("files",myFile1)
			.multiPart("files",myFile2)
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") //different URI for mulriple files upload
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test1.txt"))
			.body("[1].fileName", equalTo("Test2.txt"))
			.log().all();
		
	}
	//@Test(priority=3)
	void testMultipleFileUpload2() { //wont work for all kinds of api
		//approach 2
		File myFile1 = new File("C:\\Users\\samsu\\Documents\\Software_Test_Architect\\Backend_testing\\REST_Assured\\Test_Data\\Test1.txt");
		File myFile2 = new File("C:\\Users\\samsu\\Documents\\Software_Test_Architect\\Backend_testing\\REST_Assured\\Test_Data\\Test2.txt");
		File[] fileArr = {myFile1,myFile2};
		given()
			//.contentType(ContentType.MULTIPART)
			.contentType("multipart/form-data")
			.multiPart("files",fileArr)
		.when()
			.post("http://localhost:8080/uploadMultipleFiles") //different URI for mulriple files upload
		.then()
			.statusCode(200)
			.body("[0].fileName", equalTo("Test1.txt"))
			.body("[1].fileName", equalTo("Test2.txt"))
			.log().all();		
	}
	
	// File Download
	// "file download" uri will be taken from "fileupload" api response.
	// By sending get request for the "file download" uri, we can test file download
	//http://localhost:8080/downloadFile/Test1.txt
	
	@Test(priority = 4)
	void testFileDownload() {
		given()
		.when()
			.get("http://localhost:8080/downloadFile/Test1.txt")
		.then()
			.statusCode(200)
			.log().all();
	}

}
