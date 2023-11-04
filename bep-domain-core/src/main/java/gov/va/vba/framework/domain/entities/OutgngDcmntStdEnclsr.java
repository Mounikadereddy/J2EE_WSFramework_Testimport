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
 * The persistent class for the OUTGNG_DCMNT_STD_ENCLSR database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="OUTGNG_DCMNT_STD_ENCLSR")
public class OutgngDcmntStdEnclsr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long outgngDcmntStdEnclsrId;
	private Integer copiesQty;
	private OutgngDcmnt outgngDcmnt;
//	private StdEnclsr stdEnclsr;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public OutgngDcmntStdEnclsr() {
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
	@Column(name="OUTGNG_DCMNT_STD_ENCLSR_ID", unique=true, nullable=false, precision=15)
	public Long getOutgngDcmntStdEnclsrId() {
		return this.outgngDcmntStdEnclsrId;
	}
	public void setOutgngDcmntStdEnclsrId(Long outgngDcmntStdEnclsrId) {
		this.outgngDcmntStdEnclsrId = outgngDcmntStdEnclsrId;
	}

	@Basic()
	@Column(name="COPIES_QTY", precision=5)
	public Integer getCopiesQty() {
		return this.copiesQty;
	}
	public void setCopiesQty(Integer copiesQty) {
		this.copiesQty = copiesQty;
	}

	//bi-directional many-to-one association to OutgngDcmnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OUTGNG_DCMNT_ID", referencedColumnName="OUTGNG_DCMNT_ID", nullable=false)
	public OutgngDcmnt getOutgngDcmnt() {
		return this.outgngDcmnt;
	}
	public void setOutgngDcmnt(OutgngDcmnt outgngDcmnt) {
		this.outgngDcmnt = outgngDcmnt;
	}

	//bi-directional many-to-one association to StdEnclsr
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="STD_ENCLSR_ID", referencedColumnName="STD_ENCLSR_ID", nullable=false)
//	public StdEnclsr getStdEnclsr() {
//		return this.stdEnclsr;
//	}
//	public void setStdEnclsr(StdEnclsr stdEnclsr) {
//		this.stdEnclsr = stdEnclsr;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OutgngDcmntStdEnclsr)) {
			return false;
		}
		OutgngDcmntStdEnclsr castOther = (OutgngDcmntStdEnclsr)other;
		return new EqualsBuilder()
			.append(this.getOutgngDcmntStdEnclsrId(), castOther.getOutgngDcmntStdEnclsrId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOutgngDcmntStdEnclsrId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("outgngDcmntStdEnclsrId", getOutgngDcmntStdEnclsrId())
			.toString();
	}
}