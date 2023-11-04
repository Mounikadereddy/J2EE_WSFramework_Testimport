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
 * The persistent class for the STN_LCKOUT_APPLCN database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="STN_LCKOUT_APPLCN")
public class StnLckoutApplcn implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private StnLckoutApplcnPK compId;
	private String globalInd;
	private String localInd;
	private Applcn applcn;
	private Stn stn;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public StnLckoutApplcn() {
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
	public StnLckoutApplcnPK getCompId() {
		return this.compId;
	}
	public void setCompId(StnLckoutApplcnPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="GLOBAL_IND", length=1)
	public String getGlobalInd() {
		return this.globalInd;
	}
	public void setGlobalInd(String globalInd) {
		this.globalInd = globalInd;
	}

	@Basic()
	@Column(name="LOCAL_IND", length=1)
	public String getLocalInd() {
		return this.localInd;
	}
	public void setLocalInd(String localInd) {
		this.localInd = localInd;
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
		if (!(other instanceof StnLckoutApplcn)) {
			return false;
		}
		StnLckoutApplcn castOther = (StnLckoutApplcn)other;
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