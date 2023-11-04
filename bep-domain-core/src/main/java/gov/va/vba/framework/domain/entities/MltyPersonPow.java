package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_PERSON_POW database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_PERSON_POW")
public class MltyPersonPow implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private MltyPersonPowPK compId;
	private String campSectorTxt;
	private String captorTxt;
	private java.sql.Date captrDt;
	private Integer daysQty;
	private String mltyTheatrTypeNm;
	private String powCntryTypeCd;
	private java.sql.Date rlseDt;
	private String underThirtyDaysInd;
	private String verifdInd;
	private MltyPerson mltyPerson;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyPersonPow() {
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
	
	@EmbeddedId
	public MltyPersonPowPK getCompId() {
		return this.compId;
	}
	public void setCompId(MltyPersonPowPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="CAMP_SECTOR_TXT", length=50)
	public String getCampSectorTxt() {
		return this.campSectorTxt;
	}
	public void setCampSectorTxt(String campSectorTxt) {
		this.campSectorTxt = campSectorTxt;
	}

	@Basic()
	@Column(name="CAPTOR_TXT", length=50)
	public String getCaptorTxt() {
		return this.captorTxt;
	}
	public void setCaptorTxt(String captorTxt) {
		this.captorTxt = captorTxt;
	}

	@Basic()
	@Column(name="CAPTR_DT", length=7)
	public java.sql.Date getCaptrDt() {
		return this.captrDt;
	}
	public void setCaptrDt(java.sql.Date captrDt) {
		this.captrDt = captrDt;
	}

	@Basic()
	@Column(name="DAYS_QTY", precision=4)
	public Integer getDaysQty() {
		return this.daysQty;
	}
	public void setDaysQty(Integer daysQty) {
		this.daysQty = daysQty;
	}

	@Basic()
	@Column(name="MLTY_THEATR_TYPE_NM", length=50)
	public String getMltyTheatrTypeNm() {
		return this.mltyTheatrTypeNm;
	}
	public void setMltyTheatrTypeNm(String mltyTheatrTypeNm) {
		this.mltyTheatrTypeNm = mltyTheatrTypeNm;
	}

	@Basic()
	@Column(name="POW_CNTRY_TYPE_CD", length=12)
	public String getPowCntryTypeCd() {
		return this.powCntryTypeCd;
	}
	public void setPowCntryTypeCd(String powCntryTypeCd) {
		this.powCntryTypeCd = powCntryTypeCd;
	}

	@Basic()
	@Column(name="RLSE_DT", length=7)
	public java.sql.Date getRlseDt() {
		return this.rlseDt;
	}
	public void setRlseDt(java.sql.Date rlseDt) {
		this.rlseDt = rlseDt;
	}

	@Basic()
	@Column(name="UNDER_THIRTY_DAYS_IND", length=1)
	public String getUnderThirtyDaysInd() {
		return this.underThirtyDaysInd;
	}
	public void setUnderThirtyDaysInd(String underThirtyDaysInd) {
		this.underThirtyDaysInd = underThirtyDaysInd;
	}

	@Basic()
	@Column(name="VERIFD_IND", length=1)
	public String getVerifdInd() {
		return this.verifdInd;
	}
	public void setVerifdInd(String verifdInd) {
		this.verifdInd = verifdInd;
	}

	//bi-directional many-to-one association to MltyPerson
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public MltyPerson getMltyPerson() {
		return this.mltyPerson;
	}
	public void setMltyPerson(MltyPerson mltyPerson) {
		this.mltyPerson = mltyPerson;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltyPersonPow)) {
			return false;
		}
		MltyPersonPow castOther = (MltyPersonPow)other;
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