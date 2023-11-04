package gov.va.vba.framework.domain.entities;

import gov.va.vba.framework.logging.Logger;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PTCPNT_PHONE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntPhonePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.util.Date efctvDt;
	private String phoneTypeNm;
	private Long ptcpntId;
	private static Logger logger=Logger.getLogger(PtcpntPhonePK.class.getName());

    public PtcpntPhonePK() {
    }
    
    public PtcpntPhonePK(String ptcpntId){
    	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    	String[] data = ptcpntId.split("=");
    	String date = data[1].substring(0, data[1].indexOf(","));
    	String phoneType = data[2].substring(0, data[2].indexOf(","));
    	String id = data[3].substring(0, data[3].indexOf("]"));
    	
    	try{
    		this.efctvDt = new java.util.Date(dateFormatter.parse(date).getTime());
    	}catch(ParseException pe){
    		logger.error("",pe);
    	}
    	this.phoneTypeNm = phoneType;
    	this.ptcpntId = new Long(id);
 
    }
    public PtcpntPhonePK(Long ptcpntId, java.util.Date effectiveDate, String typeName){
    	this.efctvDt = effectiveDate;
    	this.phoneTypeNm = typeName;
    	this.ptcpntId = ptcpntId;
    }

	@Column(name="EFCTV_DT", nullable=false, length=7)
	public java.util.Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(java.util.Date efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Column(name="PHONE_TYPE_NM", nullable=false, length=50)
	public String getPhoneTypeNm() {
		return this.phoneTypeNm;
	}
	public void setPhoneTypeNm(String phoneTypeNm) {
		this.phoneTypeNm = phoneTypeNm;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntPhonePK)) {
			return false;
		}
		PtcpntPhonePK castOther = (PtcpntPhonePK)other;
		return new EqualsBuilder()
			.append(this.getEfctvDt(), castOther.getEfctvDt())
			.append(this.getPhoneTypeNm(), castOther.getPhoneTypeNm())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getEfctvDt())
			.append(getPhoneTypeNm())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("efctvDt", getEfctvDt())
			.append("phoneTypeNm", getPhoneTypeNm())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}