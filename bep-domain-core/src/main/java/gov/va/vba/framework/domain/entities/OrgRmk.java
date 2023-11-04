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
 * The persistent class for the ORG_RMKS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="ORG_RMKS")
public class OrgRmk  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long orgRmksId;
	private Date rmksDt;
	private long rmksPtcpntId;
	private String rmksTxt;
	private Org org;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public OrgRmk() {
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
	@Column(name="ORG_RMKS_ID", unique=true, nullable=false, precision=15)
	public Long getOrgRmksId() {
		return this.orgRmksId;
	}
	public void setOrgRmksId(Long orgRmksId) {
		this.orgRmksId = orgRmksId;
	}

	@Basic()
	@Column(name="RMKS_DT", length=7)
	public Date getRmksDt() {
		return this.rmksDt;
	}
	public void setRmksDt(Date rmksDt) {
		this.rmksDt = rmksDt;
	}

	@Basic()
	@Column(name="RMKS_PTCPNT_ID", nullable=false, precision=15)
	public long getRmksPtcpntId() {
		return this.rmksPtcpntId;
	}
	public void setRmksPtcpntId(long rmksPtcpntId) {
		this.rmksPtcpntId = rmksPtcpntId;
	}

	@Basic()
	@Column(name="RMKS_TXT", length=512)
	public String getRmksTxt() {
		return this.rmksTxt;
	}
	public void setRmksTxt(String rmksTxt) {
		this.rmksTxt = rmksTxt;
	}

	//bi-directional many-to-one association to Org
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ORG_PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Org getOrg() {
		return this.org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrgRmk)) {
			return false;
		}
		OrgRmk castOther = (OrgRmk)other;
		return new EqualsBuilder()
			.append(this.getOrgRmksId(), castOther.getOrgRmksId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOrgRmksId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("orgRmksId", getOrgRmksId())
			.toString();
	}
}