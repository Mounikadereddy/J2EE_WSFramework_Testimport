package gov.va.vba.framework.serverconfig;


import net.sf.ehcache.CacheManager;

/**
 * Weblogic startup utility that instantiates and configures Ehcache cache
 * 
 * This class is deployed as part of the framework.admin.jar and is referenced
 * in the "Startup & Shutdown Classes" section of a WebLogic Domain.
 *
 * @since	Dec 13, 2012
 * @version	
 * @author	Marat Sklyarov
 */
public class CachingConfig {
	private static final String STARTUP = "startup";
	private static final String SHUTDOWN = "shutdown";
	/**
	 * @param Startup args-> startup <path to the bep caching configuration file><br\>
	 * Shutdown args->shutdown
	 */
	public static void main(String[] args) {
		if (args.length < 1){
			System.err.println("Caching can not be configured. No file specified. Default values will be used!!!.");
			printHelp();
		}
		try{
			if (STARTUP.equalsIgnoreCase(args[0])){
				if (args.length < 2){
					System.err.println("Caching can not be configured. No config file specified during the startup.");
					printHelp();
				}
				System.err.println("Initializing cache - args':" + args[0] + " " + args[1] + "'");
				startUpCache(args[1]);
			}else if (SHUTDOWN.equalsIgnoreCase(args[0])){
				System.err.println("Shutting down cache - args':" + args[0] + "'");
				shutDownCache();
			}else{
				System.err.println("Wrong command is specified. Expected the following:");
				printHelp();
			}
		}catch(Exception e){
			System.err.println("CachingConfig.class: Error initializing cache");
			e.printStackTrace();
		}		

	}
	
	private static void startUpCache(String path) throws Exception {
		CacheManager cacheManager = CacheManager.create(path);
		if (cacheManager==null){
			throw new Exception("Cache Manager has not been properly initialized and is null.");
		}
		System.out.println("Cache has been configured!" + cacheManager + " the following caches are attached");
		
		String[] cacheNames = cacheManager.getCacheNames();
		
		for (String name:cacheNames){
			System.out.println("FOUND CONFIG FOR CACHE: " + name);
			System.out.println("******** " + cacheManager.getActiveConfigurationText(name) + "*****");
		}
		
	}

	private static void shutDownCache() {
		try{
			CacheManager.getInstance().shutdown();
			System.out.println("Cache has been shutdown!");
		}catch(Exception e){
			System.out.println("Error shutting down the cache!");
			e.printStackTrace();
		}
		
	}
	
	private static void printHelp(){
		System.err.println("In the strtup configuration the following paramaters are required: 'startup ehcache.xml.\nNote the ehcahce.xml should be placed in the server's classpath, e.g. domain root folder");
		System.err.println("In the shutdown configuration the following single parameter is expected: 'shutdown'");
	}
}
