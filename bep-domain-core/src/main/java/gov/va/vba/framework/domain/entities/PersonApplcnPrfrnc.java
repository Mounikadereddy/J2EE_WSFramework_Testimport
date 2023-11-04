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
 * The persistent class for the PERSON_APPLCN_PRFRNC database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_APPLCN_PRFRNC")
public class PersonApplcnPrfrnc implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private PersonApplcnPrfrncPK compId;
	private String prfrncTextInd;
	private Double prfrncValueNbr;
	private String prfrncValueTxt;
	private Applcn applcn;
	private Person person;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonApplcnPrfrnc() {
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
	public PersonApplcnPrfrncPK getCompId() {
		return this.compId;
	}
	public void setCompId(PersonApplcnPrfrncPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="PRFRNC_TEXT_IND", nullable=false, length=1)
	public String getPrfrncTextInd() {
		return this.prfrncTextInd;
	}
	public void setPrfrncTextInd(String prfrncTextInd) {
		this.prfrncTextInd = prfrncTextInd;
	}

	@Basic()
	@Column(name="PRFRNC_VALUE_NBR", precision=15, scale=2)
	public Double getPrfrncValueNbr() {
		return this.prfrncValueNbr;
	}
	public void setPrfrncValueNbr(Double prfrncValueNbr) {
		this.prfrncValueNbr = prfrncValueNbr;
	}

	@Basic()
	@Column(name="PRFRNC_VALUE_TXT", length=250)
	public String getPrfrncValueTxt() {
		return this.prfrncValueTxt;
	}
	public void setPrfrncValueTxt(String prfrncValueTxt) {
		this.prfrncValueTxt = prfrncValueTxt;
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
		if (!(other instanceof PersonApplcnPrfrnc)) {
			return false;
		}
		PersonApplcnPrfrnc castOther = (PersonApplcnPrfrnc)other;
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