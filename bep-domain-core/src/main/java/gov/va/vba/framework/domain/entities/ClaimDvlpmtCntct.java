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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the CLAIM_DVLPMT_CNTCT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="CLAIM_DVLPMT_CNTCT")
public class ClaimDvlpmtCntct implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntId;
	private String dvlpmtCntctSubTypeCd;
	private String firstNm;
	private String lastNm;
	private String middleNm;
	private String orgNm;
	private String slttnTypeNm;
	private String suffixNm;
	private String taxIdfctnNbr;
	private Ptcpnt ptcpnt;
	private java.util.Set<Trtmnt> trtmnts;
    private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ClaimDvlpmtCntct() {
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PTCPNT_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	@Basic()
	@Column(name="DVLPMT_CNTCT_SUB_TYPE_CD", length=12)
	public String getDvlpmtCntctSubTypeCd() {
		return this.dvlpmtCntctSubTypeCd;
	}
	public void setDvlpmtCntctSubTypeCd(String dvlpmtCntctSubTypeCd) {
		this.dvlpmtCntctSubTypeCd = dvlpmtCntctSubTypeCd;
	}

	@Basic()
	@Column(name="FIRST_NM", length=30)
	public String getFirstNm() {
		return this.firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	@Basic()
	@Column(name="LAST_NM", length=30)
	public String getLastNm() {
		return this.lastNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
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
	@Column(name="ORG_NM", length=50)
	public String getOrgNm() {
		return this.orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}

	@Basic()
	@Column(name="SLTTN_TYPE_NM", length=50)
	public String getSlttnTypeNm() {
		return this.slttnTypeNm;
	}
	public void setSlttnTypeNm(String slttnTypeNm) {
		this.slttnTypeNm = slttnTypeNm;
	}

	@Basic()
	@Column(name="SUFFIX_NM", length=3)
	public String getSuffixNm() {
		return this.suffixNm;
	}
	public void setSuffixNm(String suffixNm) {
		this.suffixNm = suffixNm;
	}

	@Basic()
	@Column(name="TAX_IDFCTN_NBR", length=9)
	public String getTaxIdfctnNbr() {
		return this.taxIdfctnNbr;
	}
	public void setTaxIdfctnNbr(String taxIdfctnNbr) {
		this.taxIdfctnNbr = taxIdfctnNbr;
	}

	//bi-directional one-to-one association to Ptcpnt
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	//bi-directional many-to-one association to Trtmnt
	@OneToMany(mappedBy="claimDvlpmtCntct", fetch=FetchType.LAZY)
	public java.util.Set<Trtmnt> getTrtmnts() {
		return this.trtmnts;
	}
	public void setTrtmnts(java.util.Set<Trtmnt> trtmnts) {
		this.trtmnts = trtmnts;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ClaimDvlpmtCntct)) {
			return false;
		}
		ClaimDvlpmtCntct castOther = (ClaimDvlpmtCntct)other;
		return new EqualsBuilder()
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}