package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the WKSTDY_CNTRCT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="WKSTDY_CNTRCT")
public class WkstdyCntrct  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long wkstdyCntrctId;
	private Double aprvdAmt;
	private Date aprvdDt;
	private long bnftClaimId;
	private Date closedDt;
	private String comntTxt;
	private Double curntFiscal04eAmt;
	private Double curntFiscal08eAmt;
	private Double curntTotalAcntRcvblAmt;
	private String debtCfrInd;
	private long eduInstnPtcpntId;
	private long eduRateId;
	private Date endDt;
	//private Date lastEndDt;
	private Double orignlHoursNbr;
	private Date posteDt;
	private long ptcpntIdA;
	private long ptcpntIdB;
	private String ptcpntRlnshpTypeNm;
	private Date startDt;
	private String trngTimeTypeCd;
	private java.util.Set<WkstdyCntrctExtnsn> wkstdyCntrctExtnsns;
	private java.util.Set<WkstdyPymtHist> wkstdyPymtHists;
	private java.util.Set<WkstdyTimeRecord> wkstdyTimeRecords;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public WkstdyCntrct() {
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
	@Column(name="WKSTDY_CNTRCT_ID", unique=true, nullable=false, precision=15)
	public Long getWkstdyCntrctId() {
		return this.wkstdyCntrctId;
	}
	public void setWkstdyCntrctId(Long wkstdyCntrctId) {
		this.wkstdyCntrctId = wkstdyCntrctId;
	}

	@Basic()
	@Column(name="APRVD_AMT", precision=8, scale=2)
	public Double getAprvdAmt() {
		return this.aprvdAmt;
	}
	public void setAprvdAmt(Double aprvdAmt) {
		this.aprvdAmt = aprvdAmt;
	}

	@Basic()
	@Column(name="APRVD_DT", length=7)
	public Date getAprvdDt() {
		return this.aprvdDt;
	}
	public void setAprvdDt(Date aprvdDt) {
		this.aprvdDt = aprvdDt;
	}

	@Basic()
	@Column(name="BNFT_CLAIM_ID", nullable=false, precision=15)
	public long getBnftClaimId() {
		return this.bnftClaimId;
	}
	public void setBnftClaimId(long bnftClaimId) {
		this.bnftClaimId = bnftClaimId;
	}

	@Basic()
	@Column(name="CLOSED_DT", length=7)
	public Date getClosedDt() {
		return this.closedDt;
	}
	public void setClosedDt(Date closedDt) {
		this.closedDt = closedDt;
	}

	@Basic()
	@Column(name="COMNT_TXT", length=1500)
	public String getComntTxt() {
		return this.comntTxt;
	}
	public void setComntTxt(String comntTxt) {
		this.comntTxt = comntTxt;
	}

	@Basic()
	@Column(name="CURNT_FISCAL_04E_AMT", precision=8, scale=2)
	public Double getCurntFiscal04eAmt() {
		return this.curntFiscal04eAmt;
	}
	public void setCurntFiscal04eAmt(Double curntFiscal04eAmt) {
		this.curntFiscal04eAmt = curntFiscal04eAmt;
	}

	@Basic()
	@Column(name="CURNT_FISCAL_08E_AMT", precision=8, scale=2)
	public Double getCurntFiscal08eAmt() {
		return this.curntFiscal08eAmt;
	}
	public void setCurntFiscal08eAmt(Double curntFiscal08eAmt) {
		this.curntFiscal08eAmt = curntFiscal08eAmt;
	}

	@Basic()
	@Column(name="CURNT_TOTAL_ACNT_RCVBL_AMT", precision=8, scale=2)
	public Double getCurntTotalAcntRcvblAmt() {
		return this.curntTotalAcntRcvblAmt;
	}
	public void setCurntTotalAcntRcvblAmt(Double curntTotalAcntRcvblAmt) {
		this.curntTotalAcntRcvblAmt = curntTotalAcntRcvblAmt;
	}

	@Basic()
	@Column(name="DEBT_CFR_IND", length=1)
	public String getDebtCfrInd() {
		return this.debtCfrInd;
	}
	public void setDebtCfrInd(String debtCfrInd) {
		this.debtCfrInd = debtCfrInd;
	}

	@Basic()
	@Column(name="EDU_INSTN_PTCPNT_ID", nullable=false, precision=15)
	public long getEduInstnPtcpntId() {
		return this.eduInstnPtcpntId;
	}
	public void setEduInstnPtcpntId(long eduInstnPtcpntId) {
		this.eduInstnPtcpntId = eduInstnPtcpntId;
	}

	@Basic()
	@Column(name="EDU_RATE_ID", nullable=false, precision=15)
	public long getEduRateId() {
		return this.eduRateId;
	}
	public void setEduRateId(long eduRateId) {
		this.eduRateId = eduRateId;
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
	@Column(name="ORIGNL_HOURS_NBR", precision=8, scale=2)
	public Double getOrignlHoursNbr() {
		return this.orignlHoursNbr;
	}
	public void setOrignlHoursNbr(Double orignlHoursNbr) {
		this.orignlHoursNbr = orignlHoursNbr;
	}

	@Basic()
	@Column(name="POSTE_DT", length=7)
	public Date getPosteDt() {
		return this.posteDt;
	}
	public void setPosteDt(Date posteDt) {
		this.posteDt = posteDt;
	}

	@Basic()
	@Column(name="PTCPNT_ID_A", nullable=false, precision=15)
	public long getPtcpntIdA() {
		return this.ptcpntIdA;
	}
	public void setPtcpntIdA(long ptcpntIdA) {
		this.ptcpntIdA = ptcpntIdA;
	}

	@Basic()
	@Column(name="PTCPNT_ID_B", nullable=false, precision=15)
	public long getPtcpntIdB() {
		return this.ptcpntIdB;
	}
	public void setPtcpntIdB(long ptcpntIdB) {
		this.ptcpntIdB = ptcpntIdB;
	}

	@Basic()
	@Column(name="PTCPNT_RLNSHP_TYPE_NM", nullable=false, length=50)
	public String getPtcpntRlnshpTypeNm() {
		return this.ptcpntRlnshpTypeNm;
	}
	public void setPtcpntRlnshpTypeNm(String ptcpntRlnshpTypeNm) {
		this.ptcpntRlnshpTypeNm = ptcpntRlnshpTypeNm;
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
	@Column(name="TRNG_TIME_TYPE_CD", nullable=false, length=12)
	public String getTrngTimeTypeCd() {
		return this.trngTimeTypeCd;
	}
	public void setTrngTimeTypeCd(String trngTimeTypeCd) {
		this.trngTimeTypeCd = trngTimeTypeCd;
	}

	//bi-directional many-to-one association to WkstdyCntrctExtnsn
	@OneToMany(mappedBy="wkstdyCntrct", fetch=FetchType.EAGER)
	public java.util.Set<WkstdyCntrctExtnsn> getWkstdyCntrctExtnsns() {
		return this.wkstdyCntrctExtnsns;
	}
	public void setWkstdyCntrctExtnsns(java.util.Set<WkstdyCntrctExtnsn> wkstdyCntrctExtnsns) {
		this.wkstdyCntrctExtnsns = wkstdyCntrctExtnsns;
	}

	//bi-directional many-to-one association to WkstdyPymtHist
	@OneToMany(mappedBy="wkstdyCntrct", fetch=FetchType.EAGER)
	public java.util.Set<WkstdyPymtHist> getWkstdyPymtHists() {
		return this.wkstdyPymtHists;
	}
	public void setWkstdyPymtHists(java.util.Set<WkstdyPymtHist> wkstdyPymtHists) {
		this.wkstdyPymtHists = wkstdyPymtHists;
	}

	//bi-directional many-to-one association to WkstdyTimeRecord
	@OneToMany(mappedBy="wkstdyCntrct", fetch=FetchType.EAGER)
	public java.util.Set<WkstdyTimeRecord> getWkstdyTimeRecords() {
		return this.wkstdyTimeRecords;
	}
	public void setWkstdyTimeRecords(java.util.Set<WkstdyTimeRecord> wkstdyTimeRecords) {
		this.wkstdyTimeRecords = wkstdyTimeRecords;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WkstdyCntrct)) {
			return false;
		}
		WkstdyCntrct castOther = (WkstdyCntrct)other;
		return new EqualsBuilder()
			.append(this.getWkstdyCntrctId(), castOther.getWkstdyCntrctId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWkstdyCntrctId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("wkstdyCntrctId", getWkstdyCntrctId())
			.toString();
	}
}