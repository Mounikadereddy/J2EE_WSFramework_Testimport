package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the BURIAL_BNFT_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class BurialBnftDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String awardTypeCd;
	private String burialBnftTypeCd;
	private java.sql.Timestamp decnDt;
	private Long ptcpntIdA;
	private Long ptcpntIdB;

    public BurialBnftDecnPK() {
    }

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
	}

	@Column(name="BURIAL_BNFT_TYPE_CD", nullable=false, length=12)
	public String getBurialBnftTypeCd() {
		return this.burialBnftTypeCd;
	}
	public void setBurialBnftTypeCd(String burialBnftTypeCd) {
		this.burialBnftTypeCd = burialBnftTypeCd;
	}

	@Column(name="DECN_DT", nullable=false, length=7)
	public java.sql.Timestamp getDecnDt() {
		return this.decnDt;
	}
	public void setDecnDt(java.sql.Timestamp decnDt) {
		this.decnDt = decnDt;
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
		if (!(other instanceof BurialBnftDecnPK)) {
			return false;
		}
		BurialBnftDecnPK castOther = (BurialBnftDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getBurialBnftTypeCd(), castOther.getBurialBnftTypeCd())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardTypeCd())
			.append(getBurialBnftTypeCd())
			.append(getDecnDt())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardTypeCd", getAwardTypeCd())
			.append("burialBnftTypeCd", getBurialBnftTypeCd())
			.append("decnDt", getDecnDt())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.toString();
	}
}