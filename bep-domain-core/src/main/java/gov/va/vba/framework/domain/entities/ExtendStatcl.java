package gov.va.vba.framework.domain.entities;

import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the EXTEND_STATCL database table.
 * 
 * @author BEA Workshop
 */
@SqlResultSetMapping(name="extendedStatclMapping", entities={@EntityResult(entityClass=ExtendStatcl.class)})
@Entity()
@Table(name="EXTEND_STATCL")
public class ExtendStatcl  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long extendAwardCmpsitId;
	private String activeRsrvstCd;
	private Integer addlSvcNbr;
	private String antmclLossTypeCd;
	private Date beneBrthdyDt;
	private Date beneDeathDt;
	private String beneFirstNm;
	private String beneGenderCd;
	private String beneLastNm;
	private String beneMiddleNm;
	private String beneSsnNbr;
	private String beneSsnVrfctnStatusTypeCd;
	private String beneSuffixNm;
	private String blindInd;
	private String cmptnyDecnTypeCd;
	private Date eodDt;
	private String fidDecnCategyTypeCd;
	private String gulfWarTheatrCd;
	private String igAuditNbr;
	private String insFileNbr;
	private String insLoanNbr;
	private String lossUseTypeCd;
	private String medcadCd;
	private String medFcltyNbr;
	private String mltySeprtnReasonTypeNm;
	private String mltySvcBranchTypeNm;
	private String mltySvcOtherBranchTypeNm;
	private String mltyTourSvcStatusTypeNm;
	private String mpDschrgCharTypeNm;
	private String nursngHomeInd;
	private String otherLossTypeCd;
	private String payGradeCd;
	private String powCd;
	private String prplgcHousngNbr;
	private Date radDt;
	private Integer rsrvstDaysNbr;
	private String svcNbr;
	private String taxAbtmntCd;
	private String verifdInd;
	private Date vetBrthdyDt;
	private Date vetDeathDt;
	private String vetFirstNm;
	private String vetGenderCd;
	private String vetLastNm;
	private String vetMiddleNm;
	private String vetSsnNbr;
	private String vetSsnVrfctnStatusTypeCd;
	private String vetSuffixNm;
	private String vetToVetMarageCd;
	private String vietnmTheatrInd;
	private String warTimeSvcInd;

    public ExtendStatcl() {
    }

	@Id()
	@Column(name="EXTEND_AWARD_CMPSIT_ID", unique=true, nullable=false, precision=15)
	public Long getExtendAwardCmpsitId() {
		return this.extendAwardCmpsitId;
	}
	public void setExtendAwardCmpsitId(Long extendAwardCmpsitId) {
		this.extendAwardCmpsitId = extendAwardCmpsitId;
	}

	@Basic()
	@Column(name="ACTIVE_RSRVST_CD", length=2)
	public String getActiveRsrvstCd() {
		return this.activeRsrvstCd;
	}
	public void setActiveRsrvstCd(String activeRsrvstCd) {
		this.activeRsrvstCd = activeRsrvstCd;
	}

	@Basic()
	@Column(name="ADDL_SVC_NBR", precision=3)
	public Integer getAddlSvcNbr() {
		return this.addlSvcNbr;
	}
	public void setAddlSvcNbr(Integer addlSvcNbr) {
		this.addlSvcNbr = addlSvcNbr;
	}

	@Basic()
	@Column(name="ANTMCL_LOSS_TYPE_CD", length=12)
	public String getAntmclLossTypeCd() {
		return this.antmclLossTypeCd;
	}
	public void setAntmclLossTypeCd(String antmclLossTypeCd) {
		this.antmclLossTypeCd = antmclLossTypeCd;
	}

	@Basic()
	@Column(name="BENE_BRTHDY_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getBeneBrthdyDt() {
		return this.beneBrthdyDt;
	}
	public void setBeneBrthdyDt(Date beneBrthdyDt) {
		this.beneBrthdyDt = beneBrthdyDt;
	}

	@Basic()
	@Column(name="BENE_DEATH_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getBeneDeathDt() {
		return this.beneDeathDt;
	}
	public void setBeneDeathDt(Date beneDeathDt) {
		this.beneDeathDt = beneDeathDt;
	}

	@Basic()
	@Column(name="BENE_FIRST_NM", length=30)
	public String getBeneFirstNm() {
		return this.beneFirstNm;
	}
	public void setBeneFirstNm(String beneFirstNm) {
		this.beneFirstNm = beneFirstNm;
	}

	@Basic()
	@Column(name="BENE_GENDER_CD", length=9)
	public String getBeneGenderCd() {
		return this.beneGenderCd;
	}
	public void setBeneGenderCd(String beneGenderCd) {
		this.beneGenderCd = beneGenderCd;
	}

	@Basic()
	@Column(name="BENE_LAST_NM", length=30)
	public String getBeneLastNm() {
		return this.beneLastNm;
	}
	public void setBeneLastNm(String beneLastNm) {
		this.beneLastNm = beneLastNm;
	}

	@Basic()
	@Column(name="BENE_MIDDLE_NM", length=30)
	public String getBeneMiddleNm() {
		return this.beneMiddleNm;
	}
	public void setBeneMiddleNm(String beneMiddleNm) {
		this.beneMiddleNm = beneMiddleNm;
	}

	@Basic()
	@Column(name="BENE_SSN_NBR", length=9)
	public String getBeneSsnNbr() {
		return this.beneSsnNbr;
	}
	public void setBeneSsnNbr(String beneSsnNbr) {
		this.beneSsnNbr = beneSsnNbr;
	}

	@Basic()
	@Column(name="BENE_SSN_VRFCTN_STATUS_TYPE_CD", length=12)
	public String getBeneSsnVrfctnStatusTypeCd() {
		return this.beneSsnVrfctnStatusTypeCd;
	}
	public void setBeneSsnVrfctnStatusTypeCd(String beneSsnVrfctnStatusTypeCd) {
		this.beneSsnVrfctnStatusTypeCd = beneSsnVrfctnStatusTypeCd;
	}

	@Basic()
	@Column(name="BENE_SUFFIX_NM", length=3)
	public String getBeneSuffixNm() {
		return this.beneSuffixNm;
	}
	public void setBeneSuffixNm(String beneSuffixNm) {
		this.beneSuffixNm = beneSuffixNm;
	}

	@Basic()
	@Column(name="BLIND_IND", length=1)
	public String getBlindInd() {
		return this.blindInd;
	}
	public void setBlindInd(String blindInd) {
		this.blindInd = blindInd;
	}

	@Basic()
	@Column(name="CMPTNY_DECN_TYPE_CD", length=12)
	public String getCmptnyDecnTypeCd() {
		return this.cmptnyDecnTypeCd;
	}
	public void setCmptnyDecnTypeCd(String cmptnyDecnTypeCd) {
		this.cmptnyDecnTypeCd = cmptnyDecnTypeCd;
	}

	@Basic()
	@Column(name="EOD_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEodDt() {
		return this.eodDt;
	}
	public void setEodDt(Date eodDt) {
		this.eodDt = eodDt;
	}

	@Basic()
	@Column(name="FID_DECN_CATEGY_TYPE_CD", length=12)
	public String getFidDecnCategyTypeCd() {
		return this.fidDecnCategyTypeCd;
	}
	public void setFidDecnCategyTypeCd(String fidDecnCategyTypeCd) {
		this.fidDecnCategyTypeCd = fidDecnCategyTypeCd;
	}

	@Basic()
	@Column(name="GULF_WAR_THEATR_CD", length=1)
	public String getGulfWarTheatrCd() {
		return this.gulfWarTheatrCd;
	}
	public void setGulfWarTheatrCd(String gulfWarTheatrCd) {
		this.gulfWarTheatrCd = gulfWarTheatrCd;
	}

	@Basic()
	@Column(name="IG_AUDIT_NBR", length=9)
	public String getIgAuditNbr() {
		return this.igAuditNbr;
	}
	public void setIgAuditNbr(String igAuditNbr) {
		this.igAuditNbr = igAuditNbr;
	}

	@Basic()
	@Column(name="INS_FILE_NBR", length=10)
	public String getInsFileNbr() {
		return this.insFileNbr;
	}
	public void setInsFileNbr(String insFileNbr) {
		this.insFileNbr = insFileNbr;
	}

	@Basic()
	@Column(name="INS_LOAN_NBR", length=10)
	public String getInsLoanNbr() {
		return this.insLoanNbr;
	}
	public void setInsLoanNbr(String insLoanNbr) {
		this.insLoanNbr = insLoanNbr;
	}

	@Basic()
	@Column(name="LOSS_USE_TYPE_CD", length=12)
	public String getLossUseTypeCd() {
		return this.lossUseTypeCd;
	}
	public void setLossUseTypeCd(String lossUseTypeCd) {
		this.lossUseTypeCd = lossUseTypeCd;
	}

	@Basic()
	@Column(name="MEDCAD_CD", length=1)
	public String getMedcadCd() {
		return this.medcadCd;
	}
	public void setMedcadCd(String medcadCd) {
		this.medcadCd = medcadCd;
	}

	@Basic()
	@Column(name="MED_FCLTY_NBR", length=9)
	public String getMedFcltyNbr() {
		return this.medFcltyNbr;
	}
	public void setMedFcltyNbr(String medFcltyNbr) {
		this.medFcltyNbr = medFcltyNbr;
	}

	@Basic()
	@Column(name="MLTY_SEPRTN_REASON_TYPE_NM", length=50)
	public String getMltySeprtnReasonTypeNm() {
		return this.mltySeprtnReasonTypeNm;
	}
	public void setMltySeprtnReasonTypeNm(String mltySeprtnReasonTypeNm) {
		this.mltySeprtnReasonTypeNm = mltySeprtnReasonTypeNm;
	}

	@Basic()
	@Column(name="MLTY_SVC_BRANCH_TYPE_NM", length=50)
	public String getMltySvcBranchTypeNm() {
		return this.mltySvcBranchTypeNm;
	}
	public void setMltySvcBranchTypeNm(String mltySvcBranchTypeNm) {
		this.mltySvcBranchTypeNm = mltySvcBranchTypeNm;
	}

	@Basic()
	@Column(name="MLTY_SVC_OTHER_BRANCH_TYPE_NM", length=50)
	public String getMltySvcOtherBranchTypeNm() {
		return this.mltySvcOtherBranchTypeNm;
	}
	public void setMltySvcOtherBranchTypeNm(String mltySvcOtherBranchTypeNm) {
		this.mltySvcOtherBranchTypeNm = mltySvcOtherBranchTypeNm;
	}

	@Basic()
	@Column(name="MLTY_TOUR_SVC_STATUS_TYPE_NM", length=50)
	public String getMltyTourSvcStatusTypeNm() {
		return this.mltyTourSvcStatusTypeNm;
	}
	public void setMltyTourSvcStatusTypeNm(String mltyTourSvcStatusTypeNm) {
		this.mltyTourSvcStatusTypeNm = mltyTourSvcStatusTypeNm;
	}

	@Basic()
	@Column(name="MP_DSCHRG_CHAR_TYPE_NM", length=50)
	public String getMpDschrgCharTypeNm() {
		return this.mpDschrgCharTypeNm;
	}
	public void setMpDschrgCharTypeNm(String mpDschrgCharTypeNm) {
		this.mpDschrgCharTypeNm = mpDschrgCharTypeNm;
	}

	@Basic()
	@Column(name="NURSNG_HOME_IND", length=1)
	public String getNursngHomeInd() {
		return this.nursngHomeInd;
	}
	public void setNursngHomeInd(String nursngHomeInd) {
		this.nursngHomeInd = nursngHomeInd;
	}

	@Basic()
	@Column(name="OTHER_LOSS_TYPE_CD", length=12)
	public String getOtherLossTypeCd() {
		return this.otherLossTypeCd;
	}
	public void setOtherLossTypeCd(String otherLossTypeCd) {
		this.otherLossTypeCd = otherLossTypeCd;
	}

	@Basic()
	@Column(name="PAY_GRADE_CD", length=4)
	public String getPayGradeCd() {
		return this.payGradeCd;
	}
	public void setPayGradeCd(String payGradeCd) {
		this.payGradeCd = payGradeCd;
	}

	@Basic()
	@Column(name="POW_CD", length=2)
	public String getPowCd() {
		return this.powCd;
	}
	public void setPowCd(String powCd) {
		this.powCd = powCd;
	}

	@Basic()
	@Column(name="PRPLGC_HOUSNG_NBR", length=9)
	public String getPrplgcHousngNbr() {
		return this.prplgcHousngNbr;
	}
	public void setPrplgcHousngNbr(String prplgcHousngNbr) {
		this.prplgcHousngNbr = prplgcHousngNbr;
	}

	@Basic()
	@Column(name="RAD_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getRadDt() {
		return this.radDt;
	}
	public void setRadDt(Date radDt) {
		this.radDt = radDt;
	}

	@Basic()
	@Column(name="RSRVST_DAYS_NBR", precision=3)
	public Integer getRsrvstDaysNbr() {
		return this.rsrvstDaysNbr;
	}
	public void setRsrvstDaysNbr(Integer rsrvstDaysNbr) {
		this.rsrvstDaysNbr = rsrvstDaysNbr;
	}

	@Basic()
	@Column(name="SVC_NBR", length=9)
	public String getSvcNbr() {
		return this.svcNbr;
	}
	public void setSvcNbr(String svcNbr) {
		this.svcNbr = svcNbr;
	}

	@Basic()
	@Column(name="TAX_ABTMNT_CD", length=1)
	public String getTaxAbtmntCd() {
		return this.taxAbtmntCd;
	}
	public void setTaxAbtmntCd(String taxAbtmntCd) {
		this.taxAbtmntCd = taxAbtmntCd;
	}

	@Basic()
	@Column(name="VERIFD_IND", length=1)
	public String getVerifdInd() {
		return this.verifdInd;
	}
	public void setVerifdInd(String verifdInd) {
		this.verifdInd = verifdInd;
	}

	@Basic()
	@Column(name="VET_BRTHDY_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getVetBrthdyDt() {
		return this.vetBrthdyDt;
	}
	public void setVetBrthdyDt(Date vetBrthdyDt) {
		this.vetBrthdyDt = vetBrthdyDt;
	}

	@Basic()
	@Column(name="VET_DEATH_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getVetDeathDt() {
		return this.vetDeathDt;
	}
	public void setVetDeathDt(Date vetDeathDt) {
		this.vetDeathDt = vetDeathDt;
	}

	@Basic()
	@Column(name="VET_FIRST_NM", length=30)
	public String getVetFirstNm() {
		return this.vetFirstNm;
	}
	public void setVetFirstNm(String vetFirstNm) {
		this.vetFirstNm = vetFirstNm;
	}

	@Basic()
	@Column(name="VET_GENDER_CD", length=9)
	public String getVetGenderCd() {
		return this.vetGenderCd;
	}
	public void setVetGenderCd(String vetGenderCd) {
		this.vetGenderCd = vetGenderCd;
	}

	@Basic()
	@Column(name="VET_LAST_NM", length=30)
	public String getVetLastNm() {
		return this.vetLastNm;
	}
	public void setVetLastNm(String vetLastNm) {
		this.vetLastNm = vetLastNm;
	}

	@Basic()
	@Column(name="VET_MIDDLE_NM", length=30)
	public String getVetMiddleNm() {
		return this.vetMiddleNm;
	}
	public void setVetMiddleNm(String vetMiddleNm) {
		this.vetMiddleNm = vetMiddleNm;
	}

	@Basic()
	@Column(name="VET_SSN_NBR", length=9)
	public String getVetSsnNbr() {
		return this.vetSsnNbr;
	}
	public void setVetSsnNbr(String vetSsnNbr) {
		this.vetSsnNbr = vetSsnNbr;
	}

	@Basic()
	@Column(name="VET_SSN_VRFCTN_STATUS_TYPE_CD", length=12)
	public String getVetSsnVrfctnStatusTypeCd() {
		return this.vetSsnVrfctnStatusTypeCd;
	}
	public void setVetSsnVrfctnStatusTypeCd(String vetSsnVrfctnStatusTypeCd) {
		this.vetSsnVrfctnStatusTypeCd = vetSsnVrfctnStatusTypeCd;
	}

	@Basic()
	@Column(name="VET_SUFFIX_NM", length=3)
	public String getVetSuffixNm() {
		return this.vetSuffixNm;
	}
	public void setVetSuffixNm(String vetSuffixNm) {
		this.vetSuffixNm = vetSuffixNm;
	}

	@Basic()
	@Column(name="VET_TO_VET_MARAGE_CD", length=2)
	public String getVetToVetMarageCd() {
		return this.vetToVetMarageCd;
	}
	public void setVetToVetMarageCd(String vetToVetMarageCd) {
		this.vetToVetMarageCd = vetToVetMarageCd;
	}

	@Basic()
	@Column(name="VIETNM_THEATR_IND", length=1)
	public String getVietnmTheatrInd() {
		return this.vietnmTheatrInd;
	}
	public void setVietnmTheatrInd(String vietnmTheatrInd) {
		this.vietnmTheatrInd = vietnmTheatrInd;
	}

	@Basic()
	@Column(name="WAR_TIME_SVC_IND", length=1)
	public String getWarTimeSvcInd() {
		return this.warTimeSvcInd;
	}
	public void setWarTimeSvcInd(String warTimeSvcInd) {
		this.warTimeSvcInd = warTimeSvcInd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExtendStatcl)) {
			return false;
		}
		ExtendStatcl castOther = (ExtendStatcl)other;
		return new EqualsBuilder()
			.append(this.getExtendAwardCmpsitId(), castOther.getExtendAwardCmpsitId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getExtendAwardCmpsitId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("extendAwardCmpsitId", getExtendAwardCmpsitId())
			.toString();
	}
}