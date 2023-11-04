package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the STN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="STN")
public class Stn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private String adjustRsnblValueInd;
	private String cd;
	private String descpTxt;
	private String estabRsnblValueInd;
	private String fcltyTypeCd;
	private String nm;
//	private java.util.Set<AcntblBal> acntblBals;
//	private java.util.Set<ApplcnLogon> applcnLogons;
//	private java.util.Set<ApplcnNotfcn> applcnNotfcns;
//	private java.util.Set<AwardEvent> awardEvents;
	private java.util.Set<BnftClaim> bnftClaims1;
	private java.util.Set<BnftClaim> bnftClaims2;
//	private java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses1;
//	private java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses2;
//	private java.util.Set<CaseAsgnmtStatus> caseAsgnmtStatuses;
//	private java.util.Set<CaseTravel> caseTravels;
//	private java.util.Set<Ch31IntrfcCaddTran> ch31IntrfcCaddTrans;
//	private java.util.Set<Ch31IntrfcCastTran> ch31IntrfcCastTrans;
//	private java.util.Set<Ch31IntrfcCorrTran> ch31IntrfcCorrTrans;
//	private java.util.Set<ClaimAsgnmt> claimAsgnmts;
//	private java.util.Set<ClaimTypePrfil> claimTypePrfils;
//	private java.util.Set<CntrctPymt> cntrctPymts;
//	private java.util.Set<CntyFip> cntyFips;
//	private java.util.Set<CpClaim> cpClaims;
//	private java.util.Set<DiaryAction> diaryActions;
//	private java.util.Set<DoorIntrfcTran> doorIntrfcTrans;
//	private java.util.Set<EduPrvdrPymtAmount> eduPrvdrPymtAmounts;
//	private java.util.Set<FinclBusnsTran> finclBusnsTrans;
//	private java.util.Set<FmsTran> fmsTrans;
//	private java.util.Set<GeoPrefix> geoPrefixs;
//	private java.util.Set<GroupPrfil> groupPrfils;
//	private java.util.Set<LpMcrvFile> lpMcrvFiles;
//	private java.util.Set<LpOnelokFile> lpOnelokFiles;
//	private java.util.Set<MaintcRule> maintcRules;
//	private java.util.Set<Milage> milages;
//	private java.util.Set<OutgngDcmnt> outgngDcmnts;
//	private java.util.Set<PersonScrtyLog> personScrtyLogs;
//	private java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts;
//	private java.util.Set<Pybl> pybls;
//	private java.util.Set<Pymt> pymts;
//	private java.util.Set<Rcvbl> rcvbls;
//	private java.util.Set<RecrngPybl> recrngPybls;
//	private java.util.Set<RepPoc> repPocs;
//	private java.util.Set<Reqst> reqsts;
//	private java.util.Set<ReqstOpertnRule> reqstOpertnRules;
//	private java.util.Set<StdDvlpmtAction> stdDvlpmtActions;
	private Lctn lctn;
//	private java.util.Set<StnAddrsPrfil> stnAddrsPrfils;
//	private java.util.Set<StnApplcnOpertnRule> stnApplcnOpertnRules;
//	private java.util.Set<StnApplcnRole> stnApplcnRoles;
//	private java.util.Set<StnLckoutApplcn> stnLckoutApplcns;
//	private java.util.Set<StnOfJrsdtn> stnOfJrsdtns;
//	private StnPrfil stnPrfil;
//	private java.util.Set<StnSuspnsPrfil> stnSuspnsPrfils;
//	private java.util.Set<SvcrHolderPoc> svcrHolderPocs;
//	private java.util.Set<Team> teams;
//	private java.util.Set<TempPerson> tempPersons;
//	private java.util.Set<UtltyRule> utltyRules;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Stn() {
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
	@Column(name="LCTN_ID", unique=true, nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Basic()
	@Column(name="ADJUST_RSNBL_VALUE_IND", length=1)
	public String getAdjustRsnblValueInd() {
		return this.adjustRsnblValueInd;
	}
	public void setAdjustRsnblValueInd(String adjustRsnblValueInd) {
		this.adjustRsnblValueInd = adjustRsnblValueInd;
	}

	@Basic()
	@Column(name="CD", length=2)
	public String getCd() {
		return this.cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
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
	@Column(name="ESTAB_RSNBL_VALUE_IND", length=1)
	public String getEstabRsnblValueInd() {
		return this.estabRsnblValueInd;
	}
	public void setEstabRsnblValueInd(String estabRsnblValueInd) {
		this.estabRsnblValueInd = estabRsnblValueInd;
	}

	@Basic()
	@Column(name="FCLTY_TYPE_CD", nullable=false, length=12)
	public String getFcltyTypeCd() {
		return this.fcltyTypeCd;
	}
	public void setFcltyTypeCd(String fcltyTypeCd) {
		this.fcltyTypeCd = fcltyTypeCd;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=50)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

//	//bi-directional many-to-one association to AcntblBal
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<AcntblBal> getAcntblBals() {
//		return this.acntblBals;
//	}
//	public void setAcntblBals(java.util.Set<AcntblBal> acntblBals) {
//		this.acntblBals = acntblBals;
//	}
//
//	//bi-directional many-to-one association to ApplcnLogon
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<ApplcnLogon> getApplcnLogons() {
//		return this.applcnLogons;
//	}
//	public void setApplcnLogons(java.util.Set<ApplcnLogon> applcnLogons) {
//		this.applcnLogons = applcnLogons;
//	}
//
//	//bi-directional many-to-one association to ApplcnNotfcn
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<ApplcnNotfcn> getApplcnNotfcns() {
//		return this.applcnNotfcns;
//	}
//	public void setApplcnNotfcns(java.util.Set<ApplcnNotfcn> applcnNotfcns) {
//		this.applcnNotfcns = applcnNotfcns;
//	}
//
//	//bi-directional many-to-one association to AwardEvent
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<AwardEvent> getAwardEvents() {
//		return this.awardEvents;
//	}
//	public void setAwardEvents(java.util.Set<AwardEvent> awardEvents) {
//		this.awardEvents = awardEvents;
//	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="stn1", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims1() {
		return this.bnftClaims1;
	}
	public void setBnftClaims1(java.util.Set<BnftClaim> bnftClaims1) {
		this.bnftClaims1 = bnftClaims1;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="stn2", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims2() {
		return this.bnftClaims2;
	}
	public void setBnftClaims2(java.util.Set<BnftClaim> bnftClaims2) {
		this.bnftClaims2 = bnftClaims2;
	}

//	//bi-directional many-to-one association to BnftClaimLcStatus
//	@OneToMany(mappedBy="stn1", fetch=FetchType.LAZY)
//	public java.util.Set<BnftClaimLcStatus> getBnftClaimLcStatuses1() {
//		return this.bnftClaimLcStatuses1;
//	}
//	public void setBnftClaimLcStatuses1(java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses1) {
//		this.bnftClaimLcStatuses1 = bnftClaimLcStatuses1;
//	}
//
//	//bi-directional many-to-one association to BnftClaimLcStatus
//	@OneToMany(mappedBy="stn2", fetch=FetchType.LAZY)
//	public java.util.Set<BnftClaimLcStatus> getBnftClaimLcStatuses2() {
//		return this.bnftClaimLcStatuses2;
//	}
//	public void setBnftClaimLcStatuses2(java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses2) {
//		this.bnftClaimLcStatuses2 = bnftClaimLcStatuses2;
//	}
//
//	//bi-directional many-to-one association to CaseAsgnmtStatus
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<CaseAsgnmtStatus> getCaseAsgnmtStatuses() {
//		return this.caseAsgnmtStatuses;
//	}
//	public void setCaseAsgnmtStatuses(java.util.Set<CaseAsgnmtStatus> caseAsgnmtStatuses) {
//		this.caseAsgnmtStatuses = caseAsgnmtStatuses;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels() {
//		return this.caseTravels;
//	}
//	public void setCaseTravels(java.util.Set<CaseTravel> caseTravels) {
//		this.caseTravels = caseTravels;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCaddTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCaddTran> getCh31IntrfcCaddTrans() {
//		return this.ch31IntrfcCaddTrans;
//	}
//	public void setCh31IntrfcCaddTrans(java.util.Set<Ch31IntrfcCaddTran> ch31IntrfcCaddTrans) {
//		this.ch31IntrfcCaddTrans = ch31IntrfcCaddTrans;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCastTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCastTran> getCh31IntrfcCastTrans() {
//		return this.ch31IntrfcCastTrans;
//	}
//	public void setCh31IntrfcCastTrans(java.util.Set<Ch31IntrfcCastTran> ch31IntrfcCastTrans) {
//		this.ch31IntrfcCastTrans = ch31IntrfcCastTrans;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCorrTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCorrTran> getCh31IntrfcCorrTrans() {
//		return this.ch31IntrfcCorrTrans;
//	}
//	public void setCh31IntrfcCorrTrans(java.util.Set<Ch31IntrfcCorrTran> ch31IntrfcCorrTrans) {
//		this.ch31IntrfcCorrTrans = ch31IntrfcCorrTrans;
//	}
//
//	//bi-directional many-to-one association to ClaimAsgnmt
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<ClaimAsgnmt> getClaimAsgnmts() {
//		return this.claimAsgnmts;
//	}
//	public void setClaimAsgnmts(java.util.Set<ClaimAsgnmt> claimAsgnmts) {
//		this.claimAsgnmts = claimAsgnmts;
//	}
//
//	//bi-directional many-to-one association to ClaimTypePrfil
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<ClaimTypePrfil> getClaimTypePrfils() {
//		return this.claimTypePrfils;
//	}
//	public void setClaimTypePrfils(java.util.Set<ClaimTypePrfil> claimTypePrfils) {
//		this.claimTypePrfils = claimTypePrfils;
//	}
//
//	//bi-directional many-to-one association to CntrctPymt
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<CntrctPymt> getCntrctPymts() {
//		return this.cntrctPymts;
//	}
//	public void setCntrctPymts(java.util.Set<CntrctPymt> cntrctPymts) {
//		this.cntrctPymts = cntrctPymts;
//	}
//
//	//bi-directional many-to-one association to CntyFip
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<CntyFip> getCntyFips() {
//		return this.cntyFips;
//	}
//	public void setCntyFips(java.util.Set<CntyFip> cntyFips) {
//		this.cntyFips = cntyFips;
//	}
//
//	//bi-directional many-to-one association to CpClaim
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<CpClaim> getCpClaims() {
//		return this.cpClaims;
//	}
//	public void setCpClaims(java.util.Set<CpClaim> cpClaims) {
//		this.cpClaims = cpClaims;
//	}
//
//	//bi-directional many-to-one association to DiaryAction
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<DiaryAction> getDiaryActions() {
//		return this.diaryActions;
//	}
//	public void setDiaryActions(java.util.Set<DiaryAction> diaryActions) {
//		this.diaryActions = diaryActions;
//	}
//
//	//bi-directional many-to-one association to DoorIntrfcTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<DoorIntrfcTran> getDoorIntrfcTrans() {
//		return this.doorIntrfcTrans;
//	}
//	public void setDoorIntrfcTrans(java.util.Set<DoorIntrfcTran> doorIntrfcTrans) {
//		this.doorIntrfcTrans = doorIntrfcTrans;
//	}
//
//	//bi-directional many-to-one association to EduPrvdrPymtAmount
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<EduPrvdrPymtAmount> getEduPrvdrPymtAmounts() {
//		return this.eduPrvdrPymtAmounts;
//	}
//	public void setEduPrvdrPymtAmounts(java.util.Set<EduPrvdrPymtAmount> eduPrvdrPymtAmounts) {
//		this.eduPrvdrPymtAmounts = eduPrvdrPymtAmounts;
//	}
//
//	//bi-directional many-to-one association to FinclBusnsTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<FinclBusnsTran> getFinclBusnsTrans() {
//		return this.finclBusnsTrans;
//	}
//	public void setFinclBusnsTrans(java.util.Set<FinclBusnsTran> finclBusnsTrans) {
//		this.finclBusnsTrans = finclBusnsTrans;
//	}
//
//	//bi-directional many-to-one association to FmsTran
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<FmsTran> getFmsTrans() {
//		return this.fmsTrans;
//	}
//	public void setFmsTrans(java.util.Set<FmsTran> fmsTrans) {
//		this.fmsTrans = fmsTrans;
//	}
//
//	//bi-directional many-to-one association to GeoPrefix
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<GeoPrefix> getGeoPrefixs() {
//		return this.geoPrefixs;
//	}
//	public void setGeoPrefixs(java.util.Set<GeoPrefix> geoPrefixs) {
//		this.geoPrefixs = geoPrefixs;
//	}
//
//	//bi-directional many-to-one association to GroupPrfil
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<GroupPrfil> getGroupPrfils() {
//		return this.groupPrfils;
//	}
//	public void setGroupPrfils(java.util.Set<GroupPrfil> groupPrfils) {
//		this.groupPrfils = groupPrfils;
//	}
//
//	//bi-directional many-to-one association to LpMcrvFile
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<LpMcrvFile> getLpMcrvFiles() {
//		return this.lpMcrvFiles;
//	}
//	public void setLpMcrvFiles(java.util.Set<LpMcrvFile> lpMcrvFiles) {
//		this.lpMcrvFiles = lpMcrvFiles;
//	}
//
//	//bi-directional many-to-one association to LpOnelokFile
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<LpOnelokFile> getLpOnelokFiles() {
//		return this.lpOnelokFiles;
//	}
//	public void setLpOnelokFiles(java.util.Set<LpOnelokFile> lpOnelokFiles) {
//		this.lpOnelokFiles = lpOnelokFiles;
//	}
//
//	//bi-directional many-to-one association to MaintcRule
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<MaintcRule> getMaintcRules() {
//		return this.maintcRules;
//	}
//	public void setMaintcRules(java.util.Set<MaintcRule> maintcRules) {
//		this.maintcRules = maintcRules;
//	}
//
//	//bi-directional many-to-one association to Milage
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Milage> getMilages() {
//		return this.milages;
//	}
//	public void setMilages(java.util.Set<Milage> milages) {
//		this.milages = milages;
//	}
//
//	//bi-directional many-to-one association to OutgngDcmnt
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<OutgngDcmnt> getOutgngDcmnts() {
//		return this.outgngDcmnts;
//	}
//	public void setOutgngDcmnts(java.util.Set<OutgngDcmnt> outgngDcmnts) {
//		this.outgngDcmnts = outgngDcmnts;
//	}
//
//	//bi-directional many-to-one association to PersonScrtyLog
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<PersonScrtyLog> getPersonScrtyLogs() {
//		return this.personScrtyLogs;
//	}
//	public void setPersonScrtyLogs(java.util.Set<PersonScrtyLog> personScrtyLogs) {
//		this.personScrtyLogs = personScrtyLogs;
//	}

//	//bi-directional many-to-one association to PtcpntLedgerDcmnt
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<PtcpntLedgerDcmnt> getPtcpntLedgerDcmnts() {
//		return this.ptcpntLedgerDcmnts;
//	}
//	public void setPtcpntLedgerDcmnts(java.util.Set<PtcpntLedgerDcmnt> ptcpntLedgerDcmnts) {
//		this.ptcpntLedgerDcmnts = ptcpntLedgerDcmnts;
//	}
//
//	//bi-directional many-to-one association to Pybl
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Pybl> getPybls() {
//		return this.pybls;
//	}
//	public void setPybls(java.util.Set<Pybl> pybls) {
//		this.pybls = pybls;
//	}
//
//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts() {
//		return this.pymts;
//	}
//	public void setPymts(java.util.Set<Pymt> pymts) {
//		this.pymts = pymts;
//	}
//
//	//bi-directional many-to-one association to Rcvbl
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Rcvbl> getRcvbls() {
//		return this.rcvbls;
//	}
//	public void setRcvbls(java.util.Set<Rcvbl> rcvbls) {
//		this.rcvbls = rcvbls;
//	}
//
//	//bi-directional many-to-one association to RecrngPybl
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<RecrngPybl> getRecrngPybls() {
//		return this.recrngPybls;
//	}
//	public void setRecrngPybls(java.util.Set<RecrngPybl> recrngPybls) {
//		this.recrngPybls = recrngPybls;
//	}
//
//	//bi-directional many-to-one association to RepPoc
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<RepPoc> getRepPocs() {
//		return this.repPocs;
//	}
//	public void setRepPocs(java.util.Set<RepPoc> repPocs) {
//		this.repPocs = repPocs;
//	}
//
//	//bi-directional many-to-one association to Reqst
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Reqst> getReqsts() {
//		return this.reqsts;
//	}
//	public void setReqsts(java.util.Set<Reqst> reqsts) {
//		this.reqsts = reqsts;
//	}
//
//	//bi-directional many-to-one association to ReqstOpertnRule
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<ReqstOpertnRule> getReqstOpertnRules() {
//		return this.reqstOpertnRules;
//	}
//	public void setReqstOpertnRules(java.util.Set<ReqstOpertnRule> reqstOpertnRules) {
//		this.reqstOpertnRules = reqstOpertnRules;
//	}
//
//	//bi-directional many-to-one association to StdDvlpmtAction
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StdDvlpmtAction> getStdDvlpmtActions() {
//		return this.stdDvlpmtActions;
//	}
//	public void setStdDvlpmtActions(java.util.Set<StdDvlpmtAction> stdDvlpmtActions) {
//		this.stdDvlpmtActions = stdDvlpmtActions;
//	}

	//bi-directional one-to-one association to Lctn
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
	public Lctn getLctn() {
		return this.lctn;
	}
	public void setLctn(Lctn lctn) {
		this.lctn = lctn;
	}

//	//bi-directional many-to-one association to StnAddrsPrfil
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnAddrsPrfil> getStnAddrsPrfils() {
//		return this.stnAddrsPrfils;
//	}
//	public void setStnAddrsPrfils(java.util.Set<StnAddrsPrfil> stnAddrsPrfils) {
//		this.stnAddrsPrfils = stnAddrsPrfils;
//	}
//
//	//bi-directional many-to-one association to StnApplcnOpertnRule
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnApplcnOpertnRule> getStnApplcnOpertnRules() {
//		return this.stnApplcnOpertnRules;
//	}
//	public void setStnApplcnOpertnRules(java.util.Set<StnApplcnOpertnRule> stnApplcnOpertnRules) {
//		this.stnApplcnOpertnRules = stnApplcnOpertnRules;
//	}
//
//	//bi-directional many-to-one association to StnApplcnRole
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnApplcnRole> getStnApplcnRoles() {
//		return this.stnApplcnRoles;
//	}
//	public void setStnApplcnRoles(java.util.Set<StnApplcnRole> stnApplcnRoles) {
//		this.stnApplcnRoles = stnApplcnRoles;
//	}
//
//	//bi-directional many-to-one association to StnLckoutApplcn
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnLckoutApplcn> getStnLckoutApplcns() {
//		return this.stnLckoutApplcns;
//	}
//	public void setStnLckoutApplcns(java.util.Set<StnLckoutApplcn> stnLckoutApplcns) {
//		this.stnLckoutApplcns = stnLckoutApplcns;
//	}
//
//	//bi-directional many-to-one association to StnOfJrsdtn
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnOfJrsdtn> getStnOfJrsdtns() {
//		return this.stnOfJrsdtns;
//	}
//	public void setStnOfJrsdtns(java.util.Set<StnOfJrsdtn> stnOfJrsdtns) {
//		this.stnOfJrsdtns = stnOfJrsdtns;
//	}
//
//	//bi-directional one-to-one association to StnPrfil
//	@OneToOne(mappedBy="stn", fetch=FetchType.LAZY)
//	public StnPrfil getStnPrfil() {
//		return this.stnPrfil;
//	}
//	public void setStnPrfil(StnPrfil stnPrfil) {
//		this.stnPrfil = stnPrfil;
//	}
//
//	//bi-directional many-to-one association to StnSuspnsPrfil
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<StnSuspnsPrfil> getStnSuspnsPrfils() {
//		return this.stnSuspnsPrfils;
//	}
//	public void setStnSuspnsPrfils(java.util.Set<StnSuspnsPrfil> stnSuspnsPrfils) {
//		this.stnSuspnsPrfils = stnSuspnsPrfils;
//	}
//
//	//bi-directional many-to-one association to SvcrHolderPoc
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<SvcrHolderPoc> getSvcrHolderPocs() {
//		return this.svcrHolderPocs;
//	}
//	public void setSvcrHolderPocs(java.util.Set<SvcrHolderPoc> svcrHolderPocs) {
//		this.svcrHolderPocs = svcrHolderPocs;
//	}
//
//	//bi-directional many-to-one association to Team
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<Team> getTeams() {
//		return this.teams;
//	}
//	public void setTeams(java.util.Set<Team> teams) {
//		this.teams = teams;
//	}
//
//	//bi-directional many-to-one association to TempPerson
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<TempPerson> getTempPersons() {
//		return this.tempPersons;
//	}
//	public void setTempPersons(java.util.Set<TempPerson> tempPersons) {
//		this.tempPersons = tempPersons;
//	}
//
//	//bi-directional many-to-one association to UtltyRule
//	@OneToMany(mappedBy="stn", fetch=FetchType.LAZY)
//	public java.util.Set<UtltyRule> getUtltyRules() {
//		return this.utltyRules;
//	}
//	public void setUtltyRules(java.util.Set<UtltyRule> utltyRules) {
//		this.utltyRules = utltyRules;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Stn)) {
			return false;
		}
		Stn castOther = (Stn)other;
		return new EqualsBuilder()
			.append(this.getLctnId(), castOther.getLctnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getLctnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("lctnId", getLctnId())
			.toString();
	}


}