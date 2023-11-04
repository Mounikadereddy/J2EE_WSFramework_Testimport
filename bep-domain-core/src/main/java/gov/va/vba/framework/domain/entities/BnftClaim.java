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
import javax.persistence.Transient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the BNFT_CLAIM database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="BNFT_CLAIM")
public class BnftClaim implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long bnftClaimId;
	private Date claimRcvdDt;
	private Date claimSuspnsDt;
	private String infrmlInd;
	private Date lastPaidDt;
	private String notfcnPrfilTypeCd;
	private String payeeTypeCd;
	private String rmksTxt;
	private String statusTypeCd;
	private String termnlDigitNbr;
	private BddIntakeSite bddIntakeSite;
	private BnftChart bnftChart;
	private Person person;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private Ptcpnt ptcpnt3;
	private Ptcpnt ptcpnt4;
	private PtcpntAddr ptcpntAddr1;
	private PtcpntAddr ptcpntAddr2;
	private PtcpntDpositAcnt ptcpntDpositAcnt;
	private Stn stn1;
	private Stn stn2;
	private WkstdyCntrct wkstdyCntrct;
//	private java.util.Set<BnftClaimAwardEvent> bnftClaimAwardEvents;
//	private java.util.Set<BnftClaimLcStatus> bnftClaimLcStatuses;
//	private java.util.Set<BnftClaimNote> bnftClaimNotes;
//	private java.util.Set<BnftClaimPtcpnt> bnftClaimPtcpnts;
//	private java.util.Set<BnftClaimRecipEvent> bnftClaimRecipEvents;
//	private java.util.Set<Case> cases;
//	private java.util.Set<Chklst> chklsts;
//	private ClaimDvlpmt claimDvlpmt;
//	private java.util.Set<ClaimSuspnsLcStatus> claimSuspnsLcStatuses;
//	private java.util.Set<Cntntn> cntntns;
//	private java.util.Set<CpClaim> cpClaims;
//	private java.util.Set<DvlpmtEvdnceQstn> dvlpmtEvdnceQstns;
//	private java.util.Set<DvlpmtScreen> dvlpmtScreens;
//	private java.util.Set<FmsTranHist> fmsTranHists;
//	private java.util.Set<IncmngDcmnt> incmngDcmnts;
//	private java.util.Set<Notfcn> notfcns;
//	private java.util.Set<NotfcnPrfil> notfcnPrfils;
	private java.util.Set<OutgngDcmnt> outgngDcmnts;
//	private java.util.Set<PtcpntNote> ptcpntNotes;
//	private java.util.Set<PtcpntPoaRlnshp> ptcpntPoaRlnshps;
	private java.util.Set<RbaPrfil> rbaPrfils;
//	private java.util.Set<RbaPrfilBnftClaim> rbaPrfilBnftClaims;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
	public BnftClaim() {
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
	@Column(name="BNFT_CLAIM_ID", unique=true, nullable=false, precision=15)
	public Long getBnftClaimId() {
		return this.bnftClaimId;
	}
	public void setBnftClaimId(Long bnftClaimId) {
		this.bnftClaimId = bnftClaimId;
	}

	@Basic()
	@Column(name="CLAIM_RCVD_DT", nullable=false, length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getClaimRcvdDt() {
		return this.claimRcvdDt;
	}
	public void setClaimRcvdDt(Date claimRcvdDt) {
		this.claimRcvdDt = claimRcvdDt;
	}

	@Basic()
	@Column(name="CLAIM_SUSPNS_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getClaimSuspnsDt() {
		return this.claimSuspnsDt;
	}
	public void setClaimSuspnsDt(Date claimSuspnsDt) {
		this.claimSuspnsDt = claimSuspnsDt;
	}

	@Basic()
	@Column(name="INFRML_IND", length=1)
	public String getInfrmlInd() {
		return this.infrmlInd;
	}
	public void setInfrmlInd(String infrmlInd) {
		this.infrmlInd = infrmlInd;
	}

	@Basic()
	@Column(name="LAST_PAID_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getLastPaidDt() {
		return this.lastPaidDt;
	}
	public void setLastPaidDt(Date lastPaidDt) {
		this.lastPaidDt = lastPaidDt;
	}

	@Basic()
	@Column(name="NOTFCN_PRFIL_TYPE_CD", length=12)
	public String getNotfcnPrfilTypeCd() {
		return this.notfcnPrfilTypeCd;
	}
	public void setNotfcnPrfilTypeCd(String notfcnPrfilTypeCd) {
		this.notfcnPrfilTypeCd = notfcnPrfilTypeCd;
	}

	@Basic()
	@Column(name="PAYEE_TYPE_CD", length=12)
	public String getPayeeTypeCd() {
		return this.payeeTypeCd;
	}
	public void setPayeeTypeCd(String payeeTypeCd) {
		this.payeeTypeCd = payeeTypeCd;
	}

	@Basic()
	@Column(name="RMKS_TXT", length=254)
	public String getRmksTxt() {
		return this.rmksTxt;
	}
	public void setRmksTxt(String rmksTxt) {
		this.rmksTxt = rmksTxt;
	}

	@Basic()
	@Column(name="STATUS_TYPE_CD", nullable=false, length=12)
	public String getStatusTypeCd() {
		return this.statusTypeCd;
	}
	public void setStatusTypeCd(String statusTypeCd) {
		this.statusTypeCd = statusTypeCd;
	}

	@Basic()
	@Column(name="TERMNL_DIGIT_NBR", length=2)
	public String getTermnlDigitNbr() {
		return this.termnlDigitNbr;
	}
	public void setTermnlDigitNbr(String termnlDigitNbr) {
		this.termnlDigitNbr = termnlDigitNbr;
	}

	//bi-directional many-to-one association to BddIntakeSite
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INTAKE_JRSDTN_LCTN_ID", referencedColumnName="LCTN_ID")
	public BddIntakeSite getBddIntakeSite() {
		return this.bddIntakeSite;
	}
	public void setBddIntakeSite(BddIntakeSite bddIntakeSite) {
		this.bddIntakeSite = bddIntakeSite;
	}

	//bi-directional many-to-one association to BnftChart
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SVC_TYPE_CD", referencedColumnName="SVC_TYPE_CD", nullable=false),
		@JoinColumn(name="PGM_TYPE_CD", referencedColumnName="PGM_TYPE_CD", nullable=false),
		@JoinColumn(name="BNFT_CLAIM_TYPE_CD", referencedColumnName="BNFT_CLAIM_TYPE_CD", nullable=false)
		})
	public BnftChart getBnftChart() {
		return this.bnftChart;
	}
	public void setBnftChart(BnftChart bnftChart) {
		this.bnftChart = bnftChart;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_ID")
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEMP_PTCPNT_VSR_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_CLMANT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_RVSR_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt3() {
		return this.ptcpnt3;
	}
	public void setPtcpnt3(Ptcpnt ptcpnt3) {
		this.ptcpnt3 = ptcpnt3;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_VSR_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt4() {
		return this.ptcpnt4;
	}
	public void setPtcpnt4(Ptcpnt ptcpnt4) {
		this.ptcpnt4 = ptcpnt4;
	}

	//bi-directional many-to-one association to PtcpntAddr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_MAIL_ADDRS_ID", referencedColumnName="PTCPNT_ADDRS_ID")
	public PtcpntAddr getPtcpntAddr1() {
		return this.ptcpntAddr1;
	}
	public void setPtcpntAddr1(PtcpntAddr ptcpntAddr1) {
		this.ptcpntAddr1 = ptcpntAddr1;
	}

	//bi-directional many-to-one association to PtcpntAddr
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_PYMT_ADDRS_ID", referencedColumnName="PTCPNT_ADDRS_ID")
	public PtcpntAddr getPtcpntAddr2() {
		return this.ptcpntAddr2;
	}
	public void setPtcpntAddr2(PtcpntAddr ptcpntAddr2) {
		this.ptcpntAddr2 = ptcpntAddr2;
	}

	//bi-directional many-to-one association to PtcpntDpositAcnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_DPOSIT_ACNT_ID", referencedColumnName="PTCPNT_DPOSIT_ACNT_ID")
	public PtcpntDpositAcnt getPtcpntDpositAcnt() {
		return this.ptcpntDpositAcnt;
	}
	public void setPtcpntDpositAcnt(PtcpntDpositAcnt ptcpntDpositAcnt) {
		this.ptcpntDpositAcnt = ptcpntDpositAcnt;
	}
	
	//bi-directional many-to-one association to RbaPrfil
	@OneToMany(mappedBy="bnftClaim", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfil> getRbaPrfils() {
		return this.rbaPrfils;
	}
	public void setRbaPrfils(java.util.Set<RbaPrfil> rbaPrfils) {
		this.rbaPrfils = rbaPrfils;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TEMP_JRSDTN_LCTN_ID", referencedColumnName="LCTN_ID")
	public Stn getStn1() {
		return this.stn1;
	}
	public void setStn1(Stn stn1) {
		this.stn1 = stn1;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CLAIM_JRSDTN_LCTN_ID", referencedColumnName="LCTN_ID")
	public Stn getStn2() {
		return this.stn2;
	}
	public void setStn2(Stn stn2) {
		this.stn2 = stn2;
	}
	
//	@OneToOne(mappedBy="bnftClaim", fetch=FetchType.LAZY)
	public WkstdyCntrct getWkstdyCntrct() {
		return wkstdyCntrct;
	}

	public void setWkstdyCntrct(WkstdyCntrct wkstdyCntrct) {
		this.wkstdyCntrct = wkstdyCntrct;
	}
	
	//bi-directional many-to-one association to OutgngDcmnt
	@OneToMany(mappedBy="bnftClaim", fetch=FetchType.LAZY)
	public java.util.Set<OutgngDcmnt> getOutgngDcmnts() {
		return this.outgngDcmnts;
	}
	public void setOutgngDcmnts(java.util.Set<OutgngDcmnt> outgngDcmnts) {
		this.outgngDcmnts = outgngDcmnts;
	}
	
	@Transient
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BnftClaim)) {
			return false;
		}
		BnftClaim castOther = (BnftClaim)other;
		return new EqualsBuilder()
			.append(this.getBnftClaimId(), castOther.getBnftClaimId())
			.isEquals();
    }
    
	@Transient
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getBnftClaimId())
			.toHashCode();
    }   

	@Transient
	public String toString() {
		return new ToStringBuilder(this)
			.append("bnftClaimId", getBnftClaimId())
			.toString();
	}
	
//	/**
//	 * Checks to see if this application is active.
//	 * 
//	 * @return
//	 */
//	@Transient
//	public Boolean getActive() {
//		if("SUSP".equals(this.getStatusTypeCd()) || !"WKSTDY".equals(this.getBnftChart().getCompId().getBnftClaimTypeCd())) {
//			return false;
//		}
//		if ("APRVD".equals(this.getStatusTypeCd()) && this.getWkstdyCntrct() == null) {
//			return true;
//		}
//		if(this.getWkstdyCntrct() != null
//			&& this.getWkstdyCntrct().getDateClosed() == null) {
//			return true;
//		}
//		return false;
//	}
	public void setActive(Boolean b){
//		Stub method to remove compiler warning
	}
}