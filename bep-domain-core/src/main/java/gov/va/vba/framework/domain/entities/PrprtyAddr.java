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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PRPRTY_ADDRS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PRPRTY_ADDRS")
public class PrprtyAddr  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long lctnId;
	private String addrsOneTxt;
	private String addrsThreeTxt;
	private String addrsTwoTxt;
	private String cityNm;
	private String countyNm;
	private String drctnTxt;
	private java.sql.Timestamp efctvDt;
	private java.sql.Timestamp endDt;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private String mapCodeTxt;
	private Long mapGridNbr;
	private Integer stnAreaNbr;
	private Integer stnExtnsnNbr;
	private Long stnPhoneNbr;
	private java.sql.Timestamp updateDt;
	private String zipFirstSuffixNbr;
	private String zipPrefixNbr;
	private String zipSecondSuffixNbr;
	private CntryType cntryType;
	private Lctn lctn;
	private PstalType postalType;

    public PrprtyAddr() {
    }

	@Id()
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LCTN_ID", unique=true, nullable=false, precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Basic()
	@Column(name="ADDRS_ONE_TXT", length=50)
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
	@Column(name="DRCTN_TXT", length=254)
	public String getDrctnTxt() {
		return this.drctnTxt;
	}
	public void setDrctnTxt(String drctnTxt) {
		this.drctnTxt = drctnTxt;
	}

	@Basic()
	@Column(name="EFCTV_DT", length=7)
	public java.sql.Timestamp getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(java.sql.Timestamp efctvDt) {
		this.efctvDt = efctvDt;
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

	@Basic()
	@Column(name="MAP_CODE_TXT", length=10)
	public String getMapCodeTxt() {
		return this.mapCodeTxt;
	}
	public void setMapCodeTxt(String mapCodeTxt) {
		this.mapCodeTxt = mapCodeTxt;
	}

	@Basic()
	@Column(name="MAP_GRID_NBR", precision=10)
	public Long getMapGridNbr() {
		return this.mapGridNbr;
	}
	public void setMapGridNbr(Long mapGridNbr) {
		this.mapGridNbr = mapGridNbr;
	}

	@Basic()
	@Column(name="STN_AREA_NBR", precision=3)
	public Integer getStnAreaNbr() {
		return this.stnAreaNbr;
	}
	public void setStnAreaNbr(Integer stnAreaNbr) {
		this.stnAreaNbr = stnAreaNbr;
	}

	@Basic()
	@Column(name="STN_EXTNSN_NBR", precision=6)
	public Integer getStnExtnsnNbr() {
		return this.stnExtnsnNbr;
	}
	public void setStnExtnsnNbr(Integer stnExtnsnNbr) {
		this.stnExtnsnNbr = stnExtnsnNbr;
	}

	@Basic()
	@Column(name="STN_PHONE_NBR", precision=12)
	public Long getStnPhoneNbr() {
		return this.stnPhoneNbr;
	}
	public void setStnPhoneNbr(Long stnPhoneNbr) {
		this.stnPhoneNbr = stnPhoneNbr;
	}

	@Basic()
	@Column(name="UPDATE_DT", length=7)
	public java.sql.Timestamp getUpdateDt() {
		return this.updateDt;
	}
	public void setUpdateDt(java.sql.Timestamp updateDt) {
		this.updateDt = updateDt;
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

	//bi-directional many-to-one association to CntryType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CNTRY_TYPE_NM", referencedColumnName="CNTRY_TYPE_NM")
	public CntryType getCntryType() {
		return this.cntryType;
	}
	public void setCntryType(CntryType cntryType) {
		this.cntryType = cntryType;
	}

	//bi-directional one-to-one association to Lctn
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
	public Lctn getLctn() {
		return this.lctn;
	}
	public void setLctn(Lctn lctn) {
		this.lctn = lctn;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrprtyAddr)) {
			return false;
		}
		PrprtyAddr castOther = (PrprtyAddr)other;
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