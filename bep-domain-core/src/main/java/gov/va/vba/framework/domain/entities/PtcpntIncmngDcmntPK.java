package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_INCMNG_DCMNT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntIncmngDcmntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long incmngDcmntId;
	private Long ptcpntId;
	private String ptcpntIncmngDcmntTypeNm;

    public PtcpntIncmngDcmntPK() {
    }

	@Column(name="INCMNG_DCMNT_ID", nullable=false, precision=15)
	public Long getIncmngDcmntId() {
		return this.incmngDcmntId;
	}
	public void setIncmngDcmntId(Long incmngDcmntId) {
		this.incmngDcmntId = incmngDcmntId;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Column(name="PTCPNT_INCMNG_DCMNT_TYPE_NM", nullable=false, length=50)
	public String getPtcpntIncmngDcmntTypeNm() {
		return this.ptcpntIncmngDcmntTypeNm;
	}
	public void setPtcpntIncmngDcmntTypeNm(String ptcpntIncmngDcmntTypeNm) {
		this.ptcpntIncmngDcmntTypeNm = ptcpntIncmngDcmntTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntIncmngDcmntPK)) {
			return false;
		}
		PtcpntIncmngDcmntPK castOther = (PtcpntIncmngDcmntPK)other;
		return new EqualsBuilder()
			.append(this.getIncmngDcmntId(), castOther.getIncmngDcmntId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntIncmngDcmntTypeNm(), castOther.getPtcpntIncmngDcmntTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getIncmngDcmntId())
			.append(getPtcpntId())
			.append(getPtcpntIncmngDcmntTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("incmngDcmntId", getIncmngDcmntId())
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntIncmngDcmntTypeNm", getPtcpntIncmngDcmntTypeNm())
			.toString();
	}
}