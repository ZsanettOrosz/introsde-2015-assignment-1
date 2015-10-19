import java.io.IOException;

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

public class HealtProfileXpath {
	Document doc;
	XPath xpath;

	// to load the xml file
	public void loadXML() throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		doc = builder.parse("people.xml");

		getXPathObj();
	}

	// creating xpath object
	public XPath getXPathObj() {
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		return xpath;
	}

	// prints a persos'n weight.
	// parameters: firstname, lastname
	public void getWeight(String fname, String lname)
			throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[firstname='"
				+ fname + "' and lastname='" + lname
				+ "' ]/healthprofile/weight");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		System.out.println(fname + " " + lname + "'s weight: "
				+ node.getTextContent());
	}

	// prints a persos'n height.
	// parameters: firstname, lastname
	public void getHeight(String fname, String lname)
			throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person[firstname='"
				+ fname + "' and lastname='" + lname
				+ "' ]/healthprofile/height");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		System.out.println(fname + " " + lname + "'s height: "
				+ node.getTextContent());
	}

	// prints the health profile of a person by his ID
	//parametars: person ID
	public void getPersonByID(String personID) throws XPathExpressionException {
		// can't put the long number into the xpath expression. 0001->1 :(
		XPathExpression expr = xpath.compile("/people/person[@id='" + personID
				+ "']/healthprofile");
		/* people/person[@id='1'] */
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		if (node != null) {
			System.out.println("The person's healt profile with ID " + personID
					+ ": " + node.getTextContent());
		} else {
			System.out.println("There is nobody");
		}
	}
	
	//prints a person by a condition to his(her) weight
	//parameters: weight, condition:<,>,=
	public void getPersonByCondition(String weight, String condition)
			throws XPathExpressionException {

		XPathExpression expr = xpath.compile("//person/healthprofile[weight"
				+ condition + "'" + weight + "']/..");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("All persons with weight " + condition + " "
				+ weight);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			System.out.println(n.getTextContent());
		}
	}

	// listing all persons 
	public void listAllPeople() throws XPathExpressionException {
		XPathExpression expr = xpath.compile("/people/person");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("All persons on the list");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			System.out.println(n.getTextContent());
		}
	}


	public void start(String [] args) throws ParserConfigurationException, SAXException,
			IOException, XPathExpressionException {
		
		loadXML();	
		
		int argCount = args.length;
		if(argCount == 1){
			getPersonByID(args[0]);
		}else if (argCount == 2){
			// parameters: weight, operator
			getPersonByCondition(args[0], args[1]); 
		}
		
		//the methods:getWeight,getHeight and list is not called in evaluation
		
	}
	
	public static void main(String[] args) throws Exception {
		HealtProfileXpath hpr = new HealtProfileXpath();
		try {
			hpr.start(args); // starting the program
			//exceptions are not handeld properly
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
