package gov.va.vba.framework.domain.entities;
import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the OUTGNG_DCMNT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="OUTGNG_DCMNT")
public class OutgngDcmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long outgngDcmntId;
	private Double amt;
	private Date dcmntDt;
	private String descpTxt;
	private String dvlpmtTypeCd;
	private Date estabdDt;
	private Date extnlRfrncDt;
	private Long extnlRfrncNbr;
	private Date fileStatusDt;
	private String fileStatusTypeCd;
	private String incldEnclsrInd;
	private String letterAddtnlHeaderTxt;
	private String letterAddtnlPrgrphTxt;
	private String letterCategyTypeCd;
	private Integer letterCurtsyCopyQty;
	private Integer letterEnclsrNbr;
	private Integer letterPageNbr;
	private Date letterResendDt;
	private String letterResendReasonTypeCd;
	private String letterTypeCd;
	private String nm;
	private String outgngDcmntTypeCd;
	private Date postDt;
	private Date printDt;
	private String ptcpntAddrsTypeCd;
	private String tmplatTypeCd;
	private Date trnmtlDt;
	private String trnmtlMethodTypeCd;
	private String vaCntrlNbr;
//	private java.util.Set<CmprsdPrntdDcmnt> cmprsdPrntdDcmnts;
//	private CmprsdPrntdDcmnt cmprsdPrntdDcmnt;
//	private java.util.Set<DvlpmtAction> dvlpmtActions;
//	private java.util.Set<DvlpmtItem> dvlpmtItems;
//	private java.util.Set<LetterRlnshp> letterRlnshps1;
//	private java.util.Set<LetterRlnshp> letterRlnshps2;
//	private AwardEvent awardEvent;
	private BnftChart bnftChart;
	private BnftClaim bnftClaim;
//	private Case case1;
//	private LetterTmplat letterTmplat;
//	private MtgeLoan mtgeLoan;
//	private MtgeLoanEvent mtgeLoanEvent;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private Stn stn;
	private java.util.Set<OutgngDcmntStdEnclsr> outgngDcmntStdEnclsrs;
//	private java.util.Set<PiesTran> piesTrans;
//	private PrntdDcmnt prntdDcmnt;
//	private java.util.Set<PtcpntOutgngDcmnt> ptcpntOutgngDcmnts;
//	private java.util.Set<RbaPrfil> rbaPrfils1;
//	private java.util.Set<RbaPrfil> rbaPrfils2;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public OutgngDcmnt() {
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
	@Column(name="OUTGNG_DCMNT_ID", unique=true, nullable=false, precision=15)
	public Long getOutgngDcmntId() {
		return this.outgngDcmntId;
	}
	public void setOutgngDcmntId(Long outgngDcmntId) {
		this.outgngDcmntId = outgngDcmntId;
	}

	@Basic()
	@Column(name="AMT", precision=15, scale=2)
	public Double getAmt() {
		return this.amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Basic()
	@Column(name="DCMNT_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getDcmntDt() {
		return this.dcmntDt;
	}
	public void setDcmntDt(Date dcmntDt) {
		this.dcmntDt = dcmntDt;
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
	@Column(name="DVLPMT_TYPE_CD", length=12)
	public String getDvlpmtTypeCd() {
		return this.dvlpmtTypeCd;
	}
	public void setDvlpmtTypeCd(String dvlpmtTypeCd) {
		this.dvlpmtTypeCd = dvlpmtTypeCd;
	}

	@Basic()
	@Column(name="ESTABD_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEstabdDt() {
		return this.estabdDt;
	}
	public void setEstabdDt(Date estabdDt) {
		this.estabdDt = estabdDt;
	}

	@Basic()
	@Column(name="EXTNL_RFRNC_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getExtnlRfrncDt() {
		return this.extnlRfrncDt;
	}
	public void setExtnlRfrncDt(Date extnlRfrncDt) {
		this.extnlRfrncDt = extnlRfrncDt;
	}

	@Basic()
	@Column(name="EXTNL_RFRNC_NBR", precision=10)
	public Long getExtnlRfrncNbr() {
		return this.extnlRfrncNbr;
	}
	public void setExtnlRfrncNbr(Long extnlRfrncNbr) {
		this.extnlRfrncNbr = extnlRfrncNbr;
	}

	@Basic()
	@Column(name="FILE_STATUS_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getFileStatusDt() {
		return this.fileStatusDt;
	}
	public void setFileStatusDt(Date fileStatusDt) {
		this.fileStatusDt = fileStatusDt;
	}

	@Basic()
	@Column(name="FILE_STATUS_TYPE_CD", length=12)
	public String getFileStatusTypeCd() {
		return this.fileStatusTypeCd;
	}
	public void setFileStatusTypeCd(String fileStatusTypeCd) {
		this.fileStatusTypeCd = fileStatusTypeCd;
	}

	@Basic()
	@Column(name="INCLD_ENCLSR_IND", length=1)
	public String getIncldEnclsrInd() {
		return this.incldEnclsrInd;
	}
	public void setIncldEnclsrInd(String incldEnclsrInd) {
		this.incldEnclsrInd = incldEnclsrInd;
	}

	@Basic()
	@Column(name="LETTER_ADDTNL_HEADER_TXT", length=50)
	public String getLetterAddtnlHeaderTxt() {
		return this.letterAddtnlHeaderTxt;
	}
	public void setLetterAddtnlHeaderTxt(String letterAddtnlHeaderTxt) {
		this.letterAddtnlHeaderTxt = letterAddtnlHeaderTxt;
	}

	@Basic()
	@Column(name="LETTER_ADDTNL_PRGRPH_TXT", length=2000)
	public String getLetterAddtnlPrgrphTxt() {
		return this.letterAddtnlPrgrphTxt;
	}
	public void setLetterAddtnlPrgrphTxt(String letterAddtnlPrgrphTxt) {
		this.letterAddtnlPrgrphTxt = letterAddtnlPrgrphTxt;
	}

	@Basic()
	@Column(name="LETTER_CATEGY_TYPE_CD", length=12)
	public String getLetterCategyTypeCd() {
		return this.letterCategyTypeCd;
	}
	public void setLetterCategyTypeCd(String letterCategyTypeCd) {
		this.letterCategyTypeCd = letterCategyTypeCd;
	}

	@Basic()
	@Column(name="LETTER_CURTSY_COPY_QTY", precision=2)
	public Integer getLetterCurtsyCopyQty() {
		return this.letterCurtsyCopyQty;
	}
	public void setLetterCurtsyCopyQty(Integer letterCurtsyCopyQty) {
		this.letterCurtsyCopyQty = letterCurtsyCopyQty;
	}

	@Basic()
	@Column(name="LETTER_ENCLSR_NBR", precision=2)
	public Integer getLetterEnclsrNbr() {
		return this.letterEnclsrNbr;
	}
	public void setLetterEnclsrNbr(Integer letterEnclsrNbr) {
		this.letterEnclsrNbr = letterEnclsrNbr;
	}

	@Basic()
	@Column(name="LETTER_PAGE_NBR", precision=2)
	public Integer getLetterPageNbr() {
		return this.letterPageNbr;
	}
	public void setLetterPageNbr(Integer letterPageNbr) {
		this.letterPageNbr = letterPageNbr;
	}

	@Basic()
	@Column(name="LETTER_RESEND_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLetterResendDt() {
		return this.letterResendDt;
	}
	public void setLetterResendDt(Date letterResendDt) {
		this.letterResendDt = letterResendDt;
	}

	@Basic()
	@Column(name="LETTER_RESEND_REASON_TYPE_CD", length=1)
	public String getLetterResendReasonTypeCd() {
		return this.letterResendReasonTypeCd;
	}
	public void setLetterResendReasonTypeCd(String letterResendReasonTypeCd) {
		this.letterResendReasonTypeCd = letterResendReasonTypeCd;
	}

	@Basic()
	@Column(name="LETTER_TYPE_CD", length=12)
	public String getLetterTypeCd() {
		return this.letterTypeCd;
	}
	public void setLetterTypeCd(String letterTypeCd) {
		this.letterTypeCd = letterTypeCd;
	}

	@Basic()
	@Column(name="NM", length=50)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	@Basic()
	@Column(name="OUTGNG_DCMNT_TYPE_CD", nullable=false, length=12)
	public String getOutgngDcmntTypeCd() {
		return this.outgngDcmntTypeCd;
	}
	public void setOutgngDcmntTypeCd(String outgngDcmntTypeCd) {
		this.outgngDcmntTypeCd = outgngDcmntTypeCd;
	}

	@Basic()
	@Column(name="POST_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getPostDt() {
		return this.postDt;
	}
	public void setPostDt(Date postDt) {
		this.postDt = postDt;
	}

	@Basic()
	@Column(name="PRINT_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getPrintDt() {
		return this.printDt;
	}
	public void setPrintDt(Date printDt) {
		this.printDt = printDt;
	}

	@Basic()
	@Column(name="PTCPNT_ADDRS_TYPE_CD", length=12)
	public String getPtcpntAddrsTypeCd() {
		return this.ptcpntAddrsTypeCd;
	}
	public void setPtcpntAddrsTypeCd(String ptcpntAddrsTypeCd) {
		this.ptcpntAddrsTypeCd = ptcpntAddrsTypeCd;
	}

	@Basic()
	@Column(name="TMPLAT_TYPE_CD", length=12)
	public String getTmplatTypeCd() {
		return this.tmplatTypeCd;
	}
	public void setTmplatTypeCd(String tmplatTypeCd) {
		this.tmplatTypeCd = tmplatTypeCd;
	}

	@Basic()
	@Column(name="TRNMTL_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getTrnmtlDt() {
		return this.trnmtlDt;
	}
	public void setTrnmtlDt(Date trnmtlDt) {
		this.trnmtlDt = trnmtlDt;
	}

	@Basic()
	@Column(name="TRNMTL_METHOD_TYPE_CD", length=12)
	public String getTrnmtlMethodTypeCd() {
		return this.trnmtlMethodTypeCd;
	}
	public void setTrnmtlMethodTypeCd(String trnmtlMethodTypeCd) {
		this.trnmtlMethodTypeCd = trnmtlMethodTypeCd;
	}

	@Basic()
	@Column(name="VA_CNTRL_NBR", length=15)
	public String getVaCntrlNbr() {
		return this.vaCntrlNbr;
	}
	public void setVaCntrlNbr(String vaCntrlNbr) {
		this.vaCntrlNbr = vaCntrlNbr;
	}

	//bi-directional many-to-one association to CmprsdPrntdDcmnt
//	@OneToMany(mappedBy="outgngDcmnt1", fetch=FetchType.LAZY)
//	public java.util.Set<CmprsdPrntdDcmnt> getCmprsdPrntdDcmnts() {
//		return this.cmprsdPrntdDcmnts;
//	}
//	public void setCmprsdPrntdDcmnts(java.util.Set<CmprsdPrntdDcmnt> cmprsdPrntdDcmnts) {
//		this.cmprsdPrntdDcmnts = cmprsdPrntdDcmnts;
//	}
//
//	//bi-directional one-to-one association to CmprsdPrntdDcmnt
//	@OneToOne(mappedBy="outgngDcmnt2", fetch=FetchType.LAZY)
//	public CmprsdPrntdDcmnt getCmprsdPrntdDcmnt() {
//		return this.cmprsdPrntdDcmnt;
//	}
//	public void setCmprsdPrntdDcmnt(CmprsdPrntdDcmnt cmprsdPrntdDcmnt) {
//		this.cmprsdPrntdDcmnt = cmprsdPrntdDcmnt;
//	}
//
//	//bi-directional many-to-one association to DvlpmtAction
//	@OneToMany(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
//	public java.util.Set<DvlpmtAction> getDvlpmtActions() {
//		return this.dvlpmtActions;
//	}
//	public void setDvlpmtActions(java.util.Set<DvlpmtAction> dvlpmtActions) {
//		this.dvlpmtActions = dvlpmtActions;
//	}
//
//	//bi-directional many-to-one association to DvlpmtItem
//	@OneToMany(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
//	public java.util.Set<DvlpmtItem> getDvlpmtItems() {
//		return this.dvlpmtItems;
//	}
//	public void setDvlpmtItems(java.util.Set<DvlpmtItem> dvlpmtItems) {
//		this.dvlpmtItems = dvlpmtItems;
//	}
//
//	//bi-directional many-to-one association to LetterRlnshp
//	@OneToMany(mappedBy="outgngDcmnt1", fetch=FetchType.LAZY)
//	public java.util.Set<LetterRlnshp> getLetterRlnshps1() {
//		return this.letterRlnshps1;
//	}
//	public void setLetterRlnshps1(java.util.Set<LetterRlnshp> letterRlnshps1) {
//		this.letterRlnshps1 = letterRlnshps1;
//	}
//
//	//bi-directional many-to-one association to LetterRlnshp
//	@OneToMany(mappedBy="outgngDcmnt2", fetch=FetchType.LAZY)
//	public java.util.Set<LetterRlnshp> getLetterRlnshps2() {
//		return this.letterRlnshps2;
//	}
//	public void setLetterRlnshps2(java.util.Set<LetterRlnshp> letterRlnshps2) {
//		this.letterRlnshps2 = letterRlnshps2;
//	}
//
//	//bi-directional many-to-one association to AwardEvent
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="AWARD_EVENT_ID", referencedColumnName="AWARD_EVENT_ID")
//	public AwardEvent getAwardEvent() {
//		return this.awardEvent;
//	}
//	public void setAwardEvent(AwardEvent awardEvent) {
//		this.awardEvent = awardEvent;
//	}

	//bi-directional many-to-one association to BnftChart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SVC_TYPE_CD", referencedColumnName="SVC_TYPE_CD"),
		@JoinColumn(name="PGM_TYPE_CD", referencedColumnName="PGM_TYPE_CD"),
		@JoinColumn(name="BNFT_CLAIM_TYPE_CD", referencedColumnName="BNFT_CLAIM_TYPE_CD")
		})
	public BnftChart getBnftChart() {
		return this.bnftChart;
	}
	public void setBnftChart(BnftChart bnftChart) {
		this.bnftChart = bnftChart;
	}

	//bi-directional many-to-one association to BnftClaim
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BNFT_CLAIM_ID", referencedColumnName="BNFT_CLAIM_ID")
	public BnftClaim getBnftClaim() {
		return this.bnftClaim;
	}
	public void setBnftClaim(BnftClaim bnftClaim) {
		this.bnftClaim = bnftClaim;
	}

	//bi-directional many-to-one association to Case
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="CASE_ID", referencedColumnName="CASE_ID")
//	public Case getCase() {
//		return this.case1;
//	}
//	public void setCase(Case case1) {
//		this.case1 = case1;
//	}
//
//	//bi-directional many-to-one association to LetterTmplat
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="LETTER_TMPLAT_ID", referencedColumnName="LETTER_TMPLAT_ID")
//	public LetterTmplat getLetterTmplat() {
//		return this.letterTmplat;
//	}
//	public void setLetterTmplat(LetterTmplat letterTmplat) {
//		this.letterTmplat = letterTmplat;
//	}
//
//	//bi-directional many-to-one association to MtgeLoan
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="MTGE_LOAN_ID", referencedColumnName="MTGE_LOAN_ID")
//	public MtgeLoan getMtgeLoan() {
//		return this.mtgeLoan;
//	}
//	public void setMtgeLoan(MtgeLoan mtgeLoan) {
//		this.mtgeLoan = mtgeLoan;
//	}
//
//	//bi-directional many-to-one association to MtgeLoanEvent
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumns({
//		@JoinColumn(name="MTGE_LOAN_ID", referencedColumnName="MTGE_LOAN_ID"),
//		@JoinColumn(name="MTGE_LOAN_EVENT_NBR", referencedColumnName="MTGE_LOAN_EVENT_NBR")
//		})
//	public MtgeLoanEvent getMtgeLoanEvent() {
//		return this.mtgeLoanEvent;
//	}
//	public void setMtgeLoanEvent(MtgeLoanEvent mtgeLoanEvent) {
//		this.mtgeLoanEvent = mtgeLoanEvent;
//	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID")
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	//bi-directional many-to-one association to OutgngDcmntStdEnclsr
	@OneToMany(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
	public java.util.Set<OutgngDcmntStdEnclsr> getOutgngDcmntStdEnclsrs() {
		return this.outgngDcmntStdEnclsrs;
	}
	public void setOutgngDcmntStdEnclsrs(java.util.Set<OutgngDcmntStdEnclsr> outgngDcmntStdEnclsrs) {
		this.outgngDcmntStdEnclsrs = outgngDcmntStdEnclsrs;
	}

	//bi-directional many-to-one association to PiesTran
//	@OneToMany(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
//	public java.util.Set<PiesTran> getPiesTrans() {
//		return this.piesTrans;
//	}
//	public void setPiesTrans(java.util.Set<PiesTran> piesTrans) {
//		this.piesTrans = piesTrans;
//	}
//
//	//bi-directional one-to-one association to PrntdDcmnt
//	@OneToOne(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
//	public PrntdDcmnt getPrntdDcmnt() {
//		return this.prntdDcmnt;
//	}
//	public void setPrntdDcmnt(PrntdDcmnt prntdDcmnt) {
//		this.prntdDcmnt = prntdDcmnt;
//	}
//
//	//bi-directional many-to-one association to PtcpntOutgngDcmnt
//	@OneToMany(mappedBy="outgngDcmnt", fetch=FetchType.LAZY)
//	public java.util.Set<PtcpntOutgngDcmnt> getPtcpntOutgngDcmnts() {
//		return this.ptcpntOutgngDcmnts;
//	}
//	public void setPtcpntOutgngDcmnts(java.util.Set<PtcpntOutgngDcmnt> ptcpntOutgngDcmnts) {
//		this.ptcpntOutgngDcmnts = ptcpntOutgngDcmnts;
//	}

//	//bi-directional many-to-one association to RbaPrfil
//	@OneToMany(mappedBy="outgngDcmnt1", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfil> getRbaPrfils1() {
//		return this.rbaPrfils1;
//	}
//	public void setRbaPrfils1(java.util.Set<RbaPrfil> rbaPrfils1) {
//		this.rbaPrfils1 = rbaPrfils1;
//	}
//
//	//bi-directional many-to-one association to RbaPrfil
//	@OneToMany(mappedBy="outgngDcmnt2", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfil> getRbaPrfils2() {
//		return this.rbaPrfils2;
//	}
//	public void setRbaPrfils2(java.util.Set<RbaPrfil> rbaPrfils2) {
//		this.rbaPrfils2 = rbaPrfils2;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OutgngDcmnt)) {
			return false;
		}
		OutgngDcmnt castOther = (OutgngDcmnt)other;
		return new EqualsBuilder()
			.append(this.getOutgngDcmntId(), castOther.getOutgngDcmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOutgngDcmntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("outgngDcmntId", getOutgngDcmntId())
			.toString();
	}
}