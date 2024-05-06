package TestAutomation;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TestMain {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		
		String url = "https://reqres.in/api/users/2";

	URL test =	 new URL(url);
	URI uri = test.toURI();
	System.out.println(uri);
	}

}
