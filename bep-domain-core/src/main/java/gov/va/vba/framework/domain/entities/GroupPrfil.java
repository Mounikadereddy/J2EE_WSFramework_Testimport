package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the GROUP_PRFIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="GROUP_PRFIL")
public class GroupPrfil implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long groupPrfilId;
	private Date createDt;
	private String groupPrfilLckoutInd;
	private String groupPrfilTypeNm;
	private String jobPrfilInd;
	private String nm;
	private java.util.Set<ApplcnOpertnPrfil> applcnOpertnPrfils;
	private java.util.Set<GroupAccess> groupAccesses;
	private Stn stn;
	private java.util.Set<GroupPrfilRole> groupPrfilRoles;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public GroupPrfil() {
    }
    
	@Basic()
	@Column(name="JRN_DT", nullable=false, length=7)
	public Date getJrnDt() {
		return this.jrnDt;
	}
	public void setJrnDt(Date jrnDt) {
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
	
	@Id()
	@Column(name="GROUP_PRFIL_ID", unique=true, nullable=false, precision=15)
	public Long getGroupPrfilId() {
		return this.groupPrfilId;
	}
	public void setGroupPrfilId(Long groupPrfilId) {
		this.groupPrfilId = groupPrfilId;
	}

	@Basic()
	@Column(name="CREATE_DT", nullable=false, length=7)
	public Date getCreateDt() {
		return this.createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	@Basic()
	@Column(name="GROUP_PRFIL_LCKOUT_IND", length=1)
	public String getGroupPrfilLckoutInd() {
		return this.groupPrfilLckoutInd;
	}
	public void setGroupPrfilLckoutInd(String groupPrfilLckoutInd) {
		this.groupPrfilLckoutInd = groupPrfilLckoutInd;
	}

	@Basic()
	@Column(name="GROUP_PRFIL_TYPE_NM", nullable=false, length=50)
	public String getGroupPrfilTypeNm() {
		return this.groupPrfilTypeNm;
	}
	public void setGroupPrfilTypeNm(String groupPrfilTypeNm) {
		this.groupPrfilTypeNm = groupPrfilTypeNm;
	}

	@Basic()
	@Column(name="JOB_PRFIL_IND", nullable=false, length=1)
	public String getJobPrfilInd() {
		return this.jobPrfilInd;
	}
	public void setJobPrfilInd(String jobPrfilInd) {
		this.jobPrfilInd = jobPrfilInd;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=40)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	//bi-directional many-to-one association to ApplcnOpertnPrfil
	@OneToMany(mappedBy="groupPrfil", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnOpertnPrfil> getApplcnOpertnPrfils() {
		return this.applcnOpertnPrfils;
	}
	public void setApplcnOpertnPrfils(java.util.Set<ApplcnOpertnPrfil> applcnOpertnPrfils) {
		this.applcnOpertnPrfils = applcnOpertnPrfils;
	}

	//bi-directional many-to-one association to GroupAccess
	@OneToMany(mappedBy="groupPrfil", fetch=FetchType.LAZY)
	public java.util.Set<GroupAccess> getGroupAccesses() {
		return this.groupAccesses;
	}
	public void setGroupAccesses(java.util.Set<GroupAccess> groupAccesses) {
		this.groupAccesses = groupAccesses;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	//bi-directional many-to-one association to GroupPrfilRole
	@OneToMany(mappedBy="groupPrfil", fetch=FetchType.LAZY)
	public java.util.Set<GroupPrfilRole> getGroupPrfilRoles() {
		return this.groupPrfilRoles;
	}
	public void setGroupPrfilRoles(java.util.Set<GroupPrfilRole> groupPrfilRoles) {
		this.groupPrfilRoles = groupPrfilRoles;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof GroupPrfil)) {
			return false;
		}
		GroupPrfil castOther = (GroupPrfil)other;
		return new EqualsBuilder()
			.append(this.getGroupPrfilId(), castOther.getGroupPrfilId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getGroupPrfilId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("groupPrfilId", getGroupPrfilId())
			.toString();
	}
}