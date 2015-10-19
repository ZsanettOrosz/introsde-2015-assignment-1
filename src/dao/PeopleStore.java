package dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import model.Person;

//the class is to handle a set of persons

@XmlRootElement(name="people")
@XmlAccessorType(XmlAccessType.FIELD)
public class PeopleStore {
	@XmlElement(name="person")
	private List<Person> data = new ArrayList<Person>(); 
	
	public PeopleStore () {
		super();
	}
	public PeopleStore (PeopleStore store) {
		super();
		this.data = store.data;
	}

	public List<Person> getData() {
		return data;
	}

	public void setData(List<Person> data) {
		this.data = data;
	}
}
