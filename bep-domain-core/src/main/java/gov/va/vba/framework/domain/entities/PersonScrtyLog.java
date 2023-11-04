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
 * The persistent class for the PERSON_SCRTY_LOG database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_SCRTY_LOG")
public class PersonScrtyLog  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PersonScrtyLogPK compId;
	private Integer bdnEmpNbr;
	private String bdnPswrdTxt;
	private Date beginDt;
	private Date endDt;
	private String fteExemptInd;
	private String fteExemptTxt;
	private String netwrkLogonNm;
	private Boolean pswrdAtmptQty;
	private Date pswrdDsablDt;
	private Date pswrdEfctvDt;
	private String pswrdReasonNm;
	private String pswrdTxt;
	private java.util.Set<ApplcnPersonRole> applcnPersonRoles;
	private Person person;
	private Stn stn;
	private java.util.Set<PersonScrtyPrevPswrd> personScrtyPrevPswrds;
	private Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonScrtyLog() {
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
	public PersonScrtyLogPK getCompId() {
		return this.compId;
	}
	public void setCompId(PersonScrtyLogPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="BDN_EMP_NBR", precision=4)
	public Integer getBdnEmpNbr() {
		return this.bdnEmpNbr;
	}
	public void setBdnEmpNbr(Integer bdnEmpNbr) {
		this.bdnEmpNbr = bdnEmpNbr;
	}

	@Basic()
	@Column(name="BDN_PSWRD_TXT", length=50)
	public String getBdnPswrdTxt() {
		return this.bdnPswrdTxt;
	}
	public void setBdnPswrdTxt(String bdnPswrdTxt) {
		this.bdnPswrdTxt = bdnPswrdTxt;
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
	@Column(name="END_DT", length=7)
	public Date getEndDt() {
		return this.endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	@Basic()
	@Column(name="FTE_EXEMPT_IND", length=1)
	public String getFteExemptInd() {
		return this.fteExemptInd;
	}
	public void setFteExemptInd(String fteExemptInd) {
		this.fteExemptInd = fteExemptInd;
	}

	@Basic()
	@Column(name="FTE_EXEMPT_TXT", length=100)
	public String getFteExemptTxt() {
		return this.fteExemptTxt;
	}
	public void setFteExemptTxt(String fteExemptTxt) {
		this.fteExemptTxt = fteExemptTxt;
	}

	@Basic()
	@Column(name="NETWRK_LOGON_NM", nullable=false, length=15)
	public String getNetwrkLogonNm() {
		return this.netwrkLogonNm;
	}
	public void setNetwrkLogonNm(String netwrkLogonNm) {
		this.netwrkLogonNm = netwrkLogonNm;
	}

	@Basic()
	@Column(name="PSWRD_ATMPT_QTY", precision=1)
	public Boolean getPswrdAtmptQty() {
		return this.pswrdAtmptQty;
	}
	public void setPswrdAtmptQty(Boolean pswrdAtmptQty) {
		this.pswrdAtmptQty = pswrdAtmptQty;
	}

	@Basic()
	@Column(name="PSWRD_DSABL_DT", length=7)
	public Date getPswrdDsablDt() {
		return this.pswrdDsablDt;
	}
	public void setPswrdDsablDt(Date pswrdDsablDt) {
		this.pswrdDsablDt = pswrdDsablDt;
	}

	@Basic()
	@Column(name="PSWRD_EFCTV_DT", length=7)
	public Date getPswrdEfctvDt() {
		return this.pswrdEfctvDt;
	}
	public void setPswrdEfctvDt(Date pswrdEfctvDt) {
		this.pswrdEfctvDt = pswrdEfctvDt;
	}

	@Basic()
	@Column(name="PSWRD_REASON_NM", length=50)
	public String getPswrdReasonNm() {
		return this.pswrdReasonNm;
	}
	public void setPswrdReasonNm(String pswrdReasonNm) {
		this.pswrdReasonNm = pswrdReasonNm;
	}

	@Basic()
	@Column(name="PSWRD_TXT", length=50)
	public String getPswrdTxt() {
		return this.pswrdTxt;
	}
	public void setPswrdTxt(String pswrdTxt) {
		this.pswrdTxt = pswrdTxt;
	}

	//bi-directional many-to-one association to ApplcnPersonRole
	@OneToMany(mappedBy="personScrtyLog", fetch=FetchType.LAZY)
	public java.util.Set<ApplcnPersonRole> getApplcnPersonRoles() {
		return this.applcnPersonRoles;
	}
	public void setApplcnPersonRoles(java.util.Set<ApplcnPersonRole> applcnPersonRoles) {
		this.applcnPersonRoles = applcnPersonRoles;
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

	//bi-directional many-to-one association to Stn
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LCTN_ID", referencedColumnName="LCTN_ID", nullable=false, insertable=false, updatable=false)
	public Stn getStn() {
		return this.stn;
	}
	public void setStn(Stn stn) {
		this.stn = stn;
	}

	//bi-directional many-to-one association to PersonScrtyPrevPswrd
	@OneToMany(mappedBy="personScrtyLog", fetch=FetchType.LAZY)
	public java.util.Set<PersonScrtyPrevPswrd> getPersonScrtyPrevPswrds() {
		return this.personScrtyPrevPswrds;
	}
	public void setPersonScrtyPrevPswrds(java.util.Set<PersonScrtyPrevPswrd> personScrtyPrevPswrds) {
		this.personScrtyPrevPswrds = personScrtyPrevPswrds;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonScrtyLog)) {
			return false;
		}
		PersonScrtyLog castOther = (PersonScrtyLog)other;
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