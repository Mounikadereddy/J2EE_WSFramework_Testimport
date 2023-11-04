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
 * The persistent class for the PTCPNT_PHONE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_PHONE")
public class PtcpntPhone implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntPhonePK compId;
	private Integer areaNbr;
	private Integer cntryNbr;
	private java.util.Date endDt;
	private Integer extnsnNbr;
	private String frgnPhoneRfrncTxt;
	private long phoneNbr;
	private Ptcpnt ptcpnt;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntPhone() {
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
	public PtcpntPhonePK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntPhonePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="AREA_NBR", precision=4)
	public Integer getAreaNbr() {
		return this.areaNbr;
	}
	public void setAreaNbr(Integer areaNbr) {
		this.areaNbr = areaNbr;
	}

	@Basic()
	@Column(name="CNTRY_NBR", precision=4)
	public Integer getCntryNbr() {
		return this.cntryNbr;
	}
	public void setCntryNbr(Integer cntryNbr) {
		this.cntryNbr = cntryNbr;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.util.Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.util.Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="EXTNSN_NBR", precision=4)
	public Integer getExtnsnNbr() {
		return this.extnsnNbr;
	}
	public void setExtnsnNbr(Integer extnsnNbr) {
		this.extnsnNbr = extnsnNbr;
	}

	@Basic()
	@Column(name="FRGN_PHONE_RFRNC_TXT", length=30)
	public String getFrgnPhoneRfrncTxt() {
		return this.frgnPhoneRfrncTxt;
	}
	public void setFrgnPhoneRfrncTxt(String frgnPhoneRfrncTxt) {
		this.frgnPhoneRfrncTxt = frgnPhoneRfrncTxt;
	}

	@Basic()
	@Column(name="PHONE_NBR", nullable=false, precision=11)
	public long getPhoneNbr() {
		return this.phoneNbr;
	}
	public void setPhoneNbr(long phoneNbr) {
		this.phoneNbr = phoneNbr;
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
		if (!(other instanceof PtcpntPhone)) {
			return false;
		}
		PtcpntPhone castOther = (PtcpntPhone)other;
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