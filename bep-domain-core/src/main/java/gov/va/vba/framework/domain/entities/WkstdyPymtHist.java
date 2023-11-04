package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The persistent class for the WKSTDY_PYMT_HIST database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="WKSTDY_PYMT_HIST")
public class WkstdyPymtHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long wkstdyPymtHistId;
	private String curntPymtInd;
	private Double totalAcntRcvblAmt;
	private Double totalAdvncAmt;
	private Double totalDueAmt;
	private Double totalFiscal04eAmt;
	private Double totalFiscal08eAmt;
	private Double totalHoursNbr;
	private Double totalPaidAmt;
	private WkstdyCntrct wkstdyCntrct;
	private WkstdyTimeRecord wkstdyTimeRecord;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public WkstdyPymtHist() {
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
	@Column(name="WKSTDY_PYMT_HIST_ID", unique=true, nullable=false, precision=15)
	public Long getWkstdyPymtHistId() {
		return this.wkstdyPymtHistId;
	}
	public void setWkstdyPymtHistId(Long wkstdyPymtHistId) {
		this.wkstdyPymtHistId = wkstdyPymtHistId;
	}

	@Basic()
	@Column(name="CURNT_PYMT_IND", nullable=false, length=1)
	public String getCurntPymtInd() {
		return this.curntPymtInd;
	}
	public void setCurntPymtInd(String curntPymtInd) {
		this.curntPymtInd = curntPymtInd;
	}

	@Basic()
	@Column(name="TOTAL_ACNT_RCVBL_AMT", precision=8, scale=2)
	public Double getTotalAcntRcvblAmt() {
		return this.totalAcntRcvblAmt;
	}
	public void setTotalAcntRcvblAmt(Double totalAcntRcvblAmt) {
		this.totalAcntRcvblAmt = totalAcntRcvblAmt;
	}

	@Basic()
	@Column(name="TOTAL_ADVNC_AMT", precision=8, scale=2)
	public Double getTotalAdvncAmt() {
		return this.totalAdvncAmt;
	}
	public void setTotalAdvncAmt(Double totalAdvncAmt) {
		this.totalAdvncAmt = totalAdvncAmt;
	}

	@Basic()
	@Column(name="TOTAL_DUE_AMT", precision=8, scale=2)
	public Double getTotalDueAmt() {
		return this.totalDueAmt;
	}
	public void setTotalDueAmt(Double totalDueAmt) {
		this.totalDueAmt = totalDueAmt;
	}

	@Basic()
	@Column(name="TOTAL_FISCAL_04E_AMT", precision=8, scale=2)
	public Double getTotalFiscal04eAmt() {
		return this.totalFiscal04eAmt;
	}
	public void setTotalFiscal04eAmt(Double totalFiscal04eAmt) {
		this.totalFiscal04eAmt = totalFiscal04eAmt;
	}

	@Basic()
	@Column(name="TOTAL_FISCAL_08E_AMT", precision=8, scale=2)
	public Double getTotalFiscal08eAmt() {
		return this.totalFiscal08eAmt;
	}
	public void setTotalFiscal08eAmt(Double totalFiscal08eAmt) {
		this.totalFiscal08eAmt = totalFiscal08eAmt;
	}

	@Basic()
	@Column(name="TOTAL_HOURS_NBR", precision=8, scale=2)
	public Double getTotalHoursNbr() {
		return this.totalHoursNbr;
	}
	public void setTotalHoursNbr(Double totalHoursNbr) {
		this.totalHoursNbr = totalHoursNbr;
	}

	@Basic()
	@Column(name="TOTAL_PAID_AMT", precision=8, scale=2)
	public Double getTotalPaidAmt() {
		return this.totalPaidAmt;
	}
	public void setTotalPaidAmt(Double totalPaidAmt) {
		this.totalPaidAmt = totalPaidAmt;
	}

	//bi-directional many-to-one association to WkstdyCntrct
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WKSTDY_CNTRCT_ID", referencedColumnName="WKSTDY_CNTRCT_ID", nullable=false)
	public WkstdyCntrct getWkstdyCntrct() {
		return this.wkstdyCntrct;
	}
	public void setWkstdyCntrct(WkstdyCntrct wkstdyCntrct) {
		this.wkstdyCntrct = wkstdyCntrct;
	}

	//bi-directional many-to-one association to WkstdyTimeRecord
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="WKSTDY_TIME_RECORD_ID", referencedColumnName="WKSTDY_TIME_RECORD_ID", nullable=false)
	public WkstdyTimeRecord getWkstdyTimeRecord() {
		return this.wkstdyTimeRecord;
	}
	public void setWkstdyTimeRecord(WkstdyTimeRecord wkstdyTimeRecord) {
		this.wkstdyTimeRecord = wkstdyTimeRecord;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WkstdyPymtHist)) {
			return false;
		}
		WkstdyPymtHist castOther = (WkstdyPymtHist)other;
		return new EqualsBuilder()
			.append(this.getWkstdyPymtHistId(), castOther.getWkstdyPymtHistId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWkstdyPymtHistId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("wkstdyPymtHistId", getWkstdyPymtHistId())
			.toString();
	}
}