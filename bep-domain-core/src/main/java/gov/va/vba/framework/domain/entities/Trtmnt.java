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
 * The persistent class for the TRTMNT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="TRTMNT")
public class Trtmnt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long trtmntId;
	private Date beginDt;
	private String beginTxt;
	private long cntntnId;
	private Date endDt;
	private String endTxt;
	private ClaimDvlpmtCntct claimDvlpmtCntct;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public Trtmnt() {
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
	@Column(name="TRTMNT_ID", unique=true, nullable=false, precision=15)
	public Long getTrtmntId() {
		return this.trtmntId;
	}
	public void setTrtmntId(Long trtmntId) {
		this.trtmntId = trtmntId;
	}

	@Basic()
	@Column(name="BEGIN_DT", length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="BEGIN_TXT", length=15)
	public String getBeginTxt() {
		return this.beginTxt;
	}
	public void setBeginTxt(String beginTxt) {
		this.beginTxt = beginTxt;
	}

	@Basic()
	@Column(name="CNTNTN_ID", nullable=false, precision=15)
	public long getCntntnId() {
		return this.cntntnId;
	}
	public void setCntntnId(long cntntnId) {
		this.cntntnId = cntntnId;
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
	@Column(name="END_TXT", length=15)
	public String getEndTxt() {
		return this.endTxt;
	}
	public void setEndTxt(String endTxt) {
		this.endTxt = endTxt;
	}

	//bi-directional many-to-one association to ClaimDvlpmtCntct
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public ClaimDvlpmtCntct getClaimDvlpmtCntct() {
		return this.claimDvlpmtCntct;
	}
	public void setClaimDvlpmtCntct(ClaimDvlpmtCntct claimDvlpmtCntct) {
		this.claimDvlpmtCntct = claimDvlpmtCntct;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Trtmnt)) {
			return false;
		}
		Trtmnt castOther = (Trtmnt)other;
		return new EqualsBuilder()
			.append(this.getTrtmntId(), castOther.getTrtmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getTrtmntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("trtmntId", getTrtmntId())
			.toString();
	}
}