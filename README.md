# introsde-2015-assignment-1

#About the code

The code consist of 4 classes to do the excercies, 3 helper classes, a people.xml file with all the 20 persons inside, a  people.json file which contains the JSON version of 3 people and a peopleUnmarshalled.xml file where the unmarshalled persons are from the people.xml. To the people.xml file there is a corresponding peopleSchema.xsd file also, which contains the xml schema of the people.xml.

The main classes are HealthProfileXpath, HealthProfileJson, HealthProfileReader and HealthProfileWriter (in Tasks the code does part see the role of the classes)

Also there are additional classes like Person, HealthProfile and PeopleStore. 
The Person represents one person, each Person has a HealthProfile and the persons are stored in the PeopleStore class. 
To the whole project there is a corresponding build.xml and ivy.xml file. The ivy.xml file manages the dependencies of the projects and build.xml manages the compilation and execution of each task. 


#Tasks the code does

The HealthProfileXpath class contains the above methods using XPATH:
	loadXML – loads the XML file into memory
	getXpathObject – gets an XPATH object so we can use it in the program
	getWeight – prints the weight of a person given the first name and last name
	getHeight - prints the height of a person given the first name and last name
	getPersonByID – prints the person having the ID specified in the parameter list
	getPersonByCondition – prints all persons corresponding the given weight and operator, which can be <,> or = 
	listAllPeople – lists all the people
	start – calls the getPersonByID and getPersonByCondition methods to execute the task in the assignment
	main – instanciates the class and starts the program

The HealthProfileJson class creates 3 persons and adds them to the PeopleStore class, then marshalls the persons into JSON format and saves it into people.json file, also prints it to the System output.
The HealthProfileWriter does the same but it marshalls into XML format and saves the people in the people.xml file.
The HealthProfileReader reads the people.xml file, unmarshalls its content and prints it to the System  output. 

#How to run it

To run the application it is needed to use ant. It is possible to run ant execute.evaluation command and the program will go through all the tasks of the assignment and print them to the System output.
Also the tasks can be run separately, for example to unmarshall the people.xml file one can use the ant execute.HPReader command and it will display all persons in the System output also will write the content to the peopleUnmarshalled.xml file.
For more commands see the build.xml file



