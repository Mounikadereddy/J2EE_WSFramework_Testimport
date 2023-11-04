package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the GROUP_PRFIL_ROLE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="GROUP_PRFIL_ROLE")
public class GroupPrfilRole implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private GroupPrfilRolePK compId;
	private ApplcnRole applcnRole;
	private GroupPrfil groupPrfil;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public GroupPrfilRole() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public java.util.Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(java.util.Date jrnDt) {
		this.jrnDt = jrnDt;
	}

	@Basic()
	@Column(name="JRN_LCTN_ID", nullable=false, length=4)
	public String getJrnLctnId() {
		return this.jrnLctnId;
	}
	public void setJrnLctnId(String jrnLctnId) {
		this.jrnLctnId = jrnLctnId;
	}

	@Basic()
	@Column(name="JRN_OBJ_ID", nullable=false, length=32)
	public String getJrnObjId() {
		return this.jrnObjId;
	}
	public void setJrnObjId(String jrnObjId) {
		this.jrnObjId = jrnObjId;
	}

	@Basic()
	@Column(name="JRN_STATUS_TYPE_CD", nullable=false, length=12)
	public String getJrnStatusTypeCd() {
		return this.jrnStatusTypeCd;
	}
	public void setJrnStatusTypeCd(String jrnStatusTypeCd) {
		this.jrnStatusTypeCd = jrnStatusTypeCd;
	}

	@Basic()
	@Column(name="JRN_USER_ID", nullable=false, length=50)
	public String getJrnUserId() {
		return this.jrnUserId;
	}
	public void setJrnUserId(String jrnUserId) {
		this.jrnUserId = jrnUserId;
	}
	
	@EmbeddedId
	public GroupPrfilRolePK getCompId() {
		return this.compId;
	}
	public void setCompId(GroupPrfilRolePK compId) {
		this.compId = compId;
	}

	//bi-directional many-to-one association to ApplcnRole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ROLE_TYPE_NM", referencedColumnName="ROLE_TYPE_NM", nullable=false, insertable=false, updatable=false)
		})
	public ApplcnRole getApplcnRole() {
		return this.applcnRole;
	}
	public void setApplcnRole(ApplcnRole applcnRole) {
		this.applcnRole = applcnRole;
	}

	//bi-directional many-to-one association to GroupPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GROUP_PRFIL_ID", referencedColumnName="GROUP_PRFIL_ID", nullable=false, insertable=false, updatable=false)
	public GroupPrfil getGroupPrfil() {
		return this.groupPrfil;
	}
	public void setGroupPrfil(GroupPrfil groupPrfil) {
		this.groupPrfil = groupPrfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupPrfilRole)) {
			return false;
		}
		GroupPrfilRole castOther = (GroupPrfilRole)other;
		return new EqualsBuilder()
			.append(this.getCompId(), castOther.getCompId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("compId", getCompId())
			.toString();
	}
}