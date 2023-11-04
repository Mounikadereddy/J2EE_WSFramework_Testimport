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
 * The persistent class for the PERSON_EMPLYR database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_EMPLYR")
public class PersonEmplyr implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long personEmplyrId;
	private String lctnTxt;
	private Integer monthsEmplydNbr;
	private String nm;
	private String ocptnDescpTxt;
	private Integer phoneExtnsnNbr;
	private Long phoneNbr;
	private Date updateDt;
//	private CnslgSesion cnslgSesion;
	private Person person;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonEmplyr() {
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
	@Column(name="PERSON_EMPLYR_ID", unique=true, nullable=false, precision=15)
	public Long getPersonEmplyrId() {
		return this.personEmplyrId;
	}
	public void setPersonEmplyrId(Long personEmplyrId) {
		this.personEmplyrId = personEmplyrId;
	}

	@Basic()
	@Column(name="LCTN_TXT", length=254)
	public String getLctnTxt() {
		return this.lctnTxt;
	}
	public void setLctnTxt(String lctnTxt) {
		this.lctnTxt = lctnTxt;
	}

	@Basic()
	@Column(name="MONTHS_EMPLYD_NBR", precision=3)
	public Integer getMonthsEmplydNbr() {
		return this.monthsEmplydNbr;
	}
	public void setMonthsEmplydNbr(Integer monthsEmplydNbr) {
		this.monthsEmplydNbr = monthsEmplydNbr;
	}

	@Basic()
	@Column(name="NM", nullable=false, length=40)
	public String getNm() {
		return this.nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}

	@Basic()
	@Column(name="OCPTN_DESCP_TXT", length=40)
	public String getOcptnDescpTxt() {
		return this.ocptnDescpTxt;
	}
	public void setOcptnDescpTxt(String ocptnDescpTxt) {
		this.ocptnDescpTxt = ocptnDescpTxt;
	}

	@Basic()
	@Column(name="PHONE_EXTNSN_NBR", precision=5)
	public Integer getPhoneExtnsnNbr() {
		return this.phoneExtnsnNbr;
	}
	public void setPhoneExtnsnNbr(Integer phoneExtnsnNbr) {
		this.phoneExtnsnNbr = phoneExtnsnNbr;
	}

	@Basic()
	@Column(name="PHONE_NBR", precision=10)
	public Long getPhoneNbr() {
		return this.phoneNbr;
	}
	public void setPhoneNbr(Long phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

	@Basic()
	@Column(name="UPDATE_DT", nullable=false, length=7)
	public Date getUpdateDt() {
		return this.updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	//bi-directional many-to-one association to CnslgSesion
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="CNSLG_SESION_ID", referencedColumnName="CNSLG_SESION_ID", nullable=false)
//	public CnslgSesion getCnslgSesion() {
//		return this.cnslgSesion;
//	}
//	public void setCnslgSesion(CnslgSesion cnslgSesion) {
//		this.cnslgSesion = cnslgSesion;
//	}

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
		if (!(other instanceof PersonEmplyr)) {
			return false;
		}
		PersonEmplyr castOther = (PersonEmplyr)other;
		return new EqualsBuilder()
			.append(this.getPersonEmplyrId(), castOther.getPersonEmplyrId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPersonEmplyrId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("personEmplyrId", getPersonEmplyrId())
			.toString();
	}
}