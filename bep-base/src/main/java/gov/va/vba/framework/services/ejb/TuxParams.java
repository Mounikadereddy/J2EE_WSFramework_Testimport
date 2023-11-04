package gov.va.vba.framework.services.ejb;

import java.io.Serializable;
import java.util.Map;

import weblogic.wtc.jatmi.ApplicationToMonitorInterface;

import gov.va.vba.framework.domain.vo.ServiceVO.TuxedoService;

public class TuxParams implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String data;
	private TuxedoService tuxedoService;
	private Map<Integer, String> extensions;
	private int transactionType;
	private String tuxedoServiceName;
	
	
	public TuxParams()
	{
		this(null,null,null);
	}
	
	public TuxParams(TuxedoService tuxedoService, String data, Map extensions)
	{
		this(tuxedoService, data, ApplicationToMonitorInterface.TPNOTRAN, extensions);
	}
	
	public TuxParams(TuxedoService tuxedoService, String data, int transactiontype, Map extensions)
	{
		this.tuxedoService=tuxedoService;
		this.data=data;
		this.extensions=extensions;
		this.transactionType=transactiontype;
	}
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public TuxedoService getTuxedoService() {
		return tuxedoService;
	}
	public void setTuxedoService(TuxedoService tuxedoService) {
		this.tuxedoService = tuxedoService;
	}
	public Map getExtensions() {
		return extensions;
	}
	public void setExtensions(Map extensions) {
		this.extensions = extensions;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public void setTuxedoServiceName(String tuxedoServiceName) {
		this.tuxedoServiceName = tuxedoServiceName;
	}

	public String getTuxedoServiceName() {
		return tuxedoServiceName;
	}
	
	public String getActualServiceName()
	{
		String actualServiceName="";
		if (tuxedoService!=null)
			actualServiceName=tuxedoService.toString();
		else
			actualServiceName=tuxedoServiceName;
		return actualServiceName;
	}

}
