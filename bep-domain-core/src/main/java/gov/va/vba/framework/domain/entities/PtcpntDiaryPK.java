package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_DIARY database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntDiaryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private java.sql.Timestamp dt;
	private String ptcpntDiaryTypeNm;
	private Long ptcpntIdA;

    public PtcpntDiaryPK() {
    }

	@Column(name="DT", nullable=false, length=7)
	public java.sql.Timestamp getDt() {
		return this.dt;
	}
	public void setDt(java.sql.Timestamp dt) {
		this.dt = dt;
	}

	@Column(name="PTCPNT_DIARY_TYPE_NM", nullable=false, length=50)
	public String getPtcpntDiaryTypeNm() {
		return this.ptcpntDiaryTypeNm;
	}
	public void setPtcpntDiaryTypeNm(String ptcpntDiaryTypeNm) {
		this.ptcpntDiaryTypeNm = ptcpntDiaryTypeNm;
	}

	@Column(name="PTCPNT_ID_A", nullable=false, precision=15)
	public Long getPtcpntIdA() {
		return this.ptcpntIdA;
	}
	public void setPtcpntIdA(Long ptcpntIdA) {
		this.ptcpntIdA = ptcpntIdA;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntDiaryPK)) {
			return false;
		}
		PtcpntDiaryPK castOther = (PtcpntDiaryPK)other;
		return new EqualsBuilder()
			.append(this.getDt(), castOther.getDt())
			.append(this.getPtcpntDiaryTypeNm(), castOther.getPtcpntDiaryTypeNm())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDt())
			.append(getPtcpntDiaryTypeNm())
			.append(getPtcpntIdA())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("dt", getDt())
			.append("ptcpntDiaryTypeNm", getPtcpntDiaryTypeNm())
			.append("ptcpntIdA", getPtcpntIdA())
			.toString();
	}
}