package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the AWARD database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="AWARD")
@SqlResultSetMapping(name="awardMapping", entities={@EntityResult(entityClass=Award.class )})
public class Award implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private AwardPK compId;
	private String birthDefectTxt;
//	private long bnftClaimId;
	private java.sql.Timestamp cntrlDt;
//	private java.sql.Timestamp createDt;
//	private java.sql.Timestamp decnDt;
//	private String entitlementTypeCd;
	private String evrAdjsmtInd;
	private java.sql.Timestamp orignlClaimDt;
//	private String statusTypeCd;
	private java.sql.Timestamp totalWaiverDt;
//	private java.sql.Timestamp voidDt;
//	private java.util.Set<AcruedDecn> acruedDecns;
//	private java.util.Set<AcruedDecnBene> acruedDecnBenes;
//	private java.util.Set<AltmntDecn> altmntDecns;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
//	private java.util.Set<AwardBene> awardBenes;
//	private java.util.Set<AwardBeneHist> awardBeneHists;
//	private java.util.Set<AwardDecn> awardDecns;
//	private java.util.Set<AwardDiary> awardDiaries;
//	private java.util.Set<AwardEvent> awardEvents;
//	private java.util.Set<AwardMesage> awardMesages;
//	private java.util.Set<AwardOvrideWrksht> awardOvrideWrkshts;
//	private java.util.Set<AwardPtcpnt> awardPtcpnts;
//	private java.util.Set<BasicElgbtyDecn> basicElgbtyDecns;
//	private java.util.Set<BirthDefectDetail> birthDefectDetails;
	private java.util.Set<BurialBeneDecn> burialBeneDecns;
	private java.util.Set<BurialBnftDecn> burialBnftDecns;
	private java.util.Set<BurialSumryDecn> burialSumryDecns;
//	private java.util.Set<ChldrnCustdyDecn> chldrnCustdyDecns;
//	private java.util.Set<ClthngAlwncDecn> clthngAlwncDecns;
//	private java.util.Set<DepncyDecn> depncyDecns;
//	private java.util.Set<DicSpouseDecn> dicSpouseDecns;
//	private java.util.Set<DrillPayDecn> drillPayDecns;
	private java.util.Set<DsbltyPayDecn> dsbltyPayDecns;
//	private java.util.Set<ElectnDecn> electnDecns;
//	private java.util.Set<EvrClaim> evrClaims;
//	private java.util.Set<ExpnsDecn> expnsDecns;
//	private java.util.Set<FinclDecn> finclDecns;
//	private java.util.Set<FutureRecrngPybl> futureRecrngPybls;
	private java.util.Set<IncomeDecn> incomeDecns;
	private java.util.Set<InstznAdjsmtDecn> instznAdjsmtDecns;
//	private java.util.Set<MedalHonorDecn> medalHonorDecns;
//	private java.util.Set<MiscDecn> miscDecns;
//	private java.util.Set<MltyElgbtyDecn> mltyElgbtyDecns;
	private java.util.Set<NetWorthDecn> netWorthDecns;
//	private java.util.Set<OtherWthldgDecn> otherWthldgDecns;
	private java.util.Set<ParentMartlStatusDecn> parentMartlStatusDecns;
//	private java.util.Set<PayRateDecn> payRateDecns;
//	private java.util.Set<PriorsAnchor> priorsAnchors;
//	private java.util.Set<RtrmntPayDecn> rtrmntPayDecns;
//	private java.util.Set<SeprtnPayDecn> seprtnPayDecns;
//	private java.util.Set<SpinaBifidaDtail> spinaBifidaDtails;
//	private java.util.Set<TempAltmntSumry> tempAltmntSumries;
//	private java.util.Set<TempAward> tempAwards;
//	private java.util.Set<TempDepSumry> tempDepSumries;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public Award() {
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
	public AwardPK getCompId() {
		return this.compId;
	}
	public void setCompId(AwardPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BIRTH_DEFECT_TXT", length=2000)
	public String getBirthDefectTxt() {
		return this.birthDefectTxt;
	}
	public void setBirthDefectTxt(String birthDefectTxt) {
		this.birthDefectTxt = birthDefectTxt;
	}

//	@Basic()
//	@Column(name="BNFT_CLAIM_ID", nullable=false, precision=15)
//	public long getBnftClaimId() {
//		return this.bnftClaimId;
//	}
//	public void setBnftClaimId(long bnftClaimId) {
//		this.bnftClaimId = bnftClaimId;
//	}

	@Basic()
	@Column(name="CNTRL_DT", length=7)
	public java.sql.Timestamp getCntrlDt() {
		return this.cntrlDt;
	}
	public void setCntrlDt(java.sql.Timestamp cntrlDt) {
		this.cntrlDt = cntrlDt;
	}

//	@Basic()
//	@Column(name="CREATE_DT", nullable=false, length=7)
//	public java.sql.Timestamp getCreateDt() {
//		return this.createDt;
//	}
//	public void setCreateDt(java.sql.Timestamp createDt) {
//		this.createDt = createDt;
//	}
//
//	@Basic()
//	@Column(name="DECN_DT", length=7)
//	public java.sql.Timestamp getDecnDt() {
//		return this.decnDt;
//	}
//	public void setDecnDt(java.sql.Timestamp decnDt) {
//		this.decnDt = decnDt;
//	}
//
//	@Basic()
//	@Column(name="ENTITLEMENT_TYPE_CD", nullable=false, length=12)
//	public String getEntitlementTypeCd() {
//		return this.entitlementTypeCd;
//	}
//	public void setEntitlementTypeCd(String entitlementTypeCd) {
//		this.entitlementTypeCd = entitlementTypeCd;
//	}

	@Basic()
	@Column(name="EVR_ADJSMT_IND", length=1)
	public String getEvrAdjsmtInd() {
		return this.evrAdjsmtInd;
	}
	public void setEvrAdjsmtInd(String evrAdjsmtInd) {
		this.evrAdjsmtInd = evrAdjsmtInd;
	}

	@Basic()
	@Column(name="ORIGNL_CLAIM_DT", length=7)
	public java.sql.Timestamp getOrignlClaimDt() {
		return this.orignlClaimDt;
	}
	public void setOrignlClaimDt(java.sql.Timestamp orignlClaimDt) {
		this.orignlClaimDt = orignlClaimDt;
	}

//	@Basic()
//	@Column(name="STATUS_TYPE_CD", length=12)
//	public String getStatusTypeCd() {
//		return this.statusTypeCd;
//	}
//	public void setStatusTypeCd(String statusTypeCd) {
//		this.statusTypeCd = statusTypeCd;
//	}

	@Basic()
	@Column(name="TOTAL_WAIVER_DT", length=7)
	public java.sql.Timestamp getTotalWaiverDt() {
		return this.totalWaiverDt;
	}
	public void setTotalWaiverDt(java.sql.Timestamp totalWaiverDt) {
		this.totalWaiverDt = totalWaiverDt;
	}

//	@Basic()
//	@Column(name="VOID_DT", length=7)
//	public java.sql.Timestamp getVoidDt() {
//		return this.voidDt;
//	}
//	public void setVoidDt(java.sql.Timestamp voidDt) {
//		this.voidDt = voidDt;
//	}

//	//bi-directional many-to-one association to AcruedDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AcruedDecn> getAcruedDecns() {
//		return this.acruedDecns;
//	}
//	public void setAcruedDecns(java.util.Set<AcruedDecn> acruedDecns) {
//		this.acruedDecns = acruedDecns;
//	}
//
//	//bi-directional many-to-one association to AcruedDecnBene
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AcruedDecnBene> getAcruedDecnBenes() {
//		return this.acruedDecnBenes;
//	}
//	public void setAcruedDecnBenes(java.util.Set<AcruedDecnBene> acruedDecnBenes) {
//		this.acruedDecnBenes = acruedDecnBenes;
//	}
//
//	//bi-directional many-to-one association to AltmntDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AltmntDecn> getAltmntDecns() {
//		return this.altmntDecns;
//	}
//	public void setAltmntDecns(java.util.Set<AltmntDecn> altmntDecns) {
//		this.altmntDecns = altmntDecns;
//	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

//	//bi-directional many-to-one association to AwardBene
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardBene> getAwardBenes() {
//		return this.awardBenes;
//	}
//	public void setAwardBenes(java.util.Set<AwardBene> awardBenes) {
//		this.awardBenes = awardBenes;
//	}
//
//	//bi-directional many-to-one association to AwardBeneHist
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardBeneHist> getAwardBeneHists() {
//		return this.awardBeneHists;
//	}
//	public void setAwardBeneHists(java.util.Set<AwardBeneHist> awardBeneHists) {
//		this.awardBeneHists = awardBeneHists;
//	}
//
//	//bi-directional many-to-one association to AwardDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardDecn> getAwardDecns() {
//		return this.awardDecns;
//	}
//	public void setAwardDecns(java.util.Set<AwardDecn> awardDecns) {
//		this.awardDecns = awardDecns;
//	}
//
//	//bi-directional many-to-one association to AwardDiary
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardDiary> getAwardDiaries() {
//		return this.awardDiaries;
//	}
//	public void setAwardDiaries(java.util.Set<AwardDiary> awardDiaries) {
//		this.awardDiaries = awardDiaries;
//	}
//
//	//bi-directional many-to-one association to AwardEvent
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardEvent> getAwardEvents() {
//		return this.awardEvents;
//	}
//	public void setAwardEvents(java.util.Set<AwardEvent> awardEvents) {
//		this.awardEvents = awardEvents;
//	}
//
//	//bi-directional many-to-one association to AwardMesage
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardMesage> getAwardMesages() {
//		return this.awardMesages;
//	}
//	public void setAwardMesages(java.util.Set<AwardMesage> awardMesages) {
//		this.awardMesages = awardMesages;
//	}
//
//	//bi-directional many-to-one association to AwardOvrideWrksht
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardOvrideWrksht> getAwardOvrideWrkshts() {
//		return this.awardOvrideWrkshts;
//	}
//	public void setAwardOvrideWrkshts(java.util.Set<AwardOvrideWrksht> awardOvrideWrkshts) {
//		this.awardOvrideWrkshts = awardOvrideWrkshts;
//	}
//
//	//bi-directional many-to-one association to AwardPtcpnt
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<AwardPtcpnt> getAwardPtcpnts() {
//		return this.awardPtcpnts;
//	}
//	public void setAwardPtcpnts(java.util.Set<AwardPtcpnt> awardPtcpnts) {
//		this.awardPtcpnts = awardPtcpnts;
//	}
//
//	//bi-directional many-to-one association to BasicElgbtyDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<BasicElgbtyDecn> getBasicElgbtyDecns() {
//		return this.basicElgbtyDecns;
//	}
//	public void setBasicElgbtyDecns(java.util.Set<BasicElgbtyDecn> basicElgbtyDecns) {
//		this.basicElgbtyDecns = basicElgbtyDecns;
//	}
//
//	//bi-directional many-to-one association to BirthDefectDetail
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<BirthDefectDetail> getBirthDefectDetails() {
//		return this.birthDefectDetails;
//	}
//	public void setBirthDefectDetails(java.util.Set<BirthDefectDetail> birthDefectDetails) {
//		this.birthDefectDetails = birthDefectDetails;
//	}

	//bi-directional many-to-one association to BurialBeneDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<BurialBeneDecn> getBurialBeneDecns() {
		return this.burialBeneDecns;
	}
	public void setBurialBeneDecns(java.util.Set<BurialBeneDecn> burialBeneDecns) {
		this.burialBeneDecns = burialBeneDecns;
	}

	//bi-directional many-to-one association to BurialBnftDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<BurialBnftDecn> getBurialBnftDecns() {
		return this.burialBnftDecns;
	}
	public void setBurialBnftDecns(java.util.Set<BurialBnftDecn> burialBnftDecns) {
		this.burialBnftDecns = burialBnftDecns;
	}

	//bi-directional many-to-one association to BurialSumryDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<BurialSumryDecn> getBurialSumryDecns() {
		return this.burialSumryDecns;
	}
	public void setBurialSumryDecns(java.util.Set<BurialSumryDecn> burialSumryDecns) {
		this.burialSumryDecns = burialSumryDecns;
	}

//	//bi-directional many-to-one association to ChldrnCustdyDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<ChldrnCustdyDecn> getChldrnCustdyDecns() {
//		return this.chldrnCustdyDecns;
//	}
//	public void setChldrnCustdyDecns(java.util.Set<ChldrnCustdyDecn> chldrnCustdyDecns) {
//		this.chldrnCustdyDecns = chldrnCustdyDecns;
//	}
//
//	//bi-directional many-to-one association to ClthngAlwncDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<ClthngAlwncDecn> getClthngAlwncDecns() {
//		return this.clthngAlwncDecns;
//	}
//	public void setClthngAlwncDecns(java.util.Set<ClthngAlwncDecn> clthngAlwncDecns) {
//		this.clthngAlwncDecns = clthngAlwncDecns;
//	}
//
//	//bi-directional many-to-one association to DepncyDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<DepncyDecn> getDepncyDecns() {
//		return this.depncyDecns;
//	}
//	public void setDepncyDecns(java.util.Set<DepncyDecn> depncyDecns) {
//		this.depncyDecns = depncyDecns;
//	}
//
//	//bi-directional many-to-one association to DicSpouseDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<DicSpouseDecn> getDicSpouseDecns() {
//		return this.dicSpouseDecns;
//	}
//	public void setDicSpouseDecns(java.util.Set<DicSpouseDecn> dicSpouseDecns) {
//		this.dicSpouseDecns = dicSpouseDecns;
//	}
//
//	//bi-directional many-to-one association to DrillPayDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<DrillPayDecn> getDrillPayDecns() {
//		return this.drillPayDecns;
//	}
//	public void setDrillPayDecns(java.util.Set<DrillPayDecn> drillPayDecns) {
//		this.drillPayDecns = drillPayDecns;
//	}

	//bi-directional many-to-one association to DsbltyPayDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<DsbltyPayDecn> getDsbltyPayDecns() {
		return this.dsbltyPayDecns;
	}
	public void setDsbltyPayDecns(java.util.Set<DsbltyPayDecn> dsbltyPayDecns) {
		this.dsbltyPayDecns = dsbltyPayDecns;
	}

//	//bi-directional many-to-one association to ElectnDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<ElectnDecn> getElectnDecns() {
//		return this.electnDecns;
//	}
//	public void setElectnDecns(java.util.Set<ElectnDecn> electnDecns) {
//		this.electnDecns = electnDecns;
//	}
//
//	//bi-directional many-to-one association to EvrClaim
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<EvrClaim> getEvrClaims() {
//		return this.evrClaims;
//	}
//	public void setEvrClaims(java.util.Set<EvrClaim> evrClaims) {
//		this.evrClaims = evrClaims;
//	}
//
//	//bi-directional many-to-one association to ExpnsDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<ExpnsDecn> getExpnsDecns() {
//		return this.expnsDecns;
//	}
//	public void setExpnsDecns(java.util.Set<ExpnsDecn> expnsDecns) {
//		this.expnsDecns = expnsDecns;
//	}
//
//	//bi-directional many-to-one association to FinclDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<FinclDecn> getFinclDecns() {
//		return this.finclDecns;
//	}
//	public void setFinclDecns(java.util.Set<FinclDecn> finclDecns) {
//		this.finclDecns = finclDecns;
//	}
//
//	//bi-directional many-to-one association to FutureRecrngPybl
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<FutureRecrngPybl> getFutureRecrngPybls() {
//		return this.futureRecrngPybls;
//	}
//	public void setFutureRecrngPybls(java.util.Set<FutureRecrngPybl> futureRecrngPybls) {
//		this.futureRecrngPybls = futureRecrngPybls;
//	}

	//bi-directional many-to-one association to IncomeDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<IncomeDecn> getIncomeDecns() {
		return this.incomeDecns;
	}
	public void setIncomeDecns(java.util.Set<IncomeDecn> incomeDecns) {
		this.incomeDecns = incomeDecns;
	}

	//bi-directional many-to-one association to InstznAdjsmtDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<InstznAdjsmtDecn> getInstznAdjsmtDecns() {
		return this.instznAdjsmtDecns;
	}
	public void setInstznAdjsmtDecns(java.util.Set<InstznAdjsmtDecn> instznAdjsmtDecns) {
		this.instznAdjsmtDecns = instznAdjsmtDecns;
	}

//	//bi-directional many-to-one association to MedalHonorDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<MedalHonorDecn> getMedalHonorDecns() {
//		return this.medalHonorDecns;
//	}
//	public void setMedalHonorDecns(java.util.Set<MedalHonorDecn> medalHonorDecns) {
//		this.medalHonorDecns = medalHonorDecns;
//	}
//
//	//bi-directional many-to-one association to MiscDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<MiscDecn> getMiscDecns() {
//		return this.miscDecns;
//	}
//	public void setMiscDecns(java.util.Set<MiscDecn> miscDecns) {
//		this.miscDecns = miscDecns;
//	}
//
//	//bi-directional many-to-one association to MltyElgbtyDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<MltyElgbtyDecn> getMltyElgbtyDecns() {
//		return this.mltyElgbtyDecns;
//	}
//	public void setMltyElgbtyDecns(java.util.Set<MltyElgbtyDecn> mltyElgbtyDecns) {
//		this.mltyElgbtyDecns = mltyElgbtyDecns;
//	}

	//bi-directional many-to-one association to NetWorthDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<NetWorthDecn> getNetWorthDecns() {
		return this.netWorthDecns;
	}
	public void setNetWorthDecns(java.util.Set<NetWorthDecn> netWorthDecns) {
		this.netWorthDecns = netWorthDecns;
	}

//	//bi-directional many-to-one association to OtherWthldgDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<OtherWthldgDecn> getOtherWthldgDecns() {
//		return this.otherWthldgDecns;
//	}
//	public void setOtherWthldgDecns(java.util.Set<OtherWthldgDecn> otherWthldgDecns) {
//		this.otherWthldgDecns = otherWthldgDecns;
//	}

	//bi-directional many-to-one association to ParentMartlStatusDecn
	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
	public java.util.Set<ParentMartlStatusDecn> getParentMartlStatusDecns() {
		return this.parentMartlStatusDecns;
	}
	public void setParentMartlStatusDecns(java.util.Set<ParentMartlStatusDecn> parentMartlStatusDecns) {
		this.parentMartlStatusDecns = parentMartlStatusDecns;
	}

//	//bi-directional many-to-one association to PayRateDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<PayRateDecn> getPayRateDecns() {
//		return this.payRateDecns;
//	}
//	public void setPayRateDecns(java.util.Set<PayRateDecn> payRateDecns) {
//		this.payRateDecns = payRateDecns;
//	}
//
//	//bi-directional many-to-one association to PriorsAnchor
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<PriorsAnchor> getPriorsAnchors() {
//		return this.priorsAnchors;
//	}
//	public void setPriorsAnchors(java.util.Set<PriorsAnchor> priorsAnchors) {
//		this.priorsAnchors = priorsAnchors;
//	}
//
//	//bi-directional many-to-one association to RtrmntPayDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<RtrmntPayDecn> getRtrmntPayDecns() {
//		return this.rtrmntPayDecns;
//	}
//	public void setRtrmntPayDecns(java.util.Set<RtrmntPayDecn> rtrmntPayDecns) {
//		this.rtrmntPayDecns = rtrmntPayDecns;
//	}
//
//	//bi-directional many-to-one association to SeprtnPayDecn
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<SeprtnPayDecn> getSeprtnPayDecns() {
//		return this.seprtnPayDecns;
//	}
//	public void setSeprtnPayDecns(java.util.Set<SeprtnPayDecn> seprtnPayDecns) {
//		this.seprtnPayDecns = seprtnPayDecns;
//	}
//
//	//bi-directional many-to-one association to SpinaBifidaDtail
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<SpinaBifidaDtail> getSpinaBifidaDtails() {
//		return this.spinaBifidaDtails;
//	}
//	public void setSpinaBifidaDtails(java.util.Set<SpinaBifidaDtail> spinaBifidaDtails) {
//		this.spinaBifidaDtails = spinaBifidaDtails;
//	}
//
//	//bi-directional many-to-one association to TempAltmntSumry
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<TempAltmntSumry> getTempAltmntSumries() {
//		return this.tempAltmntSumries;
//	}
//	public void setTempAltmntSumries(java.util.Set<TempAltmntSumry> tempAltmntSumries) {
//		this.tempAltmntSumries = tempAltmntSumries;
//	}
//
//	//bi-directional many-to-one association to TempAward
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<TempAward> getTempAwards() {
//		return this.tempAwards;
//	}
//	public void setTempAwards(java.util.Set<TempAward> tempAwards) {
//		this.tempAwards = tempAwards;
//	}
//
//	//bi-directional many-to-one association to TempDepSumry
//	@OneToMany(mappedBy="award", fetch=FetchType.LAZY)
//	public java.util.Set<TempDepSumry> getTempDepSumries() {
//		return this.tempDepSumries;
//	}
//	public void setTempDepSumries(java.util.Set<TempDepSumry> tempDepSumries) {
//		this.tempDepSumries = tempDepSumries;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Award)) {
			return false;
		}
		Award castOther = (Award)other;
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