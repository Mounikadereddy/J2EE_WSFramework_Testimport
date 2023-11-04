package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the AWARD database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class AwardPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
//	private Long awardId;
	private String awardTypeCd;
	private Long ptcpntIdA;
	private Long ptcpntIdB;

    public AwardPK() {
    }

//	@Column(name="AWARD_ID", nullable=false, precision=15)
//	public Long getAwardId() {
//		return this.awardId;
//	}
//	public void setAwardId(Long awardId) {
//		this.awardId = awardId;
//	}

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AwardPK)) {
			return false;
		}
		AwardPK castOther = (AwardPK)other;
		return new EqualsBuilder()
//			.append(this.getAwardId(), castOther.getAwardId())
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
//			.append(getAwardId())
			.append(getAwardTypeCd())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
//			.append("awardId", getAwardId())
			.append("awardTypeCd", getAwardTypeCd())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.toString();
	}
}