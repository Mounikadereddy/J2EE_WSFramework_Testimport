package gov.va.vba.framework.domain.entities;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * The primary key class for the PTCPNT_RLNSHP_APRVD_STATE database table.
 * 
 * @author BEA Workshop
 */
@Embeddable()
public class PtcpntRlnshpAprvdStatePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String postalCd;
	private Long ptcpntIdA;
	private Long ptcpntIdB;
	private String ptcpntRlnshpTypeNm;

    public PtcpntRlnshpAprvdStatePK() {
    }

	@Column(name="POSTAL_CD", nullable=false, length=2)
	public String getPostalCd() {
		return this.postalCd;
	}
	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
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

	@Column(name="PTCPNT_RLNSHP_TYPE_NM", nullable=false, length=50)
	public String getPtcpntRlnshpTypeNm() {
		return this.ptcpntRlnshpTypeNm;
	}
	public void setPtcpntRlnshpTypeNm(String ptcpntRlnshpTypeNm) {
		this.ptcpntRlnshpTypeNm = ptcpntRlnshpTypeNm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtcpntRlnshpAprvdStatePK)) {
			return false;
		}
		PtcpntRlnshpAprvdStatePK castOther = (PtcpntRlnshpAprvdStatePK)other;
		return new EqualsBuilder()
			.append(this.getPostalCd(), castOther.getPostalCd())
			.append(this.getPtcpntIdA(), castOther.getPtcpntIdA())
			.append(this.getPtcpntIdB(), castOther.getPtcpntIdB())
			.append(this.getPtcpntRlnshpTypeNm(), castOther.getPtcpntRlnshpTypeNm())
			.isEquals();
    }
    
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getPostalCd())
			.append(getPtcpntIdA())
			.append(getPtcpntIdB())
			.append(getPtcpntRlnshpTypeNm())
			.toHashCode();
    }

	public String toString() {
		return new ToStringBuilder(this)
			.append("postalCd", getPostalCd())
			.append("ptcpntIdA", getPtcpntIdA())
			.append("ptcpntIdB", getPtcpntIdB())
			.append("ptcpntRlnshpTypeNm", getPtcpntRlnshpTypeNm())
			.toString();
	}
}