package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the INCOME_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class IncomeDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String awardTypeCd;
	private java.sql.Timestamp beginDt;
	private java.sql.Timestamp decnDt;
	private String frqncyTypeCd;
	private String personFinTypeCd;
	private Long ptcpntIdA;
	private Long ptcpntIdB;
	private Long ptcpntIdC;

    public IncomeDecnPK() {
    }

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
	}

	@Column(name="BEGIN_DT", nullable=false, length=7)
	public java.sql.Timestamp getBeginDt() {
		return this.beginDt;
	}
	public void setBeginDt(java.sql.Timestamp beginDt) {
		this.beginDt = beginDt;
	}

	@Column(name="DECN_DT", nullable=false, length=7)
	public java.sql.Timestamp getDecnDt() {
		return this.decnDt;
	}
	public void setDecnDt(java.sql.Timestamp decnDt) {
		this.decnDt = decnDt;
	}

	@Column(name="FRQNCY_TYPE_CD", nullable=false, length=12)
	public String getFrqncyTypeCd() {
		return this.frqncyTypeCd;
	}
	public void setFrqncyTypeCd(String frqncyTypeCd) {
		this.frqncyTypeCd = frqncyTypeCd;
	}

	@Column(name="PERSON_FIN_TYPE_CD", nullable=false, length=12)
	public String getPersonFinTypeCd() {
		return this.personFinTypeCd;
	}
	public void setPersonFinTypeCd(String personFinTypeCd) {
		this.personFinTypeCd = personFinTypeCd;
	}

	@Column(name="PTCPNT_ID_A", nullable=false, precision=15)
	public Long getPtcpntIdA() {
		return this.ptcpntIdA;
	}
	public void setPtcpntIdA(Long ptcpntIdA) {
		this.ptcpntIdA = ptcpntIdA;
	}

	@Column(name="PTCPNT_ID_B", nullable=false, precision=15)
	public Long getPtcpntIdB() {
		return this.ptcpntIdB;
	}
	public void setPtcpntIdB(Long ptcpntIdB) {
		this.ptcpntIdB = ptcpntIdB;
	}

	@Column(name="PTCPNT_ID_C", nullable=false, precision=15)
	public Long getPtcpntIdC() {
		return this.ptcpntIdC;
	}
	public void setPtcpntIdC(Long ptcpntIdC) {
		this.ptcpntIdC = ptcpntIdC;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof IncomeDecnPK)) {
			return false;
		}
		IncomeDecnPK castOther = (IncomeDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getBeginDt(), castOther.getBeginDt())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getFrqncyTypeCd(), castOther.getFrqncyTypeCd())
			.append(this.getPersonFinTypeCd(), castOther.getPersonFinTypeCd())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.append(this.getPtcpntIdC(), castOther.getPtcpntIdC())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardTypeCd())
			.append(getBeginDt())
			.append(getDecnDt())
			.append(getFrqncyTypeCd())
			.append(getPersonFinTypeCd())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.append(getPtcpntIdC())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardTypeCd", getAwardTypeCd())
			.append("beginDt", getBeginDt())
			.append("decnDt", getDecnDt())
			.append("frqncyTypeCd", getFrqncyTypeCd())
			.append("personFinTypeCd", getPersonFinTypeCd())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.append("ptcpntIdC", getPtcpntIdC())
			.toString();
	}
}