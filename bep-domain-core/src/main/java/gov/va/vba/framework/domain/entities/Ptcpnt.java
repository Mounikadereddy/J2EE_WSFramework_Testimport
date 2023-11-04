package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the PTCPNT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT")
public class Ptcpnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
//	private Double bondAmt;
//	private java.sql.Timestamp bondExprtnDt;
//	private String bondInd;
	private String fraudInd;
	private String legacyPoaCd;
	private String miscVendorInd;
	private String ptcpntShortNm;
	private String ptcpntTypeNm;
//	private String recordCnfusnTxt;
	private String taxIdfctnNbr;
	private String tinWaiverReasonTypeCd;
//	private java.util.Set<AcntblBal> acntblBals1;
//	private java.util.Set<AcntblBal> acntblBals2;
//	private java.util.Set<AcntblBal> acntblBals3;
//	private java.util.Set<AcntgTran> acntgTrans1;
//	private java.util.Set<AcntgTran> acntgTrans2;
//	private java.util.Set<AcntgTran> acntgTrans3;
//	private java.util.Set<AcruedDecnBene> acruedDecnBenes;
//	private java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts1;
//	private java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts2;
//	private java.util.Set<AltmntDecn> altmntDecns;
//	private java.util.Set<Asset> assets;
//	private AstncSpclst astncSpclst;
//	private java.util.Set<AuthznEvent> authznEvents;
	private java.util.Set<Award> awards1;
	private java.util.Set<Award> awards2;
//	private java.util.Set<AwardBene> awardBenes;
//	private java.util.Set<AwardCmpsit> awardCmpsits1;
//	private java.util.Set<AwardCmpsit> awardCmpsits2;
//	private java.util.Set<AwardCmpsit> awardCmpsits3;
//	private java.util.Set<AwardMesage> awardMesages1;
//	private java.util.Set<AwardMesage> awardMesages2;
//	private java.util.Set<AwardPtcpnt> awardPtcpnts;
	private java.util.Set<BnftClaim> bnftClaims1;
	private java.util.Set<BnftClaim> bnftClaims2;
	private java.util.Set<BnftClaim> bnftClaims3;
	private java.util.Set<BnftClaim> bnftClaims4;
//	private java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses;
//	private java.util.Set<BnftClaimNote> bnftClaimNotes;
//	private java.util.Set<BnftClaimPtcpnt> bnftClaimPtcpnts;
	private java.util.Set<BurialBeneDecn> burialBeneDecns;
//	private java.util.Set<Case> cases;
//	private java.util.Set<CaseMiscExpn> caseMiscExpns1;
//	private java.util.Set<CaseMiscExpn> caseMiscExpns2;
//	private java.util.Set<CaseTravel> caseTravels1;
//	private java.util.Set<CaseTravel> caseTravels2;
//	private java.util.Set<ChldrnCustdyDecn> chldrnCustdyDecns;
//	private java.util.Set<ClaimAsgnmt> claimAsgnmts;
	private ClaimDvlpmtCntct claimDvlpmtCntct;
//	private java.util.Set<ClaimSuspnsLcStatus> claimSuspnsLcStatuses;
//	private java.util.Set<CntrctAward> cntrctAwards1;
//	private java.util.Set<CntrctAward> cntrctAwards2;
//	private java.util.Set<CntrlAwardCmpsit> cntrlAwardCmpsits1;
//	private java.util.Set<CntrlAwardCmpsit> cntrlAwardCmpsits2;
//	private java.util.Set<CntrlBirlsIntrfcTran> cntrlBirlsIntrfcTrans1;
//	private java.util.Set<CntrlBirlsIntrfcTran> cntrlBirlsIntrfcTrans2;
//	private java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans1;
//	private java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans2;
//	private java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans3;
//	private java.util.Set<CntrlDiaryTran> cntrlDiaryTrans;
//	private java.util.Set<CntrlLetterTran> cntrlLetterTrans1;
//	private java.util.Set<CntrlLetterTran> cntrlLetterTrans2;
//	private java.util.Set<CntrlLetterTran> cntrlLetterTrans3;
//	private java.util.Set<CntrlLetterTran> cntrlLetterTrans4;
//	private java.util.Set<CntrlSuspndResume> cntrlSuspndResumes1;
//	private java.util.Set<CntrlSuspndResume> cntrlSuspndResumes2;
//	private java.util.Set<CntrlSuspndResume> cntrlSuspndResumes3;
//	private java.util.Set<Credtr> credtrs;
//	private java.util.Set<EduAward> eduAwards1;
//	private java.util.Set<EduAward> eduAwards2;
//	private java.util.Set<EduAward> eduAwards3;
//	private java.util.Set<EduBnft> eduBnfts;
//	private java.util.Set<EduPrvdrAward> eduPrvdrAwards1;
//	private java.util.Set<EduPrvdrAward> eduPrvdrAwards2;
	private java.util.Set<EmpVaOrgUnit> empVaOrgUnits;
//	private java.util.Set<Expn> expns;
//	private java.util.Set<FinclBusnsTran> finclBusnsTrans1;
//	private java.util.Set<FinclBusnsTran> finclBusnsTrans2;
//	private java.util.Set<FinclBusnsTran> finclBusnsTrans3;
//	private java.util.Set<FmsTran> fmsTrans1;
//	private java.util.Set<FmsTran> fmsTrans2;
//	private java.util.Set<Folder> folders;
//	private java.util.Set<HistSbopIntrfcTran> histSbopIntrfcTrans;
//	private java.util.Set<HistSsaDmIntrfcTran> histSsaDmIntrfcTrans;
//	private java.util.Set<HoldPymtIntrfc> holdPymtIntrfcs;
//	private java.util.Set<IncmngDcmnt> incmngDcmnts1;
//	private java.util.Set<IncmngDcmnt> incmngDcmnts2;
	private java.util.Set<Income> incomes;
//	private java.util.Set<IncomeDductn> incomeDductns;
	private java.util.Set<IncomeDecn> incomeDecns;
//	private java.util.Set<InsIntrfc> insIntrfcs;
//	private java.util.Set<IntrfcIncome> intrfcIncomes;
//	private java.util.Set<Liabty> liabties;
//	private java.util.Set<MiscDecnBene> miscDecnBenes;
//	private java.util.Set<Notfcn> notfcns1;
//	private java.util.Set<Notfcn> notfcns2;
//	private java.util.Set<NotfcnPrfil> notfcnPrfils;
//	private java.util.Set<OffsetPlan> offsetPlans1;
//	private java.util.Set<OffsetPlan> offsetPlans2;
//	private java.util.Set<OffsetPlan> offsetPlans3;
	private java.util.Set<Org> orgs;
	private java.util.Set<OutgngDcmnt> outgngDcmnts1;
	private java.util.Set<OutgngDcmnt> outgngDcmnts2;
	private java.util.Set<Person> persons;
//	private java.util.Set<PrintJob> printJobs;
//	private java.util.Set<PriorsRecip> priorsRecips;
//	private java.util.Set<PrprtyInsPolicy> prprtyInsPolicies;
//	private java.util.Set<PrprtyPtcpnt> prprtyPtcpnts;
	private java.util.Set<PtcpntAddr> ptcpntAddrs;
	private java.util.Set<PtcpntAlia> ptcpntAlias;
	private java.util.Set<PtcpntDiary> ptcpntDiaries;
	private java.util.Set<PtcpntDisptn> ptcpntDisptns;
	private java.util.Set<PtcpntDpositAcnt> ptcpntDpositAcnts;
	private java.util.Set<PtcpntIncmngDcmnt> ptcpntIncmngDcmnts;
	private java.util.Set<PtcpntLctn> ptcpntLctns;
	private java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts1;
	private java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts2;
	private java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts3;
	private java.util.Set<PtcpntMtgeLoan> ptcpntMtgeLoans;
	private java.util.Set<PtcpntNote> ptcpntNotes1;
	private java.util.Set<PtcpntNote> ptcpntNotes2;
	private java.util.Set<PtcpntOutgngDcmnt> ptcpntOutgngDcmnts;
	private java.util.Set<PtcpntPhone> ptcpntPhones;
	private java.util.Set<PtcpntRlnshp> ptcpntRlnshps1;
	private java.util.Set<PtcpntRlnshp> ptcpntRlnshps2;
	private java.util.Set<PtcpntRlnshpAprvdState> ptcpntRlnshpAprvdStates1;
	private java.util.Set<PtcpntRlnshpAprvdState> ptcpntRlnshpAprvdStates2;
	private java.util.Set<PtcpntRlnshpHist> ptcpntRlnshpHists1;
	private java.util.Set<PtcpntRlnshpHist> ptcpntRlnshpHists2;
//	private java.util.Set<Pybl> pybls1;
//	private java.util.Set<Pybl> pybls2;
//	private java.util.Set<Pybl> pybls3;
//	private java.util.Set<Pymt> pymts1;
//	private java.util.Set<Pymt> pymts2;
//	private java.util.Set<Pymt> pymts3;
	private java.util.Set<RbaPrfil> rbaPrfils;
//	private java.util.Set<Rcvbl> rcvbls1;
//	private java.util.Set<Rcvbl> rcvbls2;
//	private java.util.Set<RecrngPybl> recrngPybls1;
//	private java.util.Set<RecrngPybl> recrngPybls2;
//	private java.util.Set<RecrngPybl> recrngPybls3;
//	private java.util.Set<StdDvlpmtAction> stdDvlpmtActions;
//	private java.util.Set<StnOfJrsdtn> stnOfJrsdtns1;
//	private java.util.Set<StnOfJrsdtn> stnOfJrsdtns2;
//	private java.util.Set<TempPerson> tempPersons;
//	private TempVendor tempVendor;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Ptcpnt() {
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
    @Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

//	@Basic()
//	@Column(name="BOND_AMT", precision=15, scale=2)
//	public Double getBondAmt() {
//		return this.bondAmt;
//	}
//	public void setBondAmt(Double bondAmt) {
//		this.bondAmt = bondAmt;
//	}
//
//	@Basic()
//	@Column(name="BOND_EXPRTN_DT", length=7)
//	public java.sql.Timestamp getBondExprtnDt() {
//		return this.bondExprtnDt;
//	}
//	public void setBondExprtnDt(java.sql.Timestamp bondExprtnDt) {
//		this.bondExprtnDt = bondExprtnDt;
//	}
//
//	@Basic()
//	@Column(name="BOND_IND", length=1)
//	public String getBondInd() {
//		return this.bondInd;
//	}
//	public void setBondInd(String bondInd) {
//		this.bondInd = bondInd;
//	}

	@Basic()
	@Column(name="FRAUD_IND", length=1)
	public String getFraudInd() {
		return this.fraudInd;
	}
	public void setFraudInd(String fraudInd) {
		this.fraudInd = fraudInd;
	}

	@Basic()
	@Column(name="LEGACY_POA_CD", length=3)
	public String getLegacyPoaCd() {
		return this.legacyPoaCd;
	}
	public void setLegacyPoaCd(String legacyPoaCd) {
		this.legacyPoaCd = legacyPoaCd;
	}

	@Basic()
	@Column(name="MISC_VENDOR_IND", length=1)
	public String getMiscVendorInd() {
		return this.miscVendorInd;
	}
	public void setMiscVendorInd(String miscVendorInd) {
		this.miscVendorInd = miscVendorInd;
	}

	@Basic()
	@Column(name="PTCPNT_SHORT_NM", length=20)
	public String getPtcpntShortNm() {
		return this.ptcpntShortNm;
	}
	public void setPtcpntShortNm(String ptcpntShortNm) {
		this.ptcpntShortNm = ptcpntShortNm;
	}

	@Basic()
	@Column(name="PTCPNT_TYPE_NM", nullable=false, length=50)
	public String getPtcpntTypeNm() {
		return this.ptcpntTypeNm;
	}
	public void setPtcpntTypeNm(String ptcpntTypeNm) {
		this.ptcpntTypeNm = ptcpntTypeNm;
	}

//	@Basic()
//	@Column(name="RECORD_CNFUSN_TXT", length=254)
//	public String getRecordCnfusnTxt() {
//		return this.recordCnfusnTxt;
//	}
//	public void setRecordCnfusnTxt(String recordCnfusnTxt) {
//		this.recordCnfusnTxt = recordCnfusnTxt;
//	}

	@Basic()
	@Column(name="TAX_IDFCTN_NBR", length=9)
	public String getTaxIdfctnNbr() {
		return this.taxIdfctnNbr;
	}
	public void setTaxIdfctnNbr(String taxIdfctnNbr) {
		this.taxIdfctnNbr = taxIdfctnNbr;
	}

	@Basic()
	@Column(name="TIN_WAIVER_REASON_TYPE_CD", length=12)
	public String getTinWaiverReasonTypeCd() {
		return this.tinWaiverReasonTypeCd;
	}
	public void setTinWaiverReasonTypeCd(String tinWaiverReasonTypeCd) {
		this.tinWaiverReasonTypeCd = tinWaiverReasonTypeCd;
	}
//
//	//bi-directional many-to-one association to AcntblBal
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<AcntblBal> getAcntblBals1() {
//		return this.acntblBals1;
//	}
//	public void setAcntblBals1(java.util.Set<AcntblBal> acntblBals1) {
//		this.acntblBals1 = acntblBals1;
//	}
//
//	//bi-directional many-to-one association to AcntblBal
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<AcntblBal> getAcntblBals2() {
//		return this.acntblBals2;
//	}
//	public void setAcntblBals2(java.util.Set<AcntblBal> acntblBals2) {
//		this.acntblBals2 = acntblBals2;
//	}
//
//	//bi-directional many-to-one association to AcntblBal
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<AcntblBal> getAcntblBals3() {
//		return this.acntblBals3;
//	}
//	public void setAcntblBals3(java.util.Set<AcntblBal> acntblBals3) {
//		this.acntblBals3 = acntblBals3;
//	}
//
//	//bi-directional many-to-one association to AcntgTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<AcntgTran> getAcntgTrans1() {
//		return this.acntgTrans1;
//	}
//	public void setAcntgTrans1(java.util.Set<AcntgTran> acntgTrans1) {
//		this.acntgTrans1 = acntgTrans1;
//	}
//
//	//bi-directional many-to-one association to AcntgTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<AcntgTran> getAcntgTrans2() {
//		return this.acntgTrans2;
//	}
//	public void setAcntgTrans2(java.util.Set<AcntgTran> acntgTrans2) {
//		this.acntgTrans2 = acntgTrans2;
//	}
//
//	//bi-directional many-to-one association to AcntgTran
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<AcntgTran> getAcntgTrans3() {
//		return this.acntgTrans3;
//	}
//	public void setAcntgTrans3(java.util.Set<AcntgTran> acntgTrans3) {
//		this.acntgTrans3 = acntgTrans3;
//	}
//
//	//bi-directional many-to-one association to AcruedDecnBene
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<AcruedDecnBene> getAcruedDecnBenes() {
//		return this.acruedDecnBenes;
//	}
//	public void setAcruedDecnBenes(java.util.Set<AcruedDecnBene> acruedDecnBenes) {
//		this.acruedDecnBenes = acruedDecnBenes;
//	}
//
//	//bi-directional many-to-one association to AgencyLedgerDcmnt
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<AgencyLedgerDcmnt> getAgencyLedgerDcmnts1() {
//		return this.agencyLedgerDcmnts1;
//	}
//	public void setAgencyLedgerDcmnts1(java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts1) {
//		this.agencyLedgerDcmnts1 = agencyLedgerDcmnts1;
//	}
//
//	//bi-directional many-to-one association to AgencyLedgerDcmnt
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<AgencyLedgerDcmnt> getAgencyLedgerDcmnts2() {
//		return this.agencyLedgerDcmnts2;
//	}
//	public void setAgencyLedgerDcmnts2(java.util.Set<AgencyLedgerDcmnt> agencyLedgerDcmnts2) {
//		this.agencyLedgerDcmnts2 = agencyLedgerDcmnts2;
//	}
//
//	//bi-directional many-to-one association to AltmntDecn
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<AltmntDecn> getAltmntDecns() {
//		return this.altmntDecns;
//	}
//	public void setAltmntDecns(java.util.Set<AltmntDecn> altmntDecns) {
//		this.altmntDecns = altmntDecns;
//	}
//
//	//bi-directional many-to-one association to Asset
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Asset> getAssets() {
//		return this.assets;
//	}
//	public void setAssets(java.util.Set<Asset> assets) {
//		this.assets = assets;
//	}
//
//	//bi-directional one-to-one association to AstncSpclst
//	@OneToOne(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public AstncSpclst getAstncSpclst() {
//		return this.astncSpclst;
//	}
//	public void setAstncSpclst(AstncSpclst astncSpclst) {
//		this.astncSpclst = astncSpclst;
//	}
//
//	//bi-directional many-to-one association to AuthznEvent
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<AuthznEvent> getAuthznEvents() {
//		return this.authznEvents;
//	}
//	public void setAuthznEvents(java.util.Set<AuthznEvent> authznEvents) {
//		this.authznEvents = authznEvents;
//	}

	//bi-directional many-to-one association to Award
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<Award> getAwards1() {
		return this.awards1;
	}
	public void setAwards1(java.util.Set<Award> awards1) {
		this.awards1 = awards1;
	}

	//bi-directional many-to-one association to Award
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<Award> getAwards2() {
		return this.awards2;
	}
	public void setAwards2(java.util.Set<Award> awards2) {
		this.awards2 = awards2;
	}
//
//	//bi-directional many-to-one association to AwardBene
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<AwardBene> getAwardBenes() {
//		return this.awardBenes;
//	}
//	public void setAwardBenes(java.util.Set<AwardBene> awardBenes) {
//		this.awardBenes = awardBenes;
//	}
//
//	//bi-directional many-to-one association to AwardCmpsit
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<AwardCmpsit> getAwardCmpsits1() {
//		return this.awardCmpsits1;
//	}
//	public void setAwardCmpsits1(java.util.Set<AwardCmpsit> awardCmpsits1) {
//		this.awardCmpsits1 = awardCmpsits1;
//	}
//
//	//bi-directional many-to-one association to AwardCmpsit
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<AwardCmpsit> getAwardCmpsits2() {
//		return this.awardCmpsits2;
//	}
//	public void setAwardCmpsits2(java.util.Set<AwardCmpsit> awardCmpsits2) {
//		this.awardCmpsits2 = awardCmpsits2;
//	}
//
//	//bi-directional many-to-one association to AwardCmpsit
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<AwardCmpsit> getAwardCmpsits3() {
//		return this.awardCmpsits3;
//	}
//	public void setAwardCmpsits3(java.util.Set<AwardCmpsit> awardCmpsits3) {
//		this.awardCmpsits3 = awardCmpsits3;
//	}
//
//	//bi-directional many-to-one association to AwardMesage
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<AwardMesage> getAwardMesages1() {
//		return this.awardMesages1;
//	}
//	public void setAwardMesages1(java.util.Set<AwardMesage> awardMesages1) {
//		this.awardMesages1 = awardMesages1;
//	}
//
//	//bi-directional many-to-one association to AwardMesage
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<AwardMesage> getAwardMesages2() {
//		return this.awardMesages2;
//	}
//	public void setAwardMesages2(java.util.Set<AwardMesage> awardMesages2) {
//		this.awardMesages2 = awardMesages2;
//	}
//
//	//bi-directional many-to-one association to AwardPtcpnt
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<AwardPtcpnt> getAwardPtcpnts() {
//		return this.awardPtcpnts;
//	}
//	public void setAwardPtcpnts(java.util.Set<AwardPtcpnt> awardPtcpnts) {
//		this.awardPtcpnts = awardPtcpnts;
//	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims1() {
		return this.bnftClaims1;
	}
	public void setBnftClaims1(java.util.Set<BnftClaim> bnftClaims1) {
		this.bnftClaims1 = bnftClaims1;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims2() {
		return this.bnftClaims2;
	}
	public void setBnftClaims2(java.util.Set<BnftClaim> bnftClaims2) {
		this.bnftClaims2 = bnftClaims2;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims3() {
		return this.bnftClaims3;
	}
	public void setBnftClaims3(java.util.Set<BnftClaim> bnftClaims3) {
		this.bnftClaims3 = bnftClaims3;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpnt4", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims4() {
		return this.bnftClaims4;
	}
	public void setBnftClaims4(java.util.Set<BnftClaim> bnftClaims4) {
		this.bnftClaims4 = bnftClaims4;
	}

//	//bi-directional many-to-one association to BnftClaimLcStatus
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<BnftClaimLcStatus> getBnftClaimLcStatuses() {
//		return this.bnftClaimLcStatuses;
//	}
//	public void setBnftClaimLcStatuses(java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses) {
//		this.bnftClaimLcStatuses = bnftClaimLcStatuses;
//	}
//
//	//bi-directional many-to-one association to BnftClaimNote
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<BnftClaimNote> getBnftClaimNotes() {
//		return this.bnftClaimNotes;
//	}
//	public void setBnftClaimNotes(java.util.Set<BnftClaimNote> bnftClaimNotes) {
//		this.bnftClaimNotes = bnftClaimNotes;
//	}
//
//	//bi-directional many-to-one association to BnftClaimPtcpnt
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<BnftClaimPtcpnt> getBnftClaimPtcpnts() {
//		return this.bnftClaimPtcpnts;
//	}
//	public void setBnftClaimPtcpnts(java.util.Set<BnftClaimPtcpnt> bnftClaimPtcpnts) {
//		this.bnftClaimPtcpnts = bnftClaimPtcpnts;
//	}

	//bi-directional many-to-one association to BurialBeneDecn
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<BurialBeneDecn> getBurialBeneDecns() {
		return this.burialBeneDecns;
	}
	public void setBurialBeneDecns(java.util.Set<BurialBeneDecn> burialBeneDecns) {
		this.burialBeneDecns = burialBeneDecns;
	}

//	//bi-directional many-to-one association to Case
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Case> getCases() {
//		return this.cases;
//	}
//	public void setCases(java.util.Set<Case> cases) {
//		this.cases = cases;
//	}
//
//	//bi-directional many-to-one association to CaseMiscExpn
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CaseMiscExpn> getCaseMiscExpns1() {
//		return this.caseMiscExpns1;
//	}
//	public void setCaseMiscExpns1(java.util.Set<CaseMiscExpn> caseMiscExpns1) {
//		this.caseMiscExpns1 = caseMiscExpns1;
//	}
//
//	//bi-directional many-to-one association to CaseMiscExpn
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CaseMiscExpn> getCaseMiscExpns2() {
//		return this.caseMiscExpns2;
//	}
//	public void setCaseMiscExpns2(java.util.Set<CaseMiscExpn> caseMiscExpns2) {
//		this.caseMiscExpns2 = caseMiscExpns2;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels1() {
//		return this.caseTravels1;
//	}
//	public void setCaseTravels1(java.util.Set<CaseTravel> caseTravels1) {
//		this.caseTravels1 = caseTravels1;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels2() {
//		return this.caseTravels2;
//	}
//	public void setCaseTravels2(java.util.Set<CaseTravel> caseTravels2) {
//		this.caseTravels2 = caseTravels2;
//	}
//
//	//bi-directional many-to-one association to ChldrnCustdyDecn
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<ChldrnCustdyDecn> getChldrnCustdyDecns() {
//		return this.chldrnCustdyDecns;
//	}
//	public void setChldrnCustdyDecns(java.util.Set<ChldrnCustdyDecn> chldrnCustdyDecns) {
//		this.chldrnCustdyDecns = chldrnCustdyDecns;
//	}
//
//	//bi-directional many-to-one association to ClaimAsgnmt
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<ClaimAsgnmt> getClaimAsgnmts() {
//		return this.claimAsgnmts;
//	}
//	public void setClaimAsgnmts(java.util.Set<ClaimAsgnmt> claimAsgnmts) {
//		this.claimAsgnmts = claimAsgnmts;
//	}

	//bi-directional one-to-one association to ClaimDvlpmtCntct
	@OneToOne(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public ClaimDvlpmtCntct getClaimDvlpmtCntct() {
		return this.claimDvlpmtCntct;
	}
	public void setClaimDvlpmtCntct(ClaimDvlpmtCntct claimDvlpmtCntct) {
		this.claimDvlpmtCntct = claimDvlpmtCntct;
	}

//	//bi-directional many-to-one association to ClaimSuspnsLcStatus
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<ClaimSuspnsLcStatus> getClaimSuspnsLcStatuses() {
//		return this.claimSuspnsLcStatuses;
//	}
//	public void setClaimSuspnsLcStatuses(java.util.Set<ClaimSuspnsLcStatus> claimSuspnsLcStatuses) {
//		this.claimSuspnsLcStatuses = claimSuspnsLcStatuses;
//	}
//
//	//bi-directional many-to-one association to CntrctAward
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrctAward> getCntrctAwards1() {
//		return this.cntrctAwards1;
//	}
//	public void setCntrctAwards1(java.util.Set<CntrctAward> cntrctAwards1) {
//		this.cntrctAwards1 = cntrctAwards1;
//	}
//
//	//bi-directional many-to-one association to CntrctAward
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrctAward> getCntrctAwards2() {
//		return this.cntrctAwards2;
//	}
//	public void setCntrctAwards2(java.util.Set<CntrctAward> cntrctAwards2) {
//		this.cntrctAwards2 = cntrctAwards2;
//	}
//
//	//bi-directional many-to-one association to CntrlAwardCmpsit
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlAwardCmpsit> getCntrlAwardCmpsits1() {
//		return this.cntrlAwardCmpsits1;
//	}
//	public void setCntrlAwardCmpsits1(java.util.Set<CntrlAwardCmpsit> cntrlAwardCmpsits1) {
//		this.cntrlAwardCmpsits1 = cntrlAwardCmpsits1;
//	}
//
//	//bi-directional many-to-one association to CntrlAwardCmpsit
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlAwardCmpsit> getCntrlAwardCmpsits2() {
//		return this.cntrlAwardCmpsits2;
//	}
//	public void setCntrlAwardCmpsits2(java.util.Set<CntrlAwardCmpsit> cntrlAwardCmpsits2) {
//		this.cntrlAwardCmpsits2 = cntrlAwardCmpsits2;
//	}
//
//	//bi-directional many-to-one association to CntrlBirlsIntrfcTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlBirlsIntrfcTran> getCntrlBirlsIntrfcTrans1() {
//		return this.cntrlBirlsIntrfcTrans1;
//	}
//	public void setCntrlBirlsIntrfcTrans1(java.util.Set<CntrlBirlsIntrfcTran> cntrlBirlsIntrfcTrans1) {
//		this.cntrlBirlsIntrfcTrans1 = cntrlBirlsIntrfcTrans1;
//	}
//
//	//bi-directional many-to-one association to CntrlBirlsIntrfcTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlBirlsIntrfcTran> getCntrlBirlsIntrfcTrans2() {
//		return this.cntrlBirlsIntrfcTrans2;
//	}
//	public void setCntrlBirlsIntrfcTrans2(java.util.Set<CntrlBirlsIntrfcTran> cntrlBirlsIntrfcTrans2) {
//		this.cntrlBirlsIntrfcTrans2 = cntrlBirlsIntrfcTrans2;
//	}
//
//	//bi-directional many-to-one association to CntrlBurialLetterTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlBurialLetterTran> getCntrlBurialLetterTrans1() {
//		return this.cntrlBurialLetterTrans1;
//	}
//	public void setCntrlBurialLetterTrans1(java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans1) {
//		this.cntrlBurialLetterTrans1 = cntrlBurialLetterTrans1;
//	}
//
//	//bi-directional many-to-one association to CntrlBurialLetterTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlBurialLetterTran> getCntrlBurialLetterTrans2() {
//		return this.cntrlBurialLetterTrans2;
//	}
//	public void setCntrlBurialLetterTrans2(java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans2) {
//		this.cntrlBurialLetterTrans2 = cntrlBurialLetterTrans2;
//	}
//
//	//bi-directional many-to-one association to CntrlBurialLetterTran
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlBurialLetterTran> getCntrlBurialLetterTrans3() {
//		return this.cntrlBurialLetterTrans3;
//	}
//	public void setCntrlBurialLetterTrans3(java.util.Set<CntrlBurialLetterTran> cntrlBurialLetterTrans3) {
//		this.cntrlBurialLetterTrans3 = cntrlBurialLetterTrans3;
//	}
//
//	//bi-directional many-to-one association to CntrlDiaryTran
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlDiaryTran> getCntrlDiaryTrans() {
//		return this.cntrlDiaryTrans;
//	}
//	public void setCntrlDiaryTrans(java.util.Set<CntrlDiaryTran> cntrlDiaryTrans) {
//		this.cntrlDiaryTrans = cntrlDiaryTrans;
//	}
//
//	//bi-directional many-to-one association to CntrlLetterTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlLetterTran> getCntrlLetterTrans1() {
//		return this.cntrlLetterTrans1;
//	}
//	public void setCntrlLetterTrans1(java.util.Set<CntrlLetterTran> cntrlLetterTrans1) {
//		this.cntrlLetterTrans1 = cntrlLetterTrans1;
//	}
//
//	//bi-directional many-to-one association to CntrlLetterTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlLetterTran> getCntrlLetterTrans2() {
//		return this.cntrlLetterTrans2;
//	}
//	public void setCntrlLetterTrans2(java.util.Set<CntrlLetterTran> cntrlLetterTrans2) {
//		this.cntrlLetterTrans2 = cntrlLetterTrans2;
//	}
//
//	//bi-directional many-to-one association to CntrlLetterTran
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlLetterTran> getCntrlLetterTrans3() {
//		return this.cntrlLetterTrans3;
//	}
//	public void setCntrlLetterTrans3(java.util.Set<CntrlLetterTran> cntrlLetterTrans3) {
//		this.cntrlLetterTrans3 = cntrlLetterTrans3;
//	}
//
//	//bi-directional many-to-one association to CntrlLetterTran
//	@OneToMany(mappedBy="ptcpnt4", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlLetterTran> getCntrlLetterTrans4() {
//		return this.cntrlLetterTrans4;
//	}
//	public void setCntrlLetterTrans4(java.util.Set<CntrlLetterTran> cntrlLetterTrans4) {
//		this.cntrlLetterTrans4 = cntrlLetterTrans4;
//	}
//
//	//bi-directional many-to-one association to CntrlSuspndResume
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlSuspndResume> getCntrlSuspndResumes1() {
//		return this.cntrlSuspndResumes1;
//	}
//	public void setCntrlSuspndResumes1(java.util.Set<CntrlSuspndResume> cntrlSuspndResumes1) {
//		this.cntrlSuspndResumes1 = cntrlSuspndResumes1;
//	}
//
//	//bi-directional many-to-one association to CntrlSuspndResume
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlSuspndResume> getCntrlSuspndResumes2() {
//		return this.cntrlSuspndResumes2;
//	}
//	public void setCntrlSuspndResumes2(java.util.Set<CntrlSuspndResume> cntrlSuspndResumes2) {
//		this.cntrlSuspndResumes2 = cntrlSuspndResumes2;
//	}
//
//	//bi-directional many-to-one association to CntrlSuspndResume
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<CntrlSuspndResume> getCntrlSuspndResumes3() {
//		return this.cntrlSuspndResumes3;
//	}
//	public void setCntrlSuspndResumes3(java.util.Set<CntrlSuspndResume> cntrlSuspndResumes3) {
//		this.cntrlSuspndResumes3 = cntrlSuspndResumes3;
//	}
//
//	//bi-directional many-to-one association to Credtr
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Credtr> getCredtrs() {
//		return this.credtrs;
//	}
//	public void setCredtrs(java.util.Set<Credtr> credtrs) {
//		this.credtrs = credtrs;
//	}
//
//	//bi-directional many-to-one association to EduAward
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<EduAward> getEduAwards1() {
//		return this.eduAwards1;
//	}
//	public void setEduAwards1(java.util.Set<EduAward> eduAwards1) {
//		this.eduAwards1 = eduAwards1;
//	}
//
//	//bi-directional many-to-one association to EduAward
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<EduAward> getEduAwards2() {
//		return this.eduAwards2;
//	}
//	public void setEduAwards2(java.util.Set<EduAward> eduAwards2) {
//		this.eduAwards2 = eduAwards2;
//	}
//
//	//bi-directional many-to-one association to EduAward
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<EduAward> getEduAwards3() {
//		return this.eduAwards3;
//	}
//	public void setEduAwards3(java.util.Set<EduAward> eduAwards3) {
//		this.eduAwards3 = eduAwards3;
//	}
//
//	//bi-directional many-to-one association to EduBnft
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<EduBnft> getEduBnfts() {
//		return this.eduBnfts;
//	}
//	public void setEduBnfts(java.util.Set<EduBnft> eduBnfts) {
//		this.eduBnfts = eduBnfts;
//	}
//
//	//bi-directional many-to-one association to EduPrvdrAward
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<EduPrvdrAward> getEduPrvdrAwards1() {
//		return this.eduPrvdrAwards1;
//	}
//	public void setEduPrvdrAwards1(java.util.Set<EduPrvdrAward> eduPrvdrAwards1) {
//		this.eduPrvdrAwards1 = eduPrvdrAwards1;
//	}
//
//	//bi-directional many-to-one association to EduPrvdrAward
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<EduPrvdrAward> getEduPrvdrAwards2() {
//		return this.eduPrvdrAwards2;
//	}
//	public void setEduPrvdrAwards2(java.util.Set<EduPrvdrAward> eduPrvdrAwards2) {
//		this.eduPrvdrAwards2 = eduPrvdrAwards2;
//	}

	//bi-directional many-to-one association to EmpVaOrgUnit
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<EmpVaOrgUnit> getEmpVaOrgUnits() {
		return this.empVaOrgUnits;
	}
	public void setEmpVaOrgUnits(java.util.Set<EmpVaOrgUnit> empVaOrgUnits) {
		this.empVaOrgUnits = empVaOrgUnits;
	}

//	//bi-directional many-to-one association to Expn
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Expn> getExpns() {
//		return this.expns;
//	}
//	public void setExpns(java.util.Set<Expn> expns) {
//		this.expns = expns;
//	}
//
//	//bi-directional many-to-one association to FinclBusnsTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<FinclBusnsTran> getFinclBusnsTrans1() {
//		return this.finclBusnsTrans1;
//	}
//	public void setFinclBusnsTrans1(java.util.Set<FinclBusnsTran> finclBusnsTrans1) {
//		this.finclBusnsTrans1 = finclBusnsTrans1;
//	}
//
//	//bi-directional many-to-one association to FinclBusnsTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<FinclBusnsTran> getFinclBusnsTrans2() {
//		return this.finclBusnsTrans2;
//	}
//	public void setFinclBusnsTrans2(java.util.Set<FinclBusnsTran> finclBusnsTrans2) {
//		this.finclBusnsTrans2 = finclBusnsTrans2;
//	}
//
//	//bi-directional many-to-one association to FinclBusnsTran
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<FinclBusnsTran> getFinclBusnsTrans3() {
//		return this.finclBusnsTrans3;
//	}
//	public void setFinclBusnsTrans3(java.util.Set<FinclBusnsTran> finclBusnsTrans3) {
//		this.finclBusnsTrans3 = finclBusnsTrans3;
//	}
//
//	//bi-directional many-to-one association to FmsTran
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<FmsTran> getFmsTrans1() {
//		return this.fmsTrans1;
//	}
//	public void setFmsTrans1(java.util.Set<FmsTran> fmsTrans1) {
//		this.fmsTrans1 = fmsTrans1;
//	}
//
//	//bi-directional many-to-one association to FmsTran
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<FmsTran> getFmsTrans2() {
//		return this.fmsTrans2;
//	}
//	public void setFmsTrans2(java.util.Set<FmsTran> fmsTrans2) {
//		this.fmsTrans2 = fmsTrans2;
//	}
//
//	//bi-directional many-to-one association to Folder
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Folder> getFolders() {
//		return this.folders;
//	}
//	public void setFolders(java.util.Set<Folder> folders) {
//		this.folders = folders;
//	}
//
//	//bi-directional many-to-one association to HistSbopIntrfcTran
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<HistSbopIntrfcTran> getHistSbopIntrfcTrans() {
//		return this.histSbopIntrfcTrans;
//	}
//	public void setHistSbopIntrfcTrans(java.util.Set<HistSbopIntrfcTran> histSbopIntrfcTrans) {
//		this.histSbopIntrfcTrans = histSbopIntrfcTrans;
//	}
//
//	//bi-directional many-to-one association to HistSsaDmIntrfcTran
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<HistSsaDmIntrfcTran> getHistSsaDmIntrfcTrans() {
//		return this.histSsaDmIntrfcTrans;
//	}
//	public void setHistSsaDmIntrfcTrans(java.util.Set<HistSsaDmIntrfcTran> histSsaDmIntrfcTrans) {
//		this.histSsaDmIntrfcTrans = histSsaDmIntrfcTrans;
//	}
//
//	//bi-directional many-to-one association to HoldPymtIntrfc
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<HoldPymtIntrfc> getHoldPymtIntrfcs() {
//		return this.holdPymtIntrfcs;
//	}
//	public void setHoldPymtIntrfcs(java.util.Set<HoldPymtIntrfc> holdPymtIntrfcs) {
//		this.holdPymtIntrfcs = holdPymtIntrfcs;
//	}
//
//	//bi-directional many-to-one association to IncmngDcmnt
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<IncmngDcmnt> getIncmngDcmnts1() {
//		return this.incmngDcmnts1;
//	}
//	public void setIncmngDcmnts1(java.util.Set<IncmngDcmnt> incmngDcmnts1) {
//		this.incmngDcmnts1 = incmngDcmnts1;
//	}
//
//	//bi-directional many-to-one association to IncmngDcmnt
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<IncmngDcmnt> getIncmngDcmnts2() {
//		return this.incmngDcmnts2;
//	}
//	public void setIncmngDcmnts2(java.util.Set<IncmngDcmnt> incmngDcmnts2) {
//		this.incmngDcmnts2 = incmngDcmnts2;
//	}

	//bi-directional many-to-one association to Income
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<Income> getIncomes() {
		return this.incomes;
	}
	public void setIncomes(java.util.Set<Income> incomes) {
		this.incomes = incomes;
	}

//	//bi-directional many-to-one association to IncomeDductn
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<IncomeDductn> getIncomeDductns() {
//		return this.incomeDductns;
//	}
//	public void setIncomeDductns(java.util.Set<IncomeDductn> incomeDductns) {
//		this.incomeDductns = incomeDductns;
//	}

	//bi-directional many-to-one association to IncomeDecn
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<IncomeDecn> getIncomeDecns() {
		return this.incomeDecns;
	}
	public void setIncomeDecns(java.util.Set<IncomeDecn> incomeDecns) {
		this.incomeDecns = incomeDecns;
	}

//	//bi-directional many-to-one association to InsIntrfc
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<InsIntrfc> getInsIntrfcs() {
//		return this.insIntrfcs;
//	}
//	public void setInsIntrfcs(java.util.Set<InsIntrfc> insIntrfcs) {
//		this.insIntrfcs = insIntrfcs;
//	}
//
//	//bi-directional many-to-one association to IntrfcIncome
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<IntrfcIncome> getIntrfcIncomes() {
//		return this.intrfcIncomes;
//	}
//	public void setIntrfcIncomes(java.util.Set<IntrfcIncome> intrfcIncomes) {
//		this.intrfcIncomes = intrfcIncomes;
//	}
//
//	//bi-directional many-to-one association to Liabty
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<Liabty> getLiabties() {
//		return this.liabties;
//	}
//	public void setLiabties(java.util.Set<Liabty> liabties) {
//		this.liabties = liabties;
//	}
//
//	//bi-directional many-to-one association to MiscDecnBene
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<MiscDecnBene> getMiscDecnBenes() {
//		return this.miscDecnBenes;
//	}
//	public void setMiscDecnBenes(java.util.Set<MiscDecnBene> miscDecnBenes) {
//		this.miscDecnBenes = miscDecnBenes;
//	}
//
//	//bi-directional many-to-one association to Notfcn
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<Notfcn> getNotfcns1() {
//		return this.notfcns1;
//	}
//	public void setNotfcns1(java.util.Set<Notfcn> notfcns1) {
//		this.notfcns1 = notfcns1;
//	}
//
//	//bi-directional many-to-one association to Notfcn
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<Notfcn> getNotfcns2() {
//		return this.notfcns2;
//	}
//	public void setNotfcns2(java.util.Set<Notfcn> notfcns2) {
//		this.notfcns2 = notfcns2;
//	}
//
//	//bi-directional many-to-one association to NotfcnPrfil
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<NotfcnPrfil> getNotfcnPrfils() {
//		return this.notfcnPrfils;
//	}
//	public void setNotfcnPrfils(java.util.Set<NotfcnPrfil> notfcnPrfils) {
//		this.notfcnPrfils = notfcnPrfils;
//	}
//
//	//bi-directional many-to-one association to OffsetPlan
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<OffsetPlan> getOffsetPlans1() {
//		return this.offsetPlans1;
//	}
//	public void setOffsetPlans1(java.util.Set<OffsetPlan> offsetPlans1) {
//		this.offsetPlans1 = offsetPlans1;
//	}
//
//	//bi-directional many-to-one association to OffsetPlan
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<OffsetPlan> getOffsetPlans2() {
//		return this.offsetPlans2;
//	}
//	public void setOffsetPlans2(java.util.Set<OffsetPlan> offsetPlans2) {
//		this.offsetPlans2 = offsetPlans2;
//	}
//
//	//bi-directional many-to-one association to OffsetPlan
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<OffsetPlan> getOffsetPlans3() {
//		return this.offsetPlans3;
//	}
//	public void setOffsetPlans3(java.util.Set<OffsetPlan> offsetPlans3) {
//		this.offsetPlans3 = offsetPlans3;
//	}

	//bi-directional many-to-one association to Org
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<Org> getOrgs() {
		return this.orgs;
	}
	public void setOrgs(java.util.Set<Org> orgs) {
		this.orgs = orgs;
	}

	//bi-directional many-to-one association to OutgngDcmnt
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<OutgngDcmnt> getOutgngDcmnts1() {
		return this.outgngDcmnts1;
	}
	public void setOutgngDcmnts1(java.util.Set<OutgngDcmnt> outgngDcmnts1) {
		this.outgngDcmnts1 = outgngDcmnts1;
	}

	//bi-directional many-to-one association to OutgngDcmnt
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<OutgngDcmnt> getOutgngDcmnts2() {
		return this.outgngDcmnts2;
	}
	public void setOutgngDcmnts2(java.util.Set<OutgngDcmnt> outgngDcmnts2) {
		this.outgngDcmnts2 = outgngDcmnts2;
	}

	//bi-directional many-to-one association to Person
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<Person> getPersons() {
		return this.persons;
	}
	public void setPersons(java.util.Set<Person> persons) {
		this.persons = persons;
	}

//	//bi-directional many-to-one association to PrintJob
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<PrintJob> getPrintJobs() {
//		return this.printJobs;
//	}
//	public void setPrintJobs(java.util.Set<PrintJob> printJobs) {
//		this.printJobs = printJobs;
//	}
//
//	//bi-directional many-to-one association to PriorsRecip
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<PriorsRecip> getPriorsRecips() {
//		return this.priorsRecips;
//	}
//	public void setPriorsRecips(java.util.Set<PriorsRecip> priorsRecips) {
//		this.priorsRecips = priorsRecips;
//	}
//
//	//bi-directional many-to-one association to PrprtyInsPolicy
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<PrprtyInsPolicy> getPrprtyInsPolicies() {
//		return this.prprtyInsPolicies;
//	}
//	public void setPrprtyInsPolicies(java.util.Set<PrprtyInsPolicy> prprtyInsPolicies) {
//		this.prprtyInsPolicies = prprtyInsPolicies;
//	}
//
//	//bi-directional many-to-one association to PrprtyPtcpnt
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<PrprtyPtcpnt> getPrprtyPtcpnts() {
//		return this.prprtyPtcpnts;
//	}
//	public void setPrprtyPtcpnts(java.util.Set<PrprtyPtcpnt> prprtyPtcpnts) {
//		this.prprtyPtcpnts = prprtyPtcpnts;
//	}

	//bi-directional many-to-one association to PtcpntAddr
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntAddr> getPtcpntAddrs() {
		return this.ptcpntAddrs;
	}
	public void setPtcpntAddrs(java.util.Set<PtcpntAddr> ptcpntAddrs) {
		this.ptcpntAddrs = ptcpntAddrs;
	}

	//bi-directional many-to-one association to PtcpntAlia
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntAlia> getPtcpntAlias() {
		return this.ptcpntAlias;
	}
	public void setPtcpntAlias(java.util.Set<PtcpntAlia> ptcpntAlias) {
		this.ptcpntAlias = ptcpntAlias;
	}

	//bi-directional many-to-one association to PtcpntDiary
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntDiary> getPtcpntDiaries() {
		return this.ptcpntDiaries;
	}
	public void setPtcpntDiaries(java.util.Set<PtcpntDiary> ptcpntDiaries) {
		this.ptcpntDiaries = ptcpntDiaries;
	}

	//bi-directional many-to-one association to PtcpntDisptn
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntDisptn> getPtcpntDisptns() {
		return this.ptcpntDisptns;
	}
	public void setPtcpntDisptns(java.util.Set<PtcpntDisptn> ptcpntDisptns) {
		this.ptcpntDisptns = ptcpntDisptns;
	}

	//bi-directional many-to-one association to PtcpntDpositAcnt
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntDpositAcnt> getPtcpntDpositAcnts() {
		return this.ptcpntDpositAcnts;
	}
	public void setPtcpntDpositAcnts(java.util.Set<PtcpntDpositAcnt> ptcpntDpositAcnts) {
		this.ptcpntDpositAcnts = ptcpntDpositAcnts;
	}

	//bi-directional many-to-one association to PtcpntIncmngDcmnt
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntIncmngDcmnt> getPtcpntIncmngDcmnts() {
		return this.ptcpntIncmngDcmnts;
	}
	public void setPtcpntIncmngDcmnts(java.util.Set<PtcpntIncmngDcmnt> ptcpntIncmngDcmnts) {
		this.ptcpntIncmngDcmnts = ptcpntIncmngDcmnts;
	}

	//bi-directional many-to-one association to PtcpntLctn
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntLctn> getPtcpntLctns() {
		return this.ptcpntLctns;
	}
	public void setPtcpntLctns(java.util.Set<PtcpntLctn> ptcpntLctns) {
		this.ptcpntLctns = ptcpntLctns;
	}

	//bi-directional many-to-one association to PtcpntLedgerDcmnt
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntLedgerDcmnt> getPtcpntLedgerDcmnts1() {
		return this.ptcpntLedgerDcmnts1;
	}
	public void setPtcpntLedgerDcmnts1(java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts1) {
		this.ptcpntLedgerDcmnts1 = ptcpntLedgerDcmnts1;
	}

	//bi-directional many-to-one association to PtcpntLedgerDcmnt
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntLedgerDcmnt> getPtcpntLedgerDcmnts2() {
		return this.ptcpntLedgerDcmnts2;
	}
	public void setPtcpntLedgerDcmnts2(java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts2) {
		this.ptcpntLedgerDcmnts2 = ptcpntLedgerDcmnts2;
	}

	//bi-directional many-to-one association to PtcpntLedgerDcmnt
	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntLedgerDcmnt> getPtcpntLedgerDcmnts3() {
		return this.ptcpntLedgerDcmnts3;
	}
	public void setPtcpntLedgerDcmnts3(java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts3) {
		this.ptcpntLedgerDcmnts3 = ptcpntLedgerDcmnts3;
	}

	//bi-directional many-to-one association to PtcpntMtgeLoan
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntMtgeLoan> getPtcpntMtgeLoans() {
		return this.ptcpntMtgeLoans;
	}
	public void setPtcpntMtgeLoans(java.util.Set<PtcpntMtgeLoan> ptcpntMtgeLoans) {
		this.ptcpntMtgeLoans = ptcpntMtgeLoans;
	}

	//bi-directional many-to-one association to PtcpntNote
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntNote> getPtcpntNotes1() {
		return this.ptcpntNotes1;
	}
	public void setPtcpntNotes1(java.util.Set<PtcpntNote> ptcpntNotes1) {
		this.ptcpntNotes1 = ptcpntNotes1;
	}

	//bi-directional many-to-one association to PtcpntNote
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntNote> getPtcpntNotes2() {
		return this.ptcpntNotes2;
	}
	public void setPtcpntNotes2(java.util.Set<PtcpntNote> ptcpntNotes2) {
		this.ptcpntNotes2 = ptcpntNotes2;
	}

	//bi-directional many-to-one association to PtcpntOutgngDcmnt
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntOutgngDcmnt> getPtcpntOutgngDcmnts() {
		return this.ptcpntOutgngDcmnts;
	}
	public void setPtcpntOutgngDcmnts(java.util.Set<PtcpntOutgngDcmnt> ptcpntOutgngDcmnts) {
		this.ptcpntOutgngDcmnts = ptcpntOutgngDcmnts;
	}

	//bi-directional many-to-one association to PtcpntPhone
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntPhone> getPtcpntPhones() {
		return this.ptcpntPhones;
	}
	public void setPtcpntPhones(java.util.Set<PtcpntPhone> ptcpntPhones) {
		this.ptcpntPhones = ptcpntPhones;
	}

	//bi-directional many-to-one association to PtcpntRlnshp
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshp> getPtcpntRlnshps1() {
		return this.ptcpntRlnshps1;
	}
	public void setPtcpntRlnshps1(java.util.Set<PtcpntRlnshp> ptcpntRlnshps1) {
		this.ptcpntRlnshps1 = ptcpntRlnshps1;
	}

	//bi-directional many-to-one association to PtcpntRlnshp
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshp> getPtcpntRlnshps2() {
		return this.ptcpntRlnshps2;
	}
	public void setPtcpntRlnshps2(java.util.Set<PtcpntRlnshp> ptcpntRlnshps2) {
		this.ptcpntRlnshps2 = ptcpntRlnshps2;
	}

	//bi-directional many-to-one association to PtcpntRlnshpAprvdState
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshpAprvdState> getPtcpntRlnshpAprvdStates1() {
		return this.ptcpntRlnshpAprvdStates1;
	}
	public void setPtcpntRlnshpAprvdStates1(java.util.Set<PtcpntRlnshpAprvdState> ptcpntRlnshpAprvdStates1) {
		this.ptcpntRlnshpAprvdStates1 = ptcpntRlnshpAprvdStates1;
	}

	//bi-directional many-to-one association to PtcpntRlnshpAprvdState
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshpAprvdState> getPtcpntRlnshpAprvdStates2() {
		return this.ptcpntRlnshpAprvdStates2;
	}
	public void setPtcpntRlnshpAprvdStates2(java.util.Set<PtcpntRlnshpAprvdState> ptcpntRlnshpAprvdStates2) {
		this.ptcpntRlnshpAprvdStates2 = ptcpntRlnshpAprvdStates2;
	}

	//bi-directional many-to-one association to PtcpntRlnshpHist
	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshpHist> getPtcpntRlnshpHists1() {
		return this.ptcpntRlnshpHists1;
	}
	public void setPtcpntRlnshpHists1(java.util.Set<PtcpntRlnshpHist> ptcpntRlnshpHists1) {
		this.ptcpntRlnshpHists1 = ptcpntRlnshpHists1;
	}

	//bi-directional many-to-one association to PtcpntRlnshpHist
	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntRlnshpHist> getPtcpntRlnshpHists2() {
		return this.ptcpntRlnshpHists2;
	}
	public void setPtcpntRlnshpHists2(java.util.Set<PtcpntRlnshpHist> ptcpntRlnshpHists2) {
		this.ptcpntRlnshpHists2 = ptcpntRlnshpHists2;
	}

//	//bi-directional many-to-one association to Pybl
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<Pybl> getPybls1() {
//		return this.pybls1;
//	}
//	public void setPybls1(java.util.Set<Pybl> pybls1) {
//		this.pybls1 = pybls1;
//	}
//
//	//bi-directional many-to-one association to Pybl
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<Pybl> getPybls2() {
//		return this.pybls2;
//	}
//	public void setPybls2(java.util.Set<Pybl> pybls2) {
//		this.pybls2 = pybls2;
//	}
//
//	//bi-directional many-to-one association to Pybl
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<Pybl> getPybls3() {
//		return this.pybls3;
//	}
//	public void setPybls3(java.util.Set<Pybl> pybls3) {
//		this.pybls3 = pybls3;
//	}
//
//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts1() {
//		return this.pymts1;
//	}
//	public void setPymts1(java.util.Set<Pymt> pymts1) {
//		this.pymts1 = pymts1;
//	}
//
//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts2() {
//		return this.pymts2;
//	}
//	public void setPymts2(java.util.Set<Pymt> pymts2) {
//		this.pymts2 = pymts2;
//	}
//
//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts3() {
//		return this.pymts3;
//	}
//	public void setPymts3(java.util.Set<Pymt> pymts3) {
//		this.pymts3 = pymts3;
//	}

	//bi-directional many-to-one association to RbaPrfil
	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfil> getRbaPrfils() {
		return this.rbaPrfils;
	}
	public void setRbaPrfils(java.util.Set<RbaPrfil> rbaPrfils) {
		this.rbaPrfils = rbaPrfils;
	}

//	//bi-directional many-to-one association to Rcvbl
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<Rcvbl> getRcvbls1() {
//		return this.rcvbls1;
//	}
//	public void setRcvbls1(java.util.Set<Rcvbl> rcvbls1) {
//		this.rcvbls1 = rcvbls1;
//	}
//
//	//bi-directional many-to-one association to Rcvbl
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<Rcvbl> getRcvbls2() {
//		return this.rcvbls2;
//	}
//	public void setRcvbls2(java.util.Set<Rcvbl> rcvbls2) {
//		this.rcvbls2 = rcvbls2;
//	}
//
//	//bi-directional many-to-one association to RecrngPybl
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<RecrngPybl> getRecrngPybls1() {
//		return this.recrngPybls1;
//	}
//	public void setRecrngPybls1(java.util.Set<RecrngPybl> recrngPybls1) {
//		this.recrngPybls1 = recrngPybls1;
//	}
//
//	//bi-directional many-to-one association to RecrngPybl
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<RecrngPybl> getRecrngPybls2() {
//		return this.recrngPybls2;
//	}
//	public void setRecrngPybls2(java.util.Set<RecrngPybl> recrngPybls2) {
//		this.recrngPybls2 = recrngPybls2;
//	}
//
//	//bi-directional many-to-one association to RecrngPybl
//	@OneToMany(mappedBy="ptcpnt3", fetch=FetchType.LAZY)
//	public java.util.Set<RecrngPybl> getRecrngPybls3() {
//		return this.recrngPybls3;
//	}
//	public void setRecrngPybls3(java.util.Set<RecrngPybl> recrngPybls3) {
//		this.recrngPybls3 = recrngPybls3;
//	}
//
//	//bi-directional many-to-one association to StdDvlpmtAction
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<StdDvlpmtAction> getStdDvlpmtActions() {
//		return this.stdDvlpmtActions;
//	}
//	public void setStdDvlpmtActions(java.util.Set<StdDvlpmtAction> stdDvlpmtActions) {
//		this.stdDvlpmtActions = stdDvlpmtActions;
//	}
//
//	//bi-directional many-to-one association to StnOfJrsdtn
//	@OneToMany(mappedBy="ptcpnt1", fetch=FetchType.LAZY)
//	public java.util.Set<StnOfJrsdtn> getStnOfJrsdtns1() {
//		return this.stnOfJrsdtns1;
//	}
//	public void setStnOfJrsdtns1(java.util.Set<StnOfJrsdtn> stnOfJrsdtns1) {
//		this.stnOfJrsdtns1 = stnOfJrsdtns1;
//	}
//
//	//bi-directional many-to-one association to StnOfJrsdtn
//	@OneToMany(mappedBy="ptcpnt2", fetch=FetchType.LAZY)
//	public java.util.Set<StnOfJrsdtn> getStnOfJrsdtns2() {
//		return this.stnOfJrsdtns2;
//	}
//	public void setStnOfJrsdtns2(java.util.Set<StnOfJrsdtn> stnOfJrsdtns2) {
//		this.stnOfJrsdtns2 = stnOfJrsdtns2;
//	}
//
//	//bi-directional many-to-one association to TempPerson
//	@OneToMany(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public java.util.Set<TempPerson> getTempPersons() {
//		return this.tempPersons;
//	}
//	public void setTempPersons(java.util.Set<TempPerson> tempPersons) {
//		this.tempPersons = tempPersons;
//	}
//
//	//bi-directional one-to-one association to TempVendor
//	@OneToOne(mappedBy="ptcpnt", fetch=FetchType.LAZY)
//	public TempVendor getTempVendor() {
//		return this.tempVendor;
//	}
//	public void setTempVendor(TempVendor tempVendor) {
//		this.tempVendor = tempVendor;
//	}

	
}