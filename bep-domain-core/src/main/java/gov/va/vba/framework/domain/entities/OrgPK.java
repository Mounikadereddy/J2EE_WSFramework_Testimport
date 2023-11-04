package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the ORG database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class OrgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;

    public OrgPK() {
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
		if (!(other instanceof OrgPK)) {
			return false;
		}
		OrgPK castOther = (OrgPK)other;
		return new EqualsBuilder()
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntId())
			.append(getPtcpntId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntId", getPtcpntId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}