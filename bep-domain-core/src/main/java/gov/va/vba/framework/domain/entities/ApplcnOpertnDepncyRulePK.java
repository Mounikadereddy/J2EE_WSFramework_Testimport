package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the APPLCN_OPERTN_DEPNCY_RULE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ApplcnOpertnDepncyRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnIdA;
	private Long applcnIdB;
	private Long opertnRuleIdA;
	private Long opertnRuleIdB;

    public ApplcnOpertnDepncyRulePK() {
    }

	@Column(name="APPLCN_ID_A", nullable=false, precision=15)
	public Long getApplcnIdA() {
		return this.applcnIdA;
	}
	public void setApplcnIdA(Long applcnIdA) {
		this.applcnIdA = applcnIdA;
	}

	@Column(name="APPLCN_ID_B", nullable=false, precision=15)
	public Long getApplcnIdB() {
		return this.applcnIdB;
	}
	public void setApplcnIdB(Long applcnIdB) {
		this.applcnIdB = applcnIdB;
	}

	@Column(name="OPERTN_RULE_ID_A", nullable=false, precision=15)
	public Long getOpertnRuleIdA() {
		return this.opertnRuleIdA;
	}
	public void setOpertnRuleIdA(Long opertnRuleIdA) {
		this.opertnRuleIdA = opertnRuleIdA;
	}

	@Column(name="OPERTN_RULE_ID_B", nullable=false, precision=15)
	public Long getOpertnRuleIdB() {
		return this.opertnRuleIdB;
	}
	public void setOpertnRuleIdB(Long opertnRuleIdB) {
		this.opertnRuleIdB = opertnRuleIdB;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnOpertnDepncyRulePK)) {
			return false;
		}
		ApplcnOpertnDepncyRulePK castOther = (ApplcnOpertnDepncyRulePK)other;
		return new EqualsBuilder()
			.append(this.getApplcnIdA(), castOther.getApplcnIdA())
			.append(this.getApplcnIdB(), castOther.getApplcnIdB())
			.append(this.getOpertnRuleIdA(), castOther.getOpertnRuleIdA())
			.append(this.getOpertnRuleIdB(), castOther.getOpertnRuleIdB())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnIdA())
			.append(getApplcnIdB())
			.append(getOpertnRuleIdA())
			.append(getOpertnRuleIdB())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnIdA", getApplcnIdA())
			.append("applcnIdB", getApplcnIdB())
			.append("opertnRuleIdA", getOpertnRuleIdA())
			.append("opertnRuleIdB", getOpertnRuleIdB())
			.toString();
	}
}