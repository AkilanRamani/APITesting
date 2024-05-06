package TestAutomation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class APITest {

	@Test
	public void responseExtraction() {

		Response res = RestAssured.given().cookie("JSESSIONID", "71B1DDDD64B8AF701B34DC4EAF1058C2.jvm1")
				.get("http://10.63.39.173:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		System.out.println(res.asPrettyString());
		JsonPath jsonpath = new JsonPath(res.asString());
		System.out.println(jsonpath.getString("data"));

		System.out.println(jsonpath.getString("data[0].orderNo"));

		List<Object> obj = jsonpath.getList("data.orderNo");
		System.out.println(obj);
		int count = obj.size();
		for (int i = 0; i < count; i++) {
			System.out.println("Column name is : " + jsonpath.get("data.columnName[" + i + "]") + "\t"
					+ "Max Value is  : " + jsonpath.get("data.max[" + i + "]"));

		}
	}

	@Test
	public void responseExtractionUsingGroovy() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		// System.out.println(res.asPrettyString());
		Map<String, ?> allTeamDataForSingleTeam = res.path("data.find { it.columnName == 'netCost' }");
		System.out.println(allTeamDataForSingleTeam);
	}

	@Test
	public void responseExtractionUsingGroovyWithSingleValue() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String certainPlayer = res.path("data.find { it.orderNo == 16 }.columnName");
		System.out.println(certainPlayer);

	}

	@Test
	public void extractListOfValueUsingFindAll() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		List<String> playerNames = res.path("data.findAll { it.orderNo > 10 }.max");
		System.out.println(playerNames);
	}

	@Test
	public void extractValueUsingMax() {

		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String highestNumberPlayer = res.path("data.max { it.orderNo }.columnName");
		System.out.println(highestNumberPlayer);
	}

	@Test
	public void extractValueUsingMin() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String highestNumberPlayer = res.path("data.min { it.orderNo }.columnName");
		System.out.println(highestNumberPlayer);
	}

	@Test
	public void extractValueUsingCollect() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		int highestNumberPlayer = res.path("data.collect { it.orderNo }.sum() ");
		System.out.println(highestNumberPlayer);
	}

	@Test
	public void extractValueUsingFindAndFindAll() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		Map<String, ?> playerOfCertainPosition = res
				.path("data.findAll { it.columnName == \"cessAmount\" }.find { it.columnCaption == \"CESS Amt\" }");

		System.out.println(playerOfCertainPosition);

	}

	@Test
	public void extractValueUsingParam() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String columnName = "cessAmount";
		String columnCaption = "CESS Amt";
		Map<String, ?> playerOfCertainPositionByParameters = res.path(
				"data.findAll { it.columnName == '%s' }.find { it.columnCaption == '%s' }", columnName, columnCaption);
		System.out.println(playerOfCertainPositionByParameters);

	}

	@Test
	public void extractValueUsingParam12() {
		Response res = RestAssured.given().cookie("JSESSIONID", "08B1A5845B3CF12205E174BB44D77832.jvm1")
				.get("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig").then().extract().response();
		String columnName = "cessAmount";
		String columnCaption = "CESS Amt";
		ArrayList<Map<String, ?>> playerOfCertainPositionByParameters = res.path(
				"data.findAll { it.columnName == '%s' }.findAll { it.columnCaption == '%s' }", columnName,
				columnCaption);
		System.out.println(playerOfCertainPositionByParameters);

	}

	@Test(dataProvider = "authorizationCookies")
	public void dataDrivenTesting(String cookiename, String cookieValue) {
		RestAssured.baseURI = "http://10.63.39.141:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.cookie(cookiename, cookieValue);
		Response response = httpRequest.request(Method.GET, "");
		System.out.println("Status received" + response.getStatusLine());
		System.out.println("Response" + response.prettyPrint());
	}

	@DataProvider(name = "authorizationCookies")
	public Object[][] createAuthCookie() {
		return new Object[][] { { "JSESSIONID", "444D96EB65A33A2FC70C2A94A087C331.jvm1" },

		};

	}

	@Test
	public void validProtocolValidation() throws IOException {
		String baseURI = "http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig";
		if (baseURI.contains("http")) {
			URL urlObj = new URL(baseURI);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Cookie", "JSESSIONID=B9C8F4BCD8CA773EE565651B675A02FB.jvm1");
			int responseCode = con.getResponseCode();
			System.out.println("GET Response Code :: " + responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println(response.toString());
			}
		} else {

			System.out.println("The Given Protocol is an invalid and Does not support");
		}

	}

	@Test
	public void validProtocolValidation1() throws IOException {
		OkHttpClient client = new OkHttpClient();

		Request getRequest = new Request.Builder()
				.url("http://cloudtest5:8181/RayMedi_HQ/rest/purchaseEntry/columnConfig")
				.addHeader("Cookie",
						"JSESSIONID=E2BF75C7DECD5D7CFD80300E989FFD18.jvm1; _zcsr_tmp=ae1e1426efcac4b64c63db7eadd80209e9b05e26f0c9b9e1ebc6d48e174f13340710bc7de8cf76173fd0f5547201ab731eb5e6b4fc902ce531266775a6bb29a7; csrfCookie=ae1e1426efcac4b64c63db7eadd80209e9b05e26f0c9b9e1ebc6d48e174f13340710bc7de8cf76173fd0f5547201ab731eb5e6b4fc902ce531266775a6bb29a7")
				.build();

		try {
			okhttp3.Response response = client.newCall(getRequest).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deserializationOfJsonResponseUsingObjectMapper() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		FileReader reader = new FileReader(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITesting\\src\\test\\resources\\DataRead\\jsondata.json");
		Object obj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) obj;

		String data = jsonObject.toJSONString(); 
		
		System.out.println(data);
		try {

			GetConfigsForPO poconfig = mapper.readValue(data, GetConfigsForPO.class);

			System.out.println(poconfig);

			String stdString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(poconfig);

			System.out.println(stdString);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
