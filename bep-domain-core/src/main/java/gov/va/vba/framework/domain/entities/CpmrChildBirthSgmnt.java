package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * The persistent class for the CPMR_CHILD_BIRTH_SGMNT database table.
 * 
 * @author BEA Workshop
 */
@SqlResultSetMapping( name="cpmrChildBirthSgmnt", entities={@EntityResult(entityClass=CpmrChildBirthSgmnt.class)})
@Entity()
@Table(name="CPMR_CHILD_BIRTH_SGMNT")
public class CpmrChildBirthSgmnt  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private CpmrChildBirthSgmntPK compId;
	private String childBirthAnnualSsa;
	private String childBirthChangeDate;
	private String childBirthChangeReason;
	private String childBirthDate;
	private String childBirthDductnAmount;
	private String childBirthDductnType;
	private String childBirthErngs;
	private String childBirthFiller;
	private String childBirthIncomeOther;
	private Boolean childBirthIncomeYear;
	private Double childBirthMthlySsa;
	private String childBirthName;
	private String childBirthNetWorth;
	private String childBirthSex;
	private String childBirthSsnIndctr;
	private String childBirthStatus;
	private String childBirthType;
	private String childBirthVerifdSsn;
	private Date cnvrsnDt;
	private String fileNbr;
	private String payeeNbr;
	private Long ptcpntId;

    public CpmrChildBirthSgmnt() {
    }

	@EmbeddedId
	public CpmrChildBirthSgmntPK getCompId() {
		return this.compId;
	}
	public void setCompId(CpmrChildBirthSgmntPK compId) {
		this.compId = compId;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_ANNUAL_SSA", length=5)
	public String getChildBirthAnnualSsa() {
		return this.childBirthAnnualSsa;
	}
	public void setChildBirthAnnualSsa(String childBirthAnnualSsa) {
		this.childBirthAnnualSsa = childBirthAnnualSsa;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_CHANGE_DATE", length=8)
	public String getChildBirthChangeDate() {
		return this.childBirthChangeDate;
	}
	public void setChildBirthChangeDate(String childBirthChangeDate) {
		this.childBirthChangeDate = childBirthChangeDate;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_CHANGE_REASON", length=2)
	public String getChildBirthChangeReason() {
		return this.childBirthChangeReason;
	}
	public void setChildBirthChangeReason(String childBirthChangeReason) {
		this.childBirthChangeReason = childBirthChangeReason;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_DATE", length=8)
	public String getChildBirthDate() {
		return this.childBirthDate;
	}
	public void setChildBirthDate(String childBirthDate) {
		this.childBirthDate = childBirthDate;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_DDUCTN_AMOUNT", length=5)
	public String getChildBirthDductnAmount() {
		return this.childBirthDductnAmount;
	}
	public void setChildBirthDductnAmount(String childBirthDductnAmount) {
		this.childBirthDductnAmount = childBirthDductnAmount;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_DDUCTN_TYPE", length=1)
	public String getChildBirthDductnType() {
		return this.childBirthDductnType;
	}
	public void setChildBirthDductnType(String childBirthDductnType) {
		this.childBirthDductnType = childBirthDductnType;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_ERNGS", length=5)
	public String getChildBirthErngs() {
		return this.childBirthErngs;
	}
	public void setChildBirthErngs(String childBirthErngs) {
		this.childBirthErngs = childBirthErngs;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_FILLER", length=2)
	public String getChildBirthFiller() {
		return this.childBirthFiller;
	}
	public void setChildBirthFiller(String childBirthFiller) {
		this.childBirthFiller = childBirthFiller;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_INCOME_OTHER", length=5)
	public String getChildBirthIncomeOther() {
		return this.childBirthIncomeOther;
	}
	public void setChildBirthIncomeOther(String childBirthIncomeOther) {
		this.childBirthIncomeOther = childBirthIncomeOther;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_INCOME_YEAR", precision=1)
	public Boolean getChildBirthIncomeYear() {
		return this.childBirthIncomeYear;
	}
	public void setChildBirthIncomeYear(Boolean childBirthIncomeYear) {
		this.childBirthIncomeYear = childBirthIncomeYear;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_MTHLY_SSA", precision=6, scale=2)
	public Double getChildBirthMthlySsa() {
		return this.childBirthMthlySsa;
	}
	public void setChildBirthMthlySsa(Double childBirthMthlySsa) {
		this.childBirthMthlySsa = childBirthMthlySsa;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_NAME", length=10)
	public String getChildBirthName() {
		return this.childBirthName;
	}
	public void setChildBirthName(String childBirthName) {
		this.childBirthName = childBirthName;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_NET_WORTH", length=2)
	public String getChildBirthNetWorth() {
		return this.childBirthNetWorth;
	}
	public void setChildBirthNetWorth(String childBirthNetWorth) {
		this.childBirthNetWorth = childBirthNetWorth;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_SEX", length=1)
	public String getChildBirthSex() {
		return this.childBirthSex;
	}
	public void setChildBirthSex(String childBirthSex) {
		this.childBirthSex = childBirthSex;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_SSN_INDCTR", length=1)
	public String getChildBirthSsnIndctr() {
		return this.childBirthSsnIndctr;
	}
	public void setChildBirthSsnIndctr(String childBirthSsnIndctr) {
		this.childBirthSsnIndctr = childBirthSsnIndctr;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_STATUS", length=1)
	public String getChildBirthStatus() {
		return this.childBirthStatus;
	}
	public void setChildBirthStatus(String childBirthStatus) {
		this.childBirthStatus = childBirthStatus;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_TYPE", length=1)
	public String getChildBirthType() {
		return this.childBirthType;
	}
	public void setChildBirthType(String childBirthType) {
		this.childBirthType = childBirthType;
	}

	@Basic()
	@Column(name="CHILD_BIRTH_VERIFD_SSN", length=1)
	public String getChildBirthVerifdSsn() {
		return this.childBirthVerifdSsn;
	}
	public void setChildBirthVerifdSsn(String childBirthVerifdSsn) {
		this.childBirthVerifdSsn = childBirthVerifdSsn;
	}

	@Basic()
	@Column(name="CNVRSN_DT", nullable=false, length=7)
	public Date getCnvrsnDt() {
		return this.cnvrsnDt;
	}
	public void setCnvrsnDt(Date cnvrsnDt) {
		this.cnvrsnDt = cnvrsnDt;
	}

	@Basic()
	@Column(name="FILE_NBR", nullable=false, length=9)
	public String getFileNbr() {
		return this.fileNbr;
	}
	public void setFileNbr(String fileNbr) {
		this.fileNbr = fileNbr;
	}

	@Basic()
	@Column(name="PAYEE_NBR", nullable=false, length=2)
	public String getPayeeNbr() {
		return this.payeeNbr;
	}
	public void setPayeeNbr(String payeeNbr) {
		this.payeeNbr = payeeNbr;
	}

	@Basic()
	@Column(name="PTCPNT_ID", precision=15)
	public Long getPtcpntId() {
		return this.ptcpntId;
	}
	public void setPtcpntId(Long ptcpntId) {
		this.ptcpntId = ptcpntId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CpmrChildBirthSgmnt)) {
			return false;
		}
		CpmrChildBirthSgmnt castOther = (CpmrChildBirthSgmnt)other;
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