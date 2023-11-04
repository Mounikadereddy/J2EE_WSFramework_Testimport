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
 * The persistent class for the PTCPNT_DIARY database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PTCPNT_DIARY")
public class PtcpntDiary implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PtcpntDiaryPK compId;
	private String diaryTxt;
	private String ptcpntDtTypeNm;
	private Emp emp;
	private Ptcpnt ptcpnt;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public PtcpntDiary() {
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
	public PtcpntDiaryPK getCompId() {
		return this.compId;
	}
	public void setCompId(PtcpntDiaryPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="DIARY_TXT", length=254)
	public String getDiaryTxt() {
		return this.diaryTxt;
	}
	public void setDiaryTxt(String diaryTxt) {
		this.diaryTxt = diaryTxt;
	}

	@Basic()
	@Column(name="PTCPNT_DT_TYPE_NM", nullable=false, length=50)
	public String getPtcpntDtTypeNm() {
		return this.ptcpntDtTypeNm;
	}
	public void setPtcpntDtTypeNm(String ptcpntDtTypeNm) {
		this.ptcpntDtTypeNm = ptcpntDtTypeNm;
	}

	//bi-directional many-to-one association to Emp
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_B", referencedColumnName="PTCPNT_ID")
	public Emp getEmp() {
		return this.emp;
	}
	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	//bi-directional many-to-one association to Ptcpnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID_A", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
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
		if (!(other instanceof PtcpntDiary)) {
			return false;
		}
		PtcpntDiary castOther = (PtcpntDiary)other;
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