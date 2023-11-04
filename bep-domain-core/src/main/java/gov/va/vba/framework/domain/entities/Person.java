package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

/**
 * The persistent class for the PERSON database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON")
@SqlResultSetMapping(name="personMapping", entities={@EntityResult(entityClass=Person.class )})
public class Person implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String birthCityNm;
	private Date brthdyDt;
	private String cmptnyDecnTypeCd;
	private Date deathDt;
	private Integer depNbr;
	private String empInd;
	private String entlmtTypeCd;
	private String ethnicTypeCd;
	private String fidDecnCategyTypeCd;
	private String fileNbr;
	private String firstNm;
	private Long firstNmKey;
	private Integer frgnSvcNbr;
	private String genderCd;
	private String lastNm;
	private Long lastNmKey;
	private String lgyEntlmtTypeCd;
	private String middleNm;
	private Long middleNmKey;
	private String mltyPersonInd;
	private Integer monthsPresntEmplyrNbr;
	private Integer netWorthAmt;
	private String noSsnReasonTypeCd;
	private String ocptnTxt;
//	private String personDeathCauseNm;
	private String personDeathCauseTypeNm;
	private String personTypeNm;
	private String potntlDngrsInd;
//	private String raceNm;
	private String raceTypeNm;
	private Double sbstncAmt;
	private String serousEmplmtHndcapInd;
	private String slttnTypeNm;
//	private Date snstvFileChangeDt;
//	private Date snstvFileLastAccessDt;
//	private Date snstvFileReviewDt;
//	private String snstvFileRfrncTxt;
	private String spinaBifidaInd;
	private String ssnNbr;
	private String ssnVrfctnStatusTypeCd;
	private String suffixNm;
	private Long suffixNmKey;
	private String taxAbtmntCd;
	private String termnlDigitNbr;
	private String titleTxt;
	private String vetInd;
	private String vetTypeNm;
	private Integer yearsPresntEmplyrNbr;
//	private java.util.Set<AlpsValeriIntrfc> alpsValeriIntrfcs;
//	private java.util.Set<AwardDiary> awardDiaries;
//	private java.util.Set<AwardOvrideWrksht> awardOvrideWrkshts;
	private java.util.Set<BnftClaim> bnftClaims;
//	private java.util.Set<Case> cases;
//	private java.util.Set<CaseAlert> caseAlerts;
//	private java.util.Set<CaseAsgnmtStatus> caseAsgnmtStatuses;
//	private java.util.Set<CaseAsgnmtStatusLog> caseAsgnmtStatusLogs;
//	private java.util.Set<CaseLctn> caseLctns1;
//	private java.util.Set<CaseLctn> caseLctns2;
//	private java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns1;
//	private java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns2;
//	private java.util.Set<CaseTravel> caseTravels;
//	private java.util.Set<Ch31GedAdmstnReport> ch31GedAdmstnReports;
//	private java.util.Set<Ch31IntrfcCaddTran> ch31IntrfcCaddTrans;
//	private java.util.Set<Ch31IntrfcCastTran> ch31IntrfcCastTrans;
//	private java.util.Set<Ch31IntrfcCorrTran> ch31IntrfcCorrTrans;
//	private java.util.Set<Ch31IntrfcGedTran> ch31IntrfcGedTrans;
//	private java.util.Set<CntrctPymt> cntrctPymts;
//	private java.util.Set<DepncyDecn> depncyDecns;
//	private java.util.Set<EduBnft> eduBnfts;
//	private java.util.Set<EduDep> eduDeps;
//	private java.util.Set<EduPersonCntryWrkld> eduPersonCntryWrklds;
//	private java.util.Set<EduPersonStateWrkld> eduPersonStateWrklds;
//	private java.util.Set<EduPrvdrPymtAmount> eduPrvdrPymtAmounts;
	private Emp emp;
//	private java.util.Set<ErpTranHist> erpTranHists;
//	private ErpTranHist erpTranHist;
//	private FmsTranHist fmsTranHist;
//	private java.util.Set<FocasTran> focasTrans;
//	private java.util.Set<GroupApntmt> groupApntmts;
//	private java.util.Set<GroupApntmtPtcpnt> groupApntmtPtcpnts;
	private java.util.Set<MltyPerson> mltyPersons;
//	private java.util.Set<MtgeLoanCntctSumry> mtgeLoanCntctSumries;
//	private java.util.Set<NonMergedPtcpnt> nonMergedPtcpnts;
//	private NonMergedPtcpnt nonMergedPtcpnt;
	private java.util.Set<ParentMartlStatusDecn> parentMartlStatusDecns;
	private PstalType postalType;
	private Ptcpnt ptcpnt;
	private java.util.Set<PersonApplcnPrfrnc> personApplcnPrfrncs;
	private java.util.Set<PersonDep> personDeps;
	private java.util.Set<PersonEduLevel> personEduLevels;
	private java.util.Set<PersonEmplyr> personEmplyrs;
	private java.util.Set<PersonIdfctnHist> personIdfctnHists;
	private java.util.Set<PersonNetWorth> personNetWorths;
	private java.util.Set<PersonRace> personRaces;
	private java.util.Set<PersonScrtyLevel> personScrtyLevels;
	private java.util.Set<PersonScrtyLog> personScrtyLogs;
	private java.util.Set<PersonSpeclStatus> personSpeclStatuses;
//	private java.util.Set<PriorsLineRecip> priorsLineRecips;
	private java.util.Set<PtcpntCnvrsnMesage> ptcpntCnvrsnMesages;
	private java.util.Set<PtcpntInstzn> ptcpntInstzns;
	private java.util.Set<RatingAwardDetail> ratingAwardDetails1;
	private java.util.Set<RatingAwardDetail> ratingAwardDetails2;
	private java.util.Set<RatingDecn> ratingDecns;
	private java.util.Set<RbaPrfil> rbaPrfils;
//	private java.util.Set<RepymtPlan> repymtPlans;
	private java.util.Set<Reqst> reqsts;
//	private java.util.Set<ScrtyVioltn> scrtyVioltns;
//	private java.util.Set<SntvtyLevel> sntvtyLevels;
	private java.util.Set<TeamPerson> teamPersons;
//	private java.util.Set<UnvbleTime> unvbleTimes;
//	private java.util.Set<UserStnAddrsPrfil> userStnAddrsPrfils;
//	private java.util.Set<VaonceEnrlmtCrtfcn> vaonceEnrlmtCrtfcns;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
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
	
	
    public Person() {
    }
    @Id()
    @Column(name="PTCPNT_ID", nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="BIRTH_CITY_NM", length=30)
	public String getBirthCityNm() {
		return this.birthCityNm;
	}
	public void setBirthCityNm(String birthCityNm) {
		this.birthCityNm = birthCityNm;
	}

	@Basic()
	@Column(name="BRTHDY_DT", length=7)
	public Date getBrthdyDt() {
		return this.brthdyDt;
	}
	public void setBrthdyDt(Date brthdyDt) {
		this.brthdyDt = brthdyDt;
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
	@Column(name="DEATH_DT", length=7)
	public Date getDeathDt() {
		return this.deathDt;
	}
	public void setDeathDt(Date deathDt) {
		this.deathDt = deathDt;
	}

	@Basic()
	@Column(name="DEP_NBR", precision=2)
	public Integer getDepNbr() {
		return this.depNbr;
	}
	public void setDepNbr(Integer depNbr) {
		this.depNbr = depNbr;
	}

	@Basic()
	@Column(name="EMP_IND", length=1)
	public String getEmpInd() {
		return this.empInd;
	}
	public void setEmpInd(String empInd) {
		this.empInd = empInd;
	}

	@Basic()
	@Column(name="ENTLMT_TYPE_CD", length=12)
	public String getEntlmtTypeCd() {
		return this.entlmtTypeCd;
	}
	public void setEntlmtTypeCd(String entlmtTypeCd) {
		this.entlmtTypeCd = entlmtTypeCd;
	}

	@Basic()
	@Column(name="ETHNIC_TYPE_CD", length=12)
	public String getEthnicTypeCd() {
		return this.ethnicTypeCd;
	}
	public void setEthnicTypeCd(String ethnicTypeCd) {
		this.ethnicTypeCd = ethnicTypeCd;
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
	@Column(name="FILE_NBR", unique=true, length=9)
	public String getFileNbr() {
		return this.fileNbr;
	}
	public void setFileNbr(String fileNbr) {
		this.fileNbr = fileNbr;
	}

	@Basic()
	@Column(name="FIRST_NM", nullable=false, length=30)
	public String getFirstNm() {
		return this.firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	@Basic()
	@Column(name="FIRST_NM_KEY", precision=10)
	public Long getFirstNmKey() {
		return this.firstNmKey;
	}
	public void setFirstNmKey(Long firstNmKey) {
		this.firstNmKey = firstNmKey;
	}

	@Basic()
	@Column(name="FRGN_SVC_NBR", precision=9)
	public Integer getFrgnSvcNbr() {
		return this.frgnSvcNbr;
	}
	public void setFrgnSvcNbr(Integer frgnSvcNbr) {
		this.frgnSvcNbr = frgnSvcNbr;
	}

	@Basic()
	@Column(name="GENDER_CD", length=1)
	public String getGenderCd() {
		return this.genderCd;
	}
	public void setGenderCd(String genderCd) {
		this.genderCd = genderCd;
	}

	@Basic()
	@Column(name="LAST_NM", nullable=false, length=50)
	public String getLastNm() {
		return this.lastNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	@Basic()
	@Column(name="LAST_NM_KEY", precision=10)
	public Long getLastNmKey() {
		return this.lastNmKey;
	}
	public void setLastNmKey(Long lastNmKey) {
		this.lastNmKey = lastNmKey;
	}

	@Basic()
	@Column(name="LGY_ENTLMT_TYPE_CD", length=12)
	public String getLgyEntlmtTypeCd() {
		return this.lgyEntlmtTypeCd;
	}
	public void setLgyEntlmtTypeCd(String lgyEntlmtTypeCd) {
		this.lgyEntlmtTypeCd = lgyEntlmtTypeCd;
	}

	@Basic()
	@Column(name="MIDDLE_NM", length=30)
	public String getMiddleNm() {
		return this.middleNm;
	}
	public void setMiddleNm(String middleNm) {
		this.middleNm = middleNm;
	}

	@Basic()
	@Column(name="MIDDLE_NM_KEY", precision=10)
	public Long getMiddleNmKey() {
		return this.middleNmKey;
	}
	public void setMiddleNmKey(Long middleNmKey) {
		this.middleNmKey = middleNmKey;
	}

	@Basic()
	@Column(name="MLTY_PERSON_IND", length=1)
	public String getMltyPersonInd() {
		return this.mltyPersonInd;
	}
	public void setMltyPersonInd(String mltyPersonInd) {
		this.mltyPersonInd = mltyPersonInd;
	}

	@Basic()
	@Column(name="MONTHS_PRESNT_EMPLYR_NBR", precision=2)
	public Integer getMonthsPresntEmplyrNbr() {
		return this.monthsPresntEmplyrNbr;
	}
	public void setMonthsPresntEmplyrNbr(Integer monthsPresntEmplyrNbr) {
		this.monthsPresntEmplyrNbr = monthsPresntEmplyrNbr;
	}

	@Basic()
	@Column(name="NET_WORTH_AMT", precision=9)
	public Integer getNetWorthAmt() {
		return this.netWorthAmt;
	}
	public void setNetWorthAmt(Integer netWorthAmt) {
		this.netWorthAmt = netWorthAmt;
	}

	@Basic()
	@Column(name="NO_SSN_REASON_TYPE_CD", length=12)
	public String getNoSsnReasonTypeCd() {
		return this.noSsnReasonTypeCd;
	}
	public void setNoSsnReasonTypeCd(String noSsnReasonTypeCd) {
		this.noSsnReasonTypeCd = noSsnReasonTypeCd;
	}

	@Basic()
	@Column(name="OCPTN_TXT", length=32)
	public String getOcptnTxt() {
		return this.ocptnTxt;
	}
	public void setOcptnTxt(String ocptnTxt) {
		this.ocptnTxt = ocptnTxt;
	}

//	@Basic()
//	@Column(name="PERSON_DEATH_CAUSE_NM", length=25)
//	public String getPersonDeathCauseNm() {
//		return this.personDeathCauseNm;
//	}
//	public void setPersonDeathCauseNm(String personDeathCauseNm) {
//		this.personDeathCauseNm = personDeathCauseNm;
//	}

	@Basic()
	@Column(name="PERSON_DEATH_CAUSE_TYPE_NM", length=50)
	public String getPersonDeathCauseTypeNm() {
		return this.personDeathCauseTypeNm;
	}
	public void setPersonDeathCauseTypeNm(String personDeathCauseTypeNm) {
		this.personDeathCauseTypeNm = personDeathCauseTypeNm;
	}

	@Basic()
	@Column(name="PERSON_TYPE_NM", length=50)
	public String getPersonTypeNm() {
		return this.personTypeNm;
	}
	public void setPersonTypeNm(String personTypeNm) {
		this.personTypeNm = personTypeNm;
	}

	@Basic()
	@Column(name="POTNTL_DNGRS_IND", length=1)
	public String getPotntlDngrsInd() {
		return this.potntlDngrsInd;
	}
	public void setPotntlDngrsInd(String potntlDngrsInd) {
		this.potntlDngrsInd = potntlDngrsInd;
	}

//	@Basic()
//	@Column(name="RACE_NM", length=20)
//	public String getRaceNm() {
//		return this.raceNm;
//	}
//	public void setRaceNm(String raceNm) {
//		this.raceNm = raceNm;
//	}

	@Basic()
	@Column(name="RACE_TYPE_NM", length=50)
	public String getRaceTypeNm() {
		return this.raceTypeNm;
	}
	public void setRaceTypeNm(String raceTypeNm) {
		this.raceTypeNm = raceTypeNm;
	}

	@Basic()
	@Column(name="SBSTNC_AMT", precision=8, scale=2)
	public Double getSbstncAmt() {
		return this.sbstncAmt;
	}
	public void setSbstncAmt(Double sbstncAmt) {
		this.sbstncAmt = sbstncAmt;
	}

	@Basic()
	@Column(name="SEROUS_EMPLMT_HNDCAP_IND", length=1)
	public String getSerousEmplmtHndcapInd() {
		return this.serousEmplmtHndcapInd;
	}
	public void setSerousEmplmtHndcapInd(String serousEmplmtHndcapInd) {
		this.serousEmplmtHndcapInd = serousEmplmtHndcapInd;
	}

	@Basic()
	@Column(name="SLTTN_TYPE_NM", length=50)
	public String getSlttnTypeNm() {
		return this.slttnTypeNm;
	}
	public void setSlttnTypeNm(String slttnTypeNm) {
		this.slttnTypeNm = slttnTypeNm;
	}

//	@Basic()
//	@Column(name="SNSTV_FILE_CHANGE_DT", length=7)
//	public Date getSnstvFileChangeDt() {
//		return this.snstvFileChangeDt;
//	}
//	public void setSnstvFileChangeDt(Date snstvFileChangeDt) {
//		this.snstvFileChangeDt = snstvFileChangeDt;
//	}
//
//	@Basic()
//	@Column(name="SNSTV_FILE_LAST_ACCESS_DT", length=7)
//	public Date getSnstvFileLastAccessDt() {
//		return this.snstvFileLastAccessDt;
//	}
//	public void setSnstvFileLastAccessDt(Date snstvFileLastAccessDt) {
//		this.snstvFileLastAccessDt = snstvFileLastAccessDt;
//	}
//
//	@Basic()
//	@Column(name="SNSTV_FILE_REVIEW_DT", length=7)
//	public Date getSnstvFileReviewDt() {
//		return this.snstvFileReviewDt;
//	}
//	public void setSnstvFileReviewDt(Date snstvFileReviewDt) {
//		this.snstvFileReviewDt = snstvFileReviewDt;
//	}

//	@Basic()
//	@Column(name="SNSTV_FILE_RFRNC_TXT", length=32)
//	public String getSnstvFileRfrncTxt() {
//		return this.snstvFileRfrncTxt;
//	}
//	public void setSnstvFileRfrncTxt(String snstvFileRfrncTxt) {
//		this.snstvFileRfrncTxt = snstvFileRfrncTxt;
//	}

	@Basic()
	@Column(name="SPINA_BIFIDA_IND", length=1)
	public String getSpinaBifidaInd() {
		return this.spinaBifidaInd;
	}
	public void setSpinaBifidaInd(String spinaBifidaInd) {
		this.spinaBifidaInd = spinaBifidaInd;
	}

	@Basic()
	@Column(name="SSN_NBR", length=9)
	public String getSsnNbr() {
		return this.ssnNbr;
	}
	public void setSsnNbr(String ssnNbr) {
		this.ssnNbr = ssnNbr;
	}

	@Basic()
	@Column(name="SSN_VRFCTN_STATUS_TYPE_CD", length=12)
	public String getSsnVrfctnStatusTypeCd() {
		return this.ssnVrfctnStatusTypeCd;
	}
	public void setSsnVrfctnStatusTypeCd(String ssnVrfctnStatusTypeCd) {
		this.ssnVrfctnStatusTypeCd = ssnVrfctnStatusTypeCd;
	}

	@Basic()
	@Column(name="SUFFIX_NM", length=15)
	public String getSuffixNm() {
		return this.suffixNm;
	}
	public void setSuffixNm(String suffixNm) {
		this.suffixNm = suffixNm;
	}

	@Basic()
	@Column(name="SUFFIX_NM_KEY", precision=10)
	public Long getSuffixNmKey() {
		return this.suffixNmKey;
	}
	public void setSuffixNmKey(Long suffixNmKey) {
		this.suffixNmKey = suffixNmKey;
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
	@Column(name="TERMNL_DIGIT_NBR", length=2)
	public String getTermnlDigitNbr() {
		return this.termnlDigitNbr;
	}
	public void setTermnlDigitNbr(String termnlDigitNbr) {
		this.termnlDigitNbr = termnlDigitNbr;
	}

	@Basic()
	@Column(name="TITLE_TXT", length=50)
	public String getTitleTxt() {
		return this.titleTxt;
	}
	public void setTitleTxt(String titleTxt) {
		this.titleTxt = titleTxt;
	}

	@Basic()
	@Column(name="VET_IND", length=1)
	public String getVetInd() {
		return this.vetInd;
	}
	public void setVetInd(String vetInd) {
		this.vetInd = vetInd;
	}

	@Basic()
	@Column(name="VET_TYPE_NM", length=50)
	public String getVetTypeNm() {
		return this.vetTypeNm;
	}
	public void setVetTypeNm(String vetTypeNm) {
		this.vetTypeNm = vetTypeNm;
	}

	@Basic()
	@Column(name="YEARS_PRESNT_EMPLYR_NBR", precision=2)
	public Integer getYearsPresntEmplyrNbr() {
		return this.yearsPresntEmplyrNbr;
	}
	public void setYearsPresntEmplyrNbr(Integer yearsPresntEmplyrNbr) {
		this.yearsPresntEmplyrNbr = yearsPresntEmplyrNbr;
	}
//
//	//bi-directional many-to-one association to AlpsValeriIntrfc
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<AlpsValeriIntrfc> getAlpsValeriIntrfcs() {
//		return this.alpsValeriIntrfcs;
//	}
//	public void setAlpsValeriIntrfcs(java.util.Set<AlpsValeriIntrfc> alpsValeriIntrfcs) {
//		this.alpsValeriIntrfcs = alpsValeriIntrfcs;
//	}
//
//	//bi-directional many-to-one association to AwardDiary
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<AwardDiary> getAwardDiaries() {
//		return this.awardDiaries;
//	}
//	public void setAwardDiaries(java.util.Set<AwardDiary> awardDiaries) {
//		this.awardDiaries = awardDiaries;
//	}
//
//	//bi-directional many-to-one association to AwardOvrideWrksht
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<AwardOvrideWrksht> getAwardOvrideWrkshts() {
//		return this.awardOvrideWrkshts;
//	}
//	public void setAwardOvrideWrkshts(java.util.Set<AwardOvrideWrksht> awardOvrideWrkshts) {
//		this.awardOvrideWrkshts = awardOvrideWrkshts;
//	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims() {
		return this.bnftClaims;
	}
	public void setBnftClaims(java.util.Set<BnftClaim> bnftClaims) {
		this.bnftClaims = bnftClaims;
	}
//
//	//bi-directional many-to-one association to Case
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Case> getCases() {
//		return this.cases;
//	}
//	public void setCases(java.util.Set<Case> cases) {
//		this.cases = cases;
//	}
//
//	//bi-directional many-to-one association to CaseAlert
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<CaseAlert> getCaseAlerts() {
//		return this.caseAlerts;
//	}
//	public void setCaseAlerts(java.util.Set<CaseAlert> caseAlerts) {
//		this.caseAlerts = caseAlerts;
//	}
//
//	//bi-directional many-to-one association to CaseAsgnmtStatus
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<CaseAsgnmtStatus> getCaseAsgnmtStatuses() {
//		return this.caseAsgnmtStatuses;
//	}
//	public void setCaseAsgnmtStatuses(java.util.Set<CaseAsgnmtStatus> caseAsgnmtStatuses) {
//		this.caseAsgnmtStatuses = caseAsgnmtStatuses;
//	}
//
//	//bi-directional many-to-one association to CaseAsgnmtStatusLog
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<CaseAsgnmtStatusLog> getCaseAsgnmtStatusLogs() {
//		return this.caseAsgnmtStatusLogs;
//	}
//	public void setCaseAsgnmtStatusLogs(java.util.Set<CaseAsgnmtStatusLog> caseAsgnmtStatusLogs) {
//		this.caseAsgnmtStatusLogs = caseAsgnmtStatusLogs;
//	}
//
//	//bi-directional many-to-one association to CaseLctn
//	@OneToMany(mappedBy="person1", fetch=FetchType.LAZY)
//	public java.util.Set<CaseLctn> getCaseLctns1() {
//		return this.caseLctns1;
//	}
//	public void setCaseLctns1(java.util.Set<CaseLctn> caseLctns1) {
//		this.caseLctns1 = caseLctns1;
//	}
//
//	//bi-directional many-to-one association to CaseLctn
//	@OneToMany(mappedBy="person2", fetch=FetchType.LAZY)
//	public java.util.Set<CaseLctn> getCaseLctns2() {
//		return this.caseLctns2;
//	}
//	public void setCaseLctns2(java.util.Set<CaseLctn> caseLctns2) {
//		this.caseLctns2 = caseLctns2;
//	}
//
//	//bi-directional many-to-one association to CasePgmCostAuthzn
//	@OneToMany(mappedBy="person1", fetch=FetchType.LAZY)
//	public java.util.Set<CasePgmCostAuthzn> getCasePgmCostAuthzns1() {
//		return this.casePgmCostAuthzns1;
//	}
//	public void setCasePgmCostAuthzns1(java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns1) {
//		this.casePgmCostAuthzns1 = casePgmCostAuthzns1;
//	}
//
//	//bi-directional many-to-one association to CasePgmCostAuthzn
//	@OneToMany(mappedBy="person2", fetch=FetchType.LAZY)
//	public java.util.Set<CasePgmCostAuthzn> getCasePgmCostAuthzns2() {
//		return this.casePgmCostAuthzns2;
//	}
//	public void setCasePgmCostAuthzns2(java.util.Set<CasePgmCostAuthzn> casePgmCostAuthzns2) {
//		this.casePgmCostAuthzns2 = casePgmCostAuthzns2;
//	}
//
//	//bi-directional many-to-one association to CaseTravel
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<CaseTravel> getCaseTravels() {
//		return this.caseTravels;
//	}
//	public void setCaseTravels(java.util.Set<CaseTravel> caseTravels) {
//		this.caseTravels = caseTravels;
//	}
//
//	//bi-directional many-to-one association to Ch31GedAdmstnReport
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31GedAdmstnReport> getCh31GedAdmstnReports() {
//		return this.ch31GedAdmstnReports;
//	}
//	public void setCh31GedAdmstnReports(java.util.Set<Ch31GedAdmstnReport> ch31GedAdmstnReports) {
//		this.ch31GedAdmstnReports = ch31GedAdmstnReports;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCaddTran
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCaddTran> getCh31IntrfcCaddTrans() {
//		return this.ch31IntrfcCaddTrans;
//	}
//	public void setCh31IntrfcCaddTrans(java.util.Set<Ch31IntrfcCaddTran> ch31IntrfcCaddTrans) {
//		this.ch31IntrfcCaddTrans = ch31IntrfcCaddTrans;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCastTran
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCastTran> getCh31IntrfcCastTrans() {
//		return this.ch31IntrfcCastTrans;
//	}
//	public void setCh31IntrfcCastTrans(java.util.Set<Ch31IntrfcCastTran> ch31IntrfcCastTrans) {
//		this.ch31IntrfcCastTrans = ch31IntrfcCastTrans;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcCorrTran
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcCorrTran> getCh31IntrfcCorrTrans() {
//		return this.ch31IntrfcCorrTrans;
//	}
//	public void setCh31IntrfcCorrTrans(java.util.Set<Ch31IntrfcCorrTran> ch31IntrfcCorrTrans) {
//		this.ch31IntrfcCorrTrans = ch31IntrfcCorrTrans;
//	}
//
//	//bi-directional many-to-one association to Ch31IntrfcGedTran
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<Ch31IntrfcGedTran> getCh31IntrfcGedTrans() {
//		return this.ch31IntrfcGedTrans;
//	}
//	public void setCh31IntrfcGedTrans(java.util.Set<Ch31IntrfcGedTran> ch31IntrfcGedTrans) {
//		this.ch31IntrfcGedTrans = ch31IntrfcGedTrans;
//	}
//
//	//bi-directional many-to-one association to CntrctPymt
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<CntrctPymt> getCntrctPymts() {
//		return this.cntrctPymts;
//	}
//	public void setCntrctPymts(java.util.Set<CntrctPymt> cntrctPymts) {
//		this.cntrctPymts = cntrctPymts;
//	}
//
//	//bi-directional many-to-one association to DepncyDecn
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<DepncyDecn> getDepncyDecns() {
//		return this.depncyDecns;
//	}
//	public void setDepncyDecns(java.util.Set<DepncyDecn> depncyDecns) {
//		this.depncyDecns = depncyDecns;
//	}
//
//	//bi-directional many-to-one association to EduBnft
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<EduBnft> getEduBnfts() {
//		return this.eduBnfts;
//	}
//	public void setEduBnfts(java.util.Set<EduBnft> eduBnfts) {
//		this.eduBnfts = eduBnfts;
//	}
//
//	//bi-directional many-to-one association to EduDep
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<EduDep> getEduDeps() {
//		return this.eduDeps;
//	}
//	public void setEduDeps(java.util.Set<EduDep> eduDeps) {
//		this.eduDeps = eduDeps;
//	}
//
//	//bi-directional many-to-one association to EduPersonCntryWrkld
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<EduPersonCntryWrkld> getEduPersonCntryWrklds() {
//		return this.eduPersonCntryWrklds;
//	}
//	public void setEduPersonCntryWrklds(java.util.Set<EduPersonCntryWrkld> eduPersonCntryWrklds) {
//		this.eduPersonCntryWrklds = eduPersonCntryWrklds;
//	}
//
//	//bi-directional many-to-one association to EduPersonStateWrkld
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<EduPersonStateWrkld> getEduPersonStateWrklds() {
//		return this.eduPersonStateWrklds;
//	}
//	public void setEduPersonStateWrklds(java.util.Set<EduPersonStateWrkld> eduPersonStateWrklds) {
//		this.eduPersonStateWrklds = eduPersonStateWrklds;
//	}
//
//	//bi-directional many-to-one association to EduPrvdrPymtAmount
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<EduPrvdrPymtAmount> getEduPrvdrPymtAmounts() {
//		return this.eduPrvdrPymtAmounts;
//	}
//	public void setEduPrvdrPymtAmounts(java.util.Set<EduPrvdrPymtAmount> eduPrvdrPymtAmounts) {
//		this.eduPrvdrPymtAmounts = eduPrvdrPymtAmounts;
//	}

	//bi-directional one-to-one association to Emp
	@OneToOne(mappedBy="person", fetch=FetchType.LAZY)
	public Emp getEmp() {
		return this.emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}
//
//	//bi-directional many-to-one association to ErpTranHist
//	@OneToMany(mappedBy="person1", fetch=FetchType.LAZY)
//	public java.util.Set<ErpTranHist> getErpTranHists() {
//		return this.erpTranHists;
//	}
//	public void setErpTranHists(java.util.Set<ErpTranHist> erpTranHists) {
//		this.erpTranHists = erpTranHists;
//	}
//
//	//bi-directional one-to-one association to ErpTranHist
//	@OneToOne(mappedBy="person2", fetch=FetchType.LAZY)
//	public ErpTranHist getErpTranHist() {
//		return this.erpTranHist;
//	}
//	public void setErpTranHist(ErpTranHist erpTranHist) {
//		this.erpTranHist = erpTranHist;
//	}
//
//	//bi-directional one-to-one association to FmsTranHist
//	@OneToOne(mappedBy="person", fetch=FetchType.LAZY)
//	public FmsTranHist getFmsTranHist() {
//		return this.fmsTranHist;
//	}
//	public void setFmsTranHist(FmsTranHist fmsTranHist) {
//		this.fmsTranHist = fmsTranHist;
//	}
//
//	//bi-directional many-to-one association to FocasTran
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<FocasTran> getFocasTrans() {
//		return this.focasTrans;
//	}
//	public void setFocasTrans(java.util.Set<FocasTran> focasTrans) {
//		this.focasTrans = focasTrans;
//	}
//
//	//bi-directional many-to-one association to GroupApntmt
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<GroupApntmt> getGroupApntmts() {
//		return this.groupApntmts;
//	}
//	public void setGroupApntmts(java.util.Set<GroupApntmt> groupApntmts) {
//		this.groupApntmts = groupApntmts;
//	}
//
//	//bi-directional many-to-one association to GroupApntmtPtcpnt
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<GroupApntmtPtcpnt> getGroupApntmtPtcpnts() {
//		return this.groupApntmtPtcpnts;
//	}
//	public void setGroupApntmtPtcpnts(java.util.Set<GroupApntmtPtcpnt> groupApntmtPtcpnts) {
//		this.groupApntmtPtcpnts = groupApntmtPtcpnts;
//	}

	//bi-directional many-to-one association to MltyPerson
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<MltyPerson> getMltyPersons() {
		return this.mltyPersons;
	}
	public void setMltyPersons(java.util.Set<MltyPerson> mltyPersons) {
		this.mltyPersons = mltyPersons;
	}

//	//bi-directional many-to-one association to MtgeLoanCntctSumry
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<MtgeLoanCntctSumry> getMtgeLoanCntctSumries() {
//		return this.mtgeLoanCntctSumries;
//	}
//	public void setMtgeLoanCntctSumries(java.util.Set<MtgeLoanCntctSumry> mtgeLoanCntctSumries) {
//		this.mtgeLoanCntctSumries = mtgeLoanCntctSumries;
//	}
//
//	//bi-directional many-to-one association to NonMergedPtcpnt
//	@OneToMany(mappedBy="person1", fetch=FetchType.LAZY)
//	public java.util.Set<NonMergedPtcpnt> getNonMergedPtcpnts() {
//		return this.nonMergedPtcpnts;
//	}
//	public void setNonMergedPtcpnts(java.util.Set<NonMergedPtcpnt> nonMergedPtcpnts) {
//		this.nonMergedPtcpnts = nonMergedPtcpnts;
//	}
//
//	//bi-directional one-to-one association to NonMergedPtcpnt
//	@OneToOne(mappedBy="person2", fetch=FetchType.LAZY)
//	public NonMergedPtcpnt getNonMergedPtcpnt() {
//		return this.nonMergedPtcpnt;
//	}
//	public void setNonMergedPtcpnt(NonMergedPtcpnt nonMergedPtcpnt) {
//		this.nonMergedPtcpnt = nonMergedPtcpnt;
//	}

	//bi-directional many-to-one association to ParentMartlStatusDecn
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<ParentMartlStatusDecn> getParentMartlStatusDecns() {
		return this.parentMartlStatusDecns;
	}
	public void setParentMartlStatusDecns(java.util.Set<ParentMartlStatusDecn> parentMartlStatusDecns) {
		this.parentMartlStatusDecns = parentMartlStatusDecns;
	}

	//bi-directional many-to-one association to PostalType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BIRTH_STATE_CD", referencedColumnName="POSTAL_CD")
	public PstalType getPostalType() {
		return this.postalType;
	}
	public void setPostalType(PstalType postalType) {
		this.postalType = postalType;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	//bi-directional many-to-one association to PersonApplcnPrfrnc
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonApplcnPrfrnc> getPersonApplcnPrfrncs() {
		return this.personApplcnPrfrncs;
	}
	public void setPersonApplcnPrfrncs(java.util.Set<PersonApplcnPrfrnc> personApplcnPrfrncs) {
		this.personApplcnPrfrncs = personApplcnPrfrncs;
	}

	//bi-directional many-to-one association to PersonDep
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonDep> getPersonDeps() {
		return this.personDeps;
	}
	public void setPersonDeps(java.util.Set<PersonDep> personDeps) {
		this.personDeps = personDeps;
	}

	//bi-directional many-to-one association to PersonEduLevel
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonEduLevel> getPersonEduLevels() {
		return this.personEduLevels;
	}
	public void setPersonEduLevels(java.util.Set<PersonEduLevel> personEduLevels) {
		this.personEduLevels = personEduLevels;
	}

	//bi-directional many-to-one association to PersonEmplyr
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonEmplyr> getPersonEmplyrs() {
		return this.personEmplyrs;
	}
	public void setPersonEmplyrs(java.util.Set<PersonEmplyr> personEmplyrs) {
		this.personEmplyrs = personEmplyrs;
	}

	//bi-directional many-to-one association to PersonIdfctnHist
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonIdfctnHist> getPersonIdfctnHists() {
		return this.personIdfctnHists;
	}
	public void setPersonIdfctnHists(java.util.Set<PersonIdfctnHist> personIdfctnHists) {
		this.personIdfctnHists = personIdfctnHists;
	}

	//bi-directional many-to-one association to PersonNetWorth
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonNetWorth> getPersonNetWorths() {
		return this.personNetWorths;
	}
	public void setPersonNetWorths(java.util.Set<PersonNetWorth> personNetWorths) {
		this.personNetWorths = personNetWorths;
	}

	//bi-directional many-to-one association to PersonRace
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonRace> getPersonRaces() {
		return this.personRaces;
	}
	public void setPersonRaces(java.util.Set<PersonRace> personRaces) {
		this.personRaces = personRaces;
	}

	//bi-directional many-to-one association to PersonScrtyLevel
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonScrtyLevel> getPersonScrtyLevels() {
		return this.personScrtyLevels;
	}
	public void setPersonScrtyLevels(java.util.Set<PersonScrtyLevel> personScrtyLevels) {
		this.personScrtyLevels = personScrtyLevels;
	}

	//bi-directional many-to-one association to PersonScrtyLog
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonScrtyLog> getPersonScrtyLogs() {
		return this.personScrtyLogs;
	}
	public void setPersonScrtyLogs(java.util.Set<PersonScrtyLog> personScrtyLogs) {
		this.personScrtyLogs = personScrtyLogs;
	}

	//bi-directional many-to-one association to PersonSpeclStatus
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PersonSpeclStatus> getPersonSpeclStatuses() {
		return this.personSpeclStatuses;
	}
	public void setPersonSpeclStatuses(java.util.Set<PersonSpeclStatus> personSpeclStatuses) {
		this.personSpeclStatuses = personSpeclStatuses;
	}
//
//	//bi-directional many-to-one association to PriorsLineRecip
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<PriorsLineRecip> getPriorsLineRecips() {
//		return this.priorsLineRecips;
//	}
//	public void setPriorsLineRecips(java.util.Set<PriorsLineRecip> priorsLineRecips) {
//		this.priorsLineRecips = priorsLineRecips;
//	}

	//bi-directional many-to-one association to PtcpntCnvrsnMesage
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntCnvrsnMesage> getPtcpntCnvrsnMesages() {
		return this.ptcpntCnvrsnMesages;
	}
	public void setPtcpntCnvrsnMesages(java.util.Set<PtcpntCnvrsnMesage> ptcpntCnvrsnMesages) {
		this.ptcpntCnvrsnMesages = ptcpntCnvrsnMesages;
	}

	//bi-directional many-to-one association to PtcpntInstzn
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<PtcpntInstzn> getPtcpntInstzns() {
		return this.ptcpntInstzns;
	}
	public void setPtcpntInstzns(java.util.Set<PtcpntInstzn> ptcpntInstzns) {
		this.ptcpntInstzns = ptcpntInstzns;
	}

	//bi-directional many-to-one association to RatingAwardDetail
	@OneToMany(mappedBy="person1", fetch=FetchType.LAZY)
	public java.util.Set<RatingAwardDetail> getRatingAwardDetails1() {
		return this.ratingAwardDetails1;
	}
	public void setRatingAwardDetails1(java.util.Set<RatingAwardDetail> ratingAwardDetails1) {
		this.ratingAwardDetails1 = ratingAwardDetails1;
	}

	//bi-directional many-to-one association to RatingAwardDetail
	@OneToMany(mappedBy="person2", fetch=FetchType.LAZY)
	public java.util.Set<RatingAwardDetail> getRatingAwardDetails2() {
		return this.ratingAwardDetails2;
	}
	public void setRatingAwardDetails2(java.util.Set<RatingAwardDetail> ratingAwardDetails2) {
		this.ratingAwardDetails2 = ratingAwardDetails2;
	}

	//bi-directional many-to-one association to RatingDecn
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<RatingDecn> getRatingDecns() {
		return this.ratingDecns;
	}
	public void setRatingDecns(java.util.Set<RatingDecn> ratingDecns) {
		this.ratingDecns = ratingDecns;
	}

	//bi-directional many-to-one association to RbaPrfil
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfil> getRbaPrfils() {
		return this.rbaPrfils;
	}
	public void setRbaPrfils(java.util.Set<RbaPrfil> rbaPrfils) {
		this.rbaPrfils = rbaPrfils;
	}
//
//	//bi-directional many-to-one association to RepymtPlan
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<RepymtPlan> getRepymtPlans() {
//		return this.repymtPlans;
//	}
//	public void setRepymtPlans(java.util.Set<RepymtPlan> repymtPlans) {
//		this.repymtPlans = repymtPlans;
//	}

	//bi-directional many-to-one association to Reqst
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<Reqst> getReqsts() {
		return this.reqsts;
	}
	public void setReqsts(java.util.Set<Reqst> reqsts) {
		this.reqsts = reqsts;
	}
//
//	//bi-directional many-to-one association to ScrtyVioltn
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<ScrtyVioltn> getScrtyVioltns() {
//		return this.scrtyVioltns;
//	}
//	public void setScrtyVioltns(java.util.Set<ScrtyVioltn> scrtyVioltns) {
//		this.scrtyVioltns = scrtyVioltns;
//	}
//
//	//bi-directional many-to-one association to SntvtyLevel
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<SntvtyLevel> getSntvtyLevels() {
//		return this.sntvtyLevels;
//	}
//	public void setSntvtyLevels(java.util.Set<SntvtyLevel> sntvtyLevels) {
//		this.sntvtyLevels = sntvtyLevels;
//	}

	//bi-directional many-to-one association to TeamPerson
	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
	public java.util.Set<TeamPerson> getTeamPersons() {
		return this.teamPersons;
	}
	public void setTeamPersons(java.util.Set<TeamPerson> teamPersons) {
		this.teamPersons = teamPersons;
	}
//
//	//bi-directional many-to-one association to UnvbleTime
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<UnvbleTime> getUnvbleTimes() {
//		return this.unvbleTimes;
//	}
//	public void setUnvbleTimes(java.util.Set<UnvbleTime> unvbleTimes) {
//		this.unvbleTimes = unvbleTimes;
//	}
//
//	//bi-directional many-to-one association to UserStnAddrsPrfil
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<UserStnAddrsPrfil> getUserStnAddrsPrfils() {
//		return this.userStnAddrsPrfils;
//	}
//	public void setUserStnAddrsPrfils(java.util.Set<UserStnAddrsPrfil> userStnAddrsPrfils) {
//		this.userStnAddrsPrfils = userStnAddrsPrfils;
//	}
//
//	//bi-directional many-to-one association to VaonceEnrlmtCrtfcn
//	@OneToMany(mappedBy="person", fetch=FetchType.LAZY)
//	public java.util.Set<VaonceEnrlmtCrtfcn> getVaonceEnrlmtCrtfcns() {
//		return this.vaonceEnrlmtCrtfcns;
//	}
//	public void setVaonceEnrlmtCrtfcns(java.util.Set<VaonceEnrlmtCrtfcn> vaonceEnrlmtCrtfcns) {
//		this.vaonceEnrlmtCrtfcns = vaonceEnrlmtCrtfcns;
//	}


    
   


}