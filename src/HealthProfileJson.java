import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileJson {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		Person first = new Person ("George R. R.", "Martin", new HealthProfile(90, 1.80), "1984-09-20T18:00:00.000+02:00", (long)0001); 
		Person second = new Person ("Virág", "Harangozó", new HealthProfile(65, 1.70), "1991-09-23T18:00:00.000+02:00", (long)0002); 
		Person third = new Person ("Adam", "Toth", new HealthProfile(75, 1.80), "1991-04-12T18:00:00.000+03:00", (long)0005); 
		
		people.getData().add(first);
		people.getData().add(second);
		people.getData().add(third);
	
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
    	// Jackson Object Mapper 
		ObjectMapper mapper = new ObjectMapper();
		
		// Adding the Jackson Module to process JAXB annotations
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        
		// configure as necessary
		mapper.registerModule(module);
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        
        //mapping the JSON file
        String result = mapper.writeValueAsString(people);
        System.out.println(result);
        mapper.writeValue(new File("people.json"), people);
        
    }
}
