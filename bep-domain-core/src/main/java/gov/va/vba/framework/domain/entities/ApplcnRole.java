package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_ROLE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_ROLE")
public class ApplcnRole  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ApplcnRolePK compId;
	private java.util.Set<ApplcnPersonRole> applcnPersonRoles;
	private Applcn applcn;
	private java.util.Set<GroupPrfilRole> groupPrfilRoles;
	private java.util.Set<StnApplcnRole> stnApplcnRoles;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnRole() {
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
	public ApplcnRolePK getCompId() {
		return this.compId;
	}
	public void setCompId(ApplcnRolePK compId) {
		this.compId = compId;
	}

	//bi-directional many-to-one association to ApplcnPersonRole
	@OneToMany(mappedBy="applcnRole", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnPersonRole> getApplcnPersonRoles() {
		return this.applcnPersonRoles;
	}
	public void setApplcnPersonRoles(java.util.Set<ApplcnPersonRole> applcnPersonRoles) {
		this.applcnPersonRoles = applcnPersonRoles;
	}

	//bi-directional many-to-one association to Applcn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false)
	public Applcn getApplcn() {
		return this.applcn;
	}
	public void setApplcn(Applcn applcn) {
		this.applcn = applcn;
	}

	//bi-directional many-to-one association to GroupPrfilRole
	@OneToMany(mappedBy="applcnRole", fetch=FetchType.LAZY)
	public java.util.Set<GroupPrfilRole> getGroupPrfilRoles() {
		return this.groupPrfilRoles;
	}
	public void setGroupPrfilRoles(java.util.Set<GroupPrfilRole> groupPrfilRoles) {
		this.groupPrfilRoles = groupPrfilRoles;
	}

	//bi-directional many-to-one association to StnApplcnRole
	@OneToMany(mappedBy="applcnRole", fetch=FetchType.LAZY)
	public java.util.Set<StnApplcnRole> getStnApplcnRoles() {
		return this.stnApplcnRoles;
	}
	public void setStnApplcnRoles(java.util.Set<StnApplcnRole> stnApplcnRoles) {
		this.stnApplcnRoles = stnApplcnRoles;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnRole)) {
			return false;
		}
		ApplcnRole castOther = (ApplcnRole)other;
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