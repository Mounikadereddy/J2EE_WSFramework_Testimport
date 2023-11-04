package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlTransient;

@Embeddable
public class PhonePK implements Serializable {

	@XmlTransient public Long ptcpntId;
	@XmlTransient public String phnType;
	private Date effectiveDate;
	
	public PhonePK() {
	}

	@Column(name="PTCPNT_ID", nullable = false)
	public Long getPtcpntId() {
		return ptcpntId;
	}

	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="PHONE_TYPE_NM", nullable = false)
	public String getPhnType() {
		return phnType;
	}

	public void setPhnType(String phnType) {
		this.phnType = phnType;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PhonePK) {
			PhonePK pk = (PhonePK)obj;
			if(pk.getPtcpntId().equals(this.getPtcpntId()) && 
					pk.getPhnType().equals(this.getPhnType()) && 
					pk.getEffectiveDate().equals(this.getEffectiveDate())) {
				return true;
			}
		}
		return false;
	}
	
	/*
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	*/

	@Column(name="EFCTV_DT")
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}
