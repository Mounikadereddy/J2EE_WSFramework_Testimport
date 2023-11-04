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
 * The persistent class for the PERSON_EDU_LEVEL database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_EDU_LEVEL")
public class PersonEduLevel implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long personEduLevelId;
	private Date notfcnDt;
	private String personEduLevelTimeTypeCd;
	private String personEduLevelTypeCd;
	private Integer yearsEduNbr;
	private Person person;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonEduLevel() {
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
	@Column(name="PERSON_EDU_LEVEL_ID", unique=true, nullable=false, precision=15)
	public Long getPersonEduLevelId() {
		return this.personEduLevelId;
	}
	public void setPersonEduLevelId(Long personEduLevelId) {
		this.personEduLevelId = personEduLevelId;
	}

	@Basic()
	@Column(name="NOTFCN_DT", length=7)
	public Date getNotfcnDt() {
		return this.notfcnDt;
	}
	public void setNotfcnDt(Date notfcnDt) {
		this.notfcnDt = notfcnDt;
	}

	@Basic()
	@Column(name="PERSON_EDU_LEVEL_TIME_TYPE_CD", length=12)
	public String getPersonEduLevelTimeTypeCd() {
		return this.personEduLevelTimeTypeCd;
	}
	public void setPersonEduLevelTimeTypeCd(String personEduLevelTimeTypeCd) {
		this.personEduLevelTimeTypeCd = personEduLevelTimeTypeCd;
	}

	@Basic()
	@Column(name="PERSON_EDU_LEVEL_TYPE_CD", length=12)
	public String getPersonEduLevelTypeCd() {
		return this.personEduLevelTypeCd;
	}
	public void setPersonEduLevelTypeCd(String personEduLevelTypeCd) {
		this.personEduLevelTypeCd = personEduLevelTypeCd;
	}

	@Basic()
	@Column(name="YEARS_EDU_NBR", precision=2)
	public Integer getYearsEduNbr() {
		return this.yearsEduNbr;
	}
	public void setYearsEduNbr(Integer yearsEduNbr) {
		this.yearsEduNbr = yearsEduNbr;
	}

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
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
		if (!(other instanceof PersonEduLevel)) {
			return false;
		}
		PersonEduLevel castOther = (PersonEduLevel)other;
		return new EqualsBuilder()
			.append(this.getPersonEduLevelId(), castOther.getPersonEduLevelId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPersonEduLevelId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("personEduLevelId", getPersonEduLevelId())
			.toString();
	}
}