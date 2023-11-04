package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the PTCPNT_DISPTN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_DISPTN")
public class PtcpntDisptn  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntDisptnPK compId;
	private Integer disptnDuratnNbr;
	private String disptnReasonTypeNm;
	private Date dt;
	private Date endDt;
	private String ptcpntDisptnTypeCd;
	private String ptcpntDtTypeNm;
	private Ptcpnt ptcpnt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntDisptn() {
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
	
	@EmbeddedId
	public PtcpntDisptnPK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntDisptnPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="DISPTN_DURATN_NBR", precision=5)
	public Integer getDisptnDuratnNbr() {
		return this.disptnDuratnNbr;
	}
	public void setDisptnDuratnNbr(Integer disptnDuratnNbr) {
		this.disptnDuratnNbr = disptnDuratnNbr;
	}

	@Basic()
	@Column(name="DISPTN_REASON_TYPE_NM", nullable=false, length=50)
	public String getDisptnReasonTypeNm() {
		return this.disptnReasonTypeNm;
	}
	public void setDisptnReasonTypeNm(String disptnReasonTypeNm) {
		this.disptnReasonTypeNm = disptnReasonTypeNm;
	}

	@Basic()
	@Column(name="DT", length=7)
	public Date getDt() {
		return this.dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
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
	@Column(name="PTCPNT_DISPTN_TYPE_CD", nullable=false, length=12)
	public String getPtcpntDisptnTypeCd() {
		return this.ptcpntDisptnTypeCd;
	}
	public void setPtcpntDisptnTypeCd(String ptcpntDisptnTypeCd) {
		this.ptcpntDisptnTypeCd = ptcpntDisptnTypeCd;
	}

	@Basic()
	@Column(name="PTCPNT_DT_TYPE_NM", length=50)
	public String getPtcpntDtTypeNm() {
		return this.ptcpntDtTypeNm;
	}
	public void setPtcpntDtTypeNm(String ptcpntDtTypeNm) {
		this.ptcpntDtTypeNm = ptcpntDtTypeNm;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt() {
		return this.ptcpnt;
	}
	public void setPtcpnt(Ptcpnt ptcpnt) {
		this.ptcpnt = ptcpnt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntDisptn)) {
			return false;
		}
		PtcpntDisptn castOther = (PtcpntDisptn)other;
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