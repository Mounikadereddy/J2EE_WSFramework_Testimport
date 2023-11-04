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
 * The persistent class for the CPMR_STATCL_SGMNT database table.
 * 
 * @author BEA Workshop
 */
@SqlResultSetMapping(name="statclSgmntMapping", entities={@EntityResult(entityClass=CpmrStatclSgmnt.class )})
@Entity()
@Table(name="CPMR_STATCL_SGMNT")
public class CpmrStatclSgmnt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long cpmrBasicSgmntId;
	private String aprtmtSpouseBirthDate;
	private Date cnvrsnDt;
	private String fileNbr;
	private String payeeNbr;
	private Long ptcpntId;
	private String statclAddtnlSvc;
	private String statclAprtmtSpouseName;
	private String statclAFiller;
	private String statclBlindIndctr;
	private String statclBranchSvc;
	private String statclBFiller;
	private String statclCmptny;
	private String statclCombatDsblty;
	private String statclCFiller;
	private String statclDeathAge;
	private String statclDeathDate;
	private String statclEmplby;
	private String statclEodDate;
	private String statclFatherBirthDate;
	private String statclHsptlSmcCode;
	private String statclLossAntmcl;
	private String statclLossUse;
	private String statclMedcad;
	private String statclMedcadSpouse;
	private String statclMiscCode;
	private String statclMotherBirthDate;
	private String statclOtherLoss;
	private String statclPayeeBirthDate;
	private String statclPymtCode;
	private String statclRadDate;
	private String statclRatingCode12;
	private String statclSpeclComp;
	private String statclSpeclPrvsn;
	private String statclSpouseAa;
	private String statclSpouseBirthDate;
	private String statclSpouseName;
	private String statclSvcGrade;
	private String statclThirdPartyBirthDate;
	private String statclThirdPartyFiller;
	private String statclThirdPartyName;
	private String statclVetBirthDate;
	private String statclVetMardVet;
	private String statclVetName;
	private String statclVetSex;

    public CpmrStatclSgmnt() {
    }

	@Id()
	@Column(name="CPMR_BASIC_SGMNT_ID", unique=true, nullable=false, precision=15)
	public Long getCpmrBasicSgmntId() {
		return this.cpmrBasicSgmntId;
	}
	public void setCpmrBasicSgmntId(Long cpmrBasicSgmntId) {
		this.cpmrBasicSgmntId = cpmrBasicSgmntId;
	}

	@Basic()
	@Column(name="APRTMT_SPOUSE_BIRTH_DATE", length=8)
	public String getAprtmtSpouseBirthDate() {
		return this.aprtmtSpouseBirthDate;
	}
	public void setAprtmtSpouseBirthDate(String aprtmtSpouseBirthDate) {
		this.aprtmtSpouseBirthDate = aprtmtSpouseBirthDate;
	}

	@Basic()
	@Column(name="CNVRSN_DT", nullable=false, length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCnvrsnDt() {
		return this.cnvrsnDt;
	}
	public void setCnvrsnDt(Date cnvrsnDt) {
		this.cnvrsnDt = cnvrsnDt;
	}

	@Basic()
	@Column(name="FILE_NBR", nullable=false, length=9)
	public String getFileNbr() {
		return this.fileNbr;
	}
	public void setFileNbr(String fileNbr) {
		this.fileNbr = fileNbr;
	}

	@Basic()
	@Column(name="PAYEE_NBR", nullable=false, length=2)
	public String getPayeeNbr() {
		return this.payeeNbr;
	}
	public void setPayeeNbr(String payeeNbr) {
		this.payeeNbr = payeeNbr;
	}

	@Basic()
	@Column(name="PTCPNT_ID", precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="STATCL_ADDTNL_SVC", length=1)
	public String getStatclAddtnlSvc() {
		return this.statclAddtnlSvc;
	}
	public void setStatclAddtnlSvc(String statclAddtnlSvc) {
		this.statclAddtnlSvc = statclAddtnlSvc;
	}

	@Basic()
	@Column(name="STATCL_APRTMT_SPOUSE_NAME", length=10)
	public String getStatclAprtmtSpouseName() {
		return this.statclAprtmtSpouseName;
	}
	public void setStatclAprtmtSpouseName(String statclAprtmtSpouseName) {
		this.statclAprtmtSpouseName = statclAprtmtSpouseName;
	}

	@Basic()
	@Column(name="STATCL_A_FILLER", length=2)
	public String getStatclAFiller() {
		return this.statclAFiller;
	}
	public void setStatclAFiller(String statclAFiller) {
		this.statclAFiller = statclAFiller;
	}

	@Basic()
	@Column(name="STATCL_BLIND_INDCTR", length=1)
	public String getStatclBlindIndctr() {
		return this.statclBlindIndctr;
	}
	public void setStatclBlindIndctr(String statclBlindIndctr) {
		this.statclBlindIndctr = statclBlindIndctr;
	}

	@Basic()
	@Column(name="STATCL_BRANCH_SVC", length=1)
	public String getStatclBranchSvc() {
		return this.statclBranchSvc;
	}
	public void setStatclBranchSvc(String statclBranchSvc) {
		this.statclBranchSvc = statclBranchSvc;
	}

	@Basic()
	@Column(name="STATCL_B_FILLER", length=12)
	public String getStatclBFiller() {
		return this.statclBFiller;
	}
	public void setStatclBFiller(String statclBFiller) {
		this.statclBFiller = statclBFiller;
	}

	@Basic()
	@Column(name="STATCL_CMPTNY", length=1)
	public String getStatclCmptny() {
		return this.statclCmptny;
	}
	public void setStatclCmptny(String statclCmptny) {
		this.statclCmptny = statclCmptny;
	}

	@Basic()
	@Column(name="STATCL_COMBAT_DSBLTY", length=1)
	public String getStatclCombatDsblty() {
		return this.statclCombatDsblty;
	}
	public void setStatclCombatDsblty(String statclCombatDsblty) {
		this.statclCombatDsblty = statclCombatDsblty;
	}

	@Basic()
	@Column(name="STATCL_C_FILLER", length=66)
	public String getStatclCFiller() {
		return this.statclCFiller;
	}
	public void setStatclCFiller(String statclCFiller) {
		this.statclCFiller = statclCFiller;
	}

	@Basic()
	@Column(name="STATCL_DEATH_AGE", length=2)
	public String getStatclDeathAge() {
		return this.statclDeathAge;
	}
	public void setStatclDeathAge(String statclDeathAge) {
		this.statclDeathAge = statclDeathAge;
	}

	@Basic()
	@Column(name="STATCL_DEATH_DATE", length=8)
	public String getStatclDeathDate() {
		return this.statclDeathDate;
	}
	public void setStatclDeathDate(String statclDeathDate) {
		this.statclDeathDate = statclDeathDate;
	}

	@Basic()
	@Column(name="STATCL_EMPLBY", length=1)
	public String getStatclEmplby() {
		return this.statclEmplby;
	}
	public void setStatclEmplby(String statclEmplby) {
		this.statclEmplby = statclEmplby;
	}

	@Basic()
	@Column(name="STATCL_EOD_DATE", length=8)
	public String getStatclEodDate() {
		return this.statclEodDate;
	}
	public void setStatclEodDate(String statclEodDate) {
		this.statclEodDate = statclEodDate;
	}

	@Basic()
	@Column(name="STATCL_FATHER_BIRTH_DATE", length=8)
	public String getStatclFatherBirthDate() {
		return this.statclFatherBirthDate;
	}
	public void setStatclFatherBirthDate(String statclFatherBirthDate) {
		this.statclFatherBirthDate = statclFatherBirthDate;
	}

	@Basic()
	@Column(name="STATCL_HSPTL_SMC_CODE", length=2)
	public String getStatclHsptlSmcCode() {
		return this.statclHsptlSmcCode;
	}
	public void setStatclHsptlSmcCode(String statclHsptlSmcCode) {
		this.statclHsptlSmcCode = statclHsptlSmcCode;
	}

	@Basic()
	@Column(name="STATCL_LOSS_ANTMCL", length=2)
	public String getStatclLossAntmcl() {
		return this.statclLossAntmcl;
	}
	public void setStatclLossAntmcl(String statclLossAntmcl) {
		this.statclLossAntmcl = statclLossAntmcl;
	}

	@Basic()
	@Column(name="STATCL_LOSS_USE", length=2)
	public String getStatclLossUse() {
		return this.statclLossUse;
	}
	public void setStatclLossUse(String statclLossUse) {
		this.statclLossUse = statclLossUse;
	}

	@Basic()
	@Column(name="STATCL_MEDCAD", length=1)
	public String getStatclMedcad() {
		return this.statclMedcad;
	}
	public void setStatclMedcad(String statclMedcad) {
		this.statclMedcad = statclMedcad;
	}

	@Basic()
	@Column(name="STATCL_MEDCAD_SPOUSE", length=1)
	public String getStatclMedcadSpouse() {
		return this.statclMedcadSpouse;
	}
	public void setStatclMedcadSpouse(String statclMedcadSpouse) {
		this.statclMedcadSpouse = statclMedcadSpouse;
	}

	@Basic()
	@Column(name="STATCL_MISC_CODE", length=2)
	public String getStatclMiscCode() {
		return this.statclMiscCode;
	}
	public void setStatclMiscCode(String statclMiscCode) {
		this.statclMiscCode = statclMiscCode;
	}

	@Basic()
	@Column(name="STATCL_MOTHER_BIRTH_DATE", length=8)
	public String getStatclMotherBirthDate() {
		return this.statclMotherBirthDate;
	}
	public void setStatclMotherBirthDate(String statclMotherBirthDate) {
		this.statclMotherBirthDate = statclMotherBirthDate;
	}

	@Basic()
	@Column(name="STATCL_OTHER_LOSS", length=1)
	public String getStatclOtherLoss() {
		return this.statclOtherLoss;
	}
	public void setStatclOtherLoss(String statclOtherLoss) {
		this.statclOtherLoss = statclOtherLoss;
	}

	@Basic()
	@Column(name="STATCL_PAYEE_BIRTH_DATE", length=8)
	public String getStatclPayeeBirthDate() {
		return this.statclPayeeBirthDate;
	}
	public void setStatclPayeeBirthDate(String statclPayeeBirthDate) {
		this.statclPayeeBirthDate = statclPayeeBirthDate;
	}

	@Basic()
	@Column(name="STATCL_PYMT_CODE", length=1)
	public String getStatclPymtCode() {
		return this.statclPymtCode;
	}
	public void setStatclPymtCode(String statclPymtCode) {
		this.statclPymtCode = statclPymtCode;
	}

	@Basic()
	@Column(name="STATCL_RAD_DATE", length=8)
	public String getStatclRadDate() {
		return this.statclRadDate;
	}
	public void setStatclRadDate(String statclRadDate) {
		this.statclRadDate = statclRadDate;
	}

	@Basic()
	@Column(name="STATCL_RATING_CODE_12", length=2)
	public String getStatclRatingCode12() {
		return this.statclRatingCode12;
	}
	public void setStatclRatingCode12(String statclRatingCode12) {
		this.statclRatingCode12 = statclRatingCode12;
	}

	@Basic()
	@Column(name="STATCL_SPECL_COMP", length=2)
	public String getStatclSpeclComp() {
		return this.statclSpeclComp;
	}
	public void setStatclSpeclComp(String statclSpeclComp) {
		this.statclSpeclComp = statclSpeclComp;
	}

	@Basic()
	@Column(name="STATCL_SPECL_PRVSN", length=1)
	public String getStatclSpeclPrvsn() {
		return this.statclSpeclPrvsn;
	}
	public void setStatclSpeclPrvsn(String statclSpeclPrvsn) {
		this.statclSpeclPrvsn = statclSpeclPrvsn;
	}

	@Basic()
	@Column(name="STATCL_SPOUSE_AA", length=1)
	public String getStatclSpouseAa() {
		return this.statclSpouseAa;
	}
	public void setStatclSpouseAa(String statclSpouseAa) {
		this.statclSpouseAa = statclSpouseAa;
	}

	@Basic()
	@Column(name="STATCL_SPOUSE_BIRTH_DATE", length=8)
	public String getStatclSpouseBirthDate() {
		return this.statclSpouseBirthDate;
	}
	public void setStatclSpouseBirthDate(String statclSpouseBirthDate) {
		this.statclSpouseBirthDate = statclSpouseBirthDate;
	}

	@Basic()
	@Column(name="STATCL_SPOUSE_NAME", length=10)
	public String getStatclSpouseName() {
		return this.statclSpouseName;
	}
	public void setStatclSpouseName(String statclSpouseName) {
		this.statclSpouseName = statclSpouseName;
	}

	@Basic()
	@Column(name="STATCL_SVC_GRADE", length=2)
	public String getStatclSvcGrade() {
		return this.statclSvcGrade;
	}
	public void setStatclSvcGrade(String statclSvcGrade) {
		this.statclSvcGrade = statclSvcGrade;
	}

	@Basic()
	@Column(name="STATCL_THIRD_PARTY_BIRTH_DATE", length=8)
	public String getStatclThirdPartyBirthDate() {
		return this.statclThirdPartyBirthDate;
	}
	public void setStatclThirdPartyBirthDate(String statclThirdPartyBirthDate) {
		this.statclThirdPartyBirthDate = statclThirdPartyBirthDate;
	}

	@Basic()
	@Column(name="STATCL_THIRD_PARTY_FILLER", length=12)
	public String getStatclThirdPartyFiller() {
		return this.statclThirdPartyFiller;
	}
	public void setStatclThirdPartyFiller(String statclThirdPartyFiller) {
		this.statclThirdPartyFiller = statclThirdPartyFiller;
	}

	@Basic()
	@Column(name="STATCL_THIRD_PARTY_NAME", length=7)
	public String getStatclThirdPartyName() {
		return this.statclThirdPartyName;
	}
	public void setStatclThirdPartyName(String statclThirdPartyName) {
		this.statclThirdPartyName = statclThirdPartyName;
	}

	@Basic()
	@Column(name="STATCL_VET_BIRTH_DATE", length=8)
	public String getStatclVetBirthDate() {
		return this.statclVetBirthDate;
	}
	public void setStatclVetBirthDate(String statclVetBirthDate) {
		this.statclVetBirthDate = statclVetBirthDate;
	}

	@Basic()
	@Column(name="STATCL_VET_MARD_VET", length=1)
	public String getStatclVetMardVet() {
		return this.statclVetMardVet;
	}
	public void setStatclVetMardVet(String statclVetMardVet) {
		this.statclVetMardVet = statclVetMardVet;
	}

	@Basic()
	@Column(name="STATCL_VET_NAME", length=7)
	public String getStatclVetName() {
		return this.statclVetName;
	}
	public void setStatclVetName(String statclVetName) {
		this.statclVetName = statclVetName;
	}

	@Basic()
	@Column(name="STATCL_VET_SEX", length=1)
	public String getStatclVetSex() {
		return this.statclVetSex;
	}
	public void setStatclVetSex(String statclVetSex) {
		this.statclVetSex = statclVetSex;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CpmrStatclSgmnt)) {
			return false;
		}
		CpmrStatclSgmnt castOther = (CpmrStatclSgmnt)other;
		return new EqualsBuilder()
			.append(this.getCpmrBasicSgmntId(), castOther.getCpmrBasicSgmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCpmrBasicSgmntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("cpmrBasicSgmntId", getCpmrBasicSgmntId())
			.toString();
	}
}