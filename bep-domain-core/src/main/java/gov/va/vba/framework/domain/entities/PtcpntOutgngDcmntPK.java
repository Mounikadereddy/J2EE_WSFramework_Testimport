package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_OUTGNG_DCMNT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntOutgngDcmntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long outgngDcmntId;
	private Long ptcpntId;
	private String ptcpntOutgngDcmntTypeNm;

    public PtcpntOutgngDcmntPK() {
    }

	@Column(name="OUTGNG_DCMNT_ID", nullable=false, precision=15)
	public Long getOutgngDcmntId() {
		return this.outgngDcmntId;
	}
	public void setOutgngDcmntId(Long outgngDcmntId) {
		this.outgngDcmntId = outgngDcmntId;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="PTCPNT_OUTGNG_DCMNT_TYPE_NM", nullable=false, length=50)
	public String getPtcpntOutgngDcmntTypeNm() {
		return this.ptcpntOutgngDcmntTypeNm;
	}
	public void setPtcpntOutgngDcmntTypeNm(String ptcpntOutgngDcmntTypeNm) {
		this.ptcpntOutgngDcmntTypeNm = ptcpntOutgngDcmntTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntOutgngDcmntPK)) {
			return false;
		}
		PtcpntOutgngDcmntPK castOther = (PtcpntOutgngDcmntPK)other;
		return new EqualsBuilder()
			.append(this.getOutgngDcmntId(), castOther.getOutgngDcmntId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntOutgngDcmntTypeNm(), castOther.getPtcpntOutgngDcmntTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOutgngDcmntId())
			.append(getPtcpntId())
			.append(getPtcpntOutgngDcmntTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("outgngDcmntId", getOutgngDcmntId())
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntOutgngDcmntTypeNm", getPtcpntOutgngDcmntTypeNm())
			.toString();
	}
}