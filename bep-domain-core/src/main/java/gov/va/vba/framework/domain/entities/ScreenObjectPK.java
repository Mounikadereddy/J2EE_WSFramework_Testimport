package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the SCREEN_OBJECT database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ScreenObjectPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String objectNm;
	private Long opertnRuleId;

    public ScreenObjectPK() {
    }

	@Column(name="OBJECT_NM", nullable=false, length=30)
	public String getObjectNm() {
		return this.objectNm;
	}
	public void setObjectNm(String objectNm) {
		this.objectNm = objectNm;
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
		if (!(other instanceof ScreenObjectPK)) {
			return false;
		}
		ScreenObjectPK castOther = (ScreenObjectPK)other;
		return new EqualsBuilder()
			.append(this.getObjectNm(), castOther.getObjectNm())
			.append(this.getOpertnRuleId(), castOther.getOpertnRuleId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getObjectNm())
			.append(getOpertnRuleId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("objectNm", getObjectNm())
			.append("opertnRuleId", getOpertnRuleId())
			.toString();
	}
}