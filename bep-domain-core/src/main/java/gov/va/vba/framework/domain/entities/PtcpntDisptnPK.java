package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_DISPTN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntDisptnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String disptnRoleTypeCd;
	private Long ptcpntDisptnSeqNbr;
	private Long ptcpntId;

    public PtcpntDisptnPK() {
    }

	@Column(name="DISPTN_ROLE_TYPE_CD", nullable=false, length=12)
	public String getDisptnRoleTypeCd() {
		return this.disptnRoleTypeCd;
	}
	public void setDisptnRoleTypeCd(String disptnRoleTypeCd) {
		this.disptnRoleTypeCd = disptnRoleTypeCd;
	}

	@Column(name="PTCPNT_DISPTN_SEQ_NBR", nullable=false, precision=15)
	public Long getPtcpntDisptnSeqNbr() {
		return this.ptcpntDisptnSeqNbr;
	}
	public void setPtcpntDisptnSeqNbr(Long ptcpntDisptnSeqNbr) {
		this.ptcpntDisptnSeqNbr = ptcpntDisptnSeqNbr;
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
		if (!(other instanceof PtcpntDisptnPK)) {
			return false;
		}
		PtcpntDisptnPK castOther = (PtcpntDisptnPK)other;
		return new EqualsBuilder()
			.append(this.getDisptnRoleTypeCd(), castOther.getDisptnRoleTypeCd())
			.append(this.getPtcpntDisptnSeqNbr(), castOther.getPtcpntDisptnSeqNbr())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDisptnRoleTypeCd())
			.append(getPtcpntDisptnSeqNbr())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("disptnRoleTypeCd", getDisptnRoleTypeCd())
			.append("ptcpntDisptnSeqNbr", getPtcpntDisptnSeqNbr())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}