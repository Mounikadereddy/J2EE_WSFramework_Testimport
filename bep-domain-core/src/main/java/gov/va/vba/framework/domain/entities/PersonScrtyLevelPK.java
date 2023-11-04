package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_SCRTY_LEVEL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonScrtyLevelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String accessScrtyPurposInd;
	private Long ptcpntId;

    public PersonScrtyLevelPK() {
    }

	@Column(name="ACCESS_SCRTY_PURPOS_IND", nullable=false, length=1)
	public String getAccessScrtyPurposInd() {
		return this.accessScrtyPurposInd;
	}
	public void setAccessScrtyPurposInd(String accessScrtyPurposInd) {
		this.accessScrtyPurposInd = accessScrtyPurposInd;
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
		if (!(other instanceof PersonScrtyLevelPK)) {
			return false;
		}
		PersonScrtyLevelPK castOther = (PersonScrtyLevelPK)other;
		return new EqualsBuilder()
			.append(this.getAccessScrtyPurposInd(), castOther.getAccessScrtyPurposInd())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAccessScrtyPurposInd())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("accessScrtyPurposInd", getAccessScrtyPurposInd())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}