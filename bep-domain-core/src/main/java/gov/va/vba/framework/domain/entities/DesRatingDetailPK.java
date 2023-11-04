package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the DES_RATING_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class DesRatingDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp prfilDt;
	private Long ptcpntVetId;

    public DesRatingDetailPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DesRatingDetailPK)) {
			return false;
		}
		DesRatingDetailPK castOther = (DesRatingDetailPK)other;
		return new EqualsBuilder()
			.append(this.getPrfilDt(), castOther.getPrfilDt())
			.append(this.getPtcpntVetId(), castOther.getPtcpntVetId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPrfilDt())
			.append(getPtcpntVetId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("prfilDt", getPrfilDt())
			.append("ptcpntVetId", getPtcpntVetId())
			.toString();
	}
}