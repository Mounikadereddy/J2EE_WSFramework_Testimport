package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_LEDGER_DCMNT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_LEDGER_DCMNT")
public class PtcpntLedgerDcmnt implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntLedgerDcmntId;
	private Long acntTableId;
	private String acntTableTypeCd;
	private Long acntTranNbr;
	private Long agencyLedgerDcmntId;
	private Double amt;
	private Long awardEventId;
	private String checkSymbolCd;
	private Date dcmntDt;
	private String extnlRfrncNbr;
	private Long lctnId;
	private String ledgerDcmntTypeCd;
	private Date lgyRcptDt;
	private Double pymtDscntPct;
	private Date pymtDueDt;
	private Date rcvdDt;
	private String rmksTxt;
	private String statusTypeCd;
	private String submtrInvceNbr;
	private String vaCntrlNbr;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private Ptcpnt ptcpnt3;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntLedgerDcmnt() {
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
	
	@Id()
	@Column(name="PTCPNT_LEDGER_DCMNT_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntLedgerDcmntId() {
		return this.ptcpntLedgerDcmntId;
	}
	public void setPtcpntLedgerDcmntId(Long ptcpntLedgerDcmntId) {
		this.ptcpntLedgerDcmntId = ptcpntLedgerDcmntId;
	}

	@Basic()
	@Column(name="ACNT_TABLE_ID", precision=15)
	public Long getAcntTableId() {
		return this.acntTableId;
	}
	public void setAcntTableId(Long acntTableId) {
		this.acntTableId = acntTableId;
	}

	@Basic()
	@Column(name="ACNT_TABLE_TYPE_CD", length=12)
	public String getAcntTableTypeCd() {
		return this.acntTableTypeCd;
	}
	public void setAcntTableTypeCd(String acntTableTypeCd) {
		this.acntTableTypeCd = acntTableTypeCd;
	}

	@Basic()
	@Column(name="ACNT_TRAN_NBR", precision=15)
	public Long getAcntTranNbr() {
		return this.acntTranNbr;
	}
	public void setAcntTranNbr(Long acntTranNbr) {
		this.acntTranNbr = acntTranNbr;
	}

	@Basic()
	@Column(name="AGENCY_LEDGER_DCMNT_ID", precision=15)
	public Long getAgencyLedgerDcmntId() {
		return this.agencyLedgerDcmntId;
	}
	public void setAgencyLedgerDcmntId(Long agencyLedgerDcmntId) {
		this.agencyLedgerDcmntId = agencyLedgerDcmntId;
	}

	@Basic()
	@Column(name="AMT", precision=15, scale=2)
	public Double getAmt() {
		return this.amt;
	}
	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Basic()
	@Column(name="AWARD_EVENT_ID", precision=15)
	public Long getAwardEventId() {
		return this.awardEventId;
	}
	public void setAwardEventId(Long awardEventId) {
		this.awardEventId = awardEventId;
	}

	@Basic()
	@Column(name="CHECK_SYMBOL_CD", length=4)
	public String getCheckSymbolCd() {
		return this.checkSymbolCd;
	}
	public void setCheckSymbolCd(String checkSymbolCd) {
		this.checkSymbolCd = checkSymbolCd;
	}

	@Basic()
	@Column(name="DCMNT_DT", length=7)
	public Date getDcmntDt() {
		return this.dcmntDt;
	}
	public void setDcmntDt(Date dcmntDt) {
		this.dcmntDt = dcmntDt;
	}

	@Basic()
	@Column(name="EXTNL_RFRNC_NBR", length=15)
	public String getExtnlRfrncNbr() {
		return this.extnlRfrncNbr;
	}
	public void setExtnlRfrncNbr(String extnlRfrncNbr) {
		this.extnlRfrncNbr = extnlRfrncNbr;
	}

	@Basic()
	@Column(name="LCTN_ID", precision=15)
	public Long getLctnId() {
		return this.lctnId;
	}
	public void setLctnId(Long lctnId) {
		this.lctnId = lctnId;
	}

	@Basic()
	@Column(name="LEDGER_DCMNT_TYPE_CD", length=12)
	public String getLedgerDcmntTypeCd() {
		return this.ledgerDcmntTypeCd;
	}
	public void setLedgerDcmntTypeCd(String ledgerDcmntTypeCd) {
		this.ledgerDcmntTypeCd = ledgerDcmntTypeCd;
	}

	@Basic()
	@Column(name="LGY_RCPT_DT", length=7)
	public Date getLgyRcptDt() {
		return this.lgyRcptDt;
	}
	public void setLgyRcptDt(Date lgyRcptDt) {
		this.lgyRcptDt = lgyRcptDt;
	}

	@Basic()
	@Column(name="PYMT_DSCNT_PCT", precision=5, scale=4)
	public Double getPymtDscntPct() {
		return this.pymtDscntPct;
	}
	public void setPymtDscntPct(Double pymtDscntPct) {
		this.pymtDscntPct = pymtDscntPct;
	}

	@Basic()
	@Column(name="PYMT_DUE_DT", length=7)
	public Date getPymtDueDt() {
		return this.pymtDueDt;
	}
	public void setPymtDueDt(Date pymtDueDt) {
		this.pymtDueDt = pymtDueDt;
	}

	@Basic()
	@Column(name="RCVD_DT", length=7)
	public Date getRcvdDt() {
		return this.rcvdDt;
	}
	public void setRcvdDt(Date rcvdDt) {
		this.rcvdDt = rcvdDt;
	}

	@Basic()
	@Column(name="RMKS_TXT", length=254)
	public String getRmksTxt() {
		return this.rmksTxt;
	}
	public void setRmksTxt(String rmksTxt) {
		this.rmksTxt = rmksTxt;
	}

	@Basic()
	@Column(name="STATUS_TYPE_CD", length=12)
	public String getStatusTypeCd() {
		return this.statusTypeCd;
	}
	public void setStatusTypeCd(String statusTypeCd) {
		this.statusTypeCd = statusTypeCd;
	}

	@Basic()
	@Column(name="SUBMTR_INVCE_NBR", length=10)
	public String getSubmtrInvceNbr() {
		return this.submtrInvceNbr;
	}
	public void setSubmtrInvceNbr(String submtrInvceNbr) {
		this.submtrInvceNbr = submtrInvceNbr;
	}

	@Basic()
	@Column(name="VA_CNTRL_NBR", length=15)
	public String getVaCntrlNbr() {
		return this.vaCntrlNbr;
	}
	public void setVaCntrlNbr(String vaCntrlNbr) {
		this.vaCntrlNbr = vaCntrlNbr;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_RECIP_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_VET_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_BENE_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt3() {
		return this.ptcpnt3;
	}
	public void setPtcpnt3(Ptcpnt ptcpnt3) {
		this.ptcpnt3 = ptcpnt3;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntLedgerDcmnt)) {
			return false;
		}
		PtcpntLedgerDcmnt castOther = (PtcpntLedgerDcmnt)other;
		return new EqualsBuilder()
			.append(this.getPtcpntLedgerDcmntId(), castOther.getPtcpntLedgerDcmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntLedgerDcmntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntLedgerDcmntId", getPtcpntLedgerDcmntId())
			.toString();
	}
}