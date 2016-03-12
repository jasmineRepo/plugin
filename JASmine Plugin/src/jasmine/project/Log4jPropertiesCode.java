package jasmine.project;

public class Log4jPropertiesCode {
	
	static String log4jStringData() {

		return "\n"
				+ "log4j.rootLogger=INFO, stdout\n"
				+ "\n"
				+ "#log4j.appender.stdout=org.apache.log4j.ConsoleAppender\n"
				+ "log4j.appender.stdout=" + ClassSourceCode.IMPORT_PREFIX + "microsim.gui.shell.JasConsoleAppender\n"
				+ "log4j.appender.stdout.layout=org.apache.log4j.PatternLayout\n"
				+ "log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n\n"
				+ "\n"
				+ "#log4j.appender.file=org.apache.log4j.RollingFileAppender\n"
				+ "#log4j.appender.file.File=output/simplebug.log\n"
				+ "#log4j.appender.file.MaxFileSize=1MB\n"
				+ "#log4j.appender.file.MaxBackupIndex=1\n"
				+ "#log4j.appender.file.layout=org.apache.log4j.PatternLayout\n"
				+ "#log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";

	}
	
}
