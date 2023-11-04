package gov.va.vba.framework.domain.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PERSON_SCRTY_LEVEL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_SCRTY_LEVEL")
public class PersonScrtyLevel implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PersonScrtyLevelPK compId;
	private Date beginDt;
	private String dgnstcSuprsnInd;
	private Date endDt;
	private String nextScrtyLevelTypeCd;
	private boolean scrtyLevelRetntnQty;
	private String scrtyLevelTypeCd;
	private Person person;
	private java.util.Set<ScrtyLevelLcStatus> scrtyLevelLcStatuses;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonScrtyLevel() {
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
	public PersonScrtyLevelPK getCompId() {
		return this.compId;
	}
	public void setCompId(PersonScrtyLevelPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BEGIN_DT", nullable=false, length=7)
	public Date getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	@Basic()
	@Column(name="DGNSTC_SUPRSN_IND", length=1)
	public String getDgnstcSuprsnInd() {
		return this.dgnstcSuprsnInd;
	}
	public void setDgnstcSuprsnInd(String dgnstcSuprsnInd) {
		this.dgnstcSuprsnInd = dgnstcSuprsnInd;
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
	@Column(name="NEXT_SCRTY_LEVEL_TYPE_CD", length=12)
	public String getNextScrtyLevelTypeCd() {
		return this.nextScrtyLevelTypeCd;
	}
	public void setNextScrtyLevelTypeCd(String nextScrtyLevelTypeCd) {
		this.nextScrtyLevelTypeCd = nextScrtyLevelTypeCd;
	}

	@Basic()
	@Column(name="SCRTY_LEVEL_RETNTN_QTY", nullable=false, precision=1)
	public boolean getScrtyLevelRetntnQty() {
		return this.scrtyLevelRetntnQty;
	}
	public void setScrtyLevelRetntnQty(boolean scrtyLevelRetntnQty) {
		this.scrtyLevelRetntnQty = scrtyLevelRetntnQty;
	}

	@Basic()
	@Column(name="SCRTY_LEVEL_TYPE_CD", nullable=false, length=12)
	public String getScrtyLevelTypeCd() {
		return this.scrtyLevelTypeCd;
	}
	public void setScrtyLevelTypeCd(String scrtyLevelTypeCd) {
		this.scrtyLevelTypeCd = scrtyLevelTypeCd;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false, insertable=false, updatable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	//bi-directional many-to-one association to ScrtyLevelLcStatus
	@OneToMany(mappedBy="personScrtyLevel", fetch=FetchType.LAZY)
	public java.util.Set<ScrtyLevelLcStatus> getScrtyLevelLcStatuses() {
		return this.scrtyLevelLcStatuses;
	}
	public void setScrtyLevelLcStatuses(java.util.Set<ScrtyLevelLcStatus> scrtyLevelLcStatuses) {
		this.scrtyLevelLcStatuses = scrtyLevelLcStatuses;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonScrtyLevel)) {
			return false;
		}
		PersonScrtyLevel castOther = (PersonScrtyLevel)other;
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