package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the SCREEN_OBJECT database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="SCREEN_OBJECT")
public class ScreenObject implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ScreenObjectPK compId;
	private String accessPrmsnTypeCd;
	private String screenObjectTypeNm;
	private Date versnDt;
	private OpertnRule opertnRule;
	private ScreenObject screenObject;
	private java.util.Set<ScreenObject> screenObjects;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public ScreenObject() {
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
	
	@EmbeddedId
	public ScreenObjectPK getCompId() {
		return this.compId;
	}
	public void setCompId(ScreenObjectPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="ACCESS_PRMSN_TYPE_CD", length=12)
	public String getAccessPrmsnTypeCd() {
		return this.accessPrmsnTypeCd;
	}
	public void setAccessPrmsnTypeCd(String accessPrmsnTypeCd) {
		this.accessPrmsnTypeCd = accessPrmsnTypeCd;
	}

	@Basic()
	@Column(name="SCREEN_OBJECT_TYPE_NM", nullable=false, length=50)
	public String getScreenObjectTypeNm() {
		return this.screenObjectTypeNm;
	}
	public void setScreenObjectTypeNm(String screenObjectTypeNm) {
		this.screenObjectTypeNm = screenObjectTypeNm;
	}

	@Basic()
	@Column(name="VERSN_DT", length=7)
	public Date getVersnDt() {
		return this.versnDt;
	}
	public void setVersnDt(Date versnDt) {
		this.versnDt = versnDt;
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

	//bi-directional many-to-one association to ScreenObject
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PARENT_OPERTN_RULE_ID", referencedColumnName="OPERTN_RULE_ID"),
		@JoinColumn(name="PARENT_OBJECT_NM", referencedColumnName="OBJECT_NM")
		})
	public ScreenObject getScreenObject() {
		return this.screenObject;
	}
	public void setScreenObject(ScreenObject screenObject) {
		this.screenObject = screenObject;
	}

	//bi-directional many-to-one association to ScreenObject
	@OneToMany(mappedBy="screenObject", fetch=FetchType.LAZY)
	public java.util.Set<ScreenObject> getScreenObjects() {
		return this.screenObjects;
	}
	public void setScreenObjects(java.util.Set<ScreenObject> screenObjects) {
		this.screenObjects = screenObjects;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ScreenObject)) {
			return false;
		}
		ScreenObject castOther = (ScreenObject)other;
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