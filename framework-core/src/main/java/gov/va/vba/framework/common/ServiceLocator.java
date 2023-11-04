/*
 * J2EEServiceLocator.java
 *
 * Copyright 2005 U.S. Dept Of Veterans Affais. All rights reserved.
 * U.S. Government PROPRIETARY/CONFIDENTIAL. Use is subject to security terms.
 */

package gov.va.vba.framework.common;

import gov.va.vba.framework.exceptions.ServiceLocatorException;
import gov.va.vba.framework.logging.Logger;

import java.util.*;
import java.net.URL;

import javax.ejb.*;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

/**
 * <p>
 * A utility class used to lookup resources and services (such as EJBHomes, 
 * JMS Destinations, datasources) across the web and ejb tiers. 
 * This class is an implementation of the ServiceLocator pattern and exists as
 * a singleton thereby controlling access to shared resources and improving performance
 * by caching expensive JNDI lookups
 * </p>
 * @author	Mario Rodrigues
 * @since	11/09/2006
 */
public class ServiceLocator {

	private static Logger logger=Logger.getLogger(ServiceLocator.class.getName());
	private InitialContext _ic;
	private Map<String,Object> _cache;
	private static ServiceLocator _instance;

	/**
	 * Initializes the cache and resource bundles
	 * @param
	 * @return 
	 * @throws 
	 */
	private ServiceLocator() throws Exception {
		try{
		logger.debug("SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE)="
				+ SystemUtils
						.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE));
		if (SystemUtils.getProperty(SystemUtils.Key.JNDI_INTERFACE_TYPE)
				.equals(SystemUtils.REMOTE_INTERFACE)) {
			String jndiProviderUrl = new String();
			try {
				jndiProviderUrl = SystemUtils
						.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL);
				logger.debug("SystemUtils.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL)="
						+ SystemUtils
								.getProperty(SystemUtils.Key.JNDI_PROVIDER_URL));
				if (StringUtils.isEmpty(jndiProviderUrl)) {
					throw new Exception(SystemUtils.Key.JNDI_PROVIDER_URL
							+ " property has not been set.");
				}
				logger.info(SystemUtils.Key.JNDI_PROVIDER_URL
						+ " found in bep.properties. Using: "
						+ jndiProviderUrl);
			} catch (Exception exc) {
				logger.warn("Error processing property file."
						+ exc.getMessage());
				logger.info("No hardcoded jndiProviderUrl available: "
						+ jndiProviderUrl);
			}
			Properties props = new Properties();
			props.put(Context.INITIAL_CONTEXT_FACTORY,
					"weblogic.jndi.WLInitialContextFactory");
			props.put(Context.PROVIDER_URL, jndiProviderUrl);
			_ic = new InitialContext(props);
		} else {
			_ic = new InitialContext();
		}
		_cache = Collections.synchronizedMap(new HashMap<String, Object>());
	} catch (NamingException ne) {
		throw new ServiceLocatorException(ne);
	} catch (Exception e) {
		throw new ServiceLocatorException(e);
	}
	}

	/**
	 * Returns a singleton reference to this class using lazy instantiation. 
	 * Double checked locking is used to prevent 2 (or more) threads from trying 
	 * to create more than one object.
	 * Throws a RuntimeException to signal a critical error and to also prevent callers
	 * from having to wrap this method call in a try..catch
	 * @param
	 * @return singleton instance of ServiceLocator
	 * @throws 
	 */
	public static ServiceLocator getInstance() {

		if (_instance == null) {
			synchronized (ServiceLocator.class) {
				if (_instance == null) {
					try {
						_instance = new ServiceLocator();
					}
					catch (Exception se) {						
						logger.error("",se);
						throw new RuntimeException(se);
					}
				}
			}
		}
		return _instance;
	}

	/**
	 * Gets the ejb home object. Caller will need to cast to the type of EJBHome desired
	 * 
	 * @param	The EJB's remote JNDI name
	 * @param	The EJB <code>Class</code>
	 * @return	Remote EJBHome corresponding to the JNDI name
	 * @throws 
	 */
	public EJBHome getRemoteHome(String jndiHomeName, Class<Object> ejbClass) throws Exception {

		EJBHome home = null;

		try {
			if (_cache.containsKey(jndiHomeName)) {
				home = (EJBHome)_cache.get(jndiHomeName);
			}
			else {
				Object objref = _ic.lookup(jndiHomeName);
				Object obj = PortableRemoteObject.narrow(objref, ejbClass);
				home = (EJBHome)obj;
				_cache.put(jndiHomeName, home);
			}
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Remote Home for "+ejbClass.getName(), ne);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return home;
	}

	/**
	 * Gets an ejb local home. Caller will need to cast to the type of EJBHome desired
	 * 
	 * DEPRECATED! Not needed since EJB 3.0
	 * 
	 * @param	The EJB's local JNDI name
	 * @return	Local EJBHome corresponding to the JNDI name
	 * @throws 
	 */
	@Deprecated
	public EJBLocalHome getLocalHome(String jndiHomeName) throws Exception {

		EJBLocalHome home = null;

		try {
			if (_cache.containsKey(jndiHomeName)) {
				home = (EJBLocalHome)_cache.get(jndiHomeName);
			}
			else {
				home = (EJBLocalHome)_ic.lookup(jndiHomeName);
				_cache.put(jndiHomeName, home);
			}
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Local Home for "+jndiHomeName, ne);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return home;
	}

	/**
	 * Gets the queue connection factory so that clients can get JMS connections. 
	 * @param the factory name
	 * @return a Queue Connection Factory
	 * @throws 
	 */
	public QueueConnectionFactory getQueueConnectionFactory(String qConnFactoryName)
		throws Exception {
			
		QueueConnectionFactory factory = null;
		
		try {
			if (_cache.containsKey(qConnFactoryName)) {
				factory = (QueueConnectionFactory) _cache.get(qConnFactoryName);
			}
			else {
				factory = (QueueConnectionFactory) _ic.lookup(qConnFactoryName);
				_cache.put(qConnFactoryName, factory);
			}
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Queue Connection Factory "+qConnFactoryName, ne);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return factory;
	}

	/**
	 * 
	 * @param
	 * @return the Queue Destination to send messages to
	 * @throws 
	 */
	public Queue getQueue(String queueName) throws Exception {

		Queue queue = null;

		try {
			if (_cache.containsKey(queueName)) {
				queue = (Queue) _cache.get(queueName);
			}
			else {
				queue = (Queue) _ic.lookup(queueName);
				_cache.put(queueName, queue);
			}
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Queue "+queueName, ne);		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return queue;
	}

	/**
	 * Gets the  DataSource obj from the application Resource bundle. 
	 * @param 
	 * @return the DataSource corresponding to the name parameter
	 * @throws 
	 * TODO: modify to remove hardcoding once ResourceManager is done. 
	 * 		 Clean up exception handler
	 */
	public DataSource getDataSource(String ds) throws Exception {

		DataSource dataSource = null;
		
		try {
			if (_cache.containsKey(ds)) {
				dataSource = (DataSource) _cache.get(ds);
			}
			else {
				dataSource = (DataSource) _ic.lookup(ds);
				_cache.put(ds, dataSource);
			}
		}
		catch (NamingException ne) {
			throw new Exception("Error finding DataSource "+ds, ne);		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return dataSource;
	}


	/**
	 * 
	 * @param JNDI datasource name
	 * @return the URL value corresponding to the env entry name.
	 * @throws 
	 */
	public URL getUrl(String envName) throws Exception {
		URL url = null;
		try {
			url = (URL)_ic.lookup(envName);
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Environment entry "+envName, ne);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return url;
	}

	/**
	 * checks to see whether an env property exists in the namespace
	 * @param JNDI env property
	 * @return true/false
	 * @throws Exception
	 */
	public boolean exists(String envName) throws Exception {
		Boolean bool = null;
		try {
			bool = (Boolean) _ic.lookup(envName);
		}
		catch (NamingException ne) {
			throw new Exception("Error finding Environment entry "+envName, ne);
		}
		catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return bool.booleanValue();
	}
	
	/*
	 * Will get the EJB reference based on jndi
	 * Before usage, cast to the expected type.
	 * @param jndiName
	 * @param useLocalContext
	 * @return Object
	 * 
	 */
	public Object getServiceReference(String jndiName) throws ServiceLocatorException{
		Object ref;
		try{
				ref = _ic.lookup(jndiName);
		}catch(NamingException ne){
			throw new ServiceLocatorException(ne);
		}catch(Exception e){
			throw new ServiceLocatorException(e);
		}
		return ref;
	}
}