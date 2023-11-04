package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the CH31_INTRFC_GED_DGNSTC database table.
 * 
 * @author BEA Workshop
 */
@SqlResultSetMapping(name="ch31IntrfcGedDgnstc", entities={@EntityResult(entityClass=Ch31IntrfcGedDgnstc.class)})
@Entity()
@Table(name="CH31_INTRFC_GED_DGNSTC")
public class Ch31IntrfcGedDgnstc implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ch31IntrfcGedDgnstcId;
	private long ch31IntrfcGedTranId;
	private Integer dgnstcPct;
	private String dgnstcTypeCd;
	private String svcCnectdInd;
    private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Ch31IntrfcGedDgnstc() {
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
	@Column(name="CH31_INTRFC_GED_DGNSTC_ID", unique=true, nullable=false, precision=15)
	public Long getCh31IntrfcGedDgnstcId() {
		return this.ch31IntrfcGedDgnstcId;
	}
	public void setCh31IntrfcGedDgnstcId(Long ch31IntrfcGedDgnstcId) {
		this.ch31IntrfcGedDgnstcId = ch31IntrfcGedDgnstcId;
	}

	@Basic()
	@Column(name="CH31_INTRFC_GED_TRAN_ID", nullable=false, precision=15)
	public long getCh31IntrfcGedTranId() {
		return this.ch31IntrfcGedTranId;
	}
	public void setCh31IntrfcGedTranId(long ch31IntrfcGedTranId) {
		this.ch31IntrfcGedTranId = ch31IntrfcGedTranId;
	}

	@Basic()
	@Column(name="DGNSTC_PCT", precision=3)
	public Integer getDgnstcPct() {
		return this.dgnstcPct;
	}
	public void setDgnstcPct(Integer dgnstcPct) {
		this.dgnstcPct = dgnstcPct;
	}

	@Basic()
	@Column(name="DGNSTC_TYPE_CD", nullable=false, length=12)
	public String getDgnstcTypeCd() {
		return this.dgnstcTypeCd;
	}
	public void setDgnstcTypeCd(String dgnstcTypeCd) {
		this.dgnstcTypeCd = dgnstcTypeCd;
	}

	@Basic()
	@Column(name="SVC_CNECTD_IND", length=1)
	public String getSvcCnectdInd() {
		return this.svcCnectdInd;
	}
	public void setSvcCnectdInd(String svcCnectdInd) {
		this.svcCnectdInd = svcCnectdInd;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Ch31IntrfcGedDgnstc)) {
			return false;
		}
		Ch31IntrfcGedDgnstc castOther = (Ch31IntrfcGedDgnstc)other;
		return new EqualsBuilder()
			.append(this.getCh31IntrfcGedDgnstcId(), castOther.getCh31IntrfcGedDgnstcId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCh31IntrfcGedDgnstcId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ch31IntrfcGedDgnstcId", getCh31IntrfcGedDgnstcId())
			.toString();
	}
}