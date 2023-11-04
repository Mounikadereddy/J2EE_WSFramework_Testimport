package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the CLAIM_DVLPMT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="CLAIM_DVLPMT")
public class ClaimDvlpmt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long bnftClaimId;
	private java.sql.Timestamp accdntDt;
	private java.sql.Timestamp ionzngRdtnAsgnmtEndDt;
	private java.sql.Timestamp ionzngRdtnAsgnmtStartDt;
	private String ionzngRdtnDutyDescpTxt;
	private String ionzngRdtnExpsdOtherTxt;
	private String ionzngRdtnExpsdTypeCd;
	private java.sql.Timestamp ionzngRdtnLastExpsrDt;
	private String ionzngRdtnUnitTxt;
	private String mustrdGasChemclTypeCd;
	private String mustrdGasExpsdTypeCd;
	private java.sql.Timestamp mustrdGasExpsrDt;
	private String mustrdGasUnitTxt;
	private String ptsdCombatInd;
	private String ptsdPerslAsaultInd;
	private String ptsdPowOtherInd;
	private String rulesDvlpmtReqstdInd;
	private java.sql.Timestamp seprtnRtrmntExamDt;
	private String seprtnRtrmntExamInd;
	private BnftClaim bnftClaim;
    private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ClaimDvlpmt() {
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BNFT_CLAIM_ID", unique=true, nullable=false, precision=15)
	public Long getBnftClaimId() {
		return this.bnftClaimId;
	}
	public void setBnftClaimId(Long bnftClaimId) {
		this.bnftClaimId = bnftClaimId;
	}

	@Basic()
	@Column(name="ACCDNT_DT", length=7)
	public java.sql.Timestamp getAccdntDt() {
		return this.accdntDt;
	}
	public void setAccdntDt(java.sql.Timestamp accdntDt) {
		this.accdntDt = accdntDt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_ASGNMT_END_DT", length=7)
	public java.sql.Timestamp getIonzngRdtnAsgnmtEndDt() {
		return this.ionzngRdtnAsgnmtEndDt;
	}
	public void setIonzngRdtnAsgnmtEndDt(java.sql.Timestamp ionzngRdtnAsgnmtEndDt) {
		this.ionzngRdtnAsgnmtEndDt = ionzngRdtnAsgnmtEndDt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_ASGNMT_START_DT", length=7)
	public java.sql.Timestamp getIonzngRdtnAsgnmtStartDt() {
		return this.ionzngRdtnAsgnmtStartDt;
	}
	public void setIonzngRdtnAsgnmtStartDt(java.sql.Timestamp ionzngRdtnAsgnmtStartDt) {
		this.ionzngRdtnAsgnmtStartDt = ionzngRdtnAsgnmtStartDt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_DUTY_DESCP_TXT", length=120)
	public String getIonzngRdtnDutyDescpTxt() {
		return this.ionzngRdtnDutyDescpTxt;
	}
	public void setIonzngRdtnDutyDescpTxt(String ionzngRdtnDutyDescpTxt) {
		this.ionzngRdtnDutyDescpTxt = ionzngRdtnDutyDescpTxt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_EXPSD_OTHER_TXT", length=35)
	public String getIonzngRdtnExpsdOtherTxt() {
		return this.ionzngRdtnExpsdOtherTxt;
	}
	public void setIonzngRdtnExpsdOtherTxt(String ionzngRdtnExpsdOtherTxt) {
		this.ionzngRdtnExpsdOtherTxt = ionzngRdtnExpsdOtherTxt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_EXPSD_TYPE_CD", length=12)
	public String getIonzngRdtnExpsdTypeCd() {
		return this.ionzngRdtnExpsdTypeCd;
	}
	public void setIonzngRdtnExpsdTypeCd(String ionzngRdtnExpsdTypeCd) {
		this.ionzngRdtnExpsdTypeCd = ionzngRdtnExpsdTypeCd;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_LAST_EXPSR_DT", length=7)
	public java.sql.Timestamp getIonzngRdtnLastExpsrDt() {
		return this.ionzngRdtnLastExpsrDt;
	}
	public void setIonzngRdtnLastExpsrDt(java.sql.Timestamp ionzngRdtnLastExpsrDt) {
		this.ionzngRdtnLastExpsrDt = ionzngRdtnLastExpsrDt;
	}

	@Basic()
	@Column(name="IONZNG_RDTN_UNIT_TXT", length=50)
	public String getIonzngRdtnUnitTxt() {
		return this.ionzngRdtnUnitTxt;
	}
	public void setIonzngRdtnUnitTxt(String ionzngRdtnUnitTxt) {
		this.ionzngRdtnUnitTxt = ionzngRdtnUnitTxt;
	}

	@Basic()
	@Column(name="MUSTRD_GAS_CHEMCL_TYPE_CD", length=12)
	public String getMustrdGasChemclTypeCd() {
		return this.mustrdGasChemclTypeCd;
	}
	public void setMustrdGasChemclTypeCd(String mustrdGasChemclTypeCd) {
		this.mustrdGasChemclTypeCd = mustrdGasChemclTypeCd;
	}

	@Basic()
	@Column(name="MUSTRD_GAS_EXPSD_TYPE_CD", length=12)
	public String getMustrdGasExpsdTypeCd() {
		return this.mustrdGasExpsdTypeCd;
	}
	public void setMustrdGasExpsdTypeCd(String mustrdGasExpsdTypeCd) {
		this.mustrdGasExpsdTypeCd = mustrdGasExpsdTypeCd;
	}

	@Basic()
	@Column(name="MUSTRD_GAS_EXPSR_DT", length=7)
	public java.sql.Timestamp getMustrdGasExpsrDt() {
		return this.mustrdGasExpsrDt;
	}
	public void setMustrdGasExpsrDt(java.sql.Timestamp mustrdGasExpsrDt) {
		this.mustrdGasExpsrDt = mustrdGasExpsrDt;
	}

	@Basic()
	@Column(name="MUSTRD_GAS_UNIT_TXT", length=50)
	public String getMustrdGasUnitTxt() {
		return this.mustrdGasUnitTxt;
	}
	public void setMustrdGasUnitTxt(String mustrdGasUnitTxt) {
		this.mustrdGasUnitTxt = mustrdGasUnitTxt;
	}

	@Basic()
	@Column(name="PTSD_COMBAT_IND", length=1)
	public String getPtsdCombatInd() {
		return this.ptsdCombatInd;
	}
	public void setPtsdCombatInd(String ptsdCombatInd) {
		this.ptsdCombatInd = ptsdCombatInd;
	}

	@Basic()
	@Column(name="PTSD_PERSL_ASAULT_IND", length=1)
	public String getPtsdPerslAsaultInd() {
		return this.ptsdPerslAsaultInd;
	}
	public void setPtsdPerslAsaultInd(String ptsdPerslAsaultInd) {
		this.ptsdPerslAsaultInd = ptsdPerslAsaultInd;
	}

	@Basic()
	@Column(name="PTSD_POW_OTHER_IND", length=1)
	public String getPtsdPowOtherInd() {
		return this.ptsdPowOtherInd;
	}
	public void setPtsdPowOtherInd(String ptsdPowOtherInd) {
		this.ptsdPowOtherInd = ptsdPowOtherInd;
	}

	@Basic()
	@Column(name="RULES_DVLPMT_REQSTD_IND", length=1)
	public String getRulesDvlpmtReqstdInd() {
		return this.rulesDvlpmtReqstdInd;
	}
	public void setRulesDvlpmtReqstdInd(String rulesDvlpmtReqstdInd) {
		this.rulesDvlpmtReqstdInd = rulesDvlpmtReqstdInd;
	}

	@Basic()
	@Column(name="SEPRTN_RTRMNT_EXAM_DT", length=7)
	public java.sql.Timestamp getSeprtnRtrmntExamDt() {
		return this.seprtnRtrmntExamDt;
	}
	public void setSeprtnRtrmntExamDt(java.sql.Timestamp seprtnRtrmntExamDt) {
		this.seprtnRtrmntExamDt = seprtnRtrmntExamDt;
	}

	@Basic()
	@Column(name="SEPRTN_RTRMNT_EXAM_IND", length=1)
	public String getSeprtnRtrmntExamInd() {
		return this.seprtnRtrmntExamInd;
	}
	public void setSeprtnRtrmntExamInd(String seprtnRtrmntExamInd) {
		this.seprtnRtrmntExamInd = seprtnRtrmntExamInd;
	}

	//bi-directional one-to-one association to BnftClaim
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BNFT_CLAIM_ID", referencedColumnName="BNFT_CLAIM_ID", nullable=false, insertable=false, updatable=false)
	public BnftClaim getBnftClaim() {
		return this.bnftClaim;
	}
	public void setBnftClaim(BnftClaim bnftClaim) {
		this.bnftClaim = bnftClaim;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClaimDvlpmt)) {
			return false;
		}
		ClaimDvlpmt castOther = (ClaimDvlpmt)other;
		return new EqualsBuilder()
			.append(this.getBnftClaimId(), castOther.getBnftClaimId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBnftClaimId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("bnftClaimId", getBnftClaimId())
			.toString();
	}
}