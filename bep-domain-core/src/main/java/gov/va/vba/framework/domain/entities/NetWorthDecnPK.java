package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the NET_WORTH_DECN database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class NetWorthDecnPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String awardTypeCd;
	private java.sql.Timestamp decnDt;
	private java.sql.Timestamp netWorthDt;
	private Long ptcpntIdA;
	private Long ptcpntIdB;

    public NetWorthDecnPK() {
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

	@Column(name="NET_WORTH_DT", nullable=false, length=7)
	public java.sql.Timestamp getNetWorthDt() {
		return this.netWorthDt;
	}
	public void setNetWorthDt(java.sql.Timestamp netWorthDt) {
		this.netWorthDt = netWorthDt;
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
		if (!(other instanceof NetWorthDecnPK)) {
			return false;
		}
		NetWorthDecnPK castOther = (NetWorthDecnPK)other;
		return new EqualsBuilder()
			.append(this.getAwardTypeCd(), castOther.getAwardTypeCd())
			.append(this.getDecnDt(), castOther.getDecnDt())
			.append(this.getNetWorthDt(), castOther.getNetWorthDt())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAwardTypeCd())
			.append(getDecnDt())
			.append(getNetWorthDt())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("awardTypeCd", getAwardTypeCd())
			.append("decnDt", getDecnDt())
			.append("netWorthDt", getNetWorthDt())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.toString();
	}
}