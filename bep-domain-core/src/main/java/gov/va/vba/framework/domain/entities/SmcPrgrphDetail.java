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
 * The persistent class for the SMC_PRGRPH_DETAIL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SMC_PRGRPH_DETAIL")
public class SmcPrgrphDetail  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private SmcPrgrphDetailPK compId;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	private Long smcPrgrphValueId;
	private String smcPrgrphValueTxt;
	private SmcPrgrph smcPrgrph;

    public SmcPrgrphDetail() {
    }

	@EmbeddedId
	public SmcPrgrphDetailPK getCompId() {
		return this.compId;
	}
	public void setCompId(SmcPrgrphDetailPK compId) {
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
	@Column(name="SMC_PRGRPH_VALUE_ID", precision=15)
	public Long getSmcPrgrphValueId() {
		return this.smcPrgrphValueId;
	}
	public void setSmcPrgrphValueId(Long smcPrgrphValueId) {
		this.smcPrgrphValueId = smcPrgrphValueId;
	}

	@Basic()
	@Column(name="SMC_PRGRPH_VALUE_TXT", length=2000)
	public String getSmcPrgrphValueTxt() {
		return this.smcPrgrphValueTxt;
	}
	public void setSmcPrgrphValueTxt(String smcPrgrphValueTxt) {
		this.smcPrgrphValueTxt = smcPrgrphValueTxt;
	}

	//bi-directional many-to-one association to SmcPrgrph
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RATING_DECN_ID", referencedColumnName="RATING_DECN_ID", nullable=false, insertable=false, updatable=false)
	public SmcPrgrph getSmcPrgrph() {
		return this.smcPrgrph;
	}
	public void setSmcPrgrph(SmcPrgrph smcPrgrph) {
		this.smcPrgrph = smcPrgrph;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SmcPrgrphDetail)) {
			return false;
		}
		SmcPrgrphDetail castOther = (SmcPrgrphDetail)other;
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