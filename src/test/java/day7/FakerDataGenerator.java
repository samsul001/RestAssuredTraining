package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

//Faker Library => using this library , we can generate randon data
// It is very useful to create random data for payloads.

//Google "Java Faker Libraray maven" --> navigate to Github page --> select dependecy code

//We need to create object for Faker class, by using the object we can create different types of random data such as the following:
// first name, last name, phone, email, address, number, color, code, date etc.,

public class FakerDataGenerator {
	
	@Test
	void testGenerateDummyData() {
		Faker faker = new Faker();
		String fName = faker.name().firstName();
		String lName = faker.name().lastName();
		String fullName = faker.name().fullName();
		String phoneNum = faker.phoneNumber().cellPhone();
		String uName = faker.name().username();
		String pwd = faker.internet().password();
		String emailId = faker.internet().emailAddress();
		
		System.out.println(fName);
		System.out.println(lName);
		System.out.println(fullName);
		System.out.println(phoneNum);
		System.out.println(uName);
		System.out.println(pwd);
		System.out.println(emailId);
		
	}
	
	

}
