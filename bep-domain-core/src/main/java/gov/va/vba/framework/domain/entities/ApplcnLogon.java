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
 * The persistent class for the APPLCN_LOGON database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_LOGON")
public class ApplcnLogon implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long applcnLogonId;
	private String logonSuccesInd;
	private String netwrkLogonNm;
	private Applcn applcn;
	private Stn stn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnLogon() {
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
	@Column(name="APPLCN_LOGON_ID", unique=true, nullable=false, precision=15)
	public Long getApplcnLogonId() {
		return this.applcnLogonId;
	}
	public void setApplcnLogonId(Long applcnLogonId) {
		this.applcnLogonId = applcnLogonId;
	}

	@Basic()
	@Column(name="LOGON_SUCCES_IND", length=1)
	public String getLogonSuccesInd() {
		return this.logonSuccesInd;
	}
	public void setLogonSuccesInd(String logonSuccesInd) {
		this.logonSuccesInd = logonSuccesInd;
	}

	@Basic()
	@Column(name="NETWRK_LOGON_NM", nullable=false, length=50)
	public String getNetwrkLogonNm() {
		return this.netwrkLogonNm;
	}
	public void setNetwrkLogonNm(String netwrkLogonNm) {
		this.netwrkLogonNm = netwrkLogonNm;
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
		if (!(other instanceof ApplcnLogon)) {
			return false;
		}
		ApplcnLogon castOther = (ApplcnLogon)other;
		return new EqualsBuilder()
			.append(this.getApplcnLogonId(), castOther.getApplcnLogonId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getApplcnLogonId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("applcnLogonId", getApplcnLogonId())
			.toString();
	}
}