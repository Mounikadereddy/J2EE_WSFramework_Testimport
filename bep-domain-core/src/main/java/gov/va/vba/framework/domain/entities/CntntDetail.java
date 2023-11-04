package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the CNTNT_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="CNTNT_DETAIL")
public class CntntDetail implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long cntntDetailId;
	private String cntntColumnNm;
	private String cntntTableNm;
	private String cntntTxt;
//	private java.util.Set<RbaPrfilIssue> rbaPrfilIssues1;
//	private java.util.Set<RbaPrfilIssue> rbaPrfilIssues2;
//	private java.util.Set<RbaPrfilIssue> rbaPrfilIssues3;
//	private java.util.Set<RbaPrfilIssue> rbaPrfilIssues4;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public CntntDetail() {
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
	@Column(name="CNTNT_DETAIL_ID", unique=true, nullable=false, precision=15)
	public Long getCntntDetailId() {
		return this.cntntDetailId;
	}
	public void setCntntDetailId(Long cntntDetailId) {
		this.cntntDetailId = cntntDetailId;
	}

	@Basic()
	@Column(name="CNTNT_COLUMN_NM", nullable=false, length=30)
	public String getCntntColumnNm() {
		return this.cntntColumnNm;
	}
	public void setCntntColumnNm(String cntntColumnNm) {
		this.cntntColumnNm = cntntColumnNm;
	}

	@Basic()
	@Column(name="CNTNT_TABLE_NM", nullable=false, length=30)
	public String getCntntTableNm() {
		return this.cntntTableNm;
	}
	public void setCntntTableNm(String cntntTableNm) {
		this.cntntTableNm = cntntTableNm;
	}

	@Basic()
	@Column(name="CNTNT_TXT")
	public String getCntntTxt() {
		return this.cntntTxt;
	}
	public void setCntntTxt(String cntntTxt) {
		this.cntntTxt = cntntTxt;
	}

	//bi-directional many-to-one association to RbaPrfilIssue
//	@OneToMany(mappedBy="cntntDetail1", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfilIssue> getRbaPrfilIssues1() {
//		return this.rbaPrfilIssues1;
//	}
//	public void setRbaPrfilIssues1(java.util.Set<RbaPrfilIssue> rbaPrfilIssues1) {
//		this.rbaPrfilIssues1 = rbaPrfilIssues1;
//	}
//
//	//bi-directional many-to-one association to RbaPrfilIssue
//	@OneToMany(mappedBy="cntntDetail2", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfilIssue> getRbaPrfilIssues2() {
//		return this.rbaPrfilIssues2;
//	}
//	public void setRbaPrfilIssues2(java.util.Set<RbaPrfilIssue> rbaPrfilIssues2) {
//		this.rbaPrfilIssues2 = rbaPrfilIssues2;
//	}
//
//	//bi-directional many-to-one association to RbaPrfilIssue
//	@OneToMany(mappedBy="cntntDetail3", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfilIssue> getRbaPrfilIssues3() {
//		return this.rbaPrfilIssues3;
//	}
//	public void setRbaPrfilIssues3(java.util.Set<RbaPrfilIssue> rbaPrfilIssues3) {
//		this.rbaPrfilIssues3 = rbaPrfilIssues3;
//	}
//
//	//bi-directional many-to-one association to RbaPrfilIssue
//	@OneToMany(mappedBy="cntntDetail4", fetch=FetchType.LAZY)
//	public java.util.Set<RbaPrfilIssue> getRbaPrfilIssues4() {
//		return this.rbaPrfilIssues4;
//	}
//	public void setRbaPrfilIssues4(java.util.Set<RbaPrfilIssue> rbaPrfilIssues4) {
//		this.rbaPrfilIssues4 = rbaPrfilIssues4;
//	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CntntDetail)) {
			return false;
		}
		CntntDetail castOther = (CntntDetail)other;
		return new EqualsBuilder()
			.append(this.getCntntDetailId(), castOther.getCntntDetailId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCntntDetailId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("cntntDetailId", getCntntDetailId())
			.toString();
	}
}