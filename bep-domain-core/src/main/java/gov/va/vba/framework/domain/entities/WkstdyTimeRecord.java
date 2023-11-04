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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the WKSTDY_TIME_RECORD database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="WKSTDY_TIME_RECORD")
public class WkstdyTimeRecord implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long wkstdyTimeRecordId;
	private String advncInd;
	private Double authzdAmt;
	private Date beginDt;
	private String comntTxt;
	private Double dueAmt;
	private Long eduRateId;
	private Date endDt;
	private Date enterDt;
	private String finalTimeRecordInd;
	private Date input06lDt;
	private String input06lInd;
	private Date rcvdDt;
	private int timeRecordNbr;
	private Long wkstdyCntrctExtnsnId;
	private double workedHoursNbr;
	private java.util.Set<WkstdyPymtHist> wkstdyPymtHists;
	private WkstdyCntrct wkstdyCntrct;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public WkstdyTimeRecord() {
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
	@Column(name="WKSTDY_TIME_RECORD_ID", unique=true, nullable=false, precision=15)
	public Long getWkstdyTimeRecordId() {
		return this.wkstdyTimeRecordId;
	}
	public void setWkstdyTimeRecordId(Long wkstdyTimeRecordId) {
		this.wkstdyTimeRecordId = wkstdyTimeRecordId;
	}

	@Basic()
	@Column(name="ADVNC_IND", nullable=false, length=1)
	public String getAdvncInd() {
		return this.advncInd;
	}
	public void setAdvncInd(String advncInd) {
		this.advncInd = advncInd;
	}

	@Basic()
	@Column(name="AUTHZD_AMT", precision=8, scale=2)
	public Double getAuthzdAmt() {
		return this.authzdAmt;
	}
	public void setAuthzdAmt(Double authzdAmt) {
		this.authzdAmt = authzdAmt;
	}

	@Basic()
	@Column(name="BEGIN_DT", nullable=false, length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="COMNT_TXT", length=800)
	public String getComntTxt() {
		return this.comntTxt;
	}
	public void setComntTxt(String comntTxt) {
		this.comntTxt = comntTxt;
	}

	@Basic()
	@Column(name="DUE_AMT", precision=8, scale=2)
	public Double getDueAmt() {
		return this.dueAmt;
	}
	public void setDueAmt(Double dueAmt) {
		this.dueAmt = dueAmt;
	}

	@Basic()
	@Column(name="EDU_RATE_ID", precision=15)
	public Long getEduRateId() {
		return this.eduRateId;
	}
	public void setEduRateId(Long eduRateId) {
		this.eduRateId = eduRateId;
	}

	@Basic()
	@Column(name="END_DT", nullable=false, length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="ENTER_DT", length=7)
	public Date getEnterDt() {
		return this.enterDt;
	}
	public void setEnterDt(Date enterDt) {
		this.enterDt = enterDt;
	}

	@Basic()
	@Column(name="FINAL_TIME_RECORD_IND", length=1)
	public String getFinalTimeRecordInd() {
		return this.finalTimeRecordInd;
	}
	public void setFinalTimeRecordInd(String finalTimeRecordInd) {
		this.finalTimeRecordInd = finalTimeRecordInd;
	}

	@Basic()
	@Column(name="INPUT_06L_DT", length=7)
	public Date getInput06lDt() {
		return this.input06lDt;
	}
	public void setInput06lDt(Date input06lDt) {
		this.input06lDt = input06lDt;
	}

	@Basic()
	@Column(name="INPUT_06L_IND", length=1)
	public String getInput06lInd() {
		return this.input06lInd;
	}
	public void setInput06lInd(String input06lInd) {
		this.input06lInd = input06lInd;
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
	@Column(name="TIME_RECORD_NBR", nullable=false, precision=3)
	public int getTimeRecordNbr() {
		return this.timeRecordNbr;
	}
	public void setTimeRecordNbr(int timeRecordNbr) {
		this.timeRecordNbr = timeRecordNbr;
	}

	@Basic()
	@Column(name="WKSTDY_CNTRCT_EXTNSN_ID", precision=15)
	public Long getWkstdyCntrctExtnsnId() {
		return this.wkstdyCntrctExtnsnId;
	}
	public void setWkstdyCntrctExtnsnId(Long wkstdyCntrctExtnsnId) {
		this.wkstdyCntrctExtnsnId = wkstdyCntrctExtnsnId;
	}

	@Basic()
	@Column(name="WORKED_HOURS_NBR", nullable=false, precision=8, scale=2)
	public double getWorkedHoursNbr() {
		return this.workedHoursNbr;
	}
	public void setWorkedHoursNbr(double workedHoursNbr) {
		this.workedHoursNbr = workedHoursNbr;
	}

	//bi-directional many-to-one association to WkstdyPymtHist
	@OneToMany(mappedBy="wkstdyTimeRecord", fetch=FetchType.EAGER)
	public java.util.Set<WkstdyPymtHist> getWkstdyPymtHists() {
		return this.wkstdyPymtHists;
	}
	public void setWkstdyPymtHists(java.util.Set<WkstdyPymtHist> wkstdyPymtHists) {
		this.wkstdyPymtHists = wkstdyPymtHists;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof WkstdyTimeRecord)) {
			return false;
		}
		WkstdyTimeRecord castOther = (WkstdyTimeRecord)other;
		return new EqualsBuilder()
			.append(this.getWkstdyTimeRecordId(), castOther.getWkstdyTimeRecordId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWkstdyTimeRecordId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("wkstdyTimeRecordId", getWkstdyTimeRecordId())
			.toString();
	}
}