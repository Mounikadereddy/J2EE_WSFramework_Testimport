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
 * The persistent class for the MLTY_VSI_PAY database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_VSI_PAY")
public class MltyVsiPay implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private MltyVsiPayPK compId;
	private Integer annualInslmtQty;
	private int grossAmt;
	private java.sql.Date startYearDt;
	private String verifdInd;
	private Integer yearlyAmt;
	private MltyPerson mltyPerson;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltyVsiPay() {
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
	public MltyVsiPayPK getCompId() {
		return this.compId;
	}
	public void setCompId(MltyVsiPayPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ANNUAL_INSLMT_QTY", precision=2)
	public Integer getAnnualInslmtQty() {
		return this.annualInslmtQty;
	}
	public void setAnnualInslmtQty(Integer annualInslmtQty) {
		this.annualInslmtQty = annualInslmtQty;
	}

	@Basic()
	@Column(name="GROSS_AMT", nullable=false, precision=8)
	public int getGrossAmt() {
		return this.grossAmt;
	}
	public void setGrossAmt(int grossAmt) {
		this.grossAmt = grossAmt;
	}

	@Basic()
	@Column(name="START_YEAR_DT", length=7)
	public java.sql.Date getStartYearDt() {
		return this.startYearDt;
	}
	public void setStartYearDt(java.sql.Date startYearDt) {
		this.startYearDt = startYearDt;
	}

	@Basic()
	@Column(name="VERIFD_IND", length=1)
	public String getVerifdInd() {
		return this.verifdInd;
	}
	public void setVerifdInd(String verifdInd) {
		this.verifdInd = verifdInd;
	}

	@Basic()
	@Column(name="YEARLY_AMT", precision=7)
	public Integer getYearlyAmt() {
		return this.yearlyAmt;
	}
	public void setYearlyAmt(Integer yearlyAmt) {
		this.yearlyAmt = yearlyAmt;
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
		if (!(other instanceof MltyVsiPay)) {
			return false;
		}
		MltyVsiPay castOther = (MltyVsiPay)other;
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