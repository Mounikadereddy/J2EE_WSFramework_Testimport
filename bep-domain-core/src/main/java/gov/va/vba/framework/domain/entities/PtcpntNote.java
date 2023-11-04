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
 * The persistent class for the PTCPNT_NOTE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_NOTE")
public class PtcpntNote implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntNotePK compId;
	private Long bnftClaimId;
	private Date modifdDt;
	private String ptcpntNoteTypeNm;
	private String txt;
	private Ptcpnt ptcpnt1;
	private Ptcpnt ptcpnt2;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PtcpntNote() {
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
	public PtcpntNotePK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntNotePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BNFT_CLAIM_ID", precision=15)
	public Long getBnftClaimId() {
		return this.bnftClaimId;
	}
	public void setBnftClaimId(Long bnftClaimId) {
		this.bnftClaimId = bnftClaimId;
	}

	@Basic()
	@Column(name="MODIFD_DT", length=7)
	public Date getModifdDt() {
		return this.modifdDt;
	}
	public void setModifdDt(Date modifdDt) {
		this.modifdDt = modifdDt;
	}

	@Basic()
	@Column(name="PTCPNT_NOTE_TYPE_NM", nullable=false, length=50)
	public String getPtcpntNoteTypeNm() {
		return this.ptcpntNoteTypeNm;
	}
	public void setPtcpntNoteTypeNm(String ptcpntNoteTypeNm) {
		this.ptcpntNoteTypeNm = ptcpntNoteTypeNm;
	}

	@Basic()
	@Column(name="TXT", nullable=false, length=2000)
	public String getTxt() {
		return this.txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_EMP_ID", referencedColumnName="PTCPNT_ID")
	public Ptcpnt getPtcpnt1() {
		return this.ptcpnt1;
	}
	public void setPtcpnt1(Ptcpnt ptcpnt1) {
		this.ptcpnt1 = ptcpnt1;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Ptcpnt getPtcpnt2() {
		return this.ptcpnt2;
	}
	public void setPtcpnt2(Ptcpnt ptcpnt2) {
		this.ptcpnt2 = ptcpnt2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntNote)) {
			return false;
		}
		PtcpntNote castOther = (PtcpntNote)other;
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