package gov.va.vba.framework.serverconfig;


import java.util.*;
import javax.naming.*;
import weblogic.jndi.WLContext;

/**
 * An in-container utility class that binds Adobe LiveCycle server attributes to the 
 * WebLogic JNDI cluster that this class runs within. This class should only be run by 
 * admin personnel who understand the effects of binding names to the JNDI tree.
 * <p>
 * This class is deployed as part of the framework.admin.jar and is referenced
 * in the "Startup & Shutdown Classes" section of a WebLogic Domain.
 *
 * @since	Mar 8, 2009
 * @version	
 * @author	Mario Rodrigues
 */
public class LiveCycleJNDIBinder {

	//WLS admin console input:
	//ClassName: gov.va.vba.framework.serverconfig.LiveCycleJNDIBinder
	//Deployment Order: 1
	//Arguments: -filesystem /AdobeDoc/devl -url t3://vbahinappperf2.vba.va.gov,vbahinappperf3.vba.va.gov:45015 -user **** -pw ****
	private static final String USAGE = "Usage: LiveCycleJNDIBinder [-h] -filesystem[fs] -url[Adobe Cluster URL] -user[admin user ID] -pw[admin password]"; 
	
	/**
	 * Loops through input arguments and binds values based on names set in
	 * enum LiveCycleContexts. Only values set in the enum are bound as of this
	 * release. Value are not replicated across nodes in the cluster. This is to prevent irregularities during fail over
	 * 
	 * @param args	
	 * @throws	
	 * @return	
	 * @since	Mar 12, 2009
	 */
	public static void main(String[] args) throws Exception {
		
		Context ctx = null;				
		String subcontexts[] = null;
		Name name = null; 
		Map<LiveCycleContexts, String> argMap = new EnumMap<LiveCycleContexts, String>(LiveCycleContexts.class);			
		int i = 0;
		int j = 0;
        String arg = null;
        boolean helpFlag = false;
        String fileSystem, url, userId, pw = null;
        
        while (i < args.length) {
        	arg = args[i++];         	 
            if (arg.equals("-h")||arg.equalsIgnoreCase("-help")) {
            	helpFlag = true;
                System.out.println(USAGE);
                break;
            }            
            else if (arg.equals("-filesystem")) {
                if (isBlank(fileSystem = args[i++])) {
                	System.err.println("-filesystem requires a filesystem path structure");
                	break;
                }
                argMap.put(LiveCycleContexts.values()[j++], fileSystem);
            }
            else if (arg.equals("-url")) {
                if (isBlank(url = args[i++])) {
                    System.err.println("-url requires a qualified domain name");
                    break;
                }
                argMap.put(LiveCycleContexts.values()[j++], url);
            }
            else if (arg.equals("-user")) {
                if (isBlank(userId = args[i++])) {
                    System.err.println("-user requires a user ID value");
                    break;
                }
                argMap.put(LiveCycleContexts.values()[j++], userId);
            }                        
            else if (arg.equals("-pw")) {
                if (isBlank(pw = args[i++])) {
                    System.err.println("-pw requires a password value");
                    break;
                }
                argMap.put(LiveCycleContexts.values()[j++], pw);
            }             
            else {
            	System.err.printf("%s%s\n", "Invalid argument: ", arg);
            	break;
            }
        }
        //TODO: Temp: For VRE QA project. Need to remove this from here (and enum class) ASAP
        argMap.put(LiveCycleContexts.values()[j++], "smtp.va.gov");
        
        if (!helpFlag) {
        	if (argMap.keySet().toArray().length == LiveCycleContexts.values().length) {
        		try {
        			Hashtable<String, String> ht = new Hashtable<String, String>();
        			ht.put(WLContext.REPLICATE_BINDINGS, "false");
		        	ctx = new InitialContext(ht);
		        	for (LiveCycleContexts context : argMap.keySet()) {
		        		subcontexts = context.getName().split("[\\/]");
			        	name = new CompositeName(); 
			    		for (int k = 0; k < subcontexts.length-1; k++) {
			    			name.add(subcontexts[k]);
			    			ctx.createSubcontext(name);
			    		}		        
			    		ctx.bind(name.add(subcontexts[subcontexts.length-1]), argMap.get(context));
			    		if (context.isSensitive())
			    			System.out.printf("Bound JNDI Context Name '%s' to: %s\n", name.toString(), argMap.get(context).replaceAll(".", "*"));
			    		else
			    			System.out.printf("Bound JNDI Context Name '%s' to: %s\n", name.toString(), argMap.get(context));
					}
        		}
        		finally {
        			ctx.close();
        		}
	        }
	        else
	        	System.err.println("Required values are missing. "+USAGE);
        }	
	}


	/**
	 * 
	 * @param str
	 * @return	
	 * @throws	
	 * @return	
	 * @since	Mar 24, 2009
	 */
	private static boolean isBlank(String str) {
		
		return(str==null||str.trim().length()==0);		
	}
	
	/**
	 * Utility method that only unbinds Adobe LiveCycle server names based on
	 * names in the LiveCycleContexts enum. A future release will contain a more
	 * generic unbind utility based on a well defined interface. 
	 * 
	 * @throws Exception	
	 * @throws	
	 * @return	
	 * @since	Mar 24, 2009
	 */
	/*
	private static void unbindContexts() throws Exception {
		
		Context ctx = new InitialContext();				
		
		LiveCycleContexts[] context = LiveCycleContexts.values();
		for (int i = 0; i < context.length; i++) {
			System.out.println("destroying: "+context[i].getName());
			ctx.unbind(context[i].getName());						
		}
		ctx.destroySubcontext(context[1].getName().substring(0, context[1].getName().lastIndexOf('/')));
		ctx.destroySubcontext(context[1].getName().substring(0, context[1].getName().indexOf('/')));		
	}
	*/
}
