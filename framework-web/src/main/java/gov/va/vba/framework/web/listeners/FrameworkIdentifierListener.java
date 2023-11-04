package gov.va.vba.framework.web.listeners;

import gov.va.vba.framework.logging.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import weblogic.logging.NonCatalogLogger;

// This class logs the version number information from all VBA BEP jar files that were loaded in the EAR.
// The version information is stored in the manifest files in each jar file.
// The only jar files to be logged are the ones with packages starting "gov.va.vba.framework"

// This is a ServletContextListener class. Its classloader should be able to see all classes but will only
// see classes in jars in the weblogic domain /lib folder if a weblogic startup class references them.
// So be sure to include the LoggerStartup as a startup class using the weblogic console and set it to 
// execute before the applications.
// A <listener></listener> tag needs to be added to the web.xml files for this class to execute on
// deployment or startup.
public class FrameworkIdentifierListener implements ServletContextListener {
	private static Logger logger=Logger.getLogger(FrameworkIdentifierListener.class);
	private static NonCatalogLogger wlLogger = new NonCatalogLogger("");
	private final static String FRAMEWORK_PACKAGE = "gov.va.vba.framework";
	private final static String STARTUPMESSAGE = "VBA BEP Framework Servlet Listener started";
	private final static String DESTROYMESSAGE = "VBA BEP Framework Servlet Listener destroyed";

	@Override
	// Display a message when the weblogic server is shutdown
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println(DESTROYMESSAGE);
		wlLogger.info(DESTROYMESSAGE);
	}

	@Override
	// Display an initial message AND the framework jar file version information 
	// when the application is deployed or when weblogic is started.
	public void contextInitialized(ServletContextEvent arg0) {

		System.out.println(STARTUPMESSAGE);
		wlLogger.info(STARTUPMESSAGE);
		// Log version of framework jars in EAR and all EAR's classloader can see
		Package[] packages = Package.getPackages(); // get list of all packages that can be seen by this classloader
		HashMap<String,Package> packageMap = mapPackagesByVendorFilteredByImplementationTitle(
			packages, FRAMEWORK_PACKAGE); // gather information only from those packages with version information in the jar files and matching package.
		logFrameworkVersion (packageMap); // send version information message to log(s)
	}

	
	// Filter by the ImplementationTitle in the manifest file of the jar to pick only BGS framework packages
	// Only one row of version information needed per jar file. 
	// Use a map with the key being the name of jar in the ImplementationVendor in the manifest file of the jar
	public static HashMap<String,Package> mapPackagesByVendorFilteredByImplementationTitle(
			Package[] packages, String implTitle) {
		HashMap<String, Package> packageMap = new HashMap<String, Package>();
		for (Package pkg : packages) {
			if (pkg.getImplementationTitle() != null &&
				pkg.getImplementationTitle().toLowerCase().startsWith(implTitle.toLowerCase())) {	
				packageMap.put(pkg.getImplementationVendor() == null ? "" : pkg.getImplementationVendor() , pkg);
			}
		}
		// return the map with the reduced list of jar/package version information
		return packageMap;
	}

	// Perform the message logging
	public static void logFrameworkVersion( HashMap<String, Package> packageMap) {
		Collection<Package> frameworkPackages = packageMap.values(); 
		Iterator<Package> iter = frameworkPackages.iterator();
		String logMessage= null;
		while (iter.hasNext()) {
			Package frameworkPackage = iter.next();
			logMessage = formatMessage(frameworkPackage.getImplementationTitle(),
					frameworkPackage.getImplementationVersion(),
					frameworkPackage.getImplementationVendor());
			// dual log the framework version for a jar file to framework.log (log4j) and to the console and to the server log file
			logger.info(logMessage);  // print to framework.log. 
			System.out.println(logMessage); // print to console
			wlLogger.info(logMessage); // print into the weblogic server log
		}		
	}
	
	// Simple formatting method for the text of the log message
	private static String formatMessage (String title, String version, String vendor) {
		return "VBA BEP Framework Library: "+vendor+", Version: "+version+", Package: "+title.trim() + " is deployed to weblogic.";
	}

	
}
