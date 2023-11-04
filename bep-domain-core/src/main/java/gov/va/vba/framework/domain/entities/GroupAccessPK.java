package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The primary key class for the GROUP_ACCESS database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class GroupAccessPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String accessTypeCd;
	private Long groupPrfilId;
	private String groupTypeCd;

    public GroupAccessPK() {
    }

	@Column(name="ACCESS_TYPE_CD", nullable=false, length=12)
	public String getAccessTypeCd() {
		return this.accessTypeCd;
	}
	public void setAccessTypeCd(String accessTypeCd) {
		this.accessTypeCd = accessTypeCd;
	}

	@Column(name="GROUP_PRFIL_ID", nullable=false, precision=15)
	public Long getGroupPrfilId() {
		return this.groupPrfilId;
	}
	public void setGroupPrfilId(Long groupPrfilId) {
		this.groupPrfilId = groupPrfilId;
	}

	@Column(name="GROUP_TYPE_CD", nullable=false, length=12)
	public String getGroupTypeCd() {
		return this.groupTypeCd;
	}
	public void setGroupTypeCd(String groupTypeCd) {
		this.groupTypeCd = groupTypeCd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupAccessPK)) {
			return false;
		}
		GroupAccessPK castOther = (GroupAccessPK)other;
		return new EqualsBuilder()
			.append(this.getAccessTypeCd(), castOther.getAccessTypeCd())
			.append(this.getGroupPrfilId(), castOther.getGroupPrfilId())
			.append(this.getGroupTypeCd(), castOther.getGroupTypeCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAccessTypeCd())
			.append(getGroupPrfilId())
			.append(getGroupTypeCd())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("accessTypeCd", getAccessTypeCd())
			.append("groupPrfilId", getGroupPrfilId())
			.append("groupTypeCd", getGroupTypeCd())
			.toString();
	}
}