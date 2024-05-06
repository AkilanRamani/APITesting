package TestAutomation;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestAutomation {

	public static void main(String args[]) throws IOException, ParseException {
		// create an instance of ObjectMapper class

		JSONParser parser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		FileReader reader = new FileReader(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITesting\\src\\test\\resources\\DataRead\\jsondata.json");
		Object obj = parser.parse(reader);
		JSONObject jsonObject = (JSONObject) obj;
		String jsondata = jsonObject.toJSONString();

		try {
			
			// serialize students into json string
			String stdString1 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsondata);

			System.out.println("The json data is of " + stdString1);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
