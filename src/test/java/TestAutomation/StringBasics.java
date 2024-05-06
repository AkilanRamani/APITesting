package TestAutomation;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class StringBasics {
	
	ResponseSpecification res = null;

	@BeforeClass
	public  void test12() {
		res = RestAssured.expect();
		res.contentType(ContentType.JSON);
		res.statusCode(200);
		res.time(Matchers.lessThan(50000L));
		res.statusLine("HTTP/1.1 200 OK");
	
	}
   @Test
	public void getData() {
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
		RestAssured.when().get().then().spec(res).body("size()", Matchers.lessThanOrEqualTo(6));
		
	}
		
   @Test
	public void getData1() {
		RestAssured.baseURI = "https://reqres.in/api/users?page=2";
		RestAssured.when().get().then().spec(res).header("connection" , "keep-alive");
	

}
}
