package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN")
public class Applcn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnId;
	private String applcnLockOutInd;
	private String applcnNm;
	private String bdnDepncyInd;
	private String cntrlAccessInd;
	private String corpApplcnInd;
	private String cssRfrncCntrlTxt;
	private String descpTxt;
	private String globalInd;
	private String hiddenInd;
	private String lanPcInd;
	private String localTmplatInd;
	private String mltplCntxtInd;
	private String pswrdInd;
	private String remoteInd;
	private String svcTypeNm;
	private String userTmplatLckoutInd;
	private String webInd;
	private String webUrlTxt;
	private java.util.Set<ApplcnBatch> applcnBatches;
	private java.util.Set<ApplcnLogon> applcnLogons;
	private java.util.Set<ApplcnNotfcn> applcnNotfcns;
	private java.util.Set<ApplcnOpertnRule> applcnOpertnRules;
	private java.util.Set<ApplcnRole> applcnRoles;
	private java.util.Set<ApplcnTeam> applcnTeams;
	private java.util.Set<PersonApplcnPrfrnc> personApplcnPrfrncs;
	private java.util.Set<Reqst> reqsts;
	private java.util.Set<StnLckoutApplcn> stnLckoutApplcns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

	public Applcn() {
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

	@Id()
	@Column(name="APPLCN_ID", unique=true, nullable=false, precision=15)
	public Long getApplcnId() {
		return this.applcnId;
	}
	public void setApplcnId(Long applcnId) {
		this.applcnId = applcnId;
	}

	@Basic()
	@Column(name="APPLCN_LOCK_OUT_IND", length=1)
	public String getApplcnLockOutInd() {
		return this.applcnLockOutInd;
	}
	public void setApplcnLockOutInd(String applcnLockOutInd) {
		this.applcnLockOutInd = applcnLockOutInd;
	}

	@Basic()
	@Column(name="APPLCN_NM", length=30)
	public String getApplcnNm() {
		return this.applcnNm;
	}
	public void setApplcnNm(String applcnNm) {
		this.applcnNm = applcnNm;
	}

	@Basic()
	@Column(name="BDN_DEPNCY_IND", length=1)
	public String getBdnDepncyInd() {
		return this.bdnDepncyInd;
	}
	public void setBdnDepncyInd(String bdnDepncyInd) {
		this.bdnDepncyInd = bdnDepncyInd;
	}

	@Basic()
	@Column(name="CNTRL_ACCESS_IND", length=1)
	public String getCntrlAccessInd() {
		return this.cntrlAccessInd;
	}
	public void setCntrlAccessInd(String cntrlAccessInd) {
		this.cntrlAccessInd = cntrlAccessInd;
	}

	@Basic()
	@Column(name="CORP_APPLCN_IND", length=1)
	public String getCorpApplcnInd() {
		return this.corpApplcnInd;
	}
	public void setCorpApplcnInd(String corpApplcnInd) {
		this.corpApplcnInd = corpApplcnInd;
	}

	@Basic()
	@Column(name="CSS_RFRNC_CNTRL_TXT", length=15)
	public String getCssRfrncCntrlTxt() {
		return this.cssRfrncCntrlTxt;
	}
	public void setCssRfrncCntrlTxt(String cssRfrncCntrlTxt) {
		this.cssRfrncCntrlTxt = cssRfrncCntrlTxt;
	}

	@Basic()
	@Column(name="DESCP_TXT", length=254)
	public String getDescpTxt() {
		return this.descpTxt;
	}
	public void setDescpTxt(String descpTxt) {
		this.descpTxt = descpTxt;
	}

	@Basic()
	@Column(name="GLOBAL_IND", length=1)
	public String getGlobalInd() {
		return this.globalInd;
	}
	public void setGlobalInd(String globalInd) {
		this.globalInd = globalInd;
	}

	@Basic()
	@Column(name="HIDDEN_IND", nullable=false, length=1)
	public String getHiddenInd() {
		return this.hiddenInd;
	}
	public void setHiddenInd(String hiddenInd) {
		this.hiddenInd = hiddenInd;
	}

	@Basic()
	@Column(name="LAN_PC_IND", length=1)
	public String getLanPcInd() {
		return this.lanPcInd;
	}
	public void setLanPcInd(String lanPcInd) {
		this.lanPcInd = lanPcInd;
	}

	@Basic()
	@Column(name="LOCAL_TMPLAT_IND", length=1)
	public String getLocalTmplatInd() {
		return this.localTmplatInd;
	}
	public void setLocalTmplatInd(String localTmplatInd) {
		this.localTmplatInd = localTmplatInd;
	}

	@Basic()
	@Column(name="MLTPL_CNTXT_IND", length=1)
	public String getMltplCntxtInd() {
		return this.mltplCntxtInd;
	}
	public void setMltplCntxtInd(String mltplCntxtInd) {
		this.mltplCntxtInd = mltplCntxtInd;
	}

	@Basic()
	@Column(name="PSWRD_IND", length=1)
	public String getPswrdInd() {
		return this.pswrdInd;
	}
	public void setPswrdInd(String pswrdInd) {
		this.pswrdInd = pswrdInd;
	}

	@Basic()
	@Column(name="REMOTE_IND", length=1)
	public String getRemoteInd() {
		return this.remoteInd;
	}
	public void setRemoteInd(String remoteInd) {
		this.remoteInd = remoteInd;
	}

	@Basic()
	@Column(name="SVC_TYPE_NM", length=50)
	public String getSvcTypeNm() {
		return this.svcTypeNm;
	}
	public void setSvcTypeNm(String svcTypeNm) {
		this.svcTypeNm = svcTypeNm;
	}

	@Basic()
	@Column(name="USER_TMPLAT_LCKOUT_IND", nullable=false, length=1)
	public String getUserTmplatLckoutInd() {
		return this.userTmplatLckoutInd;
	}
	public void setUserTmplatLckoutInd(String userTmplatLckoutInd) {
		this.userTmplatLckoutInd = userTmplatLckoutInd;
	}

	@Basic()
	@Column(name="WEB_IND", length=1)
	public String getWebInd() {
		return this.webInd;
	}
	public void setWebInd(String webInd) {
		this.webInd = webInd;
	}

	@Basic()
	@Column(name="WEB_URL_TXT", length=255)
	public String getWebUrlTxt() {
		return this.webUrlTxt;
	}
	public void setWebUrlTxt(String webUrlTxt) {
		this.webUrlTxt = webUrlTxt;
	}

	//bi-directional many-to-one association to ApplcnBatch
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnBatch> getApplcnBatches() {
		return this.applcnBatches;
	}
	public void setApplcnBatches(java.util.Set<ApplcnBatch> applcnBatches) {
		this.applcnBatches = applcnBatches;
	}

	//bi-directional many-to-one association to ApplcnLogon
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnLogon> getApplcnLogons() {
		return this.applcnLogons;
	}
	public void setApplcnLogons(java.util.Set<ApplcnLogon> applcnLogons) {
		this.applcnLogons = applcnLogons;
	}

	//bi-directional many-to-one association to ApplcnNotfcn
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnNotfcn> getApplcnNotfcns() {
		return this.applcnNotfcns;
	}
	public void setApplcnNotfcns(java.util.Set<ApplcnNotfcn> applcnNotfcns) {
		this.applcnNotfcns = applcnNotfcns;
	}

	//bi-directional many-to-one association to ApplcnOpertnRule
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnOpertnRule> getApplcnOpertnRules() {
		return this.applcnOpertnRules;
	}
	public void setApplcnOpertnRules(java.util.Set<ApplcnOpertnRule> applcnOpertnRules) {
		this.applcnOpertnRules = applcnOpertnRules;
	}

	//bi-directional many-to-one association to ApplcnRole
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnRole> getApplcnRoles() {
		return this.applcnRoles;
	}
	public void setApplcnRoles(java.util.Set<ApplcnRole> applcnRoles) {
		this.applcnRoles = applcnRoles;
	}

	//bi-directional many-to-one association to ApplcnTeam
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnTeam> getApplcnTeams() {
		return this.applcnTeams;
	}
	public void setApplcnTeams(java.util.Set<ApplcnTeam> applcnTeams) {
		this.applcnTeams = applcnTeams;
	}

	//bi-directional many-to-one association to PersonApplcnPrfrnc
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<PersonApplcnPrfrnc> getPersonApplcnPrfrncs() {
		return this.personApplcnPrfrncs;
	}
	public void setPersonApplcnPrfrncs(java.util.Set<PersonApplcnPrfrnc> personApplcnPrfrncs) {
		this.personApplcnPrfrncs = personApplcnPrfrncs;
	}

	//bi-directional many-to-one association to Reqst
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<Reqst> getReqsts() {
		return this.reqsts;
	}
	public void setReqsts(java.util.Set<Reqst> reqsts) {
		this.reqsts = reqsts;
	}

	//bi-directional many-to-one association to StnLckoutApplcn
	@OneToMany(mappedBy="applcn", fetch=FetchType.LAZY)
	public java.util.Set<StnLckoutApplcn> getStnLckoutApplcns() {
		return this.stnLckoutApplcns;
	}
	public void setStnLckoutApplcns(java.util.Set<StnLckoutApplcn> stnLckoutApplcns) {
		this.stnLckoutApplcns = stnLckoutApplcns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Applcn)) {
			return false;
		}
		Applcn castOther = (Applcn)other;
		return new EqualsBuilder()
			.append(this.getApplcnId(), castOther.getApplcnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnId", getApplcnId())
			.toString();
	}
}