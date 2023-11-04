package gov.va.vba.framework.domain.entities;

import java.io.Serializable;

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
 * The persistent class for the PERSON_NET_WORTH database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="PERSON_NET_WORTH")
public class PersonNetWorth implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long personNetWorthId;
	private Long assetAmt;
	private long cnslgSesionId;
	private Long liabtyAmt;
	private Long mtgeLoanId;
	private String netWorthTypeNm;
	private Person person;
	private java.util.Date jrnDt;
	private String jrnLctnId;
	private String jrnObjId;
	private String jrnStatusTypeCd;
	private String jrnUserId;

    public PersonNetWorth() {
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
	
	@Id()
	@Column(name="PERSON_NET_WORTH_ID", unique=true, nullable=false, precision=15)
	public Long getPersonNetWorthId() {
		return this.personNetWorthId;
	}
	public void setPersonNetWorthId(Long personNetWorthId) {
		this.personNetWorthId = personNetWorthId;
	}

	@Basic()
	@Column(name="ASSET_AMT", precision=10)
	public Long getAssetAmt() {
		return this.assetAmt;
	}
	public void setAssetAmt(Long assetAmt) {
		this.assetAmt = assetAmt;
	}

	@Basic()
	@Column(name="CNSLG_SESION_ID", nullable=false, precision=15)
	public long getCnslgSesionId() {
		return this.cnslgSesionId;
	}
	public void setCnslgSesionId(long cnslgSesionId) {
		this.cnslgSesionId = cnslgSesionId;
	}

	@Basic()
	@Column(name="LIABTY_AMT", precision=10)
	public Long getLiabtyAmt() {
		return this.liabtyAmt;
	}
	public void setLiabtyAmt(Long liabtyAmt) {
		this.liabtyAmt = liabtyAmt;
	}

	@Basic()
	@Column(name="MTGE_LOAN_ID", precision=15)
	public Long getMtgeLoanId() {
		return this.mtgeLoanId;
	}
	public void setMtgeLoanId(Long mtgeLoanId) {
		this.mtgeLoanId = mtgeLoanId;
	}

	@Basic()
	@Column(name="NET_WORTH_TYPE_NM", nullable=false, length=50)
	public String getNetWorthTypeNm() {
		return this.netWorthTypeNm;
	}
	public void setNetWorthTypeNm(String netWorthTypeNm) {
		this.netWorthTypeNm = netWorthTypeNm;
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
		if (!(other instanceof PersonNetWorth)) {
			return false;
		}
		PersonNetWorth castOther = (PersonNetWorth)other;
		return new EqualsBuilder()
			.append(this.getPersonNetWorthId(), castOther.getPersonNetWorthId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPersonNetWorthId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("personNetWorthId", getPersonNetWorthId())
			.toString();
	}
}