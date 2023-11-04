package gov.va.vba.framework.domain.entities;

import gov.va.vba.framework.common.DateAdapter;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * The persistent class for the CPMR_CHILD_BIRTH database table.
 * 
 * @author BEA Workshop
 */
@Entity()
@Table(name="CPMR_CHILD_BIRTH")
public class CpmrChildBirth  implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private Long cpmrBasicSgmntId;
	private String birthFiller;
	private Integer birthLength;
	private java.sql.Date cnvrsnDt;
	private String fileNbr;
	private Integer numberBirthDates;
	private String payeeNbr;
	private Long ptcpntId;
	private String totalNumberChldrn;
	private java.util.Set <CpmrChildBirthSgmnt> cpmrChildBirthSgmnts;

    public CpmrChildBirth() {
    }

	@Id()
	@Column(name="CPMR_BASIC_SGMNT_ID", unique=true, nullable=false, precision=15)
	public Long getCpmrBasicSgmntId() {
		return this.cpmrBasicSgmntId;
	}
	public void setCpmrBasicSgmntId(Long cpmrBasicSgmntId) {
		this.cpmrBasicSgmntId = cpmrBasicSgmntId;
	}

	@Basic()
	@Column(name="BIRTH_FILLER", length=3)
	public String getBirthFiller() {
		return this.birthFiller;
	}
	public void setBirthFiller(String birthFiller) {
		this.birthFiller = birthFiller;
	}

	@Basic()
	@Column(name="BIRTH_LENGTH", precision=3)
	public Integer getBirthLength() {
		return this.birthLength;
	}
	public void setBirthLength(Integer birthLength) {
		this.birthLength = birthLength;
	}

	@Basic()
	@Column(name="CNVRSN_DT", nullable=false, length=7)
	@XmlJavaTypeAdapter(DateAdapter.class)
	public java.sql.Date getCnvrsnDt() {
		return this.cnvrsnDt;
	}
	public void setCnvrsnDt(java.sql.Date cnvrsnDt) {
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
	@Column(name="NUMBER_BIRTH_DATES", precision=2)
	public Integer getNumberBirthDates() {
		return this.numberBirthDates;
	}
	public void setNumberBirthDates(Integer numberBirthDates) {
		this.numberBirthDates = numberBirthDates;
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

	@Basic()
	@Column(name="TOTAL_NUMBER_CHLDRN", length=2)
	public String getTotalNumberChldrn() {
		return this.totalNumberChldrn;
	}
	public void setTotalNumberChldrn(String totalNumberChldrn) {
		this.totalNumberChldrn = totalNumberChldrn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CpmrChildBirth)) {
			return false;
		}
		CpmrChildBirth castOther = (CpmrChildBirth)other;
		return new EqualsBuilder()
			.append(this.getCpmrBasicSgmntId(), castOther.getCpmrBasicSgmntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCpmrBasicSgmntId())
			.toHashCode();
    }   

	public String toString() {
		return new ToStringBuilder(this)
			.append("cpmrBasicSgmntId", getCpmrBasicSgmntId())
			.toString();
	}
	@Transient
	public java.util.Set<CpmrChildBirthSgmnt> getCpmrChildBirthSgmnts() {
		return cpmrChildBirthSgmnts;
	}

	public void setCpmrChildBirthSgmnts(
			java.util.Set<CpmrChildBirthSgmnt> cpmrChildBirthSgmnts) {
		this.cpmrChildBirthSgmnts = cpmrChildBirthSgmnts;
	}
	
}