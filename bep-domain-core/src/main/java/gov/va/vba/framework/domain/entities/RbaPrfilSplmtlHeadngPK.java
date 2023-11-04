package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the RBA_PRFIL_SPLMTL_HEADNG database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class RbaPrfilSplmtlHeadngPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp prfilDt;
	private Long ptcpntVetId;
	private String splmtlHeadngTypeCd;

    public RbaPrfilSplmtlHeadngPK() {
    }

	@Column(name="PRFIL_DT", nullable=false, length=7)
	public java.sql.Timestamp getPrfilDt() {
		return this.prfilDt;
	}
	public void setPrfilDt(java.sql.Timestamp prfilDt) {
		this.prfilDt = prfilDt;
	}

	@Column(name="PTCPNT_VET_ID", nullable=false, precision=15)
	public Long getPtcpntVetId() {
		return this.ptcpntVetId;
	}
	public void setPtcpntVetId(Long ptcpntVetId) {
		this.ptcpntVetId = ptcpntVetId;
	}

	@Column(name="SPLMTL_HEADNG_TYPE_CD", nullable=false, length=12)
	public String getSplmtlHeadngTypeCd() {
		return this.splmtlHeadngTypeCd;
	}
	public void setSplmtlHeadngTypeCd(String splmtlHeadngTypeCd) {
		this.splmtlHeadngTypeCd = splmtlHeadngTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RbaPrfilSplmtlHeadngPK)) {
			return false;
		}
		RbaPrfilSplmtlHeadngPK castOther = (RbaPrfilSplmtlHeadngPK)other;
		return new EqualsBuilder()
			.append(this.getPrfilDt(), castOther.getPrfilDt())
			.append(this.getPtcpntVetId(), castOther.getPtcpntVetId())
			.append(this.getSplmtlHeadngTypeCd(), castOther.getSplmtlHeadngTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPrfilDt())
			.append(getPtcpntVetId())
			.append(getSplmtlHeadngTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("prfilDt", getPrfilDt())
			.append("ptcpntVetId", getPtcpntVetId())
			.append("splmtlHeadngTypeCd", getSplmtlHeadngTypeCd())
			.toString();
	}
}