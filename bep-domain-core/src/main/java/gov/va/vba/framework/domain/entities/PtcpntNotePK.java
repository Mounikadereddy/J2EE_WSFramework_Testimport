package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PTCPNT_NOTE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntNotePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Date dt;
	private Long ptcpntId;

    public PtcpntNotePK() {
    }

	@Column(name="DT", nullable=false, length=7)
	public Date getDt() {
		return this.dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
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
		if (!(other instanceof PtcpntNotePK)) {
			return false;
		}
		PtcpntNotePK castOther = (PtcpntNotePK)other;
		return new EqualsBuilder()
			.append(this.getDt(), castOther.getDt())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDt())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("dt", getDt())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}