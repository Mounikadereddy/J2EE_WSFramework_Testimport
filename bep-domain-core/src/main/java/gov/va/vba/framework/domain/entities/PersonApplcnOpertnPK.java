package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the PERSON_APPLCN_OPERTN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonApplcnOpertnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnId;
	private Long lctnId;
	private Long opertnRuleId;
	private Long ptcpntId;

    public PersonApplcnOpertnPK() {
    }

	@Column(name="APPLCN_ID", nullable=false, precision=15)
	public Long getApplcnId() {
		return this.applcnId;
	}
	public void setApplcnId(Long applcnId) {
		this.applcnId = applcnId;
	}

	@Column(name="LCTN_ID", nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Column(name="OPERTN_RULE_ID", nullable=false, precision=15)
	public Long getOpertnRuleId() {
		return this.opertnRuleId;
	}
	public void setOpertnRuleId(Long opertnRuleId) {
		this.opertnRuleId = opertnRuleId;
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
		if (!(other instanceof PersonApplcnOpertnPK)) {
			return false;
		}
		PersonApplcnOpertnPK castOther = (PersonApplcnOpertnPK)other;
		return new EqualsBuilder()
			.append(this.getApplcnId(), castOther.getApplcnId())
			.append(this.getLctnId(), castOther.getLctnId())
			.append(this.getOpertnRuleId(), castOther.getOpertnRuleId())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnId())
			.append(getLctnId())
			.append(getOpertnRuleId())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnId", getApplcnId())
			.append("lctnId", getLctnId())
			.append("opertnRuleId", getOpertnRuleId())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}