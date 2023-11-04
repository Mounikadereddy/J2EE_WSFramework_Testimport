package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_SCRTY_PREV_PSWRD database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonScrtyPrevPswrdPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Date endDt;
	private Long lctnId;
	private Long ptcpntId;

    public PersonScrtyPrevPswrdPK() {
    }

	@Column(name="END_DT", nullable=false, length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
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
		if (!(other instanceof PersonScrtyPrevPswrdPK)) {
			return false;
		}
		PersonScrtyPrevPswrdPK castOther = (PersonScrtyPrevPswrdPK)other;
		return new EqualsBuilder()
			.append(this.getEndDt(), castOther.getEndDt())
			.append(this.getLctnId(), castOther.getLctnId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getEndDt())
			.append(getLctnId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("endDt", getEndDt())
			.append("lctnId", getLctnId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}