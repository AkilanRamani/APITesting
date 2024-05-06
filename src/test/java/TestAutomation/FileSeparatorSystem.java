package TestAutomation;



import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class FileSeparatorSystem {

	@Test
	public  void testString(ITestContext test) {
		XmlTest t = test.getCurrentXmlTest();
		System.out.println(t.getName());
		System.out.println(test.getSuite().getName());
	/*	System.out.println(t);
		Path path = Paths.get("C:\\Users\\akilan-18633\\File.txt");
		System.out.println(path);

		// get FileSystem object
		FileSystem fs = path.getFileSystem();

		// apply getSeparator() methods
		String separator = fs.getSeparator();

		// print
		System.out.println("Separator: " + separator); */

	}

}
