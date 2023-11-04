package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_ADDRS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_ADDRS")
public class PtcpntAddr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntAddrsId;
	private String addrsOneTxt;
	private String addrsThreeTxt;
	private String addrsTwoTxt;
	private String badAddrsInd;
	private String cityNm;
	private String countyNm;
	private java.sql.Timestamp efctvDt;
	private String eftWaiverTypeNm;
	private String emailAddrsTxt;
	private java.sql.Timestamp endDt;
	private String fmsAddrsCodeTxt;
	private String frgnPostalCd;
	private String lctnNm;
	private String mltyPostalTypeCd;
	private String mltyPostOfficeTypeCd;
	private String prvncNm;
	private String ptcpntAddrsTypeNm;
	private String sharedAddrsInd;
	private String trsuryAddrsFiveTxt;
	private String trsuryAddrsFourTxt;
	private String trsuryAddrsOneTxt;
	private String trsuryAddrsSixTxt;
	private String trsuryAddrsThreeTxt;
	private String trsuryAddrsTwoTxt;
	private Integer trsurySeqNbr;
	private String trtryNm;
	private String zipFirstSuffixNbr;
	private String zipPrefixNbr;
	private String zipSecondSuffixNbr;
//	private java.util.Set<AwardBene> awardBenes1;
//	private java.util.Set<AwardBene> awardBenes2;
	private java.util.Set<BnftClaim> bnftClaims1;
	private java.util.Set<BnftClaim> bnftClaims2;
	private CntryType cntryType;
	private PstalType postalType;
	private Ptcpnt ptcpnt;
//	private java.util.Set<Pymt> pymts;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntAddr() {
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
	@Column(name="PTCPNT_ADDRS_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntAddrsId() {
		return this.ptcpntAddrsId;
	}
	public void setPtcpntAddrsId(Long ptcpntAddrsId) {
		this.ptcpntAddrsId = ptcpntAddrsId;
	}

	@Basic()
	@Column(name="ADDRS_ONE_TXT", length=35)
	public String getAddrsOneTxt() {
		return this.addrsOneTxt;
	}
	public void setAddrsOneTxt(String addrsOneTxt) {
		this.addrsOneTxt = addrsOneTxt;
	}

	@Basic()
	@Column(name="ADDRS_THREE_TXT", length=35)
	public String getAddrsThreeTxt() {
		return this.addrsThreeTxt;
	}
	public void setAddrsThreeTxt(String addrsThreeTxt) {
		this.addrsThreeTxt = addrsThreeTxt;
	}

	@Basic()
	@Column(name="ADDRS_TWO_TXT", length=35)
	public String getAddrsTwoTxt() {
		return this.addrsTwoTxt;
	}
	public void setAddrsTwoTxt(String addrsTwoTxt) {
		this.addrsTwoTxt = addrsTwoTxt;
	}

	@Basic()
	@Column(name="BAD_ADDRS_IND", length=1)
	public String getBadAddrsInd() {
		return this.badAddrsInd;
	}
	public void setBadAddrsInd(String badAddrsInd) {
		this.badAddrsInd = badAddrsInd;
	}

	@Basic()
	@Column(name="CITY_NM", length=30)
	public String getCityNm() {
		return this.cityNm;
	}
	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}

	@Basic()
	@Column(name="COUNTY_NM", length=30)
	public String getCountyNm() {
		return this.countyNm;
	}
	public void setCountyNm(String countyNm) {
		this.countyNm = countyNm;
	}

	@Basic()
	@Column(name="EFCTV_DT", nullable=false, length=7)
	public java.sql.Timestamp getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(java.sql.Timestamp efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Basic()
	@Column(name="EFT_WAIVER_TYPE_NM", length=50)
	public String getEftWaiverTypeNm() {
		return this.eftWaiverTypeNm;
	}
	public void setEftWaiverTypeNm(String eftWaiverTypeNm) {
		this.eftWaiverTypeNm = eftWaiverTypeNm;
	}

	@Basic()
	@Column(name="EMAIL_ADDRS_TXT", length=254)
	public String getEmailAddrsTxt() {
		return this.emailAddrsTxt;
	}
	public void setEmailAddrsTxt(String emailAddrsTxt) {
		this.emailAddrsTxt = emailAddrsTxt;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Timestamp getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Timestamp endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="FMS_ADDRS_CODE_TXT", length=2)
	public String getFmsAddrsCodeTxt() {
		return this.fmsAddrsCodeTxt;
	}
	public void setFmsAddrsCodeTxt(String fmsAddrsCodeTxt) {
		this.fmsAddrsCodeTxt = fmsAddrsCodeTxt;
	}

	@Basic()
	@Column(name="FRGN_POSTAL_CD", length=16)
	public String getFrgnPostalCd() {
		return this.frgnPostalCd;
	}
	public void setFrgnPostalCd(String frgnPostalCd) {
		this.frgnPostalCd = frgnPostalCd;
	}

	@Basic()
	@Column(name="LCTN_NM", length=50)
	public String getLctnNm() {
		return this.lctnNm;
	}
	public void setLctnNm(String lctnNm) {
		this.lctnNm = lctnNm;
	}

	@Basic()
	@Column(name="MLTY_POSTAL_TYPE_CD", length=12)
	public String getMltyPostalTypeCd() {
		return this.mltyPostalTypeCd;
	}
	public void setMltyPostalTypeCd(String mltyPostalTypeCd) {
		this.mltyPostalTypeCd = mltyPostalTypeCd;
	}

	@Basic()
	@Column(name="MLTY_POST_OFFICE_TYPE_CD", length=12)
	public String getMltyPostOfficeTypeCd() {
		return this.mltyPostOfficeTypeCd;
	}
	public void setMltyPostOfficeTypeCd(String mltyPostOfficeTypeCd) {
		this.mltyPostOfficeTypeCd = mltyPostOfficeTypeCd;
	}

	@Basic()
	@Column(name="PRVNC_NM", length=35)
	public String getPrvncNm() {
		return this.prvncNm;
	}
	public void setPrvncNm(String prvncNm) {
		this.prvncNm = prvncNm;
	}

	@Basic()
	@Column(name="PTCPNT_ADDRS_TYPE_NM", nullable=false, length=50)
	public String getPtcpntAddrsTypeNm() {
		return this.ptcpntAddrsTypeNm;
	}
	public void setPtcpntAddrsTypeNm(String ptcpntAddrsTypeNm) {
		this.ptcpntAddrsTypeNm = ptcpntAddrsTypeNm;
	}

	@Basic()
	@Column(name="SHARED_ADDRS_IND", nullable=false, length=1)
	public String getSharedAddrsInd() {
		return this.sharedAddrsInd;
	}
	public void setSharedAddrsInd(String sharedAddrsInd) {
		this.sharedAddrsInd = sharedAddrsInd;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_FIVE_TXT", length=20)
	public String getTrsuryAddrsFiveTxt() {
		return this.trsuryAddrsFiveTxt;
	}
	public void setTrsuryAddrsFiveTxt(String trsuryAddrsFiveTxt) {
		this.trsuryAddrsFiveTxt = trsuryAddrsFiveTxt;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_FOUR_TXT", length=20)
	public String getTrsuryAddrsFourTxt() {
		return this.trsuryAddrsFourTxt;
	}
	public void setTrsuryAddrsFourTxt(String trsuryAddrsFourTxt) {
		this.trsuryAddrsFourTxt = trsuryAddrsFourTxt;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_ONE_TXT", length=20)
	public String getTrsuryAddrsOneTxt() {
		return this.trsuryAddrsOneTxt;
	}
	public void setTrsuryAddrsOneTxt(String trsuryAddrsOneTxt) {
		this.trsuryAddrsOneTxt = trsuryAddrsOneTxt;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_SIX_TXT", length=20)
	public String getTrsuryAddrsSixTxt() {
		return this.trsuryAddrsSixTxt;
	}
	public void setTrsuryAddrsSixTxt(String trsuryAddrsSixTxt) {
		this.trsuryAddrsSixTxt = trsuryAddrsSixTxt;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_THREE_TXT", length=20)
	public String getTrsuryAddrsThreeTxt() {
		return this.trsuryAddrsThreeTxt;
	}
	public void setTrsuryAddrsThreeTxt(String trsuryAddrsThreeTxt) {
		this.trsuryAddrsThreeTxt = trsuryAddrsThreeTxt;
	}

	@Basic()
	@Column(name="TRSURY_ADDRS_TWO_TXT", length=20)
	public String getTrsuryAddrsTwoTxt() {
		return this.trsuryAddrsTwoTxt;
	}
	public void setTrsuryAddrsTwoTxt(String trsuryAddrsTwoTxt) {
		this.trsuryAddrsTwoTxt = trsuryAddrsTwoTxt;
	}

	@Basic()
	@Column(name="TRSURY_SEQ_NBR", precision=4)
	public Integer getTrsurySeqNbr() {
		return this.trsurySeqNbr;
	}
	public void setTrsurySeqNbr(Integer trsurySeqNbr) {
		this.trsurySeqNbr = trsurySeqNbr;
	}

	@Basic()
	@Column(name="TRTRY_NM", length=35)
	public String getTrtryNm() {
		return this.trtryNm;
	}
	public void setTrtryNm(String trtryNm) {
		this.trtryNm = trtryNm;
	}

	@Basic()
	@Column(name="ZIP_FIRST_SUFFIX_NBR", length=4)
	public String getZipFirstSuffixNbr() {
		return this.zipFirstSuffixNbr;
	}
	public void setZipFirstSuffixNbr(String zipFirstSuffixNbr) {
		this.zipFirstSuffixNbr = zipFirstSuffixNbr;
	}

	@Basic()
	@Column(name="ZIP_PREFIX_NBR", length=5)
	public String getZipPrefixNbr() {
		return this.zipPrefixNbr;
	}
	public void setZipPrefixNbr(String zipPrefixNbr) {
		this.zipPrefixNbr = zipPrefixNbr;
	}

	@Basic()
	@Column(name="ZIP_SECOND_SUFFIX_NBR", length=2)
	public String getZipSecondSuffixNbr() {
		return this.zipSecondSuffixNbr;
	}
	public void setZipSecondSuffixNbr(String zipSecondSuffixNbr) {
		this.zipSecondSuffixNbr = zipSecondSuffixNbr;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpntAddr1", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims1() {
		return this.bnftClaims1;
	}
	public void setBnftClaims1(java.util.Set<BnftClaim> bnftClaims1) {
		this.bnftClaims1 = bnftClaims1;
	}

	//bi-directional many-to-one association to BnftClaim
	@OneToMany(mappedBy="ptcpntAddr2", fetch=FetchType.LAZY)
	public java.util.Set<BnftClaim> getBnftClaims2() {
		return this.bnftClaims2;
	}
	public void setBnftClaims2(java.util.Set<BnftClaim> bnftClaims2) {
		this.bnftClaims2 = bnftClaims2;
	}

	//bi-directional many-to-one association to CntryType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CNTRY_TYPE_NM", referencedColumnName="CNTRY_TYPE_NM")
	public CntryType getCntryType() {
		return this.cntryType;
	}
	public void setCntryType(CntryType cntryType) {
		this.cntryType = cntryType;
	}

	//bi-directional many-to-one association to PostalType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="POSTAL_CD", referencedColumnName="POSTAL_CD")
	public PstalType getPostalType() {
		return this.postalType;
	}
	public void setPostalType(PstalType postalType) {
		this.postalType = postalType;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

//	//bi-directional many-to-one association to Pymt
//	@OneToMany(mappedBy="ptcpntAddr", fetch=FetchType.LAZY)
//	public java.util.Set<Pymt> getPymts() {
//		return this.pymts;
//	}
//	public void setPymts(java.util.Set<Pymt> pymts) {
//		this.pymts = pymts;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntAddr)) {
			return false;
		}
		PtcpntAddr castOther = (PtcpntAddr)other;
		return new EqualsBuilder()
			.append(this.getPtcpntAddrsId(), castOther.getPtcpntAddrsId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntAddrsId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntAddrsId", getPtcpntAddrsId())
			.toString();
	}
}