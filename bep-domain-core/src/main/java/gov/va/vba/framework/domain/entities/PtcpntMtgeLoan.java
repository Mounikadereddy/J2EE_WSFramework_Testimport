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
 * The persistent class for the PTCPNT_MTGE_LOAN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_MTGE_LOAN")
public class PtcpntMtgeLoan implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntMtgeLoanPK compId;
	private Date endDt;
	private Double entlmtChrgdAmt;
	private Double entlmtLossAmt;
	private String firstBuyerInd;
	private String indmfnAuditNbr;
	private Date indmfnExcdDt;
	private Date indmfnExprtnDt;
	private String lenderLoanNbr;
	private String oblgrDebtTypeCd;
	private Date startDt;
	private String svcrLossMitgtnPgmInd;
	private Ptcpnt ptcpnt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntMtgeLoan() {
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
	
	@EmbeddedId
	public PtcpntMtgeLoanPK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntMtgeLoanPK compId) {
		this.compId = compId;
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
	@Column(name="ENTLMT_CHRGD_AMT", precision=8, scale=2)
	public Double getEntlmtChrgdAmt() {
		return this.entlmtChrgdAmt;
	}
	public void setEntlmtChrgdAmt(Double entlmtChrgdAmt) {
		this.entlmtChrgdAmt = entlmtChrgdAmt;
	}

	@Basic()
	@Column(name="ENTLMT_LOSS_AMT", precision=8, scale=2)
	public Double getEntlmtLossAmt() {
		return this.entlmtLossAmt;
	}
	public void setEntlmtLossAmt(Double entlmtLossAmt) {
		this.entlmtLossAmt = entlmtLossAmt;
	}

	@Basic()
	@Column(name="FIRST_BUYER_IND", length=1)
	public String getFirstBuyerInd() {
		return this.firstBuyerInd;
	}
	public void setFirstBuyerInd(String firstBuyerInd) {
		this.firstBuyerInd = firstBuyerInd;
	}

	@Basic()
	@Column(name="INDMFN_AUDIT_NBR", length=10)
	public String getIndmfnAuditNbr() {
		return this.indmfnAuditNbr;
	}
	public void setIndmfnAuditNbr(String indmfnAuditNbr) {
		this.indmfnAuditNbr = indmfnAuditNbr;
	}

	@Basic()
	@Column(name="INDMFN_EXCD_DT", length=7)
	public Date getIndmfnExcdDt() {
		return this.indmfnExcdDt;
	}
	public void setIndmfnExcdDt(Date indmfnExcdDt) {
		this.indmfnExcdDt = indmfnExcdDt;
	}

	@Basic()
	@Column(name="INDMFN_EXPRTN_DT", length=7)
	public Date getIndmfnExprtnDt() {
		return this.indmfnExprtnDt;
	}
	public void setIndmfnExprtnDt(Date indmfnExprtnDt) {
		this.indmfnExprtnDt = indmfnExprtnDt;
	}

	@Basic()
	@Column(name="LENDER_LOAN_NBR", length=20)
	public String getLenderLoanNbr() {
		return this.lenderLoanNbr;
	}
	public void setLenderLoanNbr(String lenderLoanNbr) {
		this.lenderLoanNbr = lenderLoanNbr;
	}

	@Basic()
	@Column(name="OBLGR_DEBT_TYPE_CD", length=12)
	public String getOblgrDebtTypeCd() {
		return this.oblgrDebtTypeCd;
	}
	public void setOblgrDebtTypeCd(String oblgrDebtTypeCd) {
		this.oblgrDebtTypeCd = oblgrDebtTypeCd;
	}

	@Basic()
	@Column(name="START_DT", length=7)
	public Date getStartDt() {
		return this.startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Basic()
	@Column(name="SVCR_LOSS_MITGTN_PGM_IND", length=1)
	public String getSvcrLossMitgtnPgmInd() {
		return this.svcrLossMitgtnPgmInd;
	}
	public void setSvcrLossMitgtnPgmInd(String svcrLossMitgtnPgmInd) {
		this.svcrLossMitgtnPgmInd = svcrLossMitgtnPgmInd;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
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
		if (!(other instanceof PtcpntMtgeLoan)) {
			return false;
		}
		PtcpntMtgeLoan castOther = (PtcpntMtgeLoan)other;
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