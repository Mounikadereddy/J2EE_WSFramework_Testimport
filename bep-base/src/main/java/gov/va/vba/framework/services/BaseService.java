package gov.va.vba.framework.services;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * 
 * Service interface to be implemented by all Ejb beans if
 * <code>JournalingInterceptor</code> need to be applied.
 * 
 * It is recommended for Stateful beans to beans to implement
 * <code>BaseStatefulService</code>
 * 
 * @see BaseStatefulService
 * 
 * @author psimyeru
 * 
 */
public interface BaseService {

	/**
	 * 
	 * Returns entity manager list to have the journaling enabled.
	 * 
	 * @return
	 */
	List<EntityManager> getEntityManagerListForJournaling();
}
