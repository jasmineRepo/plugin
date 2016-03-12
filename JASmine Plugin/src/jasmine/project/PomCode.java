package jasmine.project;

public class PomCode {

	static String pomStringData() {
		
		//TODO: Update the version numbers here whenever you deploy a new version of the Eclipse Plugin
		String versionCore = "3.1.5";
		String versionGUI = "3.1.5.2";
		
		return "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n"
				+ "\t<modelVersion>4.0.0</modelVersion>\n"
				+ "\t<groupId>Package</groupId>\n"
				+ "\t<artifactId>model</artifactId>\n"
				+ "\t<version>1.0.0</version>\n"
				+ "\t<build>\n"
				+ "\t\t<sourceDirectory>src/main/java</sourceDirectory>\n"
//				+ "\t\t<resources>\n"
////				+ "\t\t\t<resource>\n"
////				+ "\t\t\t\t<directory>src</directory>\n"
////				+ "\t\t\t\t<excludes>\n"
////				+ "\t\t\t\t\t<exclude>**/*.java</exclude>\n"
////				+ "\t\t\t\t</excludes>\n"
////				+ "\t\t\t</resource>\n"
//				+ "\t\t\t<resource>\n"
//				+ "\t\t\t\t<directory>src/main/resources</directory>\n"
//				+ "\t\t\t\t<excludes>\n"
//				+ "\t\t\t\t\t<exclude>**/*.java</exclude>\n"
//				+ "\t\t\t\t</excludes>\n"
//				+ "\t\t\t</resource>\n"
//				+ "\t\t</resources>\n"
				+ "\t\t<plugins>\n"
				+ "\t\t\t<plugin>\n"
				+ "\t\t\t\t<artifactId>maven-compiler-plugin</artifactId>\n"
				+ "\t\t\t\t<version>3.1</version>\n"
				+ "\t\t\t\t<configuration>\n"
				+ "\t\t\t\t\t<source>1.8</source>\n"
				+ "\t\t\t\t\t<target>1.8</target>\n"
				+ "\t\t\t\t</configuration>\n"
				+ "\t\t\t</plugin>\n"
				+ "\t\t</plugins>\n"
				+ "\t</build>\n"
				+ "\t<dependencies>\n"
				+ "\t\t<dependency>\n"
				+ "\t\t\t<groupId>com.github.jasmineRepo</groupId>\n"
				+ "\t\t\t<artifactId>JAS-mine-core</artifactId>\n"
				+ "\t\t\t<version>"
				+ versionCore
				+ "</version>\n"
				+ "\t\t</dependency>\n"
				+ "\t\t<dependency>\n"
				+ "\t\t\t<groupId>com.github.jasmineRepo</groupId>\n"
				+ "\t\t\t<artifactId>JAS-mine-gui</artifactId>\n"
				+ "\t\t\t<version>"
				+ versionGUI
				+ "</version>\n"
				+ "\t\t</dependency>\n"
				+ "\t</dependencies>\n"
				+ "\n"
				+ "\t<repositories>\n"
				+ "\t\t<repository>\n"
				+ "\t\t\t<id>jitpack.io</id>\n"
				+ "\t\t\t<url>https://jitpack.io</url>\n"							//Use jitpack to create the project artifacts and host (intergrates with github)
				+ "\t\t</repository>\n"
				+ "\t</repositories>\n"
				+ "</project>";
			
	}
	
}
