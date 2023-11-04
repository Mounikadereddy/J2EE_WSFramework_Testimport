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
 * The persistent class for the PERSON_DEP database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_DEP")
public class PersonDep implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PersonDepPK compId;
	private String cmntsTxt;
	private int depAgeNbr;
	private String firstNm;
	private String lastNm;
	private String middleNm;
	private Person person;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonDep() {
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
	public PersonDepPK getCompId() {
		return this.compId;
	}
	public void setCompId(PersonDepPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="CMNTS_TXT", length=80)
	public String getCmntsTxt() {
		return this.cmntsTxt;
	}
	public void setCmntsTxt(String cmntsTxt) {
		this.cmntsTxt = cmntsTxt;
	}

	@Basic()
	@Column(name="DEP_AGE_NBR", nullable=false, precision=2)
	public int getDepAgeNbr() {
		return this.depAgeNbr;
	}
	public void setDepAgeNbr(int depAgeNbr) {
		this.depAgeNbr = depAgeNbr;
	}

	@Basic()
	@Column(name="FIRST_NM", length=30)
	public String getFirstNm() {
		return this.firstNm;
	}
	public void setFirstNm(String firstNm) {
		this.firstNm = firstNm;
	}

	@Basic()
	@Column(name="LAST_NM", length=30)
	public String getLastNm() {
		return this.lastNm;
	}
	public void setLastNm(String lastNm) {
		this.lastNm = lastNm;
	}

	@Basic()
	@Column(name="MIDDLE_NM", length=30)
	public String getMiddleNm() {
		return this.middleNm;
	}
	public void setMiddleNm(String middleNm) {
		this.middleNm = middleNm;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonDep)) {
			return false;
		}
		PersonDep castOther = (PersonDep)other;
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