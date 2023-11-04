package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_SPECL_STATUS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonSpeclStatusPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String personSpeclStatusTypeNm;
	private Long ptcpntId;

    public PersonSpeclStatusPK() {
    }

	@Column(name="PERSON_SPECL_STATUS_TYPE_NM", nullable=false, length=50)
	public String getPersonSpeclStatusTypeNm() {
		return this.personSpeclStatusTypeNm;
	}
	public void setPersonSpeclStatusTypeNm(String personSpeclStatusTypeNm) {
		this.personSpeclStatusTypeNm = personSpeclStatusTypeNm;
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
		if (!(other instanceof PersonSpeclStatusPK)) {
			return false;
		}
		PersonSpeclStatusPK castOther = (PersonSpeclStatusPK)other;
		return new EqualsBuilder()
			.append(this.getPersonSpeclStatusTypeNm(), castOther.getPersonSpeclStatusTypeNm())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPersonSpeclStatusTypeNm())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("personSpeclStatusTypeNm", getPersonSpeclStatusTypeNm())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}