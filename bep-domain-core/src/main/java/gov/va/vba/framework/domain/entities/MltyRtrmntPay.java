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
 * The persistent class for the MLTY_RTRMNT_PAY database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_RTRMNT_PAY")
public class MltyRtrmntPay implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private MltyRtrmntPayPK compId;
	private java.sql.Date efctvDt;
	private String fullWaiverInd;
	private Double grossMthlyAmt;
	private Double lessFedTaxAmt;
	private String rtrmntPayTypeCd;
	private java.sql.Date rtrmntWaivedDt;
	private Double sbpOvrpmtAmt;
	private String verifdInd;
	private MltyPerson mltyPerson;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyRtrmntPay() {
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
	public MltyRtrmntPayPK getCompId() {
		return this.compId;
	}
	public void setCompId(MltyRtrmntPayPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="EFCTV_DT", nullable=false, length=7)
	public java.sql.Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(java.sql.Date efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Basic()
	@Column(name="FULL_WAIVER_IND", length=1)
	public String getFullWaiverInd() {
		return this.fullWaiverInd;
	}
	public void setFullWaiverInd(String fullWaiverInd) {
		this.fullWaiverInd = fullWaiverInd;
	}

	@Basic()
	@Column(name="GROSS_MTHLY_AMT", precision=7, scale=2)
	public Double getGrossMthlyAmt() {
		return this.grossMthlyAmt;
	}
	public void setGrossMthlyAmt(Double grossMthlyAmt) {
		this.grossMthlyAmt = grossMthlyAmt;
	}

	@Basic()
	@Column(name="LESS_FED_TAX_AMT", precision=8, scale=2)
	public Double getLessFedTaxAmt() {
		return this.lessFedTaxAmt;
	}
	public void setLessFedTaxAmt(Double lessFedTaxAmt) {
		this.lessFedTaxAmt = lessFedTaxAmt;
	}

	@Basic()
	@Column(name="RTRMNT_PAY_TYPE_CD", length=12)
	public String getRtrmntPayTypeCd() {
		return this.rtrmntPayTypeCd;
	}
	public void setRtrmntPayTypeCd(String rtrmntPayTypeCd) {
		this.rtrmntPayTypeCd = rtrmntPayTypeCd;
	}

	@Basic()
	@Column(name="RTRMNT_WAIVED_DT", length=7)
	public java.sql.Date getRtrmntWaivedDt() {
		return this.rtrmntWaivedDt;
	}
	public void setRtrmntWaivedDt(java.sql.Date rtrmntWaivedDt) {
		this.rtrmntWaivedDt = rtrmntWaivedDt;
	}

	@Basic()
	@Column(name="SBP_OVRPMT_AMT", precision=8, scale=2)
	public Double getSbpOvrpmtAmt() {
		return this.sbpOvrpmtAmt;
	}
	public void setSbpOvrpmtAmt(Double sbpOvrpmtAmt) {
		this.sbpOvrpmtAmt = sbpOvrpmtAmt;
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
		if (!(other instanceof MltyRtrmntPay)) {
			return false;
		}
		MltyRtrmntPay castOther = (MltyRtrmntPay)other;
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