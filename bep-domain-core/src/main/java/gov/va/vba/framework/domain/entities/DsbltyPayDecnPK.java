package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the DSBLTY_PAY_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class DsbltyPayDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String awardTypeCd;
	private java.sql.Timestamp decnDt;
	private String dsbltyPayTypeCd;
	private Long ptcpntIdA;
	private Long ptcpntIdB;

    public DsbltyPayDecnPK() {
    }

	@Column(name="AWARD_TYPE_CD", nullable=false, length=12)
	public String getAwardTypeCd() {
		return this.awardTypeCd;
	}
	public void setAwardTypeCd(String awardTypeCd) {
		this.awardTypeCd = awardTypeCd;
	}

	@Column(name="DECN_DT", nullable=false, length=7)
	public java.sql.Timestamp getDecnDt() {
		return this.decnDt;
	}
	public void setDecnDt(java.sql.Timestamp decnDt) {
		this.decnDt = decnDt;
	}

	@Column(name="DSBLTY_PAY_TYPE_CD", nullable=false, length=12)
	public String getDsbltyPayTypeCd() {
		return this.dsbltyPayTypeCd;
	}
	public void setDsbltyPayTypeCd(String dsbltyPayTypeCd) {
		this.dsbltyPayTypeCd = dsbltyPayTypeCd;
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
		if (!(other instanceof DsbltyPayDecnPK)) {
			return false;
		}
		DsbltyPayDecnPK castOther = (DsbltyPayDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getDsbltyPayTypeCd(), castOther.getDsbltyPayTypeCd())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardTypeCd())
			.append(getDecnDt())
			.append(getDsbltyPayTypeCd())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardTypeCd", getAwardTypeCd())
			.append("decnDt", getDecnDt())
			.append("dsbltyPayTypeCd", getDsbltyPayTypeCd())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.toString();
	}
}