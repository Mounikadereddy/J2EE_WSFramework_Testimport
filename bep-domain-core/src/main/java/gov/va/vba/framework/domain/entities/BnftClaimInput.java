package gov.va.vba.framework.domain.entities;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class BnftClaimInput {

	ArrayList<String> ptcpntIds = new ArrayList<String>();
	ArrayList<String> roIds = new ArrayList<String>();
	Long id;
	
	public ArrayList<String> getPtcpntIds() {
		return ptcpntIds;
	}
	public void setPtcpntIds(ArrayList<String> ptcpntIds) {
		this.ptcpntIds = ptcpntIds;
	}
	public ArrayList<String> getRoIds() {
		return roIds;
	}
	public void setRoIds(ArrayList<String> roIds) {
		this.roIds = roIds;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
}
