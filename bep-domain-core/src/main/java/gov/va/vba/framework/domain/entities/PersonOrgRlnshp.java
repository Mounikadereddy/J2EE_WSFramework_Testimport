package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PERSON_ORG_RLNSHP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_ORG_RLNSHP")
public class PersonOrgRlnshp implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PersonOrgRlnshpPK compId;
	private Integer areaNbr;
	private Date beginDt;
	private Date cntrctBeginDt;
	private Date cntrctEndDt;
	private Integer cntrctHourQty;
	private Integer cntryNbr;
	private String compInfoTxt;
	private String concsnTxt;
	private Integer creditNbr;
	private Date dsbltyDt;
	private Date dsbltyFullDt;
	private String dsbltyInd;
	private String dsbltyTxt;
	private String emplmtObtainInd;
	private String emplmtStatusTypeNm;
	private String emplyrIncntvInd;
	private Date emplyrLastWorkDt;
	private String emplyrPocTxt;
	private Integer extnsnNbr;
	private String frgnPhoneRfrncTxt;
	private String govtExpnsInd;
	private Double gpaNbr;
	private String gpaTxt;
	private Integer hoursPerWeekNbr;
	private Integer ilnesLostNbr;
	private String jobOpninTxt;
	private Date lastFullDt;
	private Date lastWorkDt;
	private String mnagrInfoTxt;
	private Integer monthWorkedNbr;
	private String orgCntctPosnNm;
	private Long phoneNbr;
	private Date plcmntDt;
	private String posnDescpTxt;
	private String posnNm;
	private Double postRehabSalaryAmt;
	private String profnlLicnsNm;
	private String remarkTxt;
	private String selfEmplmtInd;
	private String selfEmplydTxt;
	private String speclAuthtyInd;
	private String trmntnReasonTxt;
	private String unpaidWorkExprncTxt;
	private PtcpntRlnshp ptcpntRlnshp;
	private java.util.Set<PersonOrgRlnshpBnft> personOrgRlnshpBnfts;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonOrgRlnshp() {
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
	
	@EmbeddedId
	public PersonOrgRlnshpPK getCompId() {
		return this.compId;
	}
	public void setCompId(PersonOrgRlnshpPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="AREA_NBR", precision=4)
	public Integer getAreaNbr() {
		return this.areaNbr;
	}
	public void setAreaNbr(Integer areaNbr) {
		this.areaNbr = areaNbr;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="CNTRCT_BEGIN_DT", length=7)
	public Date getCntrctBeginDt() {
		return this.cntrctBeginDt;
	}
	public void setCntrctBeginDt(Date cntrctBeginDt) {
		this.cntrctBeginDt = cntrctBeginDt;
	}

	@Basic()
	@Column(name="CNTRCT_END_DT", length=7)
	public Date getCntrctEndDt() {
		return this.cntrctEndDt;
	}
	public void setCntrctEndDt(Date cntrctEndDt) {
		this.cntrctEndDt = cntrctEndDt;
	}

	@Basic()
	@Column(name="CNTRCT_HOUR_QTY", precision=3)
	public Integer getCntrctHourQty() {
		return this.cntrctHourQty;
	}
	public void setCntrctHourQty(Integer cntrctHourQty) {
		this.cntrctHourQty = cntrctHourQty;
	}

	@Basic()
	@Column(name="CNTRY_NBR", precision=4)
	public Integer getCntryNbr() {
		return this.cntryNbr;
	}
	public void setCntryNbr(Integer cntryNbr) {
		this.cntryNbr = cntryNbr;
	}

	@Basic()
	@Column(name="COMP_INFO_TXT", length=120)
	public String getCompInfoTxt() {
		return this.compInfoTxt;
	}
	public void setCompInfoTxt(String compInfoTxt) {
		this.compInfoTxt = compInfoTxt;
	}

	@Basic()
	@Column(name="CONCSN_TXT", length=120)
	public String getConcsnTxt() {
		return this.concsnTxt;
	}
	public void setConcsnTxt(String concsnTxt) {
		this.concsnTxt = concsnTxt;
	}

	@Basic()
	@Column(name="CREDIT_NBR", precision=3)
	public Integer getCreditNbr() {
		return this.creditNbr;
	}
	public void setCreditNbr(Integer creditNbr) {
		this.creditNbr = creditNbr;
	}

	@Basic()
	@Column(name="DSBLTY_DT", length=7)
	public Date getDsbltyDt() {
		return this.dsbltyDt;
	}
	public void setDsbltyDt(Date dsbltyDt) {
		this.dsbltyDt = dsbltyDt;
	}

	@Basic()
	@Column(name="DSBLTY_FULL_DT", length=7)
	public Date getDsbltyFullDt() {
		return this.dsbltyFullDt;
	}
	public void setDsbltyFullDt(Date dsbltyFullDt) {
		this.dsbltyFullDt = dsbltyFullDt;
	}

	@Basic()
	@Column(name="DSBLTY_IND", length=1)
	public String getDsbltyInd() {
		return this.dsbltyInd;
	}
	public void setDsbltyInd(String dsbltyInd) {
		this.dsbltyInd = dsbltyInd;
	}

	@Basic()
	@Column(name="DSBLTY_TXT", length=254)
	public String getDsbltyTxt() {
		return this.dsbltyTxt;
	}
	public void setDsbltyTxt(String dsbltyTxt) {
		this.dsbltyTxt = dsbltyTxt;
	}

	@Basic()
	@Column(name="EMPLMT_OBTAIN_IND", length=1)
	public String getEmplmtObtainInd() {
		return this.emplmtObtainInd;
	}
	public void setEmplmtObtainInd(String emplmtObtainInd) {
		this.emplmtObtainInd = emplmtObtainInd;
	}

	@Basic()
	@Column(name="EMPLMT_STATUS_TYPE_NM", length=50)
	public String getEmplmtStatusTypeNm() {
		return this.emplmtStatusTypeNm;
	}
	public void setEmplmtStatusTypeNm(String emplmtStatusTypeNm) {
		this.emplmtStatusTypeNm = emplmtStatusTypeNm;
	}

	@Basic()
	@Column(name="EMPLYR_INCNTV_IND", length=1)
	public String getEmplyrIncntvInd() {
		return this.emplyrIncntvInd;
	}
	public void setEmplyrIncntvInd(String emplyrIncntvInd) {
		this.emplyrIncntvInd = emplyrIncntvInd;
	}

	@Basic()
	@Column(name="EMPLYR_LAST_WORK_DT", length=7)
	public Date getEmplyrLastWorkDt() {
		return this.emplyrLastWorkDt;
	}
	public void setEmplyrLastWorkDt(Date emplyrLastWorkDt) {
		this.emplyrLastWorkDt = emplyrLastWorkDt;
	}

	@Basic()
	@Column(name="EMPLYR_POC_TXT", length=40)
	public String getEmplyrPocTxt() {
		return this.emplyrPocTxt;
	}
	public void setEmplyrPocTxt(String emplyrPocTxt) {
		this.emplyrPocTxt = emplyrPocTxt;
	}

	@Basic()
	@Column(name="EXTNSN_NBR", precision=4)
	public Integer getExtnsnNbr() {
		return this.extnsnNbr;
	}
	public void setExtnsnNbr(Integer extnsnNbr) {
		this.extnsnNbr = extnsnNbr;
	}

	@Basic()
	@Column(name="FRGN_PHONE_RFRNC_TXT", length=30)
	public String getFrgnPhoneRfrncTxt() {
		return this.frgnPhoneRfrncTxt;
	}
	public void setFrgnPhoneRfrncTxt(String frgnPhoneRfrncTxt) {
		this.frgnPhoneRfrncTxt = frgnPhoneRfrncTxt;
	}

	@Basic()
	@Column(name="GOVT_EXPNS_IND", length=1)
	public String getGovtExpnsInd() {
		return this.govtExpnsInd;
	}
	public void setGovtExpnsInd(String govtExpnsInd) {
		this.govtExpnsInd = govtExpnsInd;
	}

	@Basic()
	@Column(name="GPA_NBR", precision=3, scale=2)
	public Double getGpaNbr() {
		return this.gpaNbr;
	}
	public void setGpaNbr(Double gpaNbr) {
		this.gpaNbr = gpaNbr;
	}

	@Basic()
	@Column(name="GPA_TXT", length=30)
	public String getGpaTxt() {
		return this.gpaTxt;
	}
	public void setGpaTxt(String gpaTxt) {
		this.gpaTxt = gpaTxt;
	}

	@Basic()
	@Column(name="HOURS_PER_WEEK_NBR", precision=3)
	public Integer getHoursPerWeekNbr() {
		return this.hoursPerWeekNbr;
	}
	public void setHoursPerWeekNbr(Integer hoursPerWeekNbr) {
		this.hoursPerWeekNbr = hoursPerWeekNbr;
	}

	@Basic()
	@Column(name="ILNES_LOST_NBR", precision=3)
	public Integer getIlnesLostNbr() {
		return this.ilnesLostNbr;
	}
	public void setIlnesLostNbr(Integer ilnesLostNbr) {
		this.ilnesLostNbr = ilnesLostNbr;
	}

	@Basic()
	@Column(name="JOB_OPNIN_TXT", length=120)
	public String getJobOpninTxt() {
		return this.jobOpninTxt;
	}
	public void setJobOpninTxt(String jobOpninTxt) {
		this.jobOpninTxt = jobOpninTxt;
	}

	@Basic()
	@Column(name="LAST_FULL_DT", length=7)
	public Date getLastFullDt() {
		return this.lastFullDt;
	}
	public void setLastFullDt(Date lastFullDt) {
		this.lastFullDt = lastFullDt;
	}

	@Basic()
	@Column(name="LAST_WORK_DT", length=7)
	public Date getLastWorkDt() {
		return this.lastWorkDt;
	}
	public void setLastWorkDt(Date lastWorkDt) {
		this.lastWorkDt = lastWorkDt;
	}

	@Basic()
	@Column(name="MNAGR_INFO_TXT", length=120)
	public String getMnagrInfoTxt() {
		return this.mnagrInfoTxt;
	}
	public void setMnagrInfoTxt(String mnagrInfoTxt) {
		this.mnagrInfoTxt = mnagrInfoTxt;
	}

	@Basic()
	@Column(name="MONTH_WORKED_NBR", precision=3)
	public Integer getMonthWorkedNbr() {
		return this.monthWorkedNbr;
	}
	public void setMonthWorkedNbr(Integer monthWorkedNbr) {
		this.monthWorkedNbr = monthWorkedNbr;
	}

	@Basic()
	@Column(name="ORG_CNTCT_POSN_NM", length=50)
	public String getOrgCntctPosnNm() {
		return this.orgCntctPosnNm;
	}
	public void setOrgCntctPosnNm(String orgCntctPosnNm) {
		this.orgCntctPosnNm = orgCntctPosnNm;
	}

	@Basic()
	@Column(name="PHONE_NBR", precision=11)
	public Long getPhoneNbr() {
		return this.phoneNbr;
	}
	public void setPhoneNbr(Long phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	@Basic()
	@Column(name="PLCMNT_DT", length=7)
	public Date getPlcmntDt() {
		return this.plcmntDt;
	}
	public void setPlcmntDt(Date plcmntDt) {
		this.plcmntDt = plcmntDt;
	}

	@Basic()
	@Column(name="POSN_DESCP_TXT", length=120)
	public String getPosnDescpTxt() {
		return this.posnDescpTxt;
	}
	public void setPosnDescpTxt(String posnDescpTxt) {
		this.posnDescpTxt = posnDescpTxt;
	}

	@Basic()
	@Column(name="POSN_NM", length=50)
	public String getPosnNm() {
		return this.posnNm;
	}
	public void setPosnNm(String posnNm) {
		this.posnNm = posnNm;
	}

	@Basic()
	@Column(name="POST_REHAB_SALARY_AMT", precision=8, scale=2)
	public Double getPostRehabSalaryAmt() {
		return this.postRehabSalaryAmt;
	}
	public void setPostRehabSalaryAmt(Double postRehabSalaryAmt) {
		this.postRehabSalaryAmt = postRehabSalaryAmt;
	}

	@Basic()
	@Column(name="PROFNL_LICNS_NM", length=50)
	public String getProfnlLicnsNm() {
		return this.profnlLicnsNm;
	}
	public void setProfnlLicnsNm(String profnlLicnsNm) {
		this.profnlLicnsNm = profnlLicnsNm;
	}

	@Basic()
	@Column(name="REMARK_TXT", length=254)
	public String getRemarkTxt() {
		return this.remarkTxt;
	}
	public void setRemarkTxt(String remarkTxt) {
		this.remarkTxt = remarkTxt;
	}

	@Basic()
	@Column(name="SELF_EMPLMT_IND", length=1)
	public String getSelfEmplmtInd() {
		return this.selfEmplmtInd;
	}
	public void setSelfEmplmtInd(String selfEmplmtInd) {
		this.selfEmplmtInd = selfEmplmtInd;
	}

	@Basic()
	@Column(name="SELF_EMPLYD_TXT", length=254)
	public String getSelfEmplydTxt() {
		return this.selfEmplydTxt;
	}
	public void setSelfEmplydTxt(String selfEmplydTxt) {
		this.selfEmplydTxt = selfEmplydTxt;
	}

	@Basic()
	@Column(name="SPECL_AUTHTY_IND", length=1)
	public String getSpeclAuthtyInd() {
		return this.speclAuthtyInd;
	}
	public void setSpeclAuthtyInd(String speclAuthtyInd) {
		this.speclAuthtyInd = speclAuthtyInd;
	}

	@Basic()
	@Column(name="TRMNTN_REASON_TXT", length=254)
	public String getTrmntnReasonTxt() {
		return this.trmntnReasonTxt;
	}
	public void setTrmntnReasonTxt(String trmntnReasonTxt) {
		this.trmntnReasonTxt = trmntnReasonTxt;
	}

	@Basic()
	@Column(name="UNPAID_WORK_EXPRNC_TXT", length=10)
	public String getUnpaidWorkExprncTxt() {
		return this.unpaidWorkExprncTxt;
	}
	public void setUnpaidWorkExprncTxt(String unpaidWorkExprncTxt) {
		this.unpaidWorkExprncTxt = unpaidWorkExprncTxt;
	}

	//bi-directional one-to-one association to PtcpntRlnshp
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID_A", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID_B", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="PTCPNT_RLNSHP_TYPE_NM", referencedColumnName="PTCPNT_RLNSHP_TYPE_NM", nullable=false, insertable=false, updatable=false)
		})
	public PtcpntRlnshp getPtcpntRlnshp() {
		return this.ptcpntRlnshp;
	}
	public void setPtcpntRlnshp(PtcpntRlnshp ptcpntRlnshp) {
		this.ptcpntRlnshp = ptcpntRlnshp;
	}

	//bi-directional many-to-one association to PersonOrgRlnshpBnft
	@OneToMany(mappedBy="personOrgRlnshp", fetch=FetchType.LAZY)
	public java.util.Set<PersonOrgRlnshpBnft> getPersonOrgRlnshpBnfts() {
		return this.personOrgRlnshpBnfts;
	}
	public void setPersonOrgRlnshpBnfts(java.util.Set<PersonOrgRlnshpBnft> personOrgRlnshpBnfts) {
		this.personOrgRlnshpBnfts = personOrgRlnshpBnfts;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonOrgRlnshp)) {
			return false;
		}
		PersonOrgRlnshp castOther = (PersonOrgRlnshp)other;
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