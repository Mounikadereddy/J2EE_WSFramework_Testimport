package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_ACNT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntAcntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long acntId;
	private Long ptcpntId;

    public PtcpntAcntPK() {
    }

	@Column(name="ACNT_ID", nullable=false, precision=15)
	public Long getAcntId() {
		return this.acntId;
	}
	public void setAcntId(Long acntId) {
		this.acntId = acntId;
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
		if (!(other instanceof PtcpntAcntPK)) {
			return false;
		}
		PtcpntAcntPK castOther = (PtcpntAcntPK)other;
		return new EqualsBuilder()
			.append(this.getAcntId(), castOther.getAcntId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAcntId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("acntId", getAcntId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}