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
 * The persistent class for the REQST database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="REQST")
public class Reqst implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long reqstId;
	private String closedInd;
	private Date endDt;
	private String reqstActionTypeNm;
	private Date reqstDt;
	private String reqstTypeNm;
	private String rmksTxt;
	private String roleTypeNm;
	private String scrtyLevelTypeCd;
	private Applcn applcn;
	private Person person;
	private Stn stn;
	private java.util.Set<ReqstLcStatus> reqstLcStatuses;
	private java.util.Set<ReqstOpertnRule> reqstOpertnRules;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Reqst() {
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
	@Column(name="REQST_ID", unique=true, nullable=false, precision=15)
	public Long getReqstId() {
		return this.reqstId;
	}
	public void setReqstId(Long reqstId) {
		this.reqstId = reqstId;
	}

	@Basic()
	@Column(name="CLOSED_IND", length=1)
	public String getClosedInd() {
		return this.closedInd;
	}
	public void setClosedInd(String closedInd) {
		this.closedInd = closedInd;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="REQST_ACTION_TYPE_NM", nullable=false, length=50)
	public String getReqstActionTypeNm() {
		return this.reqstActionTypeNm;
	}
	public void setReqstActionTypeNm(String reqstActionTypeNm) {
		this.reqstActionTypeNm = reqstActionTypeNm;
	}

	@Basic()
	@Column(name="REQST_DT", nullable=false, length=7)
	public Date getReqstDt() {
		return this.reqstDt;
	}
	public void setReqstDt(Date reqstDt) {
		this.reqstDt = reqstDt;
	}

	@Basic()
	@Column(name="REQST_TYPE_NM", nullable=false, length=50)
	public String getReqstTypeNm() {
		return this.reqstTypeNm;
	}
	public void setReqstTypeNm(String reqstTypeNm) {
		this.reqstTypeNm = reqstTypeNm;
	}

	@Basic()
	@Column(name="RMKS_TXT", length=254)
	public String getRmksTxt() {
		return this.rmksTxt;
	}
	public void setRmksTxt(String rmksTxt) {
		this.rmksTxt = rmksTxt;
	}

	@Basic()
	@Column(name="ROLE_TYPE_NM", length=50)
	public String getRoleTypeNm() {
		return this.roleTypeNm;
	}
	public void setRoleTypeNm(String roleTypeNm) {
		this.roleTypeNm = roleTypeNm;
	}

	@Basic()
	@Column(name="SCRTY_LEVEL_TYPE_CD", length=12)
	public String getScrtyLevelTypeCd() {
		return this.scrtyLevelTypeCd;
	}
	public void setScrtyLevelTypeCd(String scrtyLevelTypeCd) {
		this.scrtyLevelTypeCd = scrtyLevelTypeCd;
	}

	//bi-directional many-to-one association to Applcn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID")
	public Applcn getApplcn() {
		return this.applcn;
	}
	public void setApplcn(Applcn applcn) {
		this.applcn = applcn;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REQST_PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REQST_LCTN_ID", referencedColumnName="LCTN_ID", nullable=false)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	//bi-directional many-to-one association to ReqstLcStatus
	@OneToMany(mappedBy="reqst", fetch=FetchType.LAZY)
	public java.util.Set<ReqstLcStatus> getReqstLcStatuses() {
		return this.reqstLcStatuses;
	}
	public void setReqstLcStatuses(java.util.Set<ReqstLcStatus> reqstLcStatuses) {
		this.reqstLcStatuses = reqstLcStatuses;
	}

	//bi-directional many-to-one association to ReqstOpertnRule
	@OneToMany(mappedBy="reqst", fetch=FetchType.LAZY)
	public java.util.Set<ReqstOpertnRule> getReqstOpertnRules() {
		return this.reqstOpertnRules;
	}
	public void setReqstOpertnRules(java.util.Set<ReqstOpertnRule> reqstOpertnRules) {
		this.reqstOpertnRules = reqstOpertnRules;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Reqst)) {
			return false;
		}
		Reqst castOther = (Reqst)other;
		return new EqualsBuilder()
			.append(this.getReqstId(), castOther.getReqstId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getReqstId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("reqstId", getReqstId())
			.toString();
	}
}