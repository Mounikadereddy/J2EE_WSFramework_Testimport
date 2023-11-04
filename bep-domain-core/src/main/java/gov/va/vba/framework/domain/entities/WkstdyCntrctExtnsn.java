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
 * The persistent class for the WKSTDY_CNTRCT_EXTNSN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="WKSTDY_CNTRCT_EXTNSN")
public class WkstdyCntrctExtnsn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long wkstdyCntrctExtnsnId;
	private int cntrctExtnsnNbr;
	private Date endDt;
	private Date enterDt;
	private Double extnsnHoursNbr;
	private Date rcvdDt;
	private Date startDt;
	private String statusTypeCd;
	private WkstdyCntrct wkstdyCntrct;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public WkstdyCntrctExtnsn() {
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
	@Column(name="WKSTDY_CNTRCT_EXTNSN_ID", unique=true, nullable=false, precision=10)
	public Long getWkstdyCntrctExtnsnId() {
		return this.wkstdyCntrctExtnsnId;
	}
	public void setWkstdyCntrctExtnsnId(Long wkstdyCntrctExtnsnId) {
		this.wkstdyCntrctExtnsnId = wkstdyCntrctExtnsnId;
	}

	@Basic()
	@Column(name="CNTRCT_EXTNSN_NBR", nullable=false, precision=3)
	public int getCntrctExtnsnNbr() {
		return this.cntrctExtnsnNbr;
	}
	public void setCntrctExtnsnNbr(int cntrctExtnsnNbr) {
		this.cntrctExtnsnNbr = cntrctExtnsnNbr;
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
	@Column(name="ENTER_DT", length=7)
	public Date getEnterDt() {
		return this.enterDt;
	}
	public void setEnterDt(Date enterDt) {
		this.enterDt = enterDt;
	}

	@Basic()
	@Column(name="EXTNSN_HOURS_NBR", precision=8, scale=2)
	public Double getExtnsnHoursNbr() {
		return this.extnsnHoursNbr;
	}
	public void setExtnsnHoursNbr(Double extnsnHoursNbr) {
		this.extnsnHoursNbr = extnsnHoursNbr;
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
	@Column(name="START_DT", nullable=false, length=7)
	public Date getStartDt() {
		return this.startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	@Basic()
	@Column(name="STATUS_TYPE_CD", length=12)
	public String getStatusTypeCd() {
		return this.statusTypeCd;
	}
	public void setStatusTypeCd(String statusTypeCd) {
		this.statusTypeCd = statusTypeCd;
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
		if (!(other instanceof WkstdyCntrctExtnsn)) {
			return false;
		}
		WkstdyCntrctExtnsn castOther = (WkstdyCntrctExtnsn)other;
		return new EqualsBuilder()
			.append(this.getWkstdyCntrctExtnsnId(), castOther.getWkstdyCntrctExtnsnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getWkstdyCntrctExtnsnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("wkstdyCntrctExtnsnId", getWkstdyCntrctExtnsnId())
			.toString();
	}
}