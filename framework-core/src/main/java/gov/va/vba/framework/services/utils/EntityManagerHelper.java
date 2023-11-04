package gov.va.vba.framework.services.utils;

import gov.va.vba.framework.logging.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.openjpa.persistence.EntityManagerImpl;

public class EntityManagerHelper {
	private static final String PERSISTENCE_UNIT ="corpDbAdmin";
	private static EntityManagerFactory emf;
	private static Logger logger=Logger.getLogger(EntityManagerHelper.class.getName());
	
		static{
			if(emf == null){
				emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
				EntityManager em = emf.createEntityManager();
				EntityManagerImpl impl = (EntityManagerImpl)em.getDelegate();
					try{
						Connection conn = (Connection) impl.getConnection();
						CallableStatement call = conn.prepareCall("begin "
								+ "global_vars.g_lctn_id :='281'; "
								+ "global_vars.g_user_id :='tester'; "
								+ "global_vars.g_obj_id  :='framework'; "
								+ "end;");
						call.execute();
						call.close();
					}catch(SQLException sqlex){
						logger.error("",sqlex);
				}
			}
				
		}
		public static EntityManager getEntityManager(){
			EntityManager entityManager = emf.createEntityManager();
			EntityManagerImpl impl = (EntityManagerImpl) entityManager.getDelegate();
			try{
				Connection conn = (Connection) impl.getConnection();
				CallableStatement call = conn.prepareCall("begin "
						+ "global_vars.g_lctn_id :='281'; "
						+ "global_vars.g_user_id :='tester'; "
						+ "global_vars.g_obj_id  :='framework'; "
						+ "end;");
				call.execute();
				call.close();
			}catch(SQLException sql){
				logger.error("",sql);
			}
			return entityManager;
		}
}
