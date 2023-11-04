package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

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
 * The persistent class for the APPLCN_BATCH_NOTFCN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_BATCH_NOTFCN")
public class ApplcnBatchNotfcn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnBatchNotfcnId;
	private String emailAddrsTxt;
	private ApplcnBatch applcnBatch;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnBatchNotfcn() {
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
	@Column(name="APPLCN_BATCH_NOTFCN_ID", unique=true, nullable=false, precision=15)
	public Long getApplcnBatchNotfcnId() {
		return this.applcnBatchNotfcnId;
	}
	public void setApplcnBatchNotfcnId(Long applcnBatchNotfcnId) {
		this.applcnBatchNotfcnId = applcnBatchNotfcnId;
	}

	@Basic()
	@Column(name="EMAIL_ADDRS_TXT", nullable=false, length=100)
	public String getEmailAddrsTxt() {
		return this.emailAddrsTxt;
	}
	public void setEmailAddrsTxt(String emailAddrsTxt) {
		this.emailAddrsTxt = emailAddrsTxt;
	}

	//bi-directional many-to-one association to ApplcnBatch
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BATCH_ID", referencedColumnName="APPLCN_BATCH_ID", nullable=false)
	public ApplcnBatch getApplcnBatch() {
		return this.applcnBatch;
	}
	public void setApplcnBatch(ApplcnBatch applcnBatch) {
		this.applcnBatch = applcnBatch;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnBatchNotfcn)) {
			return false;
		}
		ApplcnBatchNotfcn castOther = (ApplcnBatchNotfcn)other;
		return new EqualsBuilder()
			.append(this.getApplcnBatchNotfcnId(), castOther.getApplcnBatchNotfcnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnBatchNotfcnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnBatchNotfcnId", getApplcnBatchNotfcnId())
			.toString();
	}
}