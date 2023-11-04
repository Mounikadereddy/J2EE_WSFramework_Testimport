package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The persistent class for the SMC_PRGRPH_DATA_VALUES database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SMC_PRGRPH_DATA_VALUES")
public class SmcPrgrphDataValue  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private SmcPrgrphDataValuePK compId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private java.sql.Timestamp smcPrgrphExprtnDt;
	private Long smcPrgrphRlnshpId;
	private String smcPrgrphValueDescTxt;
	private SmcPrgrphDataElmnt smcPrgrphDataElmnt;

    public SmcPrgrphDataValue() {
    }

	@EmbeddedId
	public SmcPrgrphDataValuePK getCompId() {
		return this.compId;
	}
	public void setCompId(SmcPrgrphDataValuePK compId) {
		this.compId = compId;
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

	@Basic()
	@Column(name="SMC_PRGRPH_EXPRTN_DT", length=7)
	public java.sql.Timestamp getSmcPrgrphExprtnDt() {
		return this.smcPrgrphExprtnDt;
	}
	public void setSmcPrgrphExprtnDt(java.sql.Timestamp smcPrgrphExprtnDt) {
		this.smcPrgrphExprtnDt = smcPrgrphExprtnDt;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_RLNSHP_ID", precision=15)
	public Long getSmcPrgrphRlnshpId() {
		return this.smcPrgrphRlnshpId;
	}
	public void setSmcPrgrphRlnshpId(Long smcPrgrphRlnshpId) {
		this.smcPrgrphRlnshpId = smcPrgrphRlnshpId;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_VALUE_DESC_TXT", length=254)
	public String getSmcPrgrphValueDescTxt() {
		return this.smcPrgrphValueDescTxt;
	}
	public void setSmcPrgrphValueDescTxt(String smcPrgrphValueDescTxt) {
		this.smcPrgrphValueDescTxt = smcPrgrphValueDescTxt;
	}

	//bi-directional many-to-one association to SmcPrgrphDataElmnt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="SMC_PRGRPH_KEY_CD", referencedColumnName="SMC_PRGRPH_KEY_CD", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="SMC_PRGRPH_EFCTV_DT", referencedColumnName="SMC_PRGRPH_EFCTV_DT", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="SMC_PRGRPH_CNTRL_NM", referencedColumnName="SMC_PRGRPH_CNTRL_NM", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="SMC_PRGRPH_DATA_ID", referencedColumnName="SMC_PRGRPH_DATA_ID", nullable=false, insertable=false, updatable=false)
		})
	public SmcPrgrphDataElmnt getSmcPrgrphDataElmnt() {
		return this.smcPrgrphDataElmnt;
	}
	public void setSmcPrgrphDataElmnt(SmcPrgrphDataElmnt smcPrgrphDataElmnt) {
		this.smcPrgrphDataElmnt = smcPrgrphDataElmnt;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmcPrgrphDataValue)) {
			return false;
		}
		SmcPrgrphDataValue castOther = (SmcPrgrphDataValue)other;
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