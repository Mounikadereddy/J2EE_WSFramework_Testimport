package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PTCPNT_ACNT_RLNSHP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_ACNT_RLNSHP")
public class PtcpntAcntRlnshp implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long ptcpntAcntRlnshpId;
	private long acntFromId;
	private String acntFromTableNm;
	private long acntFromTranNbr;
	private long acntToId;
	private String acntToTableNm;
	private long acntToTranNbr;
	private String adjsmtStatusTypeCd;
	private double amt;
	private long finclBusnsTranId;
	private Long offsetPlanId;
	private Long offsetPlanTranNbr;
	private String statusTypeCd;
	private Date tranDt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntAcntRlnshp() {
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
	@Column(name="PTCPNT_ACNT_RLNSHP_ID", unique=true, nullable=false, precision=15)
	public Long getPtcpntAcntRlnshpId() {
		return this.ptcpntAcntRlnshpId;
	}
	public void setPtcpntAcntRlnshpId(Long ptcpntAcntRlnshpId) {
		this.ptcpntAcntRlnshpId = ptcpntAcntRlnshpId;
	}

	@Basic()
	@Column(name="ACNT_FROM_ID", nullable=false, precision=15)
	public long getAcntFromId() {
		return this.acntFromId;
	}
	public void setAcntFromId(long acntFromId) {
		this.acntFromId = acntFromId;
	}

	@Basic()
	@Column(name="ACNT_FROM_TABLE_NM", nullable=false, length=50)
	public String getAcntFromTableNm() {
		return this.acntFromTableNm;
	}
	public void setAcntFromTableNm(String acntFromTableNm) {
		this.acntFromTableNm = acntFromTableNm;
	}

	@Basic()
	@Column(name="ACNT_FROM_TRAN_NBR", nullable=false, precision=15)
	public long getAcntFromTranNbr() {
		return this.acntFromTranNbr;
	}
	public void setAcntFromTranNbr(long acntFromTranNbr) {
		this.acntFromTranNbr = acntFromTranNbr;
	}

	@Basic()
	@Column(name="ACNT_TO_ID", nullable=false, precision=15)
	public long getAcntToId() {
		return this.acntToId;
	}
	public void setAcntToId(long acntToId) {
		this.acntToId = acntToId;
	}

	@Basic()
	@Column(name="ACNT_TO_TABLE_NM", nullable=false, length=50)
	public String getAcntToTableNm() {
		return this.acntToTableNm;
	}
	public void setAcntToTableNm(String acntToTableNm) {
		this.acntToTableNm = acntToTableNm;
	}

	@Basic()
	@Column(name="ACNT_TO_TRAN_NBR", nullable=false, precision=15)
	public long getAcntToTranNbr() {
		return this.acntToTranNbr;
	}
	public void setAcntToTranNbr(long acntToTranNbr) {
		this.acntToTranNbr = acntToTranNbr;
	}

	@Basic()
	@Column(name="ADJSMT_STATUS_TYPE_CD", length=12)
	public String getAdjsmtStatusTypeCd() {
		return this.adjsmtStatusTypeCd;
	}
	public void setAdjsmtStatusTypeCd(String adjsmtStatusTypeCd) {
		this.adjsmtStatusTypeCd = adjsmtStatusTypeCd;
	}

	@Basic()
	@Column(name="AMT", nullable=false, precision=15, scale=2)
	public double getAmt() {
		return this.amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}

	@Basic()
	@Column(name="FINCL_BUSNS_TRAN_ID", nullable=false, precision=15)
	public long getFinclBusnsTranId() {
		return this.finclBusnsTranId;
	}
	public void setFinclBusnsTranId(long finclBusnsTranId) {
		this.finclBusnsTranId = finclBusnsTranId;
	}

	@Basic()
	@Column(name="OFFSET_PLAN_ID", precision=15)
	public Long getOffsetPlanId() {
		return this.offsetPlanId;
	}
	public void setOffsetPlanId(Long offsetPlanId) {
		this.offsetPlanId = offsetPlanId;
	}

	@Basic()
	@Column(name="OFFSET_PLAN_TRAN_NBR", precision=15)
	public Long getOffsetPlanTranNbr() {
		return this.offsetPlanTranNbr;
	}
	public void setOffsetPlanTranNbr(Long offsetPlanTranNbr) {
		this.offsetPlanTranNbr = offsetPlanTranNbr;
	}

	@Basic()
	@Column(name="STATUS_TYPE_CD", nullable=false, length=12)
	public String getStatusTypeCd() {
		return this.statusTypeCd;
	}
	public void setStatusTypeCd(String statusTypeCd) {
		this.statusTypeCd = statusTypeCd;
	}

	@Basic()
	@Column(name="TRAN_DT", nullable=false, length=7)
	public Date getTranDt() {
		return this.tranDt;
	}
	public void setTranDt(Date tranDt) {
		this.tranDt = tranDt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntAcntRlnshp)) {
			return false;
		}
		PtcpntAcntRlnshp castOther = (PtcpntAcntRlnshp)other;
		return new EqualsBuilder()
			.append(this.getPtcpntAcntRlnshpId(), castOther.getPtcpntAcntRlnshpId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPtcpntAcntRlnshpId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("ptcpntAcntRlnshpId", getPtcpntAcntRlnshpId())
			.toString();
	}
}