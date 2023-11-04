package gov.va.vba.framework.services.ejb;

import gov.va.vba.framework.auditing.LegacyEJBAuditer;
import gov.va.vba.framework.domain.entities.jaxb.ALIASNAME;
import gov.va.vba.framework.domain.entities.jaxb.EDUCATION;
import gov.va.vba.framework.domain.entities.jaxb.MAILINGADDRESS;
import gov.va.vba.framework.domain.entities.jaxb.ObjectFactory;
import gov.va.vba.framework.domain.entities.jaxb.PAGE;
import gov.va.vba.framework.domain.entities.jaxb.PERSONNEL;
import gov.va.vba.framework.domain.entities.jaxb.PERSONNELST;
import gov.va.vba.framework.domain.entities.jaxb.PERSONV;
import gov.va.vba.framework.domain.entities.jaxb.TELEPHONENUMBER;
import gov.va.vba.framework.domain.entities.jaxb.VADIR;
import gov.va.vba.framework.logging.Logger;
import gov.va.vba.framework.services.VadirSession;
import gov.va.vba.framework.services.VadirSessionLocal;
import gov.va.vba.framework.services.VadirSessionRemote;

import java.io.File;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.jdbc.OracleTypes;

@Stateless(mappedName="VadirSessionEJB")
@Local(VadirSessionLocal.class)
@Remote(VadirSessionRemote.class)
public class VadirSessionEJB implements VadirSession {

	private static Logger logger=Logger.getLogger(VadirSessionEJB.class);
	@Override
	public String getVadirData(String vonAppUser, String vonAppTerm, String vaIdIn) {

		new LegacyEJBAuditer().audit(VadirSessionEJB.class.getName(), "getVadirData");
		ObjectFactory of = new ObjectFactory();
		CallableStatement callableStatement;
		String GetPN = "{call ?:= vonapp_temp.GetPN(?,?,?)}";
		String GetPNL = "{call ?:= vonapp_temp.GetPNL(?,?,?)}";
		String GetMA = "{call ?:= vonapp_temp.GetMA(?,?,?)}";
		String GetTNUM  = "{call ?:= vonapp_temp.GetTNUM(?,?,?)}";
		String GetAN = "{call ?:= vonapp_temp.GetAN(?,?,?)}";
		String GetEDU = "{call ?:= vonapp_temp.GetEDU(?,?,?)}";
		String GetPG  = "{call ?:= vonapp_temp.GetPG(?,?,?,?,?,?)}";
		String GetPNLST = "{call ?:= vonapp_temp.GetPNLST(?,?,?,?,?,?)}";
		String orgCd = "";
		String pnlCatCd = "";
		String pnlSegId = "";
		ResultSet pnResultSet;
		ResultSet pnlResultSet;
		ResultSet maResultSet;
		ResultSet tnumResultSet;
		ResultSet anResultSet;
		ResultSet eduResultSet;
		ResultSet pgResultSet;
		ResultSet pnlstResultSet;
		Connection con;
		String reader = null;
		//File file = new File("vadir.xml");
		VADIR vadir = of.createVADIR();
		con = setVadirConnection();
		ArrayList<PERSONNEL>tempPnl = new ArrayList<PERSONNEL>();
 
		try{
			
			callableStatement = con.prepareCall(GetPN);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			pnResultSet = (ResultSet)callableStatement.getObject(1);
			populatePnData(of, pnResultSet, vadir);
			
			callableStatement = con.prepareCall(GetPNL);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			pnlResultSet = (ResultSet)callableStatement.getObject(1);
			tempPnl = populatePnlData(of, pnlResultSet);
			populatePnlObject(of, tempPnl, vadir);
			
			callableStatement = con.prepareCall(GetMA);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			maResultSet = (ResultSet)callableStatement.getObject(1);
			populateMaData(of, maResultSet, vadir);
			
			callableStatement = con.prepareCall(GetTNUM);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			tnumResultSet = (ResultSet)callableStatement.getObject(1);
			populateTnumData(of, tnumResultSet, vadir);
			
			callableStatement = con.prepareCall(GetAN);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			anResultSet = (ResultSet)callableStatement.getObject(1);
			populateAnData(of, anResultSet, vadir);
			
			callableStatement = con.prepareCall(GetEDU);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement);
			eduResultSet = (ResultSet)callableStatement.getObject(1);
			populateEduData(of, eduResultSet, vadir);
			
			for(PERSONNEL pnl :tempPnl){
				if(null != pnl.getORGANIZATIONCODE() && !pnl.getORGANIZATIONCODE().isEmpty()){
					orgCd = pnl.getORGANIZATIONCODE();
				}
				if(null != pnl.getCATEGORYCODE()&& !pnl.getCATEGORYCODE().isEmpty()){
					pnlCatCd = pnl.getCATEGORYCODE();
				}
				if(null != pnl.getSEGMENTID() && !pnl.getSEGMENTID().isEmpty()){
				pnlSegId = pnl.getSEGMENTID();
				}
				break;
			}
			
			callableStatement = con.prepareCall(GetPNLST);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement, orgCd, pnlCatCd, pnlSegId);
			pnlstResultSet = (ResultSet)callableStatement.getObject(1);
			populatePnlstData(of, pnlstResultSet, vadir);
			
			callableStatement = con.prepareCall(GetPG);
			setParametersAndExecute(vonAppUser, vonAppTerm, vaIdIn,
					callableStatement, orgCd, pnlCatCd, pnlSegId);
			pgResultSet = (ResultSet)callableStatement.getObject(1);
			populatePgData(of, pgResultSet, vadir);
			
			reader = convertXmlToString(vadir);
			//file = convertXmlToFile(vadir);
			
			con.close();
			pnResultSet.close();
			pnlResultSet.close();
			maResultSet.close();
			tnumResultSet.close();
			anResultSet.close();
			eduResultSet.close();
			pgResultSet.close();
			pnlstResultSet.close();
			
		}catch(SQLException sqlex){
			logger.error("",sqlex);
		}catch(JAXBException jbex){
			logger.error("",jbex);
		}
			
		return reader;
	}
	
	/**
	 * Creates a database connection.
	 */
	private Connection setVadirConnection() {
		
		//Hashtable<String, String> ht;
		InitialContext ctx;
		DataSource ds;
		Connection con = null;
		String dsn = "jdbc/framework/vadir/admin";

		try{
//			ht = new Hashtable<String, String>();			
//			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");  
//			ht.put(Context.PROVIDER_URL, "t3://localhost:7002");
//		    ctx = new InitialContext(ht);
		    ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(dsn);
			con = ds.getConnection();
			
		}catch(Exception excp)
		{
			logger.error("Failed",excp);
		}
		
		return con;
	}
	
	private void populatePgData(ObjectFactory of, ResultSet pgResultSet, VADIR vadir)
			throws SQLException{
		while(pgResultSet.next()){
			PAGE page = of.createPAGE();
			if(null != pgResultSet.getString("PG_SEG_ID")){
				page.setSEGMENTID(pgResultSet.getString("PG_SEG_ID"));
			}
			if(null != pgResultSet.getString("PAY_PLN_CD")){
				page.setPAYPLANCODE(pgResultSet.getString("PAY_PLN_CD"));
			}
			if(null != pgResultSet.getString("PG_CD")){
				page.setPAGECD(pgResultSet.getString("PG_CD"));
			}
			vadir.getPAGE().add(page);
		}
	}

	private void populatePnlstData(ObjectFactory of, ResultSet pnlstResultSet, VADIR vadir)
			throws SQLException{
		while(pnlstResultSet.next()){
			PERSONNELST personnelst = of.createPERSONNELST();
			if(null != pnlstResultSet.getString("CHAR_SVC_CD")){
				personnelst.setCHARSERVICECODE(pnlstResultSet.getString("CHAR_SVC_CD"));
			}
			vadir.getPERSONNELST().add(personnelst);
		}
	}

	private void populateEduData(ObjectFactory of, ResultSet eduResultSet, VADIR vadir)
			throws SQLException{
		while(eduResultSet.next()){
			EDUCATION education = of.createEDUCATION();
			if(null != eduResultSet.getString("EDU_LVL_CD")){
				education.setEDUCATIONLEVELCODE(eduResultSet.getString("EDU_LVL_CD"));
			}
			if(null != eduResultSet.getDate("EDU_LVL_DT")){
				education.setEDUCATIONLEVELDATE(Date2XMLDate(eduResultSet.getDate("EDU_LVL_DT")));
			}
			vadir.getEDUCATION().add(education);
		}
	}

	private void populateAnData(ObjectFactory of, ResultSet anResultSet, VADIR vadir)
			throws SQLException{
		while(anResultSet.next()){
			ALIASNAME aliasname = of.createALIASNAME();
			if(null != anResultSet.getString("PRIOR_PN_1ST_NM")){
				aliasname.setPRIORFIRSTNAME(anResultSet.getString("PRIOR_PN_1ST_NM"));
			}
			if(null != anResultSet.getString("PRIOR_PN_LST_NM")){
				aliasname.setPRIORLASTNAME(anResultSet.getString("PRIOR_PN_LST_NM"));
			}
			vadir.getALIASNAME().add(aliasname);
		}
	}

	private void populateTnumData(ObjectFactory of, ResultSet tnumResultSet, VADIR vadir)
			throws SQLException{
		while(tnumResultSet.next()){
			TELEPHONENUMBER telephonenumber = of.createTELEPHONENUMBER();
			if(null != tnumResultSet.getString("PHONE_NUM")){
				telephonenumber.setPHONENUMBER(tnumResultSet.getString("PHONE_NUM"));
			}
			if(null != tnumResultSet.getString("TNUM_TYP_CD")){
				telephonenumber.setTELEPHONENUMERTYPECODE(tnumResultSet.getString("TNUM_TYP_CD"));
			}
			vadir.getTELEPHONENUMBER().add(telephonenumber);
		}
	}
	
	private void populateMaData(ObjectFactory of, ResultSet maResultSet, VADIR vadir)
		throws SQLException{
		while(maResultSet.next()){
			MAILINGADDRESS mailingAddress = of.createMAILINGADDRESS();
			if(null != maResultSet.getString("MA_LN1_TX")){
				mailingAddress.setADDRESSLINEONE(maResultSet.getString("MA_LN1_TX"));
			}
			if(null != maResultSet.getString("MA_LN2_TX")){
				mailingAddress.setADDRESSLINETWO(maResultSet.getString("MA_LN2_TX"));
			}
			if(null != maResultSet.getString("MA_CITY_NM")){
				mailingAddress.setCITY(maResultSet.getString("MA_CITY_NM"));
			}
			if(null != maResultSet.getString("MA_ST_CD")){
				mailingAddress.setSTATECODE(maResultSet.getString("MA_ST_CD"));
			}
			if(null != maResultSet.getString("MA_PR_ZIP_CD")){
				mailingAddress.setZIPCODE(maResultSet.getString("MA_PR_ZIP_CD"));
			}
			if(null != maResultSet.getString("MA_PR_ZIPX_CD")){
				mailingAddress.setZIPCODEEXT(maResultSet.getString("MA_PR_ZIPX_CD"));
			}
			vadir.getMAILINGADDRESS().add(mailingAddress);
		}
	}

	private ArrayList<PERSONNEL> populatePnlData(ObjectFactory of, ResultSet pnlResultSet)
			throws SQLException {
		ArrayList<PERSONNEL> pnls = new ArrayList<PERSONNEL>();
		while(pnlResultSet.next()){
			PERSONNEL pnl = of.createPERSONNEL();
			if(null != pnlResultSet.getString("ORG_CD")){
				pnl.setORGANIZATIONCODE(pnlResultSet.getString("ORG_CD"));
			}
			if(null != pnlResultSet.getString("PNL_CAT_CD")){
				pnl.setCATEGORYCODE(pnlResultSet.getString("PNL_CAT_CD"));
			}
			if(null != pnlResultSet.getString("PNL_SEG_ID")){
				pnl.setSEGMENTID(pnlResultSet.getString("PNL_SEG_ID"));
			}
			if(null != pnlResultSet.getString("SVC_CD")){
				pnl.setSERVICECODE(pnlResultSet.getString("SVC_CD"));
			}
			if(null != pnlResultSet.getDate("PNL_BGN_DT")){
				pnl.setBEGINDATE(Date2XMLDate(pnlResultSet.getDate("PNL_BGN_DT")));
			}
			if(null != pnlResultSet.getDate("PNL_PE_DT")){
				pnl.setPEDATE(Date2XMLDate(pnlResultSet.getDate("PNL_PE_DT")));
			}
			if(null != pnlResultSet.getDate("PNL_TERM_DT")){
				pnl.setTERMDATE(Date2XMLDate(pnlResultSet.getDate("PNL_TERM_DT")));
			}
			pnls.add(pnl);
		}
		return pnls;
	}
	private void populatePnlObject(ObjectFactory of, ArrayList<PERSONNEL> pnls, VADIR vadir)
	throws SQLException{
		for( PERSONNEL pnl : pnls){
			vadir.getPERSONNEL().add(pnl);
		}
	}

	private void populatePnData(ObjectFactory of, ResultSet pnResultSet, VADIR vadir) 
	throws SQLException{
		while(pnResultSet.next()){
			PERSONV personv = of.createPERSONV();
			if(null != pnResultSet.getString("VA_ID")){
				personv.setVAID(pnResultSet.getString("VA_ID"));
			}
			if(null != pnResultSet.getString("PN_1ST_NM")){
				personv.setFIRSTNAME(pnResultSet.getString("PN_1ST_NM"));
			}
			if(null != pnResultSet.getString("PN_MID_NM")){
				personv.setMIDDLENAME(pnResultSet.getString("PN_MID_NM"));
			}
			if(null != pnResultSet.getString("PN_LST_NM")){
				personv.setLASTNAME(pnResultSet.getString("PN_LST_NM"));
			}
			if(null != pnResultSet.getString("PN_ID")){
				personv.setPNID(pnResultSet.getString("PN_ID"));
			}
			if(null != pnResultSet.getString("PN_SEX_CD")){
				personv.setSEX(pnResultSet.getString("PN_SEX_CD"));
			}
			if(null != pnResultSet.getDate("PN_BRTH_DT")){
				personv.setBIRTHDATE(Date2XMLDate(pnResultSet.getDate("PN_BRTH_DT")));
			}
			if(null != pnResultSet.getString("MRTL_STAT_CD")){
				personv.setMARITALSTATUS(pnResultSet.getString("MRTL_STAT_CD"));
			}
			if(null != pnResultSet.getDate("PN_DTH_DT")){
				personv.setDEATHDATE(Date2XMLDate(pnResultSet.getDate("PN_DTH_DT")));
			}
			vadir.getPERSONV().add(personv);
		}
	}

	
	private String convertXmlToString(VADIR vadir) throws JAXBException, PropertyException {
		StringWriter writer = new StringWriter();
		String reader; 
		
		JAXBContext jaxb = JAXBContext.newInstance("gov.va.vba.framework.domain.entities.jaxb");
		Marshaller marshaller = jaxb.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(vadir, writer);
		
		reader = writer.toString();
		return reader;
	}
	
	
	private XMLGregorianCalendar Date2XMLDate(Date d){
		XMLGregorianCalendar x = null;
		if(null != d){
			try{
				Calendar c = new GregorianCalendar(); 
				c.setTime(d);
				int month = c.get(Calendar.MONTH)+1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				int year = c.get(Calendar.YEAR);
				DatatypeFactory df = DatatypeFactory.newInstance();
				x = df.newXMLGregorianCalendarDate(year, month, day, c.getTimeZone().getOffset(d.getTime())/(60*60*1000));
			}catch(DatatypeConfigurationException dcex){
				logger.error("",dcex);
			}
		}
	return x;
	} 
	
	protected void setParametersAndExecute(String vaIdIn,
			CallableStatement callableStatement) throws SQLException {
		callableStatement.setString(1, vaIdIn);
		
		callableStatement.executeQuery();
	}
	
	protected void setParametersAndExecute(String vonAppUser,
			String vonAppTerm, String vaIdIn,
			CallableStatement callableStatement) throws SQLException {
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.setString(2, vonAppUser);
		callableStatement.setString(3, vonAppTerm);
		callableStatement.setString(4, vaIdIn);
		
		callableStatement.executeQuery();
	}
	
	protected void setParametersAndExecute(String vonAppUser,
			String vonAppTerm, String vaIdIn, CallableStatement callableStatement, 
			String orgCdIn, String pnlCatCd, String pnlSegId) throws SQLException {
		callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
		callableStatement.setString(2, vonAppUser);
		callableStatement.setString(3, vonAppTerm);
		callableStatement.setString(4, vaIdIn);
		callableStatement.setString(5, orgCdIn);
		callableStatement.setString(6, pnlCatCd);
		callableStatement.setString(7, pnlSegId);
		
		callableStatement.execute();
	}

}
