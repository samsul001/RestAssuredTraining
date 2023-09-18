package day6;
//Serialization and DeSerialization
	// It is an in-built process in RestAssured, but we can understand how it works internally
	//	In simple words, serialization converts the java object to a byte stream. 
	//	As the name suggests, deserialization works the opposite of serialization. 
	//	Deserialization is the conversion of this byte stream back to the actual object.

	//Serialization – Convert the class's objects of POJO to JSON
	//Deserialization – Convert a JSON object to an object of the class of POJO
	//POJO -----> JSON ------> POJO
	//Java Object(Pojo class object) is converting to JSON and processing through the network, 
	//and the destination side again it is sending JSON which is coverted to POJO
	
	//jackson binder comes along with restassured libraray which can be used to validate Serialization & Deseriaization explicitly
	//JSON is the light weight format & more secured, Thatw why we always trasnmit the data in json format.

	//Serialization/Deserialization without setters methods
	// Without setters methods, we cannot serialize with expected values. 
	// All the class level fields would be initialized with default null value that will be serialized

	//Serialization/Deserialization without setters methods
	// Without getters methods, we cannot serialize.
	
	//@JsonIgnore <- Ths annotation wil exclude pojo object from Serialization and Deserialization
	//@JsonIgnoreProperties(value={"name"},allowGetters=true) <- this will allow mentioed fields of Pojo class to be used just for
	// Serialization and not deserialization
	//@JsonIgnoreProperties(value={"name"},allowSetters=true) <- this will allow mentioed fields of Pojo class to be used just for
	// Deserialization and not Serialization
	

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeSerialization {
	
	@Test(priority=1)
	void testPojo2Json() throws JsonProcessingException {
		
		Students stuObj = new Students();
		stuObj.setName("Samsul");
		stuObj.setLocation("Tenkasi");
		stuObj.setPhone("7894561230");
		String[] courses = {"C++","Java"};
		stuObj.setCourses(courses);
		
		//Java Object to JSON
		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stuObj);
		System.out.println("Serialization...");
		System.out.println(jsonData);
		
	}
	
	@Test(priority=2)
	void testJsonToPojo() throws JsonMappingException, JsonProcessingException {
		String jsonData = "{\r\n"
				+ "  \"name\" : \"Samsul\",\r\n"
				+ "  \"location\" : \"Tenkasi\",\r\n"
				+ "  \"phone\" : \"7894561230\",\r\n"
				+ "  \"courses\" : [ \"C++\", \"Java\" ]\r\n"
				+ "}";
		ObjectMapper objMapper = new ObjectMapper();
		Students stuObj =objMapper.readValue(jsonData, Students.class);
		System.out.println("Deserialization...");
		System.out.println(stuObj.getName());
		System.out.println(stuObj.getLocation());
		System.out.println(stuObj.getPhone());
		System.out.println(stuObj.getCourse()[0]);
		System.out.println(stuObj.getCourse()[1]);
	}

}
