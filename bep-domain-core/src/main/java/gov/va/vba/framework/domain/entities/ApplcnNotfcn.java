package gov.va.vba.framework.domain.entities;
import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_NOTFCN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_NOTFCN")
public class ApplcnNotfcn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnNotfcnId;
	private String applcnModuleNm;
	private String applcnRlseNbr;
	private String comntTxt;
	private Date notfcnEfctvDt;
	private String notfcnPrirtyCd;
	private String notfcnTitleTxt;
	private String notfcnTypeCd;
	private Applcn applcn;
	private Stn stn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnNotfcn() {
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
	@Column(name="APPLCN_NOTFCN_ID", unique=true, nullable=false, precision=15)
	public Long getApplcnNotfcnId() {
		return this.applcnNotfcnId;
	}
	public void setApplcnNotfcnId(Long applcnNotfcnId) {
		this.applcnNotfcnId = applcnNotfcnId;
	}

	@Basic()
	@Column(name="APPLCN_MODULE_NM", length=15)
	public String getApplcnModuleNm() {
		return this.applcnModuleNm;
	}
	public void setApplcnModuleNm(String applcnModuleNm) {
		this.applcnModuleNm = applcnModuleNm;
	}

	@Basic()
	@Column(name="APPLCN_RLSE_NBR", length=15)
	public String getApplcnRlseNbr() {
		return this.applcnRlseNbr;
	}
	public void setApplcnRlseNbr(String applcnRlseNbr) {
		this.applcnRlseNbr = applcnRlseNbr;
	}

	@Basic()
	@Column(name="COMNT_TXT", length=2000)
	public String getComntTxt() {
		return this.comntTxt;
	}
	public void setComntTxt(String comntTxt) {
		this.comntTxt = comntTxt;
	}

	@Basic()
	@Column(name="NOTFCN_EFCTV_DT", nullable=false, length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getNotfcnEfctvDt() {
		return this.notfcnEfctvDt;
	}
	public void setNotfcnEfctvDt(Date notfcnEfctvDt) {
		this.notfcnEfctvDt = notfcnEfctvDt;
	}

	@Basic()
	@Column(name="NOTFCN_PRIRTY_CD", length=1)
	public String getNotfcnPrirtyCd() {
		return this.notfcnPrirtyCd;
	}
	public void setNotfcnPrirtyCd(String notfcnPrirtyCd) {
		this.notfcnPrirtyCd = notfcnPrirtyCd;
	}

	@Basic()
	@Column(name="NOTFCN_TITLE_TXT", length=50)
	public String getNotfcnTitleTxt() {
		return this.notfcnTitleTxt;
	}
	public void setNotfcnTitleTxt(String notfcnTitleTxt) {
		this.notfcnTitleTxt = notfcnTitleTxt;
	}

	@Basic()
	@Column(name="NOTFCN_TYPE_CD", nullable=false, length=12)
	public String getNotfcnTypeCd() {
		return this.notfcnTypeCd;
	}
	public void setNotfcnTypeCd(String notfcnTypeCd) {
		this.notfcnTypeCd = notfcnTypeCd;
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

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID")
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnNotfcn)) {
			return false;
		}
		ApplcnNotfcn castOther = (ApplcnNotfcn)other;
		return new EqualsBuilder()
			.append(this.getApplcnNotfcnId(), castOther.getApplcnNotfcnId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnNotfcnId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnNotfcnId", getApplcnNotfcnId())
			.toString();
	}
}