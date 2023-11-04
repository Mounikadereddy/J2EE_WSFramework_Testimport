package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_OPERTN_DEPNCY_RULE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_OPERTN_DEPNCY_RULE")
public class ApplcnOpertnDepncyRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ApplcnOpertnDepncyRulePK compId;
	private String asgndValueTxtA;
	private String asgndValueTxtB;
	private String depncyInd;
	private String excptnInd;
	private Long ruleRlnshpTypeCd;
	private ApplcnOpertnRule applcnOpertnRule1;
	private ApplcnOpertnRule applcnOpertnRule2;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
	public ApplcnOpertnDepncyRule() {
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
	public ApplcnOpertnDepncyRulePK getCompId() {
		return this.compId;
	}
	public void setCompId(ApplcnOpertnDepncyRulePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ASGND_VALUE_TXT_A", length=12)
	public String getAsgndValueTxtA() {
		return this.asgndValueTxtA;
	}
	public void setAsgndValueTxtA(String asgndValueTxtA) {
		this.asgndValueTxtA = asgndValueTxtA;
	}

	@Basic()
	@Column(name="ASGND_VALUE_TXT_B", length=12)
	public String getAsgndValueTxtB() {
		return this.asgndValueTxtB;
	}
	public void setAsgndValueTxtB(String asgndValueTxtB) {
		this.asgndValueTxtB = asgndValueTxtB;
	}

	@Basic()
	@Column(name="DEPNCY_IND", length=1)
	public String getDepncyInd() {
		return this.depncyInd;
	}
	public void setDepncyInd(String depncyInd) {
		this.depncyInd = depncyInd;
	}

	@Basic()
	@Column(name="EXCPTN_IND", length=1)
	public String getExcptnInd() {
		return this.excptnInd;
	}
	public void setExcptnInd(String excptnInd) {
		this.excptnInd = excptnInd;
	}

	@Basic()
	@Column(name="RULE_RLNSHP_TYPE_CD", precision=12)
	public Long getRuleRlnshpTypeCd() {
		return this.ruleRlnshpTypeCd;
	}
	public void setRuleRlnshpTypeCd(Long ruleRlnshpTypeCd) {
		this.ruleRlnshpTypeCd = ruleRlnshpTypeCd;
	}

	//bi-directional many-to-one association to ApplcnOpertnRule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="OPERTN_RULE_ID_B", referencedColumnName="OPERTN_RULE_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="APPLCN_ID_B", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false)
		})
	public ApplcnOpertnRule getApplcnOpertnRule1() {
		return this.applcnOpertnRule1;
	}
	public void setApplcnOpertnRule1(ApplcnOpertnRule applcnOpertnRule1) {
		this.applcnOpertnRule1 = applcnOpertnRule1;
	}

	//bi-directional many-to-one association to ApplcnOpertnRule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="OPERTN_RULE_ID_A", referencedColumnName="OPERTN_RULE_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="APPLCN_ID_A", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false)
		})
	public ApplcnOpertnRule getApplcnOpertnRule2() {
		return this.applcnOpertnRule2;
	}
	public void setApplcnOpertnRule2(ApplcnOpertnRule applcnOpertnRule2) {
		this.applcnOpertnRule2 = applcnOpertnRule2;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnOpertnDepncyRule)) {
			return false;
		}
		ApplcnOpertnDepncyRule castOther = (ApplcnOpertnDepncyRule)other;
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