package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the POSTAL_TYPE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="POSTAL_TYPE")
public class PstalType implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String postalCd;
	private String commonLawStateInd;
	private String descpTxt;
	private String legacyFipsStateCd;
	private String nm;
	private String stateInd;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PstalType() {
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
	@Column(name="POSTAL_CD", unique=true, nullable=false, length=2)
	public String getPostalCd() {
		return this.postalCd;
	}
	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}

	@Basic()
	@Column(name="COMMON_LAW_STATE_IND", length=1)
	public String getCommonLawStateInd() {
		return this.commonLawStateInd;
	}
	public void setCommonLawStateInd(String commonLawStateInd) {
		this.commonLawStateInd = commonLawStateInd;
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
	@Column(name="LEGACY_FIPS_STATE_CD", length=2)
	public String getLegacyFipsStateCd() {
		return this.legacyFipsStateCd;
	}
	public void setLegacyFipsStateCd(String legacyFipsStateCd) {
		this.legacyFipsStateCd = legacyFipsStateCd;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=50)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	@Basic()
	@Column(name="STATE_IND", length=1)
	public String getStateInd() {
		return this.stateInd;
	}
	public void setStateInd(String stateInd) {
		this.stateInd = stateInd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PstalType)) {
			return false;
		}
		PstalType castOther = (PstalType)other;
		return new EqualsBuilder()
			.append(this.getPostalCd(), castOther.getPostalCd())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPostalCd())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("postalCd", getPostalCd())
			.toString();
	}
}