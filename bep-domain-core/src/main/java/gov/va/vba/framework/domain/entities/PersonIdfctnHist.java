package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the PERSON_IDFCTN_HIST database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_IDFCTN_HIST")
public class PersonIdfctnHist implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long personIdfctnHistId;
	private Date changeDt;
	private String idfctnChangeReasonTypeCd;
	private String newFileNbr;
	private String newSsnNbr;
	private String oldFileNbr;
	private String oldSsnNbr;
	private Person person;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonIdfctnHist() {
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
	@Column(name="PERSON_IDFCTN_HIST_ID", unique=true, nullable=false, precision=15)
	public Long getPersonIdfctnHistId() {
		return this.personIdfctnHistId;
	}
	public void setPersonIdfctnHistId(Long personIdfctnHistId) {
		this.personIdfctnHistId = personIdfctnHistId;
	}

	@Basic()
	@Column(name="CHANGE_DT", nullable=false, length=7)
	public Date getChangeDt() {
		return this.changeDt;
	}
	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}

	@Basic()
	@Column(name="IDFCTN_CHANGE_REASON_TYPE_CD", nullable=false, length=12)
	public String getIdfctnChangeReasonTypeCd() {
		return this.idfctnChangeReasonTypeCd;
	}
	public void setIdfctnChangeReasonTypeCd(String idfctnChangeReasonTypeCd) {
		this.idfctnChangeReasonTypeCd = idfctnChangeReasonTypeCd;
	}

	@Basic()
	@Column(name="NEW_FILE_NBR", length=9)
	public String getNewFileNbr() {
		return this.newFileNbr;
	}
	public void setNewFileNbr(String newFileNbr) {
		this.newFileNbr = newFileNbr;
	}

	@Basic()
	@Column(name="NEW_SSN_NBR", length=9)
	public String getNewSsnNbr() {
		return this.newSsnNbr;
	}
	public void setNewSsnNbr(String newSsnNbr) {
		this.newSsnNbr = newSsnNbr;
	}

	@Basic()
	@Column(name="OLD_FILE_NBR", length=9)
	public String getOldFileNbr() {
		return this.oldFileNbr;
	}
	public void setOldFileNbr(String oldFileNbr) {
		this.oldFileNbr = oldFileNbr;
	}

	@Basic()
	@Column(name="OLD_SSN_NBR", length=9)
	public String getOldSsnNbr() {
		return this.oldSsnNbr;
	}
	public void setOldSsnNbr(String oldSsnNbr) {
		this.oldSsnNbr = oldSsnNbr;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PTCPNT_ID", referencedColumnName="PTCPNT_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonIdfctnHist)) {
			return false;
		}
		PersonIdfctnHist castOther = (PersonIdfctnHist)other;
		return new EqualsBuilder()
			.append(this.getPersonIdfctnHistId(), castOther.getPersonIdfctnHistId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPersonIdfctnHistId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("personIdfctnHistId", getPersonIdfctnHistId())
			.toString();
	}
}