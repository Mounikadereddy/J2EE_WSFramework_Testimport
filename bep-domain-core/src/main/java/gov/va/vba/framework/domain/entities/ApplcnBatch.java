package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

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
 * The persistent class for the APPLCN_BATCH database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_BATCH")
public class ApplcnBatch implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnBatchId;
	private String batchNm;
	private String notfcnChngdInd;
	private Applcn applcn;
	private java.util.Set<ApplcnBatchNotfcn> applcnBatchNotfcns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnBatch() {
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
	@Column(name="APPLCN_BATCH_ID", unique=true, nullable=false, precision=15)
	public Long getApplcnBatchId() {
		return this.applcnBatchId;
	}
	public void setApplcnBatchId(Long applcnBatchId) {
		this.applcnBatchId = applcnBatchId;
	}

	@Basic()
	@Column(name="BATCH_NM", nullable=false, length=50)
	public String getBatchNm() {
		return this.batchNm;
	}
	public void setBatchNm(String batchNm) {
		this.batchNm = batchNm;
	}

	@Basic()
	@Column(name="NOTFCN_CHNGD_IND", nullable=false, length=1)
	public String getNotfcnChngdInd() {
		return this.notfcnChngdInd;
	}
	public void setNotfcnChngdInd(String notfcnChngdInd) {
		this.notfcnChngdInd = notfcnChngdInd;
	}

	//bi-directional many-to-one association to Applcn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false)
	public Applcn getApplcn() {
		return this.applcn;
	}
	public void setApplcn(Applcn applcn) {
		this.applcn = applcn;
	}

	//bi-directional many-to-one association to ApplcnBatchNotfcn
	@OneToMany(mappedBy="applcnBatch", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnBatchNotfcn> getApplcnBatchNotfcns() {
		return this.applcnBatchNotfcns;
	}
	public void setApplcnBatchNotfcns(java.util.Set<ApplcnBatchNotfcn> applcnBatchNotfcns) {
		this.applcnBatchNotfcns = applcnBatchNotfcns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnBatch)) {
			return false;
		}
		ApplcnBatch castOther = (ApplcnBatch)other;
		return new EqualsBuilder()
			.append(this.getApplcnBatchId(), castOther.getApplcnBatchId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnBatchId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnBatchId", getApplcnBatchId())
			.toString();
	}
}