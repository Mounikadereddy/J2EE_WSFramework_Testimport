package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the OPERTN_RULE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="OPERTN_RULE")
public class OpertnRule implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long opertnRuleId;
	private String defltValueTxt;
	private String descpTxt;
	private String dsablInd;
	private String dsplyModeTypeCd;
	private Date efctvDt;
	private Date endDt;
	private String opertnRuleTypeNm;
	private String opertnTitleTxt;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public OpertnRule() {
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
	@Column(name="OPERTN_RULE_ID", unique=true, nullable=false, precision=15)
	public Long getOpertnRuleId() {
		return this.opertnRuleId;
	}
	public void setOpertnRuleId(Long opertnRuleId) {
		this.opertnRuleId = opertnRuleId;
	}

	@Basic()
	@Column(name="DEFLT_VALUE_TXT", length=12)
	public String getDefltValueTxt() {
		return this.defltValueTxt;
	}
	public void setDefltValueTxt(String defltValueTxt) {
		this.defltValueTxt = defltValueTxt;
	}

	@Basic()
	@Column(name="DESCP_TXT", length=254)
	public String getDescpTxt() {
		return this.descpTxt;
	}
	public void setDescpTxt(String descpTxt) {
		this.descpTxt = descpTxt;
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
	@Column(name="DSPLY_MODE_TYPE_CD", length=12)
	public String getDsplyModeTypeCd() {
		return this.dsplyModeTypeCd;
	}
	public void setDsplyModeTypeCd(String dsplyModeTypeCd) {
		this.dsplyModeTypeCd = dsplyModeTypeCd;
	}

	@Basic()
	@Column(name="EFCTV_DT", length=7)
	public Date getEfctvDt() {
		return this.efctvDt;
	}
	public void setEfctvDt(Date efctvDt) {
		this.efctvDt = efctvDt;
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
	@Column(name="OPERTN_RULE_TYPE_NM", nullable=false, length=50)
	public String getOpertnRuleTypeNm() {
		return this.opertnRuleTypeNm;
	}
	public void setOpertnRuleTypeNm(String opertnRuleTypeNm) {
		this.opertnRuleTypeNm = opertnRuleTypeNm;
	}

	@Basic()
	@Column(name="OPERTN_TITLE_TXT", length=25)
	public String getOpertnTitleTxt() {
		return this.opertnTitleTxt;
	}
	public void setOpertnTitleTxt(String opertnTitleTxt) {
		this.opertnTitleTxt = opertnTitleTxt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OpertnRule)) {
			return false;
		}
		OpertnRule castOther = (OpertnRule)other;
		return new EqualsBuilder()
			.append(this.getOpertnRuleId(), castOther.getOpertnRuleId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getOpertnRuleId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("opertnRuleId", getOpertnRuleId())
			.toString();
	}
}