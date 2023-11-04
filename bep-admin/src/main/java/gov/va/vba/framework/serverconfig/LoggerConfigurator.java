package gov.va.vba.framework.serverconfig;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggerConfigurator {
	public static String PROPERTIES_FILE_NAME;

	public static void configure() {
		try {
			System.out.println("PROPERTIES_FILE_NAME=" + PROPERTIES_FILE_NAME);
			LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
			File file = new File(PROPERTIES_FILE_NAME);
			System.out.println("File URI=" +file.toURI().toString());
			context.setConfigLocation(file.toURI());
			System.out.println("log4j configured succesfully");
		} catch (Exception e) {
			System.out
					.println("Error in gov.va.vba.framework.serverconfig.LoggerConfigurator.java - ");
			e.printStackTrace();
		}
	}

	public static void firstTimeSetup() {
		System.out.println("BEP Framework Logger..");
		// get log4j.properties file name from BEP.properties
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle("BEP");
			PROPERTIES_FILE_NAME = (String) resourceBundle
					.getObject("log4j.properties.location");
			System.out.println("log4j_properties_location="
					+ PROPERTIES_FILE_NAME);
			configure();
		} catch (MissingResourceException rnfe) {
			rnfe.printStackTrace();
		}

	}
}
