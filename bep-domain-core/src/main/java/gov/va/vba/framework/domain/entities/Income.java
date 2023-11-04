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
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the INCOME database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="INCOME")
public class Income  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private IncomePK compId;
	private double amt;
	private Date efctvDt;
	private Date endDt;
	private String frqncyTypeNm;
	private String incomeRglrtyTypeNm;
	private String incomeTypeNm;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private Integer periodNbr;
	private Date rcptDt;
	private Integer reprtdAmt;
	private Integer verifdAmt;
//	private CnslgSesion cnslgSesion;
//	private MtgeLoan mtgeLoan;
	private Ptcpnt ptcpnt;

    public Income() {
    }

	@EmbeddedId
	public IncomePK getCompId() {
		return this.compId;
	}
	public void setCompId(IncomePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="AMT", nullable=false, precision=10, scale=2)
	public double getAmt() {
		return this.amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}

	@Basic()
	@Column(name="EFCTV_DT", length=7)
	public Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(Date efctvDt) {
		this.efctvDt = efctvDt;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="FRQNCY_TYPE_NM", length=50)
	public String getFrqncyTypeNm() {
		return this.frqncyTypeNm;
	}
	public void setFrqncyTypeNm(String frqncyTypeNm) {
		this.frqncyTypeNm = frqncyTypeNm;
	}

	@Basic()
	@Column(name="INCOME_RGLRTY_TYPE_NM", length=50)
	public String getIncomeRglrtyTypeNm() {
		return this.incomeRglrtyTypeNm;
	}
	public void setIncomeRglrtyTypeNm(String incomeRglrtyTypeNm) {
		this.incomeRglrtyTypeNm = incomeRglrtyTypeNm;
	}

	@Basic()
	@Column(name="INCOME_TYPE_NM", length=50)
	public String getIncomeTypeNm() {
		return this.incomeTypeNm;
	}
	public void setIncomeTypeNm(String incomeTypeNm) {
		this.incomeTypeNm = incomeTypeNm;
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
	@Column(name="PERIOD_NBR", precision=3)
	public Integer getPeriodNbr() {
		return this.periodNbr;
	}
	public void setPeriodNbr(Integer periodNbr) {
		this.periodNbr = periodNbr;
	}

	@Basic()
	@Column(name="RCPT_DT", length=7)
	public Date getRcptDt() {
		return this.rcptDt;
	}
	public void setRcptDt(Date rcptDt) {
		this.rcptDt = rcptDt;
	}

	@Basic()
	@Column(name="REPRTD_AMT", precision=8)
	public Integer getReprtdAmt() {
		return this.reprtdAmt;
	}
	public void setReprtdAmt(Integer reprtdAmt) {
		this.reprtdAmt = reprtdAmt;
	}

	@Basic()
	@Column(name="VERIFD_AMT", precision=8)
	public Integer getVerifdAmt() {
		return this.verifdAmt;
	}
	public void setVerifdAmt(Integer verifdAmt) {
		this.verifdAmt = verifdAmt;
	}

//	//bi-directional many-to-one association to CnslgSesion
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="CNSLG_SESION_ID", referencedColumnName="CNSLG_SESION_ID")
//	public CnslgSesion getCnslgSesion() {
//		return this.cnslgSesion;
//	}
//	public void setCnslgSesion(CnslgSesion cnslgSesion) {
//		this.cnslgSesion = cnslgSesion;
//	}
//
//	//bi-directional many-to-one association to MtgeLoan
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="MTGE_LOAN_ID", referencedColumnName="MTGE_LOAN_ID")
//	public MtgeLoan getMtgeLoan() {
//		return this.mtgeLoan;
//	}
//	public void setMtgeLoan(MtgeLoan mtgeLoan) {
//		this.mtgeLoan = mtgeLoan;
//	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Income)) {
			return false;
		}
		Income castOther = (Income)other;
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