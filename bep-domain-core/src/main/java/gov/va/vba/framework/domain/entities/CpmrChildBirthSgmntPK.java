package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the CPMR_CHILD_BIRTH_SGMNT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class CpmrChildBirthSgmntPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long childSgmntNbr;
	private Long cpmrBasicSgmntId;

    public CpmrChildBirthSgmntPK() {
    }

	@Column(name="CHILD_SGMNT_NBR", nullable=false, precision=15)
	public Long getChildSgmntNbr() {
		return this.childSgmntNbr;
	}
	public void setChildSgmntNbr(Long childSgmntNbr) {
		this.childSgmntNbr = childSgmntNbr;
	}

	@Column(name="CPMR_BASIC_SGMNT_ID", nullable=false, precision=15)
	public Long getCpmrBasicSgmntId() {
		return this.cpmrBasicSgmntId;
	}
	public void setCpmrBasicSgmntId(Long cpmrBasicSgmntId) {
		this.cpmrBasicSgmntId = cpmrBasicSgmntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CpmrChildBirthSgmntPK)) {
			return false;
		}
		CpmrChildBirthSgmntPK castOther = (CpmrChildBirthSgmntPK)other;
		return new EqualsBuilder()
			.append(this.getChildSgmntNbr(), castOther.getChildSgmntNbr())
			.append(this.getCpmrBasicSgmntId(), castOther.getCpmrBasicSgmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getChildSgmntNbr())
			.append(getCpmrBasicSgmntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("childSgmntNbr", getChildSgmntNbr())
			.append("cpmrBasicSgmntId", getCpmrBasicSgmntId())
			.toString();
	}
}