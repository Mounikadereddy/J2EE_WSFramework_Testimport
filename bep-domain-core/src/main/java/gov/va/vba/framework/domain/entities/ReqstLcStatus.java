package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the REQST_LC_STATUS database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="REQST_LC_STATUS")
public class ReqstLcStatus implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ReqstLcStatusPK compId;
	private String reqstLogonNm;
	private String reqstNm;
	private String reqstStatusTypeNm;
	private String rmksTxt;
	private Reqst reqst;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ReqstLcStatus() {
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
	
	@EmbeddedId
	public ReqstLcStatusPK getCompId() {
		return this.compId;
	}
	public void setCompId(ReqstLcStatusPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="REQST_LOGON_NM", nullable=false, length=15)
	public String getReqstLogonNm() {
		return this.reqstLogonNm;
	}
	public void setReqstLogonNm(String reqstLogonNm) {
		this.reqstLogonNm = reqstLogonNm;
	}

	@Basic()
	@Column(name="REQST_NM", nullable=false, length=50)
	public String getReqstNm() {
		return this.reqstNm;
	}
	public void setReqstNm(String reqstNm) {
		this.reqstNm = reqstNm;
	}

	@Basic()
	@Column(name="REQST_STATUS_TYPE_NM", nullable=false, length=50)
	public String getReqstStatusTypeNm() {
		return this.reqstStatusTypeNm;
	}
	public void setReqstStatusTypeNm(String reqstStatusTypeNm) {
		this.reqstStatusTypeNm = reqstStatusTypeNm;
	}

	@Basic()
	@Column(name="RMKS_TXT", length=254)
	public String getRmksTxt() {
		return this.rmksTxt;
	}
	public void setRmksTxt(String rmksTxt) {
		this.rmksTxt = rmksTxt;
	}

	//bi-directional many-to-one association to Reqst
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REQST_ID", referencedColumnName="REQST_ID", nullable=false, insertable=false, updatable=false)
	public Reqst getReqst() {
		return this.reqst;
	}
	public void setReqst(Reqst reqst) {
		this.reqst = reqst;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqstLcStatus)) {
			return false;
		}
		ReqstLcStatus castOther = (ReqstLcStatus)other;
		return new EqualsBuilder()
			.append(this.getCompId(), castOther.getCompId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCompId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("compId", getCompId())
			.toString();
	}
}