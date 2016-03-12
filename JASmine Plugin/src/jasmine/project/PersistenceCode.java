package jasmine.project;

public class PersistenceCode {

	static String persistenceStringData() {
		
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<persistence version=\"1.0\"\n"
				+ "\txmlns=\"http://java.sun.com/xml/ns/persistence\"\n"
				+ "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
				+ "\txsi:schemaLocation=\"http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_1_0.xsd\">\n"
				+ "\n"
				+ "\t<persistence-unit name=\"sim-model\">\n"
				+ "\t\t<properties>\n"
				+ "\t\t\t<property name=\"hibernate.connection.driver_class\" value=\"org.h2.Driver\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.username\" value=\"sa\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.password\" value=\"\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.url\" value=\"jdbc:h2:[input-path]\" />\n"
				+ "\t\t\t<property name=\"hibernate.dialect\" value=\" org.hibernate.dialect.H2Dialect\" />\n"
				+ "\t\t</properties>\n"
				+ "\t</persistence-unit>\n"
				+ "\n"
				+ "\t<persistence-unit name=\"sim-model-out\">\n"
				+ "\t\t<properties>"
				+ "\t\t\t<property name=\"hibernate.connection.driver_class\" value=\"org.h2.Driver\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.username\" value=\"sa\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.password\" value=\"\" />\n"
				+ "\t\t\t<property name=\"hibernate.connection.url\" value=\"jdbc:h2:[output-path]\" />\n"
				+ "\t\t\t<property name=\"hibernate.dialect\" value=\"org.hibernate.dialect.H2Dialect\" />\n"
				+ "\t\t</properties>\n"
				+ "\t</persistence-unit>\n"
				+ "\n"
				+ "</persistence>\n";
 
	}
	
//	public static Document loadXMLFromString(String xml) throws Exception
//	{
//	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	    DocumentBuilder builder = factory.newDocumentBuilder();
//	    InputSource is = new InputSource(new StringReader(xml));
//	    return builder.parse(is);
//	}
	
	
	
}
