package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the APPLCN_ROLE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class ApplcnRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnId;
	private String roleTypeNm;

    public ApplcnRolePK() {
    }

	@Column(name="APPLCN_ID", nullable=false, precision=15)
	public Long getApplcnId() {
		return this.applcnId;
	}
	public void setApplcnId(Long applcnId) {
		this.applcnId = applcnId;
	}

	@Column(name="ROLE_TYPE_NM", nullable=false, length=50)
	public String getRoleTypeNm() {
		return this.roleTypeNm;
	}
	public void setRoleTypeNm(String roleTypeNm) {
		this.roleTypeNm = roleTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnRolePK)) {
			return false;
		}
		ApplcnRolePK castOther = (ApplcnRolePK)other;
		return new EqualsBuilder()
			.append(this.getApplcnId(), castOther.getApplcnId())
			.append(this.getRoleTypeNm(), castOther.getRoleTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnId())
			.append(getRoleTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnId", getApplcnId())
			.append("roleTypeNm", getRoleTypeNm())
			.toString();
	}
}