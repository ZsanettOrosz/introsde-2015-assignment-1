import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileReader {
	public static PeopleStore people = new PeopleStore();

	public static void main(String[] args) throws Exception {
		//JAXB object mapper
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = jc.createUnmarshaller(); // unmarshalling the file
		PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml")); // fills the PeopleStore with data
		//writeing people to the output
		List<Person> list = people.getData();
		for (Person person : list) {
			System.out.println("Person: " + person.getFirstname()+ " " + person.getLastname()); //writer.println("Person: " + person.getFirstname()+ " " + person.getLastname());
			System.out.println("born "	+ person.getBirthdate());//writer.println()
			System.out.println("Health Profile: ");
			System.out.println("\tLast update" + person.getHProfile().getLastUpdate());
			System.out.println("\tweight: " + person.getHProfile().getWeight());
			System.out.println("\theight: " + person.getHProfile().getHeight());
			System.out.println("\tBMI: " + person.getHProfile().getLastUpdate());
		}
		System.out.println();
	}
}
