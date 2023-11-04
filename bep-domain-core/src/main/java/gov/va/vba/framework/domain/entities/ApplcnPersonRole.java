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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the APPLCN_PERSON_ROLE database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="APPLCN_PERSON_ROLE")
public class ApplcnPersonRole implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private ApplcnPersonRolePK compId;
	private String aprvlStatusInd;
	private Date dactvtDt;
	private Date notfcnDt;
	private String statusTxt;
	private ApplcnRole applcnRole;
	private PersonScrtyLog personScrtyLog;
	private java.util.Set<PersonApplcnOpertn> personApplcnOpertns;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;
	
    public ApplcnPersonRole() {
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
	public ApplcnPersonRolePK getCompId() {
		return this.compId;
	}
	public void setCompId(ApplcnPersonRolePK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="APRVL_STATUS_IND", length=1)
	public String getAprvlStatusInd() {
		return this.aprvlStatusInd;
	}
	public void setAprvlStatusInd(String aprvlStatusInd) {
		this.aprvlStatusInd = aprvlStatusInd;
	}

	@Basic()
	@Column(name="DACTVT_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getDactvtDt() {
		return this.dactvtDt;
	}
	public void setDactvtDt(Date dactvtDt) {
		this.dactvtDt = dactvtDt;
	}

	@Basic()
	@Column(name="NOTFCN_DT", length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getNotfcnDt() {
		return this.notfcnDt;
	}
	public void setNotfcnDt(Date notfcnDt) {
		this.notfcnDt = notfcnDt;
	}

	@Basic()
	@Column(name="STATUS_TXT", length=254)
	public String getStatusTxt() {
		return this.statusTxt;
	}
	public void setStatusTxt(String statusTxt) {
		this.statusTxt = statusTxt;
	}

	//bi-directional many-to-one association to ApplcnRole
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="APPLCN_ID", referencedColumnName="APPLCN_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="ROLE_TYPE_NM", referencedColumnName="ROLE_TYPE_NM", nullable=false, insertable=false, updatable=false)
		})
	public ApplcnRole getApplcnRole() {
		return this.applcnRole;
	}
	public void setApplcnRole(ApplcnRole applcnRole) {
		this.applcnRole = applcnRole;
	}

	//bi-directional many-to-one association to PersonScrtyLog
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false),
		@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
		})
	public PersonScrtyLog getPersonScrtyLog() {
		return this.personScrtyLog;
	}
	public void setPersonScrtyLog(PersonScrtyLog personScrtyLog) {
		this.personScrtyLog = personScrtyLog;
	}

	//bi-directional many-to-one association to PersonApplcnOpertn
	@OneToMany(mappedBy="applcnPersonRole", fetch=FetchType.LAZY)
	public java.util.Set<PersonApplcnOpertn> getPersonApplcnOpertns() {
		return this.personApplcnOpertns;
	}
	public void setPersonApplcnOpertns(java.util.Set<PersonApplcnOpertn> personApplcnOpertns) {
		this.personApplcnOpertns = personApplcnOpertns;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplcnPersonRole)) {
			return false;
		}
		ApplcnPersonRole castOther = (ApplcnPersonRole)other;
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