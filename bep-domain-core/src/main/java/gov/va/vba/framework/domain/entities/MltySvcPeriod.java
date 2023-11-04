package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the MLTY_SVC_PERIOD database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="MLTY_SVC_PERIOD")
public class MltySvcPeriod implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private MltySvcPeriodPK compId;
	private java.sql.Date endDt;
	private java.util.Set<MltySvcPeriodExcptn> mltySvcPeriodExcptns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public MltySvcPeriod() {
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
	public MltySvcPeriodPK getCompId() {
		return this.compId;
	}
	public void setCompId(MltySvcPeriodPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="END_DT", length=7)
	public java.sql.Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(java.sql.Date endDt) {
		this.endDt = endDt;
	}

	//bi-directional many-to-one association to MltySvcPeriodExcptn
	@OneToMany(mappedBy="mltySvcPeriod", fetch=FetchType.EAGER)
	public java.util.Set<MltySvcPeriodExcptn> getMltySvcPeriodExcptns() {
		return this.mltySvcPeriodExcptns;
	}
	public void setMltySvcPeriodExcptns(java.util.Set<MltySvcPeriodExcptn> mltySvcPeriodExcptns) {
		this.mltySvcPeriodExcptns = mltySvcPeriodExcptns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MltySvcPeriod)) {
			return false;
		}
		MltySvcPeriod castOther = (MltySvcPeriod)other;
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