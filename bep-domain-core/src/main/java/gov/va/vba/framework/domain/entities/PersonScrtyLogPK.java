package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_SCRTY_LOG database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonScrtyLogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private Long ptcpntId;

    public PersonScrtyLogPK() {
    }

	@Column(name="LCTN_ID", nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
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
		if (!(other instanceof PersonScrtyLogPK)) {
			return false;
		}
		PersonScrtyLogPK castOther = (PersonScrtyLogPK)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}