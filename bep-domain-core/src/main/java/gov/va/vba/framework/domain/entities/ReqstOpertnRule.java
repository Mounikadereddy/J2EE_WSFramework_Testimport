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
 * The persistent class for the REQST_OPERTN_RULE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="REQST_OPERTN_RULE")
public class ReqstOpertnRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ReqstOpertnRulePK compId;
	private String asgndValueTxt;
	private OpertnRule opertnRule;
	private Reqst reqst;
	private Stn stn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ReqstOpertnRule() {
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
	public ReqstOpertnRulePK getCompId() {
		return this.compId;
	}
	public void setCompId(ReqstOpertnRulePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ASGND_VALUE_TXT", nullable=false, length=12)
	public String getAsgndValueTxt() {
		return this.asgndValueTxt;
	}
	public void setAsgndValueTxt(String asgndValueTxt) {
		this.asgndValueTxt = asgndValueTxt;
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

	//bi-directional many-to-one association to Reqst
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REQST_ID", referencedColumnName="REQST_ID", nullable=false, insertable=false, updatable=false)
	public Reqst getReqst() {
		return this.reqst;
	}
	public void setReqst(Reqst reqst) {
		this.reqst = reqst;
	}

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ReqstOpertnRule)) {
			return false;
		}
		ReqstOpertnRule castOther = (ReqstOpertnRule)other;
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