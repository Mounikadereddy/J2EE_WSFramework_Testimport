package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the REQST_OPERTN_RULE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ReqstOpertnRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private Long opertnRuleId;
	private Long reqstId;

    public ReqstOpertnRulePK() {
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

	@Column(name="REQST_ID", nullable=false, precision=15)
	public Long getReqstId() {
		return this.reqstId;
	}
	public void setReqstId(Long reqstId) {
		this.reqstId = reqstId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqstOpertnRulePK)) {
			return false;
		}
		ReqstOpertnRulePK castOther = (ReqstOpertnRulePK)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.append(this.getOpertnRuleId(), castOther.getOpertnRuleId())
			.append(this.getReqstId(), castOther.getReqstId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.append(getOpertnRuleId())
			.append(getReqstId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.append("opertnRuleId", getOpertnRuleId())
			.append("reqstId", getReqstId())
			.toString();
	}
}