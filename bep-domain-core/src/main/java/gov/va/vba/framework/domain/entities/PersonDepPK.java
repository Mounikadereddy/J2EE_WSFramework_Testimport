package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_DEP database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonDepPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Integer depNbr;
	private Long ptcpntId;

    public PersonDepPK() {
    }

	@Column(name="DEP_NBR", nullable=false, precision=2)
	public Integer getDepNbr() {
		return this.depNbr;
	}
	public void setDepNbr(Integer depNbr) {
		this.depNbr = depNbr;
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
		if (!(other instanceof PersonDepPK)) {
			return false;
		}
		PersonDepPK castOther = (PersonDepPK)other;
		return new EqualsBuilder()
			.append(this.getDepNbr(), castOther.getDepNbr())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDepNbr())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("depNbr", getDepNbr())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}