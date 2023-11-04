package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PERSON_RACE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PersonRacePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String censusRaceTypeCd;
	private Long ptcpntId;

    public PersonRacePK() {
    }

	@Column(name="CENSUS_RACE_TYPE_CD", nullable=false, length=12)
	public String getCensusRaceTypeCd() {
		return this.censusRaceTypeCd;
	}
	public void setCensusRaceTypeCd(String censusRaceTypeCd) {
		this.censusRaceTypeCd = censusRaceTypeCd;
	}

	@Column(name="PTCPNT_ID", nullable=false, precision=15)
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
		if (!(other instanceof PersonRacePK)) {
			return false;
		}
		PersonRacePK castOther = (PersonRacePK)other;
		return new EqualsBuilder()
			.append(this.getCensusRaceTypeCd(), castOther.getCensusRaceTypeCd())
			.append(this.getPtcpntId(), castOther.getPtcpntId())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getCensusRaceTypeCd())
			.append(getPtcpntId())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("censusRaceTypeCd", getCensusRaceTypeCd())
			.append("ptcpntId", getPtcpntId())
			.toString();
	}
}