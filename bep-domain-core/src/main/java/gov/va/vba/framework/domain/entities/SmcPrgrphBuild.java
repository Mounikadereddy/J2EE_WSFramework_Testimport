package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The persistent class for the SMC_PRGRPH_BUILD database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SMC_PRGRPH_BUILD")
public class SmcPrgrphBuild  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private SmcPrgrphBuildPK compId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private java.sql.Timestamp smcPrgrphExprtnDt;
	private String smcPrgrphTxt;
	private java.util.Set<SmcPrgrphDataElmnt> smcPrgrphDataElmnts;

    public SmcPrgrphBuild() {
    }

	@EmbeddedId
	public SmcPrgrphBuildPK getCompId() {
		return this.compId;
	}
	public void setCompId(SmcPrgrphBuildPK compId) {
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
	@Column(name="SMC_PRGRPH_TXT", nullable=false, length=2000)
	public String getSmcPrgrphTxt() {
		return this.smcPrgrphTxt;
	}
	public void setSmcPrgrphTxt(String smcPrgrphTxt) {
		this.smcPrgrphTxt = smcPrgrphTxt;
	}

	//bi-directional many-to-one association to SmcPrgrphDataElmnt
	@OneToMany(mappedBy="smcPrgrphBuild", fetch=FetchType.LAZY)
	public java.util.Set<SmcPrgrphDataElmnt> getSmcPrgrphDataElmnts() {
		return this.smcPrgrphDataElmnts;
	}
	public void setSmcPrgrphDataElmnts(java.util.Set<SmcPrgrphDataElmnt> smcPrgrphDataElmnts) {
		this.smcPrgrphDataElmnts = smcPrgrphDataElmnts;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmcPrgrphBuild)) {
			return false;
		}
		SmcPrgrphBuild castOther = (SmcPrgrphBuild)other;
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