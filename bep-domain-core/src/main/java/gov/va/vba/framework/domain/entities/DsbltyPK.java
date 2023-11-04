package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the DSBLTY database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class DsbltyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp dsbltyDt;
	private Long dsbltyId;

    public DsbltyPK() {
    }

	@Column(name="DSBLTY_DT", nullable=false, length=7)
	public java.sql.Timestamp getDsbltyDt() {
		return this.dsbltyDt;
	}
	public void setDsbltyDt(java.sql.Timestamp dsbltyDt) {
		this.dsbltyDt = dsbltyDt;
	}

	@Column(name="DSBLTY_ID", nullable=false, precision=15)
	public Long getDsbltyId() {
		return this.dsbltyId;
	}
	public void setDsbltyId(Long dsbltyId) {
		this.dsbltyId = dsbltyId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DsbltyPK)) {
			return false;
		}
		DsbltyPK castOther = (DsbltyPK)other;
		return new EqualsBuilder()
			.append(this.getDsbltyDt(), castOther.getDsbltyDt())
			.append(this.getDsbltyId(), castOther.getDsbltyId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDsbltyDt())
			.append(getDsbltyId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("dsbltyDt", getDsbltyDt())
			.append("dsbltyId", getDsbltyId())
			.toString();
	}
}