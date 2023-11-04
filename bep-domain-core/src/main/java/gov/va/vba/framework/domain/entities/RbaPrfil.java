package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the RBA_PRFIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="RBA_PRFIL")
public class RbaPrfil  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private RbaPrfilPK compId;
	private Boolean addtnlDgnstcCodesQty;
	private Date clientRatingDt;
	private Date cnvrsnDt;
	private String combatDsbltyCd;
	private Date combndPrcntScopeDt;
	private String dcmntTitleTxt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private String jrsdtnTxt;
	private String lockedInd;
	private Date pndngPrmlgnDt;
	private Date prmlgnDt;
	private Date ratingDt;
	private String ratingIntroTxt;
	private String rbaUserId;
	private Date rcvdDt;
	private String secondSigntrTxt;
	private String systemTypeCd;
	private java.util.Set<DesRatingDetail> desRatingDetails;
	private java.util.Set<Dsblty> dsblties;
	private java.util.Set<RatingAwardSumry> ratingAwardSumries;
	private java.util.Set<RatingDecn> ratingDecns;
	private java.util.Set<RbaCopyLctn> rbaCopyLctns;
	private java.util.Set<RbaEvdnce> rbaEvdnces;
	private BnftClaim bnftClaim;
	private OutgngDcmnt outgngDcmnt1;
	private OutgngDcmnt outgngDcmnt2;
	private Person person;
	private Ptcpnt ptcpnt;
	private java.util.Set<RbaPrfilBnftClaim> rbaPrfilBnftClaims;
	private java.util.Set<RbaPrfilIssue> rbaPrfilIssues;
	private java.util.Set<RbaPrfilSplmtlHeadng> rbaPrfilSplmtlHeadngs;
	private java.util.Set<RbaRatingDetail> rbaRatingDetails;

    public RbaPrfil() {
    }

	@EmbeddedId
	public RbaPrfilPK getCompId() {
		return this.compId;
	}
	public void setCompId(RbaPrfilPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ADDTNL_DGNSTC_CODES_QTY", precision=1)
	public Boolean getAddtnlDgnstcCodesQty() {
		return this.addtnlDgnstcCodesQty;
	}
	public void setAddtnlDgnstcCodesQty(Boolean addtnlDgnstcCodesQty) {
		this.addtnlDgnstcCodesQty = addtnlDgnstcCodesQty;
	}

	@Basic()
	@Column(name="CLIENT_RATING_DT", length=7)
	public Date getClientRatingDt() {
		return this.clientRatingDt;
	}
	public void setClientRatingDt(Date clientRatingDt) {
		this.clientRatingDt = clientRatingDt;
	}

	@Basic()
	@Column(name="CNVRSN_DT", length=7)
	public Date getCnvrsnDt() {
		return this.cnvrsnDt;
	}
	public void setCnvrsnDt(Date cnvrsnDt) {
		this.cnvrsnDt = cnvrsnDt;
	}

	@Basic()
	@Column(name="COMBAT_DSBLTY_CD", length=1)
	public String getCombatDsbltyCd() {
		return this.combatDsbltyCd;
	}
	public void setCombatDsbltyCd(String combatDsbltyCd) {
		this.combatDsbltyCd = combatDsbltyCd;
	}

	@Basic()
	@Column(name="COMBND_PRCNT_SCOPE_DT", length=7)
	public Date getCombndPrcntScopeDt() {
		return this.combndPrcntScopeDt;
	}
	public void setCombndPrcntScopeDt(Date combndPrcntScopeDt) {
		this.combndPrcntScopeDt = combndPrcntScopeDt;
	}

	@Basic()
	@Column(name="DCMNT_TITLE_TXT", length=254)
	public String getDcmntTitleTxt() {
		return this.dcmntTitleTxt;
	}
	public void setDcmntTitleTxt(String dcmntTitleTxt) {
		this.dcmntTitleTxt = dcmntTitleTxt;
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

	@Basic()
	@Column(name="JRSDTN_TXT", length=50)
	public String getJrsdtnTxt() {
		return this.jrsdtnTxt;
	}
	public void setJrsdtnTxt(String jrsdtnTxt) {
		this.jrsdtnTxt = jrsdtnTxt;
	}

	@Basic()
	@Column(name="LOCKED_IND", nullable=false, length=1)
	public String getLockedInd() {
		return this.lockedInd;
	}
	public void setLockedInd(String lockedInd) {
		this.lockedInd = lockedInd;
	}

	@Basic()
	@Column(name="PNDNG_PRMLGN_DT", length=7)
	public Date getPndngPrmlgnDt() {
		return this.pndngPrmlgnDt;
	}
	public void setPndngPrmlgnDt(Date pndngPrmlgnDt) {
		this.pndngPrmlgnDt = pndngPrmlgnDt;
	}

	@Basic()
	@Column(name="PRMLGN_DT", length=7)
	public Date getPrmlgnDt() {
		return this.prmlgnDt;
	}
	public void setPrmlgnDt(Date prmlgnDt) {
		this.prmlgnDt = prmlgnDt;
	}

	@Basic()
	@Column(name="RATING_DT", length=7)
	public Date getRatingDt() {
		return this.ratingDt;
	}
	public void setRatingDt(Date ratingDt) {
		this.ratingDt = ratingDt;
	}

	@Basic()
	@Column(name="RATING_INTRO_TXT")
	public String getRatingIntroTxt() {
		return this.ratingIntroTxt;
	}
	public void setRatingIntroTxt(String ratingIntroTxt) {
		this.ratingIntroTxt = ratingIntroTxt;
	}

	@Basic()
	@Column(name="RBA_USER_ID", length=15)
	public String getRbaUserId() {
		return this.rbaUserId;
	}
	public void setRbaUserId(String rbaUserId) {
		this.rbaUserId = rbaUserId;
	}

	@Basic()
	@Column(name="RCVD_DT", length=7)
	public Date getRcvdDt() {
		return this.rcvdDt;
	}
	public void setRcvdDt(Date rcvdDt) {
		this.rcvdDt = rcvdDt;
	}

	@Basic()
	@Column(name="SECOND_SIGNTR_TXT", length=50)
	public String getSecondSigntrTxt() {
		return this.secondSigntrTxt;
	}
	public void setSecondSigntrTxt(String secondSigntrTxt) {
		this.secondSigntrTxt = secondSigntrTxt;
	}

	@Basic()
	@Column(name="SYSTEM_TYPE_CD", length=12)
	public String getSystemTypeCd() {
		return this.systemTypeCd;
	}
	public void setSystemTypeCd(String systemTypeCd) {
		this.systemTypeCd = systemTypeCd;
	}

	//bi-directional many-to-one association to DesRatingDetail
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<DesRatingDetail> getDesRatingDetails() {
		return this.desRatingDetails;
	}
	public void setDesRatingDetails(java.util.Set<DesRatingDetail> desRatingDetails) {
		this.desRatingDetails = desRatingDetails;
	}

	//bi-directional many-to-one association to Dsblty
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<Dsblty> getDsblties() {
		return this.dsblties;
	}
	public void setDsblties(java.util.Set<Dsblty> dsblties) {
		this.dsblties = dsblties;
	}

	//bi-directional many-to-one association to RatingAwardSumry
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RatingAwardSumry> getRatingAwardSumries() {
		return this.ratingAwardSumries;
	}
	public void setRatingAwardSumries(java.util.Set<RatingAwardSumry> ratingAwardSumries) {
		this.ratingAwardSumries = ratingAwardSumries;
	}

	//bi-directional many-to-one association to RatingDecn
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RatingDecn> getRatingDecns() {
		return this.ratingDecns;
	}
	public void setRatingDecns(java.util.Set<RatingDecn> ratingDecns) {
		this.ratingDecns = ratingDecns;
	}

	//bi-directional many-to-one association to RbaCopyLctn
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaCopyLctn> getRbaCopyLctns() {
		return this.rbaCopyLctns;
	}
	public void setRbaCopyLctns(java.util.Set<RbaCopyLctn> rbaCopyLctns) {
		this.rbaCopyLctns = rbaCopyLctns;
	}

	//bi-directional many-to-one association to RbaEvdnce
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaEvdnce> getRbaEvdnces() {
		return this.rbaEvdnces;
	}
	public void setRbaEvdnces(java.util.Set<RbaEvdnce> rbaEvdnces) {
		this.rbaEvdnces = rbaEvdnces;
	}

	//bi-directional many-to-one association to BnftClaim
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRMLNG_CLAIM_ID", referencedColumnName="BNFT_CLAIM_ID")
	public BnftClaim getBnftClaim() {
		return this.bnftClaim;
	}
	public void setBnftClaim(BnftClaim bnftClaim) {
		this.bnftClaim = bnftClaim;
	}

	//bi-directional many-to-one association to OutgngDcmnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NARRTV_DCMNT_ID", referencedColumnName="OUTGNG_DCMNT_ID")
	public OutgngDcmnt getOutgngDcmnt1() {
		return this.outgngDcmnt1;
	}
	public void setOutgngDcmnt1(OutgngDcmnt outgngDcmnt1) {
		this.outgngDcmnt1 = outgngDcmnt1;
	}

	//bi-directional many-to-one association to OutgngDcmnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CDSHT_DCMNT_ID", referencedColumnName="OUTGNG_DCMNT_ID")
	public OutgngDcmnt getOutgngDcmnt2() {
		return this.outgngDcmnt2;
	}
	public void setOutgngDcmnt2(OutgngDcmnt outgngDcmnt2) {
		this.outgngDcmnt2 = outgngDcmnt2;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_POA_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	//bi-directional many-to-one association to RbaPrfilBnftClaim
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfilBnftClaim> getRbaPrfilBnftClaims() {
		return this.rbaPrfilBnftClaims;
	}
	public void setRbaPrfilBnftClaims(java.util.Set<RbaPrfilBnftClaim> rbaPrfilBnftClaims) {
		this.rbaPrfilBnftClaims = rbaPrfilBnftClaims;
	}

	//bi-directional many-to-one association to RbaPrfilIssue
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfilIssue> getRbaPrfilIssues() {
		return this.rbaPrfilIssues;
	}
	public void setRbaPrfilIssues(java.util.Set<RbaPrfilIssue> rbaPrfilIssues) {
		this.rbaPrfilIssues = rbaPrfilIssues;
	}

	//bi-directional many-to-one association to RbaPrfilSplmtlHeadng
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaPrfilSplmtlHeadng> getRbaPrfilSplmtlHeadngs() {
		return this.rbaPrfilSplmtlHeadngs;
	}
	public void setRbaPrfilSplmtlHeadngs(java.util.Set<RbaPrfilSplmtlHeadng> rbaPrfilSplmtlHeadngs) {
		this.rbaPrfilSplmtlHeadngs = rbaPrfilSplmtlHeadngs;
	}

	//bi-directional many-to-one association to RbaRatingDetail
	@OneToMany(mappedBy="rbaPrfil", fetch=FetchType.LAZY)
	public java.util.Set<RbaRatingDetail> getRbaRatingDetails() {
		return this.rbaRatingDetails;
	}
	public void setRbaRatingDetails(java.util.Set<RbaRatingDetail> rbaRatingDetails) {
		this.rbaRatingDetails = rbaRatingDetails;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RbaPrfil)) {
			return false;
		}
		RbaPrfil castOther = (RbaPrfil)other;
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