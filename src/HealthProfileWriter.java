import java.io.File;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person first = new Person ("George R. R.", "Martin", new HealthProfile(92, 1.80), "1984-09-20T18:00:00.000+02:00", (long)0001); 
		Person second = new Person ("Virág", "Harangozó", new HealthProfile(65, 1.70), "1991-09-23T18:00:00.000+02:00", (long)0002); 
		Person third = new Person ("Adam", "Toth", new HealthProfile(75, 1.80), "1991-04-12T18:00:00.000+03:00", (long)0005); 
		
		people.getData().add(first);
		people.getData().add(second);
		people.getData().add(third);
	
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		//JAXB object mapper
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller(); // marhalling object
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        System.out.println(people.getData().size());
        m.marshal(people,new File("people.xml")); // fills the xml with data
        m.marshal(people, System.out);			  // marshalling into the system default output
                
    }
}
