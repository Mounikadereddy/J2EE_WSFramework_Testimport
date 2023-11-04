package gov.va.vba.framework.domain.entities;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_OPERTN_RULE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_OPERTN_RULE")
public class ApplcnOpertnRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ApplcnOpertnRulePK compId;
	private String asgndValueTxt;
	private String dsablInd;
	private int seqQty;
	private java.util.Set<ApplcnOpertnDepncyRule> applcnOpertnDepncyRules1;
	private java.util.Set<ApplcnOpertnDepncyRule> applcnOpertnDepncyRules2;
	private java.util.Set<ApplcnOpertnPrfil> applcnOpertnPrfils;
	private Applcn applcn;
	private OpertnRule opertnRule;
	private java.util.Set<PersonApplcnOpertn> personApplcnOpertns;
	private java.util.Set<StnApplcnOpertnRule> stnApplcnOpertnRules;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnOpertnRule() {
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
	public ApplcnOpertnRulePK getCompId() {
		return this.compId;
	}
	public void setCompId(ApplcnOpertnRulePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ASGND_VALUE_TXT", length=12)
	public String getAsgndValueTxt() {
		return this.asgndValueTxt;
	}
	public void setAsgndValueTxt(String asgndValueTxt) {
		this.asgndValueTxt = asgndValueTxt;
	}

	@Basic()
	@Column(name="DSABL_IND", length=1)
	public String getDsablInd() {
		return this.dsablInd;
	}
	public void setDsablInd(String dsablInd) {
		this.dsablInd = dsablInd;
	}

	@Basic()
	@Column(name="SEQ_QTY", nullable=false, precision=3)
	public int getSeqQty() {
		return this.seqQty;
	}
	public void setSeqQty(int seqQty) {
		this.seqQty = seqQty;
	}

	//bi-directional many-to-one association to ApplcnOpertnDepncyRule
	@OneToMany(mappedBy="applcnOpertnRule1", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnOpertnDepncyRule> getApplcnOpertnDepncyRules1() {
		return this.applcnOpertnDepncyRules1;
	}
	public void setApplcnOpertnDepncyRules1(java.util.Set<ApplcnOpertnDepncyRule> applcnOpertnDepncyRules1) {
		this.applcnOpertnDepncyRules1 = applcnOpertnDepncyRules1;
	}

	//bi-directional many-to-one association to ApplcnOpertnDepncyRule
	@OneToMany(mappedBy="applcnOpertnRule2", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnOpertnDepncyRule> getApplcnOpertnDepncyRules2() {
		return this.applcnOpertnDepncyRules2;
	}
	public void setApplcnOpertnDepncyRules2(java.util.Set<ApplcnOpertnDepncyRule> applcnOpertnDepncyRules2) {
		this.applcnOpertnDepncyRules2 = applcnOpertnDepncyRules2;
	}

	//bi-directional many-to-one association to ApplcnOpertnPrfil
	@OneToMany(mappedBy="applcnOpertnRule", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnOpertnPrfil> getApplcnOpertnPrfils() {
		return this.applcnOpertnPrfils;
	}
	public void setApplcnOpertnPrfils(java.util.Set<ApplcnOpertnPrfil> applcnOpertnPrfils) {
		this.applcnOpertnPrfils = applcnOpertnPrfils;
	}

	//bi-directional many-to-one association to Applcn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false)
	public Applcn getApplcn() {
		return this.applcn;
	}
	public void setApplcn(Applcn applcn) {
		this.applcn = applcn;
	}

	//bi-directional many-to-one association to OpertnRule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OPERTN_RULE_ID", referencedColumnName="OPERTN_RULE_ID", nullable=false, insertable=false, updatable=false)
	public OpertnRule getOpertnRule() {
		return this.opertnRule;
	}
	public void setOpertnRule(OpertnRule opertnRule) {
		this.opertnRule = opertnRule;
	}

	//bi-directional many-to-one association to PersonApplcnOpertn
	@OneToMany(mappedBy="applcnOpertnRule", fetch=FetchType.LAZY)
	public java.util.Set<PersonApplcnOpertn> getPersonApplcnOpertns() {
		return this.personApplcnOpertns;
	}
	public void setPersonApplcnOpertns(java.util.Set<PersonApplcnOpertn> personApplcnOpertns) {
		this.personApplcnOpertns = personApplcnOpertns;
	}

	//bi-directional many-to-one association to StnApplcnOpertnRule
	@OneToMany(mappedBy="applcnOpertnRule", fetch=FetchType.LAZY)
	public java.util.Set<StnApplcnOpertnRule> getStnApplcnOpertnRules() {
		return this.stnApplcnOpertnRules;
	}
	public void setStnApplcnOpertnRules(java.util.Set<StnApplcnOpertnRule> stnApplcnOpertnRules) {
		this.stnApplcnOpertnRules = stnApplcnOpertnRules;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnOpertnRule)) {
			return false;
		}
		ApplcnOpertnRule castOther = (ApplcnOpertnRule)other;
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