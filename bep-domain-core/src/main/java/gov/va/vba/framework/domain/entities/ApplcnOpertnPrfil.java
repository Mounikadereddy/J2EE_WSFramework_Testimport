package gov.va.vba.framework.domain.entities;
import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_OPERTN_PRFIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_OPERTN_PRFIL")
public class ApplcnOpertnPrfil implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ApplcnOpertnPrfilPK compId;
	private String asgndValueTxt;
	private Date createDt;
	private ApplcnOpertnRule applcnOpertnRule;
	private GroupPrfil groupPrfil;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
	
    public ApplcnOpertnPrfil() {
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
	public ApplcnOpertnPrfilPK getCompId() {
		return this.compId;
	}
	public void setCompId(ApplcnOpertnPrfilPK compId) {
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
	@Column(name="CREATE_DT", nullable=false, length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getCreateDt() {
		return this.createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	//bi-directional many-to-one association to ApplcnOpertnRule
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="OPERTN_RULE_ID", referencedColumnName="OPERTN_RULE_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false)
		})
	public ApplcnOpertnRule getApplcnOpertnRule() {
		return this.applcnOpertnRule;
	}
	public void setApplcnOpertnRule(ApplcnOpertnRule applcnOpertnRule) {
		this.applcnOpertnRule = applcnOpertnRule;
	}

	//bi-directional many-to-one association to GroupPrfil
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GROUP_PRFIL_ID", referencedColumnName="GROUP_PRFIL_ID", nullable=false, insertable=false, updatable=false)
	public GroupPrfil getGroupPrfil() {
		return this.groupPrfil;
	}
	public void setGroupPrfil(GroupPrfil groupPrfil) {
		this.groupPrfil = groupPrfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnOpertnPrfil)) {
			return false;
		}
		ApplcnOpertnPrfil castOther = (ApplcnOpertnPrfil)other;
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