package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the APPLCN_OPERTN_PRFIL database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ApplcnOpertnPrfilPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnId;
	private Long groupPrfilId;
	private Long opertnRuleId;

    public ApplcnOpertnPrfilPK() {
    }

	@Column(name="APPLCN_ID", nullable=false, precision=15)
	public Long getApplcnId() {
		return this.applcnId;
	}
	public void setApplcnId(Long applcnId) {
		this.applcnId = applcnId;
	}

	@Column(name="GROUP_PRFIL_ID", nullable=false, precision=15)
	public Long getGroupPrfilId() {
		return this.groupPrfilId;
	}
	public void setGroupPrfilId(Long groupPrfilId) {
		this.groupPrfilId = groupPrfilId;
	}

	@Column(name="OPERTN_RULE_ID", nullable=false, precision=15)
	public Long getOpertnRuleId() {
		return this.opertnRuleId;
	}
	public void setOpertnRuleId(Long opertnRuleId) {
		this.opertnRuleId = opertnRuleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnOpertnPrfilPK)) {
			return false;
		}
		ApplcnOpertnPrfilPK castOther = (ApplcnOpertnPrfilPK)other;
		return new EqualsBuilder()
			.append(this.getApplcnId(), castOther.getApplcnId())
			.append(this.getGroupPrfilId(), castOther.getGroupPrfilId())
			.append(this.getOpertnRuleId(), castOther.getOpertnRuleId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnId())
			.append(getGroupPrfilId())
			.append(getOpertnRuleId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnId", getApplcnId())
			.append("groupPrfilId", getGroupPrfilId())
			.append("opertnRuleId", getOpertnRuleId())
			.toString();
	}
}